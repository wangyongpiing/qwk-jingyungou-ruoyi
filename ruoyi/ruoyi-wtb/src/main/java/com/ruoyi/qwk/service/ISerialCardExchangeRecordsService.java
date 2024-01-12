package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.SerialCardExchangeRecords;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 序列卡兑换记录 Service接口
 *
 * @author cjf
 * @date 2023-12-01
 */
public interface ISerialCardExchangeRecordsService
{
    /**
     * 查询序列卡兑换记录
     *
     * @param id 序列卡兑换记录 主键
     * @return 序列卡兑换记录
     */
    public SerialCardExchangeRecords selectSerialCardExchangeRecordsById(Long id);

    /**
     * 查询序列卡兑换记录 列表
     *
     * @param serialCardExchangeRecords 序列卡兑换记录
     * @return 序列卡兑换记录 集合
     */
    public List<SerialCardExchangeRecords> selectSerialCardExchangeRecordsList(SerialCardExchangeRecords serialCardExchangeRecords);

    /**
     * 新增序列卡兑换记录
     *
     * @param serialCardExchangeRecords 序列卡兑换记录
     * @return 结果
     */
    public int insertSerialCardExchangeRecords(SerialCardExchangeRecords serialCardExchangeRecords);

    /**
     * 修改序列卡兑换记录
     *
     * @param serialCardExchangeRecords 序列卡兑换记录
     * @return 结果
     */
    public int updateSerialCardExchangeRecords(SerialCardExchangeRecords serialCardExchangeRecords);

    /**
     * 批量删除序列卡兑换记录
     *
     * @param ids 需要删除的序列卡兑换记录 主键集合
     * @return 结果
     */
    public int deleteSerialCardExchangeRecordsByIds(Long[] ids);

    /**
     * 删除序列卡兑换记录 信息
     *
     * @param id 序列卡兑换记录 主键
     * @return 结果
     */
    public int deleteSerialCardExchangeRecordsById(Long id);


    /**
     * App兑换序列卡
     * @param cardCode
     * @return
     */
    public AjaxResult exchangeCardApp(String cardCode,String userId);
}
