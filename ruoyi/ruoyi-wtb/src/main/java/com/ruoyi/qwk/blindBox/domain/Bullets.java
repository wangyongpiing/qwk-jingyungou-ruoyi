package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 子弹配置对象 bullets
 * 
 * @author cjf
 * @date 2023-09-14
 */
public class Bullets extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 子弹名称 */
    @Excel(name = "子弹名称")
    private String name;

    /** 子弹速度 */
    @Excel(name = "子弹速度")
    private Long speed;

    /** 场次编号 */
    @Excel(name = "场次编号")
    private Long placeId;

    /** 每发子弹的消耗 */
    @Excel(name = "每发子弹的消耗")
    private Long consume;

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
    public void setSpeed(Long speed) 
    {
        this.speed = speed;
    }

    public Long getSpeed() 
    {
        return speed;
    }
    public void setPlaceId(Long placeId) 
    {
        this.placeId = placeId;
    }

    public Long getPlaceId() 
    {
        return placeId;
    }
    public void setConsume(Long consume) 
    {
        this.consume = consume;
    }

    public Long getConsume() 
    {
        return consume;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("speed", getSpeed())
            .append("placeId", getPlaceId())
            .append("consume", getConsume())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
