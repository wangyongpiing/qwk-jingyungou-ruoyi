package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.AnimalsMonster;

/**
 * 怪物配置Mapper接口
 * 
 * @author cjf
 * @date 2023-11-08
 */
@DataSource(value = DataSourceType.SLAVE)
public interface AnimalsMonsterMapper 
{
    /**
     * 查询怪物配置
     * 
     * @param id 怪物配置主键
     * @return 怪物配置
     */
    public AnimalsMonster selectAnimalsMonsterById(Long id);

    /**
     * 查询怪物配置列表
     * 
     * @param animalsMonster 怪物配置
     * @return 怪物配置集合
     */
    public List<AnimalsMonster> selectAnimalsMonsterList(AnimalsMonster animalsMonster);

    /**
     * 新增怪物配置
     * 
     * @param animalsMonster 怪物配置
     * @return 结果
     */
    public int insertAnimalsMonster(AnimalsMonster animalsMonster);

    /**
     * 修改怪物配置
     * 
     * @param animalsMonster 怪物配置
     * @return 结果
     */
    public int updateAnimalsMonster(AnimalsMonster animalsMonster);

    /**
     * 删除怪物配置
     * 
     * @param id 怪物配置主键
     * @return 结果
     */
    public int deleteAnimalsMonsterById(Long id);

    /**
     * 批量删除怪物配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnimalsMonsterByIds(Long[] ids);
}
