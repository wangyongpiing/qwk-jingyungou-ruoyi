package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 奖励包对象 reward_package
 * 
 * @author cjf
 * @date 2023-09-14
 */
public class RewardPackage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 奖励包名称 */
    @Excel(name = "奖励包名称")
    private String packageName;

    /** 奖励id */
    @Excel(name = "奖励id")
    private String rewardId;

    /** 权重 */
    @Excel(name = "权重")
    private Long weight;

    /** 掉落组稀有程度 */
    @Excel(name = "掉落组稀有程度")
    private String rarityLevel;

    /**奖励集合*/
    private List<LootReward> lootRewards;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPackageName(String packageName) 
    {
        this.packageName = packageName;
    }

    public String getPackageName() 
    {
        return packageName;
    }
    public void setRewardId(String rewardId) 
    {
        this.rewardId = rewardId;
    }

    public String getRewardId() 
    {
        return rewardId;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }

    public List<LootReward> getLootRewards() {
        return lootRewards;
    }

    public void setLootRewards(List<LootReward> lootRewards) {
        this.lootRewards = lootRewards;
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
            .append("packageName", getPackageName())
            .append("rewardId", getRewardId())
            .append("weight", getWeight())
            .append("rarityLevel", getRarityLevel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("lootRewards",getLootRewards())
            .toString();
    }
}
