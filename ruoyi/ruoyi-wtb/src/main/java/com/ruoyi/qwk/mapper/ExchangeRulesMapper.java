package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.ExchangeRules;

/**
 * 积分兑换规则Mapper接口
 * 
 * @author cjf
 * @date 2023-07-14
 */
@DataSource(value = DataSourceType.SLAVE)
public interface ExchangeRulesMapper 
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
     * 删除积分兑换规则
     * 
     * @param id 积分兑换规则主键
     * @return 结果
     */
    public int deleteExchangeRulesById(Long id);

    /**
     * 批量删除积分兑换规则
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExchangeRulesByIds(Long[] ids);
}
