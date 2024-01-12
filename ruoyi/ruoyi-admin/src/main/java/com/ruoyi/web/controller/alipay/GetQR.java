package com.ruoyi.web.controller.alipay;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.xorpay.XorpayVo;
import org.springframework.http.*;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * @Author: kgw
 * @Date: 2023/8/31 16:03
 * @Description:
 */
@RestController
@RequestMapping("/getQr")
public class GetQR {
    /**
     * 两处需要替换-替换后直接运行，浏览器访问：http://localhost:8080/indexl.html 即可看到支付二维码
     * 第40行、第69行    aid和appSecret在xorpay后台查看-需开通微信支付
     **/


    @GetMapping("/getSrc")
    public String getQrSrc() {
        // 创建RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();

        String name = "测试商品";    //商品名称
        String payType = "native";      //支付方式
        String price = "0.01";         //价格
        String orderId = new Date().toString();      //平台订单号，需要唯一
        String notifyUrl = "https://xorpay.com/main";       //回调地址，测试可以用这个，实际项目中需要修改
        String appSecret = "appSecret";      //你的appSecret

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 设置请求体参数
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", name);
        requestBody.add("pay_type", payType);
        requestBody.add("price", price);
        requestBody.add("order_id", orderId);
        requestBody.add("notify_url", notifyUrl);

        // 生成sign-拼接参数值
        StringBuilder sb = new StringBuilder();
        for (List<String> value : requestBody.values()) {
            sb.append(value.get(0));
        }
        sb.append(appSecret);
        // 计算MD5签名
        String sign =  DigestUtils.md5DigestAsHex(sb.toString().getBytes());

        requestBody.add("sign", sign);       //sign
        requestBody.add("order_uid", "api-test-user");      //订单用户
        requestBody.add("more", "");        //订单其他信息，回调时原样传回

        // 发送POST请求
        String url = "http://xorpay.com/api/pay/27024";   //XXX需替换成你的aid
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<>(requestBody, headers), String.class);

        // 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            // 将字符串解析为JSONObject对象
            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            // 将JSONObject对象转换为Map对象
            XorpayVo xorpayVo = jsonObject.toJavaObject(XorpayVo.class);
            return xorpayVo.getInfo().get("qr");
        } else {
            return "";
        }
    }
}
