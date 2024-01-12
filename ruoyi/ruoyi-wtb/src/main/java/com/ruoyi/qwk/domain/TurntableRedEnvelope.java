package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 转盘领取红包对象 turntable_red_envelope
 * 
 * @author cjf
 * @date 2023-12-20
 */
public class TurntableRedEnvelope extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String userHead;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String userName;

    /** 是否领取 */
    @Excel(name = "是否领取")
    private String isReceive;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserHead(String userHead) 
    {
        this.userHead = userHead;
    }

    public String getUserHead() 
    {
        return userHead;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setIsReceive(String isReceive) 
    {
        this.isReceive = isReceive;
    }

    public String getIsReceive() 
    {
        return isReceive;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userHead", getUserHead())
            .append("userName", getUserName())
            .append("isReceive", getIsReceive())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
