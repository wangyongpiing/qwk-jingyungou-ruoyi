package com.ruoyi.web.controller.system;
/**
 * @Description:
 * @author cjf
 * @date 2023-08-01
 */
import com.ijpay.wxpay.WxPayApiConfig;
/**
 * @author cjf
 * @date 2023/8/1
 */
public abstract class AbstractWxPayApiController {
    /**
     * 获取微信支付配置
     *
     * @return {@link WxPayApiConfig} 微信支付配置
     */
    public abstract WxPayApiConfig getApiConfig();
}
