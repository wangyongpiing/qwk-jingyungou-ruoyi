package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.AnimalsEquip;

/**
 * 装备配置Mapper接口
 * 
 * @author cjf
 * @date 2023-11-07
 */
@DataSource(value = DataSourceType.SLAVE)
public interface AnimalsEquipMapper 
{
    /**
     * 查询装备配置
     * 
     * @param id 装备配置主键
     * @return 装备配置
     */
    public AnimalsEquip selectAnimalsEquipById(Long id);

    /**
     * 查询装备配置列表
     * 
     * @param animalsEquip 装备配置
     * @return 装备配置集合
     */
    public List<AnimalsEquip> selectAnimalsEquipList(AnimalsEquip animalsEquip);

    /**
     * 新增装备配置
     * 
     * @param animalsEquip 装备配置
     * @return 结果
     */
    public int insertAnimalsEquip(AnimalsEquip animalsEquip);

    /**
     * 修改装备配置
     * 
     * @param animalsEquip 装备配置
     * @return 结果
     */
    public int updateAnimalsEquip(AnimalsEquip animalsEquip);

    /**
     * 删除装备配置
     * 
     * @param id 装备配置主键
     * @return 结果
     */
    public int deleteAnimalsEquipById(Long id);

    /**
     * 批量删除装备配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnimalsEquipByIds(Long[] ids);
}
