package com.ruoyi.common.kuaidi100.sdk.request;

import lombok.Data;

/**
 * @Author: api.kuaidi100.com
 * @Date: 2020-12-04 11:11
 */
@Data
public class AutoNumReq extends com.kuaidi100.sdk.request.BaseRequest {

    private String key;

    private String num;
}
