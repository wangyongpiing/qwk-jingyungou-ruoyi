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
import com.ruoyi.qwk.blindBox.domain.AnimalsSkin;
import com.ruoyi.qwk.blindBox.service.IAnimalsSkinService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 皮肤配置Controller
 * 
 * @author cjf
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/qwk/skin")
public class AnimalsSkinController extends BaseController
{
    @Autowired
    private IAnimalsSkinService animalsSkinService;

    /**
     * 查询皮肤配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:skin:list')")
    @GetMapping("/list")
    public TableDataInfo list(AnimalsSkin animalsSkin)
    {
        startPage();
        List<AnimalsSkin> list = animalsSkinService.selectAnimalsSkinList(animalsSkin);
        return getDataTable(list);
    }

    /**
     * 导出皮肤配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:skin:export')")
    @Log(title = "皮肤配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AnimalsSkin animalsSkin)
    {
        List<AnimalsSkin> list = animalsSkinService.selectAnimalsSkinList(animalsSkin);
        ExcelUtil<AnimalsSkin> util = new ExcelUtil<AnimalsSkin>(AnimalsSkin.class);
        util.exportExcel(response, list, "皮肤配置数据");
    }

    /**
     * 获取皮肤配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:skin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(animalsSkinService.selectAnimalsSkinById(id));
    }

    /**
     * 新增皮肤配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:skin:add')")
    @Log(title = "皮肤配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnimalsSkin animalsSkin)
    {
        return toAjax(animalsSkinService.insertAnimalsSkin(animalsSkin));
    }

    /**
     * 修改皮肤配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:skin:edit')")
    @Log(title = "皮肤配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AnimalsSkin animalsSkin)
    {
        return toAjax(animalsSkinService.updateAnimalsSkin(animalsSkin));
    }

    /**
     * 删除皮肤配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:skin:remove')")
    @Log(title = "皮肤配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(animalsSkinService.deleteAnimalsSkinByIds(ids));
    }
}
