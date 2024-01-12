package com.ruoyi.qwk.blindBox.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.qwk.blindBox.domain.FishingMonster;
import com.ruoyi.qwk.blindBox.service.IFishingMonsterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 捕鱼怪物Controller
 *
 * @author cjf
 * @date 2023-09-14
 */
@RestController
@RequestMapping("/qwk/fishingMonster")
@Api(tags = "盲盒API")
public class FishingMonsterController extends BaseController
{
    @Autowired
    private IFishingMonsterService fishingMonsterService;

    /**
     * 查询捕鱼怪物列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:fishingMonster:list')")
    @GetMapping("/list")
    public TableDataInfo list(FishingMonster fishingMonster)
    {
        startPage();
        List<FishingMonster> list = fishingMonsterService.selectFishingMonsterList(fishingMonster);
        return getDataTable(list);
    }

    /**
     * 导出捕鱼怪物列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:fishingMonster:export')")
    @Log(title = "捕鱼怪物", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FishingMonster fishingMonster)
    {
        List<FishingMonster> list = fishingMonsterService.selectFishingMonsterList(fishingMonster);
        ExcelUtil<FishingMonster> util = new ExcelUtil<FishingMonster>(FishingMonster.class);
        util.exportExcel(response, list, "捕鱼怪物数据");
    }

    /**
     * 获取捕鱼怪物详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:fishingMonster:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fishingMonsterService.selectFishingMonsterById(id));
    }

    /**
     * 新增捕鱼怪物
     */
    @PreAuthorize("@ss.hasPermi('qwk:fishingMonster:add')")
    @Log(title = "捕鱼怪物", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FishingMonster fishingMonster)
    {
        return toAjax(fishingMonsterService.insertFishingMonster(fishingMonster));
    }

    /**
     * 修改捕鱼怪物
     */
    @PreAuthorize("@ss.hasPermi('qwk:fishingMonster:edit')")
    @Log(title = "捕鱼怪物", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FishingMonster fishingMonster)
    {
        return toAjax(fishingMonsterService.updateFishingMonster(fishingMonster));
    }

    /**
     * 删除捕鱼怪物
     */
    @PreAuthorize("@ss.hasPermi('qwk:fishingMonster:remove')")
    @Log(title = "捕鱼怪物", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fishingMonsterService.deleteFishingMonsterByIds(ids));
    }

//    --------------------------------app------------------------------------------------

    @GetMapping("/getAllFishingMonster")
    @ApiOperation("获取所有盲盒信息")
    public AjaxResult getAllFishingMonster(){
        List<FishingMonster> allFishingMonster = fishingMonsterService.getAllFishingMonster();
        return AjaxResult.success(allFishingMonster);
    }

    @PostMapping("/ifDeath")
    @ApiOperation("判断盲盒是否死亡以及掉落奖励")
    public AjaxResult ifDeath(@RequestBody List<Long> ids){
        return fishingMonsterService.ifDeath(ids);
    }

    /**
     * 判断盲盒是否掉落物品
     *
     * */
    @PostMapping("/isFalling")
    @ApiOperation("判断盲盒是否掉落奖励")
    @Log(title = "盲盒是否掉落奖励", businessType = BusinessType.UPDATE)
    public AjaxResult isFalling(@RequestBody FishingMonster fm){
        return fishingMonsterService.isFalling(fm);
    }

    /**
     * 获取初级场
     * */
    @GetMapping("/getElementary")
    @ApiOperation("获取初级场盲盒")
    public AjaxResult getElementary(){
        return AjaxResult.success(fishingMonsterService.getElementary());
    }

    /**
     * 获取中级场
     * */
    @GetMapping("/getIntermediate")
    @ApiOperation("获取中级场盲盒")
    public AjaxResult getIntermediate(){
        return AjaxResult.success(fishingMonsterService.getIntermediate());
    }

    /**
     * 获取高级场
     * */
    @GetMapping("/getSenior")
    @ApiOperation("获取高级场盲盒")
    public AjaxResult getSenior(){
        return AjaxResult.success(fishingMonsterService.getSenior());
    }

//    @GetMapping("/noviceIsFalling")
//    @ApiOperation("新手场盲盒是否掉落")
//    public AjaxResult noviceIsFalling(){
//        return fishingMonsterService.novicePrize();
//    }

    /**
     *
     * 修改用户为老用户
     * */
    @GetMapping("/updateByNovice")
    @ApiOperation("修改用户为老用户")
    public AjaxResult updateByNovice(){
        return fishingMonsterService.updateByNovice(SecurityUtils.getLoginUser().getUser().getUserId());
    }

    /**
     * 判断盲盒是否掉落物品
     *
     * */
    @PostMapping("/isFallingTwo")
    @ApiOperation("判断盲盒是否掉落奖励新")
    @Log(title = "盲盒是否掉落奖励", businessType = BusinessType.UPDATE)
    public AjaxResult isFallingTwo(@RequestBody FishingMonster fm){
//        return fishingMonsterService.isFallingTwo(fm);
        return fishingMonsterService.isFallingFour(fm);
    }

}
