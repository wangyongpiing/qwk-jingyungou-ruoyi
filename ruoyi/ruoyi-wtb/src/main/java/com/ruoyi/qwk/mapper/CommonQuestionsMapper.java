package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.CommonQuestions;

/**
 * 常见问题Mapper接口
 * 
 * @author cjf
 * @date 2023-08-22
 */
@DataSource(value = DataSourceType.SLAVE)
public interface CommonQuestionsMapper 
{
    /**
     * 查询常见问题
     * 
     * @param id 常见问题主键
     * @return 常见问题
     */
    public CommonQuestions selectCommonQuestionsById(Long id);

    /**
     * 查询常见问题列表
     * 
     * @param commonQuestions 常见问题
     * @return 常见问题集合
     */
    public List<CommonQuestions> selectCommonQuestionsList(CommonQuestions commonQuestions);

    /**
     * 新增常见问题
     * 
     * @param commonQuestions 常见问题
     * @return 结果
     */
    public int insertCommonQuestions(CommonQuestions commonQuestions);

    /**
     * 修改常见问题
     * 
     * @param commonQuestions 常见问题
     * @return 结果
     */
    public int updateCommonQuestions(CommonQuestions commonQuestions);

    /**
     * 删除常见问题
     * 
     * @param id 常见问题主键
     * @return 结果
     */
    public int deleteCommonQuestionsById(Long id);

    /**
     * 批量删除常见问题
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommonQuestionsByIds(Long[] ids);
}
