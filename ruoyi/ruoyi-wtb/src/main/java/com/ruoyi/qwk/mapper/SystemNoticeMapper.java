package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.SystemNotice;

/**
 * 系统消息通知Mapper接口
 * 
 * @author cjf
 * @date 2023-10-10
 */
@DataSource(value = DataSourceType.SLAVE)
public interface SystemNoticeMapper 
{
    /**
     * 查询系统消息通知
     * 
     * @param id 系统消息通知主键
     * @return 系统消息通知
     */
    public SystemNotice selectSystemNoticeById(Long id);

    /**
     * 查询系统消息通知列表
     * 
     * @param systemNotice 系统消息通知
     * @return 系统消息通知集合
     */
    public List<SystemNotice> selectSystemNoticeList(SystemNotice systemNotice);

    /**
     * 新增系统消息通知
     * 
     * @param systemNotice 系统消息通知
     * @return 结果
     */
    public int insertSystemNotice(SystemNotice systemNotice);

    /**
     * 修改系统消息通知
     * 
     * @param systemNotice 系统消息通知
     * @return 结果
     */
    public int updateSystemNotice(SystemNotice systemNotice);

    /**
     * 删除系统消息通知
     * 
     * @param id 系统消息通知主键
     * @return 结果
     */
    public int deleteSystemNoticeById(Long id);

    /**
     * 批量删除系统消息通知
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSystemNoticeByIds(Long[] ids);


    /**
     * 根据用户编号查询系统消息
     *
     * @param userId 用户编号
     *
     * */
    public List<SystemNotice> getNoticeByUserId(Long userId);

}
