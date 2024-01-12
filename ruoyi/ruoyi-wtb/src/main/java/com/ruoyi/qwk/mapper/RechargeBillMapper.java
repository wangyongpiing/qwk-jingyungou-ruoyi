package com.ruoyi.qwk.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.RechargeBill;

/**
 * 充值流水 Mapper接口
 * 
 * @author cjf
 * @date 2023-06-30
 */
@DataSource(value = DataSourceType.SLAVE)
public interface RechargeBillMapper 
{
    /**
     * 查询充值流水 
     * 
     * @param id 充值流水 主键
     * @return 充值流水 
     */
    public RechargeBill selectRechargeBillById(Long id);

    /**
     * 查询充值流水 列表
     * 
     * @param rechargeBill 充值流水 
     * @return 充值流水 集合
     */
    public List<RechargeBill> selectRechargeBillList(RechargeBill rechargeBill);

    /**
     * 新增充值流水 
     * 
     * @param rechargeBill 充值流水 
     * @return 结果
     */
    public int insertRechargeBill(RechargeBill rechargeBill);

    /**
     * 修改充值流水 
     * 
     * @param rechargeBill 充值流水 
     * @return 结果
     */
    public int updateRechargeBill(RechargeBill rechargeBill);

    /**
     * 删除充值流水 
     * 
     * @param id 充值流水 主键
     * @return 结果
     */
    public int deleteRechargeBillById(Long id);

    /**
     * 批量删除充值流水 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRechargeBillByIds(Long[] ids);

    /**
     * 查询统计数据
     *
     * @return 结果
     */
    public Map<String,String> selectByStatistics();

    /**
     * 根据订单查询流水数据
     *
     * @return 结果
     */
    public RechargeBill selectByOrderNumber(String orderNumber);

    /**
     * 根据支付订单号查询流水数据
     *
     * @return 结果
     */
    public RechargeBill selectByPaymentNumber(String paymentNumber);

}
