package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.Orders;
import org.apache.ibatis.annotations.Param;

/**
 * 商品订单 Mapper接口
 * 
 * @author cjf
 * @date 2023-07-01
 */
@DataSource(value = DataSourceType.SLAVE)
public interface OrdersMapper 
{
    /**
     * 查询商品订单 
     * 
     * @param id 商品订单 主键
     * @return 商品订单 
     */
    public Orders selectOrdersById(Long id);

    /**
     * 查询商品订单 列表
     * 
     * @param orders 商品订单 
     * @return 商品订单 集合
     */
    public List<Orders> selectOrdersList(Orders orders);

    /**
     * 新增商品订单 
     * 
     * @param orders 商品订单 
     * @return 结果
     */
    public int insertOrders(Orders orders);

    /**
     * 修改商品订单 
     * 
     * @param orders 商品订单 
     * @return 结果
     */
    public int updateOrders(Orders orders);

    /**
     * 删除商品订单 
     * 
     * @param id 商品订单 主键
     * @return 结果
     */
    public int deleteOrdersById(Long id);

    /**
     * 批量删除商品订单 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrdersByIds(Long[] ids);

    /**
     * 根据订单编号查询订单
     *
     * @param number 商品订单编号
     * @return 商品订单
     */
    public Orders selectOrdersByNumber(String number);

    /**
     * 确认收货,修改订单状态为已完成(3)
     *
     * @param id 商品订单id
     * @return 结果
     */
    public int updateOrdersStatus(Long id);

    /**
     * 根据用户id,状态查询用户订单条数
     *
     * @param userId 用户id
     * @param status 状态
     * @return 结果
     */
    public int selectByStatusCount(@Param("userId") Long userId,@Param("status") Long status);

    /**
     * 根据订单编号修改状态
     *
     * @param orderNumber 订单编号
     * @return 结果
     */
    public int updateStatusByNumber(Long orderNumber);

}
