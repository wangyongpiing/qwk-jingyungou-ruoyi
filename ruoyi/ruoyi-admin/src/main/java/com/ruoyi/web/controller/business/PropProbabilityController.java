package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.PropProbability;
import com.ruoyi.qwk.service.IPropProbabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 道具爆率管理Controller
 *
 * @author cjf
 * @date 2023-07-31
 */
@RestController
@RequestMapping("/qwk/probability")
public class PropProbabilityController extends BaseController
{
    @Autowired
    private IPropProbabilityService propProbabilityService;

    /**
     * 查询道具爆率管理列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:list')")
    @GetMapping("/list")
    public TableDataInfo list(PropProbability propProbability)
    {
        startPage();
        List<PropProbability> list = propProbabilityService.selectPropProbabilityList(propProbability);
        return getDataTable(list);
    }

    /**
     * 导出道具爆率管理列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:export')")
    @Log(title = "道具爆率管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PropProbability propProbability)
    {
        List<PropProbability> list = propProbabilityService.selectPropProbabilityList(propProbability);
        ExcelUtil<PropProbability> util = new ExcelUtil<PropProbability>(PropProbability.class);
        util.exportExcel(response, list, "道具爆率管理数据");
    }

    /**
     * 获取道具爆率管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(propProbabilityService.selectPropProbabilityById(id));
    }

    /**
     * 新增道具爆率管理
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:add')")
    @Log(title = "道具爆率管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PropProbability propProbability)
    {
        return toAjax(propProbabilityService.insertPropProbability(propProbability));
    }

    /**
     * 修改道具爆率管理
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:edit')")
    @Log(title = "道具爆率管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PropProbability propProbability)
    {
        return toAjax(propProbabilityService.updatePropProbability(propProbability));
    }

    /**
     * 删除道具爆率管理
     */
    @PreAuthorize("@ss.hasPermi('qwk:probability:remove')")
    @Log(title = "道具爆率管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(propProbabilityService.deletePropProbabilityByIds(ids));
    }
}
