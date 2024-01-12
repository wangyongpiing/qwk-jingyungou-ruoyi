package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.AssistanceDrawNumber;
import io.swagger.annotations.ApiOperation;

/**
 *  助力抽奖次数Service接口
 *
 * @author cjf
 * @date 2023-12-21
 */
public interface IAssistanceDrawNumberService
{
    /**
     * 查询 助力抽奖次数
     *
     * @param id  助力抽奖次数主键
     * @return  助力抽奖次数
     */
    public AssistanceDrawNumber selectAssistanceDrawNumberById(Long id);

    /**
     * 查询 助力抽奖次数列表
     *
     * @param assistanceDrawNumber  助力抽奖次数
     * @return  助力抽奖次数集合
     */
    public List<AssistanceDrawNumber> selectAssistanceDrawNumberList(AssistanceDrawNumber assistanceDrawNumber);

    /**
     * 新增 助力抽奖次数
     *
     * @param assistanceDrawNumber  助力抽奖次数
     * @return 结果
     */
    public int insertAssistanceDrawNumber(AssistanceDrawNumber assistanceDrawNumber);

    /**
     * 修改 助力抽奖次数
     *
     * @param assistanceDrawNumber  助力抽奖次数
     * @return 结果
     */
    public int updateAssistanceDrawNumber(AssistanceDrawNumber assistanceDrawNumber);

    /**
     * 批量删除 助力抽奖次数
     *
     * @param ids 需要删除的 助力抽奖次数主键集合
     * @return 结果
     */
    public int deleteAssistanceDrawNumberByIds(Long[] ids);

    /**
     * 删除 助力抽奖次数信息
     *
     * @param id  助力抽奖次数主键
     * @return 结果
     */
    public int deleteAssistanceDrawNumberById(Long id);

    /**
     * APP获取抽奖剩余次数
     */
    public AjaxResult userAssistanceCount();
}
