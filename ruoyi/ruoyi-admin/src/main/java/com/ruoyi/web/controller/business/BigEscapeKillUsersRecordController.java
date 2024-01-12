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
import com.ruoyi.qwk.domain.BigEscapeKillUsersRecord;
import com.ruoyi.qwk.service.IBigEscapeKillUsersRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 大逃杀用户游戏记录Controller
 *
 * @author cjf
 * @date 2023-12-22
 */
@RestController
@RequestMapping("/qwk/bigEscapeKillUsersRecord")
public class BigEscapeKillUsersRecordController extends BaseController
{
    @Autowired
    private IBigEscapeKillUsersRecordService bigEscapeKillUsersRecordService;

    /**
     * 查询大逃杀用户游戏记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(BigEscapeKillUsersRecord bigEscapeKillUsersRecord)
    {
        startPage();
        List<BigEscapeKillUsersRecord> list = bigEscapeKillUsersRecordService.selectBigEscapeKillUsersRecordList(bigEscapeKillUsersRecord);
        return getDataTable(list);
    }

    /**
     * 导出大逃杀用户游戏记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:export')")
    @Log(title = "大逃杀用户游戏记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BigEscapeKillUsersRecord bigEscapeKillUsersRecord)
    {
        List<BigEscapeKillUsersRecord> list = bigEscapeKillUsersRecordService.selectBigEscapeKillUsersRecordList(bigEscapeKillUsersRecord);
        ExcelUtil<BigEscapeKillUsersRecord> util = new ExcelUtil<BigEscapeKillUsersRecord>(BigEscapeKillUsersRecord.class);
        util.exportExcel(response, list, "大逃杀用户游戏记录数据");
    }

    /**
     * 获取大逃杀用户游戏记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bigEscapeKillUsersRecordService.selectBigEscapeKillUsersRecordById(id));
    }

    /**
     * 新增大逃杀用户游戏记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:add')")
    @Log(title = "大逃杀用户游戏记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BigEscapeKillUsersRecord bigEscapeKillUsersRecord)
    {
        return toAjax(bigEscapeKillUsersRecordService.insertBigEscapeKillUsersRecord(bigEscapeKillUsersRecord));
    }

    /**
     * 修改大逃杀用户游戏记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:edit')")
    @Log(title = "大逃杀用户游戏记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BigEscapeKillUsersRecord bigEscapeKillUsersRecord)
    {
        return toAjax(bigEscapeKillUsersRecordService.updateBigEscapeKillUsersRecord(bigEscapeKillUsersRecord));
    }

    /**
     * 删除大逃杀用户游戏记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:remove')")
    @Log(title = "大逃杀用户游戏记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bigEscapeKillUsersRecordService.deleteBigEscapeKillUsersRecordByIds(ids));
    }
}
