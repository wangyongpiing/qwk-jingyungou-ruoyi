package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 系统消息通知对象 system_notice
 * 
 * @author cjf
 * @date 2023-10-10
 */
public class SystemNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 系统通知标题 */
    @Excel(name = "系统通知标题")
    private String notificationTitle;

    /** 系统通知内容 */
    @Excel(name = "系统通知内容")
    private String notificationContent;

    /** 消息通知标题 */
    @Excel(name = "消息通知标题")
    private String notifyTitle;

    /** 消息主体内容 */
    @Excel(name = "消息主体内容")
    private String messageBody;

    /** 通知用户编号 */
    @Excel(name = "通知用户编号")
    private Long userId;

    /** 是否通知所有人 */
    @Excel(name = "是否通知所有人")
    private String isAll;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNotificationTitle(String notificationTitle) 
    {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationTitle() 
    {
        return notificationTitle;
    }
    public void setNotificationContent(String notificationContent)
    {
        this.notificationContent = notificationContent;
    }

    public String getNotificationContent()
    {
        return notificationContent;
    }
    public void setNotifyTitle(String notifyTitle) 
    {
        this.notifyTitle = notifyTitle;
    }

    public String getNotifyTitle() 
    {
        return notifyTitle;
    }
    public void setMessageBody(String messageBody) 
    {
        this.messageBody = messageBody;
    }

    public String getMessageBody() 
    {
        return messageBody;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setIsAll(String isAll) 
    {
        this.isAll = isAll;
    }

    public String getIsAll() 
    {
        return isAll;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("notificationTitle", getNotificationTitle())
            .append("notificationContent", getNotificationContent())
            .append("notifyTitle", getNotifyTitle())
            .append("messageBody", getMessageBody())
            .append("userId", getUserId())
            .append("isAll", getIsAll())
            .append("state", getState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
