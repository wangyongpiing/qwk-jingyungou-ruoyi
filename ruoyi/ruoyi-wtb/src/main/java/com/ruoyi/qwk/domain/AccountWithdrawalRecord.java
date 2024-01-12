package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 账户提现记录 对象 account_withdrawal_record
 *
 * @author cjf
 * @date 2023-12-27
 */
public class AccountWithdrawalRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 提现金额 */
    @Excel(name = "提现金额")
    private String withdrawalAmount;

    /** 是否成功 0 成功 1失败 */
    @Excel(name = "是否成功 0 成功 1失败")
    private String isSuccess;

    /** 提现类型1-支付宝提现 */
    @Excel(name = "提现类型1-支付宝提现")
    private String type;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /** 提现类型名称1-支付宝提现 */
    private String typeName;

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
    public void setWithdrawalAmount(String withdrawalAmount)
    {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getWithdrawalAmount()
    {
        return withdrawalAmount;
    }
    public void setIsSuccess(String isSuccess)
    {
        this.isSuccess = isSuccess;
    }

    public String getIsSuccess()
    {
        return isSuccess;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("withdrawalAmount", getWithdrawalAmount())
            .append("isSuccess", getIsSuccess())
            .append("remark", getRemark())
            .append("type", getType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
