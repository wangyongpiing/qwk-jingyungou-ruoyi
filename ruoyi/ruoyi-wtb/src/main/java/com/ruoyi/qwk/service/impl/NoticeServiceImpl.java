package com.ruoyi.qwk.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.NoticeMapper;
import com.ruoyi.qwk.domain.Notice;
import com.ruoyi.qwk.service.INoticeService;

/**
 * 公告管理Service业务层处理
 *
 * @author cjf
 * @date 2023-07-19
 */
@Service
public class NoticeServiceImpl implements INoticeService
{
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 查询公告管理
     *
     * @param id 公告管理主键
     * @return 公告管理
     */
    @Override
    public Notice selectNoticeById(Long id)
    {
        return noticeMapper.selectNoticeById(id);
    }

    /**
     * 查询公告管理列表
     *
     * @param notice 公告管理
     * @return 公告管理
     */
    @Override
    public List<Notice> selectNoticeList(Notice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告管理
     *
     * @param notice 公告管理
     * @return 结果
     */
    @Override
    public int insertNotice(Notice notice)
    {
        notice.setCreateBy(SecurityUtils.getUsername());
        notice.setCreateTime(DateUtils.getNowDate());
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改公告管理
     *
     * @param notice 公告管理
     * @return 结果
     */
    @Override
    public int updateNotice(Notice notice)
    {
        notice.setUpdateBy(SecurityUtils.getUsername());
        notice.setUpdateTime(DateUtils.getNowDate());
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 批量删除公告管理
     *
     * @param ids 需要删除的公告管理主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] ids)
    {
        return noticeMapper.deleteNoticeByIds(ids);
    }

    /**
     * 删除公告管理信息
     *
     * @param id 公告管理主键
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long id)
    {
        return noticeMapper.deleteNoticeById(id);
    }


    /**
     * APP获取系统公告
     * @return
     */

    public AjaxResult getList(){
        Notice notice = new Notice();
        notice.setState(0L);
        List<Notice> list = noticeMapper.selectNoticeList(notice);
        return AjaxResult.success(list);
    }
}
