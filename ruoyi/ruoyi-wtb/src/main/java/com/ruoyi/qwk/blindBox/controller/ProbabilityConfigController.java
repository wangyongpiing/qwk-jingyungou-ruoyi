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
import com.ruoyi.qwk.blindBox.domain.ProbabilityConfig;
import com.ruoyi.qwk.blindBox.service.IProbabilityConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 每种物品的中奖概率配置Controller
 * 
 * @author cjf
 * @date 2023-10-23
 */
@RestController
@RequestMapping("/qwk/probabilityConfig")
public class ProbabilityConfigController extends BaseController
{
    @Autowired
    private IProbabilityConfigService probabilityConfigService;

    /**
     * 查询每种物品的中奖概率配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:probabilityConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProbabilityConfig probabilityConfig)
    {
        startPage();
        List<ProbabilityConfig> list = probabilityConfigService.selectProbabilityConfigList(probabilityConfig);
        return getDataTable(list);
    }

    /**
     * 导出每种物品的中奖概率配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:probabilityConfig:export')")
    @Log(title = "每种物品的中奖概率配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProbabilityConfig probabilityConfig)
    {
        List<ProbabilityConfig> list = probabilityConfigService.selectProbabilityConfigList(probabilityConfig);
        ExcelUtil<ProbabilityConfig> util = new ExcelUtil<ProbabilityConfig>(ProbabilityConfig.class);
        util.exportExcel(response, list, "每种物品的中奖概率配置数据");
    }

    /**
     * 获取每种物品的中奖概率配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:probabilityConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(probabilityConfigService.selectProbabilityConfigById(id));
    }

    /**
     * 新增每种物品的中奖概率配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:probabilityConfig:add')")
    @Log(title = "每种物品的中奖概率配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProbabilityConfig probabilityConfig)
    {
        return toAjax(probabilityConfigService.insertProbabilityConfig(probabilityConfig));
    }

    /**
     * 修改每种物品的中奖概率配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:probabilityConfig:edit')")
    @Log(title = "每种物品的中奖概率配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProbabilityConfig probabilityConfig)
    {
        return toAjax(probabilityConfigService.updateProbabilityConfig(probabilityConfig));
    }

    /**
     * 删除每种物品的中奖概率配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:probabilityConfig:remove')")
    @Log(title = "每种物品的中奖概率配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(probabilityConfigService.deleteProbabilityConfigByIds(ids));
    }




}
