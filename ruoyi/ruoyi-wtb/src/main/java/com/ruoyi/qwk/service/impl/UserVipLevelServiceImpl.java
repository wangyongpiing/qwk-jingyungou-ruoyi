package com.ruoyi.qwk.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.qwk.domain.VipLevel;
import com.ruoyi.qwk.mapper.VipLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.UserVipLevelMapper;
import com.ruoyi.qwk.domain.UserVipLevel;
import com.ruoyi.qwk.service.IUserVipLevelService;
import org.springframework.util.ObjectUtils;

/**
 * 用户vip等级记录Service业务层处理
 * 
 * @author cjf
 * @date 2023-09-13
 */
@Service
public class UserVipLevelServiceImpl implements IUserVipLevelService {
    @Autowired
    private UserVipLevelMapper userVipLevelMapper;

    @Autowired
    private VipLevelMapper vipLevelMapper;

    /**
     * 查询用户vip等级记录
     * 
     * @param id 用户vip等级记录主键
     * @return 用户vip等级记录
     */
    @Override
    public UserVipLevel selectUserVipLevelById(Long id)
    {
        return userVipLevelMapper.selectUserVipLevelById(id);
    }

    /**
     * 查询用户vip等级记录列表
     * 
     * @param userVipLevel 用户vip等级记录
     * @return 用户vip等级记录
     */
    @Override
    public List<UserVipLevel> selectUserVipLevelList(UserVipLevel userVipLevel)
    {
        return userVipLevelMapper.selectUserVipLevelList(userVipLevel);
    }

    /**
     * 新增用户vip等级记录
     * 
     * @param userVipLevel 用户vip等级记录
     * @return 结果
     */
    @Override
    public int insertUserVipLevel(UserVipLevel userVipLevel)
    {
        userVipLevel.setCreateTime(DateUtils.getNowDate());
        return userVipLevelMapper.insertUserVipLevel(userVipLevel);
    }

    /**
     * 修改用户vip等级记录
     * 
     * @param userVipLevel 用户vip等级记录
     * @return 结果
     */
    @Override
    public int updateUserVipLevel(UserVipLevel userVipLevel)
    {
        userVipLevel.setUpdateTime(DateUtils.getNowDate());
        return userVipLevelMapper.updateUserVipLevel(userVipLevel);
    }

    /**
     * 批量删除用户vip等级记录
     * 
     * @param ids 需要删除的用户vip等级记录主键
     * @return 结果
     */
    @Override
    public int deleteUserVipLevelByIds(Long[] ids)
    {
        return userVipLevelMapper.deleteUserVipLevelByIds(ids);
    }

    /**
     * 删除用户vip等级记录信息
     * 
     * @param id 用户vip等级记录主键
     * @return 结果
     */
    @Override
    public int deleteUserVipLevelById(Long id)
    {
        return userVipLevelMapper.deleteUserVipLevelById(id);
    }


    /**
     * 记录用户vip等级信息
     *
     * @param userId 用户id
     * @param rechargeAmount 充值金额
     * @return 结果
     */
    @Override
    public int modifyUserVip(Long userId,String rechargeAmount){
        List<VipLevel> vipLevels = vipLevelMapper.getAllByStatusVipLevels();
        UserVipLevel byUserId = userVipLevelMapper.getByUserIdUserVipLevel(userId);
        if (ObjectUtils.isEmpty(byUserId)){
            byUserId=getUserVipLevel();
            byUserId.setUserId(userId);
            int i = userVipLevelMapper.insertUserVipLevel(byUserId);
            if (i<=0)return 0;
        }
        Double v = Double.parseDouble(rechargeAmount);
        byUserId.setUserExperience(v.longValue()+byUserId.getUserExperience());
        for (VipLevel vipLevel : vipLevels) {
            if (byUserId.getUserExperience() >= vipLevel.getExperience()){
                byUserId.setLevel(vipLevel.getLevel());
                byUserId.setLevelId(vipLevel.getId());
                byUserId.setExperience(vipLevel.getExperience());
                byUserId.setUpdateBy("系统");
                byUserId.setUpdateTime(new Date());
            }
        }
        return userVipLevelMapper.updateUserVipLevel(byUserId);
    }


    //初始化用户vip
    private UserVipLevel getUserVipLevel(){
        UserVipLevel userVipLevel = new UserVipLevel();
        userVipLevel.setLevelId(0L);//设置初始vip0
        userVipLevel.setLevel(0L);
        userVipLevel.setExperience(0L);
        userVipLevel.setUserExperience(0L);
        userVipLevel.setCreateBy("系统");
        userVipLevel.setCreateTime(new Date());
        return userVipLevel;
    }

}
