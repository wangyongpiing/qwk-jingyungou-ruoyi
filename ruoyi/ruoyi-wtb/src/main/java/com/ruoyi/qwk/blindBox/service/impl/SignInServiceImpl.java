package com.ruoyi.qwk.blindBox.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.Arith;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.qwk.domain.AssetDetails;
import com.ruoyi.qwk.domain.UserAssets;
import com.ruoyi.qwk.mapper.AssetDetailsMapper;
import com.ruoyi.qwk.mapper.UserAssetsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.SignInMapper;
import com.ruoyi.qwk.blindBox.domain.SignIn;
import com.ruoyi.qwk.blindBox.service.ISignInService;
import org.springframework.util.ObjectUtils;

/**
 * 签到Service业务层处理
 * 
 * @author cjf
 * @date 2023-11-03
 */
@Service
public class SignInServiceImpl implements ISignInService 
{
    @Autowired
    private SignInMapper signInMapper;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    /**
     * 查询签到
     * 
     * @param id 签到主键
     * @return 签到
     */
    @Override
    public SignIn selectSignInById(Long id)
    {
        return signInMapper.selectSignInById(id);
    }

    /**
     * 查询签到列表
     * 
     * @param signIn 签到
     * @return 签到
     */
    @Override
    public List<SignIn> selectSignInList(SignIn signIn)
    {
        return signInMapper.selectSignInList(signIn);
    }

    /**
     * 新增签到
     * 
     * @param signIn 签到
     * @return 结果
     */
    @Override
    public int insertSignIn(SignIn signIn)
    {
        signIn.setCreateTime(DateUtils.getNowDate());
        return signInMapper.insertSignIn(signIn);
    }

    /**
     * 修改签到
     * 
     * @param signIn 签到
     * @return 结果
     */
    @Override
    public int updateSignIn(SignIn signIn)
    {
        signIn.setUpdateTime(DateUtils.getNowDate());
        return signInMapper.updateSignIn(signIn);
    }

    /**
     * 批量删除签到
     * 
     * @param ids 需要删除的签到主键
     * @return 结果
     */
    @Override
    public int deleteSignInByIds(Long[] ids)
    {
        return signInMapper.deleteSignInByIds(ids);
    }

    /**
     * 删除签到信息
     * 
     * @param id 签到主键
     * @return 结果
     */
    @Override
    public int deleteSignInById(Long id)
    {
        return signInMapper.deleteSignInById(id);
    }

    /**
     *
     * 签到
     * */
    @Override
    public AjaxResult signIn(){
        Date date = new Date();
        int i = 0;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        int todaySignIn = signInMapper.isTodaySignIn(user.getUserId());
        if (todaySignIn > 0)return AjaxResult.error("今日已签到");
        int bySevenDay = signInMapper.selectBySevenDay(user.getUserId());
        if (bySevenDay>=7)return AjaxResult.error("签到任务已完成");
        SignIn signIn = new SignIn();
        signIn.setUserId(user.getUserId());
        signIn.setStatus("0");
        signIn.setSignInDate(date);
        signIn.setCreateBy(user.getUserName());
        signIn.setCreateTime(date);
        i += signInMapper.insertSignIn(signIn);

        //添加钻石 type:2
        UserAssets userAssets = userAssetsMapper.selectByUserId(user.getUserId(), 2L);
        if (ObjectUtils.isEmpty(userAssets)){
            userAssets = new UserAssets();
            userAssets.setTotal("0");
            userAssets.setType(2L);
            userAssets.setNickName(user.getNickName());
            userAssets.setUserPhone(user.getPhonenumber());
            userAssets.setUserId(user.getUserId());
            userAssets.setCreatedBy(user.getUserName());
            userAssets.setCreatedTime(date);
        }
        double add = Arith.add(Double.parseDouble(userAssets.getTotal()), 50.0);
        userAssets.setTotal(StringUtils.doubleTrans(add));
        userAssets.setUpdatedBy(user.getUserName());
        userAssets.setUpdatedTime(date);
        i += userAssetsMapper.updateUserAssets(userAssets);
        //添加资产记录
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setUserId(user.getUserId());
        assetDetails.setImage("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/images/1694419558989.jpg");
        assetDetails.setBalance(StringUtils.doubleTrans(add));
        assetDetails.setNotes("签到赠送");
        assetDetails.setRecords("50");
        assetDetails.setAddOrSubtract("1");
        assetDetails.setUserPhone(user.getPhonenumber());
        assetDetails.setType(2L);
        assetDetails.setCreatedTime(date);
        assetDetails.setCreatedBy(user.getUserName());
        i += assetDetailsMapper.insertAssetDetails(assetDetails);
        if(i<=0)return AjaxResult.error("获取奖励异常");
        return AjaxResult.success();
    }

}
