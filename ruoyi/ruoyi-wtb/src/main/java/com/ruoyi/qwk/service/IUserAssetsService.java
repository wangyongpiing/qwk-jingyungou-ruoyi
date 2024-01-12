package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.UserAssets;
import org.apache.ibatis.annotations.Param;

/**
 * 用户资产 Service接口
 * 
 * @author cjf
 * @date 2023-06-29
 */
public interface IUserAssetsService 
{
    /**
     * 查询用户资产 
     * 
     * @param id 用户资产 主键
     * @return 用户资产 
     */
    public UserAssets selectUserAssetsById(Long id);

    /**
     * 查询用户资产 列表
     * 
     * @param userAssets 用户资产 
     * @return 用户资产 集合
     */
    public List<UserAssets> selectUserAssetsList(UserAssets userAssets);

    /**
     * 新增用户资产 
     * 
     * @param userAssets 用户资产 
     * @return 结果
     */
    public int insertUserAssets(UserAssets userAssets);

    /**
     * 修改用户资产 
     * 
     * @param userAssets 用户资产 
     * @return 结果
     */
    public int updateUserAssets(UserAssets userAssets);

    /**
     * 批量删除用户资产 
     * 
     * @param ids 需要删除的用户资产 主键集合
     * @return 结果
     */
    public int deleteUserAssetsByIds(Long[] ids);

    /**
     * 删除用户资产 信息
     * 
     * @param id 用户资产 主键
     * @return 结果
     */
    public int deleteUserAssetsById(Long id);

    /**
     * 游戏获取资产
     *
     * @param userId 用户id
     * @param gameId 游戏id
     * @param type 用户资产类型
     * @param number 数量
     * @return 结果
     */
    public int updateAssetsByGame(Long userId,Long gameId,Long type,Integer number);

    /**
     * 根据用户id查询用户资产
     *
     * @param userId 用户id
     * @param type 资产类型
     * @return 结果
     */
    public UserAssets selectByUserId( Long userId, Long type);
}
