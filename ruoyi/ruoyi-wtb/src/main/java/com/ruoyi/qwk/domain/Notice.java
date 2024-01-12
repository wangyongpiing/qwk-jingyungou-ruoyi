package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公告管理对象 notice
 *
 * @author cjf
 * @date 2023-07-19
 */
public class Notice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 推送文案 */
    @Excel(name = "推送文案")
    private String copywriting;

    /** 推送图片 */
    @Excel(name = "推送图片")
    private String img;

    /** 详情 */
    @Excel(name = "详情")
    private String details;

    /** 点击数 */
    @Excel(name = "点击数")
    private Long clickNumber;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseTime;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** 消息类型 */
    @Excel(name = "消息类型")
    private Long messageType;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setCopywriting(String copywriting)
    {
        this.copywriting = copywriting;
    }

    public String getCopywriting()
    {
        return copywriting;
    }
    public void setImg(String img)
    {
        this.img = img;
    }

    public String getImg()
    {
        return img;
    }
    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getDetails()
    {
        return details;
    }
    public void setClickNumber(Long clickNumber)
    {
        this.clickNumber = clickNumber;
    }

    public Long getClickNumber()
    {
        return clickNumber;
    }
    public void setReleaseTime(Date releaseTime)
    {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseTime()
    {
        return releaseTime;
    }
    public void setState(Long state)
    {
        this.state = state;
    }

    public Long getState()
    {
        return state;
    }
    public void setMessageType(Long messageType)
    {
        this.messageType = messageType;
    }

    public Long getMessageType()
    {
        return messageType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("copywriting", getCopywriting())
                .append("img", getImg())
                .append("details", getDetails())
                .append("clickNumber", getClickNumber())
                .append("releaseTime", getReleaseTime())
                .append("state", getState())
                .append("messageType", getMessageType())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
