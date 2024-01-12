package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.AnimalsSkillMapper;
import com.ruoyi.qwk.blindBox.domain.AnimalsSkill;
import com.ruoyi.qwk.blindBox.service.IAnimalsSkillService;

/**
 * 技能配置Service业务层处理
 * 
 * @author cjf
 * @date 2023-11-08
 */
@Service
public class AnimalsSkillServiceImpl implements IAnimalsSkillService 
{
    @Autowired
    private AnimalsSkillMapper animalsSkillMapper;

    /**
     * 查询技能配置
     * 
     * @param id 技能配置主键
     * @return 技能配置
     */
    @Override
    public AnimalsSkill selectAnimalsSkillById(Long id)
    {
        return animalsSkillMapper.selectAnimalsSkillById(id);
    }

    /**
     * 查询技能配置列表
     * 
     * @param animalsSkill 技能配置
     * @return 技能配置
     */
    @Override
    public List<AnimalsSkill> selectAnimalsSkillList(AnimalsSkill animalsSkill)
    {
        return animalsSkillMapper.selectAnimalsSkillList(animalsSkill);
    }

    /**
     * 新增技能配置
     * 
     * @param animalsSkill 技能配置
     * @return 结果
     */
    @Override
    public int insertAnimalsSkill(AnimalsSkill animalsSkill)
    {
        animalsSkill.setCreateTime(DateUtils.getNowDate());
        return animalsSkillMapper.insertAnimalsSkill(animalsSkill);
    }

    /**
     * 修改技能配置
     * 
     * @param animalsSkill 技能配置
     * @return 结果
     */
    @Override
    public int updateAnimalsSkill(AnimalsSkill animalsSkill)
    {
        animalsSkill.setUpdateTime(DateUtils.getNowDate());
        return animalsSkillMapper.updateAnimalsSkill(animalsSkill);
    }

    /**
     * 批量删除技能配置
     * 
     * @param ids 需要删除的技能配置主键
     * @return 结果
     */
    @Override
    public int deleteAnimalsSkillByIds(Long[] ids)
    {
        return animalsSkillMapper.deleteAnimalsSkillByIds(ids);
    }

    /**
     * 删除技能配置信息
     * 
     * @param id 技能配置主键
     * @return 结果
     */
    @Override
    public int deleteAnimalsSkillById(Long id)
    {
        return animalsSkillMapper.deleteAnimalsSkillById(id);
    }
}
