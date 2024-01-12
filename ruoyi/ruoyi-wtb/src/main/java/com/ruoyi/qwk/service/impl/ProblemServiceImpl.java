package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.ProblemMapper;
import com.ruoyi.qwk.domain.Problem;
import com.ruoyi.qwk.service.IProblemService;

/**
 * 问题投诉Service业务层处理
 * 
 * @author cjf
 * @date 2023-08-30
 */
@Service
public class ProblemServiceImpl implements IProblemService 
{
    @Autowired
    private ProblemMapper problemMapper;

    /**
     * 查询问题投诉
     * 
     * @param id 问题投诉主键
     * @return 问题投诉
     */
    @Override
    public Problem selectProblemById(Long id)
    {
        return problemMapper.selectProblemById(id);
    }

    /**
     * 查询问题投诉列表
     * 
     * @param problem 问题投诉
     * @return 问题投诉
     */
    @Override
    public List<Problem> selectProblemList(Problem problem)
    {
        return problemMapper.selectProblemList(problem);
    }

    /**
     * 新增问题投诉
     * 
     * @param problem 问题投诉
     * @return 结果
     */
    @Override
    public int insertProblem(Problem problem)
    {
        problem.setCreateBy(SecurityUtils.getUsername());
        problem.setCreateTime(DateUtils.getNowDate());
        return problemMapper.insertProblem(problem);
    }

    /**
     * 修改问题投诉
     * 
     * @param problem 问题投诉
     * @return 结果
     */
    @Override
    public int updateProblem(Problem problem)
    {
        problem.setUpdateBy(SecurityUtils.getUsername());
        problem.setUpdateTime(DateUtils.getNowDate());
        return problemMapper.updateProblem(problem);
    }

    /**
     * 批量删除问题投诉
     * 
     * @param ids 需要删除的问题投诉主键
     * @return 结果
     */
    @Override
    public int deleteProblemByIds(Long[] ids)
    {
        return problemMapper.deleteProblemByIds(ids);
    }

    /**
     * 删除问题投诉信息
     * 
     * @param id 问题投诉主键
     * @return 结果
     */
    @Override
    public int deleteProblemById(Long id)
    {
        return problemMapper.deleteProblemById(id);
    }
}
