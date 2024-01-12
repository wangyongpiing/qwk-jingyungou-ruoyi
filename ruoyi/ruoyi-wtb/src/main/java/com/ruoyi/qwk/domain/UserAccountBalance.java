package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户账户余额对象 user_account_balance
 *
 * @author cjf
 * @date 2023-12-21
 */
public class UserAccountBalance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 账户余额 */
    @Excel(name = "账户余额")
    private Double accountBalance;

    /** 支付宝账户 */
    @Excel(name = "支付宝账户")
    private String alipayAccount;

    /** 支付宝账户名称 */
    @Excel(name = "支付宝账户名称")
    private String alipayAccountName;

    /** 账户状态0-正常1-异常2-冻结 */
    @Excel(name = "账户状态0-正常1-异常2-冻结")
    private String status;


    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getAlipayAccountName() {
        return alipayAccountName;
    }

    public void setAlipayAccountName(String alipayAccountName) {
        this.alipayAccountName = alipayAccountName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setAccountBalance(Double accountBalance)
    {
        this.accountBalance = accountBalance;
    }

    public Double getAccountBalance()
    {
        return accountBalance;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("accountBalance", getAccountBalance())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
