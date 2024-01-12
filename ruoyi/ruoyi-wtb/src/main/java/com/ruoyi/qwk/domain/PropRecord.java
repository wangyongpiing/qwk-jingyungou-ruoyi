package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 道具产出记录对象 prop_record
 * 
 * @author cjf
 * @date 2023-07-31
 */
public class PropRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 游戏id */
    @Excel(name = "游戏id")
    private Long gameId;

    /** 关卡 */
    @Excel(name = "关卡")
    private Long gameLevel;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 道具编号 */
    @Excel(name = "道具编号")
    private Long propId;

    /** 产出数量 */
    @Excel(name = "产出数量")
    private Long propNum;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGameId(Long gameId) 
    {
        this.gameId = gameId;
    }

    public Long getGameId() 
    {
        return gameId;
    }
    public void setGameLevel(Long gameLevel) 
    {
        this.gameLevel = gameLevel;
    }

    public Long getGameLevel() 
    {
        return gameLevel;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setPropId(Long propId) 
    {
        this.propId = propId;
    }

    public Long getPropId() 
    {
        return propId;
    }
    public void setPropNum(Long propNum) 
    {
        this.propNum = propNum;
    }

    public Long getPropNum() 
    {
        return propNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("gameId", getGameId())
            .append("gameLevel", getGameLevel())
            .append("userId", getUserId())
            .append("propId", getPropId())
            .append("propNum", getPropNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
