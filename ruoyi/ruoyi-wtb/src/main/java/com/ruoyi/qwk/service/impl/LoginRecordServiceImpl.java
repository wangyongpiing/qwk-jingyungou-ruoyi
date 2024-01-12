package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.LoginRecordMapper;
import com.ruoyi.qwk.domain.LoginRecord;
import com.ruoyi.qwk.service.ILoginRecordService;

/**
 * 登录日志 Service业务层处理
 * 
 * @author cjf
 * @date 2024-01-04
 */
@Service
public class LoginRecordServiceImpl implements ILoginRecordService 
{
    @Autowired
    private LoginRecordMapper loginRecordMapper;

    /**
     * 查询登录日志 
     * 
     * @param id 登录日志 主键
     * @return 登录日志 
     */
    @Override
    public LoginRecord selectLoginRecordById(Long id)
    {
        return loginRecordMapper.selectLoginRecordById(id);
    }

    /**
     * 查询登录日志 列表
     * 
     * @param loginRecord 登录日志 
     * @return 登录日志 
     */
    @Override
    public List<LoginRecord> selectLoginRecordList(LoginRecord loginRecord)
    {
        return loginRecordMapper.selectLoginRecordList(loginRecord);
    }

    /**
     * 新增登录日志 
     * 
     * @param loginRecord 登录日志 
     * @return 结果
     */
    @Override
    public int insertLoginRecord(LoginRecord loginRecord)
    {
        loginRecord.setCreateTime(DateUtils.getNowDate());
        return loginRecordMapper.insertLoginRecord(loginRecord);
    }

    /**
     * 修改登录日志 
     * 
     * @param loginRecord 登录日志 
     * @return 结果
     */
    @Override
    public int updateLoginRecord(LoginRecord loginRecord)
    {
        loginRecord.setUpdateTime(DateUtils.getNowDate());
        return loginRecordMapper.updateLoginRecord(loginRecord);
    }

    /**
     * 批量删除登录日志 
     * 
     * @param ids 需要删除的登录日志 主键
     * @return 结果
     */
    @Override
    public int deleteLoginRecordByIds(Long[] ids)
    {
        return loginRecordMapper.deleteLoginRecordByIds(ids);
    }

    /**
     * 删除登录日志 信息
     * 
     * @param id 登录日志 主键
     * @return 结果
     */
    @Override
    public int deleteLoginRecordById(Long id)
    {
        return loginRecordMapper.deleteLoginRecordById(id);
    }
}
