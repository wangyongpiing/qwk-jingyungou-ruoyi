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
import com.ruoyi.qwk.blindBox.domain.AnimalsEquip;
import com.ruoyi.qwk.blindBox.service.IAnimalsEquipService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 装备配置Controller
 * 
 * @author cjf
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/qwk/equip")
public class AnimalsEquipController extends BaseController
{
    @Autowired
    private IAnimalsEquipService animalsEquipService;

    /**
     * 查询装备配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:equip:list')")
    @GetMapping("/list")
    public TableDataInfo list(AnimalsEquip animalsEquip)
    {
        startPage();
        List<AnimalsEquip> list = animalsEquipService.selectAnimalsEquipList(animalsEquip);
        return getDataTable(list);
    }

    /**
     * 导出装备配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:equip:export')")
    @Log(title = "装备配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AnimalsEquip animalsEquip)
    {
        List<AnimalsEquip> list = animalsEquipService.selectAnimalsEquipList(animalsEquip);
        ExcelUtil<AnimalsEquip> util = new ExcelUtil<AnimalsEquip>(AnimalsEquip.class);
        util.exportExcel(response, list, "装备配置数据");
    }

    /**
     * 获取装备配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:equip:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(animalsEquipService.selectAnimalsEquipById(id));
    }

    /**
     * 新增装备配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:equip:add')")
    @Log(title = "装备配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnimalsEquip animalsEquip)
    {
        return toAjax(animalsEquipService.insertAnimalsEquip(animalsEquip));
    }

    /**
     * 修改装备配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:equip:edit')")
    @Log(title = "装备配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AnimalsEquip animalsEquip)
    {
        return toAjax(animalsEquipService.updateAnimalsEquip(animalsEquip));
    }

    /**
     * 删除装备配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:equip:remove')")
    @Log(title = "装备配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(animalsEquipService.deleteAnimalsEquipByIds(ids));
    }
}
