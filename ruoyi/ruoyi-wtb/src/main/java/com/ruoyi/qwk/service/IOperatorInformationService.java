package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.OperatorInformation;

/**
 * 运营商信息 Service接口
 * 
 * @author cjf
 * @date 2023-12-01
 */
public interface IOperatorInformationService 
{
    /**
     * 查询运营商信息 
     * 
     * @param id 运营商信息 主键
     * @return 运营商信息 
     */
    public OperatorInformation selectOperatorInformationById(Long id);

    /**
     * 查询运营商信息 列表
     * 
     * @param operatorInformation 运营商信息 
     * @return 运营商信息 集合
     */
    public List<OperatorInformation> selectOperatorInformationList(OperatorInformation operatorInformation);

    /**
     * 新增运营商信息 
     * 
     * @param operatorInformation 运营商信息 
     * @return 结果
     */
    public int insertOperatorInformation(OperatorInformation operatorInformation);

    /**
     * 修改运营商信息 
     * 
     * @param operatorInformation 运营商信息 
     * @return 结果
     */
    public int updateOperatorInformation(OperatorInformation operatorInformation);

    /**
     * 批量删除运营商信息 
     * 
     * @param ids 需要删除的运营商信息 主键集合
     * @return 结果
     */
    public int deleteOperatorInformationByIds(Long[] ids);

    /**
     * 删除运营商信息 信息
     * 
     * @param id 运营商信息 主键
     * @return 结果
     */
    public int deleteOperatorInformationById(Long id);
}
