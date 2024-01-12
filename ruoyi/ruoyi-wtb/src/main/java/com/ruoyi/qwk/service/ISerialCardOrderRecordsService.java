package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.SerialCardOrderRecords;

/**
 * 序列卡订单记录 Service接口
 * 
 * @author cjf
 * @date 2023-12-01
 */
public interface ISerialCardOrderRecordsService 
{
    /**
     * 查询序列卡订单记录 
     * 
     * @param id 序列卡订单记录 主键
     * @return 序列卡订单记录 
     */
    public SerialCardOrderRecords selectSerialCardOrderRecordsById(Long id);

    /**
     * 查询序列卡订单记录 列表
     * 
     * @param serialCardOrderRecords 序列卡订单记录 
     * @return 序列卡订单记录 集合
     */
    public List<SerialCardOrderRecords> selectSerialCardOrderRecordsList(SerialCardOrderRecords serialCardOrderRecords);

    /**
     * 新增序列卡订单记录 
     * 
     * @param serialCardOrderRecords 序列卡订单记录 
     * @return 结果
     */
    public int insertSerialCardOrderRecords(SerialCardOrderRecords serialCardOrderRecords);

    /**
     * 修改序列卡订单记录 
     * 
     * @param serialCardOrderRecords 序列卡订单记录 
     * @return 结果
     */
    public int updateSerialCardOrderRecords(SerialCardOrderRecords serialCardOrderRecords);

    /**
     * 批量删除序列卡订单记录 
     * 
     * @param ids 需要删除的序列卡订单记录 主键集合
     * @return 结果
     */
    public int deleteSerialCardOrderRecordsByIds(Long[] ids);

    /**
     * 删除序列卡订单记录 信息
     * 
     * @param id 序列卡订单记录 主键
     * @return 结果
     */
    public int deleteSerialCardOrderRecordsById(Long id);
}
