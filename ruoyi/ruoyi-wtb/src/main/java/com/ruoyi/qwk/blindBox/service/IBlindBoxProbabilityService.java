package com.ruoyi.qwk.blindBox.service;

import java.util.List;
import com.ruoyi.qwk.blindBox.domain.BlindBoxProbability;

/**
 * 盲盒概率Service接口
 * 
 * @author cjf
 * @date 2023-10-08
 */
public interface IBlindBoxProbabilityService 
{
    /**
     * 查询盲盒概率
     * 
     * @param id 盲盒概率主键
     * @return 盲盒概率
     */
    public BlindBoxProbability selectBlindBoxProbabilityById(Long id);

    /**
     * 查询盲盒概率列表
     * 
     * @param blindBoxProbability 盲盒概率
     * @return 盲盒概率集合
     */
    public List<BlindBoxProbability> selectBlindBoxProbabilityList(BlindBoxProbability blindBoxProbability);

    /**
     * 新增盲盒概率
     * 
     * @param blindBoxProbability 盲盒概率
     * @return 结果
     */
    public int insertBlindBoxProbability(BlindBoxProbability blindBoxProbability);

    /**
     * 修改盲盒概率
     * 
     * @param blindBoxProbability 盲盒概率
     * @return 结果
     */
    public int updateBlindBoxProbability(BlindBoxProbability blindBoxProbability);

    /**
     * 批量删除盲盒概率
     * 
     * @param ids 需要删除的盲盒概率主键集合
     * @return 结果
     */
    public int deleteBlindBoxProbabilityByIds(Long[] ids);

    /**
     * 删除盲盒概率信息
     * 
     * @param id 盲盒概率主键
     * @return 结果
     */
    public int deleteBlindBoxProbabilityById(Long id);
}
