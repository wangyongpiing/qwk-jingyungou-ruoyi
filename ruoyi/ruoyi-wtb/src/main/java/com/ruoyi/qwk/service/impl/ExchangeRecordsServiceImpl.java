package com.ruoyi.qwk.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.qwk.domain.MoneyRatio;
import com.ruoyi.qwk.domain.Warehouse;
import com.ruoyi.qwk.mapper.MoneyRatioMapper;
import com.ruoyi.qwk.mapper.WarehouseDetailsMapper;
import com.ruoyi.qwk.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.ExchangeRecordsMapper;
import com.ruoyi.qwk.domain.ExchangeRecords;
import com.ruoyi.qwk.service.IExchangeRecordsService;

import javax.xml.crypto.Data;

/**
 * 兑换记录Service业务层处理
 * 
 * @author cjf
 * @date 2023-07-25
 */
@Service
public class ExchangeRecordsServiceImpl implements IExchangeRecordsService 
{
    @Autowired
    private ExchangeRecordsMapper exchangeRecordsMapper;

    @Autowired
    private WarehouseDetailsMapper warehouseDetailsMapper;//仓库详情

    @Autowired
    private WarehouseMapper warehouseMapper;//仓库

    @Autowired
    private MoneyRatioMapper moneyRatioMapper;//充值档位及比例

    /**
     * 查询兑换记录
     * 
     * @param id 兑换记录主键
     * @return 兑换记录
     */
    @Override
    public ExchangeRecords selectExchangeRecordsById(Long id)
    {
        return exchangeRecordsMapper.selectExchangeRecordsById(id);
    }

    /**
     * 查询兑换记录列表
     * 
     * @param exchangeRecords 兑换记录
     * @return 兑换记录
     */
    @Override
    public List<ExchangeRecords> selectExchangeRecordsList(ExchangeRecords exchangeRecords)
    {
        return exchangeRecordsMapper.selectExchangeRecordsList(exchangeRecords);
    }

    /**
     * 新增兑换记录
     * 
     * @param exchangeRecords 兑换记录
     * @return 结果
     */
    @Override
    public int insertExchangeRecords(ExchangeRecords exchangeRecords)
    {
        exchangeRecords.setCreateTime(DateUtils.getNowDate());
        return exchangeRecordsMapper.insertExchangeRecords(exchangeRecords);
    }

    /**
     * 修改兑换记录
     * 
     * @param exchangeRecords 兑换记录
     * @return 结果
     */
    @Override
    public int updateExchangeRecords(ExchangeRecords exchangeRecords)
    {
        exchangeRecords.setUpdateTime(DateUtils.getNowDate());
        return exchangeRecordsMapper.updateExchangeRecords(exchangeRecords);
    }

    /**
     * 批量删除兑换记录
     * 
     * @param ids 需要删除的兑换记录主键
     * @return 结果
     */
    @Override
    public int deleteExchangeRecordsByIds(Long[] ids)
    {
        return exchangeRecordsMapper.deleteExchangeRecordsByIds(ids);
    }

    /**
     * 删除兑换记录信息
     * 
     * @param id 兑换记录主键
     * @return 结果
     */
    @Override
    public int deleteExchangeRecordsById(Long id)
    {
        return exchangeRecordsMapper.deleteExchangeRecordsById(id);
    }

    /**
     * 积分兑换钻石
     *
     * @param num 兑换数量
     * @return 结果
     */
    @Override
    public int exchange(Long moneyId,String num){
//        MoneyRatio moneyRatio = moneyRatioMapper.selectMoneyRatioById(moneyId);
//        SysUser user = SecurityUtils.getLoginUser().getUser();
//        Date date = new Date();
//        Warehouse warehouse = warehouseMapper.selectWarehouseByUserId(user.getUserId());

        return 0;
    }
}
