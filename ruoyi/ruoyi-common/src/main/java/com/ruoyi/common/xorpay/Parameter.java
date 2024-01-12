package com.ruoyi.common.xorpay;
/**
 * @Description:
 * @author cjf
 * @date 2023-11-09
 */

import lombok.Data;

/**
 * @author cjf
 * @date 2023/11/9
 */
@Data
public class Parameter {

    private String name;
    private String payType;
    private String price;
    private String orderId;
    private String orderUid;
    private String notifyUrl;
    private String returnUrl;
    private String cancelUrl;
    private String more;
    private int expire;
    private String sign;
    private String openid;
}
