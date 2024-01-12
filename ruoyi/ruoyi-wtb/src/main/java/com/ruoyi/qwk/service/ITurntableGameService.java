package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.domain.TurntableGame;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 转盘游戏 Service接口
 *
 * @author cjf
 * @date 2023-12-20
 */
public interface ITurntableGameService
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
     * 批量删除转盘游戏
     *
     * @param ids 需要删除的转盘游戏 主键集合
     * @return 结果
     */
    public int deleteTurntableGameByIds(Long[] ids);

    /**
     * 删除转盘游戏 信息
     *
     * @param id 转盘游戏 主键
     * @return 结果
     */
    public int deleteTurntableGameById(Long id);

    /**
     * APP获取生肖转盘及奖品列表
     * @return
     */
    public AjaxResult luckyTurntablePrize();
}
