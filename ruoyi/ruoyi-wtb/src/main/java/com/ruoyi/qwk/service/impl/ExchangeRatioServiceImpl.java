package com.ruoyi.qwk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.ExchangeRatioMapper;
import com.ruoyi.qwk.domain.ExchangeRatio;
import com.ruoyi.qwk.service.IExchangeRatioService;

/**
 * 积分兑换比例设置Service业务层处理
 * 
 * @author cjf
 * @date 2023-06-28
 */
@Service
public class ExchangeRatioServiceImpl implements IExchangeRatioService 
{
    @Autowired
    private ExchangeRatioMapper exchangeRatioMapper;

    /**
     * 查询积分兑换比例设置
     * 
     * @param id 积分兑换比例设置主键
     * @return 积分兑换比例设置
     */
    @Override
    public ExchangeRatio selectExchangeRatioById(Long id)
    {
        return exchangeRatioMapper.selectExchangeRatioById(id);
    }

    /**
     * 查询积分兑换比例设置列表
     * 
     * @param exchangeRatio 积分兑换比例设置
     * @return 积分兑换比例设置
     */
    @Override
    public List<ExchangeRatio> selectExchangeRatioList(ExchangeRatio exchangeRatio)
    {
        return exchangeRatioMapper.selectExchangeRatioList(exchangeRatio);
    }

    /**
     * 新增积分兑换比例设置
     * 
     * @param exchangeRatio 积分兑换比例设置
     * @return 结果
     */
    @Override
    public int insertExchangeRatio(ExchangeRatio exchangeRatio)
    {
        return exchangeRatioMapper.insertExchangeRatio(exchangeRatio);
    }

    /**
     * 修改积分兑换比例设置
     * 
     * @param exchangeRatio 积分兑换比例设置
     * @return 结果
     */
    @Override
    public int updateExchangeRatio(ExchangeRatio exchangeRatio)
    {
        return exchangeRatioMapper.updateExchangeRatio(exchangeRatio);
    }

    /**
     * 批量删除积分兑换比例设置
     * 
     * @param ids 需要删除的积分兑换比例设置主键
     * @return 结果
     */
    @Override
    public int deleteExchangeRatioByIds(Long[] ids)
    {
        return exchangeRatioMapper.deleteExchangeRatioByIds(ids);
    }

    /**
     * 删除积分兑换比例设置信息
     * 
     * @param id 积分兑换比例设置主键
     * @return 结果
     */
    @Override
    public int deleteExchangeRatioById(Long id)
    {
        return exchangeRatioMapper.deleteExchangeRatioById(id);
    }
}
