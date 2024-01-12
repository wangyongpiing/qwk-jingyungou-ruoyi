package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 转盘领取红包记录对象 turntable_red_envelope_record
 *
 * @author cjf
 * @date 2023-12-20
 */
public class TurntableRedEnvelopeRecord extends BaseEntity
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
    /** 抽奖id */
    @Excel(name = "领取红包id")
    private String lotteryId;

    /** 红包金额 */
    @Excel(name = "红包金额")
    private Double money;

    /** 红包来源1-下载安装，2-注册,3-抽奖 */
    @Excel(name = "红包来源1-下载安装，2-注册,3-抽奖")
    private String source;

    public String getSourceStr() {
        return sourceStr;
    }

    public void setSourceStr(String sourceStr) {
        this.sourceStr = sourceStr;
    }

    private String sourceStr;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setMoney(Double money)
    {
        this.money = money;
    }

    public Double getMoney()
    {
        return money;
    }
    public void setSource(String source)
    {
        this.source = source;
    }

    public String getSource()
    {
        return source;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(String isReceive) {
        this.isReceive = isReceive;
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("money", getMoney())
            .append("source", getSource())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
