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
import com.ruoyi.qwk.blindBox.domain.AnimalsSkill;
import com.ruoyi.qwk.blindBox.service.IAnimalsSkillService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 技能配置Controller
 * 
 * @author cjf
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/qwk/animalsSkill")
public class AnimalsSkillController extends BaseController
{
    @Autowired
    private IAnimalsSkillService animalsSkillService;

    /**
     * 查询技能配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsSkill:list')")
    @GetMapping("/list")
    public TableDataInfo list(AnimalsSkill animalsSkill)
    {
        startPage();
        List<AnimalsSkill> list = animalsSkillService.selectAnimalsSkillList(animalsSkill);
        return getDataTable(list);
    }

    /**
     * 导出技能配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsSkill:export')")
    @Log(title = "技能配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AnimalsSkill animalsSkill)
    {
        List<AnimalsSkill> list = animalsSkillService.selectAnimalsSkillList(animalsSkill);
        ExcelUtil<AnimalsSkill> util = new ExcelUtil<AnimalsSkill>(AnimalsSkill.class);
        util.exportExcel(response, list, "技能配置数据");
    }

    /**
     * 获取技能配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsSkill:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(animalsSkillService.selectAnimalsSkillById(id));
    }

    /**
     * 新增技能配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsSkill:add')")
    @Log(title = "技能配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnimalsSkill animalsSkill)
    {
        return toAjax(animalsSkillService.insertAnimalsSkill(animalsSkill));
    }

    /**
     * 修改技能配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsSkill:edit')")
    @Log(title = "技能配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AnimalsSkill animalsSkill)
    {
        return toAjax(animalsSkillService.updateAnimalsSkill(animalsSkill));
    }

    /**
     * 删除技能配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsSkill:remove')")
    @Log(title = "技能配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(animalsSkillService.deleteAnimalsSkillByIds(ids));
    }
}
