package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.MajorBattlePlayerHealth;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 大作战玩家血量 Service接口
 *
 * @author cjf
 * @date 2024-01-15
 */
public interface IMajorBattlePlayerHealthService
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
     * 批量删除大作战玩家血量
     *
     * @param ids 需要删除的大作战玩家血量 主键集合
     * @return 结果
     */
    public int deleteMajorBattlePlayerHealthByIds(Long[] ids);

    /**
     * 删除大作战玩家血量 信息
     *
     * @param id 大作战玩家血量 主键
     * @return 结果
     */
    public int deleteMajorBattlePlayerHealthById(Long id);

    /**
     * 初始化玩家血量
     * @return
     */
    public AjaxResult initPlayerHealth(String userId,String bloodVolume);

    /**
     * 扣减玩家血量
     * @return
     */
    public AjaxResult reduceHp(String userId,String reduceHp);
}
