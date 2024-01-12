package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.CommonQuestionsMapper;
import com.ruoyi.qwk.domain.CommonQuestions;
import com.ruoyi.qwk.service.ICommonQuestionsService;

/**
 * 常见问题Service业务层处理
 * 
 * @author cjf
 * @date 2023-08-22
 */
@Service
public class CommonQuestionsServiceImpl implements ICommonQuestionsService 
{
    @Autowired
    private CommonQuestionsMapper commonQuestionsMapper;

    /**
     * 查询常见问题
     * 
     * @param id 常见问题主键
     * @return 常见问题
     */
    @Override
    public CommonQuestions selectCommonQuestionsById(Long id)
    {
        return commonQuestionsMapper.selectCommonQuestionsById(id);
    }

    /**
     * 查询常见问题列表
     * 
     * @param commonQuestions 常见问题
     * @return 常见问题
     */
    @Override
    public List<CommonQuestions> selectCommonQuestionsList(CommonQuestions commonQuestions)
    {
        return commonQuestionsMapper.selectCommonQuestionsList(commonQuestions);
    }

    /**
     * 新增常见问题
     * 
     * @param commonQuestions 常见问题
     * @return 结果
     */
    @Override
    public int insertCommonQuestions(CommonQuestions commonQuestions)
    {
        commonQuestions.setCreateTime(DateUtils.getNowDate());
        return commonQuestionsMapper.insertCommonQuestions(commonQuestions);
    }

    /**
     * 修改常见问题
     * 
     * @param commonQuestions 常见问题
     * @return 结果
     */
    @Override
    public int updateCommonQuestions(CommonQuestions commonQuestions)
    {
        commonQuestions.setUpdateTime(DateUtils.getNowDate());
        return commonQuestionsMapper.updateCommonQuestions(commonQuestions);
    }

    /**
     * 批量删除常见问题
     * 
     * @param ids 需要删除的常见问题主键
     * @return 结果
     */
    @Override
    public int deleteCommonQuestionsByIds(Long[] ids)
    {
        return commonQuestionsMapper.deleteCommonQuestionsByIds(ids);
    }

    /**
     * 删除常见问题信息
     * 
     * @param id 常见问题主键
     * @return 结果
     */
    @Override
    public int deleteCommonQuestionsById(Long id)
    {
        return commonQuestionsMapper.deleteCommonQuestionsById(id);
    }
}
