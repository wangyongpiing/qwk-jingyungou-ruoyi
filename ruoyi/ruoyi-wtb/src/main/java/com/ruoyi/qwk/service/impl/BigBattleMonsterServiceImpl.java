package com.ruoyi.qwk.service.impl;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.BigBattleMonsterMapper;
import com.ruoyi.qwk.domain.BigBattleMonster;
import com.ruoyi.qwk.service.IBigBattleMonsterService;

/**
 *  大作战怪物Service业务层处理
 *
 * @author cjf
 * @date 2024-01-15
 */
@Service
public class BigBattleMonsterServiceImpl implements IBigBattleMonsterService
{
    @Autowired
    private BigBattleMonsterMapper bigBattleMonsterMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询 大作战怪物
     *
     * @param id  大作战怪物主键
     * @return  大作战怪物
     */
    @Override
    public BigBattleMonster selectBigBattleMonsterById(Long id)
    {
        return bigBattleMonsterMapper.selectBigBattleMonsterById(id);
    }

    /**
     * 查询 大作战怪物列表
     *
     * @param bigBattleMonster  大作战怪物
     * @return  大作战怪物
     */
    @Override
    public List<BigBattleMonster> selectBigBattleMonsterList(BigBattleMonster bigBattleMonster)
    {
        return bigBattleMonsterMapper.selectBigBattleMonsterList(bigBattleMonster);
    }

    /**
     * 新增 大作战怪物
     *
     * @param bigBattleMonster  大作战怪物
     * @return 结果
     */
    @Override
    public int insertBigBattleMonster(BigBattleMonster bigBattleMonster)
    {
        bigBattleMonster.setCreateTime(DateUtils.getNowDate());
        return bigBattleMonsterMapper.insertBigBattleMonster(bigBattleMonster);
    }

    /**
     * 修改 大作战怪物
     *
     * @param bigBattleMonster  大作战怪物
     * @return 结果
     */
    @Override
    public int updateBigBattleMonster(BigBattleMonster bigBattleMonster)
    {
        bigBattleMonster.setUpdateTime(DateUtils.getNowDate());
        return bigBattleMonsterMapper.updateBigBattleMonster(bigBattleMonster);
    }

    /**
     * 批量删除 大作战怪物
     *
     * @param ids 需要删除的 大作战怪物主键
     * @return 结果
     */
    @Override
    public int deleteBigBattleMonsterByIds(Long[] ids)
    {
        return bigBattleMonsterMapper.deleteBigBattleMonsterByIds(ids);
    }

    /**
     * 删除 大作战怪物信息
     *
     * @param id  大作战怪物主键
     * @return 结果
     */
    @Override
    public int deleteBigBattleMonsterById(Long id)
    {
        return bigBattleMonsterMapper.deleteBigBattleMonsterById(id);
    }

    /**
     * 怪物生成
     * @param userId
     * @param monsterId
     * @return
     */
    public AjaxResult generateMonsters(String userId, String monsterId){
        if(StrUtil.isEmpty(userId) || StrUtil.isEmpty(monsterId)){
            return AjaxResult.error("参数必传");
        }
        //获取用户是否存在
        SysUser user = sysUserMapper.selectUserById(Long.parseLong(userId));
        if(ObjUtil.isNull(user)){
            return AjaxResult.error("用户不存在");
        }
        //获取玩家，怪物是否已生成
        BigBattleMonster mon = new BigBattleMonster();
        mon.setUserId(userId);
        mon.setCode(monsterId);
        List<BigBattleMonster> list = bigBattleMonsterMapper.selectBigBattleMonsterList(mon);
        if(CollUtil.isNotEmpty(list)){
            return AjaxResult.error("怪物已生成，请勿重复操作");
        }
        mon.setGenerationTime(DateUtil.date());
        bigBattleMonsterMapper.insertBigBattleMonster(mon);
        return AjaxResult.success();
    }

    /**
     * 怪物死亡
     * @param userId
     * @param monsterId
     * @return
     */
    public AjaxResult deathMonsters(String userId,String monsterId){
        if(StrUtil.isEmpty(userId) || StrUtil.isEmpty(monsterId)){
            return AjaxResult.error("参数必传");
        }
        //获取用户是否存在
        SysUser user = sysUserMapper.selectUserById(Long.parseLong(userId));
        if(ObjUtil.isNull(user)){
            return AjaxResult.error("用户不存在");
        }
        //获取玩家，怪物是否已生成
        BigBattleMonster mon = new BigBattleMonster();
        mon.setUserId(userId);
        mon.setCode(monsterId);
        List<BigBattleMonster> list = bigBattleMonsterMapper.selectBigBattleMonsterList(mon);
        if(CollUtil.isEmpty(list)){
            return AjaxResult.error("怪物未生成");
        }
        mon = list.get(0);
        mon.setDeathTime(DateUtil.date());
        bigBattleMonsterMapper.updateBigBattleMonster(mon);
        return AjaxResult.success();
    }
}
