package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.InviteFriendsMapper;
import com.ruoyi.qwk.domain.InviteFriends;
import com.ruoyi.qwk.service.IInviteFriendsService;

/**
 * 邀请好友Service业务层处理
 *
 * @author cjf
 * @date 2023-09-26
 */
@Service
public class InviteFriendsServiceImpl implements IInviteFriendsService
{
    @Autowired
    private InviteFriendsMapper inviteFriendsMapper;

    /**
     * 查询邀请好友
     *
     * @param id 邀请好友主键
     * @return 邀请好友
     */
    @Override
    public InviteFriends selectInviteFriendsById(Long id)
    {
        return inviteFriendsMapper.selectInviteFriendsById(id);
    }

    /**
     * 查询邀请好友列表
     *
     * @param inviteFriends 邀请好友
     * @return 邀请好友
     */
    @Override
    public List<InviteFriends> selectInviteFriendsList(InviteFriends inviteFriends)
    {
        return inviteFriendsMapper.selectInviteFriendsList(inviteFriends);
    }

    /**
     * 新增邀请好友
     *
     * @param inviteFriends 邀请好友
     * @return 结果
     */
    @Override
    public int insertInviteFriends(InviteFriends inviteFriends)
    {
        return inviteFriendsMapper.insertInviteFriends(inviteFriends);
    }

    /**
     * 修改邀请好友
     *
     * @param inviteFriends 邀请好友
     * @return 结果
     */
    @Override
    public int updateInviteFriends(InviteFriends inviteFriends)
    {
        inviteFriends.setUpdateTime(DateUtils.getNowDate());
        return inviteFriendsMapper.updateInviteFriends(inviteFriends);
    }

    /**
     * 批量删除邀请好友
     *
     * @param ids 需要删除的邀请好友主键
     * @return 结果
     */
    @Override
    public int deleteInviteFriendsByIds(Long[] ids)
    {
        return inviteFriendsMapper.deleteInviteFriendsByIds(ids);
    }

    /**
     * 删除邀请好友信息
     *
     * @param id 邀请好友主键
     * @return 结果
     */
    @Override
    public int deleteInviteFriendsById(Long id)
    {
        return inviteFriendsMapper.deleteInviteFriendsById(id);
    }


    /**
     * 根据ip查询数据
     *
     * @param ip
     * @return 结果
     */
    @Override
    public List<InviteFriends> selectInviteFriendsByIp(String ip){
        return inviteFriendsMapper.selectInviteFriendsByIp(ip);
    }

    /**
     *
     * 删除5分钟之前的数据
     * */
    @Override
    public int deleteByCreateTime(){
        System.out.println(DateUtils.getTime()+"删除用户邀请定时任务执行");
        return inviteFriendsMapper.deleteByCreateTime();
    }

}
