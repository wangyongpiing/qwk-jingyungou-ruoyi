package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.OperateLog;
import com.ruoyi.qwk.service.IOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户状态日志Controller
 *
 * @author cjf
 * @date 2023-08-02
 */
@RestController
@RequestMapping("/qwk/log")
public class OperateLogController extends BaseController
{
    @Autowired
    private IOperateLogService operateLogService;

    /**
     * 查询用户状态日志列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(OperateLog operateLog)
    {
        startPage();
        List<OperateLog> list = operateLogService.selectOperateLogList(operateLog);
        return getDataTable(list);
    }

    /**
     * 导出用户状态日志列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:log:export')")
    @Log(title = "用户状态日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OperateLog operateLog)
    {
        List<OperateLog> list = operateLogService.selectOperateLogList(operateLog);
        ExcelUtil<OperateLog> util = new ExcelUtil<OperateLog>(OperateLog.class);
        util.exportExcel(response, list, "用户状态日志数据");
    }

    /**
     * 获取用户状态日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(operateLogService.selectOperateLogById(id));
    }

    /**
     * 新增用户状态日志
     */
    @PreAuthorize("@ss.hasPermi('qwk:log:add')")
    @Log(title = "用户状态日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OperateLog operateLog)
    {
        return toAjax(operateLogService.insertOperateLog(operateLog));
    }

    /**
     * 修改用户状态日志
     */
    @PreAuthorize("@ss.hasPermi('qwk:log:edit')")
    @Log(title = "用户状态日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OperateLog operateLog)
    {
        return toAjax(operateLogService.updateOperateLog(operateLog));
    }

    /**
     * 删除用户状态日志
     */
    @PreAuthorize("@ss.hasPermi('qwk:log:remove')")
    @Log(title = "用户状态日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(operateLogService.deleteOperateLogByIds(ids));
    }
}
