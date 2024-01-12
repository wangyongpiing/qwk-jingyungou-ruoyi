package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.SystemNotice;

/**
 * 系统消息通知Service接口
 *
 * @author cjf
 * @date 2023-10-10
 */
public interface ISystemNoticeService
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
     * 批量删除系统消息通知
     *
     * @param ids 需要删除的系统消息通知主键集合
     * @return 结果
     */
    public int deleteSystemNoticeByIds(Long[] ids);

    /**
     * 删除系统消息通知信息
     *
     * @param id 系统消息通知主键
     * @return 结果
     */
    public int deleteSystemNoticeById(Long id);

    /**
     * 发送系统通知
     * */
    public AjaxResult sendNotice(SystemNotice systemNotice);

    /**
     * 根据用户编号查询系统消息
     *
     *
     * */
    public AjaxResult getNotice();

}
