package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.LootReward;

/**
 * 掉落奖励Mapper接口
 * 
 * @author cjf
 * @date 2023-09-14
 */
@DataSource(value = DataSourceType.SLAVE)
public interface LootRewardMapper 
{
    /**
     * 查询掉落奖励
     * 
     * @param id 掉落奖励主键
     * @return 掉落奖励
     */
    public LootReward selectLootRewardById(Long id);

    /**
     * 查询掉落奖励列表
     * 
     * @param lootReward 掉落奖励
     * @return 掉落奖励集合
     */
    public List<LootReward> selectLootRewardList(LootReward lootReward);

    /**
     * 新增掉落奖励
     * 
     * @param lootReward 掉落奖励
     * @return 结果
     */
    public int insertLootReward(LootReward lootReward);

    /**
     * 修改掉落奖励
     * 
     * @param lootReward 掉落奖励
     * @return 结果
     */
    public int updateLootReward(LootReward lootReward);

    /**
     * 删除掉落奖励
     * 
     * @param id 掉落奖励主键
     * @return 结果
     */
    public int deleteLootRewardById(Long id);

    /**
     * 批量删除掉落奖励
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLootRewardByIds(Long[] ids);
}
