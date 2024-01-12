package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.InviteRewardsRecord;

/**
 * 邀请奖励记录Service接口
 * 
 * @author cjf
 * @date 2023-10-09
 */
public interface IInviteRewardsRecordService 
{
    /**
     * 查询邀请奖励记录
     * 
     * @param id 邀请奖励记录主键
     * @return 邀请奖励记录
     */
    public InviteRewardsRecord selectInviteRewardsRecordById(Long id);

    /**
     * 查询邀请奖励记录列表
     * 
     * @param inviteRewardsRecord 邀请奖励记录
     * @return 邀请奖励记录集合
     */
    public List<InviteRewardsRecord> selectInviteRewardsRecordList(InviteRewardsRecord inviteRewardsRecord);

    /**
     * 新增邀请奖励记录
     * 
     * @param inviteRewardsRecord 邀请奖励记录
     * @return 结果
     */
    public int insertInviteRewardsRecord(InviteRewardsRecord inviteRewardsRecord);

    /**
     * 修改邀请奖励记录
     * 
     * @param inviteRewardsRecord 邀请奖励记录
     * @return 结果
     */
    public int updateInviteRewardsRecord(InviteRewardsRecord inviteRewardsRecord);

    /**
     * 批量删除邀请奖励记录
     * 
     * @param ids 需要删除的邀请奖励记录主键集合
     * @return 结果
     */
    public int deleteInviteRewardsRecordByIds(Long[] ids);

    /**
     * 删除邀请奖励记录信息
     * 
     * @param id 邀请奖励记录主键
     * @return 结果
     */
    public int deleteInviteRewardsRecordById(Long id);


    /**
     * 今日收益
     * */
    public Double todayEarnings(Long userId);

}
