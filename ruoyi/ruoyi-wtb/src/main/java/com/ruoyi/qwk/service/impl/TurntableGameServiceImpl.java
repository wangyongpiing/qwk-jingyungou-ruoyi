package com.ruoyi.qwk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.qwk.domain.Prize;
import com.ruoyi.qwk.mapper.PrizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.TurntableGameMapper;
import com.ruoyi.qwk.domain.TurntableGame;
import com.ruoyi.qwk.service.ITurntableGameService;

/**
 * 转盘游戏 Service业务层处理
 *
 * @author cjf
 * @date 2023-12-20
 */
@Service
public class TurntableGameServiceImpl implements ITurntableGameService
{
    @Autowired
    private TurntableGameMapper turntableGameMapper;

    @Autowired
    private PrizeMapper prizeMapper;

    /**
     * 查询转盘游戏
     *
     * @param id 转盘游戏 主键
     * @return 转盘游戏
     */
    @Override
    public TurntableGame selectTurntableGameById(Long id)
    {
        return turntableGameMapper.selectTurntableGameById(id);
    }

    /**
     * 查询转盘游戏 列表
     *
     * @param turntableGame 转盘游戏
     * @return 转盘游戏
     */
    @Override
    public List<TurntableGame> selectTurntableGameList(TurntableGame turntableGame)
    {
        return turntableGameMapper.selectTurntableGameList(turntableGame);
    }

    /**
     * 新增转盘游戏
     *
     * @param turntableGame 转盘游戏
     * @return 结果
     */
    @Override
    public int insertTurntableGame(TurntableGame turntableGame)
    {
        turntableGame.setCreateTime(DateUtils.getNowDate());
        return turntableGameMapper.insertTurntableGame(turntableGame);
    }

    /**
     * 修改转盘游戏
     *
     * @param turntableGame 转盘游戏
     * @return 结果
     */
    @Override
    public int updateTurntableGame(TurntableGame turntableGame)
    {
        turntableGame.setUpdateTime(DateUtils.getNowDate());
        return turntableGameMapper.updateTurntableGame(turntableGame);
    }

    /**
     * 批量删除转盘游戏
     *
     * @param ids 需要删除的转盘游戏 主键
     * @return 结果
     */
    @Override
    public int deleteTurntableGameByIds(Long[] ids)
    {
        return turntableGameMapper.deleteTurntableGameByIds(ids);
    }

    /**
     * 删除转盘游戏 信息
     *
     * @param id 转盘游戏 主键
     * @return 结果
     */
    @Override
    public int deleteTurntableGameById(Long id)
    {
        return turntableGameMapper.deleteTurntableGameById(id);
    }

    /**
     * APP获取生肖转盘及奖品列表
     * @return
     */
    public AjaxResult luckyTurntablePrize(){
        //获取幸运转盘游戏
        Long gameId = 1L;
        TurntableGame game = turntableGameMapper.selectTurntableGameById(gameId);
        if(ObjUtil.isNull(game) || game.getState()!=1L){
            return AjaxResult.error("活动已结束!");
        }
        //获取活动奖品
        Prize prize = new Prize();
        prize.setGameId(String.valueOf(gameId));
        List<Prize> prizeList = prizeMapper.selectPrizeList(prize);
        Map<String,Object> map = new HashMap<>();
        map.put("game",game);
        map.put("prizeList",prizeList);
        return AjaxResult.success(map);
    }
}
