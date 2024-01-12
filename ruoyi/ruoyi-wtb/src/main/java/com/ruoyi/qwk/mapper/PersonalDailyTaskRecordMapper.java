package com.ruoyi.qwk.mapper;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.PersonalDailyTaskRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 个人每日任务记录 Mapper接口
 *
 * @author cjf
 * @date 2023-12-22
 */
@DataSource(value = DataSourceType.SLAVE)
public interface PersonalDailyTaskRecordMapper
{
    /**
     * 查询个人每日任务记录
     *
     * @param id 个人每日任务记录 主键
     * @return 个人每日任务记录
     */
    public PersonalDailyTaskRecord selectPersonalDailyTaskRecordById(Long id);

    /**
     * 查询个人每日任务记录 列表
     *
     * @param personalDailyTaskRecord 个人每日任务记录
     * @return 个人每日任务记录 集合
     */
    public List<PersonalDailyTaskRecord> selectPersonalDailyTaskRecordList(PersonalDailyTaskRecord personalDailyTaskRecord);

    /**
     * 新增个人每日任务记录
     *
     * @param personalDailyTaskRecord 个人每日任务记录
     * @return 结果
     */
    public int insertPersonalDailyTaskRecord(PersonalDailyTaskRecord personalDailyTaskRecord);

    /**
     * 修改个人每日任务记录
     *
     * @param personalDailyTaskRecord 个人每日任务记录
     * @return 结果
     */
    public int updatePersonalDailyTaskRecord(PersonalDailyTaskRecord personalDailyTaskRecord);

    /**
     * 删除个人每日任务记录
     *
     * @param id 个人每日任务记录 主键
     * @return 结果
     */
    public int deletePersonalDailyTaskRecordById(Long id);

    /**
     * 批量删除个人每日任务记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePersonalDailyTaskRecordByIds(Long[] ids);

    /**
     * 获取首页任务和每日任务
     * @param time
     * @return
     */
    public List<PersonalDailyTaskRecord> selectTaskRecordList(@Param("time") Date time);
}
