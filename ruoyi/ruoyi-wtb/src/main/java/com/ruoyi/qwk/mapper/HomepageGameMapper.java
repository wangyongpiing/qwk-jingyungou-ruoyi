package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.HomepageGame;

/**
 * 首页游戏列 Mapper接口
 *
 * @author cjf
 * @date 2023-11-27
 */
@DataSource(value = DataSourceType.SLAVE)
public interface HomepageGameMapper
{
    /**
     * 查询首页游戏列
     *
     * @param id 首页游戏列 主键
     * @return 首页游戏列
     */
    public HomepageGame selectHomepageGameById(Long id);

    /**
     * 查询首页游戏列 列表
     *
     * @param homepageGame 首页游戏列
     * @return 首页游戏列 集合
     */
    public List<HomepageGame> selectHomepageGameList(HomepageGame homepageGame);

    /**
     * 新增首页游戏列
     *
     * @param homepageGame 首页游戏列
     * @return 结果
     */
    public int insertHomepageGame(HomepageGame homepageGame);

    /**
     * 修改首页游戏列
     *
     * @param homepageGame 首页游戏列
     * @return 结果
     */
    public int updateHomepageGame(HomepageGame homepageGame);

    /**
     * 删除首页游戏列
     *
     * @param id 首页游戏列 主键
     * @return 结果
     */
    public int deleteHomepageGameById(Long id);

    /**
     * 批量删除首页游戏列
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomepageGameByIds(Long[] ids);
}
