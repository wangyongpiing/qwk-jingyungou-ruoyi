package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.UserConsume;

/**
 * 用户消耗Mapper接口
 * 
 * @author cjf
 * @date 2023-09-24
 */
@DataSource(value = DataSourceType.SLAVE)
public interface UserConsumeMapper 
{
    /**
     * 查询用户消耗
     * 
     * @param id 用户消耗主键
     * @return 用户消耗
     */
    public UserConsume selectUserConsumeById(Long id);

    /**
     * 查询用户消耗列表
     * 
     * @param userConsume 用户消耗
     * @return 用户消耗集合
     */
    public List<UserConsume> selectUserConsumeList(UserConsume userConsume);

    /**
     * 新增用户消耗
     * 
     * @param userConsume 用户消耗
     * @return 结果
     */
    public int insertUserConsume(UserConsume userConsume);

    /**
     * 修改用户消耗
     * 
     * @param userConsume 用户消耗
     * @return 结果
     */
    public int updateUserConsume(UserConsume userConsume);

    /**
     * 删除用户消耗
     * 
     * @param id 用户消耗主键
     * @return 结果
     */
    public int deleteUserConsumeById(Long id);

    /**
     * 批量删除用户消耗
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserConsumeByIds(Long[] ids);


    /**
     * 根据用户编号查询用户消耗
     *
     * @param userId 用户编号
     * @return 用户消耗
     */
    public UserConsume selectUserConsumeByUserId(Long userId);
}
