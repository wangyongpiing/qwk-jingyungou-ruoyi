package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.ProductSpecifications;

/**
 * 商品规格 Service接口
 * 
 * @author cjf
 * @date 2023-06-28
 */
public interface IProductSpecificationsService 
{
    /**
     * 查询商品规格 
     * 
     * @param id 商品规格 主键
     * @return 商品规格 
     */
    public ProductSpecifications selectProductSpecificationsById(Long id);

    /**
     * 查询商品规格 列表
     * 
     * @param productSpecifications 商品规格 
     * @return 商品规格 集合
     */
    public List<ProductSpecifications> selectProductSpecificationsList(ProductSpecifications productSpecifications);

    /**
     * 新增商品规格 
     * 
     * @param productSpecifications 商品规格 
     * @return 结果
     */
    public int insertProductSpecifications(ProductSpecifications productSpecifications);

    /**
     * 修改商品规格 
     * 
     * @param productSpecifications 商品规格 
     * @return 结果
     */
    public int updateProductSpecifications(ProductSpecifications productSpecifications);

    /**
     * 批量删除商品规格 
     * 
     * @param ids 需要删除的商品规格 主键集合
     * @return 结果
     */
    public int deleteProductSpecificationsByIds(Long[] ids);

    /**
     * 删除商品规格 信息
     * 
     * @param id 商品规格 主键
     * @return 结果
     */
    public int deleteProductSpecificationsById(Long id);
}
