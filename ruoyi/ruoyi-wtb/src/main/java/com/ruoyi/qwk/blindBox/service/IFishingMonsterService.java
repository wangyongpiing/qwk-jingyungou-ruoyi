package com.ruoyi.qwk.blindBox.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.blindBox.domain.FishingMonster;

/**
 * 捕鱼怪物Service接口
 *
 * @author cjf
 * @date 2023-09-14
 */
public interface IFishingMonsterService
{
    /**
     * 查询捕鱼怪物
     *
     * @param id 捕鱼怪物主键
     * @return 捕鱼怪物
     */
    public FishingMonster selectFishingMonsterById(Long id);

    /**
     * 查询捕鱼怪物列表
     *
     * @param fishingMonster 捕鱼怪物
     * @return 捕鱼怪物集合
     */
    public List<FishingMonster> selectFishingMonsterList(FishingMonster fishingMonster);

    /**
     * 新增捕鱼怪物
     *
     * @param fishingMonster 捕鱼怪物
     * @return 结果
     */
    public int insertFishingMonster(FishingMonster fishingMonster);

    /**
     * 修改捕鱼怪物
     *
     * @param fishingMonster 捕鱼怪物
     * @return 结果
     */
    public int updateFishingMonster(FishingMonster fishingMonster);

    /**
     * 批量删除捕鱼怪物
     *
     * @param ids 需要删除的捕鱼怪物主键集合
     * @return 结果
     */
    public int deleteFishingMonsterByIds(Long[] ids);

    /**
     * 删除捕鱼怪物信息
     *
     * @param id 捕鱼怪物主键
     * @return 结果
     */
    public int deleteFishingMonsterById(Long id);

    /**
     *
     * 查询所有盲盒怪物
     * */
    public List<FishingMonster> getAllFishingMonster();

    /**
     *
     * 判断盲盒是否死亡以及掉落奖励
     * */
    public AjaxResult ifDeath(List<Long> ids);

    /**
     * 判断盲盒是否掉落物品
     *
     * */
    public AjaxResult isFalling(FishingMonster fm);

    /**
     * 获取初级场
     * */
    public List<FishingMonster> getElementary();

    /**
     * 获取中级场
     * */
    public List<FishingMonster> getIntermediate();

    /**
     * 获取高级场
     * */
    public List<FishingMonster> getSenior();

    /**
     * 新手场掉落
     *
     * */
    public AjaxResult noviceFalling();

//    /**
//     * 新手场奖品
//     *
//     * */
//    public AjaxResult novicePrize();

    /**
     *
     * 修改用户为老用户
     * */
    public AjaxResult updateByNovice(Long userId);

    /**
     * 判断盲盒是否掉落物品 新公式
     * */
    public AjaxResult isFallingTwo(FishingMonster fm);

    /**
     * 盲盒概率第四版
     * */
    public AjaxResult isFallingFour(FishingMonster fm);

}
