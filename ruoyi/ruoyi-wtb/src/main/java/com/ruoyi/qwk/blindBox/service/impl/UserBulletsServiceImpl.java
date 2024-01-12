package com.ruoyi.qwk.blindBox.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateTime;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.qwk.blindBox.domain.*;
import com.ruoyi.qwk.blindBox.mapper.*;
import com.ruoyi.qwk.domain.AssetDetails;
import com.ruoyi.qwk.domain.UserAssets;
import com.ruoyi.qwk.mapper.AssetDetailsMapper;
import com.ruoyi.qwk.mapper.UserAssetsMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.service.IUserBulletsService;
import org.springframework.util.ObjectUtils;

/**
 * 用户子弹数量记录Service业务层处理
 *
 * @author cjf
 * @date 2023-09-21
 */
@Service
public class UserBulletsServiceImpl implements IUserBulletsService
{
    @Autowired
    private UserBulletsMapper userBulletsMapper;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    @Autowired
    private BulletsMapper bulletsMapper;

    @Autowired
    private JackpotMapper jackpotMapper;

    @Autowired
    private UserConsumeServiceImpl userConsumeService;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private ShotsNumMapper shotsNumMapper;

    /**
     * 查询用户子弹数量记录
     *
     * @param id 用户子弹数量记录主键
     * @return 用户子弹数量记录
     */
    @Override
    public UserBullets selectUserBulletsById(Long id)
    {
        return userBulletsMapper.selectUserBulletsById(id);
    }

    /**
     * 查询用户子弹数量记录列表
     *
     * @param userBullets 用户子弹数量记录
     * @return 用户子弹数量记录
     */
    @Override
    public List<UserBullets> selectUserBulletsList(UserBullets userBullets)
    {
        return userBulletsMapper.selectUserBulletsList(userBullets);
    }

    /**
     * 新增用户子弹数量记录
     *
     * @param userBullets 用户子弹数量记录
     * @return 结果
     */
    @Override
    public int insertUserBullets(UserBullets userBullets)
    {
        userBullets.setCreateTime(DateUtils.getNowDate());
        return userBulletsMapper.insertUserBullets(userBullets);
    }

    /**
     * 修改用户子弹数量记录
     *
     * @param userBullets 用户子弹数量记录
     * @return 结果
     */
    @Override
    public int updateUserBullets(UserBullets userBullets)
    {
        userBullets.setUpdateTime(DateUtils.getNowDate());
        return userBulletsMapper.updateUserBullets(userBullets);
    }

    /**
     * 批量删除用户子弹数量记录
     *
     * @param ids 需要删除的用户子弹数量记录主键
     * @return 结果
     */
    @Override
    public int deleteUserBulletsByIds(Long[] ids)
    {
        return userBulletsMapper.deleteUserBulletsByIds(ids);
    }

    /**
     * 删除用户子弹数量记录信息
     *
     * @param id 用户子弹数量记录主键
     * @return 结果
     */
    @Override
    public int deleteUserBulletsById(Long id)
    {
        return userBulletsMapper.deleteUserBulletsById(id);
    }


    /**
     *
     * 购买子弹
     * @param userBullets 用户子弹数量对象
     * */
    @Override
    public AjaxResult purchaseBullets(UserBullets userBullets){
        if (userBullets.getBulletsNum()<=0)return AjaxResult.error("请输入正确的子弹数量!");
        int value = 0;
        SysUser user = SecurityUtils.getLoginUser().getUser();
        UserBullets byUserId = userBulletsMapper.selectUserBulletsByUserId(user.getUserId(), userBullets.getBulletsId());
        if (ObjectUtils.isEmpty(byUserId)){
            byUserId = new UserBullets();
            byUserId.setBulletsId(userBullets.getBulletsId());
            byUserId.setBulletsNum(0);
            byUserId.setUserId(user.getUserId());
            byUserId.setCreateBy(user.getUserName());
            byUserId.setCreateTime(new Date());
            value += userBulletsMapper.insertUserBullets(byUserId);
        }
        UserAssets userAssets = userAssetsMapper.selectByUserId(user.getUserId(), 1L);
        Bullets bullets = bulletsMapper.selectBulletsById(userBullets.getBulletsId());
        Double total = Double.parseDouble(userAssets.getTotal());//持有总积分
        Integer spend = userBullets.getBulletsNum()*bullets.getConsume().intValue();//购买子弹所需积分
        Double residue = total - spend ;//购买后剩余积分
        if (residue<0){
            return AjaxResult.error("积分不足,请先充值再购买!");
        }
        Date date = new Date();
        userAssets.setUpdatedBy(user.getUserName());
        userAssets.setUpdatedTime(date);
        userAssets.setTotal(StringUtils.doubleTrans(residue));

        value += userAssetsMapper.updateUserAssets(userAssets);

        AssetDetails assetDetails = new AssetDetails();//构建积分消耗详情
        assetDetails.setCreatedBy(user.getUserName());
        assetDetails.setCreatedTime(date);
        assetDetails.setImage("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/images/1694419558989.jpg");
        assetDetails.setNotes("积分购买炮弹");
        assetDetails.setType(1L);//资产类型
        assetDetails.setUserPhone(user.getPhonenumber());
        assetDetails.setAddOrSubtract("0");
        assetDetails.setRecords(spend.toString());
        assetDetails.setBalance(StringUtils.doubleTrans(residue));
        assetDetails.setUserId(user.getUserId());
        value += assetDetailsMapper.insertAssetDetails(assetDetails);

        byUserId.setBulletsNum(byUserId.getBulletsNum()+userBullets.getBulletsNum());
        byUserId.setUpdateBy(user.getUserName());
        byUserId.setUpdateTime(date);
        value += userBulletsMapper.updateUserBullets(byUserId);
        if (value<=0)return AjaxResult.error("购买炮弹异常");


        return AjaxResult.success("购买成功");
    }


    /**
     * 根据炮弹类型查询数量
     * @param userId 用户id
     * @param bulletsId 炮弹类型id
     * @return 结果
     */
    public UserBullets queryBulletsNum( Long userId , Long bulletsId){
        return userBulletsMapper.selectUserBulletsByUserId(userId,bulletsId);
    }

    /**
     *
     * 炮弹消耗
     * */
    @Override
    public int consumption(Long userId,Long bulletsId){
        int value = 0;
        UserBullets userBullets = this.queryBulletsNum(userId, bulletsId);
        if((userBullets.getBulletsNum()-1)<0)return 0;
        userBullets.setBulletsNum(userBullets.getBulletsNum()-1);
        userBullets.setUpdateTime(new Date());
        userBullets.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        value+=userBulletsMapper.updateUserBullets(userBullets);

        Jackpot jackpot = jackpotMapper.selectJackpotById(1L);
        Bullets bullets = bulletsMapper.selectBulletsById(userBullets.getBulletsId());
        Double money = bullets.getConsume()*0.1;
        Double total = Double.parseDouble(jackpot.getTotal())+money;
        jackpot.setTotal(StringUtils.doubleTrans(total));
        jackpot.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        jackpot.setUpdateTime(new Date());
        value+=jackpotMapper.updateJackpot(jackpot);

        UserConsume userConsume = userConsumeService.selectUserConsumeByUserId(userId);
        if (ObjectUtils.isEmpty(userConsume))return value;
        userConsume.setConsume(StringUtils.doubleTrans(Double.parseDouble(userConsume.getConsume())+money));
        value+=userConsumeService.updateUserConsume(userConsume);
        value+=getShotsNum(userId,bulletsId);//添加射击次数
        return value;
    }

    /**
     *
     * 新手场消耗子弹
     * */
    @Override
    public AjaxResult noviceConsumption(Long userId){
        UserBullets userBullets = this.queryBulletsNum(userId, 1L);
        if((userBullets.getBulletsNum()-1)<0)return AjaxResult.error("炮弹数量不足,请购买炮弹");
        userBullets.setBulletsNum(userBullets.getBulletsNum()-1);
        userBullets.setUpdateTime(new Date());
        userBullets.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        int i = userBulletsMapper.updateUserBullets(userBullets);
        i += getShotsNum(userId,0L);
        if (i<=0)return AjaxResult.error("系统异常");
        ShotsNum shotsNum = shotsNumMapper.selectShotsNumByUserId(userId, 0);
        return AjaxResult.success().put("raffleNum",shotsNum.getNum());
    }

    /**
     * 获取用户信息
     * */
    @Override
    public AjaxResult getUserDetails(){
        SysUser user = userMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
        user.setPassword("");
        user.setImUserSig("");
        UserAssets userAssets = userAssetsMapper.selectByUserId(user.getUserId(), 1L);
        UserAssets userBattery = userAssetsMapper.selectByUserId(user.getUserId(), 2L);
        List<UserBullets> userBullets = userBulletsMapper.selectUserBulletsAllByUserId(user.getUserId());
        return AjaxResult.success()
                .put("user",user)
                .put("userAssets",userAssets)
                .put("userBullets",userBullets)
                .put("userBattery",userBattery);
    }

    /**
     * 添加用户射击次数
     * */
    private int getShotsNum(Long userId,Long bulletsId){
        ShotsNum shotsNum = shotsNumMapper.selectShotsNumByUserId(userId, bulletsId.intValue());
        if (ObjectUtils.isEmpty(shotsNum)){
            shotsNum = new ShotsNum();
            shotsNum.setFieldId(bulletsId.intValue());
            shotsNum.setUserId(userId);
            shotsNum.setNum(1);
            shotsNum.setCreateTime(new Date());
            shotsNum.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserName());
            return shotsNumMapper.insertShotsNum(shotsNum);
        }
        shotsNum.setNum(shotsNum.getNum()+1);
        shotsNum.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        shotsNum.setUpdateTime(new Date());
        return shotsNumMapper.updateShotsNum(shotsNum);
    }

}
