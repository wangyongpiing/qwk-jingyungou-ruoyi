package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.beust.ah.A;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.qwk.domain.AccountWithdrawalRecord;
import com.ruoyi.qwk.service.IAccountWithdrawalRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 账户提现记录 Controller
 *
 * @author cjf
 * @date 2023-12-27
 */
@RestController
@RequestMapping("/qwk/accountWithdrawalRrecord")
public class AccountWithdrawalRecordController extends BaseController
{
    @Autowired
    private IAccountWithdrawalRecordService accountWithdrawalRecordService;

    /**
     * 查询账户提现记录 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(AccountWithdrawalRecord accountWithdrawalRecord)
    {
        startPage();
        List<AccountWithdrawalRecord> list = accountWithdrawalRecordService.selectAccountWithdrawalRecordList(accountWithdrawalRecord);
        return getDataTable(list);
    }

    /**
     * 导出账户提现记录 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:export')")
    @Log(title = "账户提现记录 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AccountWithdrawalRecord accountWithdrawalRecord)
    {
        List<AccountWithdrawalRecord> list = accountWithdrawalRecordService.selectAccountWithdrawalRecordList(accountWithdrawalRecord);
        ExcelUtil<AccountWithdrawalRecord> util = new ExcelUtil<AccountWithdrawalRecord>(AccountWithdrawalRecord.class);
        util.exportExcel(response, list, "账户提现记录 数据");
    }

    /**
     * 获取账户提现记录 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(accountWithdrawalRecordService.selectAccountWithdrawalRecordById(id));
    }

    /**
     * 新增账户提现记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:add')")
    @Log(title = "账户提现记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AccountWithdrawalRecord accountWithdrawalRecord)
    {
        return toAjax(accountWithdrawalRecordService.insertAccountWithdrawalRecord(accountWithdrawalRecord));
    }

    /**
     * 修改账户提现记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:edit')")
    @Log(title = "账户提现记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AccountWithdrawalRecord accountWithdrawalRecord)
    {
        return toAjax(accountWithdrawalRecordService.updateAccountWithdrawalRecord(accountWithdrawalRecord));
    }

    /**
     * 删除账户提现记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:remove')")
    @Log(title = "账户提现记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(accountWithdrawalRecordService.deleteAccountWithdrawalRecordByIds(ids));
    }

    /**
     * 查询账户提现记录 列表
     */
    @ApiOperation(value = "获取提现记录")
    @GetMapping("/logs")
    public AjaxResult listLog()
    {
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        AccountWithdrawalRecord accountWithdrawalRecord = new AccountWithdrawalRecord();
        accountWithdrawalRecord.setUserId(String.valueOf(user.getUserId()));
        List<AccountWithdrawalRecord> list = accountWithdrawalRecordService.selectAccountWithdrawalRecordList(accountWithdrawalRecord);
        return AjaxResult.success(list);
    }
}
