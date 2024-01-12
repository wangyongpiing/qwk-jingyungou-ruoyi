package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 技能配置对象 animals_skill
 * 
 * @author cjf
 * @date 2023-11-08
 */
public class AnimalsSkill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 技能编号 */
    private Long id;

    /** 技能名称 */
    @Excel(name = "技能名称")
    private String name;

    /** 技能类型 */
    @Excel(name = "技能类型")
    private String type;

    /** 技能伤害 */
    @Excel(name = "技能伤害")
    private String harm;

    /** 技能效果 */
    @Excel(name = "技能效果")
    private String effect;

    /** 技能描述 */
    @Excel(name = "技能描述")
    private String describe;

    /** 技能特效 */
    @Excel(name = "技能特效")
    private String specialEffects;

    /** 技能动画 */
    @Excel(name = "技能动画")
    private String animation;

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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setHarm(String harm) 
    {
        this.harm = harm;
    }

    public String getHarm() 
    {
        return harm;
    }
    public void setEffect(String effect) 
    {
        this.effect = effect;
    }

    public String getEffect() 
    {
        return effect;
    }
    public void setDescribe(String describe) 
    {
        this.describe = describe;
    }

    public String getDescribe() 
    {
        return describe;
    }
    public void setSpecialEffects(String specialEffects) 
    {
        this.specialEffects = specialEffects;
    }

    public String getSpecialEffects() 
    {
        return specialEffects;
    }
    public void setAnimation(String animation) 
    {
        this.animation = animation;
    }

    public String getAnimation() 
    {
        return animation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("harm", getHarm())
            .append("effect", getEffect())
            .append("describe", getDescribe())
            .append("specialEffects", getSpecialEffects())
            .append("animation", getAnimation())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
