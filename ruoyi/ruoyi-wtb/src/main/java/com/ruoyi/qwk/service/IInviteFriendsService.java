package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.InviteFriends;

/**
 * 邀请好友Service接口
 *
 * @author cjf
 * @date 2023-09-26
 */
public interface IInviteFriendsService
{
    /**
     * 查询邀请好友
     *
     * @param id 邀请好友主键
     * @return 邀请好友
     */
    public InviteFriends selectInviteFriendsById(Long id);

    /**
     * 查询邀请好友列表
     *
     * @param inviteFriends 邀请好友
     * @return 邀请好友集合
     */
    public List<InviteFriends> selectInviteFriendsList(InviteFriends inviteFriends);

    /**
     * 新增邀请好友
     *
     * @param inviteFriends 邀请好友
     * @return 结果
     */
    public int insertInviteFriends(InviteFriends inviteFriends);

    /**
     * 修改邀请好友
     *
     * @param inviteFriends 邀请好友
     * @return 结果
     */
    public int updateInviteFriends(InviteFriends inviteFriends);

    /**
     * 批量删除邀请好友
     *
     * @param ids 需要删除的邀请好友主键集合
     * @return 结果
     */
    public int deleteInviteFriendsByIds(Long[] ids);

    /**
     * 删除邀请好友信息
     *
     * @param id 邀请好友主键
     * @return 结果
     */
    public int deleteInviteFriendsById(Long id);

    /**
     * 根据ip查询数据
     *
     * @param ip
     * @return 结果
     */
    public List<InviteFriends> selectInviteFriendsByIp(String ip);

    /**
     *
     * 删除5分钟之前的数据
     * */
    public int deleteByCreateTime();

}
