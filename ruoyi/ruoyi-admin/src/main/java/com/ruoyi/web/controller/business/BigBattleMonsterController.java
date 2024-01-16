package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.domain.BigBattleMonster;
import com.ruoyi.qwk.service.IBigBattleMonsterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 *  大作战怪物Controller
 *
 * @author cjf
 * @date 2024-01-15
 */
@RestController
@RequestMapping("/qwk/bigBattleMonster")
public class BigBattleMonsterController extends BaseController
{
    @Autowired
    private IBigBattleMonsterService bigBattleMonsterService;

    /**
     * 查询 大作战怪物列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:monster:list')")
    @GetMapping("/list")
    public TableDataInfo list(BigBattleMonster bigBattleMonster)
    {
        startPage();
        List<BigBattleMonster> list = bigBattleMonsterService.selectBigBattleMonsterList(bigBattleMonster);
        return getDataTable(list);
    }

    /**
     * 导出 大作战怪物列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:monster:export')")
    @Log(title = " 大作战怪物", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BigBattleMonster bigBattleMonster)
    {
        List<BigBattleMonster> list = bigBattleMonsterService.selectBigBattleMonsterList(bigBattleMonster);
        ExcelUtil<BigBattleMonster> util = new ExcelUtil<BigBattleMonster>(BigBattleMonster.class);
        util.exportExcel(response, list, " 大作战怪物数据");
    }

    /**
     * 获取 大作战怪物详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:monster:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bigBattleMonsterService.selectBigBattleMonsterById(id));
    }

    /**
     * 新增 大作战怪物
     */
    @PreAuthorize("@ss.hasPermi('qwk:monster:add')")
    @Log(title = " 大作战怪物", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BigBattleMonster bigBattleMonster)
    {
        return toAjax(bigBattleMonsterService.insertBigBattleMonster(bigBattleMonster));
    }

    /**
     * 修改 大作战怪物
     */
    @PreAuthorize("@ss.hasPermi('qwk:monster:edit')")
    @Log(title = " 大作战怪物", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BigBattleMonster bigBattleMonster)
    {
        return toAjax(bigBattleMonsterService.updateBigBattleMonster(bigBattleMonster));
    }

    /**
     * 删除 大作战怪物
     */
    @PreAuthorize("@ss.hasPermi('qwk:monster:remove')")
    @Log(title = " 大作战怪物", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bigBattleMonsterService.deleteBigBattleMonsterByIds(ids));
    }

    /**
     * 怪物生成
     * @param userId
     * @param monsterId
     * @return
     */
    @GetMapping("/generateMonsters")
    @ApiOperation(value = "怪物生成")
    @Anonymous
    public AjaxResult generateMonsters(@RequestParam("userId") String userId,@RequestParam("monsterId") String monsterId){
        return bigBattleMonsterService.generateMonsters(userId,monsterId);
    }

    /**
     * 怪物死亡
     * @param userId
     * @param monsterId
     * @return
     */
    @GetMapping("/death")
    @ApiOperation(value = "怪物死亡")
    @Anonymous
    public AjaxResult deathMonsters(@RequestParam("userId") String userId,@RequestParam("monsterId") String monsterId){
        return bigBattleMonsterService.deathMonsters(userId,monsterId);
    }
}
