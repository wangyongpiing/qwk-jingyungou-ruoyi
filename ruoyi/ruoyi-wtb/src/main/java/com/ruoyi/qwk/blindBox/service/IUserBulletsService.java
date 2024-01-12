package com.ruoyi.qwk.blindBox.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.blindBox.domain.UserBullets;

/**
 * 用户子弹数量记录Service接口
 * 
 * @author cjf
 * @date 2023-09-21
 */
public interface IUserBulletsService 
{
    /**
     * 查询用户子弹数量记录
     * 
     * @param id 用户子弹数量记录主键
     * @return 用户子弹数量记录
     */
    public UserBullets selectUserBulletsById(Long id);

    /**
     * 查询用户子弹数量记录列表
     * 
     * @param userBullets 用户子弹数量记录
     * @return 用户子弹数量记录集合
     */
    public List<UserBullets> selectUserBulletsList(UserBullets userBullets);

    /**
     * 新增用户子弹数量记录
     * 
     * @param userBullets 用户子弹数量记录
     * @return 结果
     */
    public int insertUserBullets(UserBullets userBullets);

    /**
     * 修改用户子弹数量记录
     * 
     * @param userBullets 用户子弹数量记录
     * @return 结果
     */
    public int updateUserBullets(UserBullets userBullets);

    /**
     * 批量删除用户子弹数量记录
     * 
     * @param ids 需要删除的用户子弹数量记录主键集合
     * @return 结果
     */
    public int deleteUserBulletsByIds(Long[] ids);

    /**
     * 删除用户子弹数量记录信息
     * 
     * @param id 用户子弹数量记录主键
     * @return 结果
     */
    public int deleteUserBulletsById(Long id);

    /**
     *
     * 购买子弹
     * @param userBullets 用户子弹数量对象
     * */
    public AjaxResult purchaseBullets(UserBullets userBullets);

    /**
     * 根据炮弹类型查询数量
     * @param userId 用户id
     * @param bulletsId 炮弹类型id
     * @return 结果
     */
    public UserBullets queryBulletsNum( Long userId , Long bulletsId);

    /**
     *
     * 炮弹消耗
     * */
    public int consumption(Long userId,Long bulletsId);

    /**
     *
     * 新手场消耗子弹
     * */
    public AjaxResult noviceConsumption(Long userId);

    /**
     * 获取用户信息
     * */
    public AjaxResult getUserDetails();

}
