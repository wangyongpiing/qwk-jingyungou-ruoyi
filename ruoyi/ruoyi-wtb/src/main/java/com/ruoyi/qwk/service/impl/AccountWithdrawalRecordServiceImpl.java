package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.AccountWithdrawalRecordMapper;
import com.ruoyi.qwk.domain.AccountWithdrawalRecord;
import com.ruoyi.qwk.service.IAccountWithdrawalRecordService;

/**
 * 账户提现记录 Service业务层处理
 * 
 * @author cjf
 * @date 2023-12-27
 */
@Service
public class AccountWithdrawalRecordServiceImpl implements IAccountWithdrawalRecordService 
{
    @Autowired
    private AccountWithdrawalRecordMapper accountWithdrawalRecordMapper;

    /**
     * 查询账户提现记录 
     * 
     * @param id 账户提现记录 主键
     * @return 账户提现记录 
     */
    @Override
    public AccountWithdrawalRecord selectAccountWithdrawalRecordById(Long id)
    {
        return accountWithdrawalRecordMapper.selectAccountWithdrawalRecordById(id);
    }

    /**
     * 查询账户提现记录 列表
     * 
     * @param accountWithdrawalRecord 账户提现记录 
     * @return 账户提现记录 
     */
    @Override
    public List<AccountWithdrawalRecord> selectAccountWithdrawalRecordList(AccountWithdrawalRecord accountWithdrawalRecord)
    {
        return accountWithdrawalRecordMapper.selectAccountWithdrawalRecordList(accountWithdrawalRecord);
    }

    /**
     * 新增账户提现记录 
     * 
     * @param accountWithdrawalRecord 账户提现记录 
     * @return 结果
     */
    @Override
    public int insertAccountWithdrawalRecord(AccountWithdrawalRecord accountWithdrawalRecord)
    {
        accountWithdrawalRecord.setCreateTime(DateUtils.getNowDate());
        return accountWithdrawalRecordMapper.insertAccountWithdrawalRecord(accountWithdrawalRecord);
    }

    /**
     * 修改账户提现记录 
     * 
     * @param accountWithdrawalRecord 账户提现记录 
     * @return 结果
     */
    @Override
    public int updateAccountWithdrawalRecord(AccountWithdrawalRecord accountWithdrawalRecord)
    {
        accountWithdrawalRecord.setUpdateTime(DateUtils.getNowDate());
        return accountWithdrawalRecordMapper.updateAccountWithdrawalRecord(accountWithdrawalRecord);
    }

    /**
     * 批量删除账户提现记录 
     * 
     * @param ids 需要删除的账户提现记录 主键
     * @return 结果
     */
    @Override
    public int deleteAccountWithdrawalRecordByIds(Long[] ids)
    {
        return accountWithdrawalRecordMapper.deleteAccountWithdrawalRecordByIds(ids);
    }

    /**
     * 删除账户提现记录 信息
     * 
     * @param id 账户提现记录 主键
     * @return 结果
     */
    @Override
    public int deleteAccountWithdrawalRecordById(Long id)
    {
        return accountWithdrawalRecordMapper.deleteAccountWithdrawalRecordById(id);
    }
}
