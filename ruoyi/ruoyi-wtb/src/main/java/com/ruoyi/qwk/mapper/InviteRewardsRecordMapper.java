package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.InviteRewardsRecord;

/**
 * 邀请奖励记录Mapper接口
 * 
 * @author cjf
 * @date 2023-10-09
 */
@DataSource(value = DataSourceType.SLAVE)
public interface InviteRewardsRecordMapper 
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
     * 删除邀请奖励记录
     * 
     * @param id 邀请奖励记录主键
     * @return 结果
     */
    public int deleteInviteRewardsRecordById(Long id);

    /**
     * 批量删除邀请奖励记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInviteRewardsRecordByIds(Long[] ids);

    /**
     * 查询今日收益
     * */
    public Double todayEarnings(Long userId);

}
