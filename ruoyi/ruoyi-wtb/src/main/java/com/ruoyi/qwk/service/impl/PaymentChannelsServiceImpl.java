package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.PaymentChannelsMapper;
import com.ruoyi.qwk.domain.PaymentChannels;
import com.ruoyi.qwk.service.IPaymentChannelsService;

/**
 * 支付渠道Service业务层处理
 * 
 * @author cjf
 * @date 2023-07-24
 */
@Service
public class PaymentChannelsServiceImpl implements IPaymentChannelsService 
{
    @Autowired
    private PaymentChannelsMapper paymentChannelsMapper;

    /**
     * 查询支付渠道
     * 
     * @param id 支付渠道主键
     * @return 支付渠道
     */
    @Override
    public PaymentChannels selectPaymentChannelsById(Long id)
    {
        return paymentChannelsMapper.selectPaymentChannelsById(id);
    }

    /**
     * 查询支付渠道列表
     * 
     * @param paymentChannels 支付渠道
     * @return 支付渠道
     */
    @Override
    public List<PaymentChannels> selectPaymentChannelsList(PaymentChannels paymentChannels)
    {
        return paymentChannelsMapper.selectPaymentChannelsList(paymentChannels);
    }

    /**
     * 新增支付渠道
     * 
     * @param paymentChannels 支付渠道
     * @return 结果
     */
    @Override
    public int insertPaymentChannels(PaymentChannels paymentChannels)
    {
        paymentChannels.setCreateTime(DateUtils.getNowDate());
        return paymentChannelsMapper.insertPaymentChannels(paymentChannels);
    }

    /**
     * 修改支付渠道
     * 
     * @param paymentChannels 支付渠道
     * @return 结果
     */
    @Override
    public int updatePaymentChannels(PaymentChannels paymentChannels)
    {
        paymentChannels.setUpdateTime(DateUtils.getNowDate());
        return paymentChannelsMapper.updatePaymentChannels(paymentChannels);
    }

    /**
     * 批量删除支付渠道
     * 
     * @param ids 需要删除的支付渠道主键
     * @return 结果
     */
    @Override
    public int deletePaymentChannelsByIds(Long[] ids)
    {
        return paymentChannelsMapper.deletePaymentChannelsByIds(ids);
    }

    /**
     * 删除支付渠道信息
     * 
     * @param id 支付渠道主键
     * @return 结果
     */
    @Override
    public int deletePaymentChannelsById(Long id)
    {
        return paymentChannelsMapper.deletePaymentChannelsById(id);
    }

    /**
     * 查询所有启用的支付渠道
     *
     * @return 支付渠道集合
     */
    @Override
    public List<PaymentChannels> selectByStatusList(){
        return paymentChannelsMapper.selectByStatusList();
    }
}
