package com.ruoyi.qwk.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.TasksTypeMapper;
import com.ruoyi.qwk.domain.TasksType;
import com.ruoyi.qwk.service.ITasksTypeService;

/**
 * 任务类型 Service业务层处理
 *
 * @author cjf
 * @date 2023-12-22
 */
@Service
public class TasksTypeServiceImpl implements ITasksTypeService
{
    @Autowired
    private TasksTypeMapper tasksTypeMapper;

    /**
     * 查询任务类型
     *
     * @param id 任务类型 主键
     * @return 任务类型
     */
    @Override
    public TasksType selectTasksTypeById(Long id)
    {
        return tasksTypeMapper.selectTasksTypeById(id);
    }

    /**
     * 查询任务类型 列表
     *
     * @param tasksType 任务类型
     * @return 任务类型
     */
    @Override
    public List<TasksType> selectTasksTypeList(TasksType tasksType)
    {
        return tasksTypeMapper.selectTasksTypeList(tasksType);
    }

    /**
     * 新增任务类型
     *
     * @param tasksType 任务类型
     * @return 结果
     */
    @Override
    public int insertTasksType(TasksType tasksType)
    {
        tasksType.setCreateTime(DateUtils.getNowDate());
        return tasksTypeMapper.insertTasksType(tasksType);
    }

    /**
     * 修改任务类型
     *
     * @param tasksType 任务类型
     * @return 结果
     */
    @Override
    public int updateTasksType(TasksType tasksType)
    {
        tasksType.setUpdateTime(DateUtils.getNowDate());
        return tasksTypeMapper.updateTasksType(tasksType);
    }

    /**
     * 批量删除任务类型
     *
     * @param ids 需要删除的任务类型 主键
     * @return 结果
     */
    @Override
    public int deleteTasksTypeByIds(Long[] ids)
    {
        return tasksTypeMapper.deleteTasksTypeByIds(ids);
    }

    /**
     * 删除任务类型 信息
     *
     * @param id 任务类型 主键
     * @return 结果
     */
    @Override
    public int deleteTasksTypeById(Long id)
    {
        return tasksTypeMapper.deleteTasksTypeById(id);
    }
}
