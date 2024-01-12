package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.InviteRewards;

/**
 * 用户邀请奖励Mapper接口
 * 
 * @author cjf
 * @date 2023-10-09
 */
@DataSource(value = DataSourceType.SLAVE)
public interface InviteRewardsMapper 
{
    /**
     * 查询用户邀请奖励
     * 
     * @param id 用户邀请奖励主键
     * @return 用户邀请奖励
     */
    public InviteRewards selectInviteRewardsById(Long id);

    /**
     * 查询用户邀请奖励列表
     * 
     * @param inviteRewards 用户邀请奖励
     * @return 用户邀请奖励集合
     */
    public List<InviteRewards> selectInviteRewardsList(InviteRewards inviteRewards);

    /**
     * 新增用户邀请奖励
     * 
     * @param inviteRewards 用户邀请奖励
     * @return 结果
     */
    public int insertInviteRewards(InviteRewards inviteRewards);

    /**
     * 修改用户邀请奖励
     * 
     * @param inviteRewards 用户邀请奖励
     * @return 结果
     */
    public int updateInviteRewards(InviteRewards inviteRewards);

    /**
     * 删除用户邀请奖励
     * 
     * @param id 用户邀请奖励主键
     * @return 结果
     */
    public int deleteInviteRewardsById(Long id);

    /**
     * 批量删除用户邀请奖励
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInviteRewardsByIds(Long[] ids);

    /**
     * 根据用户id查询
     * */
    public InviteRewards selectByUserId(Long userId);

}
