package com.ruoyi.framework.web.service;

import javax.annotation.Resource;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.common.im.TencentCloudImUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.RandomUtil;
import com.ruoyi.framework.smsConfig.SmsCodeAuthenticationToken;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.mapper.*;
import com.ruoyi.qwk.service.IInviteSubordinateService;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.BlackListException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.context.AuthenticationContextHolder;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private TencentCloudImUtil tencentCloudImUtil;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private IInviteSubordinateService inviteSubordinateService;

    @Autowired
    private TurntableRedEnvelopeMapper turntableRedEnvelopeMapper;

    @Autowired
    private UserAccountBalanceMapper userAccountBalanceMapper;

    @Autowired
    private PersonalDailyTaskRecordMapper personalDailyTaskRecordMapper;

    @Autowired
    private PersonalTasksMapper personalTasksMapper;

    @Autowired
    private TurntableRedEnvelopeRecordMapper turntableRedEnvelopeRecordMapper;

    @Value("${token.soloLogin}")
    private boolean soloLogin;

    private static final Logger log = LoggerFactory.getLogger("login");




    public void test(){
        tencentCloudImUtil.addGroup("12531260");
    }


    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid)
    {
        // 验证码校验
//        validateCaptcha(username, code, uuid);
        // 登录前置校验
        loginPreCheck(username, password);
        // 用户验证
        Authentication authentication = null;
        try
        {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        finally
        {
            AuthenticationContextHolder.clearContext();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 判断是否允许账户多终端同时登录
        if (!soloLogin)
        {
            // 如果用户不允许多终端同时登录，清除缓存信息
            String userIdKey = Constants.LOGIN_USERID_KEY + loginUser.getUser().getUserId();
            String userKey = redisCache.getCacheObject(userIdKey);
            if (StringUtils.isNotEmpty(userKey))
            {
                redisCache.deleteObject(userIdKey);
                redisCache.deleteObject(userKey);
            }
        }

        SysUser user = new SysUser();
        //im导入单个账号
        tencentCloudImUtil.accountImport(loginUser.getUsername(),loginUser.getUser().getNickName(),loginUser.getUser().getAvatar());
        String userSig = tencentCloudImUtil.getTxCloudUserSig(loginUser.getUsername());
        tencentCloudImUtil.addGroup(user.getUserName());
        user.setUserId(loginUser.getUserId());
        user.setOnLineStatus("0");
        user.setImUserId(loginUser.getUsername());
        user.setImUserSig(userSig);
        userService.updateUserProfile(user);//修改登陆状态

        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String weChatLogin(String username, String password, String code, String uuid)
    {
        // 验证码校验
//        validateCaptcha(username, code, uuid);
        // 登录前置校验
        loginPreCheck(username, password);
        // 用户验证
        Authentication authentication = null;
        try
        {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        finally
        {
            AuthenticationContextHolder.clearContext();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 判断是否允许账户多终端同时登录
        if (!soloLogin)
        {
            // 如果用户不允许多终端同时登录，清除缓存信息
            String userIdKey = Constants.LOGIN_USERID_KEY + loginUser.getUser().getUserId();
            String userKey = redisCache.getCacheObject(userIdKey);
            if (StringUtils.isNotEmpty(userKey))
            {
                redisCache.deleteObject(userIdKey);
                redisCache.deleteObject(userKey);
            }
        }

        SysUser user = new SysUser();
        //im导入单个账号
        tencentCloudImUtil.accountImport(loginUser.getUsername(),loginUser.getUser().getNickName(),loginUser.getUser().getAvatar());
        String userSig = tencentCloudImUtil.getTxCloudUserSig(loginUser.getUsername());
        tencentCloudImUtil.addGroup(user.getUserName());
        user.setUserId(loginUser.getUserId());
        user.setOnLineStatus("0");
        user.setImUserId(loginUser.getUsername());
        user.setImUserSig(userSig);
        userService.updateUserProfile(user);//修改登陆状态

        // 生成token
        return tokenService.createToken(loginUser);
    }



    /**
     * 登录前置校验
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // IP黑名单校验
        String blackStr = configService.selectConfigByKey("sys.login.blackIPList");
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("login.blocked")));
            throw new BlackListException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId)
    {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr());
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }

    /**
     * 手机号登录验证
     *
     * @param mobile 手机号
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public Map<String, Object> smsLogin(String mobile, String code, String uuid)
    {
        Map<String, Object> map = new HashMap<>();
        // 用户验证
        Authentication authentication = null;

        try
        {
            checkSmsCode(mobile,code,uuid);

            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new SmsCodeAuthenticationToken(mobile));
        }
        catch (Exception e)
        {

            AsyncManager.me().execute(AsyncFactory.recordLogininfor(mobile, Constants.LOGIN_FAIL, e.getMessage()));
//            throw new CustomException(e.getMessage());

        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(mobile, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 判断是否允许账户多终端同时登录
        if (!soloLogin)
        {
            // 如果用户不允许多终端同时登录，清除缓存信息
            String userIdKey = Constants.LOGIN_USERID_KEY + loginUser.getUser().getUserId();
            String userKey = redisCache.getCacheObject(userIdKey);
            if (StringUtils.isNotEmpty(userKey))
            {
                redisCache.deleteObject(userIdKey);
                redisCache.deleteObject(userKey);
            }
        }
        SysUser user = new SysUser();
        //im导入单个账号
        tencentCloudImUtil.accountImport(loginUser.getUsername(),
                loginUser.getUser().getNickName(),
                StringUtils.isEmpty(loginUser.getUser().getAvatar()) ? "https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F20230831.png": loginUser.getUser().getAvatar());
        String userSig = tencentCloudImUtil.getTxCloudUserSig(loginUser.getUsername());
//        tencentCloudImUtil.addGroup(loginUser.getUsername());
        user.setUserId(loginUser.getUser().getUserId());
        user.setOnLineStatus("0");
        user.setImUserId(loginUser.getUsername());
        user.setImUserSig(userSig);
        user.setLoginIp(IpUtils.getIpAddr());
        user.setLoginDate(new Date());
        user.setUpdateBy(loginUser.getUser().getUserName());
        userService.updateUserProfile(user);
        // 生成token
        String token = tokenService.createToken(loginUser);
        map.put("token",token);
        return map;
    }

    /**
     * 检查手机号登录
     * @param
     */
    private void checkSmsCode(String mobile,String inputCode, String uuid) {

        String verifyKey = Constants.SMS_CAPTCHA_CODE_KEY + uuid;

        Map<String, Object> smsCode =  redisCache.getCacheObject(verifyKey);
//        redisCache.deleteObject(verifyKey);
        if(StringUtils.isEmpty(inputCode)){
            throw new BadCredentialsException("验证码不能为空");
        }

        if(smsCode == null) {
            throw new BadCredentialsException("验证码失效");
        }

        String applyMobile = (String) smsCode.get("mobile");
        int code = (int) smsCode.get("code");

        if(!applyMobile.equals(mobile)) {
            throw new BadCredentialsException("手机号码不一致");
        }
        if(code != Integer.parseInt(inputCode)) {
            throw new BadCredentialsException("验证码错误");
        }
    }

    /**
     * 无密码登录
     * @param userName
     * @return
     */
    public String noPwdLogin(String userName){

        LoginUser loginUser
                = (LoginUser)userDetailsService.loadUserByUsername(userName);
        // 记录登陆信息
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_SUCCESS,
                MessageUtils.message("user.login.success")));
        recordLoginInfo(loginUser.getUserId());
        // 判断是否允许账户多终端同时登录
        if (!soloLogin)
        {
            // 如果用户不允许多终端同时登录，清除缓存信息
            String userIdKey = Constants.LOGIN_USERID_KEY + loginUser.getUser().getUserId();
            String userKey = redisCache.getCacheObject(userIdKey);
            if (StringUtils.isNotEmpty(userKey))
            {
                redisCache.deleteObject(userIdKey);
                redisCache.deleteObject(userKey);
                log.info(DateUtils.getTime()+userName+"被挤号");
            }
        }
        return tokenService.createToken(loginUser);
    }


    /**
     * 微信登录
     *
     * @param openId 登录凭证 只能用一次
     * @return
     */
    public String wxLogin(String openId,String accessToken){

        String nickName = getUserName().toString();// 生成随机账号昵称
        //获取微信头像
        //https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?access_token=ACCESS_TOKEN
//        restTemplate.patchForObject()
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId;
        String res = restTemplate.getForObject(url, String.class);
        if (StringUtils.isEmpty(res)){
            log.error("获取微信用户信息失败!");
        }else {
            log.info("获取微信用户信息----->"+res);
        }
        JSONObject jsonObject = JSONObject.parseObject(res);
        String nickname = jsonObject.getString("nickname");//微信昵称
        try {
            nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");//重新编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String avatarUrl = jsonObject.getString("headimgurl");//微信头像

        //还可以获取其他信息
        //根据openid判断数据库中是否有该用户
        //根据openid查询用户信息
        SysUser user = userService.selectWxUserByOpenId(openId);

        //如果查不到，则新增，查到了，则更新
//        SysUser user = new SysUser();
        if (user == null) {
            user = new SysUser();
            // 新增
            user.setUserName(nickName);// 生成16位随机用户名
            user.setNickName(nickname);
            user.setAvatar(avatarUrl);
            //wxUser.setUnionId(unionid);
            user.setOpenId(openId);
            user.setCreateTime(DateUtils.getNowDate());
            user.setCreateBy("系统");
            user.setDeptId(200L);//微信注册为游戏用户
            user.setPassword(SecurityUtils.encryptPassword("123456"));
            //新增 用户
            userService.insertUser(user);

            addUserAssets(user);//添加用户资产
            inviteSubordinateService.addInviteSubordinate(IpUtils.getIpAddr(),user);//添加用户上级
            tencentCloudImUtil.accountImport(user.getUserName(),user.getNickName(),user.getAvatar());
            tencentCloudImUtil.addGroup(user.getUserName());//添加群组
            tencentCloudImUtil.sendGroupMsg(user.getNickName());//发送欢迎消息
        }
        user.setAvatar(StringUtils.isEmpty(user.getAvatar()) ? "https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F20230831.png": user.getAvatar());
        tencentCloudImUtil.accountImport(user.getUserName(),user.getNickName(),user.getAvatar());
//        tencentCloudImUtil.addGroup(user.getUserName());//添加群组
        String userSig = tencentCloudImUtil.getTxCloudUserSig(user.getUserName());
        user.setOnLineStatus("0");
        user.setImUserId(user.getUserName());
        user.setImUserSig(userSig);
        user.setOpenId(openId);
        user.setLoginIp(IpUtils.getIpAddr());
        user.setLoginDate(DateUtils.getNowDate());
        user.setUpdateBy(user.getUserName());
        user.setAvatar(avatarUrl);
        userService.updateUserProfile(user);
        return this.noPwdLogin(user.getUserName());
    }


    //生成随机用户名
    private Long getUserName(){
        Long randomForIntegerBounded = RandomUtil.getRandomForLongBounded(10000000, 99999999);
        SysUser sysUser = userService.selectUserByUserName(randomForIntegerBounded.toString());
        if (ObjectUtils.isEmpty(sysUser)){
            return randomForIntegerBounded;
        }else {
            return getUserName();
        }
    }

    //添加用户资产
    private void addUserAssets(SysUser user){
        Date date = new Date();
        List<SysDictData> dictDataList = sysDictDataMapper.selectDictDataByType("wtb_asset_type");
        for (SysDictData dictData : dictDataList) {
            UserAssets userAssets = new UserAssets();
            userAssets.setUserId(user.getUserId());
//                userAssets.setUserPhone(user.getPhonenumber());
            if ("1".equals(dictData.getDictValue())){
                userAssets.setTotal("100000");
            }else {
                userAssets.setTotal("0");
            }
            userAssets.setType(Long.parseLong(dictData.getDictValue()));
            userAssets.setNickName(user.getNickName());
            userAssets.setCreatedTime(date);
            userAssets.setCreatedBy("系统");
            userAssetsMapper.insertUserAssets(userAssets);
        }
    }


//    /**
//     * 校验验证码
//     *
//     * @param username 用户名
//     * @param code 验证码
//     * @param uuid 唯一标识
//     * @return 结果
//     */
//    public void validateCaptcha(String username, String code, String uuid)
//    {
//        boolean captchaEnabled = configService.selectCaptchaEnabled();
//        if (captchaEnabled)
//        {
//            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
//            String captcha = redisCache.getCacheObject(verifyKey);
//            redisCache.deleteObject(verifyKey);
//            if (captcha == null)
//            {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
//                throw new CaptchaExpireException();
//            }
//            if (!code.equalsIgnoreCase(captcha))
//            {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//                throw new CaptchaException();
//            }
//        }
//    }
}
