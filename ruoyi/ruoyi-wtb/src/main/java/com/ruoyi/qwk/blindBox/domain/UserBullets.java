package com.ruoyi.qwk.blindBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户子弹数量记录对象 user_bullets
 * 
 * @author cjf
 * @date 2023-09-21
 */
public class UserBullets extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 子弹编号 */
    @Excel(name = "子弹编号")
    private Long bulletsId;

    /** 子弹数量 */
    @Excel(name = "子弹数量")
    private Integer bulletsNum;

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
    public void setBulletsId(Long bulletsId) 
    {
        this.bulletsId = bulletsId;
    }

    public Long getBulletsId() 
    {
        return bulletsId;
    }
    public void setBulletsNum(Integer bulletsNum) 
    {
        this.bulletsNum = bulletsNum;
    }

    public Integer getBulletsNum() 
    {
        return bulletsNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("bulletsId", getBulletsId())
            .append("bulletsNum", getBulletsNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
