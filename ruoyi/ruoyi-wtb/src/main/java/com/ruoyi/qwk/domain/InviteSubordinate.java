package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邀请用户关系对象 invite_subordinate
 *
 * @author cjf
 * @date 2023-09-27
 */
public class InviteSubordinate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 被邀请者编号 */
    @Excel(name = "被邀请者编号")
    private Long inviteeId;

    /** 被邀请者账号 */
    @Excel(name = "被邀请者账号")
    private String inviteeName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 头像
     */
    private String avatar;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setInviteeId(Long inviteeId)
    {
        this.inviteeId = inviteeId;
    }

    public Long getInviteeId()
    {
        return inviteeId;
    }
    public void setInviteeName(String inviteeName)
    {
        this.inviteeName = inviteeName;
    }

    public String getInviteeName()
    {
        return inviteeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("inviteeId", getInviteeId())
            .append("inviteeName", getInviteeName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
