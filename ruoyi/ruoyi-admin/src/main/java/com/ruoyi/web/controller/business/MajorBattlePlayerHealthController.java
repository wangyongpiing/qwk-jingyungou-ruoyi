package com.ruoyi.web.controller.business;

import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.domain.MajorBattlePlayerHealth;
import com.ruoyi.qwk.service.IMajorBattlePlayerHealthService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

/**
 * 大作战玩家血量 Controller
 *
 * @author cjf
 * @date 2024-01-15
 */
@RestController
@RequestMapping("/qwk/majorBattlePlayerHealth")
public class MajorBattlePlayerHealthController extends BaseController
{
    @Autowired
    private IMajorBattlePlayerHealthService majorBattlePlayerHealthService;

    /**
     * 查询大作战玩家血量 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:health:list')")
    @GetMapping("/list")
    public TableDataInfo list(MajorBattlePlayerHealth majorBattlePlayerHealth)
    {
        startPage();
        List<MajorBattlePlayerHealth> list = majorBattlePlayerHealthService.selectMajorBattlePlayerHealthList(majorBattlePlayerHealth);
        return getDataTable(list);
    }

    /**
     * 导出大作战玩家血量 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:health:export')")
    @Log(title = "大作战玩家血量 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MajorBattlePlayerHealth majorBattlePlayerHealth)
    {
        List<MajorBattlePlayerHealth> list = majorBattlePlayerHealthService.selectMajorBattlePlayerHealthList(majorBattlePlayerHealth);
        ExcelUtil<MajorBattlePlayerHealth> util = new ExcelUtil<MajorBattlePlayerHealth>(MajorBattlePlayerHealth.class);
        util.exportExcel(response, list, "大作战玩家血量 数据");
    }

    /**
     * 获取大作战玩家血量 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:health:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(majorBattlePlayerHealthService.selectMajorBattlePlayerHealthById(id));
    }

    /**
     * 新增大作战玩家血量
     */
    @PreAuthorize("@ss.hasPermi('qwk:health:add')")
    @Log(title = "大作战玩家血量 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MajorBattlePlayerHealth majorBattlePlayerHealth)
    {
        return toAjax(majorBattlePlayerHealthService.insertMajorBattlePlayerHealth(majorBattlePlayerHealth));
    }

    /**
     * 修改大作战玩家血量
     */
    @PreAuthorize("@ss.hasPermi('qwk:health:edit')")
    @Log(title = "大作战玩家血量 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MajorBattlePlayerHealth majorBattlePlayerHealth)
    {
        return toAjax(majorBattlePlayerHealthService.updateMajorBattlePlayerHealth(majorBattlePlayerHealth));
    }

    /**
     * 删除大作战玩家血量
     */
    @PreAuthorize("@ss.hasPermi('qwk:health:remove')")
    @Log(title = "大作战玩家血量 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(majorBattlePlayerHealthService.deleteMajorBattlePlayerHealthByIds(ids));
    }

    /**
     * 初始化玩家血量
     * @return
     */
    @GetMapping("/init")
    @ApiOperation(value = "初始化玩家血量")
    @Anonymous
    public AjaxResult initPlayerHealth(@RequestParam("userId") String userId,@RequestParam("bloodVolume") String bloodVolume){
        return majorBattlePlayerHealthService.initPlayerHealth(userId,bloodVolume);
    }

    /**
     * 扣减玩家血量
     * @return
     */
    @GetMapping("/reduce")
    @ApiOperation(value = "扣减玩家血量")
    @Anonymous
    public AjaxResult reduceHp(@RequestParam("userId") String userId,@RequestParam("reduceHp") String reduceHp){
        return majorBattlePlayerHealthService.reduceHp(userId,reduceHp);
    }
}
