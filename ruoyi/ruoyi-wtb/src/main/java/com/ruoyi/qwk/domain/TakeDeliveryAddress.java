package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收货地址对象 take_delivery_address
 *
 * @author cjf
 * @date 2023-07-24
 */
public class TakeDeliveryAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    private String consignee;

    /** 收货人手机号 */
    @Excel(name = "收货人手机号")
    private String phone;

    /** 收货人地区 */
    @Excel(name = "收货人地区")
    private String area;

    /** 收货人详细地址 */
    @Excel(name = "收货人详细地址")
    private String address;

    /** 邮编 */
    @Excel(name = "邮编")
    private String zipCode;

    /** 是否默认收货地址 */
    @Excel(name = "是否默认收货地址")
    private Long isDefault;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setConsignee(String consignee)
    {
        this.consignee = consignee;
    }

    public String getConsignee()
    {
        return consignee;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setArea(String area)
    {
        this.area = area;
    }

    public String getArea()
    {
        return area;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getZipCode()
    {
        return zipCode;
    }
    public void setIsDefault(Long isDefault)
    {
        this.isDefault = isDefault;
    }

    public Long getIsDefault()
    {
        return isDefault;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("consignee", getConsignee())
                .append("phone", getPhone())
                .append("area", getArea())
                .append("address", getAddress())
                .append("zipCode", getZipCode())
                .append("isDefault", getIsDefault())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
