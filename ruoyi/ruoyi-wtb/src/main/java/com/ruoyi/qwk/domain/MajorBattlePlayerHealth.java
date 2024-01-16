package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 大作战玩家血量 对象 major_battle_player_health
 * 
 * @author cjf
 * @date 2024-01-15
 */
public class MajorBattlePlayerHealth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 玩家id */
    @Excel(name = "玩家id")
    private String userId;

    /** 玩家血量 */
    @Excel(name = "玩家血量")
    private String bloodVolume;

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
    public void setBloodVolume(String bloodVolume) 
    {
        this.bloodVolume = bloodVolume;
    }

    public String getBloodVolume() 
    {
        return bloodVolume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("bloodVolume", getBloodVolume())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
