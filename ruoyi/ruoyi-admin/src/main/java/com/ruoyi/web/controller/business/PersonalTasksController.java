package com.ruoyi.web.controller.business;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.domain.PersonalTasks;
import com.ruoyi.qwk.service.IPersonalTasksService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 个人任务 Controller
 *
 * @author cjf
 * @date 2023-12-22
 */
@RestController
@RequestMapping("/qwk/tasks")
public class PersonalTasksController extends BaseController
{
    @Autowired
    private IPersonalTasksService personalTasksService;

    /**
     * 查询个人任务 列表
     */
    @ApiOperation(value = "查询个人任务列表")
    @GetMapping("/list")
    public AjaxResult list(@RequestParam("type") String type)
    {
        PersonalTasks personalTasks = new PersonalTasks();
        personalTasks.setTypeCode(type);
        return personalTasksService.selectPersonalTasksList(personalTasks);
    }

//    /**
//     * 导出个人任务 列表
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:tasks:export')")
//    @Log(title = "个人任务 ", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, PersonalTasks personalTasks)
//    {
//        List<PersonalTasks> list = personalTasksService.selectPersonalTasksList(personalTasks);
//        ExcelUtil<PersonalTasks> util = new ExcelUtil<PersonalTasks>(PersonalTasks.class);
//        util.exportExcel(response, list, "个人任务 数据");
//    }

    /**
     * 获取个人任务 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:tasks:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(personalTasksService.selectPersonalTasksById(id));
    }

    /**
     * 新增个人任务
     */
    @PreAuthorize("@ss.hasPermi('qwk:tasks:add')")
    @Log(title = "个人任务 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonalTasks personalTasks)
    {
        return toAjax(personalTasksService.insertPersonalTasks(personalTasks));
    }

    /**
     * 修改个人任务
     */
    @PreAuthorize("@ss.hasPermi('qwk:tasks:edit')")
    @Log(title = "个人任务 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonalTasks personalTasks)
    {
        return toAjax(personalTasksService.updatePersonalTasks(personalTasks));
    }

    /**
     * 删除个人任务
     */
    @PreAuthorize("@ss.hasPermi('qwk:tasks:remove')")
    @Log(title = "个人任务 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(personalTasksService.deletePersonalTasksByIds(ids));
    }

}
