package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.ExchangeRules;
import com.ruoyi.qwk.service.IExchangeRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 积分兑换规则Controller
 *
 * @author cjf
 * @date 2023-07-14
 */
@RestController
@RequestMapping("/qwk/rules")
public class ExchangeRulesController extends BaseController
{
    @Autowired
    private IExchangeRulesService exchangeRulesService;

    /**
     * 查询积分兑换规则列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:rules:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExchangeRules exchangeRules)
    {
        startPage();
        List<ExchangeRules> list = exchangeRulesService.selectExchangeRulesList(exchangeRules);
        return getDataTable(list);
    }

    /**
     * 导出积分兑换规则列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:rules:export')")
    @Log(title = "积分兑换规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExchangeRules exchangeRules)
    {
        List<ExchangeRules> list = exchangeRulesService.selectExchangeRulesList(exchangeRules);
        ExcelUtil<ExchangeRules> util = new ExcelUtil<ExchangeRules>(ExchangeRules.class);
        util.exportExcel(response, list, "积分兑换规则数据");
    }

    /**
     * 获取积分兑换规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:rules:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exchangeRulesService.selectExchangeRulesById(id));
    }

    /**
     * 新增积分兑换规则
     */
    @PreAuthorize("@ss.hasPermi('qwk:rules:add')")
    @Log(title = "积分兑换规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExchangeRules exchangeRules)
    {
        return toAjax(exchangeRulesService.insertExchangeRules(exchangeRules));
    }

    /**
     * 修改积分兑换规则
     */
    @PreAuthorize("@ss.hasPermi('qwk:rules:edit')")
    @Log(title = "积分兑换规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExchangeRules exchangeRules)
    {
        return toAjax(exchangeRulesService.updateExchangeRules(exchangeRules));
    }

    /**
     * 删除积分兑换规则
     */
    @PreAuthorize("@ss.hasPermi('qwk:rules:remove')")
    @Log(title = "积分兑换规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exchangeRulesService.deleteExchangeRulesByIds(ids));
    }
}
