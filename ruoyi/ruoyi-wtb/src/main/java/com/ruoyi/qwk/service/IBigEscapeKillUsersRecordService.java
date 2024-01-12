package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.BigEscapeKillUsersRecord;

/**
 * 大逃杀用户游戏记录Service接口
 * 
 * @author cjf
 * @date 2023-12-22
 */
public interface IBigEscapeKillUsersRecordService 
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
     * 批量删除大逃杀用户游戏记录
     * 
     * @param ids 需要删除的大逃杀用户游戏记录主键集合
     * @return 结果
     */
    public int deleteBigEscapeKillUsersRecordByIds(String[] ids);

    /**
     * 删除大逃杀用户游戏记录信息
     * 
     * @param id 大逃杀用户游戏记录主键
     * @return 结果
     */
    public int deleteBigEscapeKillUsersRecordById(String id);
}
