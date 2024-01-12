package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
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
import com.ruoyi.qwk.domain.TasksType;
import com.ruoyi.qwk.service.ITasksTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务类型 Controller
 *
 * @author cjf
 * @date 2023-12-22
 */
@RestController
@RequestMapping("/qwk/typeTask")
public class TasksTypeController extends BaseController
{
    @Autowired
    private ITasksTypeService tasksTypeService;

    /**
     * 查询任务类型 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(TasksType tasksType)
    {
        startPage();
        List<TasksType> list = tasksTypeService.selectTasksTypeList(tasksType);
        return getDataTable(list);
    }

    /**
     * 导出任务类型 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:export')")
    @Log(title = "任务类型 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TasksType tasksType)
    {
        List<TasksType> list = tasksTypeService.selectTasksTypeList(tasksType);
        ExcelUtil<TasksType> util = new ExcelUtil<TasksType>(TasksType.class);
        util.exportExcel(response, list, "任务类型 数据");
    }

    /**
     * 获取任务类型 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tasksTypeService.selectTasksTypeById(id));
    }

    /**
     * 新增任务类型
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:add')")
    @Log(title = "任务类型 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TasksType tasksType)
    {
        return toAjax(tasksTypeService.insertTasksType(tasksType));
    }

    /**
     * 修改任务类型
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:edit')")
    @Log(title = "任务类型 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TasksType tasksType)
    {
        return toAjax(tasksTypeService.updateTasksType(tasksType));
    }

    /**
     * 删除任务类型
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:remove')")
    @Log(title = "任务类型 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tasksTypeService.deleteTasksTypeByIds(ids));
    }

    /**
     * 获取任务类型列表
     * @return
     */
    @ApiOperation(value = "获取任务类型列表")
    @GetMapping("/typeList")
    public AjaxResult selectList(){
         TasksType tasksType = new TasksType();
         tasksType.setIsShow("1");
         List<TasksType> list = tasksTypeService.selectTasksTypeList(tasksType);
         return AjaxResult.success(list);
    }
}
