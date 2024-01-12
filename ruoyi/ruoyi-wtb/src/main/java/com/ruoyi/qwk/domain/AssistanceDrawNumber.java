package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 *  助力抽奖次数对象 assistance_draw_number
 *
 * @author cjf
 * @date 2023-12-21
 */
public class AssistanceDrawNumber extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 助力人id */
    @Excel(name = "助力人id")
    private String assistanceId;

    /** 总抽奖次数 */
    @Excel(name = "总抽奖次数")
    private Long lotteryDrawSum;

    /** 未抽奖次数 */
    @Excel(name = "未抽奖次数")
    private Long noLotteryDrawNum;

    /** 虚抽次数 */
    @Excel(name = "虚抽次数")
    private Long vacuumingNum;

    /** 实抽次数 */
    @Excel(name = "实抽次数")
    private Long actualExtractionNum;

    /**
     * 是否存在第一次免费抽奖机会 1存在 0-不存在
     */
    private String isFree;

    public String getIsLotteryAllowed() {
        return isLotteryAllowed;
    }

    public void setIsLotteryAllowed(String isLotteryAllowed) {
        this.isLotteryAllowed = isLotteryAllowed;
    }

    /**
     * 是否允许抽奖
     */
    private String isLotteryAllowed;

    public Long getVacuumingNum() {
        return vacuumingNum;
    }

    public void setVacuumingNum(Long vacuumingNum) {
        this.vacuumingNum = vacuumingNum;
    }

    public Long getActualExtractionNum() {
        return actualExtractionNum;
    }

    public void setActualExtractionNum(Long actualExtractionNum) {
        this.actualExtractionNum = actualExtractionNum;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAssistanceId(String assistanceId)
    {
        this.assistanceId = assistanceId;
    }

    public String getAssistanceId()
    {
        return assistanceId;
    }
    public void setLotteryDrawSum(Long lotteryDrawSum)
    {
        this.lotteryDrawSum = lotteryDrawSum;
    }

    public Long getLotteryDrawSum()
    {
        return lotteryDrawSum;
    }
    public void setNoLotteryDrawNum(Long noLotteryDrawNum)
    {
        this.noLotteryDrawNum = noLotteryDrawNum;
    }

    public Long getNoLotteryDrawNum()
    {
        return noLotteryDrawNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assistanceId", getAssistanceId())
            .append("lotteryDrawSum", getLotteryDrawSum())
            .append("noLotteryDrawNum", getNoLotteryDrawNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
