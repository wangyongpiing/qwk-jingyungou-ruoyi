package com.ruoyi.qwk.blindBox.service;

import java.util.List;
import com.ruoyi.qwk.blindBox.domain.LootReward;

/**
 * 掉落奖励Service接口
 * 
 * @author cjf
 * @date 2023-09-14
 */
public interface ILootRewardService 
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
     * 批量删除掉落奖励
     * 
     * @param ids 需要删除的掉落奖励主键集合
     * @return 结果
     */
    public int deleteLootRewardByIds(Long[] ids);

    /**
     * 删除掉落奖励信息
     * 
     * @param id 掉落奖励主键
     * @return 结果
     */
    public int deleteLootRewardById(Long id);
}
