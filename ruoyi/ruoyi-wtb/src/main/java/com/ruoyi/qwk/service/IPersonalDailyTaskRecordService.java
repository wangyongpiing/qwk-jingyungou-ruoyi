package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.PersonalDailyTaskRecord;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 个人每日任务记录 Service接口
 *
 * @author cjf
 * @date 2023-12-22
 */
public interface IPersonalDailyTaskRecordService
{
    /**
     * 查询个人每日任务记录
     *
     * @param id 个人每日任务记录 主键
     * @return 个人每日任务记录
     */
    public PersonalDailyTaskRecord selectPersonalDailyTaskRecordById(Long id);

    /**
     * 查询个人每日任务记录 列表
     *
     * @param personalDailyTaskRecord 个人每日任务记录
     * @return 个人每日任务记录 集合
     */
    public List<PersonalDailyTaskRecord> selectPersonalDailyTaskRecordList(PersonalDailyTaskRecord personalDailyTaskRecord);

    /**
     * 新增个人每日任务记录
     *
     * @param personalDailyTaskRecord 个人每日任务记录
     * @return 结果
     */
    public int insertPersonalDailyTaskRecord(PersonalDailyTaskRecord personalDailyTaskRecord);

    /**
     * 修改个人每日任务记录
     *
     * @param personalDailyTaskRecord 个人每日任务记录
     * @return 结果
     */
    public int updatePersonalDailyTaskRecord(PersonalDailyTaskRecord personalDailyTaskRecord);

    /**
     * 批量删除个人每日任务记录
     *
     * @param ids 需要删除的个人每日任务记录 主键集合
     * @return 结果
     */
    public int deletePersonalDailyTaskRecordByIds(Long[] ids);

    /**
     * 删除个人每日任务记录 信息
     *
     * @param id 个人每日任务记录 主键
     * @return 结果
     */
    public int deletePersonalDailyTaskRecordById(Long id);

    /**
     * APP领取任务
     * @param taskId
     * @return
     */
    public AjaxResult claimTasks(String taskId,String isDaily);

    /**
     * 用户首次进入游戏
     * @param gameType
     * @return
     */
    public AjaxResult firstGame(String gameType,String userId);

    /**
     * 定时生成用户每日任务
     */
    public void UserDailyTasks();
}
