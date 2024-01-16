package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 大作战玩家血量记录对象 major_battle_player_health_record
 * 
 * @author cjf
 * @date 2024-01-15
 */
public class MajorBattlePlayerHealthRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 玩家id */
    @Excel(name = "玩家id")
    private String userId;

    /** 当前血量 */
    @Excel(name = "当前血量")
    private String totalBloodVolume;

    /** 扣减血量 */
    @Excel(name = "扣减血量")
    private String reduceHp;

    /** 剩余血量 */
    @Excel(name = "剩余血量")
    private String remainingHp;

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
    public void setTotalBloodVolume(String totalBloodVolume) 
    {
        this.totalBloodVolume = totalBloodVolume;
    }

    public String getTotalBloodVolume() 
    {
        return totalBloodVolume;
    }
    public void setReduceHp(String reduceHp) 
    {
        this.reduceHp = reduceHp;
    }

    public String getReduceHp() 
    {
        return reduceHp;
    }
    public void setRemainingHp(String remainingHp) 
    {
        this.remainingHp = remainingHp;
    }

    public String getRemainingHp() 
    {
        return remainingHp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("totalBloodVolume", getTotalBloodVolume())
            .append("reduceHp", getReduceHp())
            .append("remainingHp", getRemainingHp())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
