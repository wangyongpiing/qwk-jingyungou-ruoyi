package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生肖序列卡 对象 serial_card
 *
 * @author cjf
 * @date 2023-12-01
 */
public class SerialCard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单id */
    @Excel(name = "订单id")
    private String orderId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderCode;

    /** 卡号 */
    @Excel(name = "卡号")
    private String cardCode;

    /** 卡号类型 */
    @Excel(name = "卡号类型")
    private String cardType;

    /** 运营商id */
    @Excel(name = "运营商id")
    private String operatorId;

    /** 运营商名称 */
    @Excel(name = "运营商名称")
    private String operatorName;

    /** 购买状态:(0-未购买 1-已购买) */
    @Excel(name = "购买状态:(0-未购买 1-已购买)")
    private String purchaseState;

    /** 兑换状态:(0-未兑换 1-已兑换) */
    @Excel(name = "兑换状态:(0-未兑换 1-已兑换)")
    private String redemptionStatus;

    public String getRedemptionStatusName() {
        return redemptionStatusName;
    }

    public void setRedemptionStatusName(String redemptionStatusName) {
        this.redemptionStatusName = redemptionStatusName;
    }

    private String redemptionStatusName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }
    public void setCardCode(String cardCode)
    {
        this.cardCode = cardCode;
    }

    public String getCardCode()
    {
        return cardCode;
    }
    public void setCardType(String cardType)
    {
        this.cardType = cardType;
    }

    public String getCardType()
    {
        return cardType;
    }
    public void setOperatorId(String operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getOperatorId()
    {
        return operatorId;
    }
    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }

    public String getOperatorName()
    {
        return operatorName;
    }
    public void setPurchaseState(String purchaseState)
    {
        this.purchaseState = purchaseState;
    }

    public String getPurchaseState()
    {
        return purchaseState;
    }
    public void setRedemptionStatus(String redemptionStatus)
    {
        this.redemptionStatus = redemptionStatus;
    }

    public String getRedemptionStatus()
    {
        return redemptionStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderCode", getOrderCode())
            .append("cardCode", getCardCode())
            .append("cardType", getCardType())
            .append("operatorId", getOperatorId())
            .append("operatorName", getOperatorName())
            .append("purchaseState", getPurchaseState())
            .append("redemptionStatus", getRedemptionStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
