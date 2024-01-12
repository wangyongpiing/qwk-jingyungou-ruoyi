package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 个人任务 对象 personal_tasks
 *
 * @author cjf
 * @date 2023-12-22
 */
public class PersonalTasks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 游戏id */
    @Excel(name = "游戏id")
    private String gameId;

    /** 任务类型编码 */
    @Excel(name = "任务类型编码")
    private String typeCode;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 是否发布任务1-发布0-取消发布 */
    @Excel(name = "是否发布任务1-发布0-取消发布")
    private String isRelease;

    /** 奖励 */
    @Excel(name = "奖励")
    private Double reward;

    /** 奖励类型1-红包2-糖果 */
    @Excel(name = "奖励类型1-红包2-糖果")
    private String rewardType;

    /** 任务次数 */
    @Excel(name = "任务次数")
    private Long taskCount;

    /**
     * 是否是每日任务
     */
    private String isDaily;

    /**
     * 任务编码
     */
    private String taskCode;

    /**
     * 排序
     */
    private Long sort;

    private String remark;

    private String remark2;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getTaskCompleteType() {
        return taskCompleteType;
    }

    public void setTaskCompleteType(String taskCompleteType) {
        this.taskCompleteType = taskCompleteType;
    }

    /**
     * 完成任务类型1-局内获取糖果 2-局内游戏次数
     */
    private String taskCompleteType;

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 任务状态
     */
    private String taskStatus;

    public String getCompletedQuantity() {
        return completedQuantity;
    }

    public void setCompletedQuantity(String completedQuantity) {
        this.completedQuantity = completedQuantity;
    }

    /**
     * 已完成数量
     */
    private String completedQuantity;

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    private String gameUrl;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 用户id
     */
    private Long userId;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    //当前时间
    private Date startTime;

    public String getIsDaily() {
        return isDaily;
    }

    public void setIsDaily(String isDaily) {
        this.isDaily = isDaily;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setGameId(String gameId)
    {
        this.gameId = gameId;
    }

    public String getGameId()
    {
        return gameId;
    }
    public void setTypeCode(String typeCode)
    {
        this.typeCode = typeCode;
    }

    public String getTypeCode()
    {
        return typeCode;
    }
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public String getTaskName()
    {
        return taskName;
    }
    public void setIsRelease(String isRelease)
    {
        this.isRelease = isRelease;
    }

    public String getIsRelease()
    {
        return isRelease;
    }
    public void setReward(Double reward)
    {
        this.reward = reward;
    }

    public Double getReward()
    {
        return reward;
    }
    public void setRewardType(String rewardType)
    {
        this.rewardType = rewardType;
    }

    public String getRewardType()
    {
        return rewardType;
    }
    public void setTaskCount(Long taskCount)
    {
        this.taskCount = taskCount;
    }

    public Long getTaskCount()
    {
        return taskCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("gameId", getGameId())
            .append("typeCode", getTypeCode())
            .append("taskName", getTaskName())
            .append("isRelease", getIsRelease())
            .append("reward", getReward())
            .append("rewardType", getRewardType())
            .append("taskCount", getTaskCount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
