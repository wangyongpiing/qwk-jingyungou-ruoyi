package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 版本管理对象 versioning
 *
 * @author cjf
 * @date 2023-08-31
 */
public class Versioning extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 版本名称 */
    @Excel(name = "版本名称")
    private String version;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long versionNumber;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String fileUrl;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private String fileSize;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }
    public void setVersionNumber(Long versionNumber)
    {
        this.versionNumber = versionNumber;
    }

    public Long getVersionNumber()
    {
        return versionNumber;
    }
    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl()
    {
        return fileUrl;
    }
    public void setFileSize(String fileSize)
    {
        this.fileSize = fileSize;
    }

    public String getFileSize()
    {
        return fileSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("version", getVersion())
                .append("versionNumber", getVersionNumber())
                .append("fileUrl", getFileUrl())
                .append("fileSize", getFileSize())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
