package com.ruoyi.common.kuaidi100.sdk.request;

import lombok.Data;

import java.io.File;

/**
 * @Author: api.kuaidi100.com
 * @Date: 2020-07-20 9:36
 */
@Data
public class CloudAttachmentReq extends com.kuaidi100.sdk.request.PrintReq {
    private File file;
}
