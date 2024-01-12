package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.ExchangeRecords;
import com.ruoyi.qwk.service.IExchangeRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 兑换记录Controller
 *
 * @author cjf
 * @date 2023-07-25
 */
@RestController
@RequestMapping("/qwk/records")
public class ExchangeRecordsController extends BaseController
{
    @Autowired
    private IExchangeRecordsService exchangeRecordsService;

    /**
     * 查询兑换记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExchangeRecords exchangeRecords)
    {
        startPage();
        List<ExchangeRecords> list = exchangeRecordsService.selectExchangeRecordsList(exchangeRecords);
        return getDataTable(list);
    }

    /**
     * 导出兑换记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:export')")
    @Log(title = "兑换记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExchangeRecords exchangeRecords)
    {
        List<ExchangeRecords> list = exchangeRecordsService.selectExchangeRecordsList(exchangeRecords);
        ExcelUtil<ExchangeRecords> util = new ExcelUtil<ExchangeRecords>(ExchangeRecords.class);
        util.exportExcel(response, list, "兑换记录数据");
    }

    /**
     * 获取兑换记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exchangeRecordsService.selectExchangeRecordsById(id));
    }

    /**
     * 新增兑换记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:add')")
    @Log(title = "兑换记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExchangeRecords exchangeRecords)
    {
        return toAjax(exchangeRecordsService.insertExchangeRecords(exchangeRecords));
    }

    /**
     * 修改兑换记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:edit')")
    @Log(title = "兑换记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExchangeRecords exchangeRecords)
    {
        return toAjax(exchangeRecordsService.updateExchangeRecords(exchangeRecords));
    }

    /**
     * 删除兑换记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:remove')")
    @Log(title = "兑换记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exchangeRecordsService.deleteExchangeRecordsByIds(ids));
    }
}
