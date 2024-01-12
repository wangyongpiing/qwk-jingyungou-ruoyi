package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 兑换记录对象 exchange_records
 * 
 * @author cjf
 * @date 2023-07-25
 */
public class ExchangeRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 兑换订单id */
    @Excel(name = "兑换订单id")
    private Long orderId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String userName;

    /** 消耗类型 */
    @Excel(name = "消耗类型")
    private String spendType;

    /** 消耗 */
    @Excel(name = "消耗")
    private String spend;

    /** 到账类型 */
    @Excel(name = "到账类型")
    private String receiveType;

    /** 到账 */
    @Excel(name = "到账")
    private String receive;

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
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setSpendType(String spendType) 
    {
        this.spendType = spendType;
    }

    public String getSpendType() 
    {
        return spendType;
    }
    public void setSpend(String spend) 
    {
        this.spend = spend;
    }

    public String getSpend() 
    {
        return spend;
    }
    public void setReceiveType(String receiveType) 
    {
        this.receiveType = receiveType;
    }

    public String getReceiveType() 
    {
        return receiveType;
    }
    public void setReceive(String receive) 
    {
        this.receive = receive;
    }

    public String getReceive() 
    {
        return receive;
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
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("spendType", getSpendType())
            .append("spend", getSpend())
            .append("receiveType", getReceiveType())
            .append("receive", getReceive())
            .append("state", getState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
