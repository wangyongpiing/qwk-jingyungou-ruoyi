package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.UserAccountBalance;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户账户余额Service接口
 *
 * @author cjf
 * @date 2023-12-21
 */
public interface IUserAccountBalanceService
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
     * 批量删除用户账户余额
     *
     * @param ids 需要删除的用户账户余额主键集合
     * @return 结果
     */
    public int deleteUserAccountBalanceByIds(Long[] ids);

    /**
     * 删除用户账户余额信息
     *
     * @param id 用户账户余额主键
     * @return 结果
     */
    public int deleteUserAccountBalanceById(Long id);

    /**
     * APP查询账户余额
     * @return
     */
    public AjaxResult queryAccountBalance();

    /**
     * APP添加支付宝账户
     * @return
     */
    public AjaxResult addAlipayAccount(String alipayAccount,String alipayAccountName);

    /**
     * 分享助力查询账户余额
     * @return
     */
    public AjaxResult queryShareAccountBalance(String userId);
}
