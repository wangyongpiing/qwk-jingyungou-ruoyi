package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.AnimalsSkin;

/**
 * 皮肤配置Mapper接口
 * 
 * @author cjf
 * @date 2023-11-07
 */
@DataSource(value = DataSourceType.SLAVE)
public interface AnimalsSkinMapper 
{
    /**
     * 查询皮肤配置
     * 
     * @param id 皮肤配置主键
     * @return 皮肤配置
     */
    public AnimalsSkin selectAnimalsSkinById(Long id);

    /**
     * 查询皮肤配置列表
     * 
     * @param animalsSkin 皮肤配置
     * @return 皮肤配置集合
     */
    public List<AnimalsSkin> selectAnimalsSkinList(AnimalsSkin animalsSkin);

    /**
     * 新增皮肤配置
     * 
     * @param animalsSkin 皮肤配置
     * @return 结果
     */
    public int insertAnimalsSkin(AnimalsSkin animalsSkin);

    /**
     * 修改皮肤配置
     * 
     * @param animalsSkin 皮肤配置
     * @return 结果
     */
    public int updateAnimalsSkin(AnimalsSkin animalsSkin);

    /**
     * 删除皮肤配置
     * 
     * @param id 皮肤配置主键
     * @return 结果
     */
    public int deleteAnimalsSkinById(Long id);

    /**
     * 批量删除皮肤配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnimalsSkinByIds(Long[] ids);
}
