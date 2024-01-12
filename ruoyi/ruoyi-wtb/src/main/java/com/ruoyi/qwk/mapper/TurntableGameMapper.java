package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.TurntableGame;

/**
 * 转盘游戏 Mapper接口
 *
 * @author cjf
 * @date 2023-12-20
 */
@DataSource(value = DataSourceType.SLAVE)
public interface TurntableGameMapper
{
    /**
     * 查询转盘游戏
     *
     * @param id 转盘游戏 主键
     * @return 转盘游戏
     */
    public TurntableGame selectTurntableGameById(Long id);

    /**
     * 查询转盘游戏 列表
     *
     * @param turntableGame 转盘游戏
     * @return 转盘游戏 集合
     */
    public List<TurntableGame> selectTurntableGameList(TurntableGame turntableGame);

    /**
     * 新增转盘游戏
     *
     * @param turntableGame 转盘游戏
     * @return 结果
     */
    public int insertTurntableGame(TurntableGame turntableGame);

    /**
     * 修改转盘游戏
     *
     * @param turntableGame 转盘游戏
     * @return 结果
     */
    public int updateTurntableGame(TurntableGame turntableGame);

    /**
     * 删除转盘游戏
     *
     * @param id 转盘游戏 主键
     * @return 结果
     */
    public int deleteTurntableGameById(Long id);

    /**
     * 批量删除转盘游戏
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTurntableGameByIds(Long[] ids);
}
