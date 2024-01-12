package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.ProbabilityConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 每种物品的中奖概率配置Mapper接口
 * 
 * @author cjf
 * @date 2023-10-23
 */
@DataSource(value = DataSourceType.SLAVE)
public interface ProbabilityConfigMapper 
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
     * 删除每种物品的中奖概率配置
     * 
     * @param id 每种物品的中奖概率配置主键
     * @return 结果
     */
    public int deleteProbabilityConfigById(Long id);

    /**
     * 批量删除每种物品的中奖概率配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProbabilityConfigByIds(Long[] ids);

    /**
     * 根据场次,盲盒查询概率
     * */
    public ProbabilityConfig selectByFieldId(@Param("fMId")Long fMId,@Param("fieldId") Integer fieldId);

}
