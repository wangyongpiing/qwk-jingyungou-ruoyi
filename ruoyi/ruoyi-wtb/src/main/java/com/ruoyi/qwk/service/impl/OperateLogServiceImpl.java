package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.OperateLogMapper;
import com.ruoyi.qwk.domain.OperateLog;
import com.ruoyi.qwk.service.IOperateLogService;

/**
 * 用户状态日志Service业务层处理
 * 
 * @author cjf
 * @date 2023-08-02
 */
@Service
public class OperateLogServiceImpl implements IOperateLogService 
{
    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 查询用户状态日志
     * 
     * @param id 用户状态日志主键
     * @return 用户状态日志
     */
    @Override
    public OperateLog selectOperateLogById(Long id)
    {
        return operateLogMapper.selectOperateLogById(id);
    }

    /**
     * 查询用户状态日志列表
     * 
     * @param operateLog 用户状态日志
     * @return 用户状态日志
     */
    @Override
    public List<OperateLog> selectOperateLogList(OperateLog operateLog)
    {
        return operateLogMapper.selectOperateLogList(operateLog);
    }

    /**
     * 新增用户状态日志
     * 
     * @param operateLog 用户状态日志
     * @return 结果
     */
    @Override
    public int insertOperateLog(OperateLog operateLog)
    {
        operateLog.setCreateTime(DateUtils.getNowDate());
        return operateLogMapper.insertOperateLog(operateLog);
    }

    /**
     * 修改用户状态日志
     * 
     * @param operateLog 用户状态日志
     * @return 结果
     */
    @Override
    public int updateOperateLog(OperateLog operateLog)
    {
        return operateLogMapper.updateOperateLog(operateLog);
    }

    /**
     * 批量删除用户状态日志
     * 
     * @param ids 需要删除的用户状态日志主键
     * @return 结果
     */
    @Override
    public int deleteOperateLogByIds(Long[] ids)
    {
        return operateLogMapper.deleteOperateLogByIds(ids);
    }

    /**
     * 删除用户状态日志信息
     * 
     * @param id 用户状态日志主键
     * @return 结果
     */
    @Override
    public int deleteOperateLogById(Long id)
    {
        return operateLogMapper.deleteOperateLogById(id);
    }
}
