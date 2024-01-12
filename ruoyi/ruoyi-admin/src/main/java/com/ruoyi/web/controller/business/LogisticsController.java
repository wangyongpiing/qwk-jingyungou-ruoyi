package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.Logistics;
import com.ruoyi.qwk.service.ILogisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 物流 Controller
 *
 * @author cjf
 * @date 2023-06-30
 */
@RestController
@RequestMapping("/qwk/logistics")
@Api(tags = "物流API")
public class LogisticsController extends BaseController
{
    @Autowired
    private ILogisticsService logisticsService;

    /**
     * 查询物流 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:logistics:list')")
    @GetMapping("/list")
    public TableDataInfo list(Logistics logistics)
    {
        startPage();
        List<Logistics> list = logisticsService.selectLogisticsList(logistics);
        return getDataTable(list);
    }

    /**
     * 导出物流 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:logistics:export')")
    @Log(title = "物流 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Logistics logistics)
    {
        List<Logistics> list = logisticsService.selectLogisticsList(logistics);
        ExcelUtil<Logistics> util = new ExcelUtil<Logistics>(Logistics.class);
        util.exportExcel(response, list, "物流 数据");
    }

    /**
     * 获取物流 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:logistics:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(logisticsService.selectLogisticsById(id));
    }

    /**
     * 新增物流
     */
    @PreAuthorize("@ss.hasPermi('qwk:logistics:add')")
    @Log(title = "物流 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Logistics logistics)
    {
        return toAjax(logisticsService.insertLogistics(logistics));
    }

    /**
     * 修改物流
     */
    @PreAuthorize("@ss.hasPermi('qwk:logistics:edit')")
    @Log(title = "物流 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Logistics logistics)
    {
        return toAjax(logisticsService.updateLogistics(logistics));
    }

    /**
     * 删除物流
     */
    @PreAuthorize("@ss.hasPermi('qwk:logistics:remove')")
    @Log(title = "物流 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(logisticsService.deleteLogisticsByIds(ids));
    }

    /**
     * 发货
     */
    @PreAuthorize("@ss.hasPermi('qwk:logistics:add')")
    @Log(title = "物流 ", businessType = BusinessType.INSERT)
    @PostMapping("/addLogistics")
    public AjaxResult addLogistics(@RequestBody Logistics logistics){
        return toAjax(logisticsService.addLogistics(logistics));
    }


//    ------------------------------------app--------------------------------------------
    @ApiOperation("根据订单编号查询物流")
    @GetMapping("/getByOrderId/{orderId}")
    public AjaxResult getByOrderId(@PathVariable("orderId") String orderId){
        return success(logisticsService.selectLogisticsByOrderId(orderId));
    }

    /**
     * 获取物流 详细信息
     */
    @ApiOperation("根据物流编号查询物流")
    @GetMapping(value = "/getLogisticsInfo/{id}")
    public AjaxResult getLogisticsInfo(@PathVariable("id") Long id)
    {
        return success(logisticsService.selectLogisticsById(id));
    }

}
