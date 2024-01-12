package com.ruoyi.framework.web.service;
/**
 * @Description:
 * @author cjf
 * @date 2023-07-05
 */

import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.im.TencentCloudImUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.uuid.RandomUtil;
import com.ruoyi.qwk.domain.TurntableRedEnvelope;
import com.ruoyi.qwk.domain.UserAssets;
import com.ruoyi.qwk.mapper.TurntableRedEnvelopeMapper;
import com.ruoyi.qwk.mapper.UserAssetsMapper;
import com.ruoyi.qwk.service.IInviteSubordinateService;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.service.ISysUserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;


/**
 * @author cjf
 * @date 2023/7/5
 */
@Service
public class SmsUserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private TencentCloudImUtil tencentCloudImUtil;

    @Autowired
    private IInviteSubordinateService inviteSubordinateService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByPhone(phone);
        if (StringUtils.isNull(user)) {
            RegisterBody registerBody = new RegisterBody();
            registerBody.setPassword("123456");//默认密码
            registerBody.setMobile(phone);
//            registerBody.setUsername(getUserName().toString());
            registerBody.setUsername(phone);
            registerBody.setPhonenumber(phone);
            registerBody.setAvatar("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png");//默认头像
            registerBody.setDeptId(200L);//手机号注册为游戏用户
            String register = registerService.register(registerBody);
//            tencentCloudImUtil.addGroup(registerBody.getUsername());
            user = userService.selectUserByPhone(phone);
            Date date = new Date();
            List<SysDictData> dictDataList = sysDictDataMapper.selectDictDataByType("wtb_asset_type");
            for (SysDictData dictData : dictDataList) {
                UserAssets userAssets = new UserAssets();
                userAssets.setUserId(user.getUserId());
                userAssets.setUserPhone(user.getPhonenumber());
                userAssets.setTotal("0");
                //新用户添加100糖果
                if ("3".equals(dictData.getDictValue())) {
                    userAssets.setTotal("100");
                }
                userAssets.setType(Long.parseLong(dictData.getDictValue()));
                userAssets.setNickName(user.getNickName());
                userAssets.setCreatedTime(date);
                userAssets.setCreatedBy("系统");
                userAssetsMapper.insertUserAssets(userAssets);
            }
            inviteSubordinateService.addInviteSubordinate(IpUtils.getIpAddr(),user);//添加上下级关系
            tencentCloudImUtil.accountImport(registerBody.getUsername(),registerBody.getUsername(),registerBody.getAvatar());
            tencentCloudImUtil.addGroup(registerBody.getUsername());//添加群组
            tencentCloudImUtil.sendGroupMsg(registerBody.getUsername());//发送欢迎消息
//            log.info("登录手机号：{} 不存在.", phone);
//            throw new UsernameNotFoundException("登录手机号：" + phone + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", phone);
            throw new BaseException("对不起，您的账号：" + phone + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", phone);
            throw new BaseException("对不起，您的账号：" + phone + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user, permissionService.getMenuPermission(user));
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

}
