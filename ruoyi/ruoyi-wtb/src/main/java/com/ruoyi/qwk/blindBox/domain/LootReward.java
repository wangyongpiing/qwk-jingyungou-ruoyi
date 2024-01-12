package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 掉落奖励对象 loot_reward
 * 
 * @author cjf
 * @date 2023-09-14
 */
public class LootReward extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 掉落奖励名称 */
    @Excel(name = "掉落奖励名称")
    private String name;

    /** 掉落物品编号 */
    @Excel(name = "掉落物品编号")
    private Long goodsId;

    /** 掉落物品权重 */
    @Excel(name = "掉落物品权重")
    private Integer dropRewardWeight;

    /** 掉落物品稀有程度 */
    @Excel(name = "掉落物品稀有程度")
    private String rarityLevel;

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
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setDropRewardWeight(Integer dropRewardWeight) 
    {
        this.dropRewardWeight = dropRewardWeight;
    }

    public Integer getDropRewardWeight() 
    {
        return dropRewardWeight;
    }
    public void setRarityLevel(String rarityLevel)
    {
        this.rarityLevel = rarityLevel;
    }

    public String getRarityLevel()
    {
        return rarityLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("goodsId", getGoodsId())
            .append("dropRewardWeight", getDropRewardWeight())
            .append("rarityLevel", getRarityLevel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
