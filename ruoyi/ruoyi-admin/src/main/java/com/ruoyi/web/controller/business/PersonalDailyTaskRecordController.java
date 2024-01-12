package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.domain.PersonalDailyTaskRecord;
import com.ruoyi.qwk.service.IPersonalDailyTaskRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 个人每日任务记录 Controller
 *
 * @author cjf
 * @date 2023-12-22
 */
@RestController
@RequestMapping("/qwk/personalDailyTaskrecord")
public class PersonalDailyTaskRecordController extends BaseController
{
    @Autowired
    private IPersonalDailyTaskRecordService personalDailyTaskRecordService;

    /**
     * 查询个人每日任务记录 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(PersonalDailyTaskRecord personalDailyTaskRecord)
    {
        startPage();
        List<PersonalDailyTaskRecord> list = personalDailyTaskRecordService.selectPersonalDailyTaskRecordList(personalDailyTaskRecord);
        return getDataTable(list);
    }

    /**
     * 导出个人每日任务记录 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:export')")
    @Log(title = "个人每日任务记录 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PersonalDailyTaskRecord personalDailyTaskRecord)
    {
        List<PersonalDailyTaskRecord> list = personalDailyTaskRecordService.selectPersonalDailyTaskRecordList(personalDailyTaskRecord);
        ExcelUtil<PersonalDailyTaskRecord> util = new ExcelUtil<PersonalDailyTaskRecord>(PersonalDailyTaskRecord.class);
        util.exportExcel(response, list, "个人每日任务记录 数据");
    }

    /**
     * 获取个人每日任务记录 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(personalDailyTaskRecordService.selectPersonalDailyTaskRecordById(id));
    }

    /**
     * 新增个人每日任务记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:add')")
    @Log(title = "个人每日任务记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonalDailyTaskRecord personalDailyTaskRecord)
    {
        return toAjax(personalDailyTaskRecordService.insertPersonalDailyTaskRecord(personalDailyTaskRecord));
    }

    /**
     * 修改个人每日任务记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:edit')")
    @Log(title = "个人每日任务记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonalDailyTaskRecord personalDailyTaskRecord)
    {
        return toAjax(personalDailyTaskRecordService.updatePersonalDailyTaskRecord(personalDailyTaskRecord));
    }

    /**
     * 删除个人每日任务记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:remove')")
    @Log(title = "个人每日任务记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(personalDailyTaskRecordService.deletePersonalDailyTaskRecordByIds(ids));
    }

    /**
     * APP领取任务
     * @param taskId
     * @return
     */
    @ApiOperation(value = "APP领取任务")
    @GetMapping("/claimTasks")
    public AjaxResult claimTasks(@RequestParam("id") String taskId,@RequestParam("isDaily") String isDaily){
        return personalDailyTaskRecordService.claimTasks(taskId,isDaily);
    }

    /**
     * 用户首次进入游戏
     * @param gameType
     * @return
     */
    @ApiOperation(value = "用户首次进入游戏")
    @GetMapping("/firstGame")
    @Anonymous
    public AjaxResult firstGame(@RequestParam("gameType") String gameType,@RequestParam("userId") String userId){
        return personalDailyTaskRecordService.firstGame(gameType,userId);
    }
}
