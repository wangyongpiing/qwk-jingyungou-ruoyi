package com.ruoyi.common.config;
/**
 * @Description:
 * @author cjf
 * @date 2023-06-29
 */

import com.alibaba.fastjson2.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.ruoyi.common.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

/**
 * @author cjf
 * @date 2023/6/29
 * 腾讯云短信
 */
@Component
public class TencentSms {

    @Autowired
    private TencentConfig tencentConfig;

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @param code  验证码
     */
    public Result senSms(String phone, String code) {
        Result result = new Result();
        try {
            //短信模板中的参数列表
            String[] params = {code};
            SmsSingleSender sender = new SmsSingleSender(tencentConfig.getSmsAppId(), tencentConfig.getSmsAppKey());
            SmsSingleSenderResult sendResult = sender.sendWithParam("86", phone,
                    tencentConfig.getSmsTemplateId(), params, tencentConfig.getSmsSign(), "", "");
            if (sendResult.result == 0) {
                result.setCode(1);
                result.setMsg("发送成功");
            } else {
                result.setCode(0);
                result.setMsg(sendResult.errMsg);
            }
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
            result.setCode(0);
            result.setMsg("相应码错误");
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
            result.setCode(0);
            result.setMsg("JSON解析错误");
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
            result.setMsg("网络IO错误");
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
        return result;
    }
}
