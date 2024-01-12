package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.PersonalTasks;

/**
 * 个人任务 Mapper接口
 *
 * @author cjf
 * @date 2023-12-22
 */
@DataSource(value = DataSourceType.SLAVE)
public interface PersonalTasksMapper
{
    /**
     * 查询个人任务
     *
     * @param id 个人任务 主键
     * @return 个人任务
     */
    public PersonalTasks selectPersonalTasksById(Long id);

    /**
     * 查询个人任务 列表
     *
     * @param personalTasks 个人任务
     * @return 个人任务 集合
     */
    public List<PersonalTasks> selectPersonalTasksList(PersonalTasks personalTasks);

    /**
     * 新增个人任务
     *
     * @param personalTasks 个人任务
     * @return 结果
     */
    public int insertPersonalTasks(PersonalTasks personalTasks);

    /**
     * 修改个人任务
     *
     * @param personalTasks 个人任务
     * @return 结果
     */
    public int updatePersonalTasks(PersonalTasks personalTasks);

    /**
     * 删除个人任务
     *
     * @param id 个人任务 主键
     * @return 结果
     */
    public int deletePersonalTasksById(Long id);

    /**
     * 批量删除个人任务
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePersonalTasksByIds(Long[] ids);

    /**
     * 查询个人任务 列表
     *
     * @param personalTasks 个人任务
     * @return 个人任务 集合
     */
    public List<PersonalTasks> selectUserTasksList(PersonalTasks personalTasks);
}
