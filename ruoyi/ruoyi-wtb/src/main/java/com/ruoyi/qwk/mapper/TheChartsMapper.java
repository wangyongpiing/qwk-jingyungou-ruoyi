package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.TheCharts;
import org.apache.ibatis.annotations.Param;

/**
 * 游戏排行榜Mapper接口
 * 
 * @author cjf
 * @date 2023-07-17
 */
@DataSource(value = DataSourceType.SLAVE)
public interface TheChartsMapper 
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
     * 删除游戏排行榜
     * 
     * @param id 游戏排行榜主键
     * @return 结果
     */
    public int deleteTheChartsById(Long id);

    /**
     * 批量删除游戏排行榜
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTheChartsByIds(Long[] ids);

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
     * @return 游戏排行榜
     */
    public TheCharts selectTheChartsByUserId(@Param("gameId") Long gameId,@Param("userId") Long userId);

}
