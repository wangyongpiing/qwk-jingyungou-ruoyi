package com.ruoyi.qwk.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.myJPush.MyJPushClient;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.qwk.domain.Orders;
import com.ruoyi.qwk.domain.SystemNotice;
import com.ruoyi.qwk.mapper.OrdersMapper;
import com.ruoyi.qwk.mapper.SystemNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.LogisticsMapper;
import com.ruoyi.qwk.domain.Logistics;
import com.ruoyi.qwk.service.ILogisticsService;

/**
 * 物流 Service业务层处理
 * 
 * @author cjf
 * @date 2023-06-30
 */
@Service
public class LogisticsServiceImpl implements ILogisticsService 
{
    @Autowired
    private LogisticsMapper logisticsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private MyJPushClient myJPushClient;

    @Autowired
    private SystemNoticeMapper systemNoticeMapper;

    /**
     * 查询物流 
     * 
     * @param id 物流 主键
     * @return 物流 
     */
    @Override
    public Logistics selectLogisticsById(Long id)
    {
        return logisticsMapper.selectLogisticsById(id);
    }

    /**
     * 查询物流 列表
     * 
     * @param logistics 物流 
     * @return 物流 
     */
    @Override
    public List<Logistics> selectLogisticsList(Logistics logistics)
    {
        return logisticsMapper.selectLogisticsList(logistics);
    }

    /**
     * 新增物流 
     * 
     * @param logistics 物流 
     * @return 结果
     */
    @Override
    public int insertLogistics(Logistics logistics)
    {
        return logisticsMapper.insertLogistics(logistics);
    }

    /**
     * 修改物流 
     * 
     * @param logistics 物流 
     * @return 结果
     */
    @Override
    public int updateLogistics(Logistics logistics)
    {
        return logisticsMapper.updateLogistics(logistics);
    }

    /**
     * 批量删除物流 
     * 
     * @param ids 需要删除的物流 主键
     * @return 结果
     */
    @Override
    public int deleteLogisticsByIds(Long[] ids)
    {
        return logisticsMapper.deleteLogisticsByIds(ids);
    }

    /**
     * 删除物流 信息
     * 
     * @param id 物流 主键
     * @return 结果
     */
    @Override
    public int deleteLogisticsById(Long id)
    {
        return logisticsMapper.deleteLogisticsById(id);
    }

    /**
     * 查询物流 信息
     *
     * @param id 订单id
     * @return 结果
     */
    @Override
    public Logistics selectLogisticsByOrderId(String id){
        return logisticsMapper.selectLogisticsByOrderId(id);
    }


    /**
     * 发货
     * */
    @Override
    public int addLogistics(Logistics logistics) {
        int i = 0;
        i+=logisticsMapper.insertLogistics(logistics);
        if (i<=0)return i;
        Orders orders = ordersMapper.selectOrdersByNumber(logistics.getOrderNumber());
        Date date = new Date();
        orders.setStatus(2L);
        orders.setUpdatedBy(SecurityUtils.getLoginUser().getUser().getUserName());
        orders.setUpdatedTime(date);
        orders.setSendTime(date);
        orders.setLogisticsId(logistics.getId());
        i+=ordersMapper.updateOrders(orders);

        SystemNotice systemNotice = new SystemNotice();

//        myJPushClient

//        i+=ordersMapper.updateStatusByNumber(Long.parseLong(logistics.getOrderNumber()));
        return i;
    }


}
