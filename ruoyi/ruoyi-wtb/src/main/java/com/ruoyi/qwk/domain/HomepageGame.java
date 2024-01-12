package com.ruoyi.qwk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 首页游戏列 对象 homepage_game
 *
 * @author cjf
 * @date 2023-11-27
 */
public class HomepageGame extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 游戏名称 */
    @Excel(name = "游戏名称")
    private String gameName;

    /** 游戏图标 */
    @Excel(name = "游戏图标")
    private String gamePicture;

    /** 游戏内容 */
    @Excel(name = "游戏内容")
    private String gameContent;

    /** 是否热门 0:是1：否 */
    @Excel(name = "是否热门 0:是1：否")
    private String popular;

    /** 游戏链接 */
    @Excel(name = "游戏链接")
    private String gameUrl;

    /** 排序 */
    @Excel(name = "排序")
    private String sort;

    /** 是否开放 */
    @Excel(name = "是否开放")
    private String openness;

    public String getGameButtons() {
        return gameButtons;
    }

    public void setGameButtons(String gameButtons) {
        this.gameButtons = gameButtons;
    }

    /** 游戏按钮名称 */
    @Excel(name = "游戏按钮名称")
    private String gameButtons;

    public String getOpenness() {
        return openness;
    }

    public void setOpenness(String openness) {
        this.openness = openness;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }

    public String getGameName()
    {
        return gameName;
    }
    public void setGamePicture(String gamePicture)
    {
        this.gamePicture = gamePicture;
    }

    public String getGamePicture()
    {
        return gamePicture;
    }
    public void setGameContent(String gameContent)
    {
        this.gameContent = gameContent;
    }

    public String getGameContent()
    {
        return gameContent;
    }
    public void setPopular(String popular)
    {
        this.popular = popular;
    }

    public String getPopular()
    {
        return popular;
    }
    public void setGameUrl(String gameUrl)
    {
        this.gameUrl = gameUrl;
    }

    public String getGameUrl()
    {
        return gameUrl;
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
            .append("gameName", getGameName())
            .append("gamePicture", getGamePicture())
            .append("gameContent", getGameContent())
            .append("popular", getPopular())
            .append("gameUrl", getGameUrl())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
