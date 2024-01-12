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
import com.ruoyi.qwk.blindBox.domain.RecoveryConfig;
import com.ruoyi.qwk.blindBox.service.IRecoveryConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 玩家商品自动回收配置Controller
 * 
 * @author cjf
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/qwk/recoveryConfig")
public class RecoveryConfigController extends BaseController
{
    @Autowired
    private IRecoveryConfigService recoveryConfigService;

    /**
     * 查询玩家商品自动回收配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:recoveryConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(RecoveryConfig recoveryConfig)
    {
        startPage();
        List<RecoveryConfig> list = recoveryConfigService.selectRecoveryConfigList(recoveryConfig);
        return getDataTable(list);
    }

    /**
     * 导出玩家商品自动回收配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:recoveryConfig:export')")
    @Log(title = "玩家商品自动回收配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RecoveryConfig recoveryConfig)
    {
        List<RecoveryConfig> list = recoveryConfigService.selectRecoveryConfigList(recoveryConfig);
        ExcelUtil<RecoveryConfig> util = new ExcelUtil<RecoveryConfig>(RecoveryConfig.class);
        util.exportExcel(response, list, "玩家商品自动回收配置数据");
    }

    /**
     * 获取玩家商品自动回收配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:recoveryConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(recoveryConfigService.selectRecoveryConfigById(id));
    }

    /**
     * 新增玩家商品自动回收配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:recoveryConfig:add')")
    @Log(title = "玩家商品自动回收配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecoveryConfig recoveryConfig)
    {
        return toAjax(recoveryConfigService.insertRecoveryConfig(recoveryConfig));
    }

    /**
     * 修改玩家商品自动回收配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:recoveryConfig:edit')")
    @Log(title = "玩家商品自动回收配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RecoveryConfig recoveryConfig)
    {
        return toAjax(recoveryConfigService.updateRecoveryConfig(recoveryConfig));
    }

    /**
     * 删除玩家商品自动回收配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:recoveryConfig:remove')")
    @Log(title = "玩家商品自动回收配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(recoveryConfigService.deleteRecoveryConfigByIds(ids));
    }
}
