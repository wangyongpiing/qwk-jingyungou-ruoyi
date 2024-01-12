package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.ExchangeRecords;

/**
 * 兑换记录Mapper接口
 * 
 * @author cjf
 * @date 2023-07-25
 */
@DataSource(value = DataSourceType.SLAVE)
public interface ExchangeRecordsMapper 
{
    /**
     * 查询兑换记录
     * 
     * @param id 兑换记录主键
     * @return 兑换记录
     */
    public ExchangeRecords selectExchangeRecordsById(Long id);

    /**
     * 查询兑换记录列表
     * 
     * @param exchangeRecords 兑换记录
     * @return 兑换记录集合
     */
    public List<ExchangeRecords> selectExchangeRecordsList(ExchangeRecords exchangeRecords);

    /**
     * 新增兑换记录
     * 
     * @param exchangeRecords 兑换记录
     * @return 结果
     */
    public int insertExchangeRecords(ExchangeRecords exchangeRecords);

    /**
     * 修改兑换记录
     * 
     * @param exchangeRecords 兑换记录
     * @return 结果
     */
    public int updateExchangeRecords(ExchangeRecords exchangeRecords);

    /**
     * 删除兑换记录
     * 
     * @param id 兑换记录主键
     * @return 结果
     */
    public int deleteExchangeRecordsById(Long id);

    /**
     * 批量删除兑换记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExchangeRecordsByIds(Long[] ids);
}
