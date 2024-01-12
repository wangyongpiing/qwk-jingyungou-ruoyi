package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.InviteSubordinate;
import com.ruoyi.qwk.service.IInviteSubordinateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 邀请用户关系Controller
 *
 * @author cjf
 * @date 2023-09-27
 */
@RestController
@RequestMapping("/qwk/inviteSubordinate")
public class InviteSubordinateController extends BaseController
{
    @Autowired
    private IInviteSubordinateService inviteSubordinateService;

    /**
     * 查询邀请用户关系列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteSubordinate:list')")
    @GetMapping("/list")
    public TableDataInfo list(InviteSubordinate inviteSubordinate)
    {
        startPage();
        List<InviteSubordinate> list = inviteSubordinateService.selectInviteSubordinateList(inviteSubordinate);
        return getDataTable(list);
    }

    /**
     * 导出邀请用户关系列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteSubordinate:export')")
    @Log(title = "邀请用户关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InviteSubordinate inviteSubordinate)
    {
        List<InviteSubordinate> list = inviteSubordinateService.selectInviteSubordinateList(inviteSubordinate);
        ExcelUtil<InviteSubordinate> util = new ExcelUtil<InviteSubordinate>(InviteSubordinate.class);
        util.exportExcel(response, list, "邀请用户关系数据");
    }

    /**
     * 获取邀请用户关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteSubordinate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inviteSubordinateService.selectInviteSubordinateById(id));
    }

    /**
     * 新增邀请用户关系
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteSubordinate:add')")
    @Log(title = "邀请用户关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InviteSubordinate inviteSubordinate)
    {
        return toAjax(inviteSubordinateService.insertInviteSubordinate(inviteSubordinate));
    }

    /**
     * 修改邀请用户关系
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteSubordinate:edit')")
    @Log(title = "邀请用户关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InviteSubordinate inviteSubordinate)
    {
        return toAjax(inviteSubordinateService.updateInviteSubordinate(inviteSubordinate));
    }

    /**
     * 删除邀请用户关系
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteSubordinate:remove')")
    @Log(title = "邀请用户关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inviteSubordinateService.deleteInviteSubordinateByIds(ids));
    }

    /**
     * App获取邀请人助力记录
     * @return
     */
    @GetMapping("/invitationAssistanceRecord")
    @ApiOperation("邀请人助力记录")
    public AjaxResult invitationAssistanceRecord(){
        return inviteSubordinateService.invitationAssistanceRecord();
    }
}
