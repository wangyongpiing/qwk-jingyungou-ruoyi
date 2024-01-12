package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.Warehouse;
import com.ruoyi.qwk.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 仓库 Controller
 *
 * @author cjf
 * @date 2023-06-29
 */
@RestController
@RequestMapping("/qwk/warehouse")
public class WarehouseController extends BaseController
{
    @Autowired
    private IWarehouseService warehouseService;

    /**
     * 查询仓库 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:warehouse:list')")
    @GetMapping("/list")
    public TableDataInfo list(Warehouse warehouse)
    {
        startPage();
        List<Warehouse> list = warehouseService.selectWarehouseList(warehouse);
        return getDataTable(list);
    }

    /**
     * 导出仓库 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:warehouse:export')")
    @Log(title = "仓库 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Warehouse warehouse)
    {
        List<Warehouse> list = warehouseService.selectWarehouseList(warehouse);
        ExcelUtil<Warehouse> util = new ExcelUtil<Warehouse>(Warehouse.class);
        util.exportExcel(response, list, "仓库 数据");
    }

    /**
     * 获取仓库 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:warehouse:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(warehouseService.selectWarehouseById(id));
    }

    /**
     * 新增仓库
     */
    @PreAuthorize("@ss.hasPermi('qwk:warehouse:add')")
    @Log(title = "仓库 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Warehouse warehouse)
    {
        return toAjax(warehouseService.insertWarehouse(warehouse));
    }

    /**
     * 修改仓库
     */
    @PreAuthorize("@ss.hasPermi('qwk:warehouse:edit')")
    @Log(title = "仓库 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Warehouse warehouse)
    {
        return toAjax(warehouseService.updateWarehouse(warehouse));
    }

    /**
     * 删除仓库
     */
    @PreAuthorize("@ss.hasPermi('qwk:warehouse:remove')")
    @Log(title = "仓库 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(warehouseService.deleteWarehouseByIds(ids));
    }
}
