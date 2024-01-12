package com.ruoyi.qwk.blindBox.domain;

import com.ruoyi.qwk.domain.Goods;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户盲盒爆出奖品对象 user_prize
 * 
 * @author cjf
 * @date 2023-09-21
 */
public class UserPrize extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long goodsId;

    /** 奖励类型 */
    @Excel(name = "奖励类型")
    private String type;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    /** 奖品数量 */
    @Excel(name = "奖品数量")
    private Long num;

    /** 是否新手奖品 */
    @Excel(name = "是否新手奖品")
    private String isNovice;

    /**商品对象*/
    private Goods goods;

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
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }
    public void setIsNovice(String isNovice)
    {
        this.isNovice = isNovice;
    }

    public String getIsNovice()
    {
        return isNovice;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("goodsId", getGoodsId())
            .append("type", getType())
            .append("state", getState())
            .append("num", getNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("goods", getGoods())
            .append("isNovice", getIsNovice())
            .toString();
    }
}
