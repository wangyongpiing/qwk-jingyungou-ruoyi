package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.AssetDetails;

/**
 * 资产详情 Mapper接口
 * 
 * @author cjf
 * @date 2023-06-30
 */
@DataSource(value = DataSourceType.SLAVE)
public interface AssetDetailsMapper 
{
    /**
     * 查询资产详情 
     * 
     * @param id 资产详情 主键
     * @return 资产详情 
     */
    public AssetDetails selectAssetDetailsById(Long id);

    /**
     * 查询资产详情 列表
     * 
     * @param assetDetails 资产详情 
     * @return 资产详情 集合
     */
    public List<AssetDetails> selectAssetDetailsList(AssetDetails assetDetails);

    /**
     * 新增资产详情 
     * 
     * @param assetDetails 资产详情 
     * @return 结果
     */
    public int insertAssetDetails(AssetDetails assetDetails);

    /**
     * 修改资产详情 
     * 
     * @param assetDetails 资产详情 
     * @return 结果
     */
    public int updateAssetDetails(AssetDetails assetDetails);

    /**
     * 删除资产详情 
     * 
     * @param id 资产详情 主键
     * @return 结果
     */
    public int deleteAssetDetailsById(Long id);

    /**
     * 批量删除资产详情 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAssetDetailsByIds(Long[] ids);
}
