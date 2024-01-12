package com.ruoyi.common.kuaidi100.sdk.pojo;
/**
 * @Description:
 * @author cjf
 * @date 2023-08-05
 */

import lombok.Data;

/**
 * @author cjf
 * @date 2023/8/5
 */
@Data
public class ReqParam {
    /*查询的快递公司的编码， 一律用小写字母*/
    private String com;
    /*查询的快递单号*/
    private String num;
    /*收、寄件人的电话号码*/
    private String phone;
    /*出发地信息，最小颗粒到市级，例如：广东省深圳市*/
    private String from;
    /*目的地信息，最小颗粒到市级，例如：广东省深圳市*/
    private String to;

}
