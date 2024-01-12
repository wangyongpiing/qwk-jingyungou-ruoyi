package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户vip等级记录对象 user_vip_level
 * 
 * @author cjf
 * @date 2023-09-13
 */
public class UserVipLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 等级编号 */
    @Excel(name = "等级编号")
    private Long levelId;

    /** vip等级 */
    @Excel(name = "vip等级")
    private Long level;

    /** 升级所需经验 */
    @Excel(name = "升级所需经验")
    private Long experience;

    /** 用户此时的经验 */
    @Excel(name = "用户此时的经验")
    private Long userExperience;

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
    public void setLevelId(Long levelId) 
    {
        this.levelId = levelId;
    }

    public Long getLevelId() 
    {
        return levelId;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setExperience(Long experience) 
    {
        this.experience = experience;
    }

    public Long getExperience() 
    {
        return experience;
    }
    public void setUserExperience(Long userExperience) 
    {
        this.userExperience = userExperience;
    }

    public Long getUserExperience() 
    {
        return userExperience;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("levelId", getLevelId())
            .append("level", getLevel())
            .append("experience", getExperience())
            .append("userExperience", getUserExperience())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
