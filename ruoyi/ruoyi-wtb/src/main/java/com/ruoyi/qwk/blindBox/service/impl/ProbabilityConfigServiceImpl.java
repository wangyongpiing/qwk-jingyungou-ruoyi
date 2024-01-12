package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.ProbabilityConfigMapper;
import com.ruoyi.qwk.blindBox.domain.ProbabilityConfig;
import com.ruoyi.qwk.blindBox.service.IProbabilityConfigService;

/**
 * 每种物品的中奖概率配置Service业务层处理
 * 
 * @author cjf
 * @date 2023-10-23
 */
@Service
public class ProbabilityConfigServiceImpl implements IProbabilityConfigService 
{
    @Autowired
    private ProbabilityConfigMapper probabilityConfigMapper;

    /**
     * 查询每种物品的中奖概率配置
     * 
     * @param id 每种物品的中奖概率配置主键
     * @return 每种物品的中奖概率配置
     */
    @Override
    public ProbabilityConfig selectProbabilityConfigById(Long id)
    {
        return probabilityConfigMapper.selectProbabilityConfigById(id);
    }

    /**
     * 查询每种物品的中奖概率配置列表
     * 
     * @param probabilityConfig 每种物品的中奖概率配置
     * @return 每种物品的中奖概率配置
     */
    @Override
    public List<ProbabilityConfig> selectProbabilityConfigList(ProbabilityConfig probabilityConfig)
    {
        return probabilityConfigMapper.selectProbabilityConfigList(probabilityConfig);
    }

    /**
     * 新增每种物品的中奖概率配置
     * 
     * @param probabilityConfig 每种物品的中奖概率配置
     * @return 结果
     */
    @Override
    public int insertProbabilityConfig(ProbabilityConfig probabilityConfig)
    {
        probabilityConfig.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        probabilityConfig.setCreateTime(DateUtils.getNowDate());
        return probabilityConfigMapper.insertProbabilityConfig(probabilityConfig);
    }

    /**
     * 修改每种物品的中奖概率配置
     * 
     * @param probabilityConfig 每种物品的中奖概率配置
     * @return 结果
     */
    @Override
    public int updateProbabilityConfig(ProbabilityConfig probabilityConfig)
    {
        probabilityConfig.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        probabilityConfig.setUpdateTime(DateUtils.getNowDate());
        return probabilityConfigMapper.updateProbabilityConfig(probabilityConfig);
    }

    /**
     * 批量删除每种物品的中奖概率配置
     * 
     * @param ids 需要删除的每种物品的中奖概率配置主键
     * @return 结果
     */
    @Override
    public int deleteProbabilityConfigByIds(Long[] ids)
    {
        return probabilityConfigMapper.deleteProbabilityConfigByIds(ids);
    }

    /**
     * 删除每种物品的中奖概率配置信息
     * 
     * @param id 每种物品的中奖概率配置主键
     * @return 结果
     */
    @Override
    public int deleteProbabilityConfigById(Long id)
    {
        return probabilityConfigMapper.deleteProbabilityConfigById(id);
    }
}
