package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.RewardMapper;
import com.ruoyi.qwk.domain.Reward;
import com.ruoyi.qwk.service.IRewardService;

/**
 * 游戏奖励Service业务层处理
 * 
 * @author cjf
 * @date 2023-07-17
 */
@Service
public class RewardServiceImpl implements IRewardService 
{
    @Autowired
    private RewardMapper rewardMapper;

    /**
     * 查询游戏奖励
     * 
     * @param id 游戏奖励主键
     * @return 游戏奖励
     */
    @Override
    public Reward selectRewardById(Long id)
    {
        return rewardMapper.selectRewardById(id);
    }

    /**
     * 查询游戏奖励列表
     * 
     * @param reward 游戏奖励
     * @return 游戏奖励
     */
    @Override
    public List<Reward> selectRewardList(Reward reward)
    {
        return rewardMapper.selectRewardList(reward);
    }

    /**
     * 新增游戏奖励
     * 
     * @param reward 游戏奖励
     * @return 结果
     */
    @Override
    public int insertReward(Reward reward)
    {
        reward.setCreateTime(DateUtils.getNowDate());
        return rewardMapper.insertReward(reward);
    }

    /**
     * 修改游戏奖励
     * 
     * @param reward 游戏奖励
     * @return 结果
     */
    @Override
    public int updateReward(Reward reward)
    {
        reward.setUpdateTime(DateUtils.getNowDate());
        return rewardMapper.updateReward(reward);
    }

    /**
     * 批量删除游戏奖励
     * 
     * @param ids 需要删除的游戏奖励主键
     * @return 结果
     */
    @Override
    public int deleteRewardByIds(Long[] ids)
    {
        return rewardMapper.deleteRewardByIds(ids);
    }

    /**
     * 删除游戏奖励信息
     * 
     * @param id 游戏奖励主键
     * @return 结果
     */
    @Override
    public int deleteRewardById(Long id)
    {
        return rewardMapper.deleteRewardById(id);
    }
}
