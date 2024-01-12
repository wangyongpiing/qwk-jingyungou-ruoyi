package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.ExchangeRatio;
import com.ruoyi.qwk.service.IExchangeRatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 积分兑换比例设置Controller
 *
 * @author cjf
 * @date 2023-06-28
 */
@RestController
@RequestMapping("/qwk/ratio")
public class ExchangeRatioController extends BaseController
{
    @Autowired
    private IExchangeRatioService exchangeRatioService;

    /**
     * 查询积分兑换比例设置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:ratio:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExchangeRatio exchangeRatio)
    {
        startPage();
        List<ExchangeRatio> list = exchangeRatioService.selectExchangeRatioList(exchangeRatio);
        return getDataTable(list);
    }

    /**
     * 导出积分兑换比例设置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:ratio:export')")
    @Log(title = "积分兑换比例设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExchangeRatio exchangeRatio)
    {
        List<ExchangeRatio> list = exchangeRatioService.selectExchangeRatioList(exchangeRatio);
        ExcelUtil<ExchangeRatio> util = new ExcelUtil<ExchangeRatio>(ExchangeRatio.class);
        util.exportExcel(response, list, "积分兑换比例设置数据");
    }

    /**
     * 获取积分兑换比例设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:ratio:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exchangeRatioService.selectExchangeRatioById(id));
    }

    /**
     * 新增积分兑换比例设置
     */
    @PreAuthorize("@ss.hasPermi('qwk:ratio:add')")
    @Log(title = "积分兑换比例设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExchangeRatio exchangeRatio)
    {
        return toAjax(exchangeRatioService.insertExchangeRatio(exchangeRatio));
    }

    /**
     * 修改积分兑换比例设置
     */
    @PreAuthorize("@ss.hasPermi('qwk:ratio:edit')")
    @Log(title = "积分兑换比例设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExchangeRatio exchangeRatio)
    {
        return toAjax(exchangeRatioService.updateExchangeRatio(exchangeRatio));
    }

    /**
     * 删除积分兑换比例设置
     */
    @PreAuthorize("@ss.hasPermi('qwk:ratio:remove')")
    @Log(title = "积分兑换比例设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exchangeRatioService.deleteExchangeRatioByIds(ids));
    }
}
