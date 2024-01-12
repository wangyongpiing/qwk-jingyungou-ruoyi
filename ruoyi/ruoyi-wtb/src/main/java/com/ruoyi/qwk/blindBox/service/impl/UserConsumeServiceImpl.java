package com.ruoyi.qwk.blindBox.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.UserConsumeMapper;
import com.ruoyi.qwk.blindBox.domain.UserConsume;
import com.ruoyi.qwk.blindBox.service.IUserConsumeService;
import org.springframework.util.ObjectUtils;

/**
 * 用户消耗Service业务层处理
 * 
 * @author cjf
 * @date 2023-09-24
 */
@Service
public class UserConsumeServiceImpl implements IUserConsumeService 
{
    @Autowired
    private UserConsumeMapper userConsumeMapper;

    /**
     * 查询用户消耗
     * 
     * @param id 用户消耗主键
     * @return 用户消耗
     */
    @Override
    public UserConsume selectUserConsumeById(Long id)
    {
        return userConsumeMapper.selectUserConsumeById(id);
    }

    /**
     * 查询用户消耗列表
     * 
     * @param userConsume 用户消耗
     * @return 用户消耗
     */
    @Override
    public List<UserConsume> selectUserConsumeList(UserConsume userConsume)
    {
        return userConsumeMapper.selectUserConsumeList(userConsume);
    }

    /**
     * 新增用户消耗
     * 
     * @param userConsume 用户消耗
     * @return 结果
     */
    @Override
    public int insertUserConsume(UserConsume userConsume)
    {
        userConsume.setCreateTime(DateUtils.getNowDate());
        return userConsumeMapper.insertUserConsume(userConsume);
    }

    /**
     * 修改用户消耗
     * 
     * @param userConsume 用户消耗
     * @return 结果
     */
    @Override
    public int updateUserConsume(UserConsume userConsume)
    {
        userConsume.setUpdateTime(DateUtils.getNowDate());
        return userConsumeMapper.updateUserConsume(userConsume);
    }

    /**
     * 批量删除用户消耗
     * 
     * @param ids 需要删除的用户消耗主键
     * @return 结果
     */
    @Override
    public int deleteUserConsumeByIds(Long[] ids)
    {
        return userConsumeMapper.deleteUserConsumeByIds(ids);
    }

    /**
     * 删除用户消耗信息
     * 
     * @param id 用户消耗主键
     * @return 结果
     */
    @Override
    public int deleteUserConsumeById(Long id)
    {
        return userConsumeMapper.deleteUserConsumeById(id);
    }

    /**
     * 根据用户编号查询用户消耗
     *
     * @param userId 用户编号
     * @return 用户消耗
     */
    @Override
    public UserConsume selectUserConsumeByUserId(Long userId) {
        UserConsume userConsume = userConsumeMapper.selectUserConsumeByUserId(userId);
        if (ObjectUtils.isEmpty(userConsume)){
            userConsume = new UserConsume();
            userConsume.setUserId(userId);
            userConsume.setConsume("0");
            userConsume.setCreateTime(new Date());
            userConsume.setCreateBy(SecurityUtils.getLoginUser().getUsername());
            userConsumeMapper.insertUserConsume(userConsume);
        }
        return userConsume;
    }

}
