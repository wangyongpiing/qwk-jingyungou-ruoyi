package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 运营商信息 对象 operator_information
 *
 * @author cjf
 * @date 2023-12-01
 */
public class OperatorInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 运营商名称 */
    @Excel(name = "运营商名称")
    private String operatorName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 微信号 */
    @Excel(name = "微信号")
    private String wx;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 地址 */
    @Excel(name = "地址")
    private String addresss;

    /** 头像 */
    @Excel(name = "头像")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }

    public String getOperatorName()
    {
        return operatorName;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setWx(String wx)
    {
        this.wx = wx;
    }

    public String getWx()
    {
        return wx;
    }
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getIdCard()
    {
        return idCard;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("operatorName", getOperatorName())
            .append("phone", getPhone())
            .append("wx", getWx())
            .append("idCard", getIdCard())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
