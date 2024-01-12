package com.ruoyi.qwk.blindBox.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.blindBox.domain.UserPrize;
import org.apache.ibatis.annotations.Param;

/**
 * 用户盲盒爆出奖品Service接口
 * 
 * @author cjf
 * @date 2023-09-21
 */
public interface IUserPrizeService 
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
     * 批量删除用户盲盒爆出奖品
     * 
     * @param ids 需要删除的用户盲盒爆出奖品主键集合
     * @return 结果
     */
    public int deleteUserPrizeByIds(Long[] ids);

    /**
     * 删除用户盲盒爆出奖品信息
     * 
     * @param id 用户盲盒爆出奖品主键
     * @return 结果
     */
    public int deleteUserPrizeById(Long id);

    /**
     *
     * 领取奖品
     */
    public AjaxResult receivePrize(Long userId, Long id);

    /**
     *
     * 碎片兑换
     * */
    public AjaxResult debrisExchange(Long userId,Long id);

    /**
     *
     * 判断用户是否中新手礼品
     * */
    public Integer isNovice(Long userId);

    /**
     * 根据用户id商品id查询中奖信息
     * */
    public UserPrize getByUserIdAndGoodsId(Long userId,Long goodsId);

    /**
     * 查询幸运榜
     * */
    public AjaxResult getLuckyList();

}
