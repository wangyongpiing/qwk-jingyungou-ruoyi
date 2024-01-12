package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.UserVipLevel;

/**
 * 用户vip等级记录Service接口
 * 
 * @author cjf
 * @date 2023-09-13
 */
public interface IUserVipLevelService 
{
    /**
     * 查询用户vip等级记录
     * 
     * @param id 用户vip等级记录主键
     * @return 用户vip等级记录
     */
    public UserVipLevel selectUserVipLevelById(Long id);

    /**
     * 查询用户vip等级记录列表
     * 
     * @param userVipLevel 用户vip等级记录
     * @return 用户vip等级记录集合
     */
    public List<UserVipLevel> selectUserVipLevelList(UserVipLevel userVipLevel);

    /**
     * 新增用户vip等级记录
     * 
     * @param userVipLevel 用户vip等级记录
     * @return 结果
     */
    public int insertUserVipLevel(UserVipLevel userVipLevel);

    /**
     * 修改用户vip等级记录
     * 
     * @param userVipLevel 用户vip等级记录
     * @return 结果
     */
    public int updateUserVipLevel(UserVipLevel userVipLevel);

    /**
     * 批量删除用户vip等级记录
     * 
     * @param ids 需要删除的用户vip等级记录主键集合
     * @return 结果
     */
    public int deleteUserVipLevelByIds(Long[] ids);

    /**
     * 删除用户vip等级记录信息
     * 
     * @param id 用户vip等级记录主键
     * @return 结果
     */
    public int deleteUserVipLevelById(Long id);

    /**
     * 记录用户vip等级信息
     *
     * @param userId 用户id
     * @param rechargeAmount 充值金额
     * @return 结果
     */
    public int modifyUserVip(Long userId,String rechargeAmount);

}
