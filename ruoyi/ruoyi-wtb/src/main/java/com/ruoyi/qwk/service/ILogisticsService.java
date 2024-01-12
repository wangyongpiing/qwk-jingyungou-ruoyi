package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.Logistics;

/**
 * 物流 Service接口
 * 
 * @author cjf
 * @date 2023-06-30
 */
public interface ILogisticsService 
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
     * 批量删除物流 
     * 
     * @param ids 需要删除的物流 主键集合
     * @return 结果
     */
    public int deleteLogisticsByIds(Long[] ids);

    /**
     * 删除物流 信息
     * 
     * @param id 物流 主键
     * @return 结果
     */
    public int deleteLogisticsById(Long id);

    /**
     * 查询物流 信息
     *
     * @param id 订单id
     * @return 结果
     */
    public Logistics selectLogisticsByOrderId(String id);


    public int addLogistics(Logistics logistics);

}
