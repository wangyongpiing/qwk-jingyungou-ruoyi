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
import com.ruoyi.qwk.domain.LoginRecord;
import com.ruoyi.qwk.service.ILoginRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 登录日志 Controller
 *
 * @author cjf
 * @date 2024-01-04
 */
@RestController
@RequestMapping("/qwk/loginRecord")
public class LoginRecordController extends BaseController
{
    @Autowired
    private ILoginRecordService loginRecordService;

    /**
     * 查询登录日志 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(LoginRecord loginRecord)
    {
        startPage();
        List<LoginRecord> list = loginRecordService.selectLoginRecordList(loginRecord);
        return getDataTable(list);
    }

    /**
     * 导出登录日志 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:export')")
    @Log(title = "登录日志 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LoginRecord loginRecord)
    {
        List<LoginRecord> list = loginRecordService.selectLoginRecordList(loginRecord);
        ExcelUtil<LoginRecord> util = new ExcelUtil<LoginRecord>(LoginRecord.class);
        util.exportExcel(response, list, "登录日志 数据");
    }

    /**
     * 获取登录日志 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(loginRecordService.selectLoginRecordById(id));
    }

    /**
     * 新增登录日志
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:add')")
    @Log(title = "登录日志 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LoginRecord loginRecord)
    {
        return toAjax(loginRecordService.insertLoginRecord(loginRecord));
    }

    /**
     * 修改登录日志
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:edit')")
    @Log(title = "登录日志 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LoginRecord loginRecord)
    {
        return toAjax(loginRecordService.updateLoginRecord(loginRecord));
    }

    /**
     * 删除登录日志
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:remove')")
    @Log(title = "登录日志 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(loginRecordService.deleteLoginRecordByIds(ids));
    }
}
