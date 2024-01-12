package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.BlindBoxProbability;

/**
 * 盲盒概率Mapper接口
 * 
 * @author cjf
 * @date 2023-10-08
 */
@DataSource(value = DataSourceType.SLAVE)
public interface BlindBoxProbabilityMapper 
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
     * 删除盲盒概率
     * 
     * @param id 盲盒概率主键
     * @return 结果
     */
    public int deleteBlindBoxProbabilityById(Long id);

    /**
     * 批量删除盲盒概率
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlindBoxProbabilityByIds(Long[] ids);
}
