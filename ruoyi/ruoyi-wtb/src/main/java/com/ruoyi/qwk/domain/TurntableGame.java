package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 转盘游戏 对象 turntable_game
 * 
 * @author cjf
 * @date 2023-12-20
 */
public class TurntableGame extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String name;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 单人当天限制次数 0：代表不限制 */
    @Excel(name = "单人当天限制次数 0：代表不限制")
    private Long dayLimit;

    /** 单人总次数限制 0：代表不限制 */
    @Excel(name = "单人总次数限制 0：代表不限制")
    private Long singleLimit;

    /** 活动状态，1 开启 2 关闭 */
    @Excel(name = "活动状态，1 开启 2 关闭")
    private Long state;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setDayLimit(Long dayLimit) 
    {
        this.dayLimit = dayLimit;
    }

    public Long getDayLimit() 
    {
        return dayLimit;
    }
    public void setSingleLimit(Long singleLimit) 
    {
        this.singleLimit = singleLimit;
    }

    public Long getSingleLimit() 
    {
        return singleLimit;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("description", getDescription())
            .append("dayLimit", getDayLimit())
            .append("singleLimit", getSingleLimit())
            .append("state", getState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
