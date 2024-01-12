package com.ruoyi.qwk.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资产详情 对象 asset_details
 *
 * @author cjf
 * @date 2023-07-04
 */
public class AssetDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    private String userPhone;

    /** 类型 */
    @Excel(name = "类型")
    private Long type;

    /** 图片 */
    @Excel(name = "图片")
    private String image;

    /** 添加或者相减(0:减;1:加) */
    @Excel(name = "添加或者相减(0:减;1:加)")
    private String addOrSubtract;

    /** 总额变化记录 */
    @Excel(name = "总额变化记录")
    private String records;

    /** 余额 */
    @Excel(name = "余额")
    private String balance;

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

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
    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public String getUserPhone()
    {
        return userPhone;
    }
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setAddOrSubtract(String addOrSubtract)
    {
        this.addOrSubtract = addOrSubtract;
    }

    public String getAddOrSubtract()
    {
        return addOrSubtract;
    }
    public void setRecords(String records)
    {
        this.records = records;
    }

    public String getRecords()
    {
        return records;
    }
    public void setBalance(String balance)
    {
        this.balance = balance;
    }

    public String getBalance()
    {
        return balance;
    }
    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getNotes()
    {
        return notes;
    }
    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
    public void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy()
    {
        return updatedBy;
    }
    public void setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("userPhone", getUserPhone())
                .append("type", getType())
                .append("image", getImage())
                .append("addOrSubtract", getAddOrSubtract())
                .append("records", getRecords())
                .append("balance", getBalance())
                .append("notes", getNotes())
                .append("createdBy", getCreatedBy())
                .append("createdTime", getCreatedTime())
                .append("updatedBy", getUpdatedBy())
                .append("updatedTime", getUpdatedTime())
                .toString();
    }
}
