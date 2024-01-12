package com.ruoyi.qwk.blindBox.service;

import java.util.List;
import com.ruoyi.qwk.blindBox.domain.AnimalsSkill;

/**
 * 技能配置Service接口
 * 
 * @author cjf
 * @date 2023-11-08
 */
public interface IAnimalsSkillService 
{
    /**
     * 查询技能配置
     * 
     * @param id 技能配置主键
     * @return 技能配置
     */
    public AnimalsSkill selectAnimalsSkillById(Long id);

    /**
     * 查询技能配置列表
     * 
     * @param animalsSkill 技能配置
     * @return 技能配置集合
     */
    public List<AnimalsSkill> selectAnimalsSkillList(AnimalsSkill animalsSkill);

    /**
     * 新增技能配置
     * 
     * @param animalsSkill 技能配置
     * @return 结果
     */
    public int insertAnimalsSkill(AnimalsSkill animalsSkill);

    /**
     * 修改技能配置
     * 
     * @param animalsSkill 技能配置
     * @return 结果
     */
    public int updateAnimalsSkill(AnimalsSkill animalsSkill);

    /**
     * 批量删除技能配置
     * 
     * @param ids 需要删除的技能配置主键集合
     * @return 结果
     */
    public int deleteAnimalsSkillByIds(Long[] ids);

    /**
     * 删除技能配置信息
     * 
     * @param id 技能配置主键
     * @return 结果
     */
    public int deleteAnimalsSkillById(Long id);
}
