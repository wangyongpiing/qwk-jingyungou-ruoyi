package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 每种物品的中奖概率配置对象 probability_config
 *
 * @author cjf
 * @date 2023-10-23
 */
public class ProbabilityConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 盲盒编号 */
    @Excel(name = "盲盒编号")
    private Long fMId;

    /** 场次编号 */
    @Excel(name = "场次编号")
    private Integer fieldId;

    /** Fn次数 */
    @Excel(name = "Fn次数")
    private Double fn;

    /** Oi概率 */
    @Excel(name = "Oi概率")
    private Double oi;

    /** Ui概率 */
    @Excel(name = "Ui概率")
    private Double ui;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setfMId(Long fMId)
    {
        this.fMId = fMId;
    }

    public Long getfMId()
    {
        return fMId;
    }
    public void setFieldId(Integer fieldId)
    {
        this.fieldId = fieldId;
    }

    public Integer getFieldId()
    {
        return fieldId;
    }
    public void setFn(Double fn)
    {
        this.fn = fn;
    }

    public Double getFn()
    {
        return fn;
    }
    public void setOi(Double oi)
    {
        this.oi = oi;
    }

    public Double getOi()
    {
        return oi;
    }
    public void setUi(Double ui)
    {
        this.ui = ui;
    }

    public Double getUi()
    {
        return ui;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("fMId", getfMId())
                .append("fieldId", getFieldId())
                .append("fn", getFn())
                .append("oi", getOi())
                .append("ui", getUi())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
