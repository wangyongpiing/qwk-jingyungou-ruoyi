package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.AnimalsEquipMapper;
import com.ruoyi.qwk.blindBox.domain.AnimalsEquip;
import com.ruoyi.qwk.blindBox.service.IAnimalsEquipService;

/**
 * 装备配置Service业务层处理
 * 
 * @author cjf
 * @date 2023-11-07
 */
@Service
public class AnimalsEquipServiceImpl implements IAnimalsEquipService 
{
    @Autowired
    private AnimalsEquipMapper animalsEquipMapper;

    /**
     * 查询装备配置
     * 
     * @param id 装备配置主键
     * @return 装备配置
     */
    @Override
    public AnimalsEquip selectAnimalsEquipById(Long id)
    {
        return animalsEquipMapper.selectAnimalsEquipById(id);
    }

    /**
     * 查询装备配置列表
     * 
     * @param animalsEquip 装备配置
     * @return 装备配置
     */
    @Override
    public List<AnimalsEquip> selectAnimalsEquipList(AnimalsEquip animalsEquip)
    {
        return animalsEquipMapper.selectAnimalsEquipList(animalsEquip);
    }

    /**
     * 新增装备配置
     * 
     * @param animalsEquip 装备配置
     * @return 结果
     */
    @Override
    public int insertAnimalsEquip(AnimalsEquip animalsEquip)
    {
        animalsEquip.setCreateTime(DateUtils.getNowDate());
        return animalsEquipMapper.insertAnimalsEquip(animalsEquip);
    }

    /**
     * 修改装备配置
     * 
     * @param animalsEquip 装备配置
     * @return 结果
     */
    @Override
    public int updateAnimalsEquip(AnimalsEquip animalsEquip)
    {
        animalsEquip.setUpdateTime(DateUtils.getNowDate());
        return animalsEquipMapper.updateAnimalsEquip(animalsEquip);
    }

    /**
     * 批量删除装备配置
     * 
     * @param ids 需要删除的装备配置主键
     * @return 结果
     */
    @Override
    public int deleteAnimalsEquipByIds(Long[] ids)
    {
        return animalsEquipMapper.deleteAnimalsEquipByIds(ids);
    }

    /**
     * 删除装备配置信息
     * 
     * @param id 装备配置主键
     * @return 结果
     */
    @Override
    public int deleteAnimalsEquipById(Long id)
    {
        return animalsEquipMapper.deleteAnimalsEquipById(id);
    }
}
