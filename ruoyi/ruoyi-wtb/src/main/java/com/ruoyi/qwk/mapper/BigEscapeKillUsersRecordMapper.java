package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.BigEscapeKillUsersRecord;

/**
 * 大逃杀用户游戏记录Mapper接口
 *
 * @author cjf
 * @date 2023-12-22
 */
@DataSource(value = DataSourceType.SLAVE)
public interface BigEscapeKillUsersRecordMapper
{
    /**
     * 查询大逃杀用户游戏记录
     *
     * @param id 大逃杀用户游戏记录主键
     * @return 大逃杀用户游戏记录
     */
    public BigEscapeKillUsersRecord selectBigEscapeKillUsersRecordById(String id);

    /**
     * 查询大逃杀用户游戏记录列表
     *
     * @param bigEscapeKillUsersRecord 大逃杀用户游戏记录
     * @return 大逃杀用户游戏记录集合
     */
    public List<BigEscapeKillUsersRecord> selectBigEscapeKillUsersRecordList(BigEscapeKillUsersRecord bigEscapeKillUsersRecord);

    /**
     * 新增大逃杀用户游戏记录
     *
     * @param bigEscapeKillUsersRecord 大逃杀用户游戏记录
     * @return 结果
     */
    public int insertBigEscapeKillUsersRecord(BigEscapeKillUsersRecord bigEscapeKillUsersRecord);

    /**
     * 修改大逃杀用户游戏记录
     *
     * @param bigEscapeKillUsersRecord 大逃杀用户游戏记录
     * @return 结果
     */
    public int updateBigEscapeKillUsersRecord(BigEscapeKillUsersRecord bigEscapeKillUsersRecord);

    /**
     * 删除大逃杀用户游戏记录
     *
     * @param id 大逃杀用户游戏记录主键
     * @return 结果
     */
    public int deleteBigEscapeKillUsersRecordById(String id);

    /**
     * 批量删除大逃杀用户游戏记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBigEscapeKillUsersRecordByIds(String[] ids);
}
