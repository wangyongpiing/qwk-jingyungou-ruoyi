package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.VipLevel;
import com.ruoyi.qwk.service.IVipLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * vip等级Controller
 *
 * @author cjf
 * @date 2023-09-13
 */
@RestController
@RequestMapping("/qwk/level")
public class VipLevelController extends BaseController
{
    @Autowired
    private IVipLevelService vipLevelService;

    /**
     * 查询vip等级列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:level:list')")
    @GetMapping("/list")
    public TableDataInfo list(VipLevel vipLevel)
    {
        startPage();
        List<VipLevel> list = vipLevelService.selectVipLevelList(vipLevel);
        return getDataTable(list);
    }

    /**
     * 导出vip等级列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:level:export')")
    @Log(title = "vip等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VipLevel vipLevel)
    {
        List<VipLevel> list = vipLevelService.selectVipLevelList(vipLevel);
        ExcelUtil<VipLevel> util = new ExcelUtil<VipLevel>(VipLevel.class);
        util.exportExcel(response, list, "vip等级数据");
    }

    /**
     * 获取vip等级详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:level:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vipLevelService.selectVipLevelById(id));
    }

    /**
     * 新增vip等级
     */
    @PreAuthorize("@ss.hasPermi('qwk:level:add')")
    @Log(title = "vip等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipLevel vipLevel)
    {
        return toAjax(vipLevelService.insertVipLevel(vipLevel));
    }

    /**
     * 修改vip等级
     */
    @PreAuthorize("@ss.hasPermi('qwk:level:edit')")
    @Log(title = "vip等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipLevel vipLevel)
    {
        return toAjax(vipLevelService.updateVipLevel(vipLevel));
    }

    /**
     * 删除vip等级
     */
    @PreAuthorize("@ss.hasPermi('qwk:level:remove')")
    @Log(title = "vip等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vipLevelService.deleteVipLevelByIds(ids));
    }
}
