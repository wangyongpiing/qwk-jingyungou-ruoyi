package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.ExchangeRatio;

/**
 * 积分兑换比例设置Mapper接口
 * 
 * @author cjf
 * @date 2023-06-28
 */
@DataSource(value = DataSourceType.SLAVE)
public interface ExchangeRatioMapper 
{
    /**
     * 查询积分兑换比例设置
     * 
     * @param id 积分兑换比例设置主键
     * @return 积分兑换比例设置
     */
    public ExchangeRatio selectExchangeRatioById(Long id);

    /**
     * 查询积分兑换比例设置列表
     * 
     * @param exchangeRatio 积分兑换比例设置
     * @return 积分兑换比例设置集合
     */
    public List<ExchangeRatio> selectExchangeRatioList(ExchangeRatio exchangeRatio);

    /**
     * 新增积分兑换比例设置
     * 
     * @param exchangeRatio 积分兑换比例设置
     * @return 结果
     */
    public int insertExchangeRatio(ExchangeRatio exchangeRatio);

    /**
     * 修改积分兑换比例设置
     * 
     * @param exchangeRatio 积分兑换比例设置
     * @return 结果
     */
    public int updateExchangeRatio(ExchangeRatio exchangeRatio);

    /**
     * 删除积分兑换比例设置
     * 
     * @param id 积分兑换比例设置主键
     * @return 结果
     */
    public int deleteExchangeRatioById(Long id);

    /**
     * 批量删除积分兑换比例设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExchangeRatioByIds(Long[] ids);
}
