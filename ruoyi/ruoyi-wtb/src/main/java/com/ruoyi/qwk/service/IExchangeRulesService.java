package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.ExchangeRules;

/**
 * 积分兑换规则Service接口
 * 
 * @author cjf
 * @date 2023-07-14
 */
public interface IExchangeRulesService 
{
    /**
     * 查询积分兑换规则
     * 
     * @param id 积分兑换规则主键
     * @return 积分兑换规则
     */
    public ExchangeRules selectExchangeRulesById(Long id);

    /**
     * 查询积分兑换规则列表
     * 
     * @param exchangeRules 积分兑换规则
     * @return 积分兑换规则集合
     */
    public List<ExchangeRules> selectExchangeRulesList(ExchangeRules exchangeRules);

    /**
     * 新增积分兑换规则
     * 
     * @param exchangeRules 积分兑换规则
     * @return 结果
     */
    public int insertExchangeRules(ExchangeRules exchangeRules);

    /**
     * 修改积分兑换规则
     * 
     * @param exchangeRules 积分兑换规则
     * @return 结果
     */
    public int updateExchangeRules(ExchangeRules exchangeRules);

    /**
     * 批量删除积分兑换规则
     * 
     * @param ids 需要删除的积分兑换规则主键集合
     * @return 结果
     */
    public int deleteExchangeRulesByIds(Long[] ids);

    /**
     * 删除积分兑换规则信息
     * 
     * @param id 积分兑换规则主键
     * @return 结果
     */
    public int deleteExchangeRulesById(Long id);
}
