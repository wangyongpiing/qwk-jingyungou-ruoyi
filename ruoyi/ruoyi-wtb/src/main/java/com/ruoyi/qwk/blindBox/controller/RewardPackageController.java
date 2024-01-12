package com.ruoyi.qwk.blindBox.controller;

import java.util.HashMap;
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
import com.ruoyi.qwk.blindBox.domain.RewardPackage;
import com.ruoyi.qwk.blindBox.service.IRewardPackageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 奖励包Controller
 * 
 * @author cjf
 * @date 2023-09-14
 */
@RestController
@RequestMapping("/qwk/rewardPackage")
@Api(tags = "查询奖励包API")
public class RewardPackageController extends BaseController
{
    @Autowired
    private IRewardPackageService rewardPackageService;

    /**
     * 查询奖励包列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:rewardPackage:list')")
    @GetMapping("/list")
    public TableDataInfo list(RewardPackage rewardPackage)
    {
        startPage();
        List<RewardPackage> list = rewardPackageService.selectRewardPackageList(rewardPackage);
        return getDataTable(list);
    }

    /**
     * 导出奖励包列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:rewardPackage:export')")
    @Log(title = "奖励包", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RewardPackage rewardPackage)
    {
        List<RewardPackage> list = rewardPackageService.selectRewardPackageList(rewardPackage);
        ExcelUtil<RewardPackage> util = new ExcelUtil<RewardPackage>(RewardPackage.class);
        util.exportExcel(response, list, "奖励包数据");
    }

    /**
     * 获取奖励包详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:rewardPackage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rewardPackageService.selectRewardPackageById(id));
    }

    /**
     * 新增奖励包
     */
    @PreAuthorize("@ss.hasPermi('qwk:rewardPackage:add')")
    @Log(title = "奖励包", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RewardPackage rewardPackage)
    {
        return toAjax(rewardPackageService.insertRewardPackage(rewardPackage));
    }

    /**
     * 修改奖励包
     */
    @PreAuthorize("@ss.hasPermi('qwk:rewardPackage:edit')")
    @Log(title = "奖励包", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RewardPackage rewardPackage)
    {
        return toAjax(rewardPackageService.updateRewardPackage(rewardPackage));
    }

    /**
     * 删除奖励包
     */
    @PreAuthorize("@ss.hasPermi('qwk:rewardPackage:remove')")
    @Log(title = "奖励包", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rewardPackageService.deleteRewardPackageByIds(ids));
    }


//    -------------------------------app-------------------------------------------
    /**
     * 获取奖励包详细信息
     */
    @GetMapping(value = "/getRewardPackageInfo/{id}")
    @ApiOperation("获取奖励包详细信息")
    public AjaxResult getRewardPackageInfo(@PathVariable("id") Long id) {
        return success(rewardPackageService.selectRewardPackageById(id));
    }

    /**
     * 查询奖励包列表
     */
    @GetMapping("/getRewardPackageList")
    @ApiOperation("查询奖励包列表")
    public AjaxResult getRewardPackageList(RewardPackage rewardPackage)
    {
        startPage();
        List<RewardPackage> list = rewardPackageService.selectRewardPackageList(rewardPackage);
        HashMap<String, Object> map = new HashMap<>();
        TableDataInfo dataTable = getDataTable(list);
        map.put("total",dataTable.getTotal());
        map.put("rows",dataTable.getRows());
        return AjaxResult.success(map);
    }

}
