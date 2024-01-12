package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.OperatorInformationMapper;
import com.ruoyi.qwk.domain.OperatorInformation;
import com.ruoyi.qwk.service.IOperatorInformationService;

/**
 * 运营商信息 Service业务层处理
 * 
 * @author cjf
 * @date 2023-12-01
 */
@Service
public class OperatorInformationServiceImpl implements IOperatorInformationService 
{
    @Autowired
    private OperatorInformationMapper operatorInformationMapper;

    /**
     * 查询运营商信息 
     * 
     * @param id 运营商信息 主键
     * @return 运营商信息 
     */
    @Override
    public OperatorInformation selectOperatorInformationById(Long id)
    {
        return operatorInformationMapper.selectOperatorInformationById(id);
    }

    /**
     * 查询运营商信息 列表
     * 
     * @param operatorInformation 运营商信息 
     * @return 运营商信息 
     */
    @Override
    public List<OperatorInformation> selectOperatorInformationList(OperatorInformation operatorInformation)
    {
        return operatorInformationMapper.selectOperatorInformationList(operatorInformation);
    }

    /**
     * 新增运营商信息 
     * 
     * @param operatorInformation 运营商信息 
     * @return 结果
     */
    @Override
    public int insertOperatorInformation(OperatorInformation operatorInformation)
    {
        operatorInformation.setCreateTime(DateUtils.getNowDate());
        return operatorInformationMapper.insertOperatorInformation(operatorInformation);
    }

    /**
     * 修改运营商信息 
     * 
     * @param operatorInformation 运营商信息 
     * @return 结果
     */
    @Override
    public int updateOperatorInformation(OperatorInformation operatorInformation)
    {
        operatorInformation.setUpdateTime(DateUtils.getNowDate());
        return operatorInformationMapper.updateOperatorInformation(operatorInformation);
    }

    /**
     * 批量删除运营商信息 
     * 
     * @param ids 需要删除的运营商信息 主键
     * @return 结果
     */
    @Override
    public int deleteOperatorInformationByIds(Long[] ids)
    {
        return operatorInformationMapper.deleteOperatorInformationByIds(ids);
    }

    /**
     * 删除运营商信息 信息
     * 
     * @param id 运营商信息 主键
     * @return 结果
     */
    @Override
    public int deleteOperatorInformationById(Long id)
    {
        return operatorInformationMapper.deleteOperatorInformationById(id);
    }
}
