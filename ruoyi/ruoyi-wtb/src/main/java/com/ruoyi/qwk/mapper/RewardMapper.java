package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.Reward;

/**
 * 游戏奖励Mapper接口
 * 
 * @author cjf
 * @date 2023-07-17
 */
@DataSource(value = DataSourceType.SLAVE)
public interface RewardMapper 
{
    /**
     * 查询游戏奖励
     * 
     * @param id 游戏奖励主键
     * @return 游戏奖励
     */
    public Reward selectRewardById(Long id);

    /**
     * 查询游戏奖励列表
     * 
     * @param reward 游戏奖励
     * @return 游戏奖励集合
     */
    public List<Reward> selectRewardList(Reward reward);

    /**
     * 新增游戏奖励
     * 
     * @param reward 游戏奖励
     * @return 结果
     */
    public int insertReward(Reward reward);

    /**
     * 修改游戏奖励
     * 
     * @param reward 游戏奖励
     * @return 结果
     */
    public int updateReward(Reward reward);

    /**
     * 删除游戏奖励
     * 
     * @param id 游戏奖励主键
     * @return 结果
     */
    public int deleteRewardById(Long id);

    /**
     * 批量删除游戏奖励
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRewardByIds(Long[] ids);
}
