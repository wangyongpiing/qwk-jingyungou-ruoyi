package com.ruoyi.common.kuaidi100.sdk.request.cloud;

import com.ruoyi.common.kuaidi100.sdk.request.cloud.CloudBaseReq;
import lombok.Data;

/**
 * @Author: api.kuaidi100.com
 * @Date: 2020-11-20 11:09
 */
@Data
public class COrderQueryReq extends CloudBaseReq {
    /**
     *
     */
    private String sendManPrintAddr;

    private String recManPrintAddr;

    private String address;
}
