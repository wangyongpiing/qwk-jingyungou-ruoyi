package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.BlindBoxProbabilityMapper;
import com.ruoyi.qwk.blindBox.domain.BlindBoxProbability;
import com.ruoyi.qwk.blindBox.service.IBlindBoxProbabilityService;

/**
 * 盲盒概率Service业务层处理
 * 
 * @author cjf
 * @date 2023-10-08
 */
@Service
public class BlindBoxProbabilityServiceImpl implements IBlindBoxProbabilityService 
{
    @Autowired
    private BlindBoxProbabilityMapper blindBoxProbabilityMapper;

    /**
     * 查询盲盒概率
     * 
     * @param id 盲盒概率主键
     * @return 盲盒概率
     */
    @Override
    public BlindBoxProbability selectBlindBoxProbabilityById(Long id)
    {
        return blindBoxProbabilityMapper.selectBlindBoxProbabilityById(id);
    }

    /**
     * 查询盲盒概率列表
     * 
     * @param blindBoxProbability 盲盒概率
     * @return 盲盒概率
     */
    @Override
    public List<BlindBoxProbability> selectBlindBoxProbabilityList(BlindBoxProbability blindBoxProbability)
    {
        return blindBoxProbabilityMapper.selectBlindBoxProbabilityList(blindBoxProbability);
    }

    /**
     * 新增盲盒概率
     * 
     * @param blindBoxProbability 盲盒概率
     * @return 结果
     */
    @Override
    public int insertBlindBoxProbability(BlindBoxProbability blindBoxProbability)
    {
        blindBoxProbability.setCreateTime(DateUtils.getNowDate());
        return blindBoxProbabilityMapper.insertBlindBoxProbability(blindBoxProbability);
    }

    /**
     * 修改盲盒概率
     * 
     * @param blindBoxProbability 盲盒概率
     * @return 结果
     */
    @Override
    public int updateBlindBoxProbability(BlindBoxProbability blindBoxProbability)
    {
        blindBoxProbability.setUpdateTime(DateUtils.getNowDate());
        return blindBoxProbabilityMapper.updateBlindBoxProbability(blindBoxProbability);
    }

    /**
     * 批量删除盲盒概率
     * 
     * @param ids 需要删除的盲盒概率主键
     * @return 结果
     */
    @Override
    public int deleteBlindBoxProbabilityByIds(Long[] ids)
    {
        return blindBoxProbabilityMapper.deleteBlindBoxProbabilityByIds(ids);
    }

    /**
     * 删除盲盒概率信息
     * 
     * @param id 盲盒概率主键
     * @return 结果
     */
    @Override
    public int deleteBlindBoxProbabilityById(Long id)
    {
        return blindBoxProbabilityMapper.deleteBlindBoxProbabilityById(id);
    }
}
