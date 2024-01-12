package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.InviteRewardsRecord;
import com.ruoyi.qwk.service.IInviteRewardsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 邀请奖励记录Controller
 *
 * @author cjf
 * @date 2023-10-09
 */
@RestController
@RequestMapping("/qwk/inviteRewardsRecord")
public class InviteRewardsRecordController extends BaseController
{
    @Autowired
    private IInviteRewardsRecordService inviteRewardsRecordService;


    /**
     * 查询邀请奖励记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewardsRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(InviteRewardsRecord inviteRewardsRecord)
    {
        startPage();
        List<InviteRewardsRecord> list = inviteRewardsRecordService.selectInviteRewardsRecordList(inviteRewardsRecord);
        return getDataTable(list);
    }

    /**
     * 导出邀请奖励记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewardsRecord:export')")
    @Log(title = "邀请奖励记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InviteRewardsRecord inviteRewardsRecord)
    {
        List<InviteRewardsRecord> list = inviteRewardsRecordService.selectInviteRewardsRecordList(inviteRewardsRecord);
        ExcelUtil<InviteRewardsRecord> util = new ExcelUtil<InviteRewardsRecord>(InviteRewardsRecord.class);
        util.exportExcel(response, list, "邀请奖励记录数据");
    }

    /**
     * 获取邀请奖励记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewardsRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inviteRewardsRecordService.selectInviteRewardsRecordById(id));
    }

    /**
     * 新增邀请奖励记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewardsRecord:add')")
    @Log(title = "邀请奖励记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InviteRewardsRecord inviteRewardsRecord)
    {
        return toAjax(inviteRewardsRecordService.insertInviteRewardsRecord(inviteRewardsRecord));
    }

    /**
     * 修改邀请奖励记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewardsRecord:edit')")
    @Log(title = "邀请奖励记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InviteRewardsRecord inviteRewardsRecord)
    {
        return toAjax(inviteRewardsRecordService.updateInviteRewardsRecord(inviteRewardsRecord));
    }

    /**
     * 删除邀请奖励记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewardsRecord:remove')")
    @Log(title = "邀请奖励记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inviteRewardsRecordService.deleteInviteRewardsRecordByIds(ids));
    }
}
