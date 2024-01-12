package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 掉落配置对象 animals_drop
 * 
 * @author cjf
 * @date 2023-11-08
 */
public class AnimalsDrop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 掉落编号 */
    private Integer id;

    /** 服务器掉落数量 */
    @Excel(name = "服务器掉落数量")
    private Integer serverNum;

    /** 个人掉落数量 */
    @Excel(name = "个人掉落数量")
    private Integer personNum;

    /** 随机类型（概率or权重） */
    @Excel(name = "随机类型", readConverterExp = "概=率or权重")
    private String randomType;

    /** 掉落类型（物品or掉落包） */
    @Excel(name = "掉落类型", readConverterExp = "物=品or掉落包")
    private String dropType;

    /** 掉落内容（物品ID或掉落包ID） */
    @Excel(name = "掉落内容", readConverterExp = "物=品ID或掉落包ID")
    private String dropContent;

    /** 掉落概率 */
    @Excel(name = "掉落概率")
    private String dropProbability;

    /** 掉落数量 */
    @Excel(name = "掉落数量")
    private Integer dropNum;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setServerNum(Integer serverNum) 
    {
        this.serverNum = serverNum;
    }

    public Integer getServerNum() 
    {
        return serverNum;
    }
    public void setPersonNum(Integer personNum) 
    {
        this.personNum = personNum;
    }

    public Integer getPersonNum() 
    {
        return personNum;
    }
    public void setRandomType(String randomType) 
    {
        this.randomType = randomType;
    }

    public String getRandomType() 
    {
        return randomType;
    }
    public void setDropType(String dropType) 
    {
        this.dropType = dropType;
    }

    public String getDropType() 
    {
        return dropType;
    }
    public void setDropContent(String dropContent) 
    {
        this.dropContent = dropContent;
    }

    public String getDropContent() 
    {
        return dropContent;
    }
    public void setDropProbability(String dropProbability) 
    {
        this.dropProbability = dropProbability;
    }

    public String getDropProbability() 
    {
        return dropProbability;
    }
    public void setDropNum(Integer dropNum) 
    {
        this.dropNum = dropNum;
    }

    public Integer getDropNum() 
    {
        return dropNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("serverNum", getServerNum())
            .append("personNum", getPersonNum())
            .append("randomType", getRandomType())
            .append("dropType", getDropType())
            .append("dropContent", getDropContent())
            .append("dropProbability", getDropProbability())
            .append("dropNum", getDropNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
