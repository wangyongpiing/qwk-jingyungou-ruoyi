package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.AnimalsSkinMapper;
import com.ruoyi.qwk.blindBox.domain.AnimalsSkin;
import com.ruoyi.qwk.blindBox.service.IAnimalsSkinService;

/**
 * 皮肤配置Service业务层处理
 * 
 * @author cjf
 * @date 2023-11-07
 */
@Service
public class AnimalsSkinServiceImpl implements IAnimalsSkinService 
{
    @Autowired
    private AnimalsSkinMapper animalsSkinMapper;

    /**
     * 查询皮肤配置
     * 
     * @param id 皮肤配置主键
     * @return 皮肤配置
     */
    @Override
    public AnimalsSkin selectAnimalsSkinById(Long id)
    {
        return animalsSkinMapper.selectAnimalsSkinById(id);
    }

    /**
     * 查询皮肤配置列表
     * 
     * @param animalsSkin 皮肤配置
     * @return 皮肤配置
     */
    @Override
    public List<AnimalsSkin> selectAnimalsSkinList(AnimalsSkin animalsSkin)
    {
        return animalsSkinMapper.selectAnimalsSkinList(animalsSkin);
    }

    /**
     * 新增皮肤配置
     * 
     * @param animalsSkin 皮肤配置
     * @return 结果
     */
    @Override
    public int insertAnimalsSkin(AnimalsSkin animalsSkin)
    {
        animalsSkin.setCreateTime(DateUtils.getNowDate());
        return animalsSkinMapper.insertAnimalsSkin(animalsSkin);
    }

    /**
     * 修改皮肤配置
     * 
     * @param animalsSkin 皮肤配置
     * @return 结果
     */
    @Override
    public int updateAnimalsSkin(AnimalsSkin animalsSkin)
    {
        animalsSkin.setUpdateTime(DateUtils.getNowDate());
        return animalsSkinMapper.updateAnimalsSkin(animalsSkin);
    }

    /**
     * 批量删除皮肤配置
     * 
     * @param ids 需要删除的皮肤配置主键
     * @return 结果
     */
    @Override
    public int deleteAnimalsSkinByIds(Long[] ids)
    {
        return animalsSkinMapper.deleteAnimalsSkinByIds(ids);
    }

    /**
     * 删除皮肤配置信息
     * 
     * @param id 皮肤配置主键
     * @return 结果
     */
    @Override
    public int deleteAnimalsSkinById(Long id)
    {
        return animalsSkinMapper.deleteAnimalsSkinById(id);
    }
}
