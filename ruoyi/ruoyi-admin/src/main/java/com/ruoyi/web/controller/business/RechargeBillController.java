package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.RechargeBill;
import com.ruoyi.qwk.service.IRechargeBillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 充值流水 Controller
 *
 * @author cjf
 * @date 2023-06-30
 */
@RestController
@RequestMapping("/qwk/bill")
@Api(tags = "充值流水API")
public class RechargeBillController extends BaseController
{
    @Autowired
    private IRechargeBillService rechargeBillService;

    /**
     * 查询充值流水 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:bill:list')")
    @GetMapping("/list")
    public TableDataInfo list(RechargeBill rechargeBill)
    {
        startPage();
        List<RechargeBill> list = rechargeBillService.selectRechargeBillList(rechargeBill);
        return getDataTable(list);
    }

    /**
     * 导出充值流水 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:bill:export')")
    @Log(title = "充值流水 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RechargeBill rechargeBill)
    {
        List<RechargeBill> list = rechargeBillService.selectRechargeBillList(rechargeBill);
        ExcelUtil<RechargeBill> util = new ExcelUtil<RechargeBill>(RechargeBill.class);
        util.exportExcel(response, list, "充值流水 数据");
    }

    /**
     * 获取充值流水 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:bill:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rechargeBillService.selectRechargeBillById(id));
    }

    /**
     * 新增充值流水
     */
    @PreAuthorize("@ss.hasPermi('qwk:bill:add')")
    @Log(title = "充值流水 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RechargeBill rechargeBill)
    {
        return toAjax(rechargeBillService.insertRechargeBill(rechargeBill));
    }

    /**
     * 修改充值流水
     */
    @PreAuthorize("@ss.hasPermi('qwk:bill:edit')")
    @Log(title = "充值流水 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RechargeBill rechargeBill)
    {
        return toAjax(rechargeBillService.updateRechargeBill(rechargeBill));
    }

    /**
     * 删除充值流水
     */
    @PreAuthorize("@ss.hasPermi('qwk:bill:remove')")
    @Log(title = "充值流水 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rechargeBillService.deleteRechargeBillByIds(ids));
    }

    /**
     * 查询统计数据
     *
     * @return 结果
     */
    @GetMapping(value = "/getStatistics")
    @PreAuthorize("@ss.hasPermi('qwk:bill:list')")
    public AjaxResult getStatistics(){
        return success(rechargeBillService.selectByStatistics());
    }

//    --------------------------------------app------------------------------------------------

    @Log(title = "充值流水 ", businessType = BusinessType.INSERT)
    @PostMapping("/addRechargeBill")
    @ApiOperation(value = "积分充值")
    public AjaxResult addRechargeBill(@RequestBody RechargeBill rechargeBill){
        return rechargeBillService.pointRecharge(rechargeBill);
    }

    @GetMapping("/getRechargeBill")
    @ApiOperation(value = "查询积分充值流水")
    public AjaxResult getRechargeBill(RechargeBill rechargeBill){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        rechargeBill.setUserId(userId);
        rechargeBill.setType(1L);
        startPage();
        List<RechargeBill> list = rechargeBillService.selectRechargeBillList(rechargeBill);
        TableDataInfo dataTable = getDataTable(list);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",dataTable.getTotal());
        map.put("rows",dataTable.getRows());
        return AjaxResult.success(map);
    }

}
