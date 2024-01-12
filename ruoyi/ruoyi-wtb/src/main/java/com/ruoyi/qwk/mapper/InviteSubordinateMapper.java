package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.InviteSubordinate;

/**
 * 邀请用户关系Mapper接口
 * 
 * @author cjf
 * @date 2023-09-27
 */
@DataSource(value = DataSourceType.SLAVE)
public interface InviteSubordinateMapper 
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
     * 删除邀请用户关系
     * 
     * @param id 邀请用户关系主键
     * @return 结果
     */
    public int deleteInviteSubordinateById(Long id);

    /**
     * 批量删除邀请用户关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInviteSubordinateByIds(Long[] ids);

    /**
     * 今日邀请人数
     * */
    public Integer todayPeople(Long userId);

    /**
     * 邀请总人数
     * */
    public Integer totalPeople(Long userId);

}
