package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.Goods;
import com.ruoyi.qwk.domain.Orders;
import com.ruoyi.qwk.domain.TakeDeliveryAddress;
import org.apache.ibatis.annotations.Param;

/**
 * 商品订单 Service接口
 *
 * @author cjf
 * @date 2023-07-01
 */
public interface IOrdersService
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
     * 查询商品订单 列表
     *
     * @param orders 商品订单
     * @return 商品订单 集合
     */
    public List<Orders> getOrdersList(Orders orders);


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
     * 批量删除商品订单
     *
     * @param ids 需要删除的商品订单 主键集合
     * @return 结果
     */
    public int deleteOrdersByIds(Long[] ids);

    /**
     * 删除商品订单 信息
     *
     * @param id 商品订单 主键
     * @return 结果
     */
    public int deleteOrdersById(Long id);

    /**
     * 根据订单编号查询订单
     *
     * @param number 商品订单编号
     * @return 商品订单
     */
    public Orders selectOrdersByNumber(String number);

    /**
     * 下单
     *
     * @param orders 商品订单
     * @return 结果
     */
    public AjaxResult placeOrder(Orders orders);

    /**
     * 确认收货
     *
     * @param id 订单id
     * @return 结果
     */
    public int confirmReceipt(Long id);

    /**
     * 仓库一键发货
     *
     * @param wdId 仓库详情id
     * @param goodsId 商品id
     * @param tDAId 收货地址id
     * @return 结果
     */
    public int sendGoods(Long wdId,Long goodsId,Long tDAId);

    /**
     * 根据用户id,状态查询用户订单条数
     *
     * @param userId 用户id
     * @param status 状态
     * @return 结果
     */
    public int selectByStatusCount( Long userId, Long status);

}
