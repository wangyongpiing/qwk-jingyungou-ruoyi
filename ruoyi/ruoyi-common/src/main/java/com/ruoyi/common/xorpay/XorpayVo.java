package com.ruoyi.common.xorpay;
/**
 * @Description:
 * @author cjf
 * @date 2023-11-08
 */

import lombok.Data;

import java.util.Map;

/**
 * @author cjf
 * @date 2023/11/8
 */
@Data
public class XorpayVo {
    private String status;
    private Map<String, String> info;
    private String expires_in;
    private String aoid;
    private String orderId;
    private String payPrice;
    private String payTime;
    private String more;
    private String detail;
}
