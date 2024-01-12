package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.TasksType;

/**
 * 任务类型 Service接口
 *
 * @author cjf
 * @date 2023-12-22
 */
public interface ITasksTypeService
{
    /**
     * 查询任务类型
     *
     * @param id 任务类型 主键
     * @return 任务类型
     */
    public TasksType selectTasksTypeById(Long id);

    /**
     * 查询任务类型 列表
     *
     * @param tasksType 任务类型
     * @return 任务类型 集合
     */
    public List<TasksType> selectTasksTypeList(TasksType tasksType);

    /**
     * 新增任务类型
     *
     * @param tasksType 任务类型
     * @return 结果
     */
    public int insertTasksType(TasksType tasksType);

    /**
     * 修改任务类型
     *
     * @param tasksType 任务类型
     * @return 结果
     */
    public int updateTasksType(TasksType tasksType);

    /**
     * 批量删除任务类型
     *
     * @param ids 需要删除的任务类型 主键集合
     * @return 结果
     */
    public int deleteTasksTypeByIds(Long[] ids);

    /**
     * 删除任务类型 信息
     *
     * @param id 任务类型 主键
     * @return 结果
     */
    public int deleteTasksTypeById(Long id);
}
