package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.BlockheadUserRecord;

/**
 * 木头人游戏用户记录Service接口
 * 
 * @author cjf
 * @date 2023-12-23
 */
public interface IBlockheadUserRecordService 
{
    /**
     * 查询木头人游戏用户记录
     * 
     * @param id 木头人游戏用户记录主键
     * @return 木头人游戏用户记录
     */
    public BlockheadUserRecord selectBlockheadUserRecordById(String id);

    /**
     * 查询木头人游戏用户记录列表
     * 
     * @param blockheadUserRecord 木头人游戏用户记录
     * @return 木头人游戏用户记录集合
     */
    public List<BlockheadUserRecord> selectBlockheadUserRecordList(BlockheadUserRecord blockheadUserRecord);

    /**
     * 新增木头人游戏用户记录
     * 
     * @param blockheadUserRecord 木头人游戏用户记录
     * @return 结果
     */
    public int insertBlockheadUserRecord(BlockheadUserRecord blockheadUserRecord);

    /**
     * 修改木头人游戏用户记录
     * 
     * @param blockheadUserRecord 木头人游戏用户记录
     * @return 结果
     */
    public int updateBlockheadUserRecord(BlockheadUserRecord blockheadUserRecord);

    /**
     * 批量删除木头人游戏用户记录
     * 
     * @param ids 需要删除的木头人游戏用户记录主键集合
     * @return 结果
     */
    public int deleteBlockheadUserRecordByIds(String[] ids);

    /**
     * 删除木头人游戏用户记录信息
     * 
     * @param id 木头人游戏用户记录主键
     * @return 结果
     */
    public int deleteBlockheadUserRecordById(String id);
}
