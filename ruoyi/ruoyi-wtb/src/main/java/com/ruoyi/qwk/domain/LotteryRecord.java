package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抽奖记录 对象 lottery_record
 * 
 * @author cjf
 * @date 2023-12-21
 */
public class LotteryRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 游戏id */
    @Excel(name = "游戏id")
    private String gameId;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 抽奖时间 */
    @Excel(name = "抽奖时间")
    private String drawTime;

    /** 是否中奖 0：未中奖 1：中奖 */
    @Excel(name = "是否中奖 0：未中奖 1：中奖")
    private String isHit;

    /** 奖品类型：3-红包 */
    @Excel(name = "奖品类型：3-红包")
    private String type;

    /** 中奖奖品（type:3转盘红包id） */
    @Excel(name = "中奖奖品", readConverterExp = "t=ype:3转盘红包id")
    private String hitPrize;

    /** 是否发放 1未发放，2 已发放 3 发放失败 */
    @Excel(name = "是否发放 1未发放，2 已发放 3 发放失败")
    private String isSend;

    /** 发放结果 */
    @Excel(name = "发放结果")
    private String sendMsg;

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
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setDrawTime(String drawTime) 
    {
        this.drawTime = drawTime;
    }

    public String getDrawTime() 
    {
        return drawTime;
    }
    public void setIsHit(String isHit) 
    {
        this.isHit = isHit;
    }

    public String getIsHit() 
    {
        return isHit;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setHitPrize(String hitPrize) 
    {
        this.hitPrize = hitPrize;
    }

    public String getHitPrize() 
    {
        return hitPrize;
    }
    public void setIsSend(String isSend) 
    {
        this.isSend = isSend;
    }

    public String getIsSend() 
    {
        return isSend;
    }
    public void setSendMsg(String sendMsg) 
    {
        this.sendMsg = sendMsg;
    }

    public String getSendMsg() 
    {
        return sendMsg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("gameId", getGameId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("drawTime", getDrawTime())
            .append("isHit", getIsHit())
            .append("type", getType())
            .append("hitPrize", getHitPrize())
            .append("isSend", getIsSend())
            .append("sendMsg", getSendMsg())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
