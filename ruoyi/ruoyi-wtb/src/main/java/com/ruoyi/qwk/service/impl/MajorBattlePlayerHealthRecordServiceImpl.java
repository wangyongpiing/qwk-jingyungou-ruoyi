package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.MajorBattlePlayerHealthRecordMapper;
import com.ruoyi.qwk.domain.MajorBattlePlayerHealthRecord;
import com.ruoyi.qwk.service.IMajorBattlePlayerHealthRecordService;

/**
 * 大作战玩家血量记录Service业务层处理
 * 
 * @author cjf
 * @date 2024-01-15
 */
@Service
public class MajorBattlePlayerHealthRecordServiceImpl implements IMajorBattlePlayerHealthRecordService 
{
    @Autowired
    private MajorBattlePlayerHealthRecordMapper majorBattlePlayerHealthRecordMapper;

    /**
     * 查询大作战玩家血量记录
     * 
     * @param id 大作战玩家血量记录主键
     * @return 大作战玩家血量记录
     */
    @Override
    public MajorBattlePlayerHealthRecord selectMajorBattlePlayerHealthRecordById(Long id)
    {
        return majorBattlePlayerHealthRecordMapper.selectMajorBattlePlayerHealthRecordById(id);
    }

    /**
     * 查询大作战玩家血量记录列表
     * 
     * @param majorBattlePlayerHealthRecord 大作战玩家血量记录
     * @return 大作战玩家血量记录
     */
    @Override
    public List<MajorBattlePlayerHealthRecord> selectMajorBattlePlayerHealthRecordList(MajorBattlePlayerHealthRecord majorBattlePlayerHealthRecord)
    {
        return majorBattlePlayerHealthRecordMapper.selectMajorBattlePlayerHealthRecordList(majorBattlePlayerHealthRecord);
    }

    /**
     * 新增大作战玩家血量记录
     * 
     * @param majorBattlePlayerHealthRecord 大作战玩家血量记录
     * @return 结果
     */
    @Override
    public int insertMajorBattlePlayerHealthRecord(MajorBattlePlayerHealthRecord majorBattlePlayerHealthRecord)
    {
        majorBattlePlayerHealthRecord.setCreateTime(DateUtils.getNowDate());
        return majorBattlePlayerHealthRecordMapper.insertMajorBattlePlayerHealthRecord(majorBattlePlayerHealthRecord);
    }

    /**
     * 修改大作战玩家血量记录
     * 
     * @param majorBattlePlayerHealthRecord 大作战玩家血量记录
     * @return 结果
     */
    @Override
    public int updateMajorBattlePlayerHealthRecord(MajorBattlePlayerHealthRecord majorBattlePlayerHealthRecord)
    {
        majorBattlePlayerHealthRecord.setUpdateTime(DateUtils.getNowDate());
        return majorBattlePlayerHealthRecordMapper.updateMajorBattlePlayerHealthRecord(majorBattlePlayerHealthRecord);
    }

    /**
     * 批量删除大作战玩家血量记录
     * 
     * @param ids 需要删除的大作战玩家血量记录主键
     * @return 结果
     */
    @Override
    public int deleteMajorBattlePlayerHealthRecordByIds(Long[] ids)
    {
        return majorBattlePlayerHealthRecordMapper.deleteMajorBattlePlayerHealthRecordByIds(ids);
    }

    /**
     * 删除大作战玩家血量记录信息
     * 
     * @param id 大作战玩家血量记录主键
     * @return 结果
     */
    @Override
    public int deleteMajorBattlePlayerHealthRecordById(Long id)
    {
        return majorBattlePlayerHealthRecordMapper.deleteMajorBattlePlayerHealthRecordById(id);
    }
}
