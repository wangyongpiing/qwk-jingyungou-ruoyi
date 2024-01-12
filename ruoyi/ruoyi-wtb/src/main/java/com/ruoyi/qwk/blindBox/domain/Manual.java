package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图鉴对象 manual
 * 
 * @author cjf
 * @date 2023-10-31
 */
public class Manual extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 分类编号 */
    @Excel(name = "分类编号")
    private Long categoryId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String goodsImage;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private String goodsPrice;

    /** 获取概率 */
    @Excel(name = "获取概率")
    private String probability;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
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
    public void setGoodsPrice(String goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setProbability(String probability) 
    {
        this.probability = probability;
    }

    public String getProbability() 
    {
        return probability;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryId", getCategoryId())
            .append("goodsName", getGoodsName())
            .append("goodsImage", getGoodsImage())
            .append("goodsPrice", getGoodsPrice())
            .append("probability", getProbability())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
