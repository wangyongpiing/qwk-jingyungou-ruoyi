package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.Reward;
import com.ruoyi.qwk.domain.TheCharts;
import com.ruoyi.qwk.service.IRewardService;
import com.ruoyi.qwk.service.ITheChartsService;
import com.ruoyi.system.mapper.SysUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 游戏排行榜Controller
 *
 * @author cjf
 * @date 2023-07-17
 */
@RestController
@RequestMapping("/qwk/charts")
@Api(tags = "游戏排行榜API")
public class TheChartsController extends BaseController
{
    @Autowired
    private ITheChartsService theChartsService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private IRewardService rewardService;

    /**
     * 查询游戏排行榜列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:charts:list')")
    @GetMapping("/list")
    public TableDataInfo list(TheCharts theCharts)
    {
        startPage();
        List<TheCharts> list = theChartsService.selectTheChartsList(theCharts);
        return getDataTable(list);
    }

    /**
     * 导出游戏排行榜列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:charts:export')")
    @Log(title = "游戏排行榜", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TheCharts theCharts)
    {
        List<TheCharts> list = theChartsService.selectTheChartsList(theCharts);
        ExcelUtil<TheCharts> util = new ExcelUtil<TheCharts>(TheCharts.class);
        util.exportExcel(response, list, "游戏排行榜数据");
    }

    /**
     * 获取游戏排行榜详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:charts:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(theChartsService.selectTheChartsById(id));
    }

    /**
     * 新增游戏排行榜
     */
    @PreAuthorize("@ss.hasPermi('qwk:charts:add')")
    @Log(title = "游戏排行榜", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TheCharts theCharts)
    {
        return toAjax(theChartsService.insertTheCharts(theCharts));
    }

    /**
     * 修改游戏排行榜
     */
    @PreAuthorize("@ss.hasPermi('qwk:charts:edit')")
    @Log(title = "游戏排行榜", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TheCharts theCharts)
    {
        return toAjax(theChartsService.updateTheCharts(theCharts));
    }

    /**
     * 删除游戏排行榜
     */
    @PreAuthorize("@ss.hasPermi('qwk:charts:remove')")
    @Log(title = "游戏排行榜", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(theChartsService.deleteTheChartsByIds(ids));
    }

//--------------------------------------------------------------------------------------

    @ApiOperation("获取排行榜数据")
    @GetMapping(value = "/getTheCharts/{gameId}")
//    @Anonymous
    public AjaxResult getTheCharts(@PathVariable Long gameId){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<TheCharts> theCharts = theChartsService.selectTheChartsByGameId(gameId);
        TheCharts byUserId = theChartsService.selectTheChartsByUserId(gameId,userId);
        Reward reward = new Reward();
        reward.setGameId(gameId);
        List<Reward> rewards = rewardService.selectRewardList(reward);
        return success()
                .put("theCharts",theCharts)
                .put("myData",byUserId)
                .put("rewards",rewards);
    }

    /**
     * 修改游戏排行榜
     */
    @ApiOperation("修改游戏排行榜数据")
    @Log(title = "游戏排行榜", businessType = BusinessType.UPDATE)
    @PostMapping("/updateTheCharts")
    public AjaxResult updateTheCharts(@RequestBody TheCharts theCharts){
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        TheCharts byUserId = theChartsService.selectTheChartsByUserId(theCharts.getGameId(), sysUser.getUserId());
        if (ObjectUtils.isEmpty(byUserId)){
            byUserId = new TheCharts();
            byUserId.setNikeName(sysUser.getNickName());
            byUserId.setAvatar(ObjectUtils.isEmpty(sysUser.getAvatar()) ? "https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath%2F2023%2F08%2F29%2F%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20230822114504_20230829105425A002.png" : sysUser.getAvatar() );
            byUserId.setCreateBy(sysUser.getUserName());
            int i = theChartsService.insertTheCharts(byUserId);
            if (i<=0)return AjaxResult.error();
        }
        byUserId.setAvatar(ObjectUtils.isEmpty(sysUser.getAvatar()) ? "https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath%2F2023%2F08%2F29%2F%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20230822114504_20230829105425A002.png" : sysUser.getAvatar() );
        byUserId.setCombatPower(theCharts.getCombatPower());
        byUserId.setUpdateBy(sysUser.getUserName());
        return toAjax(theChartsService.updateTheCharts(byUserId));
    }

}
