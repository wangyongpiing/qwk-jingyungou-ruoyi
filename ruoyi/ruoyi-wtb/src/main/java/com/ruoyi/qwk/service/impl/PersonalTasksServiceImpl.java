package com.ruoyi.qwk.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.domain.Person;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.qwk.domain.BigEscapeKillUsersRecord;
import com.ruoyi.qwk.domain.BlockheadUserRecord;
import com.ruoyi.qwk.domain.PersonalDailyTaskRecord;
import com.ruoyi.qwk.mapper.BigEscapeKillUsersRecordMapper;
import com.ruoyi.qwk.mapper.BlockheadUserRecordMapper;
import com.ruoyi.qwk.mapper.PersonalDailyTaskRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.PersonalTasksMapper;
import com.ruoyi.qwk.domain.PersonalTasks;
import com.ruoyi.qwk.service.IPersonalTasksService;

/**
 * 个人任务 Service业务层处理
 *
 * @author cjf
 * @date 2023-12-22
 */
@Service
public class PersonalTasksServiceImpl implements IPersonalTasksService
{
    @Autowired
    private PersonalTasksMapper personalTasksMapper;

    @Autowired
    private PersonalDailyTaskRecordMapper personalDailyTaskRecordMapper;

    @Autowired
    private BigEscapeKillUsersRecordMapper bigEscapeKillUsersRecordMapper;

    @Autowired
    private BlockheadUserRecordMapper blockheadUserRecordMapper;

    /**
     * 查询个人任务
     *
     * @param id 个人任务 主键
     * @return 个人任务
     */
    @Override
    public PersonalTasks selectPersonalTasksById(Long id)
    {
        return personalTasksMapper.selectPersonalTasksById(id);
    }

    /**
     * 查询个人任务 列表
     *
     * @param personalTasks 个人任务
     * @return 个人任务
     */
    @Override
    public AjaxResult selectPersonalTasksList(PersonalTasks personalTasks)
    {
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if(ObjUtil.isNull(user)){
            return AjaxResult.error("会话失效，请重新登录");
        }
        updateUserTask(user);
        List<PersonalTasks> list = new ArrayList<>();
        personalTasks.setStartTime(DateUtil.date());
        personalTasks.setIsRelease("1");
        personalTasks.setUserId(user.getUserId());
        //获取全部
        if("0".equals(personalTasks.getTypeCode()) || "1".equals(personalTasks.getTypeCode()) ){
            //获取每日任务
            personalTasks.setIsDaily("1");
            if("0".equals(personalTasks.getTypeCode())){
                personalTasks.setTypeCode("");
            }
            List<PersonalTasks> dailyList = personalTasksMapper.selectUserTasksList(personalTasks);
            //获取首页任务
            personalTasks.setIsDaily("0");
            personalTasks.setStartTime(null);
            List<PersonalTasks> homePageList = personalTasksMapper.selectUserTasksList(personalTasks);
            if(CollUtil.isEmpty(dailyList) || CollUtil.isEmpty(homePageList)){
                return AjaxResult.error("任务已刷新，请重新登录！");
            }
            list = Stream.concat(homePageList.stream(), dailyList.stream())
                    .collect(Collectors.toList());
            return AjaxResult.success(list);
        }
        list = personalTasksMapper.selectUserTasksList(personalTasks);
        return AjaxResult.success(list);
    }

    /**
     * 更新用户是否完成每日任务
     * @param user
     */
    public void updateUserTask(SysUser user){
        //获取任务信息
        PersonalTasks task = new PersonalTasks();
        task.setIsRelease("1");
        task.setIsDaily("1");
        List<PersonalTasks> taskList = personalTasksMapper.selectPersonalTasksList(task);
        //获取用户任务信息
        PersonalDailyTaskRecord rec = new PersonalDailyTaskRecord();
        rec.setUserId(String.valueOf(user.getUserId()));
        rec.setTaskStartTime(DateUtil.date());
        rec.setStatus("0");
        List<PersonalDailyTaskRecord> recordList =  personalDailyTaskRecordMapper.selectPersonalDailyTaskRecordList(rec);
        if(CollUtil.isNotEmpty(taskList) && CollUtil.isNotEmpty(recordList)){
            Map<String, PersonalDailyTaskRecord> map =  recordList.stream().collect(Collectors.toMap(PersonalDailyTaskRecord::getTaskId, each->each,(value1, value2) -> value1));
            taskList.forEach(s->{
                PersonalDailyTaskRecord record = map.get(String.valueOf(s.getId()));
                if(ObjUtil.isNotNull(record)) {
                if("0".equals(s.getGameId())){
                    if("task_login_daily".equals(s.getTaskCode())){
                        record.setTaskEndTime(DateUtil.date());
                        record.setStatus("1");
                        record.setCompletedQuantity(String.valueOf(task.getTaskCount()));
                    }
                }else if("1".equals(s.getGameId())){
                        BigEscapeKillUsersRecord bigEscapeKillUsersRecord = new BigEscapeKillUsersRecord();
                        bigEscapeKillUsersRecord.setUserId(user.getUserId());
                        bigEscapeKillUsersRecord.setCreated(DateUtil.date());
                        List<BigEscapeKillUsersRecord> bigList = bigEscapeKillUsersRecordMapper.selectBigEscapeKillUsersRecordList(bigEscapeKillUsersRecord);
                        if(CollUtil.isNotEmpty(bigList)){
                            List<BigDecimal> bigDecimalList = bigList.stream().filter(t->t.getGetCoin().compareTo(BigDecimal.ZERO) > 0).map(BigEscapeKillUsersRecord::getGetCoin).collect(Collectors.toList());
                            boolean flag = checkGameTask(s,bigDecimalList,bigDecimalList.size());
                            if(flag==true){
                                record.setTaskEndTime(DateUtil.date());
                                record.setStatus("1");
                                record.setCompletedQuantity(String.valueOf(task.getTaskCount()));
                            }
                        }

                        //木头人
                    }else if("5".equals(s.getGameId())){
                        BlockheadUserRecord blockheadUserRecord = new BlockheadUserRecord();
                        blockheadUserRecord.setUserId(user.getUserId());
                        blockheadUserRecord.setCreated(DateUtil.date());
                        List<BlockheadUserRecord> blockRecordList = blockheadUserRecordMapper.selectBlockheadUserRecordList(blockheadUserRecord);
                        if(CollUtil.isNotEmpty(blockRecordList)){
                            List<Long> blockheadList = blockRecordList.stream().filter(t->t.getGetCoin() > 0).map(BlockheadUserRecord::getGetCoin).collect(Collectors.toList());
                            List<BigDecimal> bigDecimalList = blockheadList.stream().map(BigDecimal::new).collect(Collectors.toList());
                            boolean flag =  checkGameTask(s,bigDecimalList,bigDecimalList.size());
                            if(flag==true){
                                record.setTaskEndTime(DateUtil.date());
                                record.setStatus("1");
                                record.setCompletedQuantity(String.valueOf(task.getTaskCount()));
                            }
                        }
                    }
                    //更新每日任务
                    personalDailyTaskRecordMapper.updatePersonalDailyTaskRecord(record);
                }
            });
        }
    }

    /**
     * 校验游戏任务是否达标
     * @param task
     * @param bigDecimalList
     * @param count
     * @return
     */
    public boolean checkGameTask(PersonalTasks task,List<BigDecimal> bigDecimalList,int count){
        boolean flag = true;
        //糖果任务
        if("1".equals(task.getTaskCompleteType())){
            //今日获取糖果数量
            BigDecimal sum = bigDecimalList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal taskCountBig = new BigDecimal(task.getTaskCount());
            if(sum.compareTo(BigDecimal.ZERO)<0 || sum.compareTo(taskCountBig)<0){
                flag = false;
            }
            //局数任务
        }else if("2".equals(task.getTaskCompleteType())){
            if(task.getTaskCount()>count){
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 新增个人任务
     *
     * @param personalTasks 个人任务
     * @return 结果
     */
    @Override
    public int insertPersonalTasks(PersonalTasks personalTasks)
    {
        personalTasks.setCreateTime(DateUtils.getNowDate());
        return personalTasksMapper.insertPersonalTasks(personalTasks);
    }

    /**
     * 修改个人任务
     *
     * @param personalTasks 个人任务
     * @return 结果
     */
    @Override
    public int updatePersonalTasks(PersonalTasks personalTasks)
    {
        personalTasks.setUpdateTime(DateUtils.getNowDate());
        return personalTasksMapper.updatePersonalTasks(personalTasks);
    }

    /**
     * 批量删除个人任务
     *
     * @param ids 需要删除的个人任务 主键
     * @return 结果
     */
    @Override
    public int deletePersonalTasksByIds(Long[] ids)
    {
        return personalTasksMapper.deletePersonalTasksByIds(ids);
    }

    /**
     * 删除个人任务 信息
     *
     * @param id 个人任务 主键
     * @return 结果
     */
    @Override
    public int deletePersonalTasksById(Long id)
    {
        return personalTasksMapper.deletePersonalTasksById(id);
    }

    /**
     * 查询个人任务 列表
     *
     * @param personalTasks 个人任务
     * @return 个人任务 集合
     */
    @Override
    public List<PersonalTasks> selectUserTasksList(PersonalTasks personalTasks) {
        return personalTasksMapper.selectUserTasksList(personalTasks);
    }

}
