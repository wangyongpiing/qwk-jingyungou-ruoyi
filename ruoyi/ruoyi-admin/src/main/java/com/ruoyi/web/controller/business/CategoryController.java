package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.Category;
import com.ruoyi.qwk.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品分类Controller
 *
 * @author cjf
 * @date 2023-08-31
 */
@RestController
@RequestMapping("/qwk/category")
@Api(tags = "商品分类Api")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:category:list')")
    @GetMapping("/list")
    public AjaxResult list(Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        return success(list);
    }

    /**
     * 导出商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:category:export')")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "商品分类数据");
    }

    /**
     * 获取商品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categoryService.selectCategoryById(id));
    }

    /**
     * 新增商品分类
     */
    @PreAuthorize("@ss.hasPermi('qwk:category:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改商品分类
     */
    @PreAuthorize("@ss.hasPermi('qwk:category:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除商品分类
     */
    @PreAuthorize("@ss.hasPermi('qwk:category:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryService.deleteCategoryByIds(ids));
    }


//    --------------------------------------app--------------------------------------------
    /**
     * 查询商品分类列表
     */
    @GetMapping("/getCategoryList")
//    @ApiOperation("查询商品分类列表")
    public AjaxResult getCategoryList(Category category){
        List<Category> list = categoryService.selectCategoryList(category);
        return success(list);
    }

    @GetMapping("/getByParentId/{parentId}")
    @ApiOperation("根据父级id查询子集")
    public AjaxResult getByParentId(@PathVariable("parentId") Long parentId){
        List<Category> byParentId = categoryService.getByParentId(parentId);
        return AjaxResult.success(byParentId);
    }

}
