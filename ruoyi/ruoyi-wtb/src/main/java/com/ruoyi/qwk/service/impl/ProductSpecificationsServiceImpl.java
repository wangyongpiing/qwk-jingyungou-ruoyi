package com.ruoyi.qwk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.ProductSpecificationsMapper;
import com.ruoyi.qwk.domain.ProductSpecifications;
import com.ruoyi.qwk.service.IProductSpecificationsService;

/**
 * 商品规格 Service业务层处理
 * 
 * @author cjf
 * @date 2023-06-28
 */
@Service
public class ProductSpecificationsServiceImpl implements IProductSpecificationsService 
{
    @Autowired
    private ProductSpecificationsMapper productSpecificationsMapper;

    /**
     * 查询商品规格 
     * 
     * @param id 商品规格 主键
     * @return 商品规格 
     */
    @Override
    public ProductSpecifications selectProductSpecificationsById(Long id)
    {
        return productSpecificationsMapper.selectProductSpecificationsById(id);
    }

    /**
     * 查询商品规格 列表
     * 
     * @param productSpecifications 商品规格 
     * @return 商品规格 
     */
    @Override
    public List<ProductSpecifications> selectProductSpecificationsList(ProductSpecifications productSpecifications)
    {
        return productSpecificationsMapper.selectProductSpecificationsList(productSpecifications);
    }

    /**
     * 新增商品规格 
     * 
     * @param productSpecifications 商品规格 
     * @return 结果
     */
    @Override
    public int insertProductSpecifications(ProductSpecifications productSpecifications)
    {
        return productSpecificationsMapper.insertProductSpecifications(productSpecifications);
    }

    /**
     * 修改商品规格 
     * 
     * @param productSpecifications 商品规格 
     * @return 结果
     */
    @Override
    public int updateProductSpecifications(ProductSpecifications productSpecifications)
    {
        return productSpecificationsMapper.updateProductSpecifications(productSpecifications);
    }

    /**
     * 批量删除商品规格 
     * 
     * @param ids 需要删除的商品规格 主键
     * @return 结果
     */
    @Override
    public int deleteProductSpecificationsByIds(Long[] ids)
    {
        return productSpecificationsMapper.deleteProductSpecificationsByIds(ids);
    }

    /**
     * 删除商品规格 信息
     * 
     * @param id 商品规格 主键
     * @return 结果
     */
    @Override
    public int deleteProductSpecificationsById(Long id)
    {
        return productSpecificationsMapper.deleteProductSpecificationsById(id);
    }
}
