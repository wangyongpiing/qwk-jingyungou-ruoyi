package com.ruoyi.qwk.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.myJPush.MyJPushClient;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.SystemNoticeMapper;
import com.ruoyi.qwk.domain.SystemNotice;
import com.ruoyi.qwk.service.ISystemNoticeService;
import org.springframework.util.ObjectUtils;

/**
 * 系统消息通知Service业务层处理
 *
 * @author cjf
 * @date 2023-10-10
 */
@Service
public class SystemNoticeServiceImpl implements ISystemNoticeService
{
    @Autowired
    private SystemNoticeMapper systemNoticeMapper;

    @Autowired
    private MyJPushClient myJPushClient;

    /**
     * 查询系统消息通知
     *
     * @param id 系统消息通知主键
     * @return 系统消息通知
     */
    @Override
    public SystemNotice selectSystemNoticeById(Long id)
    {
        return systemNoticeMapper.selectSystemNoticeById(id);
    }

    /**
     * 查询系统消息通知列表
     *
     * @param systemNotice 系统消息通知
     * @return 系统消息通知
     */
    @Override
    public List<SystemNotice> selectSystemNoticeList(SystemNotice systemNotice)
    {
        return systemNoticeMapper.selectSystemNoticeList(systemNotice);
    }

    /**
     * 新增系统消息通知
     *
     * @param systemNotice 系统消息通知
     * @return 结果
     */
    @Override
    public int insertSystemNotice(SystemNotice systemNotice)
    {
        systemNotice.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        systemNotice.setCreateTime(DateUtils.getNowDate());
        return systemNoticeMapper.insertSystemNotice(systemNotice);
    }

    /**
     * 修改系统消息通知
     *
     * @param systemNotice 系统消息通知
     * @return 结果
     */
    @Override
    public int updateSystemNotice(SystemNotice systemNotice)
    {
        systemNotice.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        systemNotice.setUpdateTime(DateUtils.getNowDate());
        return systemNoticeMapper.updateSystemNotice(systemNotice);
    }

    /**
     * 批量删除系统消息通知
     *
     * @param ids 需要删除的系统消息通知主键
     * @return 结果
     */
    @Override
    public int deleteSystemNoticeByIds(Long[] ids)
    {
        return systemNoticeMapper.deleteSystemNoticeByIds(ids);
    }

    /**
     * 删除系统消息通知信息
     *
     * @param id 系统消息通知主键
     * @return 结果
     */
    @Override
    public int deleteSystemNoticeById(Long id)
    {
        return systemNoticeMapper.deleteSystemNoticeById(id);
    }


    /**
     * 发送系统通知
     * */
    @Override
    public AjaxResult sendNotice(SystemNotice systemNotice){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",systemNotice.getId());
        map.put("type",1);
        if ("0".equals(systemNotice.getIsAll())){//发送所有人
            myJPushClient.sendToAll(systemNotice.getNotificationTitle()
                    ,systemNotice.getNotificationContent(),
                    systemNotice.getMessageBody(),map);
        }else {
            if (ObjectUtils.isEmpty(systemNotice.getUserId()))return AjaxResult.error("用户id不能为空!");
            myJPushClient.sendToAliasList(Arrays.asList(systemNotice.getUserId().toString()),systemNotice.getNotificationTitle()
                    ,systemNotice.getNotificationContent(),
                    systemNotice.getMessageBody(),map);
        }
        systemNotice.setState("1");
        systemNoticeMapper.updateSystemNotice(systemNotice);
        return AjaxResult.success();
    }

//    ------------------------------------app----------------------------------------------------

    /**
     * 根据用户编号查询系统消息
     *
     *
     * */
    @Override
    public AjaxResult getNotice(){
        SystemNotice notice = new SystemNotice();
        SystemNotice sn = new SystemNotice();
        notice.setState("0");
        List<SystemNotice> list = systemNoticeMapper.selectSystemNoticeList(notice);
        return AjaxResult.success(list);
    }

}
