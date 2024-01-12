package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 捕鱼怪物对象 fishing_monster
 * 
 * @author cjf
 * @date 2023-09-14
 */
public class FishingMonster extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 怪物名称 */
    @Excel(name = "怪物名称")
    private String nanme;

    /** 怪物血量 */
    @Excel(name = "怪物血量")
    private Long hp;

    /** 掉落组编号 */
    @Excel(name = "掉落组编号")
    private String fallId;

    /** 价格最高档 */
    @Excel(name = "价格最高档")
    private String tall;

    /** 价格最低档 */
    @Excel(name = "价格最低档")
    private String low;

    /** 低级场权重 */
    @Excel(name = "低级场权重")
    private Double weightLow;

    /** 中级场权重 */
    @Excel(name = "中级场权重")
    private Double weightCentre;

    /** 高级场权重 */
    @Excel(name = "高级场权重")
    private Double weightTall;

    /** 游行方式 */
    @Excel(name = "游行方式")
    private Long paradeMode;

    /** 游行次数 */
    @Excel(name = "游行次数")
    private Long paradesNumber;

    /** 模型动画 */
    @Excel(name = "模型动画")
    private String modelAnimation;

    /** 死亡动画 */
    @Excel(name = "死亡动画")
    private String deathAnimation;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNanme(String nanme) 
    {
        this.nanme = nanme;
    }

    public String getNanme() 
    {
        return nanme;
    }
    public void setHp(Long hp) 
    {
        this.hp = hp;
    }

    public Long getHp() 
    {
        return hp;
    }
    public void setFallId(String fallId) 
    {
        this.fallId = fallId;
    }

    public String getFallId() 
    {
        return fallId;
    }
    public void setTall(String tall)
    {
        this.tall = tall;
    }

    public String getTall()
    {
        return tall;
    }
    public void setLow(String low)
    {
        this.low = low;
    }

    public String getLow()
    {
        return low;
    }
    public void setWeightLow(Double weightLow)
    {
        this.weightLow = weightLow;
    }

    public Double getWeightLow()
    {
        return weightLow;
    }
    public void setWeightCentre(Double weightCentre)
    {
        this.weightCentre = weightCentre;
    }

    public Double getWeightCentre()
    {
        return weightCentre;
    }
    public void setWeightTall(Double weightTall)
    {
        this.weightTall = weightTall;
    }

    public Double getWeightTall()
    {
        return weightTall;
    }
    public void setParadeMode(Long paradeMode) 
    {
        this.paradeMode = paradeMode;
    }

    public Long getParadeMode() 
    {
        return paradeMode;
    }
    public void setParadesNumber(Long paradesNumber) 
    {
        this.paradesNumber = paradesNumber;
    }

    public Long getParadesNumber() 
    {
        return paradesNumber;
    }
    public void setModelAnimation(String modelAnimation) 
    {
        this.modelAnimation = modelAnimation;
    }

    public String getModelAnimation() 
    {
        return modelAnimation;
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
            .append("nanme", getNanme())
            .append("hp", getHp())
            .append("fallId", getFallId())
            .append("tall", getTall())
            .append("low", getLow())
            .append("weightLow", getWeightLow())
            .append("weightCentre", getWeightCentre())
            .append("weightTall", getWeightTall())
            .append("paradeMode", getParadeMode())
            .append("paradesNumber", getParadesNumber())
            .append("modelAnimation", getModelAnimation())
            .append("deathAnimation", getDeathAnimation())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
