package com.ruoyi.qwk.service.impl;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.qwk.domain.MajorBattlePlayerHealthRecord;
import com.ruoyi.qwk.mapper.MajorBattlePlayerHealthRecordMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.MajorBattlePlayerHealthMapper;
import com.ruoyi.qwk.domain.MajorBattlePlayerHealth;
import com.ruoyi.qwk.service.IMajorBattlePlayerHealthService;

/**
 * 大作战玩家血量 Service业务层处理
 *
 * @author cjf
 * @date 2024-01-15
 */
@Service
public class MajorBattlePlayerHealthServiceImpl implements IMajorBattlePlayerHealthService
{
    @Autowired
    private MajorBattlePlayerHealthMapper majorBattlePlayerHealthMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private MajorBattlePlayerHealthRecordMapper majorBattlePlayerHealthRecordMapper;

    /**
     * 查询大作战玩家血量
     *
     * @param id 大作战玩家血量 主键
     * @return 大作战玩家血量
     */
    @Override
    public MajorBattlePlayerHealth selectMajorBattlePlayerHealthById(Long id)
    {
        return majorBattlePlayerHealthMapper.selectMajorBattlePlayerHealthById(id);
    }

    /**
     * 查询大作战玩家血量 列表
     *
     * @param majorBattlePlayerHealth 大作战玩家血量
     * @return 大作战玩家血量
     */
    @Override
    public List<MajorBattlePlayerHealth> selectMajorBattlePlayerHealthList(MajorBattlePlayerHealth majorBattlePlayerHealth)
    {
        return majorBattlePlayerHealthMapper.selectMajorBattlePlayerHealthList(majorBattlePlayerHealth);
    }

    /**
     * 新增大作战玩家血量
     *
     * @param majorBattlePlayerHealth 大作战玩家血量
     * @return 结果
     */
    @Override
    public int insertMajorBattlePlayerHealth(MajorBattlePlayerHealth majorBattlePlayerHealth)
    {
        majorBattlePlayerHealth.setCreateTime(DateUtils.getNowDate());
        return majorBattlePlayerHealthMapper.insertMajorBattlePlayerHealth(majorBattlePlayerHealth);
    }

    /**
     * 修改大作战玩家血量
     *
     * @param majorBattlePlayerHealth 大作战玩家血量
     * @return 结果
     */
    @Override
    public int updateMajorBattlePlayerHealth(MajorBattlePlayerHealth majorBattlePlayerHealth)
    {
        majorBattlePlayerHealth.setUpdateTime(DateUtils.getNowDate());
        return majorBattlePlayerHealthMapper.updateMajorBattlePlayerHealth(majorBattlePlayerHealth);
    }

    /**
     * 批量删除大作战玩家血量
     *
     * @param ids 需要删除的大作战玩家血量 主键
     * @return 结果
     */
    @Override
    public int deleteMajorBattlePlayerHealthByIds(Long[] ids)
    {
        return majorBattlePlayerHealthMapper.deleteMajorBattlePlayerHealthByIds(ids);
    }

    /**
     * 删除大作战玩家血量 信息
     *
     * @param id 大作战玩家血量 主键
     * @return 结果
     */
    @Override
    public int deleteMajorBattlePlayerHealthById(Long id)
    {
        return majorBattlePlayerHealthMapper.deleteMajorBattlePlayerHealthById(id);
    }

    /**
     * 初始化玩家血量
     * @return
     */
    public AjaxResult initPlayerHealth(String userId,String bloodVolume){
        if(StrUtil.isEmpty(userId) || StrUtil.isEmpty(bloodVolume)){
            return AjaxResult.error("参数必传");
        }
        //获取用户是否存在
        SysUser user = sysUserMapper.selectUserById(Long.parseLong(userId));
        if(ObjUtil.isNull(user)){
            return AjaxResult.error("用户不存在");
        }
        //获取玩家血量是否初始化
        MajorBattlePlayerHealth battlePlayerHealth = new MajorBattlePlayerHealth();
        battlePlayerHealth.setUserId(userId);
        List<MajorBattlePlayerHealth> list = majorBattlePlayerHealthMapper.selectMajorBattlePlayerHealthList(battlePlayerHealth);
        if(CollUtil.isEmpty(list)){
            //初始化玩家血量
            battlePlayerHealth.setBloodVolume(bloodVolume);
            majorBattlePlayerHealthMapper.insertMajorBattlePlayerHealth(battlePlayerHealth);
        }
        return AjaxResult.success();
    }

    /**
     * 扣减玩家血量
     * @return
     */
    public AjaxResult reduceHp(String userId,String reduceHp){
        if(StrUtil.isEmpty(userId) || StrUtil.isEmpty(reduceHp)){
            return AjaxResult.error("参数必传");
        }
        //获取用户是否存在
        SysUser user = sysUserMapper.selectUserById(Long.parseLong(userId));
        if(ObjUtil.isNull(user)){
            return AjaxResult.error("用户不存在");
        }
        //获取玩家血量是否初始化
        MajorBattlePlayerHealth battlePlayerHealth = new MajorBattlePlayerHealth();
        battlePlayerHealth.setUserId(userId);
        List<MajorBattlePlayerHealth> list = majorBattlePlayerHealthMapper.selectMajorBattlePlayerHealthList(battlePlayerHealth);
        if(CollUtil.isEmpty(list)){
            return AjaxResult.error("玩家血量未初始化");
        }
        battlePlayerHealth = list.get(0);
        Long totalBloodVolume = Long.parseLong(battlePlayerHealth.getBloodVolume());
        Long reduceHpNumber =  Long.parseLong(reduceHp);
        Long remainingHp = 0L;
        if(totalBloodVolume-reduceHpNumber >0){
            remainingHp = totalBloodVolume-reduceHpNumber;
        }

        //插入血量变化记录表
        MajorBattlePlayerHealthRecord record = new MajorBattlePlayerHealthRecord();
        record.setUserId(userId);
        record.setReduceHp(reduceHp);
        record.setTotalBloodVolume(battlePlayerHealth.getBloodVolume());
        record.setReduceHp(reduceHp);
        record.setRemainingHp(String.valueOf(remainingHp));
        battlePlayerHealth.setBloodVolume(String.valueOf(remainingHp));
        majorBattlePlayerHealthMapper.updateMajorBattlePlayerHealth(battlePlayerHealth);
        majorBattlePlayerHealthRecordMapper.insertMajorBattlePlayerHealthRecord(record);
        return AjaxResult.success();
    }
}
