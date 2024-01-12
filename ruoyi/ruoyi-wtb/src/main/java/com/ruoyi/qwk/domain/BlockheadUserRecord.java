package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 木头人游戏用户记录对象 blockhead_user_record
 * 
 * @author cjf
 * @date 2023-12-23
 */
public class BlockheadUserRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 游戏局数 */
    @Excel(name = "游戏局数")
    private String number;

    /** 等级1000 10000 10000场 */
    @Excel(name = "等级1000 10000 10000场")
    private Long level;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 第2次选择形状 */
    @Excel(name = "第2次选择形状")
    private Long selectOne;

    /** 第2次选择形状 */
    @Excel(name = "第2次选择形状")
    private Long selectTwo;

    /** 第3次选择形状 */
    @Excel(name = "第3次选择形状")
    private Long selectThr;

    /** 状态 1正常 0失败 */
    @Excel(name = "状态 1正常 0失败")
    private Long status;

    /** 获得糖果数 */
    @Excel(name = "获得糖果数")
    private Long getCoin;

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
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setSelectOne(Long selectOne) 
    {
        this.selectOne = selectOne;
    }

    public Long getSelectOne() 
    {
        return selectOne;
    }
    public void setSelectTwo(Long selectTwo) 
    {
        this.selectTwo = selectTwo;
    }

    public Long getSelectTwo() 
    {
        return selectTwo;
    }
    public void setSelectThr(Long selectThr) 
    {
        this.selectThr = selectThr;
    }

    public Long getSelectThr() 
    {
        return selectThr;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setGetCoin(Long getCoin) 
    {
        this.getCoin = getCoin;
    }

    public Long getGetCoin() 
    {
        return getCoin;
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
            .append("level", getLevel())
            .append("userId", getUserId())
            .append("selectOne", getSelectOne())
            .append("selectTwo", getSelectTwo())
            .append("selectThr", getSelectThr())
            .append("status", getStatus())
            .append("getCoin", getGetCoin())
            .append("created", getCreated())
            .toString();
    }
}
