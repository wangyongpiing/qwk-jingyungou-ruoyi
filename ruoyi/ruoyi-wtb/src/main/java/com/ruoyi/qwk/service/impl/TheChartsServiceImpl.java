package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.TheChartsMapper;
import com.ruoyi.qwk.domain.TheCharts;
import com.ruoyi.qwk.service.ITheChartsService;

/**
 * 游戏排行榜Service业务层处理
 * 
 * @author cjf
 * @date 2023-07-17
 */
@Service
public class TheChartsServiceImpl implements ITheChartsService 
{
    @Autowired
    private TheChartsMapper theChartsMapper;

    /**
     * 查询游戏排行榜
     * 
     * @param id 游戏排行榜主键
     * @return 游戏排行榜
     */
    @Override
    public TheCharts selectTheChartsById(Long id)
    {
        return theChartsMapper.selectTheChartsById(id);
    }

    /**
     * 查询游戏排行榜列表
     * 
     * @param theCharts 游戏排行榜
     * @return 游戏排行榜
     */
    @Override
    public List<TheCharts> selectTheChartsList(TheCharts theCharts)
    {
        return theChartsMapper.selectTheChartsList(theCharts);
    }

    /**
     * 新增游戏排行榜
     * 
     * @param theCharts 游戏排行榜
     * @return 结果
     */
    @Override
    public int insertTheCharts(TheCharts theCharts)
    {
        theCharts.setCreateTime(DateUtils.getNowDate());
        return theChartsMapper.insertTheCharts(theCharts);
    }

    /**
     * 修改游戏排行榜
     * 
     * @param theCharts 游戏排行榜
     * @return 结果
     */
    @Override
    public int updateTheCharts(TheCharts theCharts)
    {
        theCharts.setUpdateTime(DateUtils.getNowDate());
        return theChartsMapper.updateTheCharts(theCharts);
    }

    /**
     * 批量删除游戏排行榜
     * 
     * @param ids 需要删除的游戏排行榜主键
     * @return 结果
     */
    @Override
    public int deleteTheChartsByIds(Long[] ids)
    {
        return theChartsMapper.deleteTheChartsByIds(ids);
    }

    /**
     * 删除游戏排行榜信息
     * 
     * @param id 游戏排行榜主键
     * @return 结果
     */
    @Override
    public int deleteTheChartsById(Long id)
    {
        return theChartsMapper.deleteTheChartsById(id);
    }

    /**
     * 根据游戏id查询排行榜
     *
     * @param gameId 游戏id
     * @return 结果
     */
    @Override
    public List<TheCharts> selectTheChartsByGameId(Long gameId){
        return theChartsMapper.selectTheChartsByGameId(gameId);
    }

    /**
     * 根据用户id查询游戏排行数据
     *
     * @param userId 用户id
     * @return 游戏排行榜
     */
    @Override
    public TheCharts selectTheChartsByUserId(Long gameId,Long userId){
        return theChartsMapper.selectTheChartsByUserId(gameId,userId);
    }

}
