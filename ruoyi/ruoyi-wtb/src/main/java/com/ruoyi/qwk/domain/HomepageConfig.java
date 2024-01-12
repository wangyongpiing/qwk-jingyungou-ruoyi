package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 首页配置 对象 homepage_config
 *
 * @author cjf
 * @date 2023-07-19
 */
public class HomepageConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id id唯一标识 */
    private Long id;

    /** 排序 数值越大排序越靠前 */
    @Excel(name = "排序 数值越大排序越靠前")
    private Long sort;

    /** 分类 */
    @Excel(name = "分类")
    private Long classify;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 图片 */
    @Excel(name = "图片")
    private String imgUrl;

    /** 宽 */
    @Excel(name = "宽")
    private String wide;

    /** 高 */
    @Excel(name = "高")
    private String high;

    /** 链接地址 */
    @Excel(name = "链接地址")
    private String linkAddress;

    /** 加入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "加入时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** 显示状态 */
    @Excel(name = "显示状态")
    private Long displayStatus;

    /** 显示位置 */
    @Excel(name = "显示位置")
    private Long displayLocation;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setClassify(Long classify)
    {
        this.classify = classify;
    }

    public Long getClassify()
    {
        return classify;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }
    public void setWide(String wide)
    {
        this.wide = wide;
    }

    public String getWide()
    {
        return wide;
    }
    public void setHigh(String high)
    {
        this.high = high;
    }

    public String getHigh()
    {
        return high;
    }
    public void setLinkAddress(String linkAddress)
    {
        this.linkAddress = linkAddress;
    }

    public String getLinkAddress()
    {
        return linkAddress;
    }
    public void setCreationTime(Date creationTime)
    {
        this.creationTime = creationTime;
    }

    public Date getCreationTime()
    {
        return creationTime;
    }
    public void setState(Long state)
    {
        this.state = state;
    }

    public Long getState()
    {
        return state;
    }
    public void setDisplayStatus(Long displayStatus)
    {
        this.displayStatus = displayStatus;
    }

    public Long getDisplayStatus()
    {
        return displayStatus;
    }
    public void setDisplayLocation(Long displayLocation)
    {
        this.displayLocation = displayLocation;
    }

    public Long getDisplayLocation()
    {
        return displayLocation;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sort", getSort())
                .append("classify", getClassify())
                .append("title", getTitle())
                .append("imgUrl", getImgUrl())
                .append("wide", getWide())
                .append("high", getHigh())
                .append("linkAddress", getLinkAddress())
                .append("creationTime", getCreationTime())
                .append("state", getState())
                .append("displayStatus", getDisplayStatus())
                .append("displayLocation", getDisplayLocation())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .toString();
    }
}
