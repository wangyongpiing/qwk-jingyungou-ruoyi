package com.ruoyi.qwk.blindBox.service;

import java.util.List;
import com.ruoyi.qwk.blindBox.domain.AnimalsDrop;

/**
 * 掉落配置Service接口
 * 
 * @author cjf
 * @date 2023-11-08
 */
public interface IAnimalsDropService 
{
    /**
     * 查询掉落配置
     * 
     * @param id 掉落配置主键
     * @return 掉落配置
     */
    public AnimalsDrop selectAnimalsDropById(Integer id);

    /**
     * 查询掉落配置列表
     * 
     * @param animalsDrop 掉落配置
     * @return 掉落配置集合
     */
    public List<AnimalsDrop> selectAnimalsDropList(AnimalsDrop animalsDrop);

    /**
     * 新增掉落配置
     * 
     * @param animalsDrop 掉落配置
     * @return 结果
     */
    public int insertAnimalsDrop(AnimalsDrop animalsDrop);

    /**
     * 修改掉落配置
     * 
     * @param animalsDrop 掉落配置
     * @return 结果
     */
    public int updateAnimalsDrop(AnimalsDrop animalsDrop);

    /**
     * 批量删除掉落配置
     * 
     * @param ids 需要删除的掉落配置主键集合
     * @return 结果
     */
    public int deleteAnimalsDropByIds(Integer[] ids);

    /**
     * 删除掉落配置信息
     * 
     * @param id 掉落配置主键
     * @return 结果
     */
    public int deleteAnimalsDropById(Integer id);
}
