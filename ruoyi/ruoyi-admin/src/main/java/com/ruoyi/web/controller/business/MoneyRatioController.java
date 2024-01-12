package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.MoneyRatio;
import com.ruoyi.qwk.service.IMoneyRatioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 充值档位及比例 Controller
 *
 * @author cjf
 * @date 2023-06-30
 */
@RestController
@RequestMapping("/qwk/moneyRatio")
@Api(tags = "充值档位及比例")
public class MoneyRatioController extends BaseController
{
    @Autowired
    private IMoneyRatioService moneyRatioService;

    /**
     * 查询充值档位及比例 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:moneyRatio:list')")
    @GetMapping("/list")
    public TableDataInfo list(MoneyRatio moneyRatio)
    {
        startPage();
        List<MoneyRatio> list = moneyRatioService.selectMoneyRatioList(moneyRatio);
        return getDataTable(list);
    }

    /**
     * 导出充值档位及比例 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:moneyRatio:export')")
    @Log(title = "充值档位及比例 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MoneyRatio moneyRatio)
    {
        List<MoneyRatio> list = moneyRatioService.selectMoneyRatioList(moneyRatio);
        ExcelUtil<MoneyRatio> util = new ExcelUtil<MoneyRatio>(MoneyRatio.class);
        util.exportExcel(response, list, "充值档位及比例 数据");
    }

    /**
     * 获取充值档位及比例 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:moneyRatio:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(moneyRatioService.selectMoneyRatioById(id));
    }

    /**
     * 新增充值档位及比例
     */
    @PreAuthorize("@ss.hasPermi('qwk:moneyRatio:add')")
    @Log(title = "充值档位及比例 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MoneyRatio moneyRatio)
    {
        return toAjax(moneyRatioService.insertMoneyRatio(moneyRatio));
    }

    /**
     * 修改充值档位及比例
     */
    @PreAuthorize("@ss.hasPermi('qwk:moneyRatio:edit')")
    @Log(title = "充值档位及比例 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MoneyRatio moneyRatio)
    {
        return toAjax(moneyRatioService.updateMoneyRatio(moneyRatio));
    }

    /**
     * 删除充值档位及比例
     */
    @PreAuthorize("@ss.hasPermi('qwk:moneyRatio:remove')")
    @Log(title = "充值档位及比例 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(moneyRatioService.deleteMoneyRatioByIds(ids));
    }


//    -------------------app-------------------------
    /**
     * 获取充值档位及比例 详细信息
     */
    @GetMapping(value = "/getMoneyRatio/{id}")
    @ApiOperation("根据类型获取充值档位及比例")
    public AjaxResult getMoneyRatio(@PathVariable("id") Long id)
    {
        return success(moneyRatioService.selectMoneyRatioByType(id));
    }


}
