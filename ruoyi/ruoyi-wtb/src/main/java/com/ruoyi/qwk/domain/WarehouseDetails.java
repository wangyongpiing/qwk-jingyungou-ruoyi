package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库详情 对象 warehouse_details
 *
 * @author cjf
 * @date 2023-07-24
 */
public class WarehouseDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 仓库id */
    @Excel(name = "仓库id")
    private Long wid;

    /** 订单号 */
    @Excel(name = "订单号")
    private Long ordersId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 物品类型 */
    @Excel(name = "物品类型")
    private String type;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImage;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品来源 */
    @Excel(name = "商品来源")
    private String source;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long quantity;

    /** 商品价格（积分） */
    @Excel(name = "商品价格（积分） ")
    private String price;

    /** 商品现金价 */
    @Excel(name = "商品现金价 ")
    private String cashPrice;

    /** 状态 0:未做任何操作,可兑换积分或转赠;1:已兑换积分;2:已转赠;3:已兑换实物. */
    @Excel(name = "状态 0:未做任何操作,可兑换积分或转赠;1:已兑换积分;2:已转赠;3:已兑换实物.")
    private Long state;

    /** 是否新手奖品 */
    @Excel(name = "是否新手奖品")
    private String isNovice;

    /** 助力次数 */
    @Excel(name = "助力次数")
    private Long assistanceNum;

    /** 虚拟商品类型 */
    @Excel(name = "虚拟商品类型")
    private String virtualProductType;

    /**商品*/
    private Goods goods;

    public String getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(String cashPrice) {
        this.cashPrice = cashPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
    public void setWid(Long wid)
    {
        this.wid = wid;
    }

    public Long getWid()
    {
        return wid;
    }
    public void setOrdersId(Long ordersId)
    {
        this.ordersId = ordersId;
    }

    public Long getOrdersId()
    {
        return ordersId;
    }
    public void setGoodsId(Long goodsId)
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId()
    {
        return goodsId;
    }
    public void setGoodsImage(String goodsImage)
    {
        this.goodsImage = goodsImage;
    }

    public String getGoodsImage()
    {
        return goodsImage;
    }
    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName()
    {
        return goodsName;
    }
    public void setSource(String source)
    {
        this.source = source;
    }

    public String getSource()
    {
        return source;
    }
    public void setQuantity(Long quantity)
    {
        this.quantity = quantity;
    }

    public Long getQuantity()
    {
        return quantity;
    }
    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getPrice()
    {
        return price;
    }
    public void setState(Long state)
    {
        this.state = state;
    }

    public Long getState()
    {
        return state;
    }
    public void setIsNovice(String isNovice)
    {
        this.isNovice = isNovice;
    }

    public String getIsNovice()
    {
        return isNovice;
    }
    public void setAssistanceNum(Long assistanceNum)
    {
        this.assistanceNum = assistanceNum;
    }

    public Long getAssistanceNum()
    {
        return assistanceNum;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    public void setVirtualProductType(String virtualProductType)
    {
        this.virtualProductType = virtualProductType;
    }

    public String getVirtualProductType()
    {
        return virtualProductType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("wid", getWid())
                .append("ordersId", getOrdersId())
                .append("goodsId", getGoodsId())
                .append("type", getType())
                .append("goodsImage", getGoodsImage())
                .append("goodsName", getGoodsName())
                .append("source", getSource())
                .append("quantity", getQuantity())
                .append("price", getPrice())
                .append("state", getState())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("remark", getRemark())
                .append("isNovice", getIsNovice())
                .append("assistanceNum", getAssistanceNum())
                .append("goods",getGoods())
                .append("virtualProductType", getVirtualProductType())
                .toString();
    }
}
