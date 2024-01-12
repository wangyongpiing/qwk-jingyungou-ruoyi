package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.SignIn;
import org.apache.ibatis.annotations.Param;

/**
 * 签到Mapper接口
 * 
 * @author cjf
 * @date 2023-11-03
 */
@DataSource(value = DataSourceType.SLAVE)
public interface SignInMapper 
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
     * 删除签到
     * 
     * @param id 签到主键
     * @return 结果
     */
    public int deleteSignInById(Long id);

    /**
     * 批量删除签到
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSignInByIds(Long[] ids);

    /**
     * 今日是否签到
     * */
    public int isTodaySignIn(@Param("userId") Long userId);

    /**
     *
     * */
    public int selectBySevenDay(@Param("userId") Long userId);

}
