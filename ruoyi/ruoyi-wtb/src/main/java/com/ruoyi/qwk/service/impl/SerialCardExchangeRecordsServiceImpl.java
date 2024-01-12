package com.ruoyi.qwk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.alipay.AlipayService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.mapper.*;
import com.ruoyi.system.mapper.SysUserMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.service.ISerialCardExchangeRecordsService;

/**
 * 序列卡兑换记录 Service业务层处理
 *
 * @author cjf
 * @date 2023-12-01
 */
@Service
public class SerialCardExchangeRecordsServiceImpl implements ISerialCardExchangeRecordsService
{
    @Autowired
    private SerialCardExchangeRecordsMapper serialCardExchangeRecordsMapper;

    @Autowired
    private SerialCardMapper serialCardMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private NewEnergyUserZodiacSignMapper newEnergyUserZodiacSignMapper;

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private SerialCardOrderRecordsMapper serialCardOrderRecordsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 查询序列卡兑换记录
     *
     * @param id 序列卡兑换记录 主键
     * @return 序列卡兑换记录
     */
    @Override
    public SerialCardExchangeRecords selectSerialCardExchangeRecordsById(Long id)
    {
        return serialCardExchangeRecordsMapper.selectSerialCardExchangeRecordsById(id);
    }

    /**
     * 查询序列卡兑换记录 列表
     *
     * @param serialCardExchangeRecords 序列卡兑换记录
     * @return 序列卡兑换记录
     */
    @Override
    public List<SerialCardExchangeRecords> selectSerialCardExchangeRecordsList(SerialCardExchangeRecords serialCardExchangeRecords)
    {
        return serialCardExchangeRecordsMapper.selectSerialCardExchangeRecordsList(serialCardExchangeRecords);
    }

    /**
     * 新增序列卡兑换记录
     *
     * @param serialCardExchangeRecords 序列卡兑换记录
     * @return 结果
     */
    @Override
    public int insertSerialCardExchangeRecords(SerialCardExchangeRecords serialCardExchangeRecords)
    {
        serialCardExchangeRecords.setCreateTime(DateUtils.getNowDate());
        return serialCardExchangeRecordsMapper.insertSerialCardExchangeRecords(serialCardExchangeRecords);
    }

    /**
     * 修改序列卡兑换记录
     *
     * @param serialCardExchangeRecords 序列卡兑换记录
     * @return 结果
     */
    @Override
    public int updateSerialCardExchangeRecords(SerialCardExchangeRecords serialCardExchangeRecords)
    {
        serialCardExchangeRecords.setUpdateTime(DateUtils.getNowDate());
        return serialCardExchangeRecordsMapper.updateSerialCardExchangeRecords(serialCardExchangeRecords);
    }

    /**
     * 批量删除序列卡兑换记录
     *
     * @param ids 需要删除的序列卡兑换记录 主键
     * @return 结果
     */
    @Override
    public int deleteSerialCardExchangeRecordsByIds(Long[] ids)
    {
        return serialCardExchangeRecordsMapper.deleteSerialCardExchangeRecordsByIds(ids);
    }

    /**
     * 删除序列卡兑换记录 信息
     *
     * @param id 序列卡兑换记录 主键
     * @return 结果
     */
    @Override
    public int deleteSerialCardExchangeRecordsById(Long id)
    {
        return serialCardExchangeRecordsMapper.deleteSerialCardExchangeRecordsById(id);
    }

    /**
     * App兑换序列卡
     * @param cardCode
     * @return
     */
    public AjaxResult exchangeCardApp(String cardCode,String userId){
        //获取用户信息
        SysUser user = userMapper.selectUserById(Long.parseLong(userId));
        //获取卡号是否存在
        SerialCard card = serialCardMapper.selectSerialCardByCardCode(cardCode);
        if(ObjUtil.isNull(card)){
            return AjaxResult.error("卡号不存在!");
        }
        checkSelectSerialCardList(user);
        if(!"1".equals(card.getPurchaseState())){
            return AjaxResult.error("卡号不存在!");
        }
        if("1".equals(card.getRedemptionStatus())){
            return AjaxResult.error("卡号已兑换！");
        }
        //判断用户是否超过兑换总量100
        NewEnergyUserZodiacSign nzs = new NewEnergyUserZodiacSign();
        nzs.setUserId(user.getUserId());
        List<NewEnergyUserZodiacSign> nesList=  newEnergyUserZodiacSignMapper.selectNewEnergyUserZodiacSignList(nzs);
        if(nesList.size()+Integer.parseInt(card.getCardType())>100){
            return AjaxResult.error("超出生产灵宠数量，每人最高100个！");
        }
        SerialCardExchangeRecords exchang = new SerialCardExchangeRecords();
        //卡号
        exchang.setCardCode(cardCode);
        exchang.setCardId(String.valueOf(card.getId()));
        exchang.setUserName(user.getUserName());
        exchang.setUserId(String.valueOf(user.getUserId()));
        //生成兑换记录
        serialCardExchangeRecordsMapper.insertSerialCardExchangeRecords(exchang);
        //更新序列卡状态
        card.setRedemptionStatus("1");
        serialCardMapper.updateSerialCard(card);
        Map<String,Object> map = new HashMap<>();
        map.put("type",card.getCardType());
        return AjaxResult.success(map);
    }

    /**
     * 更新订单状态
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
                JSONObject jsonObject = new JSONObject(orderInfo);
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
                    s.setPurchaseState("1");
                    //更新已支付生肖卡
                    serialCardMapper.updateSerialCard(s);
                }
            });
        }
    }
}
