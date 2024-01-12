package com.ruoyi.qwk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户生肖资产对象 new_energy_user_zodiac_sign
 * 
 * @author cjf
 * @date 2023-12-08
 */
public class NewEnergyUserZodiacSign extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 生肖卡 ID */
    @Excel(name = "生肖卡 ID")
    private Long zodiacSignId;

    /** 1启用 0禁用/过期 */
    @Excel(name = "1启用 0禁用/过期")
    private Long status;

    /** 排序 1-100 */
    @Excel(name = "排序 1-100")
    private Long indexId;

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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setZodiacSignId(Long zodiacSignId) 
    {
        this.zodiacSignId = zodiacSignId;
    }

    public Long getZodiacSignId() 
    {
        return zodiacSignId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setIndexId(Long indexId) 
    {
        this.indexId = indexId;
    }

    public Long getIndexId() 
    {
        return indexId;
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
            .append("userId", getUserId())
            .append("zodiacSignId", getZodiacSignId())
            .append("status", getStatus())
            .append("indexId", getIndexId())
            .append("created", getCreated())
            .toString();
    }
}
