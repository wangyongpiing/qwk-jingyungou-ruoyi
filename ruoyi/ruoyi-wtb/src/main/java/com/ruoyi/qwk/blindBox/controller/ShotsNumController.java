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
import com.ruoyi.qwk.blindBox.domain.ShotsNum;
import com.ruoyi.qwk.blindBox.service.IShotsNumService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 射击次数Controller
 * 
 * @author cjf
 * @date 2023-10-20
 */
@RestController
@RequestMapping("/qwk/shotsNum")
public class ShotsNumController extends BaseController
{
    @Autowired
    private IShotsNumService shotsNumService;

    /**
     * 查询射击次数列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:shotsNum:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShotsNum shotsNum)
    {
        startPage();
        List<ShotsNum> list = shotsNumService.selectShotsNumList(shotsNum);
        return getDataTable(list);
    }

    /**
     * 导出射击次数列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:shotsNum:export')")
    @Log(title = "射击次数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShotsNum shotsNum)
    {
        List<ShotsNum> list = shotsNumService.selectShotsNumList(shotsNum);
        ExcelUtil<ShotsNum> util = new ExcelUtil<ShotsNum>(ShotsNum.class);
        util.exportExcel(response, list, "射击次数数据");
    }

    /**
     * 获取射击次数详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:shotsNum:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shotsNumService.selectShotsNumById(id));
    }

    /**
     * 新增射击次数
     */
    @PreAuthorize("@ss.hasPermi('qwk:shotsNum:add')")
    @Log(title = "射击次数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShotsNum shotsNum)
    {
        return toAjax(shotsNumService.insertShotsNum(shotsNum));
    }

    /**
     * 修改射击次数
     */
    @PreAuthorize("@ss.hasPermi('qwk:shotsNum:edit')")
    @Log(title = "射击次数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShotsNum shotsNum)
    {
        return toAjax(shotsNumService.updateShotsNum(shotsNum));
    }

    /**
     * 删除射击次数
     */
    @PreAuthorize("@ss.hasPermi('qwk:shotsNum:remove')")
    @Log(title = "射击次数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shotsNumService.deleteShotsNumByIds(ids));
    }
}
