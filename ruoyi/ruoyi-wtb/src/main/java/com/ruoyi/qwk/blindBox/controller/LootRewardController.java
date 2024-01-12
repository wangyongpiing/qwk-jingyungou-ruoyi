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
import com.ruoyi.qwk.blindBox.domain.LootReward;
import com.ruoyi.qwk.blindBox.service.ILootRewardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 掉落奖励Controller
 * 
 * @author cjf
 * @date 2023-09-14
 */
@RestController
@RequestMapping("/qwk/lootReward")
public class LootRewardController extends BaseController
{
    @Autowired
    private ILootRewardService lootRewardService;

    /**
     * 查询掉落奖励列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:lootReward:list')")
    @GetMapping("/list")
    public TableDataInfo list(LootReward lootReward)
    {
        startPage();
        List<LootReward> list = lootRewardService.selectLootRewardList(lootReward);
        return getDataTable(list);
    }

    /**
     * 导出掉落奖励列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:lootReward:export')")
    @Log(title = "掉落奖励", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LootReward lootReward)
    {
        List<LootReward> list = lootRewardService.selectLootRewardList(lootReward);
        ExcelUtil<LootReward> util = new ExcelUtil<LootReward>(LootReward.class);
        util.exportExcel(response, list, "掉落奖励数据");
    }

    /**
     * 获取掉落奖励详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:lootReward:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(lootRewardService.selectLootRewardById(id));
    }

    /**
     * 新增掉落奖励
     */
    @PreAuthorize("@ss.hasPermi('qwk:lootReward:add')")
    @Log(title = "掉落奖励", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LootReward lootReward)
    {
        return toAjax(lootRewardService.insertLootReward(lootReward));
    }

    /**
     * 修改掉落奖励
     */
    @PreAuthorize("@ss.hasPermi('qwk:lootReward:edit')")
    @Log(title = "掉落奖励", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LootReward lootReward)
    {
        return toAjax(lootRewardService.updateLootReward(lootReward));
    }

    /**
     * 删除掉落奖励
     */
    @PreAuthorize("@ss.hasPermi('qwk:lootReward:remove')")
    @Log(title = "掉落奖励", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(lootRewardService.deleteLootRewardByIds(ids));
    }
}
