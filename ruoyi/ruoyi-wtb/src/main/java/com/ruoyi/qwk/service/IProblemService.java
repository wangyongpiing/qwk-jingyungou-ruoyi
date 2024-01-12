package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.Problem;

/**
 * 问题投诉Service接口
 * 
 * @author cjf
 * @date 2023-08-30
 */
public interface IProblemService 
{
    /**
     * 查询问题投诉
     * 
     * @param id 问题投诉主键
     * @return 问题投诉
     */
    public Problem selectProblemById(Long id);

    /**
     * 查询问题投诉列表
     * 
     * @param problem 问题投诉
     * @return 问题投诉集合
     */
    public List<Problem> selectProblemList(Problem problem);

    /**
     * 新增问题投诉
     * 
     * @param problem 问题投诉
     * @return 结果
     */
    public int insertProblem(Problem problem);

    /**
     * 修改问题投诉
     * 
     * @param problem 问题投诉
     * @return 结果
     */
    public int updateProblem(Problem problem);

    /**
     * 批量删除问题投诉
     * 
     * @param ids 需要删除的问题投诉主键集合
     * @return 结果
     */
    public int deleteProblemByIds(Long[] ids);

    /**
     * 删除问题投诉信息
     * 
     * @param id 问题投诉主键
     * @return 结果
     */
    public int deleteProblemById(Long id);
}
