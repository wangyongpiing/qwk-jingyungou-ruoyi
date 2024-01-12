package com.ruoyi.qwk.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 大逃杀用户游戏记录对象 big_escape_kill_users_record
 * 
 * @author cjf
 * @date 2023-12-22
 */
public class BigEscapeKillUsersRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 游戏期数 */
    @Excel(name = "游戏期数")
    private String number;

    /** 用户Id */
    @Excel(name = "用户Id")
    private Long userId;

    /** 用户选择房间Id */
    @Excel(name = "用户选择房间Id")
    private Long userSelectRoomId;

    /** 用户选择房间名称 */
    @Excel(name = "用户选择房间名称")
    private String userSelectRoomName;

    /** boss选择房间Id */
    @Excel(name = "boss选择房间Id")
    private Long bossSelectRoomId;

    /** boss选择房间名称 */
    @Excel(name = "boss选择房间名称")
    private String bossSelectRoomName;

    /** 投入货币数量 */
    @Excel(name = "投入货币数量")
    private BigDecimal putCoin;

    /** 获取货币数量 */
    @Excel(name = "获取货币数量")
    private BigDecimal getCoin;

    /** 0游戏未开始 1躲避成功 2躲避失败 */
    @Excel(name = "0游戏未开始 1躲避成功 2躲避失败")
    private Long status;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date created;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserSelectRoomId(Long userSelectRoomId) 
    {
        this.userSelectRoomId = userSelectRoomId;
    }

    public Long getUserSelectRoomId() 
    {
        return userSelectRoomId;
    }
    public void setUserSelectRoomName(String userSelectRoomName) 
    {
        this.userSelectRoomName = userSelectRoomName;
    }

    public String getUserSelectRoomName() 
    {
        return userSelectRoomName;
    }
    public void setBossSelectRoomId(Long bossSelectRoomId) 
    {
        this.bossSelectRoomId = bossSelectRoomId;
    }

    public Long getBossSelectRoomId() 
    {
        return bossSelectRoomId;
    }
    public void setBossSelectRoomName(String bossSelectRoomName) 
    {
        this.bossSelectRoomName = bossSelectRoomName;
    }

    public String getBossSelectRoomName() 
    {
        return bossSelectRoomName;
    }
    public void setPutCoin(BigDecimal putCoin) 
    {
        this.putCoin = putCoin;
    }

    public BigDecimal getPutCoin() 
    {
        return putCoin;
    }
    public void setGetCoin(BigDecimal getCoin) 
    {
        this.getCoin = getCoin;
    }

    public BigDecimal getGetCoin() 
    {
        return getCoin;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setCreated(Date created) 
    {
        this.created = created;
    }

    public Date getCreated() 
    {
        return created;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("number", getNumber())
            .append("userId", getUserId())
            .append("userSelectRoomId", getUserSelectRoomId())
            .append("userSelectRoomName", getUserSelectRoomName())
            .append("bossSelectRoomId", getBossSelectRoomId())
            .append("bossSelectRoomName", getBossSelectRoomName())
            .append("putCoin", getPutCoin())
            .append("getCoin", getGetCoin())
            .append("status", getStatus())
            .append("created", getCreated())
            .toString();
    }
}
