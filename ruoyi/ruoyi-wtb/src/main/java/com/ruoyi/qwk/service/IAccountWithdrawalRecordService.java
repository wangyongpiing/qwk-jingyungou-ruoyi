package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.AccountWithdrawalRecord;

/**
 * 账户提现记录 Service接口
 * 
 * @author cjf
 * @date 2023-12-27
 */
public interface IAccountWithdrawalRecordService 
{
    /**
     * 查询账户提现记录 
     * 
     * @param id 账户提现记录 主键
     * @return 账户提现记录 
     */
    public AccountWithdrawalRecord selectAccountWithdrawalRecordById(Long id);

    /**
     * 查询账户提现记录 列表
     * 
     * @param accountWithdrawalRecord 账户提现记录 
     * @return 账户提现记录 集合
     */
    public List<AccountWithdrawalRecord> selectAccountWithdrawalRecordList(AccountWithdrawalRecord accountWithdrawalRecord);

    /**
     * 新增账户提现记录 
     * 
     * @param accountWithdrawalRecord 账户提现记录 
     * @return 结果
     */
    public int insertAccountWithdrawalRecord(AccountWithdrawalRecord accountWithdrawalRecord);

    /**
     * 修改账户提现记录 
     * 
     * @param accountWithdrawalRecord 账户提现记录 
     * @return 结果
     */
    public int updateAccountWithdrawalRecord(AccountWithdrawalRecord accountWithdrawalRecord);

    /**
     * 批量删除账户提现记录 
     * 
     * @param ids 需要删除的账户提现记录 主键集合
     * @return 结果
     */
    public int deleteAccountWithdrawalRecordByIds(Long[] ids);

    /**
     * 删除账户提现记录 信息
     * 
     * @param id 账户提现记录 主键
     * @return 结果
     */
    public int deleteAccountWithdrawalRecordById(Long id);
}
