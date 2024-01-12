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
import com.ruoyi.qwk.blindBox.domain.AnimalsMonster;
import com.ruoyi.qwk.blindBox.service.IAnimalsMonsterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 怪物配置Controller
 * 
 * @author cjf
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/qwk/animalsMonster")
public class AnimalsMonsterController extends BaseController
{
    @Autowired
    private IAnimalsMonsterService animalsMonsterService;

    /**
     * 查询怪物配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsMonster:list')")
    @GetMapping("/list")
    public TableDataInfo list(AnimalsMonster animalsMonster)
    {
        startPage();
        List<AnimalsMonster> list = animalsMonsterService.selectAnimalsMonsterList(animalsMonster);
        return getDataTable(list);
    }

    /**
     * 导出怪物配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsMonster:export')")
    @Log(title = "怪物配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AnimalsMonster animalsMonster)
    {
        List<AnimalsMonster> list = animalsMonsterService.selectAnimalsMonsterList(animalsMonster);
        ExcelUtil<AnimalsMonster> util = new ExcelUtil<AnimalsMonster>(AnimalsMonster.class);
        util.exportExcel(response, list, "怪物配置数据");
    }

    /**
     * 获取怪物配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsMonster:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(animalsMonsterService.selectAnimalsMonsterById(id));
    }

    /**
     * 新增怪物配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsMonster:add')")
    @Log(title = "怪物配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnimalsMonster animalsMonster)
    {
        return toAjax(animalsMonsterService.insertAnimalsMonster(animalsMonster));
    }

    /**
     * 修改怪物配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsMonster:edit')")
    @Log(title = "怪物配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AnimalsMonster animalsMonster)
    {
        return toAjax(animalsMonsterService.updateAnimalsMonster(animalsMonster));
    }

    /**
     * 删除怪物配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsMonster:remove')")
    @Log(title = "怪物配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(animalsMonsterService.deleteAnimalsMonsterByIds(ids));
    }
}
