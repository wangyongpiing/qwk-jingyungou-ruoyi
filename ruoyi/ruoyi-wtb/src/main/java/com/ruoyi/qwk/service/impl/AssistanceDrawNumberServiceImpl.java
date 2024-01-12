package com.ruoyi.qwk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beust.ah.A;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.AssistanceDrawNumberMapper;
import com.ruoyi.qwk.domain.AssistanceDrawNumber;
import com.ruoyi.qwk.service.IAssistanceDrawNumberService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  助力抽奖次数Service业务层处理
 *
 * @author cjf
 * @date 2023-12-21
 */
@Service
public class AssistanceDrawNumberServiceImpl implements IAssistanceDrawNumberService
{
    @Autowired
    private AssistanceDrawNumberMapper assistanceDrawNumberMapper;

    /**
     * 查询 助力抽奖次数
     *
     * @param id  助力抽奖次数主键
     * @return  助力抽奖次数
     */
    @Override
    public AssistanceDrawNumber selectAssistanceDrawNumberById(Long id)
    {
        return assistanceDrawNumberMapper.selectAssistanceDrawNumberById(id);
    }

    /**
     * 查询 助力抽奖次数列表
     *
     * @param assistanceDrawNumber  助力抽奖次数
     * @return  助力抽奖次数
     */
    @Override
    public List<AssistanceDrawNumber> selectAssistanceDrawNumberList(AssistanceDrawNumber assistanceDrawNumber)
    {
        return assistanceDrawNumberMapper.selectAssistanceDrawNumberList(assistanceDrawNumber);
    }

    /**
     * 新增 助力抽奖次数
     *
     * @param assistanceDrawNumber  助力抽奖次数
     * @return 结果
     */
    @Override
    public int insertAssistanceDrawNumber(AssistanceDrawNumber assistanceDrawNumber)
    {
        assistanceDrawNumber.setCreateTime(DateUtils.getNowDate());
        return assistanceDrawNumberMapper.insertAssistanceDrawNumber(assistanceDrawNumber);
    }

    /**
     * 修改 助力抽奖次数
     *
     * @param assistanceDrawNumber  助力抽奖次数
     * @return 结果
     */
    @Override
    public int updateAssistanceDrawNumber(AssistanceDrawNumber assistanceDrawNumber)
    {
        assistanceDrawNumber.setUpdateTime(DateUtils.getNowDate());
        return assistanceDrawNumberMapper.updateAssistanceDrawNumber(assistanceDrawNumber);
    }

    /**
     * 批量删除 助力抽奖次数
     *
     * @param ids 需要删除的 助力抽奖次数主键
     * @return 结果
     */
    @Override
    public int deleteAssistanceDrawNumberByIds(Long[] ids)
    {
        return assistanceDrawNumberMapper.deleteAssistanceDrawNumberByIds(ids);
    }

    /**
     * 删除 助力抽奖次数信息
     *
     * @param id  助力抽奖次数主键
     * @return 结果
     */
    @Override
    public int deleteAssistanceDrawNumberById(Long id)
    {
        return assistanceDrawNumberMapper.deleteAssistanceDrawNumberById(id);
    }

    /**
     * APP获取抽奖剩余次数
     */
    public AjaxResult userAssistanceCount(){
        Map<String,Object> map = new HashMap<>();
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取用户抽奖次数
        AssistanceDrawNumber number = new AssistanceDrawNumber();
        number.setAssistanceId(String.valueOf(user.getUserId()));
        List<AssistanceDrawNumber> list = assistanceDrawNumberMapper.selectAssistanceDrawNumberList(number);
        map.put("remainingTimes",list.get(0).getNoLotteryDrawNum());
        return AjaxResult.success(map);
    }
}
