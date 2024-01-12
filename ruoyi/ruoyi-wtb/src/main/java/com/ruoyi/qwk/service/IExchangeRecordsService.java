package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.ExchangeRecords;

/**
 * 兑换记录Service接口
 * 
 * @author cjf
 * @date 2023-07-25
 */
public interface IExchangeRecordsService 
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
     * 批量删除兑换记录
     * 
     * @param ids 需要删除的兑换记录主键集合
     * @return 结果
     */
    public int deleteExchangeRecordsByIds(Long[] ids);

    /**
     * 删除兑换记录信息
     * 
     * @param id 兑换记录主键
     * @return 结果
     */
    public int deleteExchangeRecordsById(Long id);

    /**
     * 删除积分兑换钻石
     *
     *
     * @return 结果
     */
    public int exchange(Long moneyId,String num);
}
