package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.ExchangeRulesMapper;
import com.ruoyi.qwk.domain.ExchangeRules;
import com.ruoyi.qwk.service.IExchangeRulesService;

/**
 * 积分兑换规则Service业务层处理
 * 
 * @author cjf
 * @date 2023-07-14
 */
@Service
public class ExchangeRulesServiceImpl implements IExchangeRulesService 
{
    @Autowired
    private ExchangeRulesMapper exchangeRulesMapper;

    /**
     * 查询积分兑换规则
     * 
     * @param id 积分兑换规则主键
     * @return 积分兑换规则
     */
    @Override
    public ExchangeRules selectExchangeRulesById(Long id)
    {
        return exchangeRulesMapper.selectExchangeRulesById(id);
    }

    /**
     * 查询积分兑换规则列表
     * 
     * @param exchangeRules 积分兑换规则
     * @return 积分兑换规则
     */
    @Override
    public List<ExchangeRules> selectExchangeRulesList(ExchangeRules exchangeRules)
    {
        return exchangeRulesMapper.selectExchangeRulesList(exchangeRules);
    }

    /**
     * 新增积分兑换规则
     * 
     * @param exchangeRules 积分兑换规则
     * @return 结果
     */
    @Override
    public int insertExchangeRules(ExchangeRules exchangeRules)
    {
        exchangeRules.setCreateTime(DateUtils.getNowDate());
        return exchangeRulesMapper.insertExchangeRules(exchangeRules);
    }

    /**
     * 修改积分兑换规则
     * 
     * @param exchangeRules 积分兑换规则
     * @return 结果
     */
    @Override
    public int updateExchangeRules(ExchangeRules exchangeRules)
    {
        exchangeRules.setUpdateTime(DateUtils.getNowDate());
        return exchangeRulesMapper.updateExchangeRules(exchangeRules);
    }

    /**
     * 批量删除积分兑换规则
     * 
     * @param ids 需要删除的积分兑换规则主键
     * @return 结果
     */
    @Override
    public int deleteExchangeRulesByIds(Long[] ids)
    {
        return exchangeRulesMapper.deleteExchangeRulesByIds(ids);
    }

    /**
     * 删除积分兑换规则信息
     * 
     * @param id 积分兑换规则主键
     * @return 结果
     */
    @Override
    public int deleteExchangeRulesById(Long id)
    {
        return exchangeRulesMapper.deleteExchangeRulesById(id);
    }
}
