package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 *  大作战怪物对象 big_battle_monster
 * 
 * @author cjf
 * @date 2024-01-15
 */
public class BigBattleMonster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 怪物id或编码 */
    @Excel(name = "怪物id或编码")
    private String code;

    /** 怪物生成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "怪物生成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date generationTime;

    /** 怪物死亡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "怪物死亡时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deathTime;

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
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setGenerationTime(Date generationTime) 
    {
        this.generationTime = generationTime;
    }

    public Date getGenerationTime() 
    {
        return generationTime;
    }
    public void setDeathTime(Date deathTime) 
    {
        this.deathTime = deathTime;
    }

    public Date getDeathTime() 
    {
        return deathTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("code", getCode())
            .append("generationTime", getGenerationTime())
            .append("deathTime", getDeathTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
