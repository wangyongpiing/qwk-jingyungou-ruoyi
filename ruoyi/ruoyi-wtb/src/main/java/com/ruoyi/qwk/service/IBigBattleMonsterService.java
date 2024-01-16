package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.BigBattleMonster;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  大作战怪物Service接口
 *
 * @author cjf
 * @date 2024-01-15
 */
public interface IBigBattleMonsterService
{
    /**
     * 查询 大作战怪物
     *
     * @param id  大作战怪物主键
     * @return  大作战怪物
     */
    public BigBattleMonster selectBigBattleMonsterById(Long id);

    /**
     * 查询 大作战怪物列表
     *
     * @param bigBattleMonster  大作战怪物
     * @return  大作战怪物集合
     */
    public List<BigBattleMonster> selectBigBattleMonsterList(BigBattleMonster bigBattleMonster);

    /**
     * 新增 大作战怪物
     *
     * @param bigBattleMonster  大作战怪物
     * @return 结果
     */
    public int insertBigBattleMonster(BigBattleMonster bigBattleMonster);

    /**
     * 修改 大作战怪物
     *
     * @param bigBattleMonster  大作战怪物
     * @return 结果
     */
    public int updateBigBattleMonster(BigBattleMonster bigBattleMonster);

    /**
     * 批量删除 大作战怪物
     *
     * @param ids 需要删除的 大作战怪物主键集合
     * @return 结果
     */
    public int deleteBigBattleMonsterByIds(Long[] ids);

    /**
     * 删除 大作战怪物信息
     *
     * @param id  大作战怪物主键
     * @return 结果
     */
    public int deleteBigBattleMonsterById(Long id);

    /**
     * 怪物生成
     * @param userId
     * @param monsterId
     * @return
     */
    public AjaxResult generateMonsters(String userId,String monsterId);

    /**
     * 怪物死亡
     * @param userId
     * @param monsterId
     * @return
     */
    public AjaxResult deathMonsters(String userId,String monsterId);
}
