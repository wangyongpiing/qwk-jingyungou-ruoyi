package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.Notice;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 公告管理Service接口
 *
 * @author cjf
 * @date 2023-07-19
 */
public interface INoticeService
{
    /**
     * 查询公告管理
     *
     * @param id 公告管理主键
     * @return 公告管理
     */
    public Notice selectNoticeById(Long id);

    /**
     * 查询公告管理列表
     *
     * @param notice 公告管理
     * @return 公告管理集合
     */
    public List<Notice> selectNoticeList(Notice notice);

    /**
     * 新增公告管理
     *
     * @param notice 公告管理
     * @return 结果
     */
    public int insertNotice(Notice notice);

    /**
     * 修改公告管理
     *
     * @param notice 公告管理
     * @return 结果
     */
    public int updateNotice(Notice notice);

    /**
     * 批量删除公告管理
     *
     * @param ids 需要删除的公告管理主键集合
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] ids);

    /**
     * 删除公告管理信息
     *
     * @param id 公告管理主键
     * @return 结果
     */
    public int deleteNoticeById(Long id);

    /**
     * APP获取系统公告
     * @return
     */

    public AjaxResult getList();
}
