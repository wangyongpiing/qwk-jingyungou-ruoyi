package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.qwk.domain.InviteSubordinate;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 邀请用户关系Service接口
 *
 * @author cjf
 * @date 2023-09-27
 */
public interface IInviteSubordinateService
{
    /**
     * 查询邀请用户关系
     *
     * @param id 邀请用户关系主键
     * @return 邀请用户关系
     */
    public InviteSubordinate selectInviteSubordinateById(Long id);

    /**
     * 查询邀请用户关系列表
     *
     * @param inviteSubordinate 邀请用户关系
     * @return 邀请用户关系集合
     */
    public List<InviteSubordinate> selectInviteSubordinateList(InviteSubordinate inviteSubordinate);

    /**
     * 新增邀请用户关系
     *
     * @param inviteSubordinate 邀请用户关系
     * @return 结果
     */
    public int insertInviteSubordinate(InviteSubordinate inviteSubordinate);

    /**
     * 修改邀请用户关系
     *
     * @param inviteSubordinate 邀请用户关系
     * @return 结果
     */
    public int updateInviteSubordinate(InviteSubordinate inviteSubordinate);

    /**
     * 批量删除邀请用户关系
     *
     * @param ids 需要删除的邀请用户关系主键集合
     * @return 结果
     */
    public int deleteInviteSubordinateByIds(Long[] ids);

    /**
     * 删除邀请用户关系信息
     *
     * @param id 邀请用户关系主键
     * @return 结果
     */
    public int deleteInviteSubordinateById(Long id);

    /**
     *
     * 添加用户上下级关系
     * */
    public int addInviteSubordinate(String ip, SysUser user);

    /**
     * 今日邀请人数
     * */
    public Integer todayPeople(Long userId);

    /**
     * 邀请总人数
     * */
    public Integer totalPeople(Long userId);

    /**
     * App获取邀请人助力记录
     * @return
     */
    public AjaxResult invitationAssistanceRecord();
}
