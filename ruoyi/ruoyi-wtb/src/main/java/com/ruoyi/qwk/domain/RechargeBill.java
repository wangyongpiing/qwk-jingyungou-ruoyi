package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充值流水 对象 recharge_bill
 *
 * @author cjf
 * @date 2023-07-21
 */
public class RechargeBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单编号 */
    @Excel(name = "充值订单编号")
    private String orderNumber;

    /** 支付订单号 */
    @Excel(name = "支付订单号")
    private String paymentNumber;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    private String userPhone;

    /** 充值类型 */
    @Excel(name = "充值类型")
    private Long type;

    /** 充值账号 */
    @Excel(name = "充值账号")
    private String account;

    /** 充值档位 */
    @Excel(name = "充值档位")
    private String rechargeGrade;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private String paymentAmount;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String paymentMethod;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }
    public void setPaymentNumber(String paymentNumber)
    {
        this.paymentNumber = paymentNumber;
    }

    public String getPaymentNumber()
    {
        return paymentNumber;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public String getUserPhone()
    {
        return userPhone;
    }
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getAccount()
    {
        return account;
    }
    public void setRechargeGrade(String rechargeGrade)
    {
        this.rechargeGrade = rechargeGrade;
    }

    public String getRechargeGrade()
    {
        return rechargeGrade;
    }
    public void setPaymentAmount(String paymentAmount)
    {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentAmount()
    {
        return paymentAmount;
    }
    public void setPaymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }
    public void setPaymentTime(Date paymentTime)
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime()
    {
        return paymentTime;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderNumber", getOrderNumber())
                .append("paymentNumber", getPaymentNumber())
                .append("userId", getUserId())
                .append("userPhone", getUserPhone())
                .append("type", getType())
                .append("account", getAccount())
                .append("rechargeGrade", getRechargeGrade())
                .append("paymentAmount", getPaymentAmount())
                .append("paymentMethod", getPaymentMethod())
                .append("paymentTime", getPaymentTime())
                .append("state", getState())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
