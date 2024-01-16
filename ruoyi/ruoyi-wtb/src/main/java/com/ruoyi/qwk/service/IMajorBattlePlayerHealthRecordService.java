package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.MajorBattlePlayerHealthRecord;

/**
 * 大作战玩家血量记录Service接口
 * 
 * @author cjf
 * @date 2024-01-15
 */
public interface IMajorBattlePlayerHealthRecordService 
{
    /**
     * 查询大作战玩家血量记录
     * 
     * @param id 大作战玩家血量记录主键
     * @return 大作战玩家血量记录
     */
    public MajorBattlePlayerHealthRecord selectMajorBattlePlayerHealthRecordById(Long id);

    /**
     * 查询大作战玩家血量记录列表
     * 
     * @param majorBattlePlayerHealthRecord 大作战玩家血量记录
     * @return 大作战玩家血量记录集合
     */
    public List<MajorBattlePlayerHealthRecord> selectMajorBattlePlayerHealthRecordList(MajorBattlePlayerHealthRecord majorBattlePlayerHealthRecord);

    /**
     * 新增大作战玩家血量记录
     * 
     * @param majorBattlePlayerHealthRecord 大作战玩家血量记录
     * @return 结果
     */
    public int insertMajorBattlePlayerHealthRecord(MajorBattlePlayerHealthRecord majorBattlePlayerHealthRecord);

    /**
     * 修改大作战玩家血量记录
     * 
     * @param majorBattlePlayerHealthRecord 大作战玩家血量记录
     * @return 结果
     */
    public int updateMajorBattlePlayerHealthRecord(MajorBattlePlayerHealthRecord majorBattlePlayerHealthRecord);

    /**
     * 批量删除大作战玩家血量记录
     * 
     * @param ids 需要删除的大作战玩家血量记录主键集合
     * @return 结果
     */
    public int deleteMajorBattlePlayerHealthRecordByIds(Long[] ids);

    /**
     * 删除大作战玩家血量记录信息
     * 
     * @param id 大作战玩家血量记录主键
     * @return 结果
     */
    public int deleteMajorBattlePlayerHealthRecordById(Long id);
}
