package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库商品积分详情 对象 points_details
 *
 * @author cjf
 * @date 2023-07-26
 */
public class PointsDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    private String userPhone;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String userName;

    /** 仓库详情id */
    @Excel(name = "仓库详情id")
    private Long wDId;

    /** 兑换订单id */
    @Excel(name = "兑换订单id")
    private Long orderId;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品类型 */
    @Excel(name = "商品类型")
    private String goodsType;

    /** 商品价值 */
    @Excel(name = "商品价值")
    private String commodityValue;

    /** 兑换积分 */
    @Excel(name = "兑换积分")
    private String redeemPoints;

    /** 转赠账号 */
    @Excel(name = "转赠账号")
    private String transfer;

    /** 物流详情 */
    @Excel(name = "物流详情")
    private String logistics;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public String getUserPhone()
    {
        return userPhone;
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
    public void setwDId(Long wDId)
    {
        this.wDId = wDId;
    }

    public Long getwDId()
    {
        return wDId;
    }
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
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
    public void setGoodsType(String goodsType)
    {
        this.goodsType = goodsType;
    }

    public String getGoodsType()
    {
        return goodsType;
    }
    public void setCommodityValue(String commodityValue)
    {
        this.commodityValue = commodityValue;
    }

    public String getCommodityValue()
    {
        return commodityValue;
    }
    public void setRedeemPoints(String redeemPoints)
    {
        this.redeemPoints = redeemPoints;
    }

    public String getRedeemPoints()
    {
        return redeemPoints;
    }
    public void setTransfer(String transfer)
    {
        this.transfer = transfer;
    }

    public String getTransfer()
    {
        return transfer;
    }
    public void setLogistics(String logistics)
    {
        this.logistics = logistics;
    }

    public String getLogistics()
    {
        return logistics;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userPhone", getUserPhone())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("wDId", getwDId())
                .append("orderId", getOrderId())
                .append("state", getState())
                .append("goodsId", getGoodsId())
                .append("goodsName", getGoodsName())
                .append("goodsType", getGoodsType())
                .append("commodityValue", getCommodityValue())
                .append("redeemPoints", getRedeemPoints())
                .append("transfer", getTransfer())
                .append("logistics", getLogistics())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .toString();
    }
}
