package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.SerialCardExchangeRecords;

/**
 * 序列卡兑换记录 Mapper接口
 *
 * @author cjf
 * @date 2023-12-01
 */
@DataSource(value = DataSourceType.SLAVE)
public interface SerialCardExchangeRecordsMapper
{
    /**
     * 查询序列卡兑换记录
     *
     * @param id 序列卡兑换记录 主键
     * @return 序列卡兑换记录
     */
    public SerialCardExchangeRecords selectSerialCardExchangeRecordsById(Long id);

    /**
     * 查询序列卡兑换记录 列表
     *
     * @param serialCardExchangeRecords 序列卡兑换记录
     * @return 序列卡兑换记录 集合
     */
    public List<SerialCardExchangeRecords> selectSerialCardExchangeRecordsList(SerialCardExchangeRecords serialCardExchangeRecords);

    /**
     * 新增序列卡兑换记录
     *
     * @param serialCardExchangeRecords 序列卡兑换记录
     * @return 结果
     */
    public int insertSerialCardExchangeRecords(SerialCardExchangeRecords serialCardExchangeRecords);

    /**
     * 修改序列卡兑换记录
     *
     * @param serialCardExchangeRecords 序列卡兑换记录
     * @return 结果
     */
    public int updateSerialCardExchangeRecords(SerialCardExchangeRecords serialCardExchangeRecords);

    /**
     * 删除序列卡兑换记录
     *
     * @param id 序列卡兑换记录 主键
     * @return 结果
     */
    public int deleteSerialCardExchangeRecordsById(Long id);

    /**
     * 批量删除序列卡兑换记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSerialCardExchangeRecordsByIds(Long[] ids);
}
