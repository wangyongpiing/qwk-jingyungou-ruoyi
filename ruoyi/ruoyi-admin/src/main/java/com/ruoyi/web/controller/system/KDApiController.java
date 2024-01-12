package com.ruoyi.web.controller.system;
/**
 * @Description:
 * @author cjf
 * @date 2023-08-04
 */

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.kuaidi100.sdk.api.QueryTrack;
import com.kuaidi100.sdk.contant.CompanyConstant;
import com.kuaidi100.sdk.core.IBaseClient;
import com.kuaidi100.sdk.pojo.HttpResult;
import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.request.QueryTrackReq;
import com.kuaidi100.sdk.utils.PropertiesReader;
import com.kuaidi100.sdk.utils.SignUtils;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.kuaidi100.sdk.api.QueryTrackMap;
import com.ruoyi.common.kuaidi100.sdk.pojo.ReqParam;
import com.ruoyi.common.kuaidi100.sdk.response.QueryTrackMapResp;
import com.ruoyi.system.service.impl.SysDictDataServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cjf
 * @date 2023/8/4
 */
@RestController
@RequestMapping("/fastMail")
@Api(tags = "快递API")
public class KDApiController {
//    @Autowired
//    KDApiBean kdApiBean;

    private String key = PropertiesReader.get("key");
    private String customer = PropertiesReader.get("customer");
    private String secret = PropertiesReader.get("secret");
    private String siid = PropertiesReader.get("siid");
    private String userid = PropertiesReader.get("userid");
    private String tid = PropertiesReader.get("tid");
    private String secret_key = PropertiesReader.get("secret_key");
    private String secret_secret = PropertiesReader.get("secret_secret");

    @Autowired
    private SysDictDataServiceImpl sysDictDataService;

    @GetMapping("/query")
    @ApiOperation("查询快递物流信息")
    public AjaxResult query(ReqParam reqParam) throws Exception {
        QueryTrackReq queryTrackReq = new QueryTrackReq();
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom(reqParam.getCom());
        queryTrackParam.setNum(reqParam.getNum());
        queryTrackParam.setPhone(reqParam.getPhone());
        String param = new Gson().toJson(queryTrackParam);

        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtils.querySign(param ,key,customer));

        IBaseClient baseClient = new QueryTrack();
        HttpResult execute = baseClient.execute(queryTrackReq);
        String body = execute.getBody();
        JSONObject entries = JSONUtil.parseObj(body);
        //错误返回
//        String result = entries.get("result").toString();
//        String message = entries.get("message").toString();
//        if("false".equals(result)){
//            return AjaxResult.error(message);
//        }
        String com = entries.get("com").toString();
        String dictLabel = sysDictDataService.selectDictLabel("qwk_kd", com);
        entries.set("expressCompany",dictLabel);

        return AjaxResult.success(entries);
    }


    @GetMapping("/queryMapView")
    @ApiOperation("查询快递物流地图轨迹")
    public AjaxResult queryMapView(ReqParam reqParam) throws Exception {
        QueryTrackReq queryTrackReq = new QueryTrackReq();
        QueryTrackParam queryTrackParam = new QueryTrackParam();
        queryTrackParam.setCom(reqParam.getCom());
        queryTrackParam.setNum(reqParam.getNum());
        queryTrackParam.setPhone(reqParam.getPhone());
        queryTrackParam.setFrom(reqParam.getFrom());
        queryTrackParam.setTo(reqParam.getTo());
//        queryTrackParam.setResultv2("2");
        String param = new Gson().toJson(queryTrackParam);

        queryTrackReq.setParam(param);
        queryTrackReq.setCustomer(customer);
        queryTrackReq.setSign(SignUtils.querySign(param ,key,customer));

        IBaseClient baseClient = new QueryTrackMap();
        HttpResult result = baseClient.execute(queryTrackReq);

        QueryTrackMapResp queryTrackMapResp = new Gson().fromJson(result.getBody(), QueryTrackMapResp.class);

        return AjaxResult.success(queryTrackMapResp);
    }
}
