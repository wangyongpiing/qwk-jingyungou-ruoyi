package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 个人每日任务记录 对象 personal_daily_task_record
 * 
 * @author cjf
 * @date 2023-12-22
 */
public class PersonalDailyTaskRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 任务id */
    @Excel(name = "任务id")
    private String taskId;

    /** 任务状态2-已领取1-领取0-前往（待完成） */
    @Excel(name = "任务状态2-已领取1-领取0-前往", readConverterExp = "待=完成")
    private String status;

    /** 任务完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskEndTime;

    /** 任务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskStartTime;

    /** 已完成数量 */
    @Excel(name = "已完成数量")
    private String completedQuantity;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setTaskEndTime(Date taskEndTime) 
    {
        this.taskEndTime = taskEndTime;
    }

    public Date getTaskEndTime() 
    {
        return taskEndTime;
    }
    public void setTaskStartTime(Date taskStartTime) 
    {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskStartTime() 
    {
        return taskStartTime;
    }
    public void setCompletedQuantity(String completedQuantity) 
    {
        this.completedQuantity = completedQuantity;
    }

    public String getCompletedQuantity() 
    {
        return completedQuantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("taskId", getTaskId())
            .append("status", getStatus())
            .append("taskEndTime", getTaskEndTime())
            .append("taskStartTime", getTaskStartTime())
            .append("completedQuantity", getCompletedQuantity())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
