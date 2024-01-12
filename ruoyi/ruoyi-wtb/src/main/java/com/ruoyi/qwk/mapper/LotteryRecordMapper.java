package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.LotteryRecord;

/**
 * 抽奖记录 Mapper接口
 *
 * @author cjf
 * @date 2023-12-21
 */
@DataSource(value = DataSourceType.SLAVE)
public interface LotteryRecordMapper
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
     * 删除抽奖记录
     *
     * @param id 抽奖记录 主键
     * @return 结果
     */
    public int deleteLotteryRecordById(Long id);

    /**
     * 批量删除抽奖记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLotteryRecordByIds(Long[] ids);
}
