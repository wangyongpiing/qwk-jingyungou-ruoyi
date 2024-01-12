package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充值档位及比例 对象 money_ratio
 * 
 * @author cjf
 * @date 2023-06-30
 */
public class MoneyRatio extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 充值类型 */
    @Excel(name = "充值类型")
    private Long type;

    /** 充值档位 */
    @Excel(name = "充值档位")
    private String gearPosition;

    /** 现金占比 */
    @Excel(name = "现金占比")
    private String moneyProportion;

    /** 虚拟占比 */
    @Excel(name = "虚拟占比")
    private String unrealProportion;

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
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setGearPosition(String gearPosition) 
    {
        this.gearPosition = gearPosition;
    }

    public String getGearPosition() 
    {
        return gearPosition;
    }
    public void setMoneyProportion(String moneyProportion) 
    {
        this.moneyProportion = moneyProportion;
    }

    public String getMoneyProportion() 
    {
        return moneyProportion;
    }
    public void setUnrealProportion(String unrealProportion) 
    {
        this.unrealProportion = unrealProportion;
    }

    public String getUnrealProportion() 
    {
        return unrealProportion;
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
            .append("type", getType())
            .append("gearPosition", getGearPosition())
            .append("moneyProportion", getMoneyProportion())
            .append("unrealProportion", getUnrealProportion())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
