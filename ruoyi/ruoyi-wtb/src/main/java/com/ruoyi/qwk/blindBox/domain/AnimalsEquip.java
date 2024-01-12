package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 装备配置对象 animals_equip
 * 
 * @author cjf
 * @date 2023-11-07
 */
public class AnimalsEquip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 装备编号 */
    private Long id;

    /** 装备名称 */
    @Excel(name = "装备名称")
    private String name;

    /** 装备部位 */
    @Excel(name = "装备部位")
    private String position;

    /** 装备等级 */
    @Excel(name = "装备等级")
    private Long rank;

    /** 装备攻击 */
    @Excel(name = "装备攻击")
    private String attack;

    /** 装备生命 */
    @Excel(name = "装备生命")
    private String life;

    /** 装备耐久度 */
    @Excel(name = "装备耐久度")
    private String lasting;

    /** 装备技能 */
    @Excel(name = "装备技能")
    private String skill;

    /** 装备icon */
    @Excel(name = "装备icon")
    private String icon;

    /** 装备外显 */
    @Excel(name = "装备外显")
    private String overt;

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
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setRank(Long rank) 
    {
        this.rank = rank;
    }

    public Long getRank() 
    {
        return rank;
    }
    public void setAttack(String attack) 
    {
        this.attack = attack;
    }

    public String getAttack() 
    {
        return attack;
    }
    public void setLife(String life) 
    {
        this.life = life;
    }

    public String getLife() 
    {
        return life;
    }
    public void setLasting(String lasting) 
    {
        this.lasting = lasting;
    }

    public String getLasting() 
    {
        return lasting;
    }
    public void setSkill(String skill) 
    {
        this.skill = skill;
    }

    public String getSkill() 
    {
        return skill;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setOvert(String overt) 
    {
        this.overt = overt;
    }

    public String getOvert() 
    {
        return overt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("position", getPosition())
            .append("rank", getRank())
            .append("attack", getAttack())
            .append("life", getLife())
            .append("lasting", getLasting())
            .append("skill", getSkill())
            .append("icon", getIcon())
            .append("overt", getOvert())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
