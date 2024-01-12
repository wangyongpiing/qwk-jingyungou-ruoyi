package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * vip等级对象 vip_level
 * 
 * @author cjf
 * @date 2023-09-13
 */
public class VipLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 等级名称 */
    @Excel(name = "等级名称")
    private String name;

    /** 等级 */
    @Excel(name = "等级")
    private Long level;

    /** 升级所需经验 */
    @Excel(name = "升级所需经验")
    private Long experience;

    /** 等级图标 */
    @Excel(name = "等级图标")
    private String icon;

    /** 等级背景图 */
    @Excel(name = "等级背景图")
    private String backgroundUrl;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setBackgroundUrl(String backgroundUrl) 
    {
        this.backgroundUrl = backgroundUrl;
    }

    public String getBackgroundUrl() 
    {
        return backgroundUrl;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("level", getLevel())
            .append("experience", getExperience())
            .append("icon", getIcon())
            .append("backgroundUrl", getBackgroundUrl())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
