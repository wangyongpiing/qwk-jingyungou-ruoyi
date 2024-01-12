package com.ruoyi.qwk.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.alipay.AlipayService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.RandomUtil;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.mapper.*;
import com.ruoyi.qwk.service.IInviteSubordinateService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.aspectj.weaver.ast.Or;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.service.IOrdersService;
import org.springframework.util.ObjectUtils;

/**
 * 商品订单 Service业务层处理
 *
 * @author cjf
 * @date 2023-07-01
 */
@Service
public class OrdersServiceImpl implements IOrdersService
{
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    @Autowired
    private WarehouseDetailsMapper warehouseDetailsMapper;

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private IInviteSubordinateService inviteSubordinateService;


    /**
     * 查询商品订单
     *
     * @param id 商品订单 主键
     * @return 商品订单
     */
    @Override
    public Orders selectOrdersById(Long id)
    {
        return ordersMapper.selectOrdersById(id);
    }

    /**
     * 查询商品订单 列表
     *
     * @param orders 商品订单
     * @return 商品订单
     */
    @Override
    public List<Orders> selectOrdersList(Orders orders)
    {
        return ordersMapper.selectOrdersList(orders);
    }

    /**
     * 查询商品订单 列表
     *
     * @param orders 商品订单
     * @return 商品订单 集合
     */
    public List<Orders> getOrdersList(Orders orders){
        //获取订单信息
        List<Orders> list = ordersMapper.selectOrdersList(orders);
        if(CollUtil.isNotEmpty(list)){
            //获取待支付，app支付订单
            List<Orders> orderList = list.stream().filter(s-> 2L==s.getPaymentMethod() && 0L==s.getStatus()).collect(Collectors.toList());
            if(CollUtil.isNotEmpty(orderList)){
                 orderList.forEach(s->{
                     checkOrder(s.getOrderNumber());
                 });
            }
            list = list.stream().filter(s->0L!=s.getStatus()).collect(Collectors.toList());
        }
        return list;
    }

    public void checkOrder(String outTradeNo){
        String orderInfo = alipayService.tradeQuery(outTradeNo,null);
        log.info("交易信息："+orderInfo);
        org.json.JSONObject jsonObject = new org.json.JSONObject(orderInfo);
        JSONObject transferResponse = jsonObject.getJSONObject("alipay_trade_query_response");
        String code = transferResponse.getString("code");
        if("10000".equals(code)){
            //获取待支付订单
            Orders orders = ordersMapper.selectOrdersByNumber(outTradeNo);
            if(ObjUtil.isNotNull(orders)){
                //更新支付订单
                if(0L==orders.getStatus()){
                    orders.setStatus(1L);
                    orders.setSendTime(DateUtil.date());
                    ordersMapper.updateOrders(orders);
                }
            }
        }
    }

    /**
     * 新增商品订单
     *
     * @param orders 商品订单
     * @return 结果
     */
    @Override
    public int insertOrders(Orders orders)
    {
//        orders.setCreatedBy(SecurityUtils.getUsername());
        return ordersMapper.insertOrders(orders);
    }

    /**
     * 修改商品订单
     *
     * @param orders 商品订单
     * @return 结果
     */
    @Override
    public int updateOrders(Orders orders)
    {
        return ordersMapper.updateOrders(orders);
    }

    /**
     * 批量删除商品订单
     *
     * @param ids 需要删除的商品订单 主键
     * @return 结果
     */
    @Override
    public int deleteOrdersByIds(Long[] ids)
    {
        return ordersMapper.deleteOrdersByIds(ids);
    }

    /**
     * 删除商品订单 信息
     *
     * @param id 商品订单 主键
     * @return 结果
     */
    @Override
    public int deleteOrdersById(Long id)
    {
        return ordersMapper.deleteOrdersById(id);
    }

    /**
     * 根据订单编号查询订单
     *
     * @param number 商品订单编号
     * @return 商品订单
     */
    @Override
    public Orders selectOrdersByNumber(String number){
        return ordersMapper.selectOrdersByNumber(number);
    }

    /**
     * 下单
     *
     * @param orders 商品订单
     * @return 结果
     */
    @Override
    public AjaxResult placeOrder(Orders orders) {
        int value = 0;
        Goods goods = goodsMapper.selectGoodsById(Long.parseLong(orders.getGoodsName()));
        if (ObjectUtils.isEmpty(goods))return AjaxResult.error();
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        orders.setUserId(sysUser.getUserId());
        orders.setGoodsType(goods.getType());
        orders.setGoodsId(goods.getId());
        orders.setGoodsName(goods.getName());
        orders.setGoodsImage(goods.getImage());
        orders.setUserPhone(sysUser.getPhonenumber());
        orders.setNickName(sysUser.getNickName());
        orders.setPrice(goods.getPlatformPrice());
        Long number = RandomUtil.getRandomForLongBounded2(1000000000L, 100000000000L);
        orders.setOrderNumber(number.toString());
        orders.setStatus(1L);
        orders.setCreatedBy(SecurityUtils.getUsername());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("orderNumber",number.toString());
        switch (orders.getPaymentMethod().intValue()){
            case 1:
                long parseLong = Long.parseLong(orders.getPrice());
                Long quantity = orders.getQuantity()*parseLong;
                orders.setIntegral(quantity.toString());
                value+=exchange(orders);
                break;
            case 2:
                int num = orders.getQuantity().intValue();
                String goodsPrice = StringUtils.doubleTrans(Double.parseDouble(goods.getCashPrice())*num);//商品价格
                orders.setStatus(0L);//状态为待支付
//                orders.setPaymentAmount(StringUtils.doubleTrans(goodsPrice));//应支付金额
//                cashPurchase(orders)//
//                JSONObject orderInfo = alipayService.tradePreCreatePay(goods.getName(), goodsPrice, number.toString(), number.toString());
                String orderInfo = alipayService.appPay(goods.getName(), goodsPrice, number.toString(), number.toString());
                hashMap.put("orderInfo",orderInfo);
                if (ObjectUtils.isEmpty(orderInfo)){
                    log.error("拉取支付宝支付异常: {}",orderInfo);
                    value = 0;
                }else {
                    log.info("拉取支付宝支付,返回参数 {}",orderInfo);
                    value += 1;
                }
                break;
            default:
                return AjaxResult.error();
        }
        if (value<=0)return AjaxResult.error("积分不足");
        value+=ordersMapper.insertOrders(orders);
        if (value<=0){
            return AjaxResult.error();
        }else {
            int i = Integer.parseInt(goods.getSoldQuantity());
            Long quantity = orders.getQuantity()+i;
            goods.setSoldQuantity(quantity.toString());
            goods.setUpdatedBy("系统");
            goods.setUpdatedTime(new Date());
            goodsMapper.updateGoods(goods);
            return AjaxResult.success("兑换成功",hashMap);
        }

    }

    /**
     * 确认收货
     *
     * @param id 订单id
     * @return 结果
     */
    @Override
    public int confirmReceipt(Long id) {
        Date date = new Date();
        Orders orders = new Orders();
        orders.setId(id);
        orders.setStatus(3L);
        orders.setReceivingTime(date);
        orders.setUpdatedBy(SecurityUtils.getLoginUser().getUser().getUserName());
        orders.setUpdatedTime(date);
        return ordersMapper.updateOrders(orders);
    }

    /**
     * 仓库一键发货
     * @param wdId 仓库详情id
     * @param goodsId 商品id
     * @param tDAId 收货地址id
     * @return 结果
     */
    @Override
    public int sendGoods(Long wdId,Long goodsId,Long tDAId){
        int value = 0;
        Integer integer = inviteSubordinateService.totalPeople(SecurityUtils.getLoginUser().getUser().getUserId());
        WarehouseDetails warehouseDetails = warehouseDetailsMapper.selectWarehouseDetailsById(wdId);
        if ("0".equals(warehouseDetails.getIsNovice())){
            if (integer < 2)return -1;
        }
        Goods goods = goodsMapper.selectGoodsById(goodsId);
//        Date date = new Date();
        Orders orders = new Orders();
        orders.setStatus(1L);
//        orders.setCosts();
        orders.setGoodsId(goodsId);
        orders.setGoodsImage(goods.getImage());
        orders.setGoodsName(goods.getName());
        orders.setGoodsType(goods.getType());
        orders.setQuantity(warehouseDetails.getQuantity());
        long random = RandomUtil.getRandomForLongBounded2(1000000000L, 100000000000L);
        orders.setOrderNumber(random+"");
        orders.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        orders.setUserPhone(SecurityUtils.getLoginUser().getUser().getPhonenumber());
        orders.setNickName(SecurityUtils.getLoginUser().getUser().getNickName());
        orders.setPrice(goods.getPlatformPrice());
//        orders.setPaymentTime(date);
//        orders.setCreatedTime(date);
        orders.setCreatedBy(SecurityUtils.getUsername());
        orders.settDAId(tDAId);
        value+=ordersMapper.insertOrders(orders);
        WarehouseDetails wd = new WarehouseDetails();
        wd.setId(wdId);
        wd.setState(3L);
//        wd.setUpdateTime(date);
        wd.setUpdateBy(SecurityUtils.getUsername());
        value+=warehouseDetailsMapper.updateWarehouseDetails(wd);
        return value;
    }

    /**
     * 根据用户id,状态查询用户订单条数
     *
     * @param userId 用户id
     * @param status 状态
     * @return 结果
     */
    @Override
    public int selectByStatusCount( Long userId, Long status){
        return ordersMapper.selectByStatusCount(userId,status);
    }

    private int purchase(){
        return 0;
    }

    //积分兑换下单
    private int exchange(Orders orders){
        int value = 0;
        UserAssets userAssets = userAssetsMapper.selectByUserId(orders.getUserId(), 1L);
//        double total = Double.parseDouble(userAssets.getTotal());
        Double total = (Double.parseDouble(userAssets.getTotal())) - ((Double.parseDouble(orders.getIntegral())) * orders.getQuantity().intValue());
        if (total<0){
            return 0;
        }
        userAssets.setTotal(StringUtils.doubleTrans(total));
        value+=userAssetsMapper.updateUserAssets(userAssets);
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setUserId(orders.getUserId());
        assetDetails.setUserPhone(orders.getUserPhone());
        assetDetails.setType(1L);
        assetDetails.setAddOrSubtract("0");
        assetDetails.setRecords(orders.getIntegral());
        assetDetails.setBalance(StringUtils.doubleTrans(total));
        assetDetails.setNotes("积分兑换商品");
        assetDetails.setCreatedBy(SecurityUtils.getUsername());
        assetDetails.setCreatedTime(new Date());
        assetDetails.setImage(orders.getGoodsImage());
        value+=assetDetailsMapper.insertAssetDetails(assetDetails);
        return value;
    }

    //现金购买
    public AjaxResult cashPurchase(Orders orders){


            return null;
    }

}
