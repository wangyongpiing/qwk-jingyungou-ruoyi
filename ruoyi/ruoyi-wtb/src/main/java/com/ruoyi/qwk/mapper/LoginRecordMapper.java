package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.LoginRecord;

/**
 * 登录日志 Mapper接口
 *
 * @author cjf
 * @date 2024-01-04
 */
@DataSource(value = DataSourceType.SLAVE)
public interface LoginRecordMapper
{
    /**
     * 查询登录日志
     *
     * @param id 登录日志 主键
     * @return 登录日志
     */
    public LoginRecord selectLoginRecordById(Long id);

    /**
     * 查询登录日志 列表
     *
     * @param loginRecord 登录日志
     * @return 登录日志 集合
     */
    public List<LoginRecord> selectLoginRecordList(LoginRecord loginRecord);

    /**
     * 新增登录日志
     *
     * @param loginRecord 登录日志
     * @return 结果
     */
    public int insertLoginRecord(LoginRecord loginRecord);

    /**
     * 修改登录日志
     *
     * @param loginRecord 登录日志
     * @return 结果
     */
    public int updateLoginRecord(LoginRecord loginRecord);

    /**
     * 删除登录日志
     *
     * @param id 登录日志 主键
     * @return 结果
     */
    public int deleteLoginRecordById(Long id);

    /**
     * 批量删除登录日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLoginRecordByIds(Long[] ids);
}
