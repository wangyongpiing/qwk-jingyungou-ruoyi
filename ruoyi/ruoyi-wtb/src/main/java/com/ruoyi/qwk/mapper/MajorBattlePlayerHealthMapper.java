package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.MajorBattlePlayerHealth;

/**
 * 大作战玩家血量 Mapper接口
 *
 * @author cjf
 * @date 2024-01-15
 */
@DataSource(value = DataSourceType.SLAVE)
public interface MajorBattlePlayerHealthMapper
{
    /**
     * 查询大作战玩家血量
     *
     * @param id 大作战玩家血量 主键
     * @return 大作战玩家血量
     */
    public MajorBattlePlayerHealth selectMajorBattlePlayerHealthById(Long id);

    /**
     * 查询大作战玩家血量 列表
     *
     * @param majorBattlePlayerHealth 大作战玩家血量
     * @return 大作战玩家血量 集合
     */
    public List<MajorBattlePlayerHealth> selectMajorBattlePlayerHealthList(MajorBattlePlayerHealth majorBattlePlayerHealth);

    /**
     * 新增大作战玩家血量
     *
     * @param majorBattlePlayerHealth 大作战玩家血量
     * @return 结果
     */
    public int insertMajorBattlePlayerHealth(MajorBattlePlayerHealth majorBattlePlayerHealth);

    /**
     * 修改大作战玩家血量
     *
     * @param majorBattlePlayerHealth 大作战玩家血量
     * @return 结果
     */
    public int updateMajorBattlePlayerHealth(MajorBattlePlayerHealth majorBattlePlayerHealth);

    /**
     * 删除大作战玩家血量
     *
     * @param id 大作战玩家血量 主键
     * @return 结果
     */
    public int deleteMajorBattlePlayerHealthById(Long id);

    /**
     * 批量删除大作战玩家血量
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMajorBattlePlayerHealthByIds(Long[] ids);
}
