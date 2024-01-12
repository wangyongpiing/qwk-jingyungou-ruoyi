package com.ruoyi.common.txyIdCard;
/**
 * @Description:
 * @author cjf
 * @date 2023-07-13
 */
import com.ruoyi.common.core.domain.AjaxResult;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author cjf
 * @date 2023/7/13
 */
@Component
public class IdCardVerification {

    @Value("${txyApi.secretId}")
    private String secretId;
    @Value("${txyApi.secretKey}")
    private String secretKey;

    public AjaxResult authentication(String name,String idCard){
        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential(secretId, secretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            IdCardVerificationRequest req = new IdCardVerificationRequest();
            req.setName(name);
            req.setIdCard(idCard);
            // 返回的resp是一个IdCardVerificationResponse的实例，与请求对象对应
            IdCardVerificationResponse resp = client.IdCardVerification(req);
            if (resp.getResult().equals("0")){
                return AjaxResult.success();
            }else {
                return AjaxResult.error();
            }
            // 输出json格式的字符串回包
//            System.out.println(IdCardVerificationResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return AjaxResult.error(e.toString());
        }
    }


}
