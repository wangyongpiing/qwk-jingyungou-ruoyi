package com.ruoyi.common.core.domain.model;
/**
 * @Description:
 * @author cjf
 * @date 2023-08-07
 */

/**
 * 微信登陆对象
 * @author cjf
 * @date 2023/8/7
 */
public class WxLoginBody {
    /**
     * 临时登陆凭证 code 只能使用一次
     */
    private String code;

    /**
     * 偏移量
     */
    private String encryptedIv;

    /**
     * 加密数据
     */
    private String encryptedData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEncryptedIv() {
        return encryptedIv;
    }

    public void setEncryptedIv(String encryptedIv) {
        this.encryptedIv = encryptedIv;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

}
