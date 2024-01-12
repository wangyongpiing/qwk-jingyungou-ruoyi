package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.UserAccountBalance;

/**
 * 用户账户余额Mapper接口
 *
 * @author cjf
 * @date 2023-12-21
 */
@DataSource(value = DataSourceType.SLAVE)
public interface UserAccountBalanceMapper
{
    /**
     * 查询用户账户余额
     *
     * @param id 用户账户余额主键
     * @return 用户账户余额
     */
    public UserAccountBalance selectUserAccountBalanceById(Long id);

    /**
     * 查询用户账户余额列表
     *
     * @param userAccountBalance 用户账户余额
     * @return 用户账户余额集合
     */
    public List<UserAccountBalance> selectUserAccountBalanceList(UserAccountBalance userAccountBalance);

    /**
     * 新增用户账户余额
     *
     * @param userAccountBalance 用户账户余额
     * @return 结果
     */
    public int insertUserAccountBalance(UserAccountBalance userAccountBalance);

    /**
     * 修改用户账户余额
     *
     * @param userAccountBalance 用户账户余额
     * @return 结果
     */
    public int updateUserAccountBalance(UserAccountBalance userAccountBalance);

    /**
     * 删除用户账户余额
     *
     * @param id 用户账户余额主键
     * @return 结果
     */
    public int deleteUserAccountBalanceById(Long id);

    /**
     * 批量删除用户账户余额
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserAccountBalanceByIds(Long[] ids);
}
