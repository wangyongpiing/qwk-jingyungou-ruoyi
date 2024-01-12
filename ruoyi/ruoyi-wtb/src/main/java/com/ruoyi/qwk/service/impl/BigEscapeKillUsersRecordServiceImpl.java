package com.ruoyi.qwk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.BigEscapeKillUsersRecordMapper;
import com.ruoyi.qwk.domain.BigEscapeKillUsersRecord;
import com.ruoyi.qwk.service.IBigEscapeKillUsersRecordService;

/**
 * 大逃杀用户游戏记录Service业务层处理
 * 
 * @author cjf
 * @date 2023-12-22
 */
@Service
public class BigEscapeKillUsersRecordServiceImpl implements IBigEscapeKillUsersRecordService 
{
    @Autowired
    private BigEscapeKillUsersRecordMapper bigEscapeKillUsersRecordMapper;

    /**
     * 查询大逃杀用户游戏记录
     * 
     * @param id 大逃杀用户游戏记录主键
     * @return 大逃杀用户游戏记录
     */
    @Override
    public BigEscapeKillUsersRecord selectBigEscapeKillUsersRecordById(String id)
    {
        return bigEscapeKillUsersRecordMapper.selectBigEscapeKillUsersRecordById(id);
    }

    /**
     * 查询大逃杀用户游戏记录列表
     * 
     * @param bigEscapeKillUsersRecord 大逃杀用户游戏记录
     * @return 大逃杀用户游戏记录
     */
    @Override
    public List<BigEscapeKillUsersRecord> selectBigEscapeKillUsersRecordList(BigEscapeKillUsersRecord bigEscapeKillUsersRecord)
    {
        return bigEscapeKillUsersRecordMapper.selectBigEscapeKillUsersRecordList(bigEscapeKillUsersRecord);
    }

    /**
     * 新增大逃杀用户游戏记录
     * 
     * @param bigEscapeKillUsersRecord 大逃杀用户游戏记录
     * @return 结果
     */
    @Override
    public int insertBigEscapeKillUsersRecord(BigEscapeKillUsersRecord bigEscapeKillUsersRecord)
    {
        return bigEscapeKillUsersRecordMapper.insertBigEscapeKillUsersRecord(bigEscapeKillUsersRecord);
    }

    /**
     * 修改大逃杀用户游戏记录
     * 
     * @param bigEscapeKillUsersRecord 大逃杀用户游戏记录
     * @return 结果
     */
    @Override
    public int updateBigEscapeKillUsersRecord(BigEscapeKillUsersRecord bigEscapeKillUsersRecord)
    {
        return bigEscapeKillUsersRecordMapper.updateBigEscapeKillUsersRecord(bigEscapeKillUsersRecord);
    }

    /**
     * 批量删除大逃杀用户游戏记录
     * 
     * @param ids 需要删除的大逃杀用户游戏记录主键
     * @return 结果
     */
    @Override
    public int deleteBigEscapeKillUsersRecordByIds(String[] ids)
    {
        return bigEscapeKillUsersRecordMapper.deleteBigEscapeKillUsersRecordByIds(ids);
    }

    /**
     * 删除大逃杀用户游戏记录信息
     * 
     * @param id 大逃杀用户游戏记录主键
     * @return 结果
     */
    @Override
    public int deleteBigEscapeKillUsersRecordById(String id)
    {
        return bigEscapeKillUsersRecordMapper.deleteBigEscapeKillUsersRecordById(id);
    }
}
