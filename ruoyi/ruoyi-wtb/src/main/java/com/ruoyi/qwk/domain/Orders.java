package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品订单 对象 orders
 * 
 * @author cjf
 * @date 2023-07-01
 */
@ApiModel(value = "Orders" ,description = "商品订单")
public class Orders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty(value = "唯一标识")
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    /** 用户id */
    @Excel(name = "用户id")
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    @ApiModelProperty(value = "商品图片")
    private String goodsImage;

    /** 商品类型 */
    @Excel(name = "商品类型")
    @ApiModelProperty(value = "商品类型")
    private Long goodsType;

    /** 单价 */
    @Excel(name = "单价")
    @ApiModelProperty(value = "单价")
    private String price;

    /** 商品数量 */
    @Excel(name = "商品数量")
    @ApiModelProperty(value = "商品数量")
    private Long quantity;

    /** 收货地址id */
    @Excel(name = "收货地址id")
    private Long tDAId;

    /** 运费 */
    @Excel(name = "运费")
    @ApiModelProperty(value = "运费")
    private String costs;

    /** 消耗积分 */
    @Excel(name = "消耗积分")
    @ApiModelProperty(value = "消耗积分")
    private String integral;

    /** 支付金额 */
    @Excel(name = "支付金额")
    @ApiModelProperty(value = "支付金额")
    private String paymentAmount;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "支付时间")
    private Date paymentTime;

    /** 支付方式 */
    @Excel(name = "支付方式")
    @ApiModelProperty(value = "支付方式")
    private Long paymentMethod;

    /** 订单状态 */
    @Excel(name = "订单状态")
    @ApiModelProperty(value = "订单状态")
    private Long status;

    /** 物流编号 */
    @Excel(name = "物流编号")
    private Long logisticsId;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /** 收货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "收货时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date receivingTime;

    /** 创建人 */
    @Excel(name = "创建人")
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    /** 物流信息 */
    private Logistics logistics;

    /** 收货地址 */
    private TakeDeliveryAddress takeDeliveryAddress;

    /**商品信息*/
    private Goods goods;

    public Long gettDAId() {
        return tDAId;
    }

    public void settDAId(Long tDAId) {
        this.tDAId = tDAId;
    }

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
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setGoodsId(Long goodsId)
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId()
    {
        return goodsId;
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
    public void setGoodsType(Long goodsType) 
    {
        this.goodsType = goodsType;
    }

    public Long getGoodsType() 
    {
        return goodsType;
    }
    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getPrice() 
    {
        return price;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setCosts(String costs) 
    {
        this.costs = costs;
    }

    public String getCosts() 
    {
        return costs;
    }
    public void setIntegral(String integral) 
    {
        this.integral = integral;
    }

    public String getIntegral() 
    {
        return integral;
    }
    public void setPaymentAmount(String paymentAmount) 
    {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentAmount() 
    {
        return paymentAmount;
    }
    public void setPaymentTime(Date paymentTime) 
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() 
    {
        return paymentTime;
    }
    public void setPaymentMethod(Long paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }

    public Long getPaymentMethod() 
    {
        return paymentMethod;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setLogisticsId(Long logisticsId)
    {
        this.logisticsId = logisticsId;
    }

    public Long getLogisticsId()
    {
        return logisticsId;
    }
    public void setSendTime(Date sendTime)
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime()
    {
        return sendTime;
    }
    public void setReceivingTime(Date receivingTime)
    {
        this.receivingTime = receivingTime;
    }

    public Date getReceivingTime()
    {
        return receivingTime;
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

    public Logistics getLogistics() {
        return logistics;
    }

    public void setLogistics(Logistics logistics) {
        this.logistics = logistics;
    }

    public TakeDeliveryAddress getTakeDeliveryAddress() {
        return takeDeliveryAddress;
    }

    public void setTakeDeliveryAddress(TakeDeliveryAddress takeDeliveryAddress) {
        this.takeDeliveryAddress = takeDeliveryAddress;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", userId=" + userId +
                ", userPhone='" + userPhone + '\'' +
                ", nickName='" + nickName + '\'' +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsImage='" + goodsImage + '\'' +
                ", goodsType=" + goodsType +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                ", tDAId=" + tDAId +
                ", costs='" + costs + '\'' +
                ", integral='" + integral + '\'' +
                ", paymentAmount='" + paymentAmount + '\'' +
                ", paymentTime=" + paymentTime +
                ", paymentMethod=" + paymentMethod +
                ", status=" + status +
                ", logisticsId=" + logisticsId +
                ", sendTime=" + sendTime +
                ", receivingTime=" + receivingTime +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", logistics=" + logistics +
                ", takeDeliveryAddress=" + takeDeliveryAddress +
                ", goods=" + goods +
                '}';
    }
}
