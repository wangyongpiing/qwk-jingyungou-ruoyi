package com.ruoyi.web.controller.common;

import lombok.Data;

import java.util.Map;

@Data
public class XorpayVo {
    private String status;
    private Map<String, String> info;
    private String expires_in;
    private String aoid;
}
