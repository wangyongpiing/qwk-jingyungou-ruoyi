package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.PropProbability;

/**
 * 道具爆率管理Mapper接口
 * 
 * @author cjf
 * @date 2023-07-31
 */
@DataSource(value = DataSourceType.SLAVE)
public interface PropProbabilityMapper 
{
    /**
     * 查询道具爆率管理
     * 
     * @param id 道具爆率管理主键
     * @return 道具爆率管理
     */
    public PropProbability selectPropProbabilityById(Long id);

    /**
     * 查询道具爆率管理列表
     * 
     * @param propProbability 道具爆率管理
     * @return 道具爆率管理集合
     */
    public List<PropProbability> selectPropProbabilityList(PropProbability propProbability);

    /**
     * 新增道具爆率管理
     * 
     * @param propProbability 道具爆率管理
     * @return 结果
     */
    public int insertPropProbability(PropProbability propProbability);

    /**
     * 修改道具爆率管理
     * 
     * @param propProbability 道具爆率管理
     * @return 结果
     */
    public int updatePropProbability(PropProbability propProbability);

    /**
     * 删除道具爆率管理
     * 
     * @param id 道具爆率管理主键
     * @return 结果
     */
    public int deletePropProbabilityById(Long id);

    /**
     * 批量删除道具爆率管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePropProbabilityByIds(Long[] ids);
}
