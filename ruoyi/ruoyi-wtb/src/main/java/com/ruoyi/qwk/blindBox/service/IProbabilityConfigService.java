package com.ruoyi.qwk.blindBox.service;

import java.util.List;
import com.ruoyi.qwk.blindBox.domain.ProbabilityConfig;

/**
 * 每种物品的中奖概率配置Service接口
 * 
 * @author cjf
 * @date 2023-10-23
 */
public interface IProbabilityConfigService 
{
    /**
     * 查询每种物品的中奖概率配置
     * 
     * @param id 每种物品的中奖概率配置主键
     * @return 每种物品的中奖概率配置
     */
    public ProbabilityConfig selectProbabilityConfigById(Long id);

    /**
     * 查询每种物品的中奖概率配置列表
     * 
     * @param probabilityConfig 每种物品的中奖概率配置
     * @return 每种物品的中奖概率配置集合
     */
    public List<ProbabilityConfig> selectProbabilityConfigList(ProbabilityConfig probabilityConfig);

    /**
     * 新增每种物品的中奖概率配置
     * 
     * @param probabilityConfig 每种物品的中奖概率配置
     * @return 结果
     */
    public int insertProbabilityConfig(ProbabilityConfig probabilityConfig);

    /**
     * 修改每种物品的中奖概率配置
     * 
     * @param probabilityConfig 每种物品的中奖概率配置
     * @return 结果
     */
    public int updateProbabilityConfig(ProbabilityConfig probabilityConfig);

    /**
     * 批量删除每种物品的中奖概率配置
     * 
     * @param ids 需要删除的每种物品的中奖概率配置主键集合
     * @return 结果
     */
    public int deleteProbabilityConfigByIds(Long[] ids);

    /**
     * 删除每种物品的中奖概率配置信息
     * 
     * @param id 每种物品的中奖概率配置主键
     * @return 结果
     */
    public int deleteProbabilityConfigById(Long id);
}
