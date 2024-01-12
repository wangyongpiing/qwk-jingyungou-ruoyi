package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.UserFriends;
import com.ruoyi.system.mapper.UserFriendsMapper;
import com.ruoyi.system.service.IUserFriendsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户好友 Service业务层处理
 * 
 * @author cjf
 * @date 2023-06-27
 */
@Service
public class UserFriendsServiceImpl implements IUserFriendsService
{
    @Autowired
    private UserFriendsMapper userFriendsMapper;

    /**
     * 查询用户好友 
     * 
     * @param userId 用户好友 主键
     * @return 用户好友 
     */
    @Override
    public UserFriends selectUserFriendsByUserId(Long userId)
    {
        return userFriendsMapper.selectUserFriendsByUserId(userId);
    }

    /**
     * 查询用户好友 列表
     * 
     * @param userFriends 用户好友 
     * @return 用户好友 
     */
    @Override
    public List<UserFriends> selectUserFriendsList(UserFriends userFriends)
    {
        return userFriendsMapper.selectUserFriendsList(userFriends);
    }

    /**
     * 新增用户好友 
     * 
     * @param userFriends 用户好友 
     * @return 结果
     */
    @Override
    public int insertUserFriends(UserFriends userFriends)
    {
        userFriends.setCreateTime(DateUtils.getNowDate());
        return userFriendsMapper.insertUserFriends(userFriends);
    }

    /**
     * 修改用户好友 
     * 
     * @param userFriends 用户好友 
     * @return 结果
     */
    @Override
    public int updateUserFriends(UserFriends userFriends)
    {
        return userFriendsMapper.updateUserFriends(userFriends);
    }

    /**
     * 批量删除用户好友 
     * 
     * @param userIds 需要删除的用户好友 主键
     * @return 结果
     */
    @Override
    public int deleteUserFriendsByUserIds(Long[] userIds)
    {
        return userFriendsMapper.deleteUserFriendsByUserIds(userIds);
    }

    /**
     * 删除用户好友 信息
     * 
     * @param userId 用户好友 主键
     * @return 结果
     */
    @Override
    public int deleteUserFriendsByUserId(Long userId)
    {
        return userFriendsMapper.deleteUserFriendsByUserId(userId);
    }

    /**
     * 获取用户好友数
     *
     * @param userId 用户id
     * @return 结果
     */
    @Override
    public int getFriendsCount(Long userId){
        return userFriendsMapper.getFriendsCount(userId);
    }

    /**
     * 删除用户好友
     *
     * @param userId 用户id
     * @param friendsId 好友id
     *
     * @return 结果
     */
    @Override
    public int deleteUserFriends( Long userId,Long friendsId){
        return userFriendsMapper.deleteUserFriends(userId,friendsId.toString());
    }

}
