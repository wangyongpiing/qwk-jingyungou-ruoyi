package com.ruoyi.qwk.blindBox.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.blindBox.domain.BlindBoxProbability;
import com.ruoyi.qwk.blindBox.service.IBlindBoxProbabilityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 盲盒概率Controller
 * 
 * @author cjf
 * @date 2023-10-08
 */
@RestController
@RequestMapping("/qwk/blindBoxProbability")
public class BlindBoxProbabilityController extends BaseController
{
    @Autowired
    private IBlindBoxProbabilityService blindBoxProbabilityService;

    /**
     * 查询盲盒概率列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlindBoxProbability blindBoxProbability)
    {
        startPage();
        List<BlindBoxProbability> list = blindBoxProbabilityService.selectBlindBoxProbabilityList(blindBoxProbability);
        return getDataTable(list);
    }

    /**
     * 导出盲盒概率列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:export')")
    @Log(title = "盲盒概率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlindBoxProbability blindBoxProbability)
    {
        List<BlindBoxProbability> list = blindBoxProbabilityService.selectBlindBoxProbabilityList(blindBoxProbability);
        ExcelUtil<BlindBoxProbability> util = new ExcelUtil<BlindBoxProbability>(BlindBoxProbability.class);
        util.exportExcel(response, list, "盲盒概率数据");
    }

    /**
     * 获取盲盒概率详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blindBoxProbabilityService.selectBlindBoxProbabilityById(id));
    }

    /**
     * 新增盲盒概率
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:add')")
    @Log(title = "盲盒概率", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlindBoxProbability blindBoxProbability)
    {
        return toAjax(blindBoxProbabilityService.insertBlindBoxProbability(blindBoxProbability));
    }

    /**
     * 修改盲盒概率
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:edit')")
    @Log(title = "盲盒概率", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlindBoxProbability blindBoxProbability)
    {
        return toAjax(blindBoxProbabilityService.updateBlindBoxProbability(blindBoxProbability));
    }

    /**
     * 删除盲盒概率
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:remove')")
    @Log(title = "盲盒概率", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blindBoxProbabilityService.deleteBlindBoxProbabilityByIds(ids));
    }
}
