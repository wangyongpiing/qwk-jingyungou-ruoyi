package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.CategoryMapper;
import com.ruoyi.qwk.domain.Category;
import com.ruoyi.qwk.service.ICategoryService;

/**
 * 商品分类Service业务层处理
 * 
 * @author cjf
 * @date 2023-08-31
 */
@Service
public class CategoryServiceImpl implements ICategoryService 
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询商品分类
     * 
     * @param id 商品分类主键
     * @return 商品分类
     */
    @Override
    public Category selectCategoryById(Long id)
    {
        return categoryMapper.selectCategoryById(id);
    }

    /**
     * 查询商品分类列表
     * 
     * @param category 商品分类
     * @return 商品分类
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增商品分类
     * 
     * @param category 商品分类
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        category.setCreateBy(SecurityUtils.getUsername());
        category.setCreateTime(DateUtils.getNowDate());
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改商品分类
     * 
     * @param category 商品分类
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        category.setUpdateBy(SecurityUtils.getUsername());
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的商品分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(Long[] ids)
    {
        return categoryMapper.deleteCategoryByIds(ids);
    }

    /**
     * 删除商品分类信息
     * 
     * @param id 商品分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long id)
    {
        return categoryMapper.deleteCategoryById(id);
    }

    /**
     * 根据父级id查询子级
     *
     * @param parentId 父级id
     * */
    @Override
    public List<Category> getByParentId(Long parentId){
        return categoryMapper.getByParentId(parentId);
    }
}
