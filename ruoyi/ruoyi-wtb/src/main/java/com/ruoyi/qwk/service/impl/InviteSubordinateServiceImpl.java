package com.ruoyi.qwk.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.mapper.AssistanceDrawNumberMapper;
import com.ruoyi.qwk.mapper.InviteFriendsMapper;
import com.ruoyi.qwk.mapper.LoginRecordMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.InviteSubordinateMapper;
import com.ruoyi.qwk.service.IInviteSubordinateService;
import org.springframework.util.ObjectUtils;

/**
 * 邀请用户关系Service业务层处理
 *
 * @author cjf
 * @date 2023-09-27
 */
@Service
public class InviteSubordinateServiceImpl implements IInviteSubordinateService
{
    @Autowired
    private InviteSubordinateMapper inviteSubordinateMapper;

    @Autowired
    private InviteFriendsMapper inviteFriendsMapper;

    @Autowired
    private LoginRecordMapper loginRecordMapper;

    @Autowired
    private AssistanceDrawNumberMapper assistanceDrawNumberMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询邀请用户关系
     *
     * @param id 邀请用户关系主键
     * @return 邀请用户关系
     */
    @Override
    public InviteSubordinate selectInviteSubordinateById(Long id)
    {
        return inviteSubordinateMapper.selectInviteSubordinateById(id);
    }

    /**
     * 查询邀请用户关系列表
     *
     * @param inviteSubordinate 邀请用户关系
     * @return 邀请用户关系
     */
    @Override
    public List<InviteSubordinate> selectInviteSubordinateList(InviteSubordinate inviteSubordinate)
    {
        return inviteSubordinateMapper.selectInviteSubordinateList(inviteSubordinate);
    }

    /**
     * 新增邀请用户关系
     *
     * @param inviteSubordinate 邀请用户关系
     * @return 结果
     */
    @Override
    public int insertInviteSubordinate(InviteSubordinate inviteSubordinate)
    {
        inviteSubordinate.setCreateTime(DateUtils.getNowDate());
        return inviteSubordinateMapper.insertInviteSubordinate(inviteSubordinate);
    }

    /**
     * 修改邀请用户关系
     *
     * @param inviteSubordinate 邀请用户关系
     * @return 结果
     */
    @Override
    public int updateInviteSubordinate(InviteSubordinate inviteSubordinate)
    {
        inviteSubordinate.setUpdateTime(DateUtils.getNowDate());
        return inviteSubordinateMapper.updateInviteSubordinate(inviteSubordinate);
    }

    /**
     * 批量删除邀请用户关系
     *
     * @param ids 需要删除的邀请用户关系主键
     * @return 结果
     */
    @Override
    public int deleteInviteSubordinateByIds(Long[] ids)
    {
        return inviteSubordinateMapper.deleteInviteSubordinateByIds(ids);
    }

    /**
     * 删除邀请用户关系信息
     *
     * @param id 邀请用户关系主键
     * @return 结果
     */
    @Override
    public int deleteInviteSubordinateById(Long id)
    {
        return inviteSubordinateMapper.deleteInviteSubordinateById(id);
    }

    /**
     *
     * 添加用户上下级关系
     * */
    @Override
    public int addInviteSubordinate(String ip, SysUser user){
        if (StringUtils.isEmpty(ip))return 0;
        String sub = StrUtil.sub(ip, 0, ip.lastIndexOf("."));
        LoginRecord lg = new LoginRecord();
        lg.setIp(ip);
        lg.setSubIp(sub);
        loginRecordMapper.insertLoginRecord(lg);
        List<InviteFriends> inviteFriendsList = inviteFriendsMapper.selectInviteFriendsByIp(sub);
        if (CollUtil.isEmpty(inviteFriendsList))return 0;
        InviteSubordinate subordinate = new InviteSubordinate();
        subordinate.setInviteeId(user.getUserId());
        subordinate.setUserId(inviteFriendsList.get(inviteFriendsList.size()-1).getInviterId());
        subordinate.setInviteeName(user.getUserName());
        int count = inviteSubordinateMapper.insertInviteSubordinate(subordinate);
        //邀请人添加2次抽奖机会
        AssistanceDrawNumber number = new AssistanceDrawNumber();
        number.setAssistanceId(String.valueOf(inviteFriendsList.get(inviteFriendsList.size()-1).getInviterId()));
        List<AssistanceDrawNumber> numberList = assistanceDrawNumberMapper.selectAssistanceDrawNumberList(number);
        if(CollUtil.isNotEmpty(numberList)){
            number = numberList.get(0);
            number.setLotteryDrawSum(number.getLotteryDrawSum()+2);
            number.setNoLotteryDrawNum(number.getNoLotteryDrawNum()+2);
            number.setVacuumingNum(number.getVacuumingNum()+2);
            number.setActualExtractionNum(number.getActualExtractionNum()+2);
            assistanceDrawNumberMapper.updateAssistanceDrawNumber(number);
        }
        //删除邀请好友记录
        inviteFriendsList.forEach(s->{
            inviteFriendsMapper.deleteInviteFriendsById(s.getId());
        });
        return count;
    }

    /**
     * 今日邀请人数
     * */
    @Override
    public Integer todayPeople(Long userId) {
        return inviteSubordinateMapper.todayPeople(userId);
    }

    /**
     * 邀请总人数
     * */
    @Override
    public Integer totalPeople(Long userId) {
        return inviteSubordinateMapper.totalPeople(userId);
    }

    /**
     * App获取邀请人助力记录
     * @return
     */
    @Override
    public AjaxResult invitationAssistanceRecord(){
        List<InvitationAssistanceRecord> initList = initInviteSubordinate();
        List<InvitationAssistanceRecord> invList = new ArrayList<>();
        InviteSubordinate sub = new InviteSubordinate();
        List<InviteSubordinate> recordList = inviteSubordinateMapper.selectInviteSubordinateList(sub);
        if(CollUtil.isNotEmpty(recordList)){
            List<InvitationAssistanceRecord> assList = new ArrayList<>();
            //获取用户信息
            Set<Long> subIds = recordList.stream().map(InviteSubordinate::getUserId).collect(Collectors.toSet());
            List<SysUser> avatarList = sysUserMapper.selectUserListByIds(subIds);
            Map<Long,SysUser> avatarMap =  avatarList.stream().collect(Collectors.toMap(SysUser::getUserId, each->each,(value1, value2) -> value1));
            recordList.forEach(s->{
                SysUser user = avatarMap.get(s.getUserId());
                InvitationAssistanceRecord rec = new InvitationAssistanceRecord();
                rec.setInvitationPhone(user.getPhonenumber().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                rec.setAvatar(user.getAvatar());
                rec.setRemark("好友助力成功！");
                rec.setZfbPicture("http://admin.hbqwk.com/images/zfb.jpg");
                assList.add(rec);
            });
            invList = Stream.of(assList, initList).flatMap(Collection::stream).collect(Collectors.toList());
            return AjaxResult.success(invList);
        }

        return AjaxResult.success(initList);
    }

    /**
     * 初始化邀请成功记录
     * @return
     */
    public List<InvitationAssistanceRecord> initInviteSubordinate(){
        List<InvitationAssistanceRecord> list = new ArrayList<>();
        InvitationAssistanceRecord in1 = new InvitationAssistanceRecord("18185698511".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in2 = new InvitationAssistanceRecord("15185698822".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in3 = new InvitationAssistanceRecord("18185698788".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in4 = new InvitationAssistanceRecord("18185698457".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in5 = new InvitationAssistanceRecord("18185698544".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in6 = new InvitationAssistanceRecord("18185698756".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in7 = new InvitationAssistanceRecord("18185698588".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in8 = new InvitationAssistanceRecord("18185698895".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in9 = new InvitationAssistanceRecord("18185698478".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in10 = new InvitationAssistanceRecord("18185698325".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in11 = new InvitationAssistanceRecord("18185698987".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in12 = new InvitationAssistanceRecord("18185698254".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in13 = new InvitationAssistanceRecord("18185698215".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in14 = new InvitationAssistanceRecord("13885698985".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in15 = new InvitationAssistanceRecord("18185698894".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        InvitationAssistanceRecord in16 = new InvitationAssistanceRecord("15085698874".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),"https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/20230823165714.png","好友助力成功！","http://admin.hbqwk.com/images/zfb.jpg");
        list.add(in1);
        list.add(in2);
        list.add(in3);
        list.add(in4);
        list.add(in5);
        list.add(in6);
        list.add(in7);
        list.add(in8);
        list.add(in9);
        list.add(in10);
        list.add(in11);
        list.add(in12);
        list.add(in13);
        list.add(in14);
        list.add(in15);
        list.add(in16);
        return list;
    }
}
