package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.PropRecordMapper;
import com.ruoyi.qwk.domain.PropRecord;
import com.ruoyi.qwk.service.IPropRecordService;

/**
 * 道具产出记录Service业务层处理
 * 
 * @author cjf
 * @date 2023-07-31
 */
@Service
public class PropRecordServiceImpl implements IPropRecordService 
{
    @Autowired
    private PropRecordMapper propRecordMapper;

    /**
     * 查询道具产出记录
     * 
     * @param id 道具产出记录主键
     * @return 道具产出记录
     */
    @Override
    public PropRecord selectPropRecordById(Long id)
    {
        return propRecordMapper.selectPropRecordById(id);
    }

    /**
     * 查询道具产出记录列表
     * 
     * @param propRecord 道具产出记录
     * @return 道具产出记录
     */
    @Override
    public List<PropRecord> selectPropRecordList(PropRecord propRecord)
    {
        return propRecordMapper.selectPropRecordList(propRecord);
    }

    /**
     * 新增道具产出记录
     * 
     * @param propRecord 道具产出记录
     * @return 结果
     */
    @Override
    public int insertPropRecord(PropRecord propRecord)
    {
        propRecord.setCreateTime(DateUtils.getNowDate());
        return propRecordMapper.insertPropRecord(propRecord);
    }

    /**
     * 修改道具产出记录
     * 
     * @param propRecord 道具产出记录
     * @return 结果
     */
    @Override
    public int updatePropRecord(PropRecord propRecord)
    {
        propRecord.setUpdateTime(DateUtils.getNowDate());
        return propRecordMapper.updatePropRecord(propRecord);
    }

    /**
     * 批量删除道具产出记录
     * 
     * @param ids 需要删除的道具产出记录主键
     * @return 结果
     */
    @Override
    public int deletePropRecordByIds(Long[] ids)
    {
        return propRecordMapper.deletePropRecordByIds(ids);
    }

    /**
     * 删除道具产出记录信息
     * 
     * @param id 道具产出记录主键
     * @return 结果
     */
    @Override
    public int deletePropRecordById(Long id)
    {
        return propRecordMapper.deletePropRecordById(id);
    }
}
