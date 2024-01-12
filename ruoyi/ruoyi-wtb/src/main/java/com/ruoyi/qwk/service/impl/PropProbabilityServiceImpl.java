package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.PropProbabilityMapper;
import com.ruoyi.qwk.domain.PropProbability;
import com.ruoyi.qwk.service.IPropProbabilityService;

/**
 * 道具爆率管理Service业务层处理
 * 
 * @author cjf
 * @date 2023-07-31
 */
@Service
public class PropProbabilityServiceImpl implements IPropProbabilityService 
{
    @Autowired
    private PropProbabilityMapper propProbabilityMapper;

    /**
     * 查询道具爆率管理
     * 
     * @param id 道具爆率管理主键
     * @return 道具爆率管理
     */
    @Override
    public PropProbability selectPropProbabilityById(Long id)
    {
        return propProbabilityMapper.selectPropProbabilityById(id);
    }

    /**
     * 查询道具爆率管理列表
     * 
     * @param propProbability 道具爆率管理
     * @return 道具爆率管理
     */
    @Override
    public List<PropProbability> selectPropProbabilityList(PropProbability propProbability)
    {
        return propProbabilityMapper.selectPropProbabilityList(propProbability);
    }

    /**
     * 新增道具爆率管理
     * 
     * @param propProbability 道具爆率管理
     * @return 结果
     */
    @Override
    public int insertPropProbability(PropProbability propProbability)
    {
        propProbability.setCreateBy(SecurityUtils.getUsername());
        propProbability.setCreateTime(DateUtils.getNowDate());
        return propProbabilityMapper.insertPropProbability(propProbability);
    }

    /**
     * 修改道具爆率管理
     * 
     * @param propProbability 道具爆率管理
     * @return 结果
     */
    @Override
    public int updatePropProbability(PropProbability propProbability)
    {
        propProbability.setUpdateBy(SecurityUtils.getUsername());
        propProbability.setUpdateTime(DateUtils.getNowDate());
        return propProbabilityMapper.updatePropProbability(propProbability);
    }

    /**
     * 批量删除道具爆率管理
     * 
     * @param ids 需要删除的道具爆率管理主键
     * @return 结果
     */
    @Override
    public int deletePropProbabilityByIds(Long[] ids)
    {
        return propProbabilityMapper.deletePropProbabilityByIds(ids);
    }

    /**
     * 删除道具爆率管理信息
     * 
     * @param id 道具爆率管理主键
     * @return 结果
     */
    @Override
    public int deletePropProbabilityById(Long id)
    {
        return propProbabilityMapper.deletePropProbabilityById(id);
    }
}
