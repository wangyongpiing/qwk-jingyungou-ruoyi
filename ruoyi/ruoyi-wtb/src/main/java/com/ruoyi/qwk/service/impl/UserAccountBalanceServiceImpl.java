package com.ruoyi.qwk.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.domain.Person;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.mapper.AssetDetailsMapper;
import com.ruoyi.qwk.mapper.InviteSubordinateMapper;
import com.ruoyi.qwk.mapper.TurntableRedEnvelopeRecordMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.UserAccountBalanceMapper;
import com.ruoyi.qwk.service.IUserAccountBalanceService;

/**
 * 用户账户余额Service业务层处理
 *
 * @author cjf
 * @date 2023-12-21
 */
@Service
public class UserAccountBalanceServiceImpl implements IUserAccountBalanceService
{
    @Autowired
    private UserAccountBalanceMapper userAccountBalanceMapper;

    @Autowired
    private TurntableRedEnvelopeRecordMapper turntableRedEnvelopeRecordMapper;

    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    @Autowired
    private InviteSubordinateMapper inviteSubordinateMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询用户账户余额
     *
     * @param id 用户账户余额主键
     * @return 用户账户余额
     */
    @Override
    public UserAccountBalance selectUserAccountBalanceById(Long id)
    {
        return userAccountBalanceMapper.selectUserAccountBalanceById(id);
    }

    /**
     * 查询用户账户余额列表
     *
     * @param userAccountBalance 用户账户余额
     * @return 用户账户余额
     */
    @Override
    public List<UserAccountBalance> selectUserAccountBalanceList(UserAccountBalance userAccountBalance)
    {
        return userAccountBalanceMapper.selectUserAccountBalanceList(userAccountBalance);
    }

    /**
     * 新增用户账户余额
     *
     * @param userAccountBalance 用户账户余额
     * @return 结果
     */
    @Override
    public int insertUserAccountBalance(UserAccountBalance userAccountBalance)
    {
        userAccountBalance.setCreateTime(DateUtils.getNowDate());
        return userAccountBalanceMapper.insertUserAccountBalance(userAccountBalance);
    }

    /**
     * 修改用户账户余额
     *
     * @param userAccountBalance 用户账户余额
     * @return 结果
     */
    @Override
    public int updateUserAccountBalance(UserAccountBalance userAccountBalance)
    {
        userAccountBalance.setUpdateTime(DateUtils.getNowDate());
        return userAccountBalanceMapper.updateUserAccountBalance(userAccountBalance);
    }

    /**
     * 批量删除用户账户余额
     *
     * @param ids 需要删除的用户账户余额主键
     * @return 结果
     */
    @Override
    public int deleteUserAccountBalanceByIds(Long[] ids)
    {
        return userAccountBalanceMapper.deleteUserAccountBalanceByIds(ids);
    }

    /**
     * 删除用户账户余额信息
     *
     * @param id 用户账户余额主键
     * @return 结果
     */
    @Override
    public int deleteUserAccountBalanceById(Long id)
    {
        return userAccountBalanceMapper.deleteUserAccountBalanceById(id);
    }

    /**
     * APP查询账户余额
     * @return
     */
    @Override
    public AjaxResult queryAccountBalance() {
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if(ObjUtil.isNull(user)){
            return AjaxResult.error("会话失效，请重新登录");
        }
        Map<String,Object> map = new HashMap<>();
        //查询账户余额
        UserAccountBalance acc = new UserAccountBalance();
        acc.setUserId(String.valueOf(user.getUserId()));
        acc.setStatus("0");
        List<UserAccountBalance> accList = userAccountBalanceMapper.selectUserAccountBalanceList(acc);
        if(CollUtil.isEmpty(accList)){
            return AjaxResult.error("账户存在异常记录");
        }
        //获取红包记录
        TurntableRedEnvelopeRecord record = new TurntableRedEnvelopeRecord();
        record.setUserId(String.valueOf(user.getUserId()));
        record.setIsReceive("1");
        List<TurntableRedEnvelopeRecord> recordList = turntableRedEnvelopeRecordMapper.selectTurntableRedEnvelopeRecordList(record);
        if(CollUtil.isNotEmpty(recordList)){
            recordList = recordList.stream().limit(20).collect(Collectors.toList());
            recordList.forEach(s->{
                s.setUserName(s.getUserName().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
            });
        }
        //获取提现晒单
        AssetDetails details = new AssetDetails();
        details.setType(12L);
        details.setAddOrSubtract("0");
        List<AssetDetails> detailsList = assetDetailsMapper.selectAssetDetailsList(details);
        List<WithdrawalDetails> withdrawalDetailsList = new ArrayList<>();
        if(CollUtil.isNotEmpty(detailsList)){
            //获取用户信息
            Set<Long> subIds = detailsList.stream().map(AssetDetails::getUserId).collect(Collectors.toSet());
            List<SysUser> avatarList = sysUserMapper.selectUserListByIds(subIds);
            Map<Long,SysUser> avatarMap =  avatarList.stream().collect(Collectors.toMap(SysUser::getUserId,each->each,(value1, value2) -> value1));
            List<WithdrawalDetails> drawalDetailsList = new ArrayList<>();
            detailsList.forEach(s->{
                WithdrawalDetails detail = new WithdrawalDetails();
                SysUser person = avatarMap.get(s.getUserId());
                detail.setImage(person.getAvatar());
                detail.setUserPhone(s.getUserPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                detail.setAddOrSubtract(s.getAddOrSubtract());
                detail.setRemark("获取"+s.getRecords()+"元支付宝现金打款");
                detail.setCreatedTime(s.getCreatedTime());
                detail.setCreateTime(s.getCreatedTime());
                detail.setRecords(s.getRecords());
                detail.setRedPicture("http://admin.hbqwk.com/images/red.png");
                drawalDetailsList.add(detail);
            });
            withdrawalDetailsList = drawalDetailsList.stream().limit(20).collect(Collectors.toList());
        }
        //获取助力记录
        InviteSubordinate sub = new InviteSubordinate();
        sub.setUserId(user.getUserId());
        List<InviteSubordinate> subList = inviteSubordinateMapper.selectInviteSubordinateList(sub);
        if(CollUtil.isNotEmpty(subList)){
            //获取用户信息
            Set<Long> subIds = subList.stream().map(InviteSubordinate::getInviteeId).collect(Collectors.toSet());
            List<SysUser> avatarList = sysUserMapper.selectUserListByIds(subIds);
            Map<Long,SysUser> avatarMap =  avatarList.stream().collect(Collectors.toMap(SysUser::getUserId,each->each,(value1, value2) -> value1));
            subList.forEach(s->{
                SysUser person = avatarMap.get(s.getInviteeId());
                s.setAvatar(person.getAvatar());
                s.setInviteeName(s.getInviteeName().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                s.setRemark("助力成功，抽奖次数+2");
            });
            subList = subList.stream().limit(20).collect(Collectors.toList());
        }
        map.put("userAccountBalance",accList.get(0).getAccountBalance());
        map.put("userName",user.getUserName());
        map.put("userPhone",user.getPhonenumber());
        map.put("userHead",user.getAvatar());
        map.put("redEnvelopeList",recordList);
        map.put("withdrawalList",withdrawalDetailsList);
        map.put("assistanceList",subList);
        return AjaxResult.success(map);
    }

    /**
     * APP添加支付宝账户
     * @return
     */
    public AjaxResult addAlipayAccount(String alipayAccount,String alipayAccountName){
        if(StrUtil.isEmpty(alipayAccount) || StrUtil.isEmpty(alipayAccountName)){
            return AjaxResult.error("参数必填");
        }
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取用户账户信息
        UserAccountBalance balance = new UserAccountBalance();
        List<UserAccountBalance> list = userAccountBalanceMapper.selectUserAccountBalanceList(balance);
        if(CollUtil.isNotEmpty(list)){
            List<String> accountList = list.stream().map(UserAccountBalance::getAlipayAccount).collect(Collectors.toList());
            List<UserAccountBalance> userAccountList = list.stream().filter(s->String.valueOf(user.getUserId()).equals(s.getUserId())).collect(Collectors.toList());
            if(accountList.contains(alipayAccount)){
                return AjaxResult.error("该支付宝账号已绑定");
            }
            if(CollUtil.isNotEmpty(userAccountList) && StrUtil.isNotEmpty(userAccountList.get(0).getAlipayAccount())){
                return AjaxResult.error("用户支付宝账号已绑定");
            }
            balance = userAccountList.get(0);
            //更新账户信息
            balance.setAlipayAccount(alipayAccount);
            balance.setAlipayAccountName(alipayAccountName);
            int count = userAccountBalanceMapper.updateUserAccountBalance(balance);
            if(count>0){
                return AjaxResult.success("绑定成功！");
            }
        }
        return AjaxResult.error();
    }

    /**
     * 分享助力查询账户余额
     * @return
     */
    public AjaxResult queryShareAccountBalance(String userId){
        Map<String,Object> map = new HashMap<>();
        //获取用户账户信息
        UserAccountBalance balance = new UserAccountBalance();
        balance.setUserId(userId);
        List<UserAccountBalance> list = userAccountBalanceMapper.selectUserAccountBalanceList(balance);
        balance = list.get(0);
        String price = null;
        if(100-balance.getAccountBalance()>0){
            price = String.valueOf(100-balance.getAccountBalance());
        }
        map.put("price",price);
        return AjaxResult.success(map);
    }
}
