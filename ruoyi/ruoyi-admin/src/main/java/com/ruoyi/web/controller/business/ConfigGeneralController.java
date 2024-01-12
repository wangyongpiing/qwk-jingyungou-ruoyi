package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.ConfigGeneral;
import com.ruoyi.qwk.service.IConfigGeneralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * app通用配置Controller
 *
 * @author cjf
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/qwk/general")
@Api(tags = "app通用配置API")
public class ConfigGeneralController extends BaseController
{
    @Autowired
    private IConfigGeneralService configGeneralService;

    /**
     * 查询app通用配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:general:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConfigGeneral configGeneral)
    {
        startPage();
        List<ConfigGeneral> list = configGeneralService.selectConfigGeneralList(configGeneral);
        return getDataTable(list);
    }

    /**
     * 导出app通用配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:general:export')")
    @Log(title = "app通用配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ConfigGeneral configGeneral)
    {
        List<ConfigGeneral> list = configGeneralService.selectConfigGeneralList(configGeneral);
        ExcelUtil<ConfigGeneral> util = new ExcelUtil<ConfigGeneral>(ConfigGeneral.class);
        util.exportExcel(response, list, "app通用配置数据");
    }

    /**
     * 获取app通用配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:general:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(configGeneralService.selectConfigGeneralById(id));
    }

    /**
     * 新增app通用配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:general:add')")
    @Log(title = "app通用配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConfigGeneral configGeneral)
    {
        return toAjax(configGeneralService.insertConfigGeneral(configGeneral));
    }

    /**
     * 修改app通用配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:general:edit')")
    @Log(title = "app通用配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConfigGeneral configGeneral)
    {
        return toAjax(configGeneralService.updateConfigGeneral(configGeneral));
    }

    /**
     * 删除app通用配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:general:remove')")
    @Log(title = "app通用配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(configGeneralService.deleteConfigGeneralByIds(ids));
    }

//    -----------------------------app------------------------------------------
    @GetMapping(value = "/getGeneral/{id}")
    @ApiOperation("根据id获取配置文本详情")
    public AjaxResult getGeneral(@PathVariable("id") Long id)
    {
        return success(configGeneralService.selectConfigGeneralById(id));
    }

}
