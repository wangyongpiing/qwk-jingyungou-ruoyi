package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物流 对象 logistics
 * 
 * @author cjf
 * @date 2023-06-30
 */
public class Logistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNumber;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImage;

    /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    private String consigneeName;

    /** 收货人手机号 */
    @Excel(name = "收货人手机号")
    private String consigneePhone;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String trackingNumber;

    /** 快递公司 */
    @Excel(name = "快递公司")
    private String expressCompany;

    /** 快递公司编号 */
    @Excel(name = "快递公司编号")
    private String expressCode;

    /** 快递logo */
    @Excel(name = "快递logo")
    private String logo;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String consigneeAddress;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

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
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsImage(String goodsImage) 
    {
        this.goodsImage = goodsImage;
    }

    public String getGoodsImage() 
    {
        return goodsImage;
    }
    public void setConsigneeName(String consigneeName) 
    {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeName() 
    {
        return consigneeName;
    }
    public void setConsigneePhone(String consigneePhone) 
    {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneePhone() 
    {
        return consigneePhone;
    }
    public void setTrackingNumber(String trackingNumber) 
    {
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingNumber() 
    {
        return trackingNumber;
    }
    public void setExpressCompany(String expressCompany) 
    {
        this.expressCompany = expressCompany;
    }

    public String getExpressCompany() 
    {
        return expressCompany;
    }
    public void setExpressCode(String expressCode)
    {
        this.expressCode = expressCode;
    }

    public String getExpressCode()
    {
        return expressCode;
    }
    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getLogo()
    {
        return logo;
    }
    public void setConsigneeAddress(String consigneeAddress) 
    {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeAddress() 
    {
        return consigneeAddress;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNumber", getOrderNumber())
            .append("goodsName", getGoodsName())
            .append("goodsImage", getGoodsImage())
            .append("consigneeName", getConsigneeName())
            .append("consigneePhone", getConsigneePhone())
            .append("trackingNumber", getTrackingNumber())
            .append("expressCompany", getExpressCompany())
            .append("expressCode", getExpressCode())
            .append("logo", getLogo())
            .append("consigneeAddress", getConsigneeAddress())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
