package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.InviteRewards;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户邀请奖励Service接口
 *
 * @author cjf
 * @date 2023-10-09
 */
public interface IInviteRewardsService
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
     * 批量删除用户邀请奖励
     *
     * @param ids 需要删除的用户邀请奖励主键集合
     * @return 结果
     */
    public int deleteInviteRewardsByIds(Long[] ids);

    /**
     * 删除用户邀请奖励信息
     *
     * @param id 用户邀请奖励主键
     * @return 结果
     */
    public int deleteInviteRewardsById(Long id);

    /**
     * 根据用户id查询
     * */
    public InviteRewards selectByUserId(Long userId);

}
