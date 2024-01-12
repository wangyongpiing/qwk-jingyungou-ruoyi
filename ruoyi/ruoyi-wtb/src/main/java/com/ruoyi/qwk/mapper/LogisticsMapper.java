package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.Logistics;

/**
 * 物流 Mapper接口
 * 
 * @author cjf
 * @date 2023-06-30
 */
@DataSource(value = DataSourceType.SLAVE)
public interface LogisticsMapper 
{
    /**
     * 查询物流 
     * 
     * @param id 物流 主键
     * @return 物流 
     */
    public Logistics selectLogisticsById(Long id);

    /**
     * 查询物流 列表
     * 
     * @param logistics 物流 
     * @return 物流 集合
     */
    public List<Logistics> selectLogisticsList(Logistics logistics);

    /**
     * 新增物流 
     * 
     * @param logistics 物流 
     * @return 结果
     */
    public int insertLogistics(Logistics logistics);

    /**
     * 修改物流 
     * 
     * @param logistics 物流 
     * @return 结果
     */
    public int updateLogistics(Logistics logistics);

    /**
     * 删除物流 
     * 
     * @param id 物流 主键
     * @return 结果
     */
    public int deleteLogisticsById(Long id);

    /**
     * 批量删除物流 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLogisticsByIds(Long[] ids);

    /**
     * 根据订单id查询物流
     *
     * @param id 订单id
     * @return 物流
     */
    public Logistics selectLogisticsByOrderId(String id);
}
