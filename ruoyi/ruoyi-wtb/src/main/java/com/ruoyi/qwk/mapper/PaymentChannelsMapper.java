package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.PaymentChannels;

/**
 * 支付渠道Mapper接口
 * 
 * @author cjf
 * @date 2023-07-24
 */
@DataSource(value = DataSourceType.SLAVE)
public interface PaymentChannelsMapper 
{
    /**
     * 查询支付渠道
     * 
     * @param id 支付渠道主键
     * @return 支付渠道
     */
    public PaymentChannels selectPaymentChannelsById(Long id);

    /**
     * 查询支付渠道列表
     * 
     * @param paymentChannels 支付渠道
     * @return 支付渠道集合
     */
    public List<PaymentChannels> selectPaymentChannelsList(PaymentChannels paymentChannels);

    /**
     * 新增支付渠道
     * 
     * @param paymentChannels 支付渠道
     * @return 结果
     */
    public int insertPaymentChannels(PaymentChannels paymentChannels);

    /**
     * 修改支付渠道
     * 
     * @param paymentChannels 支付渠道
     * @return 结果
     */
    public int updatePaymentChannels(PaymentChannels paymentChannels);

    /**
     * 删除支付渠道
     * 
     * @param id 支付渠道主键
     * @return 结果
     */
    public int deletePaymentChannelsById(Long id);

    /**
     * 批量删除支付渠道
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePaymentChannelsByIds(Long[] ids);

    /**
     * 查询所有启用的支付渠道
     *
     * @return 支付渠道集合
     */
    public List<PaymentChannels> selectByStatusList();
}
