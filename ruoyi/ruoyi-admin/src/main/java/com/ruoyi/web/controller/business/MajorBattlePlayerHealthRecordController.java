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
import com.ruoyi.qwk.domain.MajorBattlePlayerHealthRecord;
import com.ruoyi.qwk.service.IMajorBattlePlayerHealthRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 大作战玩家血量记录Controller
 *
 * @author cjf
 * @date 2024-01-15
 */
@RestController
@RequestMapping("/qwk/majorBattlePlayerHealthRecord")
public class MajorBattlePlayerHealthRecordController extends BaseController
{
    @Autowired
    private IMajorBattlePlayerHealthRecordService majorBattlePlayerHealthRecordService;

    /**
     * 查询大作战玩家血量记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(MajorBattlePlayerHealthRecord majorBattlePlayerHealthRecord)
    {
        startPage();
        List<MajorBattlePlayerHealthRecord> list = majorBattlePlayerHealthRecordService.selectMajorBattlePlayerHealthRecordList(majorBattlePlayerHealthRecord);
        return getDataTable(list);
    }

    /**
     * 导出大作战玩家血量记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:export')")
    @Log(title = "大作战玩家血量记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MajorBattlePlayerHealthRecord majorBattlePlayerHealthRecord)
    {
        List<MajorBattlePlayerHealthRecord> list = majorBattlePlayerHealthRecordService.selectMajorBattlePlayerHealthRecordList(majorBattlePlayerHealthRecord);
        ExcelUtil<MajorBattlePlayerHealthRecord> util = new ExcelUtil<MajorBattlePlayerHealthRecord>(MajorBattlePlayerHealthRecord.class);
        util.exportExcel(response, list, "大作战玩家血量记录数据");
    }

    /**
     * 获取大作战玩家血量记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(majorBattlePlayerHealthRecordService.selectMajorBattlePlayerHealthRecordById(id));
    }

    /**
     * 新增大作战玩家血量记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:add')")
    @Log(title = "大作战玩家血量记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MajorBattlePlayerHealthRecord majorBattlePlayerHealthRecord)
    {
        return toAjax(majorBattlePlayerHealthRecordService.insertMajorBattlePlayerHealthRecord(majorBattlePlayerHealthRecord));
    }

    /**
     * 修改大作战玩家血量记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:edit')")
    @Log(title = "大作战玩家血量记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MajorBattlePlayerHealthRecord majorBattlePlayerHealthRecord)
    {
        return toAjax(majorBattlePlayerHealthRecordService.updateMajorBattlePlayerHealthRecord(majorBattlePlayerHealthRecord));
    }

    /**
     * 删除大作战玩家血量记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:remove')")
    @Log(title = "大作战玩家血量记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(majorBattlePlayerHealthRecordService.deleteMajorBattlePlayerHealthRecordByIds(ids));
    }
}
