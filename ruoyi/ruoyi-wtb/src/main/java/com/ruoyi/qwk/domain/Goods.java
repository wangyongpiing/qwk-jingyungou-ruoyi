package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品 对象 goods
 *
 * @author cjf
 * @date 2023-07-03
 */
public class Goods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品类型 */
    @Excel(name = "商品类型")
    private Long type;

    /** 分类id */
    @Excel(name = "分类id")
    private Long categoryId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String image;

    /** 商品货号 */
    @Excel(name = "商品货号")
    private String number;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 成本价 */
    @Excel(name = "成本价")
    private String costPrice;

    /** 平台价 */
    @Excel(name = "平台价")
    private String platformPrice;

    /** 现金价 */
    @Excel(name = "现金价")
    private String cashPrice;

    /** 邮费 */
    @Excel(name = "邮费")
    private String postage;

    /** 已售数量 */
    @Excel(name = "已售数量")
    private String soldQuantity;

    /** 库存 */
    @Excel(name = "库存")
    private String inventory;

    /** 上架时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上架时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date listingTime;

    /** 下架时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "下架时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date offShelfTime;

    /** 商品状态 */
    @Excel(name = "商品状态")
    private String status;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 是否碎片 */
    @Excel(name = "是否碎片")
    private String isDebris;

    /** 中奖权重 */
    @Excel(name = "中奖权重")
    private String weight;

    /** 商品规格 */
    @Excel(name = "商品规格")
    private String pSId;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String describe;

    /** 是否是积分兑换 */
    @Excel(name = "商品描述")
    private String isIntegral;

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
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }

    public String getSupplier()
    {
        return supplier;
    }
    public void setCostPrice(String costPrice)
    {
        this.costPrice = costPrice;
    }

    public String getCostPrice()
    {
        return costPrice;
    }
    public void setPlatformPrice(String platformPrice)
    {
        this.platformPrice = platformPrice;
    }

    public String getPlatformPrice()
    {
        return platformPrice;
    }
    public void setCashPrice(String cashPrice)
    {
        this.cashPrice = cashPrice;
    }

    public String getCashPrice()
    {
        return cashPrice;
    }
    public void setPostage(String postage)
    {
        this.postage = postage;
    }

    public String getPostage()
    {
        return postage;
    }
    public void setSoldQuantity(String soldQuantity)
    {
        this.soldQuantity = soldQuantity;
    }

    public String getSoldQuantity()
    {
        return soldQuantity;
    }
    public void setInventory(String inventory)
    {
        this.inventory = inventory;
    }

    public String getInventory()
    {
        return inventory;
    }
    public void setListingTime(Date listingTime)
    {
        this.listingTime = listingTime;
    }

    public Date getListingTime()
    {
        return listingTime;
    }
    public void setOffShelfTime(Date offShelfTime)
    {
        this.offShelfTime = offShelfTime;
    }

    public Date getOffShelfTime()
    {
        return offShelfTime;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setIsDebris(String isDebris)
    {
        this.isDebris = isDebris;
    }

    public String getIsDebris()
    {
        return isDebris;
    }
    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public String getWeight()
    {
        return weight;
    }
    public void setpSId(String pSId)
    {
        this.pSId = pSId;
    }

    public String getpSId()
    {
        return pSId;
    }
    public void setDescribe(String describe)
    {
        this.describe = describe;
    }

    public String getDescribe()
    {
        return describe;
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
                .append("type", getType())
                .append("categoryId",getCategoryId())
                .append("name", getName())
                .append("image", getImage())
                .append("number", getNumber())
                .append("supplier", getSupplier())
                .append("costPrice", getCostPrice())
                .append("platformPrice", getPlatformPrice())
                .append("cashPrice", getCashPrice())
                .append("postage", getPostage())
                .append("soldQuantity", getSoldQuantity())
                .append("inventory", getInventory())
                .append("listingTime", getListingTime())
                .append("offShelfTime", getOffShelfTime())
                .append("status", getStatus())
                .append("sort", getSort())
                .append("isDebris", getIsDebris())
                .append("weight", getWeight())
                .append("pSId", getpSId())
                .append("describe", getDescribe())
                .append("createdTime", getCreatedTime())
                .append("updatedBy", getUpdatedBy())
                .append("updatedTime", getUpdatedTime())
                .toString();
    }
}
