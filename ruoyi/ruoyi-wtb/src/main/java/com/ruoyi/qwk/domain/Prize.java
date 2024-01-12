package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 奖品 对象 prize
 * 
 * @author cjf
 * @date 2023-12-20
 */
public class Prize extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 游戏id */
    @Excel(name = "游戏id")
    private String gameId;

    /** 奖品类型（1优惠券，2商品，3提现红包，4谢谢参与，5积分,6其他） */
    @Excel(name = "奖品类型", readConverterExp = "1=优惠券，2商品，3提现红包，4谢谢参与，5积分,6其他")
    private Long prizeType;

    /** 奖品名字 */
    @Excel(name = "奖品名字")
    private String prizeName;

    /** 奖品id */
    @Excel(name = "奖品id")
    private String prizeId;

    /** 奖品数量 */
    @Excel(name = "奖品数量")
    private Long prizeValue;

    /** 中奖几率 */
    @Excel(name = "中奖几率")
    private String ratio;

    /** 当前命中 */
    @Excel(name = "当前命中")
    private Long currentNum;

    /** 排序 */
    @Excel(name = "排序")
    private String sort;

    /** 最大中奖数 0：代表不限制 */
    @Excel(name = "最大中奖数 0：代表不限制")
    private Long maxNum;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGameId(String gameId) 
    {
        this.gameId = gameId;
    }

    public String getGameId() 
    {
        return gameId;
    }
    public void setPrizeType(Long prizeType) 
    {
        this.prizeType = prizeType;
    }

    public Long getPrizeType() 
    {
        return prizeType;
    }
    public void setPrizeName(String prizeName) 
    {
        this.prizeName = prizeName;
    }

    public String getPrizeName() 
    {
        return prizeName;
    }
    public void setPrizeId(String prizeId) 
    {
        this.prizeId = prizeId;
    }

    public String getPrizeId() 
    {
        return prizeId;
    }
    public void setPrizeValue(Long prizeValue) 
    {
        this.prizeValue = prizeValue;
    }

    public Long getPrizeValue() 
    {
        return prizeValue;
    }
    public void setRatio(String ratio) 
    {
        this.ratio = ratio;
    }

    public String getRatio() 
    {
        return ratio;
    }
    public void setCurrentNum(Long currentNum) 
    {
        this.currentNum = currentNum;
    }

    public Long getCurrentNum() 
    {
        return currentNum;
    }
    public void setSort(String sort) 
    {
        this.sort = sort;
    }

    public String getSort() 
    {
        return sort;
    }
    public void setMaxNum(Long maxNum) 
    {
        this.maxNum = maxNum;
    }

    public Long getMaxNum() 
    {
        return maxNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("gameId", getGameId())
            .append("prizeType", getPrizeType())
            .append("prizeName", getPrizeName())
            .append("prizeId", getPrizeId())
            .append("prizeValue", getPrizeValue())
            .append("ratio", getRatio())
            .append("currentNum", getCurrentNum())
            .append("sort", getSort())
            .append("maxNum", getMaxNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
