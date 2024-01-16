package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.BigBattleMonster;

/**
 *  大作战怪物Mapper接口
 *
 * @author cjf
 * @date 2024-01-15
 */
@DataSource(value = DataSourceType.SLAVE)
public interface BigBattleMonsterMapper
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
     * 删除 大作战怪物
     *
     * @param id  大作战怪物主键
     * @return 结果
     */
    public int deleteBigBattleMonsterById(Long id);

    /**
     * 批量删除 大作战怪物
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBigBattleMonsterByIds(Long[] ids);
}
