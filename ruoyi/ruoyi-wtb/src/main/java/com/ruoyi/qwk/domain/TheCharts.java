package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 游戏排行榜对象 the_charts
 * 
 * @author cjf
 * @date 2023-07-17
 */
public class TheCharts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nikeName;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String avatar;

    /** 游戏id */
    @Excel(name = "游戏id")
    private Long gameId;

    /** 战力值 */
    @Excel(name = "战力值")
    private String combatPower;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setNikeName(String nikeName) 
    {
        this.nikeName = nikeName;
    }

    public String getNikeName() 
    {
        return nikeName;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setGameId(Long gameId) 
    {
        this.gameId = gameId;
    }

    public Long getGameId() 
    {
        return gameId;
    }
    public void setCombatPower(String combatPower) 
    {
        this.combatPower = combatPower;
    }

    public String getCombatPower() 
    {
        return combatPower;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("nikeName", getNikeName())
            .append("avatar", getAvatar())
            .append("gameId", getGameId())
            .append("combatPower", getCombatPower())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
