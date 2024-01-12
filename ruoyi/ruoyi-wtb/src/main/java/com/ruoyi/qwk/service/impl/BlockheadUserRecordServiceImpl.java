package com.ruoyi.qwk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.BlockheadUserRecordMapper;
import com.ruoyi.qwk.domain.BlockheadUserRecord;
import com.ruoyi.qwk.service.IBlockheadUserRecordService;

/**
 * 木头人游戏用户记录Service业务层处理
 * 
 * @author cjf
 * @date 2023-12-23
 */
@Service
public class BlockheadUserRecordServiceImpl implements IBlockheadUserRecordService 
{
    @Autowired
    private BlockheadUserRecordMapper blockheadUserRecordMapper;

    /**
     * 查询木头人游戏用户记录
     * 
     * @param id 木头人游戏用户记录主键
     * @return 木头人游戏用户记录
     */
    @Override
    public BlockheadUserRecord selectBlockheadUserRecordById(String id)
    {
        return blockheadUserRecordMapper.selectBlockheadUserRecordById(id);
    }

    /**
     * 查询木头人游戏用户记录列表
     * 
     * @param blockheadUserRecord 木头人游戏用户记录
     * @return 木头人游戏用户记录
     */
    @Override
    public List<BlockheadUserRecord> selectBlockheadUserRecordList(BlockheadUserRecord blockheadUserRecord)
    {
        return blockheadUserRecordMapper.selectBlockheadUserRecordList(blockheadUserRecord);
    }

    /**
     * 新增木头人游戏用户记录
     * 
     * @param blockheadUserRecord 木头人游戏用户记录
     * @return 结果
     */
    @Override
    public int insertBlockheadUserRecord(BlockheadUserRecord blockheadUserRecord)
    {
        return blockheadUserRecordMapper.insertBlockheadUserRecord(blockheadUserRecord);
    }

    /**
     * 修改木头人游戏用户记录
     * 
     * @param blockheadUserRecord 木头人游戏用户记录
     * @return 结果
     */
    @Override
    public int updateBlockheadUserRecord(BlockheadUserRecord blockheadUserRecord)
    {
        return blockheadUserRecordMapper.updateBlockheadUserRecord(blockheadUserRecord);
    }

    /**
     * 批量删除木头人游戏用户记录
     * 
     * @param ids 需要删除的木头人游戏用户记录主键
     * @return 结果
     */
    @Override
    public int deleteBlockheadUserRecordByIds(String[] ids)
    {
        return blockheadUserRecordMapper.deleteBlockheadUserRecordByIds(ids);
    }

    /**
     * 删除木头人游戏用户记录信息
     * 
     * @param id 木头人游戏用户记录主键
     * @return 结果
     */
    @Override
    public int deleteBlockheadUserRecordById(String id)
    {
        return blockheadUserRecordMapper.deleteBlockheadUserRecordById(id);
    }
}
