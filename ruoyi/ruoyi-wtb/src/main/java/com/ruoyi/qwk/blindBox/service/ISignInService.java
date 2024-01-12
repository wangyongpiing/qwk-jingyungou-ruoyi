package com.ruoyi.qwk.blindBox.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.blindBox.domain.SignIn;

/**
 * 签到Service接口
 * 
 * @author cjf
 * @date 2023-11-03
 */
public interface ISignInService 
{
    /**
     * 查询签到
     * 
     * @param id 签到主键
     * @return 签到
     */
    public SignIn selectSignInById(Long id);

    /**
     * 查询签到列表
     * 
     * @param signIn 签到
     * @return 签到集合
     */
    public List<SignIn> selectSignInList(SignIn signIn);

    /**
     * 新增签到
     * 
     * @param signIn 签到
     * @return 结果
     */
    public int insertSignIn(SignIn signIn);

    /**
     * 修改签到
     * 
     * @param signIn 签到
     * @return 结果
     */
    public int updateSignIn(SignIn signIn);

    /**
     * 批量删除签到
     * 
     * @param ids 需要删除的签到主键集合
     * @return 结果
     */
    public int deleteSignInByIds(Long[] ids);

    /**
     * 删除签到信息
     * 
     * @param id 签到主键
     * @return 结果
     */
    public int deleteSignInById(Long id);

    /**
     *
     * 签到
     * */
    public AjaxResult signIn();

}
