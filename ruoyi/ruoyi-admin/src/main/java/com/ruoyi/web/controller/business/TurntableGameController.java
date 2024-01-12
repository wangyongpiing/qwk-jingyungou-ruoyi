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
import com.ruoyi.qwk.domain.TurntableGame;
import com.ruoyi.qwk.service.ITurntableGameService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 转盘游戏 Controller
 *
 * @author cjf
 * @date 2023-12-20
 */
@RestController
@RequestMapping("/qwk/turntablegame")
public class TurntableGameController extends BaseController
{
    @Autowired
    private ITurntableGameService turntableGameService;

    /**
     * 查询转盘游戏 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:list')")
    @GetMapping("/list")
    public TableDataInfo list(TurntableGame turntableGame)
    {
        startPage();
        List<TurntableGame> list = turntableGameService.selectTurntableGameList(turntableGame);
        return getDataTable(list);
    }

    /**
     * 导出转盘游戏 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:export')")
    @Log(title = "转盘游戏 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TurntableGame turntableGame)
    {
        List<TurntableGame> list = turntableGameService.selectTurntableGameList(turntableGame);
        ExcelUtil<TurntableGame> util = new ExcelUtil<TurntableGame>(TurntableGame.class);
        util.exportExcel(response, list, "转盘游戏 数据");
    }

    /**
     * 获取转盘游戏 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(turntableGameService.selectTurntableGameById(id));
    }

    /**
     * 新增转盘游戏
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:add')")
    @Log(title = "转盘游戏 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TurntableGame turntableGame)
    {
        return toAjax(turntableGameService.insertTurntableGame(turntableGame));
    }

    /**
     * 修改转盘游戏
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:edit')")
    @Log(title = "转盘游戏 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TurntableGame turntableGame)
    {
        return toAjax(turntableGameService.updateTurntableGame(turntableGame));
    }

    /**
     * 删除转盘游戏
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:remove')")
    @Log(title = "转盘游戏 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(turntableGameService.deleteTurntableGameByIds(ids));
    }

    /**
     * 获取生肖转盘及奖品列表
     * @return
     */
    @GetMapping("/lucky")
    @Log(title = "APP获取生肖转盘及奖品列表 ", businessType = BusinessType.OTHER)
    public AjaxResult luckyTurntablePrize(){
       return turntableGameService.luckyTurntablePrize();
    }
}
