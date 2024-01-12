package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.SerialCardOrderRecordsMapper;
import com.ruoyi.qwk.domain.SerialCardOrderRecords;
import com.ruoyi.qwk.service.ISerialCardOrderRecordsService;

/**
 * 序列卡订单记录 Service业务层处理
 * 
 * @author cjf
 * @date 2023-12-01
 */
@Service
public class SerialCardOrderRecordsServiceImpl implements ISerialCardOrderRecordsService 
{
    @Autowired
    private SerialCardOrderRecordsMapper serialCardOrderRecordsMapper;

    /**
     * 查询序列卡订单记录 
     * 
     * @param id 序列卡订单记录 主键
     * @return 序列卡订单记录 
     */
    @Override
    public SerialCardOrderRecords selectSerialCardOrderRecordsById(Long id)
    {
        return serialCardOrderRecordsMapper.selectSerialCardOrderRecordsById(id);
    }

    /**
     * 查询序列卡订单记录 列表
     * 
     * @param serialCardOrderRecords 序列卡订单记录 
     * @return 序列卡订单记录 
     */
    @Override
    public List<SerialCardOrderRecords> selectSerialCardOrderRecordsList(SerialCardOrderRecords serialCardOrderRecords)
    {
        return serialCardOrderRecordsMapper.selectSerialCardOrderRecordsList(serialCardOrderRecords);
    }

    /**
     * 新增序列卡订单记录 
     * 
     * @param serialCardOrderRecords 序列卡订单记录 
     * @return 结果
     */
    @Override
    public int insertSerialCardOrderRecords(SerialCardOrderRecords serialCardOrderRecords)
    {
        serialCardOrderRecords.setCreateTime(DateUtils.getNowDate());
        return serialCardOrderRecordsMapper.insertSerialCardOrderRecords(serialCardOrderRecords);
    }

    /**
     * 修改序列卡订单记录 
     * 
     * @param serialCardOrderRecords 序列卡订单记录 
     * @return 结果
     */
    @Override
    public int updateSerialCardOrderRecords(SerialCardOrderRecords serialCardOrderRecords)
    {
        serialCardOrderRecords.setUpdateTime(DateUtils.getNowDate());
        return serialCardOrderRecordsMapper.updateSerialCardOrderRecords(serialCardOrderRecords);
    }

    /**
     * 批量删除序列卡订单记录 
     * 
     * @param ids 需要删除的序列卡订单记录 主键
     * @return 结果
     */
    @Override
    public int deleteSerialCardOrderRecordsByIds(Long[] ids)
    {
        return serialCardOrderRecordsMapper.deleteSerialCardOrderRecordsByIds(ids);
    }

    /**
     * 删除序列卡订单记录 信息
     * 
     * @param id 序列卡订单记录 主键
     * @return 结果
     */
    @Override
    public int deleteSerialCardOrderRecordsById(Long id)
    {
        return serialCardOrderRecordsMapper.deleteSerialCardOrderRecordsById(id);
    }
}
