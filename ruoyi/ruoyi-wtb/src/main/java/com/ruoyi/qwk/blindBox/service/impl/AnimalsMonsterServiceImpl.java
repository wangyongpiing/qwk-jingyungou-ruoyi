package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.AnimalsMonsterMapper;
import com.ruoyi.qwk.blindBox.domain.AnimalsMonster;
import com.ruoyi.qwk.blindBox.service.IAnimalsMonsterService;

/**
 * 怪物配置Service业务层处理
 * 
 * @author cjf
 * @date 2023-11-08
 */
@Service
public class AnimalsMonsterServiceImpl implements IAnimalsMonsterService 
{
    @Autowired
    private AnimalsMonsterMapper animalsMonsterMapper;

    /**
     * 查询怪物配置
     * 
     * @param id 怪物配置主键
     * @return 怪物配置
     */
    @Override
    public AnimalsMonster selectAnimalsMonsterById(Long id)
    {
        return animalsMonsterMapper.selectAnimalsMonsterById(id);
    }

    /**
     * 查询怪物配置列表
     * 
     * @param animalsMonster 怪物配置
     * @return 怪物配置
     */
    @Override
    public List<AnimalsMonster> selectAnimalsMonsterList(AnimalsMonster animalsMonster)
    {
        return animalsMonsterMapper.selectAnimalsMonsterList(animalsMonster);
    }

    /**
     * 新增怪物配置
     * 
     * @param animalsMonster 怪物配置
     * @return 结果
     */
    @Override
    public int insertAnimalsMonster(AnimalsMonster animalsMonster)
    {
        animalsMonster.setCreateTime(DateUtils.getNowDate());
        return animalsMonsterMapper.insertAnimalsMonster(animalsMonster);
    }

    /**
     * 修改怪物配置
     * 
     * @param animalsMonster 怪物配置
     * @return 结果
     */
    @Override
    public int updateAnimalsMonster(AnimalsMonster animalsMonster)
    {
        animalsMonster.setUpdateTime(DateUtils.getNowDate());
        return animalsMonsterMapper.updateAnimalsMonster(animalsMonster);
    }

    /**
     * 批量删除怪物配置
     * 
     * @param ids 需要删除的怪物配置主键
     * @return 结果
     */
    @Override
    public int deleteAnimalsMonsterByIds(Long[] ids)
    {
        return animalsMonsterMapper.deleteAnimalsMonsterByIds(ids);
    }

    /**
     * 删除怪物配置信息
     * 
     * @param id 怪物配置主键
     * @return 结果
     */
    @Override
    public int deleteAnimalsMonsterById(Long id)
    {
        return animalsMonsterMapper.deleteAnimalsMonsterById(id);
    }
}
