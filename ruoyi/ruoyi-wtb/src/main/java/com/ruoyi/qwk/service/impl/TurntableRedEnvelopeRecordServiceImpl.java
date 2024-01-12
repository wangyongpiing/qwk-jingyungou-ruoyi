package com.ruoyi.qwk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.mapper.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.service.ITurntableRedEnvelopeRecordService;

/**
 * 转盘领取红包记录Service业务层处理
 *
 * @author cjf
 * @date 2023-12-20
 */
@Service
public class TurntableRedEnvelopeRecordServiceImpl implements ITurntableRedEnvelopeRecordService
{
    @Autowired
    private TurntableRedEnvelopeRecordMapper turntableRedEnvelopeRecordMapper;

    @Autowired
    private TurntableRedEnvelopeMapper turntableRedEnvelopeMapper;

    @Autowired
    private UserAccountBalanceMapper userAccountBalanceMapper;

    @Autowired
    private AssistanceDrawNumberMapper assistanceDrawNumberMapper;

    @Autowired
    private PersonalTasksMapper personalTasksMapper;

    @Autowired
    private PersonalDailyTaskRecordMapper  personalDailyTaskRecordMapper;

    @Autowired
    private UserAssetsMapper userAssetsMapper;


    /**
     * 查询转盘领取红包记录
     *
     * @param id 转盘领取红包记录主键
     * @return 转盘领取红包记录
     */
    @Override
    public TurntableRedEnvelopeRecord selectTurntableRedEnvelopeRecordById(Long id)
    {
        return turntableRedEnvelopeRecordMapper.selectTurntableRedEnvelopeRecordById(id);
    }

    /**
     * 查询转盘领取红包记录列表
     *
     * @param turntableRedEnvelopeRecord 转盘领取红包记录
     * @return 转盘领取红包记录
     */
    @Override
    public List<TurntableRedEnvelopeRecord> selectTurntableRedEnvelopeRecordList(TurntableRedEnvelopeRecord turntableRedEnvelopeRecord)
    {
        return turntableRedEnvelopeRecordMapper.selectTurntableRedEnvelopeRecordList(turntableRedEnvelopeRecord);
    }

    /**
     * 新增转盘领取红包记录
     *
     * @param turntableRedEnvelopeRecord 转盘领取红包记录
     * @return 结果
     */
    @Override
    public int insertTurntableRedEnvelopeRecord(TurntableRedEnvelopeRecord turntableRedEnvelopeRecord)
    {
        turntableRedEnvelopeRecord.setCreateTime(DateUtils.getNowDate());
        return turntableRedEnvelopeRecordMapper.insertTurntableRedEnvelopeRecord(turntableRedEnvelopeRecord);
    }

    /**
     * 修改转盘领取红包记录
     *
     * @param turntableRedEnvelopeRecord 转盘领取红包记录
     * @return 结果
     */
    @Override
    public int updateTurntableRedEnvelopeRecord(TurntableRedEnvelopeRecord turntableRedEnvelopeRecord)
    {
        turntableRedEnvelopeRecord.setUpdateTime(DateUtils.getNowDate());
        return turntableRedEnvelopeRecordMapper.updateTurntableRedEnvelopeRecord(turntableRedEnvelopeRecord);
    }

    /**
     * 批量删除转盘领取红包记录
     *
     * @param ids 需要删除的转盘领取红包记录主键
     * @return 结果
     */
    @Override
    public int deleteTurntableRedEnvelopeRecordByIds(Long[] ids)
    {
        return turntableRedEnvelopeRecordMapper.deleteTurntableRedEnvelopeRecordByIds(ids);
    }

    /**
     * 删除转盘领取红包记录信息
     *
     * @param id 转盘领取红包记录主键
     * @return 结果
     */
    @Override
    public int deleteTurntableRedEnvelopeRecordById(Long id)
    {
        return turntableRedEnvelopeRecordMapper.deleteTurntableRedEnvelopeRecordById(id);
    }

    /**
     * 是否领取下载，注册红包
     * @return
     */
    public AjaxResult checkReceiveRedEnvelope(){
        Map<String,Object> map = new HashMap<>();
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //初始化个人每日任务
        boolean flag = initPersonTask(user);
        //是否存在账户余额记录
        UserAccountBalance account = new UserAccountBalance();
        account.setUserId(String.valueOf(user.getUserId()));
        List<UserAccountBalance> accountList = userAccountBalanceMapper.selectUserAccountBalanceList(account);
        if(CollUtil.isEmpty(accountList)){
            //新增账户余额记录
            UserAccountBalance balance = new UserAccountBalance();
            balance.setUserId(String.valueOf(user.getUserId()));
            balance.setAccountBalance(0.00);
            userAccountBalanceMapper.insertUserAccountBalance(balance);
        }
        //获取用户是否未领取下载，注册红包
        TurntableRedEnvelopeRecord lope = new TurntableRedEnvelopeRecord();
        lope.setUserId(String.valueOf(user.getUserId()));
        lope.setSourceStr("1");
        List<TurntableRedEnvelopeRecord> lopeList = turntableRedEnvelopeRecordMapper.selectTurntableRedEnvelopeRecordList(lope);
        //不显示红包
         String isReceive ="1";
        //第一次领取
        if(CollUtil.isEmpty(lopeList)){
            //新增下载，注册红包记录
            addRedRecord(user,50.00,"1","领取下载安装红包50元");
            addRedRecord(user,40.00,"2","领取注册红包40元");
            isReceive ="0";
        }else{
            List<TurntableRedEnvelopeRecord> noLopeList = lopeList.stream().filter(s->"0".equals(s.getIsReceive())).collect(Collectors.toList());
            if(CollUtil.isNotEmpty(noLopeList)){
                isReceive ="0";
            }
        }
        //添加抽奖次数
        AssistanceDrawNumber ass = new AssistanceDrawNumber();
        ass.setAssistanceId(String.valueOf(user.getUserId()));
        List<AssistanceDrawNumber> assList = assistanceDrawNumberMapper.selectAssistanceDrawNumberList(ass);
        if(CollUtil.isEmpty(assList)){
            //新增助力抽奖次数
            ass.setLotteryDrawSum(0L);
            ass.setNoLotteryDrawNum(0L);
            ass.setIsFree("0");
            ass.setIsLotteryAllowed("0");
            ass.setCreateBy(user.getUserName());
            ass.setUpdateBy(user.getUserName());
            assistanceDrawNumberMapper.insertAssistanceDrawNumber(ass);
        }
        //获取钱包余额
        UserAssets assets = userAssetsMapper.selectByUserId(user.getUserId(),4L);
        if(ObjUtil.isNull(assets)){
            assets = new UserAssets();
            assets.setTotal("0");
            assets.setUserId(user.getUserId());
            assets.setUserPhone(user.getPhonenumber());
            assets.setType(4L);
            assets.setNickName(user.getNickName());
            userAssetsMapper.insertUserAssets(assets);
        }
        map.put("isReceive",isReceive);
        return AjaxResult.success(map);
    }

    /**
     * 初始化个人每日任务
     */
    public boolean initPersonTask(SysUser user){
        boolean flag = true;
        //获取用户任务是否生成
        PersonalDailyTaskRecord person = new PersonalDailyTaskRecord();
        person.setUserId(String.valueOf(user.getUserId()));
        List<PersonalDailyTaskRecord> userTaskList = personalDailyTaskRecordMapper.selectPersonalDailyTaskRecordList(person);
        //第一次登录
        if(CollUtil.isEmpty(userTaskList)){
            addUserTask(user,"0");
            return flag;
        }
        //获取用户当天任务是否生成
        person.setTaskStartTime(DateUtil.date());
        userTaskList = personalDailyTaskRecordMapper.selectPersonalDailyTaskRecordList(person);
        if(CollUtil.isEmpty(userTaskList)){
            addUserTask(user,"1");
        }
        return flag;
    }

    /**
     * 添加个人每日任务
     * @param user
     * @param isDaily
     */
    public void addUserTask(SysUser user,String isDaily){
        //获取任务列表
        PersonalTasks task = new PersonalTasks();
        task.setIsRelease("1");
        if("1".equals(isDaily)){
            task.setIsDaily(isDaily);
        }
        List<PersonalTasks> tasksList = personalTasksMapper.selectPersonalTasksList(task);
        if(CollUtil.isNotEmpty(tasksList)){
            tasksList.forEach(s->{
                PersonalDailyTaskRecord daily = new PersonalDailyTaskRecord();
                daily.setUserId(String.valueOf(user.getUserId()));
                daily.setTaskStartTime(DateUtil.date());
                daily.setTaskId(String.valueOf(s.getId()));
                daily.setStatus("0");
                //判断是否是首次登录任务
                if("task_login_app".equals(s.getTaskCode())){
                    daily.setStatus("1");
                    daily.setTaskEndTime(DateUtil.date());
                    daily.setCompletedQuantity(String.valueOf(s.getTaskCount()));
                }
                daily.setCreateBy(user.getPhonenumber());
                daily.setUpdateBy(user.getPhonenumber());
                personalDailyTaskRecordMapper.insertPersonalDailyTaskRecord(daily);
            });
        }
    }

    /**
     * APP领取下载，注册红包
     * @return
     */
    @Override
    public AjaxResult receiveRedEnvelope() {
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取用户是否未领取下载，注册红包
        TurntableRedEnvelopeRecord lope = new TurntableRedEnvelopeRecord();
        lope.setUserId(String.valueOf(user.getUserId()));
        lope.setSourceStr("1");
        lope.setIsReceive("0");
        List<TurntableRedEnvelopeRecord> lopeList = turntableRedEnvelopeRecordMapper.selectTurntableRedEnvelopeRecordList(lope);
        if(CollUtil.isEmpty(lopeList)){
            return AjaxResult.error("红包已领取，请重新登录");
        }
        lopeList.forEach(s->{
            s.setIsReceive("1");
            turntableRedEnvelopeRecordMapper.updateTurntableRedEnvelopeRecord(s);
        });
        //更新账号余额
        UserAccountBalance account = new UserAccountBalance();
        account.setUserId(String.valueOf(user.getUserId()));
        account.setStatus("0");
        List<UserAccountBalance> accountList = userAccountBalanceMapper.selectUserAccountBalanceList(account);
        if(CollUtil.isEmpty(accountList)){
            return AjaxResult.error("账户余额存在异常记录！");
        }
        UserAccountBalance userAccount = accountList.get(0);
        userAccount.setAccountBalance(userAccount.getAccountBalance()+90.00);
        userAccountBalanceMapper.updateUserAccountBalance(userAccount);
        //添加抽奖次数
        AssistanceDrawNumber ass = new AssistanceDrawNumber();
        ass.setAssistanceId(String.valueOf(user.getUserId()));
        List<AssistanceDrawNumber> assList = assistanceDrawNumberMapper.selectAssistanceDrawNumberList(ass);
        if(CollUtil.isEmpty(assList)){
            //新增助力抽奖次数
            ass.setLotteryDrawSum(1L);
            ass.setNoLotteryDrawNum(1L);
            ass.setIsFree("1");
            ass.setCreateBy(user.getUserName());
            ass.setUpdateBy(user.getUserName());
            assistanceDrawNumberMapper.insertAssistanceDrawNumber(ass);
            return AjaxResult.success("领取成功，并获得一次抽奖机会");
        }
        ass = assList.get(0);
        ass.setLotteryDrawSum(ass.getLotteryDrawSum()+1L);
        ass.setNoLotteryDrawNum(ass.getNoLotteryDrawNum()+1L);
        ass.setIsFree("1");
        assistanceDrawNumberMapper.updateAssistanceDrawNumber(ass);
        return AjaxResult.success("领取成功，并获得一次抽奖机会");
    }

    /**
     * 插入红包记录
     * @param user
     * @param money
     * @param source
     * @param remark
     */
    public void addRedRecord(SysUser user,Double money,String source,String remark){
        TurntableRedEnvelopeRecord record = new TurntableRedEnvelopeRecord();
        record.setUserId(String.valueOf(user.getUserId()));
        record.setUserHead(user.getAvatar());
        record.setUserName(user.getPhonenumber());
        record.setIsReceive("0");
        record.setMoney(money);
        record.setSource(source);
        record.setRemark(remark);
        turntableRedEnvelopeRecordMapper.insertTurntableRedEnvelopeRecord(record);
    }
}
