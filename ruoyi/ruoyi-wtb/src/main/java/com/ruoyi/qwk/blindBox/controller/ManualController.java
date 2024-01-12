package com.ruoyi.qwk.blindBox.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.qwk.blindBox.domain.Manual;
import com.ruoyi.qwk.blindBox.service.IManualService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 图鉴Controller
 * 
 * @author cjf
 * @date 2023-10-31
 */
@RestController
@RequestMapping("/qwk/manual")
@Api(tags = "图鉴API")
public class ManualController extends BaseController
{
    @Autowired
    private IManualService manualService;

    /**
     * 查询图鉴列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:manual:list')")
    @GetMapping("/list")
    public TableDataInfo list(Manual manual)
    {
        startPage();
        List<Manual> list = manualService.selectManualList(manual);
        return getDataTable(list);
    }

    /**
     * 导出图鉴列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:manual:export')")
    @Log(title = "图鉴", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Manual manual)
    {
        List<Manual> list = manualService.selectManualList(manual);
        ExcelUtil<Manual> util = new ExcelUtil<Manual>(Manual.class);
        util.exportExcel(response, list, "图鉴数据");
    }

    /**
     * 获取图鉴详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:manual:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(manualService.selectManualById(id));
    }

    /**
     * 新增图鉴
     */
    @PreAuthorize("@ss.hasPermi('qwk:manual:add')")
    @Log(title = "图鉴", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Manual manual)
    {
        return toAjax(manualService.insertManual(manual));
    }

    /**
     * 修改图鉴
     */
    @PreAuthorize("@ss.hasPermi('qwk:manual:edit')")
    @Log(title = "图鉴", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Manual manual)
    {
        return toAjax(manualService.updateManual(manual));
    }

    /**
     * 删除图鉴
     */
    @PreAuthorize("@ss.hasPermi('qwk:manual:remove')")
    @Log(title = "图鉴", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(manualService.deleteManualByIds(ids));
    }

//    -----------------------------------------app-------------------------------------------------------

    /**
     * 游戏获取图鉴
     * */
    @PostMapping("/getManual")
    @ApiOperation("获取图鉴")
    public AjaxResult getManual(@RequestBody Manual manual){
        return manualService.getManual(manual);
    }

}
