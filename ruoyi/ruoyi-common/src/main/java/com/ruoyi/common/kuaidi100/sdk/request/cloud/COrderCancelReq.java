package com.ruoyi.common.kuaidi100.sdk.request.cloud;

import com.ruoyi.common.kuaidi100.sdk.request.cloud.CloudBaseReq;
import lombok.Data;

/**
 * @Author: api.kuaidi100.com
 * @Date: 2020-11-25 15:47
 */
@Data
public class COrderCancelReq extends CloudBaseReq {
    /**
     * 任务ID
     */
    private String taskId;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 取消原因，例：暂时不寄件了
     */
    private String cancelMsg;
}
