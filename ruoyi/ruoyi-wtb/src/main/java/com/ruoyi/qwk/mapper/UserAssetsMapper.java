package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.UserAssets;
import org.apache.ibatis.annotations.Param;

/**
 * 用户资产 Mapper接口
 * 
 * @author cjf
 * @date 2023-06-29
 */
@DataSource(value = DataSourceType.SLAVE)
public interface UserAssetsMapper 
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
     * 删除用户资产 
     * 
     * @param id 用户资产 主键
     * @return 结果
     */
    public int deleteUserAssetsById(Long id);

    /**
     * 批量删除用户资产 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserAssetsByIds(Long[] ids);

    /**
     * 根据用户id查询用户资产
     *
     * @param userId 用户id
     * @param type 资产类型
     * @return 结果
     */
    public UserAssets selectByUserId(@Param("userId") Long userId,@Param("type") Long type);

    /**
     * 根据用户id,资产类型修改用户资产
     *
     * @param userAssets 用户资产
     * @return 结果
     */
    public int updateUserAssetsByUserId(UserAssets userAssets);

    /**
     * 批量删除用户仓库详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserAssetsByUserId(Long[] ids);

}
