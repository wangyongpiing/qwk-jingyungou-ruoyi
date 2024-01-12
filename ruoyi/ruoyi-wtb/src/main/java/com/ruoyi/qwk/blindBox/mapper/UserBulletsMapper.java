package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.UserBullets;
import org.apache.ibatis.annotations.Param;

/**
 * 用户子弹数量记录Mapper接口
 * 
 * @author cjf
 * @date 2023-09-21
 */
@DataSource(value = DataSourceType.SLAVE)
public interface UserBulletsMapper 
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
     * 删除用户子弹数量记录
     * 
     * @param id 用户子弹数量记录主键
     * @return 结果
     */
    public int deleteUserBulletsById(Long id);

    /**
     * 批量删除用户子弹数量记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserBulletsByIds(Long[] ids);

    /**
     * 查询用户子弹数量
     *
     * @param userId 用户id
     * @param bulletsId 子弹id
     * @return 结果
     */
    public UserBullets selectUserBulletsByUserId(@Param("userId") Long userId ,@Param("bulletsId") Long bulletsId);

    /**
     * 根据用户id查询用户所有子弹信息
     *
     * @param userId 用户id
     * @return 结果
     */
    public List<UserBullets> selectUserBulletsAllByUserId(@Param("userId") Long userId);

    /**
     * 批量删除用户子弹信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserBulletsByUserId(Long[] ids);

}
