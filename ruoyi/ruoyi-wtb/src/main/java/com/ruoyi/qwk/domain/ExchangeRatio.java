package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 积分兑换比例设置对象 exchange_ratio
 * 
 * @author cjf
 * @date 2023-06-28
 */
public class ExchangeRatio extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品占比 */
    @Excel(name = "商品占比")
    private Long goodsProportion;

    /** 积分占比 */
    @Excel(name = "积分占比")
    private Long integralProportion;

    /** 适用商品 */
    @Excel(name = "适用商品")
    private String applicable;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Long enableOrNot;

    /** 创建人 */
    private String createdBy;

    /** 创建时间 */
    private Date createdTime;

    /** 更新人 */
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
    public void setGoodsProportion(Long goodsProportion) 
    {
        this.goodsProportion = goodsProportion;
    }

    public Long getGoodsProportion() 
    {
        return goodsProportion;
    }
    public void setIntegralProportion(Long integralProportion) 
    {
        this.integralProportion = integralProportion;
    }

    public Long getIntegralProportion() 
    {
        return integralProportion;
    }
    public void setApplicable(String applicable) 
    {
        this.applicable = applicable;
    }

    public String getApplicable() 
    {
        return applicable;
    }
    public void setEnableOrNot(Long enableOrNot) 
    {
        this.enableOrNot = enableOrNot;
    }

    public Long getEnableOrNot() 
    {
        return enableOrNot;
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
            .append("goodsProportion", getGoodsProportion())
            .append("integralProportion", getIntegralProportion())
            .append("applicable", getApplicable())
            .append("enableOrNot", getEnableOrNot())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
