package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.Reward;
import com.ruoyi.qwk.service.IRewardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 游戏奖励Controller
 *
 * @author cjf
 * @date 2023-07-17
 */
@RestController
@RequestMapping("/qwk/reward")
@Api(tags = "游戏排行奖励API")
public class RewardController extends BaseController
{
    @Autowired
    private IRewardService rewardService;

    /**
     * 查询游戏奖励列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:reward:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reward reward)
    {
        startPage();
        List<Reward> list = rewardService.selectRewardList(reward);
        return getDataTable(list);
    }

    /**
     * 导出游戏奖励列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:reward:export')")
    @Log(title = "游戏奖励", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reward reward)
    {
        List<Reward> list = rewardService.selectRewardList(reward);
        ExcelUtil<Reward> util = new ExcelUtil<Reward>(Reward.class);
        util.exportExcel(response, list, "游戏奖励数据");
    }

    /**
     * 获取游戏奖励详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:reward:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rewardService.selectRewardById(id));
    }

    /**
     * 新增游戏奖励
     */
    @PreAuthorize("@ss.hasPermi('qwk:reward:add')")
    @Log(title = "游戏奖励", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reward reward)
    {
        return toAjax(rewardService.insertReward(reward));
    }

    /**
     * 修改游戏奖励
     */
    @PreAuthorize("@ss.hasPermi('qwk:reward:edit')")
    @Log(title = "游戏奖励", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reward reward)
    {
        return toAjax(rewardService.updateReward(reward));
    }

    /**
     * 删除游戏奖励
     */
    @PreAuthorize("@ss.hasPermi('qwk:reward:remove')")
    @Log(title = "游戏奖励", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rewardService.deleteRewardByIds(ids));
    }

    /**
     * 获取游戏排行奖励信息
     */
//    @Anonymous
    @ApiOperation("获取游戏排行奖励信息")
    @GetMapping(value = "/getRewardList/{id}")
    public AjaxResult getRewardList(@PathVariable("id") Long id){
        Reward reward = new Reward();
        reward.setGameId(id);
        return success(rewardService.selectRewardList(reward));
    }
}
