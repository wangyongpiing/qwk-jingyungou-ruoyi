package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.AccountWithdrawalRecord;

/**
 * 账户提现记录 Mapper接口
 *
 * @author cjf
 * @date 2023-12-27
 */
@DataSource(value = DataSourceType.SLAVE)
public interface AccountWithdrawalRecordMapper
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
     * 删除账户提现记录
     *
     * @param id 账户提现记录 主键
     * @return 结果
     */
    public int deleteAccountWithdrawalRecordById(Long id);

    /**
     * 批量删除账户提现记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAccountWithdrawalRecordByIds(Long[] ids);
}
