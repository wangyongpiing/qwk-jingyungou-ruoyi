package com.ruoyi.web.controller.system;
/**
 * @Description:
 * @author cjf
 * @date 2023-07-06
 */
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.im.TencentCloudImUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
/**
 * @author cjf
 * @date 2023/7/6
 */
@RestController
@RequestMapping("/wnapp")
@Api(tags = "腾讯IM实时对话")
public class TXIMController {
    @Autowired
    private TencentCloudImUtil tencentCloudImUtil;

    @GetMapping("/contextLoads/{userId}")
    @ApiOperation("获取userSig")
    public AjaxResult contextLoads(@PathVariable String userId) {
        String userSig = tencentCloudImUtil.getTxCloudUserSig(userId);
        System.out.println(userSig);
        return AjaxResult.success(userSig);
    }

//    /**
//     * 查询单聊消息
//     * @param fromUserId 发送方用户id
//     * @param toUserId 接收方用户id
//     * @param maxCnt 查询条数
//     * @param startTime 起始时间（单位：秒）
//     * @param endTime 结束时间（单位：秒）
//     * @param lastMsgKey 最后一条消息的 MsgKey  可以为null
//     * @return 单聊消息列表
//     */
//    @GetMapping("/testSendIMMsg")
//    @ApiOperation("查询单聊消息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "fromUserId", value = "发送方用户id", dataType = "String", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "toUserId", value = "接收方用户id", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "maxCnt", value = "查询条数", dataType = "Integer", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "startTime", value = "起始时间（单位：秒）", dataType = "Long", dataTypeClass = Long.class),
//            @ApiImplicitParam(name = "endTime", value = "结束时间（单位：秒）", dataType = "Long", dataTypeClass = Long.class),
//            @ApiImplicitParam(name = "lastMsgKey", value = "最后一条消息的 MsgKey  可以为null", dataType = "String", dataTypeClass = String.class)
//    })
//    public AjaxResult testSendIMMsg(String fromUserId,String toUserId,Integer maxCnt,Long startTime,Long endTime,String lastMsgKey){
//        Calendar cale = Calendar.getInstance();
//        cale.setTime(new Date());
//        cale.set(Calendar.HOUR_OF_DAY, 0);
//        cale.set(Calendar.MINUTE, 0);
//        cale.set(Calendar.SECOND, 0);
////        Date beginTime = cale.getTime();
//        String s = tencentCloudImUtil.adminGetRoamMsg(fromUserId, toUserId, maxCnt, startTime, endTime, lastMsgKey);
//        return AjaxResult.success(s);
//    }
//
//    /**
//     * 查询账号是否已经导入im
//     * @param userIds 用户id集合
//     */
//    @GetMapping("/testAccount")
//    @ApiOperation("查询账号是否已经导入im")
//    @ApiImplicitParam(name = "userIds",value = "用户id集合")
//    public AjaxResult testAccount(List<String> userIds){
//
//        String accountCheck = tencentCloudImUtil.accountCheck(userIds);
//        System.out.println(accountCheck);
//        return AjaxResult.success(accountCheck);
//    }
//
//    /**
//     * 单发单聊消息
//     * @param syncOtherMachine 是否同步消息到发送方（1-同步，2-不同步）
//     * @param fromUserId 发送方用户id
//     * @param toUserId 接收方用户id
//     * @param msgType 消息对象类型
//     * @param msgContent 消息内容
//     */
//    @GetMapping("/sendMsg")
//    @ApiOperation("单发单聊消息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "syncOtherMachine", value = "是否同步消息到发送方（1-同步，2-不同步）", dataType = "Integer", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "fromUserId", value = "发送方用户id", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "toUserId", value = "接收方用户id", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "msgType", value = "消息对象类型", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "msgContent", value = "消息内容", dataType = "String", dataTypeClass = String.class)
//    })
//    public AjaxResult sendMsg(Integer syncOtherMachine,String fromUserId,String toUserId,String msgType,String msgContent){
//
//        String sendMsg = tencentCloudImUtil.sendMsg(syncOtherMachine,fromUserId,toUserId,msgType,msgContent);
//        System.out.println(sendMsg);
//        return AjaxResult.success(sendMsg);
//    }

//    /**
//     * 查询账号是否已经导入im
//     * @param userIds 用户id集合
//     */
//    @GetMapping("/testAccount")
//    @ApiOperation("查询账号是否已经导入im")
//    @ApiImplicitParam(name = "userIds",value = "用户id集合")
//    public AjaxResult testAccount(List<String> userIds){
//
//        String accountCheck = tencentCloudImUtil.accountCheck(userIds);
//        System.out.println(accountCheck);
//        return AjaxResult.success(accountCheck);
//    }
//
//    /**
//     * 单发单聊消息
//     * @param syncOtherMachine 是否同步消息到发送方（1-同步，2-不同步）
//     * @param fromUserId 发送方用户id
//     * @param toUserId 接收方用户id
//     * @param msgType 消息对象类型
//     * @param msgContent 消息内容
//     */
//    @GetMapping("/sendMsg")
//    @ApiOperation("单发单聊消息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "syncOtherMachine", value = "是否同步消息到发送方（1-同步，2-不同步）", dataType = "Integer", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "fromUserId", value = "发送方用户id", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "toUserId", value = "接收方用户id", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "msgType", value = "消息对象类型", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "msgContent", value = "消息内容", dataType = "String", dataTypeClass = String.class)
//    })
//    public AjaxResult sendMsg(Integer syncOtherMachine,String fromUserId,String toUserId,String msgType,String msgContent){
//
//        String sendMsg = tencentCloudImUtil.sendMsg(syncOtherMachine,fromUserId,toUserId,msgType,msgContent);
//        System.out.println(sendMsg);
//        return AjaxResult.success(sendMsg);
//    }
//
//    /**
//     * 撤回单聊消息
//     * 发送方用户id
//     * 接收方用户id
//     * MsgKey
//     */
//    @GetMapping("/adminMsgWithDraw")
//    @ApiOperation("撤回单聊消息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "fromUserId", value = "发送方用户id", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "toUserId", value = "接收方用户id", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "msgKey", value = "当前信息key", dataType = "String", dataTypeClass = String.class)
//    })
//    public void adminMsgWithDraw(String fromUserId,String toUserId,String msgKey){
//        tencentCloudImUtil.adminMsgWithDraw(fromUserId, toUserId, msgKey);
//    }
//
//    /**
//     * 设置单聊消息已读
//     * @param reportUserId 读取消息的用户
//     * @param peerUserId 发送消息的用户
//     */
//    @GetMapping("/adminSetMsgRead")
//    @ApiOperation("设置单聊消息已读")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "reportUserId", value = "读取消息的用户", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "peerUserId", value = "发送消息的用户", dataType = "String", dataTypeClass = String.class)
//    })
//    public void adminSetMsgRead(String reportUserId,String peerUserId){
//        tencentCloudImUtil.adminSetMsgRead(reportUserId,peerUserId);
//    }
//
//    /**
//     * 删除账号
//     * @param userIds  账号集合
//     */
//    @GetMapping("/accountDelete")
//    @ApiOperation("删除账号")
//    @ApiImplicitParam(name = "userIds",value = "账号集合")
//    public void accountDelete(List<String> userIds){
//        tencentCloudImUtil.accountDelete(userIds);
//    }

}
