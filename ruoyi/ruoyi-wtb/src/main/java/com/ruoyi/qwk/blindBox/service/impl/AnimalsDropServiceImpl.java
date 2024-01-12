package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.AnimalsDropMapper;
import com.ruoyi.qwk.blindBox.domain.AnimalsDrop;
import com.ruoyi.qwk.blindBox.service.IAnimalsDropService;

/**
 * 掉落配置Service业务层处理
 * 
 * @author cjf
 * @date 2023-11-08
 */
@Service
public class AnimalsDropServiceImpl implements IAnimalsDropService 
{
    @Autowired
    private AnimalsDropMapper animalsDropMapper;

    /**
     * 查询掉落配置
     * 
     * @param id 掉落配置主键
     * @return 掉落配置
     */
    @Override
    public AnimalsDrop selectAnimalsDropById(Integer id)
    {
        return animalsDropMapper.selectAnimalsDropById(id);
    }

    /**
     * 查询掉落配置列表
     * 
     * @param animalsDrop 掉落配置
     * @return 掉落配置
     */
    @Override
    public List<AnimalsDrop> selectAnimalsDropList(AnimalsDrop animalsDrop)
    {
        return animalsDropMapper.selectAnimalsDropList(animalsDrop);
    }

    /**
     * 新增掉落配置
     * 
     * @param animalsDrop 掉落配置
     * @return 结果
     */
    @Override
    public int insertAnimalsDrop(AnimalsDrop animalsDrop)
    {
        animalsDrop.setCreateTime(DateUtils.getNowDate());
        return animalsDropMapper.insertAnimalsDrop(animalsDrop);
    }

    /**
     * 修改掉落配置
     * 
     * @param animalsDrop 掉落配置
     * @return 结果
     */
    @Override
    public int updateAnimalsDrop(AnimalsDrop animalsDrop)
    {
        animalsDrop.setUpdateTime(DateUtils.getNowDate());
        return animalsDropMapper.updateAnimalsDrop(animalsDrop);
    }

    /**
     * 批量删除掉落配置
     * 
     * @param ids 需要删除的掉落配置主键
     * @return 结果
     */
    @Override
    public int deleteAnimalsDropByIds(Integer[] ids)
    {
        return animalsDropMapper.deleteAnimalsDropByIds(ids);
    }

    /**
     * 删除掉落配置信息
     * 
     * @param id 掉落配置主键
     * @return 结果
     */
    @Override
    public int deleteAnimalsDropById(Integer id)
    {
        return animalsDropMapper.deleteAnimalsDropById(id);
    }
}
