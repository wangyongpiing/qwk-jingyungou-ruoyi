package com.ruoyi.qwk.blindBox.mapper;

import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.UserPrize;
import org.apache.ibatis.annotations.Param;

/**
 * 用户盲盒爆出奖品Mapper接口
 * 
 * @author cjf
 * @date 2023-09-21
 */
@DataSource(value = DataSourceType.SLAVE)
public interface UserPrizeMapper 
{
    /**
     * 查询用户盲盒爆出奖品
     * 
     * @param id 用户盲盒爆出奖品主键
     * @return 用户盲盒爆出奖品
     */
    public UserPrize selectUserPrizeById(Long id);

    /**
     * 查询用户盲盒爆出奖品列表
     * 
     * @param userPrize 用户盲盒爆出奖品
     * @return 用户盲盒爆出奖品集合
     */
    public List<UserPrize> selectUserPrizeList(UserPrize userPrize);

    /**
     * 新增用户盲盒爆出奖品
     * 
     * @param userPrize 用户盲盒爆出奖品
     * @return 结果
     */
    public int insertUserPrize(UserPrize userPrize);

    /**
     * 修改用户盲盒爆出奖品
     * 
     * @param userPrize 用户盲盒爆出奖品
     * @return 结果
     */
    public int updateUserPrize(UserPrize userPrize);

    /**
     * 删除用户盲盒爆出奖品
     * 
     * @param id 用户盲盒爆出奖品主键
     * @return 结果
     */
    public int deleteUserPrizeById(Long id);

    /**
     * 批量删除用户盲盒爆出奖品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserPrizeByIds(Long[] ids);

    /**
     *
     * 根据奖品编号用户编号查询奖品
     * */
    public UserPrize selectUserPrizeByUserId(@Param("id")Long id,@Param("userId")Long userId);

    /**
     *
     * 判断用户是否中新手礼品
     * */
    public Integer isNovice(@Param("userId")Long userId);

    /**
     * 根据用户id商品id查询中奖信息
     * */
    public UserPrize getByUserIdAndGoodsId(@Param("userId")Long userId,@Param("goodsId")Long goodsId);

    /**
     * 获取幸运榜
     * */
    public List<HashMap<String,Object>> getLuckyList(UserPrize userPrize);


}
