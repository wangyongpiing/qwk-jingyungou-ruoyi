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
import com.ruoyi.qwk.blindBox.domain.AnimalsDrop;
import com.ruoyi.qwk.blindBox.service.IAnimalsDropService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 掉落配置Controller
 * 
 * @author cjf
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/qwk/animalsDrop")
public class AnimalsDropController extends BaseController
{
    @Autowired
    private IAnimalsDropService animalsDropService;

    /**
     * 查询掉落配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsDrop:list')")
    @GetMapping("/list")
    public TableDataInfo list(AnimalsDrop animalsDrop)
    {
        startPage();
        List<AnimalsDrop> list = animalsDropService.selectAnimalsDropList(animalsDrop);
        return getDataTable(list);
    }

    /**
     * 导出掉落配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsDrop:export')")
    @Log(title = "掉落配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AnimalsDrop animalsDrop)
    {
        List<AnimalsDrop> list = animalsDropService.selectAnimalsDropList(animalsDrop);
        ExcelUtil<AnimalsDrop> util = new ExcelUtil<AnimalsDrop>(AnimalsDrop.class);
        util.exportExcel(response, list, "掉落配置数据");
    }

    /**
     * 获取掉落配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsDrop:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(animalsDropService.selectAnimalsDropById(id));
    }

    /**
     * 新增掉落配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsDrop:add')")
    @Log(title = "掉落配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnimalsDrop animalsDrop)
    {
        return toAjax(animalsDropService.insertAnimalsDrop(animalsDrop));
    }

    /**
     * 修改掉落配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsDrop:edit')")
    @Log(title = "掉落配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AnimalsDrop animalsDrop)
    {
        return toAjax(animalsDropService.updateAnimalsDrop(animalsDrop));
    }

    /**
     * 删除掉落配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:animalsDrop:remove')")
    @Log(title = "掉落配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(animalsDropService.deleteAnimalsDropByIds(ids));
    }
}
