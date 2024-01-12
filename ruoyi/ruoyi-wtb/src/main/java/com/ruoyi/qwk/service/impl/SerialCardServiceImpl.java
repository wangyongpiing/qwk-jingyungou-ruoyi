package com.ruoyi.qwk.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.domain.Person;
import com.ruoyi.common.alipay.AlipayService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RedeemCodeGeneratorUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.RandomUtil;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.enumeration.PersonalTasksGameType;
import com.ruoyi.qwk.enumeration.SerialCardExchangeType;
import com.ruoyi.qwk.mapper.*;
import org.aspectj.weaver.loadtime.Aj;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.service.ISerialCardService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 生肖序列卡 Service业务层处理
 *
 * @author cjf
 * @date 2023-12-01
 */
@Service
public class SerialCardServiceImpl implements ISerialCardService
{
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SerialCardMapper serialCardMapper;

    @Autowired
    private OperatorInformationMapper operatorInformationMapper;

    @Autowired
    private SerialCardOrderRecordsMapper serialCardOrderRecordsMapper;

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private TakeDeliveryAddressMapper takeDeliveryAddressMapper;

    /**
     * 查询生肖序列卡
     *
     * @param id 生肖序列卡 主键
     * @return 生肖序列卡
     */
    @Override
    public SerialCard selectSerialCardById(Long id)
    {
        return serialCardMapper.selectSerialCardById(id);
    }

    /**
     * 查询生肖序列卡 列表
     *
     * @param serialCard 生肖序列卡
     * @return 生肖序列卡
     */
    @Override
    public List<SerialCard> selectSerialCardList(SerialCard serialCard)
    {
        return serialCardMapper.selectSerialCardList(serialCard);
    }

    /**
     * 新增生肖序列卡
     *
     * @param serialCard 生肖序列卡
     * @return 结果
     */
    @Override
    public int insertSerialCard(SerialCard serialCard)
    {
        serialCard.setCreateTime(DateUtils.getNowDate());
        return serialCardMapper.insertSerialCard(serialCard);
    }

    /**
     * 修改生肖序列卡
     *
     * @param serialCard 生肖序列卡
     * @return 结果
     */
    @Override
    public int updateSerialCard(SerialCard serialCard)
    {
        serialCard.setUpdateTime(DateUtils.getNowDate());
        return serialCardMapper.updateSerialCard(serialCard);
    }

    /**
     * 批量删除生肖序列卡
     *
     * @param ids 需要删除的生肖序列卡 主键
     * @return 结果
     */
    @Override
    public int deleteSerialCardByIds(Long[] ids)
    {
        return serialCardMapper.deleteSerialCardByIds(ids);
    }

    /**
     * 删除生肖序列卡 信息
     *
     * @param id 生肖序列卡 主键
     * @return 结果
     */
    @Override
    public int deleteSerialCardById(Long id)
    {
        return serialCardMapper.deleteSerialCardById(id);
    }

//    /**
//     * 批量生成序列卡
//     * @param card
//     * @return
//     */
//    public AjaxResult batchInsertSerialCard(BatchInsertSerialCard card){
//        //获取运营商信息
//        OperatorInformation operatorInformation = new OperatorInformation();
//        operatorInformation.setWx(card.getWx());
//        List<OperatorInformation> operList = operatorInformationMapper.selectOperatorInformationList(operatorInformation);
//        if(CollUtil.isEmpty(operList)){
//            return AjaxResult.error("运营商微信号："+card.getWx()+"，输入错误或未录入运营商信息");
//        }
//        OperatorInformation info = operList.get(0);
//        //获取序列卡类型数量
//        List<BatchInsertSerialCardType> cardList = card.getCardList();
//        if(CollUtil.isEmpty(cardList)){
//            return AjaxResult.error("序列卡类型和数量未填写");
//        }
//        //序列卡数量
//        Long cardNumber = cardList.stream().mapToLong(BatchInsertSerialCardType::getNumber).sum();
//        List<String> cardCodeList = new ArrayList();
//        for (BatchInsertSerialCardType bo : cardList) {
//            if(StrUtil.isNotBlank(bo.getType()) && null!=bo.getNumber()){
//                SerialCardOrderRecords order = new SerialCardOrderRecords();
//                //订单编号
//                order.setOrderCode(ObjectId.next());
//                //订单数量
//                order.setOrderNumber(cardNumber);
//                //订单金额
//                order.setOrderAmount(String.valueOf(cardNumber*50));
//                order.setOperatorId(String.valueOf(info.getId()));
//                order.setOperatorName(info.getOperatorName());
//                //生成订单
//                serialCardOrderRecordsMapper.insertSerialCardOrderRecords(order);
//                String type = bo.getType();
//                Long number = bo.getNumber();
//                //生成序列号
//                for(int i=0;i<number;i++){
//                    SerialCard serialCard = new SerialCard();
//                    //卡号
//                    String code = IdUtil.simpleUUID();
//                    serialCard.setCardCode(code);
//                    //类型
//                    serialCard.setCardType(type);
//                    //运营商id
//                    serialCard.setOperatorId(info.getId().toString());
//                    //运营商名称
//                    serialCard.setOperatorName(info.getOperatorName());
//                    serialCard.setOrderId(String.valueOf(order.getId()));
//                    //订单编号
//                    serialCard.setOrderCode(order.getOrderCode());
//                    //生成序列号表
//                    serialCardMapper.insertSerialCard(serialCard);
//                    cardCodeList.add(code);
//                }
//            }
//        }
//        return AjaxResult.success(cardCodeList);
//    }


    /**
     * 批量生成序列卡
     * @param card
     * @return
     */
    public AjaxResult batchInsertSerialCard(BatchInsertSerialCard card){
        //获取运营商信息
        OperatorInformation operatorInformation = new OperatorInformation();
        operatorInformation.setWx(card.getWx());
        List<OperatorInformation> operList = operatorInformationMapper.selectOperatorInformationList(operatorInformation);
        if(CollUtil.isEmpty(operList)){
            return AjaxResult.error("运营商微信号："+card+"，输入错误或未录入运营商信息");
        }
        OperatorInformation info = operList.get(0);
        //获取序列卡类型数量
        List<BatchInsertSerialCardType> cardList = card.getCardList();
        if(CollUtil.isEmpty(cardList)){
            return AjaxResult.error("序列卡类型和数量未填写");
        }
        //序列卡数量
        Long cardNumber = cardList.stream().mapToLong(BatchInsertSerialCardType::getGoodCount).sum();
        List<String> cardCodeList = new ArrayList();
        //获取数据表已存在序列号
        List<String> scList = serialCardMapper.selectCardCodeList();
        Set<String> codeSet = new HashSet<>();
        if(CollUtil.isNotEmpty(scList)){
            codeSet = scList.stream().collect(Collectors.toSet());
        }
        SerialCardOrderRecords order = new SerialCardOrderRecords();
        //订单编号
        order.setOrderCode(ObjectId.next());
        //订单数量
        order.setOrderNumber(cardNumber);
        //订单金额
        order.setOrderAmount(String.valueOf(cardNumber*50));
        order.setOperatorId(String.valueOf(info.getId()));
        order.setOperatorName(info.getOperatorName());
        //生成订单
        serialCardOrderRecordsMapper.insertSerialCardOrderRecords(order);
        for (BatchInsertSerialCardType bo : cardList) {
            if(StrUtil.isNotBlank(bo.getGoodsType()) && null!=bo.getGoodCount()){
                String type = bo.getGoodsType();
                Long number = bo.getGoodCount();
                for(int i=0;i<number;i++){
                    SerialCard serialCard = new SerialCard();
                    //生成序列号
                    String code = RedeemCodeGeneratorUtils.generateCode(15,codeSet);
                    serialCard.setCardCode(code);
                    //类型
                    serialCard.setCardType(type);
                    //运营商id
                    serialCard.setOperatorId(info.getId().toString());
                    //运营商名称
                    serialCard.setOperatorName(info.getOperatorName());
                    serialCard.setOrderId(String.valueOf(order.getId()));
                    //订单编号
                    serialCard.setOrderCode(order.getOrderCode());
                    //生成序列号表
                    serialCardMapper.insertSerialCard(serialCard);
                    cardCodeList.add(code);
                }
            }
        }
        return AjaxResult.success(cardCodeList);
    }

    /**
     * 微信公众号端-批量生成序列卡
     * @param cardList
     * @return
     */
    @Override
    public AjaxResult batchInsertSerialCardWx(List<BatchInsertSerialCardType> cardList) {
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if(ObjUtil.isNull(user)){
            return AjaxResult.error("用户信息不存在!");
        }
        //获取序列卡类型数量
        if(CollUtil.isEmpty(cardList)){
            return AjaxResult.error("序列卡类型和数量未填写");
        }
        //序列卡数量
        Long cardNumber = cardList.stream().mapToLong(BatchInsertSerialCardType::getGoodCount).sum();
        List<String> cardCodeList = new ArrayList();
        //获取数据表已存在序列号
        List<String> scList = serialCardMapper.selectCardCodeList();
        Set<String> codeSet = new HashSet<>();
        if(CollUtil.isNotEmpty(scList)){
            codeSet = scList.stream().collect(Collectors.toSet());
        }
        SerialCardOrderRecords order = new SerialCardOrderRecords();
        //订单编号
        order.setOrderCode(ObjectId.next());
        //订单数量
        order.setOrderNumber(cardNumber);
        //订单金额
        order.setOrderAmount(String.valueOf(cardNumber*50));
        order.setOperatorId(String.valueOf(user.getUserId()));
        order.setOperatorName(user.getUserName());
        //生成订单
        serialCardOrderRecordsMapper.insertSerialCardOrderRecords(order);
        for (BatchInsertSerialCardType bo : cardList) {
            if(StrUtil.isNotBlank(bo.getGoodsType()) && null!=bo.getGoodCount()){
                String type = bo.getGoodsType();
                Long number = bo.getGoodCount();
                for(int i=0;i<number;i++){
                    SerialCard serialCard = new SerialCard();
                    //生成序列号
                    String code = RedeemCodeGeneratorUtils.generateCode(15,codeSet);
                    serialCard.setCardCode(code);
                    //类型
                    serialCard.setCardType(type);
                    //运营商id
                    serialCard.setOperatorId(String.valueOf(user.getUserId()));
                    //运营商名称
                    serialCard.setOperatorName(user.getUserName());
                    serialCard.setOrderId(String.valueOf(order.getId()));
                    //订单编号
                    serialCard.setOrderCode(order.getOrderCode());
                    //生成序列号表
                    serialCardMapper.insertSerialCard(serialCard);
                    cardCodeList.add(code);
                }
            }
        }
        return AjaxResult.success(cardCodeList);
    }

    /**
     * App兑换生肖序列卡
     * @param type
     * @return
     */
    @Transactional
    public AjaxResult serialCardExchange(String type){
        HashMap<String, Object> hashMap = new HashMap<>();
        SerialCardExchangeType cardType = SerialCardExchangeType.getName(type);
        if(ObjUtil.isNull(cardType)){
            return AjaxResult.error("传参错误");
        }
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取商品信息
        Goods goods = goodsMapper.selectGoodsById(Long.parseLong(cardType.getType()));
        if(ObjUtil.isNull(goods)){
            return AjaxResult.error("商品不存在");
        }
        //获取数据表已存在序列号
        List<String> scList = serialCardMapper.selectCardCodeList();
        Set<String> codeSet = new HashSet<>();
        if(CollUtil.isNotEmpty(scList)){
            codeSet = scList.stream().collect(Collectors.toSet());
        }
//        Long number = RandomUtil.getRandomForLongBounded2(1000000000L, 100000000000L);
//        String number = IdUtil.simpleUUID();
        Long number = RandomUtil.getRandomForLongBounded2(1000000000L, 100000000000L);
        //获取用户收货地址
        TakeDeliveryAddress takeDeliveryAddress = new TakeDeliveryAddress();
        takeDeliveryAddress.setUserId(user.getUserId());
        List<TakeDeliveryAddress> addrList = takeDeliveryAddressMapper.selectTakeDeliveryAddressList(takeDeliveryAddress);
        Long address = null;
        if(CollUtil.isEmpty(addrList)){
            return AjaxResult.error("请填写收货地址！");
        }
        address = addrList.get(0).getId();
        SerialCardOrderRecords order = new SerialCardOrderRecords();
        //订单编号
        order.setOrderCode(ObjectId.next());
        //订单数量
        order.setOrderNumber(1L);
        //订单金额
        order.setOrderAmount(cardType.getPrice());
        order.setOperatorId(String.valueOf(user.getUserId()));
        order.setOperatorName(user.getUserName());

        SerialCard serialCard = new SerialCard();
        //生成序列号
        String code = RedeemCodeGeneratorUtils.generateCode(15,codeSet);
        serialCard.setCardCode(code);
        //类型
        serialCard.setCardType(cardType.getCardType());
        //运营商id
        serialCard.setOperatorId(String.valueOf(user.getUserId()));
        //运营商名称
        serialCard.setOperatorName(user.getUserName());
        //是否购买
        serialCard.setPurchaseState("0");
        String orderInfo = alipayService.appPay(cardType.getName(), cardType.getPrice(), String.valueOf(number), String.valueOf(number));
        hashMap.put("orderInfo",orderInfo);
        if (ObjectUtils.isEmpty(orderInfo)){
            log.error("拉取支付宝支付异常: {}",orderInfo);
            return AjaxResult.error("购买失败",hashMap);
        }
        //生成待支付订单
        int count = generateOrder(user,goods,number,address);
        //生成生肖卡订单
        order.setRemark(String.valueOf(number));
        serialCardOrderRecordsMapper.insertSerialCardOrderRecords(order);
        serialCard.setOrderId(String.valueOf(order.getId()));
        //订单编号
        serialCard.setOrderCode(order.getOrderCode());
        //生成序列号表
        serialCardMapper.insertSerialCard(serialCard);
        return AjaxResult.success("购买成功",hashMap);
    }

    /**
     * App查询生肖序列卡 列表
     * @return
     */
    public AjaxResult selectSerialCardList(){
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        checkSelectSerialCardList(user);
        SerialCard card = new SerialCard();
        card.setOperatorId(String.valueOf(user.getUserId()));
        card.setPurchaseState("1");
        List<SerialCard> list = serialCardMapper.selectSerialCardList(card);
        return AjaxResult.success(list);
    }

    /**
     * 更新待支付订单
     * @param user
     */
    public void checkSelectSerialCardList(SysUser user){
        SerialCard card = new SerialCard();
        card.setOperatorId(String.valueOf(user.getUserId()));
        card.setPurchaseState("0");
        List<SerialCard> list = serialCardMapper.selectSerialCardList(card);
        if(CollUtil.isNotEmpty(list)){
            List<String> orderIdList = list.stream().map(SerialCard::getOrderId).collect(Collectors.toList());
            //获取订单信息
            SerialCardOrderRecords orderRecords = new SerialCardOrderRecords();
            orderRecords.setParams(new HashMap<String, Object>(){{
                put("orderIds",orderIdList);
            }});
            List<SerialCardOrderRecords> recList = serialCardOrderRecordsMapper.selectSerialCardOrderRecordsList(orderRecords);
            //list转map
            Map<Long, SerialCardOrderRecords> map =  recList.stream().collect(Collectors.toMap(SerialCardOrderRecords::getId, each->each,(value1, value2) -> value1));
            list.forEach(s->{
                SerialCardOrderRecords order = map.get(Long.parseLong(s.getOrderId()));
                String outTradeNo = order.getRemark();
                String orderInfo = alipayService.tradeQuery(outTradeNo,null);
                log.info("交易信息："+orderInfo);
                JSONObject jsonObject = new JSONObject(orderInfo);
                JSONObject transferResponse = jsonObject.getJSONObject("alipay_trade_query_response");
                String code = transferResponse.getString("code");
                if("10000".equals(code)){
                    //获取待支付订单
                    Orders orders = ordersMapper.selectOrdersByNumber(outTradeNo);
                    //更新支付订单
                    if(ObjUtil.isNotNull(orders)){
                        if(0L==orders.getStatus()){
                            orders.setStatus(1L);
                            orders.setSendTime(DateUtil.date());
                            ordersMapper.updateOrders(orders);
                        }
                    }
                    s.setPurchaseState("1");
                    //更新已支付生肖卡
                    serialCardMapper.updateSerialCard(s);
                }
            });
        }
    }

    /**
     * 生成待支付订单
     * @param sysUser
     * @param goods
     * @param number
     * @return
     */
    public int generateOrder(SysUser sysUser,Goods goods,Long number,Long address){
        Orders orders = new Orders();
        orders.setUserId(sysUser.getUserId());
        orders.setGoodsType(goods.getType());
        orders.setGoodsId(goods.getId());
        orders.setGoodsName(goods.getName());
        orders.setGoodsImage(goods.getImage());
        orders.setUserPhone(sysUser.getPhonenumber());
        orders.setNickName(sysUser.getNickName());
        orders.setPrice(goods.getPlatformPrice());
        orders.setOrderNumber(String.valueOf(number));
        orders.setStatus(0L);
        orders.setCreatedBy(SecurityUtils.getUsername());
        orders.setQuantity(1L);
        orders.setPaymentMethod(2L);
        if(null!=address){
            orders.settDAId(address);
        }
        return ordersMapper.insertOrders(orders);
    }

}
