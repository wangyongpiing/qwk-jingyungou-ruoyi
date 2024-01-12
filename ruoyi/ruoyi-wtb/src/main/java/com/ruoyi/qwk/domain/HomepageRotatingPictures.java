package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 *  首页轮播图片对象 homepage_rotating_pictures
 *
 * @author cjf
 * @date 2023-11-27
 */
public class HomepageRotatingPictures extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 图片名称 */
    @Excel(name = "图片名称")
    private String pictureName;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String pictureUrl;

    /** 排序 */
    @Excel(name = "排序")
    private String sort;

    /** 背景图片 */
    @Excel(name = "背景图片")
    private String backgroundUrl;

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPictureName(String pictureName)
    {
        this.pictureName = pictureName;
    }

    public String getPictureName()
    {
        return pictureName;
    }
    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl()
    {
        return pictureUrl;
    }
    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getSort()
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pictureName", getPictureName())
            .append("pictureUrl", getPictureUrl())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
