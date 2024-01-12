package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.Category;

/**
 * 商品分类Service接口
 * 
 * @author cjf
 * @date 2023-08-31
 */
public interface ICategoryService 
{
    /**
     * 查询商品分类
     * 
     * @param id 商品分类主键
     * @return 商品分类
     */
    public Category selectCategoryById(Long id);

    /**
     * 查询商品分类列表
     * 
     * @param category 商品分类
     * @return 商品分类集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增商品分类
     * 
     * @param category 商品分类
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改商品分类
     * 
     * @param category 商品分类
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的商品分类主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(Long[] ids);

    /**
     * 删除商品分类信息
     * 
     * @param id 商品分类主键
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 根据父级id查询子级
     *
     * @param parentId 父级id
     * */
    public List<Category> getByParentId(Long parentId);
}
