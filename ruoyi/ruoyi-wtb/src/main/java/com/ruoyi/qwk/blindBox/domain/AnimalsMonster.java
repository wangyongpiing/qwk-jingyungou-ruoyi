package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 怪物配置对象 animals_monster
 * 
 * @author cjf
 * @date 2023-11-08
 */
public class AnimalsMonster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 怪物编号 */
    private Long id;

    /** 怪物名称 */
    @Excel(name = "怪物名称")
    private String name;

    /** 怪物攻击 */
    @Excel(name = "怪物攻击")
    private String attack;

    /** 怪物生命 */
    @Excel(name = "怪物生命")
    private String hp;

    /** 怪物移动速度 */
    @Excel(name = "怪物移动速度")
    private String speed;

    /** 怪物掉落 */
    @Excel(name = "怪物掉落")
    private String fall;

    /** 怪物技能 */
    @Excel(name = "怪物技能")
    private String skill;

    /** 怪物荣誉值 */
    @Excel(name = "怪物荣誉值")
    private String honor;

    /** 怪物头像 */
    @Excel(name = "怪物头像")
    private String avatar;

    /** 怪物模型 */
    @Excel(name = "怪物模型")
    private String model;

    /** 怪物模型尺寸 */
    @Excel(name = "怪物模型尺寸")
    private String size;

    /** 怪物受击动画 */
    @Excel(name = "怪物受击动画")
    private String hitAnimation;

    /** 怪物受击特效 */
    @Excel(name = "怪物受击特效")
    private String hitSpecial;

    /** 怪物受击音效 */
    @Excel(name = "怪物受击音效")
    private String hitSound;

    /** 怪物死亡动画 */
    @Excel(name = "怪物死亡动画")
    private String deathAnimation;

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
    public void setAttack(String attack) 
    {
        this.attack = attack;
    }

    public String getAttack() 
    {
        return attack;
    }
    public void setHp(String hp) 
    {
        this.hp = hp;
    }

    public String getHp() 
    {
        return hp;
    }
    public void setSpeed(String speed) 
    {
        this.speed = speed;
    }

    public String getSpeed() 
    {
        return speed;
    }
    public void setFall(String fall) 
    {
        this.fall = fall;
    }

    public String getFall() 
    {
        return fall;
    }
    public void setSkill(String skill) 
    {
        this.skill = skill;
    }

    public String getSkill() 
    {
        return skill;
    }
    public void setHonor(String honor) 
    {
        this.honor = honor;
    }

    public String getHonor() 
    {
        return honor;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setSize(String size) 
    {
        this.size = size;
    }

    public String getSize() 
    {
        return size;
    }
    public void setHitAnimation(String hitAnimation) 
    {
        this.hitAnimation = hitAnimation;
    }

    public String getHitAnimation() 
    {
        return hitAnimation;
    }
    public void setHitSpecial(String hitSpecial) 
    {
        this.hitSpecial = hitSpecial;
    }

    public String getHitSpecial() 
    {
        return hitSpecial;
    }
    public void setHitSound(String hitSound) 
    {
        this.hitSound = hitSound;
    }

    public String getHitSound() 
    {
        return hitSound;
    }
    public void setDeathAnimation(String deathAnimation) 
    {
        this.deathAnimation = deathAnimation;
    }

    public String getDeathAnimation() 
    {
        return deathAnimation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("attack", getAttack())
            .append("hp", getHp())
            .append("speed", getSpeed())
            .append("fall", getFall())
            .append("skill", getSkill())
            .append("honor", getHonor())
            .append("avatar", getAvatar())
            .append("model", getModel())
            .append("size", getSize())
            .append("hitAnimation", getHitAnimation())
            .append("hitSpecial", getHitSpecial())
            .append("hitSound", getHitSound())
            .append("deathAnimation", getDeathAnimation())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
