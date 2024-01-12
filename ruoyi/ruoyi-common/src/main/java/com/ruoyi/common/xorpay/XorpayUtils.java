package com.ruoyi.common.xorpay;
/**
 * @Description:
 * @author cjf
 * @date 2023-11-08
 */

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.sign.Base64;
import org.springframework.http.*;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cjf
 * @date 2023/11/8
 */
public class XorpayUtils {

    public static JSONObject getOpenId(String aid){
        String callback = "https://www.hbqwk.com:8080/aliPay/callback";
        String openId="";
        try {
            openId = XorpayHttpUtils.get("https://xorpay.com/api/openid/"+aid+"?callback="+callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(openId)){
            JSONObject jsonObject = JSONObject.parseObject(openId);
            return jsonObject;
        }
        return null;
    }

    public static JSONObject getCashier(Parameter parameter){
       // parameter.setOpenid("ooanUviUvegNNXnH6LbYB3abpRE4");
        // 计算MD5签名
        String sb = parameter.getName() + parameter.getPayType() + parameter.getPrice() +
                parameter.getOrderId() + parameter.getNotifyUrl() + PayService.app_secret;
        String sign =  DigestUtils.md5DigestAsHex(sb.getBytes());
        Map<String,String> map = new HashMap<String, String>();
        map.put("name", parameter.getName());
        map.put("pay_type", parameter.getPayType());
        map.put("price", parameter.getPrice());
        map.put("order_id", parameter.getOrderId());
        map.put("notify_url", parameter.getNotifyUrl());
        map.put("sign", sign);
        map.put("openid", parameter.getOpenid());
        String post = "";
        try {
            post = XorpayHttpUtils.post("https://xorpay.com/api/pay/"+27024, map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(post!=null) {
            JSONObject json = JSONObject.parseObject(post);
            return json;
        }
        return null;
    }

    /**
     * 	获取订单的状态
     */
    public static JSONObject getState(String aoid){
//        String aoid = request.getParameter("aoid");//查询订单唯一标识
        String get = "";
        try {
            get = XorpayHttpUtils.get("https://xorpay.com/api/query/"+aoid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(get)){
            JSONObject jsonObject = JSONObject.parseObject(get);
            return jsonObject;
        }
        return null;
    }

}
