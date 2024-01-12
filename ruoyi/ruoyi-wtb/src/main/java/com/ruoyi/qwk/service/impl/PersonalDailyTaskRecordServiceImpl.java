package com.ruoyi.qwk.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.domain.Person;
import com.beust.ah.A;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.enumeration.PersonalTasksGameType;
import com.ruoyi.qwk.mapper.*;
import com.ruoyi.system.mapper.SysUserMapper;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.service.IPersonalDailyTaskRecordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 个人每日任务记录 Service业务层处理
 *
 * @author cjf
 * @date 2023-12-22
 */
@Service
public class PersonalDailyTaskRecordServiceImpl implements IPersonalDailyTaskRecordService
{
    @Autowired
    private PersonalDailyTaskRecordMapper personalDailyTaskRecordMapper;

    @Autowired
    private PersonalTasksMapper personalTasksMapper;

    @Autowired
    private BigEscapeKillUsersRecordMapper bigEscapeKillUsersRecordMapper;

    @Autowired
    private UserAccountBalanceMapper userAccountBalanceMapper;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    @Autowired
    private BlockheadUserRecordMapper blockheadUserRecordMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TurntableRedEnvelopeRecordMapper turntableRedEnvelopeRecordMapper;

    /**
     * 查询个人每日任务记录
     *
     * @param id 个人每日任务记录 主键
     * @return 个人每日任务记录
     */
    @Override
    public PersonalDailyTaskRecord selectPersonalDailyTaskRecordById(Long id)
    {
        return personalDailyTaskRecordMapper.selectPersonalDailyTaskRecordById(id);
    }

    /**
     * 查询个人每日任务记录 列表
     *
     * @param personalDailyTaskRecord 个人每日任务记录
     * @return 个人每日任务记录
     */
    @Override
    public List<PersonalDailyTaskRecord> selectPersonalDailyTaskRecordList(PersonalDailyTaskRecord personalDailyTaskRecord)
    {
        return personalDailyTaskRecordMapper.selectPersonalDailyTaskRecordList(personalDailyTaskRecord);
    }

    /**
     * 新增个人每日任务记录
     *
     * @param personalDailyTaskRecord 个人每日任务记录
     * @return 结果
     */
    @Override
    public int insertPersonalDailyTaskRecord(PersonalDailyTaskRecord personalDailyTaskRecord)
    {
        personalDailyTaskRecord.setCreateTime(DateUtils.getNowDate());
        return personalDailyTaskRecordMapper.insertPersonalDailyTaskRecord(personalDailyTaskRecord);
    }

    /**
     * 修改个人每日任务记录
     *
     * @param personalDailyTaskRecord 个人每日任务记录
     * @return 结果
     */
    @Override
    public int updatePersonalDailyTaskRecord(PersonalDailyTaskRecord personalDailyTaskRecord)
    {
        personalDailyTaskRecord.setUpdateTime(DateUtils.getNowDate());
        return personalDailyTaskRecordMapper.updatePersonalDailyTaskRecord(personalDailyTaskRecord);
    }

    /**
     * 批量删除个人每日任务记录
     *
     * @param ids 需要删除的个人每日任务记录 主键
     * @return 结果
     */
    @Override
    public int deletePersonalDailyTaskRecordByIds(Long[] ids)
    {
        return personalDailyTaskRecordMapper.deletePersonalDailyTaskRecordByIds(ids);
    }

    /**
     * 删除个人每日任务记录 信息
     *
     * @param id 个人每日任务记录 主键
     * @return 结果
     */
    @Override
    public int deletePersonalDailyTaskRecordById(Long id)
    {
        return personalDailyTaskRecordMapper.deletePersonalDailyTaskRecordById(id);
    }

    /**
     * APP领取任务
     * @param taskId
     * @return
     */
    @Override
    @Transactional
    public AjaxResult claimTasks(String taskId,String isDaily) {
        if (StrUtil.isEmpty(taskId) || StrUtil.isEmpty(isDaily)) {
            return AjaxResult.error("参数必填！");
        }
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (ObjUtil.isNull(user)) {
            return AjaxResult.error("会话失效，请重新登录");
        }
        //获取任务信息
        PersonalTasks task = new PersonalTasks();
        task.setId(Long.parseLong(taskId));
        task.setIsRelease("1");
        List<PersonalTasks> taskList = personalTasksMapper.selectPersonalTasksList(task);
        //获取用户任务信息
        PersonalDailyTaskRecord rec = new PersonalDailyTaskRecord();
        rec.setTaskId(taskId);
        rec.setUserId(String.valueOf(user.getUserId()));
        if ("1".equals(isDaily)) {
            rec.setTaskStartTime(DateUtil.date());
        }
        List<PersonalDailyTaskRecord> recordList = personalDailyTaskRecordMapper.selectPersonalDailyTaskRecordList(rec);
        if (ObjUtil.isNull(task) || CollUtil.isEmpty(recordList)) {
            return AjaxResult.error("任务已刷新，请重新登录！");
        }
        task = taskList.get(0);
        PersonalDailyTaskRecord record = recordList.get(0);
        //判断游戏
        if (!"1".equals(record.getStatus())) {
            return AjaxResult.error("任务状态已变更，请刷新！");
        }
        //首页登录任务
       if("0".equals(task.getGameId())){
           //首次登录任务
           if("0".equals(task.getIsDaily()) && "task_login_app".equals(task.getTaskCode())){
               TurntableRedEnvelopeRecord turntableRedEnvelopeRecordt = new TurntableRedEnvelopeRecord();
               turntableRedEnvelopeRecordt.setSourceStr("0");
               turntableRedEnvelopeRecordt.setUserId(String.valueOf(user.getUserId()));
               List<TurntableRedEnvelopeRecord> redList= turntableRedEnvelopeRecordMapper.selectTurntableRedEnvelopeRecordList(turntableRedEnvelopeRecordt);
               if(CollUtil.isNotEmpty(redList)){
                   redList.forEach(s->{
                       s.setIsReceive("1");
                       turntableRedEnvelopeRecordMapper.updateTurntableRedEnvelopeRecord(s);
                   });
               }
           }
        //大逃杀
       }else if("1".equals(task.getGameId())){
            BigEscapeKillUsersRecord bigEscapeKillUsersRecord = new BigEscapeKillUsersRecord();
            bigEscapeKillUsersRecord.setUserId(user.getUserId());
            bigEscapeKillUsersRecord.setCreated(DateUtil.date());
            List<BigEscapeKillUsersRecord> bigList = bigEscapeKillUsersRecordMapper.selectBigEscapeKillUsersRecordList(bigEscapeKillUsersRecord);
            if(CollUtil.isEmpty(bigList)){
                return AjaxResult.error("今日未进行游戏，请先完成游戏");
            }
            List<BigDecimal> bigDecimalList = bigList.stream().filter(s->s.getGetCoin().compareTo(BigDecimal.ZERO) > 0).map(BigEscapeKillUsersRecord::getGetCoin).collect(Collectors.toList());
            String msg = checkGameTask(task,bigDecimalList,bigDecimalList.size());
            if(StrUtil.isNotEmpty(msg)){
                return AjaxResult.error(msg);
            }
        //木头人
        }else if("5".equals(task.getGameId())){
            BlockheadUserRecord blockheadUserRecord = new BlockheadUserRecord();
            blockheadUserRecord.setUserId(user.getUserId());
            blockheadUserRecord.setCreated(DateUtil.date());
            List<BlockheadUserRecord> blockRecordList = blockheadUserRecordMapper.selectBlockheadUserRecordList(blockheadUserRecord);
            if(CollUtil.isEmpty(blockRecordList)){
                return AjaxResult.error("今日未进行游戏，请先完成游戏");
            }
            List<Long> blockheadList = blockRecordList.stream().filter(s->s.getGetCoin() > 0).map(BlockheadUserRecord::getGetCoin).collect(Collectors.toList());
            List<BigDecimal> bigDecimalList = blockheadList.stream().map(BigDecimal::new).collect(Collectors.toList());
            String msg = checkGameTask(task,bigDecimalList,bigDecimalList.size());
            if(StrUtil.isNotEmpty(msg)){
                return AjaxResult.error(msg);
            }
        }
        //更新每日任务
        record.setTaskEndTime(DateUtil.date());
        record.setStatus("2");
        record.setCompletedQuantity(String.valueOf(task.getTaskCount()));
        personalDailyTaskRecordMapper.updatePersonalDailyTaskRecord(record);
        //更新生肖宝钱包
        UserAssets userAssets = userAssetsMapper.selectByUserId(user.getUserId(),4L);
        if(ObjUtil.isNull(userAssets)){
           return AjaxResult.error("生肖宝钱包存在异常记录，请重新登录！");
        }
        DecimalFormat df = new DecimalFormat("0.00");
        Double prices = Double.parseDouble(userAssets.getTotal())+task.getReward();
        String result = df.format(prices);
        userAssets.setTotal(result);
        userAssetsMapper.updateUserAssets(userAssets);
        Long detailsType = 13L;
        if("1".equals(task.getIsDaily())){
            detailsType = 14L;
        }
        //更新资产明细记录
        setAssetDetails(user,userAssets.getTotal(),String.valueOf(task.getReward()),detailsType);
        return AjaxResult.success("领取成功");
    }

    /**
     * 校验游戏任务是否达标
     * @param task
     * @param bigDecimalList
     * @param count
     * @return
     */
    public String checkGameTask(PersonalTasks task,List<BigDecimal> bigDecimalList,int count){
        String msg = "";
        //糖果任务
        if("1".equals(task.getTaskCompleteType())){
            //今日获取糖果数量
            BigDecimal sum = bigDecimalList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal taskCountBig = new BigDecimal(task.getTaskCount());
            if(sum.compareTo(BigDecimal.ZERO)<0 || sum.compareTo(taskCountBig)<0){
                msg =  "任务未完成，不可领取！";
            }
            //局数任务
        }else if("2".equals(task.getTaskCompleteType())){
            if(task.getTaskCount()>count){
                msg =  "任务未完成，不可领取！";
            }
        }
        return msg;
    }

    /**
     * 新增资产明细记录
     * @param user
     * @param money
     * @param number
     */
    public void setAssetDetails(SysUser user,String money,String number,Long type){
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setImage("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/images/1694419558989.jpg");
        assetDetails.setUserPhone(user.getPhonenumber());
        assetDetails.setUserId(user.getUserId());
        assetDetails.setBalance(money);
        String notes = "成长任务";
        if(type==14L){
            notes = "每日任务";
        }
        assetDetails.setNotes(notes);
        assetDetails.setCreatedBy(user.getUserName());
        assetDetails.setUpdatedBy(user.getUserName());
        assetDetails.setType(type);
        assetDetails.setAddOrSubtract("1");
        assetDetails.setRecords(number);
        assetDetailsMapper.insertAssetDetails(assetDetails);
    }

    /**
     * 用户首次进入游戏
     * @param gameType
     * @return
     */
    public AjaxResult firstGame(String gameType,String userId){
        if(StrUtil.isEmpty(gameType) || StrUtil.isEmpty(userId)){
            return AjaxResult.error("参数必填");
        }
        //获取用户信息
        SysUser user = sysUserMapper.selectUserById(Long.parseLong(userId));
        if(ObjUtil.isNull(user)){
            return AjaxResult.error("会话失效，请重新登录");
        }
        //获取任务信息
        PersonalTasksGameType taksType = PersonalTasksGameType.getName(Integer.parseInt(gameType));
        if(ObjUtil.isNull(taksType)){
            return AjaxResult.error("传参错误！");
        }
        //获取任务信息
        PersonalTasks task = new PersonalTasks();
        task.setIsRelease("1");
        task.setTaskCode(taksType.getTaskCode());
        List<PersonalTasks> taskList = personalTasksMapper.selectPersonalTasksList(task);
        if(CollUtil.isNotEmpty(taskList)){
            task = taskList.get(0);
            //获取用户任务信息
            PersonalDailyTaskRecord rec = new PersonalDailyTaskRecord();
            rec.setTaskId(String.valueOf(task.getId()));
            rec.setUserId(String.valueOf(user.getUserId()));
            List<PersonalDailyTaskRecord> recordList =  personalDailyTaskRecordMapper.selectPersonalDailyTaskRecordList(rec);
            if(CollUtil.isNotEmpty(recordList)){
                rec = recordList.get(0);
                if("0".equals(rec.getStatus())){
                    //更新状态领取
                    rec.setStatus("1");
                    rec.setCompletedQuantity("1");
                    rec.setTaskEndTime(DateUtil.date());
                    personalDailyTaskRecordMapper.updatePersonalDailyTaskRecord(rec);
                }
            }
        }

        return AjaxResult.success();
    }

    /**
     * 定时生成用户每日任务
     */
    public void UserDailyTasks(){
        //获取所有的用户信息
        SysUser sysUser = new SysUser();
        List<SysUser> userList = sysUserMapper.selectUserList(sysUser);
        //获取任务列表
        PersonalTasks tasks = new PersonalTasks();
        tasks.setIsRelease("1");
        List<PersonalTasks> tasksList = personalTasksMapper.selectPersonalTasksList(tasks);
        if(CollUtil.isNotEmpty(userList) && CollUtil.isNotEmpty(tasksList)){
            //获取用户任务是否生成
            List<PersonalDailyTaskRecord> recordList = personalDailyTaskRecordMapper.selectTaskRecordList(DateUtil.date());
            //全部生成
            if(CollUtil.isEmpty(recordList)){
                userList.forEach(u->{
                    //生成个人任务
                    addUserTask(u,tasksList);
                });
            }else if(CollUtil.isNotEmpty(recordList)){
                //list转map
                Map<String, List<PersonalDailyTaskRecord>> recMap = recordList.stream().collect(Collectors.groupingBy(PersonalDailyTaskRecord::getUserId));
                userList.forEach(s->{
                    List<PersonalDailyTaskRecord> dailyList = recMap.get(String.valueOf(s.getUserId()));
                    if(CollUtil.isEmpty(dailyList)){
                        //生成个人任务
                        addUserTask(s,tasksList);
                    }else{
                        tasksList.forEach(t->{
                            List<PersonalDailyTaskRecord> pdList =  dailyList.stream().filter(d->d.getTaskId().equals(String.valueOf(t.getId()))).collect(Collectors.toList());
                            if(CollUtil.isEmpty(pdList)){
                                PersonalDailyTaskRecord daily = new PersonalDailyTaskRecord();
                                daily.setUserId(String.valueOf(s.getUserId()));
                                daily.setTaskStartTime(DateUtil.date());
                                daily.setTaskId(String.valueOf(t.getId()));
                                daily.setStatus("0");
                                daily.setCreateBy(s.getPhonenumber());
                                daily.setUpdateBy(s.getPhonenumber());
                                personalDailyTaskRecordMapper.insertPersonalDailyTaskRecord(daily);
                            }
                        });
                    }
                });
            }
        }
    }


    /**
     * 添加个人任务
     * @param user
     * @param tasksList
     */
    public void addUserTask(SysUser user,List<PersonalTasks> tasksList){
        tasksList.forEach(s-> {
            PersonalDailyTaskRecord daily = new PersonalDailyTaskRecord();
            daily.setUserId(String.valueOf(user.getUserId()));
            daily.setTaskStartTime(DateUtil.date());
            daily.setTaskId(String.valueOf(s.getId()));
            daily.setStatus("0");
            daily.setCreateBy(user.getPhonenumber());
            daily.setUpdateBy(user.getPhonenumber());
            personalDailyTaskRecordMapper.insertPersonalDailyTaskRecord(daily);
        });
    }
}
