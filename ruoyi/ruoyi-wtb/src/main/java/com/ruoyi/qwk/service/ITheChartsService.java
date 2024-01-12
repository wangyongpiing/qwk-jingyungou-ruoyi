package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.TheCharts;

/**
 * 游戏排行榜Service接口
 * 
 * @author cjf
 * @date 2023-07-17
 */
public interface ITheChartsService 
{
    /**
     * 查询游戏排行榜
     * 
     * @param id 游戏排行榜主键
     * @return 游戏排行榜
     */
    public TheCharts selectTheChartsById(Long id);

    /**
     * 查询游戏排行榜列表
     * 
     * @param theCharts 游戏排行榜
     * @return 游戏排行榜集合
     */
    public List<TheCharts> selectTheChartsList(TheCharts theCharts);

    /**
     * 新增游戏排行榜
     * 
     * @param theCharts 游戏排行榜
     * @return 结果
     */
    public int insertTheCharts(TheCharts theCharts);

    /**
     * 修改游戏排行榜
     * 
     * @param theCharts 游戏排行榜
     * @return 结果
     */
    public int updateTheCharts(TheCharts theCharts);

    /**
     * 批量删除游戏排行榜
     * 
     * @param ids 需要删除的游戏排行榜主键集合
     * @return 结果
     */
    public int deleteTheChartsByIds(Long[] ids);

    /**
     * 删除游戏排行榜信息
     * 
     * @param id 游戏排行榜主键
     * @return 结果
     */
    public int deleteTheChartsById(Long id);

    /**
     * 根据游戏id查询排行榜
     *
     * @param gameId 游戏id
     * @return 结果
     */
    public List<TheCharts> selectTheChartsByGameId(Long gameId);

    /**
     * 根据用户id查询游戏排行数据
     *
     * @param userId 用户id
     * @param gameId 游戏id
     * @return 游戏排行榜
     */
    public TheCharts selectTheChartsByUserId(Long gameId,Long userId);

}
