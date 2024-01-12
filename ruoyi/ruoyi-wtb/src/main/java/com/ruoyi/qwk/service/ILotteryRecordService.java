package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.LotteryRecord;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 抽奖记录 Service接口
 *
 * @author cjf
 * @date 2023-12-21
 */
public interface ILotteryRecordService
{
    /**
     * 查询抽奖记录
     *
     * @param id 抽奖记录 主键
     * @return 抽奖记录
     */
    public LotteryRecord selectLotteryRecordById(Long id);

    /**
     * 查询抽奖记录 列表
     *
     * @param lotteryRecord 抽奖记录
     * @return 抽奖记录 集合
     */
    public List<LotteryRecord> selectLotteryRecordList(LotteryRecord lotteryRecord);

    /**
     * 新增抽奖记录
     *
     * @param lotteryRecord 抽奖记录
     * @return 结果
     */
    public int insertLotteryRecord(LotteryRecord lotteryRecord);

    /**
     * 修改抽奖记录
     *
     * @param lotteryRecord 抽奖记录
     * @return 结果
     */
    public int updateLotteryRecord(LotteryRecord lotteryRecord);

    /**
     * 批量删除抽奖记录
     *
     * @param ids 需要删除的抽奖记录 主键集合
     * @return 结果
     */
    public int deleteLotteryRecordByIds(Long[] ids);

    /**
     * 删除抽奖记录 信息
     *
     * @param id 抽奖记录 主键
     * @return 结果
     */
    public int deleteLotteryRecordById(Long id);

    /**
     * 生肖大转盘抽奖
     * @return
     */
    public AjaxResult drawLottery();
}
