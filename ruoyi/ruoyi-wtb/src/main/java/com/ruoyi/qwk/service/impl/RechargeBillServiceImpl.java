package com.ruoyi.qwk.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.alipay.AlipayService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.xorpay.Parameter;
import com.ruoyi.common.xorpay.XorpayUtils;
import com.ruoyi.qwk.domain.MoneyRatio;
import com.ruoyi.qwk.mapper.MoneyRatioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.RechargeBillMapper;
import com.ruoyi.qwk.domain.RechargeBill;
import com.ruoyi.qwk.service.IRechargeBillService;
import org.springframework.util.ObjectUtils;

/**
 * 充值流水 Service业务层处理
 *
 * @author cjf
 * @date 2023-06-30
 */
@Service
public class RechargeBillServiceImpl implements IRechargeBillService
{
    @Autowired
    private RechargeBillMapper rechargeBillMapper;

    @Autowired
    private MoneyRatioMapper moneyRatioMapper;

    @Autowired
    private AlipayService alipayService;

    /**
     * 查询充值流水
     *
     * @param id 充值流水 主键
     * @return 充值流水
     */
    @Override
    public RechargeBill selectRechargeBillById(Long id)
    {
        return rechargeBillMapper.selectRechargeBillById(id);
    }

    /**
     * 查询充值流水 列表
     *
     * @param rechargeBill 充值流水
     * @return 充值流水
     */
    @Override
    public List<RechargeBill> selectRechargeBillList(RechargeBill rechargeBill)
    {
        return rechargeBillMapper.selectRechargeBillList(rechargeBill);
    }

    /**
     * 新增充值流水
     *
     * @param rechargeBill 充值流水
     * @return 结果
     */
    @Override
    public int insertRechargeBill(RechargeBill rechargeBill)
    {
        return rechargeBillMapper.insertRechargeBill(rechargeBill);
    }

    /**
     * 修改充值流水
     *
     * @param rechargeBill 充值流水
     * @return 结果
     */
    @Override
    public int updateRechargeBill(RechargeBill rechargeBill)
    {
        return rechargeBillMapper.updateRechargeBill(rechargeBill);
    }

    /**
     * 批量删除充值流水
     *
     * @param ids 需要删除的充值流水 主键
     * @return 结果
     */
    @Override
    public int deleteRechargeBillByIds(Long[] ids)
    {
        return rechargeBillMapper.deleteRechargeBillByIds(ids);
    }

    /**
     * 删除充值流水 信息
     *
     * @param id 充值流水 主键
     * @return 结果
     */
    @Override
    public int deleteRechargeBillById(Long id)
    {
        return rechargeBillMapper.deleteRechargeBillById(id);
    }

    /**
     * 查询统计数据
     *
     * @return 结果
     */
    @Override
    public Map<String,String> selectByStatistics(){
        return rechargeBillMapper.selectByStatistics();
    }

//    ---------------------------------app--------------------------------------------

    /**
     * 积分充值
     * @param rechargeBill 充值流水对象
     * @return 结果
     * */
    @Override
    public AjaxResult pointRecharge(RechargeBill rechargeBill){
        MoneyRatio moneyRatio = moneyRatioMapper.selectMoneyRatioByType(1L);
        Integer randomInt = RandomUtil.randomInt(100000000, 999999999);
        String orderNumber = getOrderNumber();
        rechargeBill.setOrderNumber(orderNumber);
        SysUser user = SecurityUtils.getLoginUser().getUser();
        rechargeBill.setUserId(user.getUserId());
        rechargeBill.setUserPhone(user.getPhonenumber());
        rechargeBill.setAccount(user.getUserName());
        rechargeBill.setState("0");
        rechargeBill.setCreateBy(user.getUserName());
        rechargeBill.setCreateTime(new Date());
        rechargeBill.setType(1L);//设置充值类型为积分
        rechargeBill.setPaymentMethod("支付宝");
        Double rechargeGrade = Double.parseDouble(rechargeBill.getRechargeGrade());//应到账积分
        Double unrealProportion = Double.parseDouble(moneyRatio.getUnrealProportion());//虚拟占比
        Double payAmount = rechargeGrade/unrealProportion;
        rechargeBill.setPaymentAmount(payAmount.toString());//应支付金额
        String outTradeNo = StringUtils.getOutTradeNo();//支付订单号

        //app支付
//        String appPay = alipayService.appPay("积分充值", payAmount.toString(), randomInt.toString(), outTradeNo);

        //扫码支付
//        JSONObject appPay = alipayService.tradePreCreatePay("积分充值", payAmount.toString(), randomInt.toString(),outTradeNo);

//        if (ObjectUtils.isEmpty(appPay)){
//            return AjaxResult.error("拉取支付异常");
//        }

        JSONObject appPay = null;
        Parameter parameter = new Parameter();
        parameter.setName("积分充值");//商品名称
        parameter.setPayType("native");//支付方式 收银台 jsapi
        parameter.setPrice(payAmount.toString());//价格
        parameter.setOrderId(outTradeNo);//平台订单号，需要唯一
        parameter.setNotifyUrl("https://www.hbqwk.com:8080/aliPay/callback");//回调地址
        appPay = XorpayUtils.getCashier(parameter);
        System.out.println("调用第三方支付----------->"+appPay);

        HashMap<String, Object> hashMap = new HashMap<>();
//        扫码支付
//        String qrCode = appPay.getString("qr_code");//二维码链接
//        outTradeNo = appPay.getString("out_trade_no");//订单号
//        String code = appPay.getString("code");//返回状态码
//        String msg = appPay.getString("msg");//消息体
//        if (!"10000".equals(code) && !"Success".equals(msg)){
//            return AjaxResult.error("拉取支付异常");
//        }
//        ------------

        rechargeBill.setPaymentNumber(outTradeNo);
        if (rechargeBillMapper.insertRechargeBill(rechargeBill)<=0){
            return AjaxResult.error();
        }
        hashMap.put("outTradeNo",outTradeNo);
        hashMap.put("orderInfo",appPay.getJSONObject("info").getString("qr"));
        return AjaxResult.success(hashMap);
    }


    private String getOrderNumber(){
        Integer randomInt = RandomUtil.randomInt(100000000, 999999999);
        RechargeBill rechargeBill = rechargeBillMapper.selectByOrderNumber(randomInt.toString());
        if (ObjectUtils.isEmpty(rechargeBill)){
            return randomInt.toString();
        }else {
            return getOrderNumber();
        }
    }
}
