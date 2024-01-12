package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.LootRewardMapper;
import com.ruoyi.qwk.blindBox.domain.LootReward;
import com.ruoyi.qwk.blindBox.service.ILootRewardService;

/**
 * 掉落奖励Service业务层处理
 * 
 * @author cjf
 * @date 2023-09-14
 */
@Service
public class LootRewardServiceImpl implements ILootRewardService 
{
    @Autowired
    private LootRewardMapper lootRewardMapper;

    /**
     * 查询掉落奖励
     * 
     * @param id 掉落奖励主键
     * @return 掉落奖励
     */
    @Override
    public LootReward selectLootRewardById(Long id)
    {
        return lootRewardMapper.selectLootRewardById(id);
    }

    /**
     * 查询掉落奖励列表
     * 
     * @param lootReward 掉落奖励
     * @return 掉落奖励
     */
    @Override
    public List<LootReward> selectLootRewardList(LootReward lootReward)
    {
        return lootRewardMapper.selectLootRewardList(lootReward);
    }

    /**
     * 新增掉落奖励
     * 
     * @param lootReward 掉落奖励
     * @return 结果
     */
    @Override
    public int insertLootReward(LootReward lootReward)
    {
        lootReward.setCreateTime(DateUtils.getNowDate());
        return lootRewardMapper.insertLootReward(lootReward);
    }

    /**
     * 修改掉落奖励
     * 
     * @param lootReward 掉落奖励
     * @return 结果
     */
    @Override
    public int updateLootReward(LootReward lootReward)
    {
        lootReward.setUpdateTime(DateUtils.getNowDate());
        return lootRewardMapper.updateLootReward(lootReward);
    }

    /**
     * 批量删除掉落奖励
     * 
     * @param ids 需要删除的掉落奖励主键
     * @return 结果
     */
    @Override
    public int deleteLootRewardByIds(Long[] ids)
    {
        return lootRewardMapper.deleteLootRewardByIds(ids);
    }

    /**
     * 删除掉落奖励信息
     * 
     * @param id 掉落奖励主键
     * @return 结果
     */
    @Override
    public int deleteLootRewardById(Long id)
    {
        return lootRewardMapper.deleteLootRewardById(id);
    }
}
