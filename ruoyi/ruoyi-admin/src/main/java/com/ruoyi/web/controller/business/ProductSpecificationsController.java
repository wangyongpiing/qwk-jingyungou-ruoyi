package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.ProductSpecifications;
import com.ruoyi.qwk.service.IProductSpecificationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品规格 Controller
 *
 * @author cjf
 * @date 2023-06-28
 */
@RestController
@RequestMapping("/qwk/specifications")
@Api(tags = "商品规格API")
public class ProductSpecificationsController extends BaseController
{
    @Autowired
    private IProductSpecificationsService productSpecificationsService;

    /**
     * 查询商品规格 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:specifications:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductSpecifications productSpecifications)
    {
        startPage();
        List<ProductSpecifications> list = productSpecificationsService.selectProductSpecificationsList(productSpecifications);
        return getDataTable(list);
    }

    /**
     * 导出商品规格 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:specifications:export')")
    @Log(title = "商品规格 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductSpecifications productSpecifications)
    {
        List<ProductSpecifications> list = productSpecificationsService.selectProductSpecificationsList(productSpecifications);
        ExcelUtil<ProductSpecifications> util = new ExcelUtil<ProductSpecifications>(ProductSpecifications.class);
        util.exportExcel(response, list, "商品规格 数据");
    }

    /**
     * 获取商品规格 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:specifications:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(productSpecificationsService.selectProductSpecificationsById(id));
    }

    /**
     * 新增商品规格
     */
    @PreAuthorize("@ss.hasPermi('qwk:specifications:add')")
    @Log(title = "商品规格 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductSpecifications productSpecifications)
    {
        return toAjax(productSpecificationsService.insertProductSpecifications(productSpecifications));
    }

    /**
     * 修改商品规格
     */
    @PreAuthorize("@ss.hasPermi('qwk:specifications:edit')")
    @Log(title = "商品规格 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductSpecifications productSpecifications)
    {
        return toAjax(productSpecificationsService.updateProductSpecifications(productSpecifications));
    }

    /**
     * 删除商品规格
     */
    @PreAuthorize("@ss.hasPermi('qwk:specifications:remove')")
    @Log(title = "商品规格 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productSpecificationsService.deleteProductSpecificationsByIds(ids));
    }

    /**
     * 查询商品规格列表
     */
    @GetMapping("/getPSList")
    @ApiOperation(value = "获取商品规格列表")
    public AjaxResult getPSList(){
        List<ProductSpecifications> list = productSpecificationsService.selectProductSpecificationsList(null);
        return success(list);
    }

}
