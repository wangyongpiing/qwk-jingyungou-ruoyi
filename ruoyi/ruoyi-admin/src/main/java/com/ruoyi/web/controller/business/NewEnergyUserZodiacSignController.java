package com.ruoyi.web.controller.business;

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
import com.ruoyi.qwk.domain.NewEnergyUserZodiacSign;
import com.ruoyi.qwk.service.INewEnergyUserZodiacSignService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户生肖资产Controller
 *
 * @author cjf
 * @date 2023-12-08
 */
@RestController
@RequestMapping("/qwk/sign")
public class NewEnergyUserZodiacSignController extends BaseController
{
    @Autowired
    private INewEnergyUserZodiacSignService newEnergyUserZodiacSignService;

    /**
     * 查询用户生肖资产列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:sign:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewEnergyUserZodiacSign newEnergyUserZodiacSign)
    {
        startPage();
        List<NewEnergyUserZodiacSign> list = newEnergyUserZodiacSignService.selectNewEnergyUserZodiacSignList(newEnergyUserZodiacSign);
        return getDataTable(list);
    }

    /**
     * 导出用户生肖资产列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:sign:export')")
    @Log(title = "用户生肖资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewEnergyUserZodiacSign newEnergyUserZodiacSign)
    {
        List<NewEnergyUserZodiacSign> list = newEnergyUserZodiacSignService.selectNewEnergyUserZodiacSignList(newEnergyUserZodiacSign);
        ExcelUtil<NewEnergyUserZodiacSign> util = new ExcelUtil<NewEnergyUserZodiacSign>(NewEnergyUserZodiacSign.class);
        util.exportExcel(response, list, "用户生肖资产数据");
    }

    /**
     * 获取用户生肖资产详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:sign:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(newEnergyUserZodiacSignService.selectNewEnergyUserZodiacSignById(id));
    }

    /**
     * 新增用户生肖资产
     */
    @PreAuthorize("@ss.hasPermi('qwk:sign:add')")
    @Log(title = "用户生肖资产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewEnergyUserZodiacSign newEnergyUserZodiacSign)
    {
        return toAjax(newEnergyUserZodiacSignService.insertNewEnergyUserZodiacSign(newEnergyUserZodiacSign));
    }

    /**
     * 修改用户生肖资产
     */
    @PreAuthorize("@ss.hasPermi('qwk:sign:edit')")
    @Log(title = "用户生肖资产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewEnergyUserZodiacSign newEnergyUserZodiacSign)
    {
        return toAjax(newEnergyUserZodiacSignService.updateNewEnergyUserZodiacSign(newEnergyUserZodiacSign));
    }

    /**
     * 删除用户生肖资产
     */
    @PreAuthorize("@ss.hasPermi('qwk:sign:remove')")
    @Log(title = "用户生肖资产", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(newEnergyUserZodiacSignService.deleteNewEnergyUserZodiacSignByIds(ids));
    }
}
