package com.ruoyi.web.controller.system;

import java.util.*;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.alipay.AlipayService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.myJPush.MyJPushClient;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.xorpay.Parameter;
import com.ruoyi.common.xorpay.PayService;
import com.ruoyi.common.xorpay.XorpayUtils;
import com.ruoyi.common.xorpay.XorpayVo;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.qwk.domain.UserAccountBalance;
import com.ruoyi.qwk.mapper.UserAccountBalanceMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.IUserFriendsService;
import com.ruoyi.web.controller.common.WxPayBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.system.service.ISysMenuService;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
@Api(tags = "用户 API 接口")
public class SysLoginController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private IUserFriendsService iUserFriendsService;

    @Autowired
    private WxPayBean wxAppConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private MyJPushClient myJPushClient;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserAccountBalanceMapper userAccountBalanceMapper;

//    @Autowired
//    private IUserPrizeService userPrizeService;

//    @Autowired
//    private AlipayService alipayService;

    @GetMapping("/getOpenId")
    public AjaxResult getOpenId(){
        com.alibaba.fastjson.JSONObject openId = XorpayUtils.getOpenId(PayService.aid);
        return AjaxResult.success(openId);
    }

    @GetMapping("/getOrderStatus/{orderId}")
    public AjaxResult getOrderStatus(@PathVariable String orderId){
        com.alibaba.fastjson.JSONObject getState = XorpayUtils.getState(orderId);
        System.out.println("-------->"+getState);
        return AjaxResult.success(getState);
    }

    @GetMapping("/test")
    public AjaxResult test(){
        Parameter parameter = new Parameter();
        parameter.setName("alipay-xxx");//商品名称
        parameter.setPayType("alipay");//支付方式 收银台 jsapi
        parameter.setPrice("1.15");//价格
        parameter.setOrderId("11112XXXXX");//平台订单号，需要唯一
        parameter.setNotifyUrl("https://xorpay.com/main");//回调地址
        com.alibaba.fastjson.JSONObject cashier = XorpayUtils.getCashier(parameter);
        System.out.println("-------->"+cashier);

        return AjaxResult.success(cashier);
    }


    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    @ApiOperation(value = "登陆接口", notes = "")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        ajax.put("data",map);
        return ajax;
    }

    /**
     * 微信公众号登录方法
     *
     * @param loginBody 微信公众号登录方法
     * @return 结果
     */
    @PostMapping("/weChat/login")
    @ApiOperation(value = "登陆接口", notes = "")
    public AjaxResult weChatLogin(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.weChatLogin(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        ajax.put("data",map);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
//    @ApiOperation(value = "获取用户信息", notes = "")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        user.setPassword("");
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        Integer friendsCount = iUserFriendsService.getFriendsCount(user.getUserId());
        AjaxResult ajax = AjaxResult.success();
//        HashMap<String, Object> map = new HashMap<>();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
//        ajax.put("data",map);
//        map.put("friendsCount", friendsCount);
//        map.put("user", user);
        return ajax;
    }

    /**
     * APP获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getUserInfo")
    @ApiOperation(value = "获取用户信息", notes = "")
    public AjaxResult getUserInfo(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        SysUser sysUser = sysUserMapper.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
        if(ObjUtil.isNull(sysUser)){
            return AjaxResult.error(401,"请重新登录");
        }
        sysUser.setPassword("");
        Integer friendsCount = iUserFriendsService.getFriendsCount(SecurityUtils.getLoginUser().getUser().getUserId());
        //获取支付宝账号信息
        UserAccountBalance bal = new UserAccountBalance();
        bal.setUserId(String.valueOf(sysUser.getUserId()));
        List<UserAccountBalance> accountList = userAccountBalanceMapper.selectUserAccountBalanceList(bal);
        if(CollUtil.isNotEmpty(accountList)){
            bal = accountList.get(0);
            if(StrUtil.isEmpty(bal.getAlipayAccount())){
               bal.setAlipayAccount("");
            }
            if(StrUtil.isEmpty(bal.getAlipayAccountName())){
                bal.setAlipayAccountName("");
            }
        }
        AjaxResult ajax = AjaxResult.success();
        HashMap<String, Object> map = new HashMap<>();
        map.put("user",sysUser);
        map.put("friendsCount", friendsCount);
        map.put("account",bal);
        ajax.put("data",map);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    /**
     * 手机号登录方法
     *
     * @param
     * @return 结果
     */
    @ApiOperation("手机号登录")
    @ApiImplicitParam(name = "loginBody", value = "登录信息", dataType = "LoginBody")
    @PostMapping("/sms/login")
    public AjaxResult smsLogin(@RequestBody LoginBody loginBody){
        String mobile=loginBody.getMobile();
        String smsCode=loginBody.getSmsCode();
        String uuid=loginBody.getUuid();
        AjaxResult ajax = AjaxResult.success();
        Map<String, Object> map  = loginService.smsLogin(mobile, smsCode,uuid);
        ajax.put(Constants.TOKEN, map.get("token"));
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("token",token);
        ajax.put("data",map);
        return ajax;
    }

    /**
     * 微信登陆方法
     *
     * @param code 微信登陆对象
     * @return 结果
     */
    @ApiOperation("微信登录")
    @GetMapping("/wxLogin/{code}")
    public AjaxResult wxLogin(@PathVariable String code) {
      String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wxAppConfig.getAppId() + "&secret=" + wxAppConfig.getAppSecret() + "&code=" + code + "&grant_type=authorization_code";
      String res = restTemplate.getForObject(url, String.class);
      if (StringUtils.isEmpty(res)){
          log.error("微信获取openid失败code为:{}",code);
          return AjaxResult.error("获取微信登陆信息失败");
      }
        JSONObject jsonObject = JSONObject.parseObject(res);
        log.info("获取微信openid等信息 接口返回值---> {}",jsonObject);
//        System.out.println("res="+jsonObject);
        //获取session_key和openid
        String sessionKey = jsonObject.getString("session_key");
        String accessToken = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        if (!StringUtils.isEmpty(openid)) {
            //获取token
            String token = loginService.wxLogin(openid,accessToken);
            AjaxResult ajax = AjaxResult.success();
            ajax.put(Constants.TOKEN, token);
            return ajax;
        } else {
            return AjaxResult.error("微信登录失败！");
        }
    }

    /**
     * 绑定微信方法
     *
     * @param code 微信参数
     * @return 结果
     */
    @ApiOperation("绑定微信")
    @GetMapping("/wxLogin/{code}/{userName}")
    public AjaxResult bindWx(@PathVariable String code,@PathVariable String userName){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wxAppConfig.getAppId() + "&secret=" + wxAppConfig.getAppSecret() + "&code=" + code + "&grant_type=authorization_code";
        String res = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(res);
        String openid = jsonObject.getString("openid");
        if (StringUtils.isEmpty(openid))return AjaxResult.error("绑定失败");
        SysUser sysUser = sysUserMapper.selectUserByUserName(userName);
        sysUser.setOpenId(openid);
        int i = sysUserMapper.updateUser(sysUser);
        if (i<=0){
            return AjaxResult.error("绑定失败");
        }else {
            return AjaxResult.success("绑定成功");
        }

    }

    @Anonymous
    @GetMapping("/getDate")
    @ApiOperation("获取系统时间")
    public AjaxResult getDate(){
        return AjaxResult.success("获取成功",new Date());
    }


}
