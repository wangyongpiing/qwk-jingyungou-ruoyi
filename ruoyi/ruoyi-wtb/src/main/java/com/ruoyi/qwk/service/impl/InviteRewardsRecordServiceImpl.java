package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.InviteRewardsRecordMapper;
import com.ruoyi.qwk.domain.InviteRewardsRecord;
import com.ruoyi.qwk.service.IInviteRewardsRecordService;

/**
 * 邀请奖励记录Service业务层处理
 * 
 * @author cjf
 * @date 2023-10-09
 */
@Service
public class InviteRewardsRecordServiceImpl implements IInviteRewardsRecordService 
{
    @Autowired
    private InviteRewardsRecordMapper inviteRewardsRecordMapper;

    /**
     * 查询邀请奖励记录
     * 
     * @param id 邀请奖励记录主键
     * @return 邀请奖励记录
     */
    @Override
    public InviteRewardsRecord selectInviteRewardsRecordById(Long id)
    {
        return inviteRewardsRecordMapper.selectInviteRewardsRecordById(id);
    }

    /**
     * 查询邀请奖励记录列表
     * 
     * @param inviteRewardsRecord 邀请奖励记录
     * @return 邀请奖励记录
     */
    @Override
    public List<InviteRewardsRecord> selectInviteRewardsRecordList(InviteRewardsRecord inviteRewardsRecord)
    {
        return inviteRewardsRecordMapper.selectInviteRewardsRecordList(inviteRewardsRecord);
    }

    /**
     * 新增邀请奖励记录
     * 
     * @param inviteRewardsRecord 邀请奖励记录
     * @return 结果
     */
    @Override
    public int insertInviteRewardsRecord(InviteRewardsRecord inviteRewardsRecord)
    {
        inviteRewardsRecord.setCreateTime(DateUtils.getNowDate());
        return inviteRewardsRecordMapper.insertInviteRewardsRecord(inviteRewardsRecord);
    }

    /**
     * 修改邀请奖励记录
     * 
     * @param inviteRewardsRecord 邀请奖励记录
     * @return 结果
     */
    @Override
    public int updateInviteRewardsRecord(InviteRewardsRecord inviteRewardsRecord)
    {
        inviteRewardsRecord.setUpdateTime(DateUtils.getNowDate());
        return inviteRewardsRecordMapper.updateInviteRewardsRecord(inviteRewardsRecord);
    }

    /**
     * 批量删除邀请奖励记录
     * 
     * @param ids 需要删除的邀请奖励记录主键
     * @return 结果
     */
    @Override
    public int deleteInviteRewardsRecordByIds(Long[] ids)
    {
        return inviteRewardsRecordMapper.deleteInviteRewardsRecordByIds(ids);
    }

    /**
     * 删除邀请奖励记录信息
     * 
     * @param id 邀请奖励记录主键
     * @return 结果
     */
    @Override
    public int deleteInviteRewardsRecordById(Long id)
    {
        return inviteRewardsRecordMapper.deleteInviteRewardsRecordById(id);
    }

    /**
     * 今日收益
     * */
    @Override
    public Double todayEarnings(Long userId){
        return inviteRewardsRecordMapper.todayEarnings(userId);
    }

}
