package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户状态日志对象 operate_log
 * 
 * @author cjf
 * @date 2023-08-02
 */
public class OperateLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户角色 */
    @Excel(name = "用户角色")
    private String roleId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String userName;

    /** 被操作用户id */
    @Excel(name = "被操作用户id")
    private Long operateId;

    /** 被操作用户账号 */
    @Excel(name = "被操作用户账号")
    private String operateName;

    /** 操作后用户状态 */
    @Excel(name = "操作后用户状态")
    private String operateState;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRoleId(String roleId) 
    {
        this.roleId = roleId;
    }

    public String getRoleId() 
    {
        return roleId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setOperateId(Long operateId) 
    {
        this.operateId = operateId;
    }

    public Long getOperateId() 
    {
        return operateId;
    }
    public void setOperateName(String operateName) 
    {
        this.operateName = operateName;
    }

    public String getOperateName() 
    {
        return operateName;
    }
    public void setOperateState(String operateState) 
    {
        this.operateState = operateState;
    }

    public String getOperateState() 
    {
        return operateState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("operateId", getOperateId())
            .append("operateName", getOperateName())
            .append("operateState", getOperateState())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
}
