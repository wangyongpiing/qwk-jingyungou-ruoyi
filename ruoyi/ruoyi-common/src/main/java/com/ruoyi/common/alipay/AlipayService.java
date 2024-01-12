package com.ruoyi.common.alipay;
/**
 * @Description:
 * @author cjf
 * @date 2023-09-08
 */

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.ijpay.alipay.AliPayApi;
import com.ijpay.alipay.AliPayApiConfig;
import com.ijpay.alipay.AliPayApiConfigKit;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author cjf
 * @date 2023/9/8
 */
@Component
public class AlipayService extends AbstractAliPayApiController{
    private static final Logger log = LoggerFactory.getLogger(AlipayService.class);
    @Resource
    private AliPayBean aliPayBean;

    // 普通公钥模式
    private final static String NOTIFY_URL = "/aliPay/notify_url";

    @Override
    public AliPayApiConfig getApiConfig(){
        AliPayApiConfig aliPayApiConfig;
        try {
            aliPayApiConfig = AliPayApiConfigKit.getApiConfig(aliPayBean.getAppId());
        } catch (Exception e) {
            aliPayApiConfig = AliPayApiConfig.builder()
                    .setAppId(aliPayBean.getAppId())
                    .setAliPayPublicKey(aliPayBean.getPublicKey())
                    .setAppCertPath(aliPayBean.getAppCertPath())
                    .setAliPayCertPath(aliPayBean.getAliPayCertPath())
                    .setAliPayRootCertPath(aliPayBean.getAliPayRootCertPath())
                    .setCharset("UTF-8")
                    .setPrivateKey(aliPayBean.getPrivateKey())
                    .setServiceUrl(aliPayBean.getServerUrl())
                    .setSignType("RSA2")
                    // 普通公钥方式
                    .build();
            // 证书模式
//                    .buildByCert();

        }
        return aliPayApiConfig;
    }

    @PostConstruct
    public void init(){
        //初始化方法
        AliPayApiConfigKit.putApiConfig(this.getApiConfig());
    }

    /**
     * app支付
     */
    public String appPay(String name,String totalAmount,String storeId,String outTradeNo) {
        try {
            System.out.println("name---->"+name);
            System.out.println("totalAmount---->"+totalAmount);
            System.out.println("storeId---->"+storeId);
            System.out.println("outTradeNo---->"+outTradeNo);
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//            model.setBody(name);
            model.setSubject(name);
            model.setOutTradeNo(outTradeNo);
            model.setTimeoutExpress("30m");
            model.setTotalAmount(totalAmount);
            model.setStoreId(storeId);
//            model.setPassbackParams("callback params");
            model.setProductCode("QUICK_MSECURITY_PAY");
            String orderInfo = AliPayApi.appPayToResponse(model, aliPayBean.getDomain() + NOTIFY_URL).getBody();
            if (StringUtils.isEmpty(orderInfo)){
                log.error("获取支付orderInfo为空!");
            }
            log.info("拉取app支付返回orderInfo------------>"+orderInfo);
            return orderInfo;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error("拉取app支付异常!");
        }
        return null;
    }

    /**
     *
     * 扫码支付
     *
     * @param name 商品名称
     * @param totalAmount 金额
     * @param storeId
     * @param outTradeNo 订单编号
     */
    public JSONObject tradePreCreatePay(String name,String totalAmount,String storeId,String outTradeNo) {
        String subject = name;
//        String totalAmount = "100";
//        String storeId = "123";
        String notifyUrl = aliPayBean.getDomain() + NOTIFY_URL;
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setSubject(subject);
        model.setTotalAmount(totalAmount);
        model.setStoreId(storeId);
        model.setTimeoutExpress("5m");
        //StringUtils.getOutTradeNo()
        model.setOutTradeNo(outTradeNo);
        try {

            String resultStr = AliPayApi.tradePrecreatePayToResponse(model, notifyUrl).getBody();
            JSONObject jsonObject = JSONObject.parseObject(resultStr);
            String qrCode = jsonObject.getJSONObject("alipay_trade_precreate_response").getString("qr_code");//二维码链接
            outTradeNo = jsonObject.getJSONObject("alipay_trade_precreate_response").getString("out_trade_no");//订单号
            return jsonObject.getJSONObject("alipay_trade_precreate_response");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     * jsApi支付
     *
//     * @param name 商品名称
//     * @param totalAmount 金额
//     * @param storeId
//     * @param outTradeNo 订单编号
     */
    public JSONObject jsApi() {
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",aliPayBean.getAppId(),aliPayBean.getPrivateKey(),"json","GBK",aliPayBean.getPublicKey(),"RSA2");
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        request.setNotifyUrl("");
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "20210817010101004");
        bizContent.put("total_amount", 0.01);
        bizContent.put("subject", "测试商品");
        bizContent.put("buyer_id", "2088822148633492");
        bizContent.put("timeout_express", "10m");
        bizContent.put("product_code", "JSAPI_PAY");

        //// 商品明细信息，按需传入
        //JSONArray goodsDetail = new JSONArray();
        //JSONObject goods1 = new JSONObject();
        //goods1.put("goods_id", "goodsNo1");
        //goods1.put("goods_name", "子商品1");
        //goods1.put("quantity", 1);
        //goods1.put("price", 0.01);
        //goodsDetail.add(goods1);
        //bizContent.put("goods_detail", goodsDetail);

        //// 扩展信息，按需传入
        //JSONObject extendParams = new JSONObject();
        //extendParams.put("sys_service_provider_id", "2088511833207846");
        //bizContent.put("extend_params", extendParams);

        //// 结算信息，按需传入
        //JSONObject settleInfo = new JSONObject();
        //JSONArray settleDetailInfos = new JSONArray();
        //JSONObject settleDetail = new JSONObject();
        //settleDetail.put("trans_in_type", "defaultSettle");
        //settleDetail.put("amount", 0.01);
        //settleDetailInfos.add(settleDetail);
        //settleInfo.put("settle_detail_infos", settleDetailInfos);
        //bizContent.put("settle_info", settleInfo);

        //// 二级商户信息，按需传入
        //JSONObject subMerchant = new JSONObject();
        //subMerchant.put("merchant_id", "2088000603999128");
        //bizContent.put("sub_merchant", subMerchant);

        //// 业务参数信息，按需传入
        //JSONObject businessParams = new JSONObject();
        //businessParams.put("busi_params_key", "busiParamsValue");
        //bizContent.put("business_params", businessParams);

        //// 营销信息，按需传入
        //JSONObject promoParams = new JSONObject();
        //promoParams.put("promo_params_key", "promoParamsValue");
        //bizContent.put("promo_params", promoParams);

        request.setBizContent(bizContent.toString());
        AlipayTradeCreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            System.out.println("-------->getApiMethodName:"+request.getApiMethodName());
            System.out.println("-------->getApiVersion:"+request.getApiVersion());
            System.out.println("-------->getBizContent:"+request.getBizContent());
            System.out.println("-------->getProdCode:"+request.getProdCode());
            System.out.println("-------->getReturnUrl:"+request.getReturnUrl());
            System.out.println("-------->getNotifyUrl:"+request.getNotifyUrl());
            System.out.println("-------->getTerminalInfo:"+request.getTerminalInfo());
            System.out.println("-------->getTerminalType:"+request.getTerminalType());
            System.out.println("-------->getBizModel:"+request.getBizModel());
            System.out.println("-------->getResponseClass:"+request.getResponseClass());
            System.out.println("-------->getTextParams:"+request.getTextParams());
            return JSONObject.parseObject("");
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);
            return null;
        }

    }

    /**
     * 交易查询
     */
    public String tradeQuery(String outTradeNo,String tradeNo) {
        try {
            AlipayTradeQueryModel model = new AlipayTradeQueryModel();
            if (StringUtils.isNotEmpty(outTradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            if (StringUtils.isNotEmpty(tradeNo)) {
                model.setTradeNo(tradeNo);
            }
            return AliPayApi.tradeQueryToResponse(model).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }






}
