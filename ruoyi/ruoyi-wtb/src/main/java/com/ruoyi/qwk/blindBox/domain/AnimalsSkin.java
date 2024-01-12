package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 皮肤配置对象 animals_skin
 * 
 * @author cjf
 * @date 2023-11-07
 */
public class AnimalsSkin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 皮肤编号 */
    private Long id;

    /** 皮肤名称 */
    @Excel(name = "皮肤名称")
    private String name;

    /** 皮肤价格 */
    @Excel(name = "皮肤价格")
    private String price;

    /** 皮肤icon */
    @Excel(name = "皮肤icon")
    private String icon;

    /** 皮肤头像 */
    @Excel(name = "皮肤头像")
    private String avatar;

    /** 皮肤外显 */
    @Excel(name = "皮肤外显")
    private String overt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getPrice() 
    {
        return price;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setOvert(String overt) 
    {
        this.overt = overt;
    }

    public String getOvert() 
    {
        return overt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("price", getPrice())
            .append("icon", getIcon())
            .append("avatar", getAvatar())
            .append("overt", getOvert())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
