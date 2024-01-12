package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.OperateLog;

/**
 * 用户状态日志Service接口
 * 
 * @author cjf
 * @date 2023-08-02
 */
public interface IOperateLogService 
{
    /**
     * 查询用户状态日志
     * 
     * @param id 用户状态日志主键
     * @return 用户状态日志
     */
    public OperateLog selectOperateLogById(Long id);

    /**
     * 查询用户状态日志列表
     * 
     * @param operateLog 用户状态日志
     * @return 用户状态日志集合
     */
    public List<OperateLog> selectOperateLogList(OperateLog operateLog);

    /**
     * 新增用户状态日志
     * 
     * @param operateLog 用户状态日志
     * @return 结果
     */
    public int insertOperateLog(OperateLog operateLog);

    /**
     * 修改用户状态日志
     * 
     * @param operateLog 用户状态日志
     * @return 结果
     */
    public int updateOperateLog(OperateLog operateLog);

    /**
     * 批量删除用户状态日志
     * 
     * @param ids 需要删除的用户状态日志主键集合
     * @return 结果
     */
    public int deleteOperateLogByIds(Long[] ids);

    /**
     * 删除用户状态日志信息
     * 
     * @param id 用户状态日志主键
     * @return 结果
     */
    public int deleteOperateLogById(Long id);
}
