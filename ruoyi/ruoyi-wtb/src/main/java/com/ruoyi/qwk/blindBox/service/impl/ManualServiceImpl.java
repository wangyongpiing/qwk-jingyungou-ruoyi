package com.ruoyi.qwk.blindBox.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.qwk.blindBox.domain.FishingMonster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.ManualMapper;
import com.ruoyi.qwk.blindBox.domain.Manual;
import com.ruoyi.qwk.blindBox.service.IManualService;

/**
 * 图鉴Service业务层处理
 * 
 * @author cjf
 * @date 2023-10-31
 */
@Service
public class ManualServiceImpl implements IManualService 
{
    @Autowired
    private ManualMapper manualMapper;

    /**
     * 查询图鉴
     * 
     * @param id 图鉴主键
     * @return 图鉴
     */
    @Override
    public Manual selectManualById(Long id)
    {
        return manualMapper.selectManualById(id);
    }

    /**
     * 查询图鉴列表
     * 
     * @param manual 图鉴
     * @return 图鉴
     */
    @Override
    public List<Manual> selectManualList(Manual manual)
    {
        return manualMapper.selectManualList(manual);
    }

    /**
     * 新增图鉴
     * 
     * @param manual 图鉴
     * @return 结果
     */
    @Override
    public int insertManual(Manual manual)
    {
        manual.setCreateTime(DateUtils.getNowDate());
        return manualMapper.insertManual(manual);
    }

    /**
     * 修改图鉴
     * 
     * @param manual 图鉴
     * @return 结果
     */
    @Override
    public int updateManual(Manual manual)
    {
        manual.setUpdateTime(DateUtils.getNowDate());
        return manualMapper.updateManual(manual);
    }

    /**
     * 批量删除图鉴
     * 
     * @param ids 需要删除的图鉴主键
     * @return 结果
     */
    @Override
    public int deleteManualByIds(Long[] ids)
    {
        return manualMapper.deleteManualByIds(ids);
    }

    /**
     * 删除图鉴信息
     * 
     * @param id 图鉴主键
     * @return 结果
     */
    @Override
    public int deleteManualById(Long id)
    {
        return manualMapper.deleteManualById(id);
    }


    /**
     * 获取图鉴
     * */
    @Override
    public AjaxResult getManual(Manual manual){
        Map<String, Object> params = manual.getParams();
        if (!params.containsKey("fieldId"))return AjaxResult.error("缺少必要参数");
        int fieldId = Integer.parseInt(params.get("fieldId").toString());
        List<Manual> manuals = new ArrayList<>();
        switch (fieldId){
            case 1:
                params.put("low",0);
                params.put("tall",300);
                manual.setParams(params);
                manuals = manualMapper.selectManualList(manual);
                break;
            case 2:
                params.put("low",300);
                params.put("tall",1500);
                manual.setParams(params);
                manuals = manualMapper.selectManualList(manual);
                break;
            case 3:
                manuals = manualMapper.selectManualList(manual);
                break;
        }
        return AjaxResult.success(manuals);
    }





}
