package com.ruoyi.qwk.blindBox.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.qwk.domain.Goods;
import com.ruoyi.qwk.domain.Warehouse;
import com.ruoyi.qwk.domain.WarehouseDetails;
import com.ruoyi.qwk.mapper.WarehouseDetailsMapper;
import com.ruoyi.qwk.mapper.WarehouseMapper;
import com.ruoyi.system.service.ISysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.UserPrizeMapper;
import com.ruoyi.qwk.blindBox.domain.UserPrize;
import com.ruoyi.qwk.blindBox.service.IUserPrizeService;
import org.springframework.util.ObjectUtils;

/**
 * 用户盲盒爆出奖品Service业务层处理
 *
 * @author cjf
 * @date 2023-09-21
 */
@Service
public class UserPrizeServiceImpl implements IUserPrizeService
{
    @Autowired
    private UserPrizeMapper userPrizeMapper;

    @Autowired
    private WarehouseDetailsMapper warehouseDetailsMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private ISysUserService userService;

    /**
     * 查询用户盲盒爆出奖品
     *
     * @param id 用户盲盒爆出奖品主键
     * @return 用户盲盒爆出奖品
     */
    @Override
    public UserPrize selectUserPrizeById(Long id)
    {
        return userPrizeMapper.selectUserPrizeById(id);
    }

    /**
     * 查询用户盲盒爆出奖品列表
     *
     * @param userPrize 用户盲盒爆出奖品
     * @return 用户盲盒爆出奖品
     */
    @Override
    public List<UserPrize> selectUserPrizeList(UserPrize userPrize)
    {
        return userPrizeMapper.selectUserPrizeList(userPrize);
    }

    /**
     * 新增用户盲盒爆出奖品
     *
     * @param userPrize 用户盲盒爆出奖品
     * @return 结果
     */
    @Override
    public int insertUserPrize(UserPrize userPrize)
    {
        userPrize.setCreateTime(DateUtils.getNowDate());
        return userPrizeMapper.insertUserPrize(userPrize);
    }

    /**
     * 修改用户盲盒爆出奖品
     *
     * @param userPrize 用户盲盒爆出奖品
     * @return 结果
     */
    @Override
    public int updateUserPrize(UserPrize userPrize)
    {
        userPrize.setUpdateTime(DateUtils.getNowDate());
        return userPrizeMapper.updateUserPrize(userPrize);
    }

    /**
     * 批量删除用户盲盒爆出奖品
     *
     * @param ids 需要删除的用户盲盒爆出奖品主键
     * @return 结果
     */
    @Override
    public int deleteUserPrizeByIds(Long[] ids)
    {
        return userPrizeMapper.deleteUserPrizeByIds(ids);
    }

    /**
     * 删除用户盲盒爆出奖品信息
     *
     * @param id 用户盲盒爆出奖品主键
     * @return 结果
     */
    @Override
    public int deleteUserPrizeById(Long id)
    {
        return userPrizeMapper.deleteUserPrizeById(id);
    }


    /**
     *
     * 领取奖品
     */
    @Override
    public AjaxResult receivePrize(Long userId,Long id){
        UserPrize userPrize = userPrizeMapper.selectUserPrizeByUserId(id, userId);
        if (ObjectUtils.isEmpty(userPrize))return AjaxResult.error("奖品为空,请刷新后重试","");
//        if (!"1".equals(userPrize.getType()))return AjaxResult.error("该奖品不为实物奖品,无法领取","");
//        if (userPrize.getNum().intValue()<=0)return AjaxResult.error("数量不足,无法领取","");
//        if (!"0".equals(userPrize.getState()))return AjaxResult.error("奖品状态异常,无法领取","");
        SysUser user = SecurityUtils.getLoginUser().getUser();
        int value = 0;
        Date date = new Date();
        Goods goods = userPrize.getGoods();
        Warehouse warehouse = warehouseMapper.selectWarehouseByUserId(user.getUserId());
        if (ObjectUtils.isEmpty(warehouse)){
            warehouse = new Warehouse();
            warehouse.setUserId(user.getUserId());
            warehouse.setUserPhone(user.getPhonenumber());
            warehouse.setNickName(user.getNickName());
            warehouse.setQuantity(0L);
            warehouse.setTotalPrice("0");
            warehouse.setCreateBy(user.getUserName());
            warehouse.setCreateTime(date);
            value += warehouseMapper.insertWarehouse(warehouse);
        }
        warehouse.setQuantity(warehouse.getQuantity()+1L);
        Double totalPrice = Double.parseDouble(warehouse.getTotalPrice())+Double.parseDouble(goods.getPlatformPrice());
        warehouse.setTotalPrice(StringUtils.doubleTrans(totalPrice));
        warehouse.setUpdatedTime(date);
        warehouse.setUpdatedBy(user.getUserName());
        value+=warehouseMapper.updateWarehouse(warehouse);

        WarehouseDetails byGoodsId = warehouseDetailsMapper.getByGoodsId(userId, goods.getId());
        if (ObjectUtils.isEmpty(byGoodsId)){
            byGoodsId = new WarehouseDetails();
            byGoodsId.setUserId(userId);
            byGoodsId.setWid(warehouse.getId());
            byGoodsId.setGoodsId(goods.getId());
            byGoodsId.setGoodsImage(goods.getImage());
            byGoodsId.setGoodsName(goods.getName());
            byGoodsId.setType(goods.getType().toString());
            byGoodsId.setSource("盲盒奖品");
            byGoodsId.setQuantity(1L);
            byGoodsId.setPrice(goods.getPlatformPrice());
            byGoodsId.setState(0L);
            byGoodsId.setCreateBy(user.getUserName());
            byGoodsId.setCreateTime(date);
            byGoodsId.setIsNovice(userPrize.getIsNovice());
            value+=warehouseDetailsMapper.insertWarehouseDetails(byGoodsId);
        }else {
            byGoodsId.setQuantity(byGoodsId.getQuantity()+1);
            byGoodsId.setState(0L);
            byGoodsId.setCreateTime(date);
            byGoodsId.setUpdateTime(date);
            byGoodsId.setUpdateBy(user.getUserName());
            byGoodsId.setIsNovice(userPrize.getIsNovice());
            value+=warehouseDetailsMapper.updateWarehouseDetails(byGoodsId);
        }

        Integer num = userPrize.getNum().intValue();//奖品数量
        if ((num-1)<=0){
            userPrize.setNum(0L);
            userPrize.setUpdateBy(user.getUserName());
            userPrize.setUpdateTime(date);
            userPrize.setState("1");
            value+=userPrizeMapper.updateUserPrize(userPrize);
            //删除奖品
//            value+=userPrizeMapper.deleteUserPrizeById(userPrize.getId());
        }else {
            //修改数量
            Long prizeNum = userPrize.getNum() - 1;
            userPrize.setNum(prizeNum);
            userPrize.setUpdateBy(user.getUserName());
            userPrize.setUpdateTime(date);
            value+=userPrizeMapper.updateUserPrize(userPrize);
        }
//        if (value <= 0)return AjaxResult.error("领取异常","");
//        return AjaxResult.success("领取成功,请去仓库查看","");
        return AjaxResult.success();
    }

     /**
     *
     * 碎片兑换
     * */
     @Override
    public AjaxResult debrisExchange(Long userId,Long id){
        UserPrize userPrize = userPrizeMapper.selectUserPrizeByUserId(id, userId);
        if (ObjectUtils.isEmpty(userPrize))return AjaxResult.error("奖品为空,请刷新后重试","");
        if (!"0".equals(userPrize.getType()))return AjaxResult.error("该奖品不支持碎片兑换","");
        if (userPrize.getNum().intValue()<Integer.parseInt(userPrize.getGoods().getPlatformPrice()))return AjaxResult.error("碎片不足,无法兑换","");
        if (!"2".equals(userPrize.getState()))return AjaxResult.error("奖品状态异常,无法兑换","");
        SysUser user = SecurityUtils.getLoginUser().getUser();
        int value = 0;
        Date date = new Date();
        Goods goods = userPrize.getGoods();

        Integer num = userPrize.getNum().intValue();//碎片数量
        Integer platformPrice = Integer.parseInt(goods.getPlatformPrice());//兑换需要的碎片数量
        Integer residue = platformPrice - num;
        if (residue>0){
            userPrize.setNum(residue.longValue());
            userPrize.setUpdateBy(user.getUserName());
            userPrize.setUpdateTime(date);
            value+=userPrizeMapper.updateUserPrize(userPrize);
        }else {
            userPrize.setNum(0L);
            userPrize.setUpdateBy(user.getUserName());
            userPrize.setUpdateTime(date);
            userPrize.setState("3");
            value+=userPrizeMapper.updateUserPrize(userPrize);
//            value+=userPrizeMapper.deleteUserPrizeById(userPrize.getId());
        }

        Warehouse warehouse = warehouseMapper.selectWarehouseByUserId(user.getUserId());
        if (ObjectUtils.isEmpty(warehouse)){
            warehouse = new Warehouse();
            warehouse.setUserId(user.getUserId());
            warehouse.setUserPhone(user.getPhonenumber());
            warehouse.setNickName(user.getNickName());
            warehouse.setQuantity(0L);
            warehouse.setTotalPrice("0");
            warehouse.setCreateBy(user.getUserName());
            warehouse.setCreateTime(date);
            value += warehouseMapper.insertWarehouse(warehouse);
        }
        warehouse.setQuantity(warehouse.getQuantity()+1L);
        Double totalPrice = Double.parseDouble(warehouse.getTotalPrice())+Double.parseDouble(goods.getPlatformPrice());
        warehouse.setTotalPrice(StringUtils.doubleTrans(totalPrice));
        warehouse.setUpdatedTime(date);
        warehouse.setUpdatedBy(user.getUserName());
        value+=warehouseMapper.updateWarehouse(warehouse);

        WarehouseDetails byGoodsId = warehouseDetailsMapper.getByGoodsId(userId, goods.getId());
        if (ObjectUtils.isEmpty(byGoodsId)){
            byGoodsId = new WarehouseDetails();
            byGoodsId.setUserId(userId);
            byGoodsId.setWid(warehouse.getId());
            byGoodsId.setGoodsId(goods.getId());
            byGoodsId.setGoodsImage(goods.getImage());
            byGoodsId.setGoodsName(goods.getName());
            byGoodsId.setType(goods.getType().toString());
            byGoodsId.setSource("盲盒奖品");
            byGoodsId.setQuantity(1L);
            byGoodsId.setPrice(goods.getPlatformPrice());
            byGoodsId.setState(0L);
            byGoodsId.setCreateBy(user.getUserName());
            byGoodsId.setCreateTime(date);
            value+=warehouseDetailsMapper.insertWarehouseDetails(byGoodsId);
        }else {
            byGoodsId.setQuantity(byGoodsId.getQuantity()+1);
            byGoodsId.setState(0L);
            byGoodsId.setUpdateTime(date);
            byGoodsId.setUpdateBy(user.getUserName());
            value+=warehouseDetailsMapper.updateWarehouseDetails(byGoodsId);
        }

        if (value <= 0)return AjaxResult.error("兑换异常","");
        return AjaxResult.success("兑换成功,请去仓库查看","");
    }


    /**
     *
     * 判断用户是否中新手礼品
     * */
    @Override
    public Integer isNovice(Long userId){
        return userPrizeMapper.isNovice(userId);
    }

    /**
     * 根据用户id商品id查询中奖信息
     * */
    @Override
    public UserPrize getByUserIdAndGoodsId(Long userId,Long goodsId){
        return userPrizeMapper.getByUserIdAndGoodsId(userId,goodsId);
    }

    /**
     * 查询幸运榜
     * */
    @Override
    public AjaxResult getLuckyList(){
        UserPrize userPrize = new UserPrize();
        List<HashMap<String, Object>> luckyList = userPrizeMapper.getLuckyList(userPrize);
        HashMap<String, Object> map = new HashMap<>();
        map.put("luckyList",luckyList);
        userPrize.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        List<HashMap<String, Object>> myLuckyList = userPrizeMapper.getLuckyList(userPrize);
        map.put("myLuckyList",myLuckyList);
        return AjaxResult.success(map);
    }

}
