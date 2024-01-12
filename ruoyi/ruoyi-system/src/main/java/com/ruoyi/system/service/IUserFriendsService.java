package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserFriends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户好友 Service接口
 * 
 * @author cjf
 * @date 2023-06-27
 */
public interface IUserFriendsService 
{
    /**
     * 查询用户好友 
     * 
     * @param userId 用户好友 主键
     * @return 用户好友 
     */
    public UserFriends selectUserFriendsByUserId(Long userId);

    /**
     * 查询用户好友 列表
     * 
     * @param userFriends 用户好友 
     * @return 用户好友 集合
     */
    public List<UserFriends> selectUserFriendsList(UserFriends userFriends);

    /**
     * 新增用户好友 
     * 
     * @param userFriends 用户好友 
     * @return 结果
     */
    public int insertUserFriends(UserFriends userFriends);

    /**
     * 修改用户好友 
     * 
     * @param userFriends 用户好友 
     * @return 结果
     */
    public int updateUserFriends(UserFriends userFriends);

    /**
     * 批量删除用户好友 
     * 
     * @param userIds 需要删除的用户好友 主键集合
     * @return 结果
     */
    public int deleteUserFriendsByUserIds(Long[] userIds);

    /**
     * 删除用户好友 信息
     * 
     * @param userId 用户好友 主键
     * @return 结果
     */
    public int deleteUserFriendsByUserId(Long userId);

    /**
     * 获取用户好友数
     *
     * @param userId 用户id
     * @return 结果
     */
    public int getFriendsCount(Long userId);


    public int deleteUserFriends(Long userId,Long friendsId);
}
