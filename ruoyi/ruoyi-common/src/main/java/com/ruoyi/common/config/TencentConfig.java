package com.ruoyi.common.config;/**
 * @Description:
 * @author cjf
 * @date 2023-06-29
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cjf
 * @date 2023/6/29
 */
@Component
@ConfigurationProperties(prefix = "tencent")
public class TencentConfig {
    private int smsAppId;
    private String smsAppKey;
    private int smsTemplatetd;
    private String smsSign;
    private int smsTemplateId;

    public int getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(int smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }

    public int getSmsAppId() {
        return smsAppId;
    }

    public void setSmsAppId(int smsAppId) {
        this.smsAppId = smsAppId;
    }

    public String getSmsAppKey() {
        return smsAppKey;
    }

    public void setSmsAppKey(String smsAppKey) {
        this.smsAppKey = smsAppKey;
    }

    public int getSmsTemplatetd() {
        return smsTemplatetd;
    }

    public void setSmsTemplatetd(int smsTemplatetd) {
        this.smsTemplatetd = smsTemplatetd;
    }

    public String getSmsSign() {
        return smsSign;
    }

    public void setSmsSign(String smsSign) {
        this.smsSign = smsSign;
    }

    @Override
    public String toString() {
        return "TencentConfig{" +
                "smsAppId=" + smsAppId +
                ", smsAppKey='" + smsAppKey + '\'' +
                ", smsTemplatetd=" + smsTemplatetd +
                ", smsSign='" + smsSign + '\'' +
                ", smsTemplateId=" + smsTemplateId +
                '}';
    }
}
