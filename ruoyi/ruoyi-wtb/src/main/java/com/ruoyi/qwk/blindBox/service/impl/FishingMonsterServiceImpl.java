package com.ruoyi.qwk.blindBox.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.RandomUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.Arith;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.qwk.blindBox.domain.*;
import com.ruoyi.qwk.blindBox.mapper.*;
import com.ruoyi.qwk.blindBox.service.*;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.mapper.*;
import com.ruoyi.qwk.service.IGoodsService;
import com.ruoyi.qwk.service.impl.GoodsServiceImpl;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 捕鱼怪物Service业务层处理
 *
 * @author cjf
 * @date 2023-09-14
 */
@Service
public class FishingMonsterServiceImpl implements IFishingMonsterService {

    Logger logger = LoggerFactory.getLogger(getClass());

//    private final Double k = 100.0;

    @Autowired
    private FishingMonsterMapper fishingMonsterMapper;

    @Autowired
    private RewardPackageMapper rewardPackageMapper;

    @Autowired
    private UserVipLevelMapper userVipLevelMapper;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IJackpotService jackpotService;

    @Autowired
    private IUserConsumeService userConsumeService;

    @Autowired
    private IUserPrizeService userPrizeService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IBlindBoxProbabilityService blindBoxProbabilityService;

    @Autowired
    private UserBulletsMapper userBulletsMapper;

    @Autowired
    private ShotsNumMapper shotsNumMapper;

    @Autowired
    private ProbabilityConfigMapper probabilityConfigMapper;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    @Autowired
    private WarehouseDetailsMapper warehouseDetailsMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserPrizeMapper userPrizeMapper;

    /**
     * 查询捕鱼怪物
     *
     * @param id 捕鱼怪物主键
     * @return 捕鱼怪物
     */
    @Override
    public FishingMonster selectFishingMonsterById(Long id)
    {
        return fishingMonsterMapper.selectFishingMonsterById(id);
    }

    /**
     * 查询捕鱼怪物列表
     *
     * @param fishingMonster 捕鱼怪物
     * @return 捕鱼怪物
     */
    @Override
    public List<FishingMonster> selectFishingMonsterList(FishingMonster fishingMonster)
    {
        return fishingMonsterMapper.selectFishingMonsterList(fishingMonster);
    }

    /**
     * 新增捕鱼怪物
     *
     * @param fishingMonster 捕鱼怪物
     * @return 结果
     */
    @Override
    public int insertFishingMonster(FishingMonster fishingMonster)
    {
        fishingMonster.setCreateTime(DateUtils.getNowDate());
        return fishingMonsterMapper.insertFishingMonster(fishingMonster);
    }

    /**
     * 修改捕鱼怪物
     *
     * @param fishingMonster 捕鱼怪物
     * @return 结果
     */
    @Override
    public int updateFishingMonster(FishingMonster fishingMonster)
    {
        fishingMonster.setUpdateTime(DateUtils.getNowDate());
        return fishingMonsterMapper.updateFishingMonster(fishingMonster);
    }

    /**
     * 批量删除捕鱼怪物
     *
     * @param ids 需要删除的捕鱼怪物主键
     * @return 结果
     */
    @Override
    public int deleteFishingMonsterByIds(Long[] ids)
    {
        return fishingMonsterMapper.deleteFishingMonsterByIds(ids);
    }

    /**
     * 删除捕鱼怪物信息
     *
     * @param id 捕鱼怪物主键
     * @return 结果
     */
    @Override
    public int deleteFishingMonsterById(Long id)
    {
        return fishingMonsterMapper.deleteFishingMonsterById(id);
    }

    /**
     *
     * 查询所有盲盒怪物
     * */
    @Override
    public List<FishingMonster> getAllFishingMonster(){
        return fishingMonsterMapper.getAllFishingMonster();
    }

    /**
     * 获取初级场
     * */
    @Override
    public List<FishingMonster> getElementary(){
        List<FishingMonster> byTall = fishingMonsterMapper.getByTall(0, 300);
        int temp = 9 - byTall.size();
        for (int i = 0; i < temp; i++) {
            FishingMonster fishingMonster = RandomUtil.randomEle(byTall);
            byTall.add(fishingMonster);
        }
        return byTall;
    }

    /**
     * 获取中级场
     * */
    @Override
    public List<FishingMonster> getIntermediate(){
        List<FishingMonster> byTall = fishingMonsterMapper.getByTall(300, 1500);
        int temp = 9 - byTall.size();
        for (int i = 0; i < temp; i++) {
            FishingMonster fishingMonster = RandomUtil.randomEle(byTall);
            byTall.add(fishingMonster);
        }
        return byTall;
    }

    /**
     * 获取高级场
     * */
    @Override
    public List<FishingMonster> getSenior(){
        return fishingMonsterMapper.selectFishingMonsterList(new FishingMonster());
    }

    /**
     *
     * 判断盲盒是否死亡以及掉落奖励
     * */
    @Override
    public AjaxResult ifDeath(List<Long> ids){
        List<FishingMonster> fishingMonsterList = fishingMonsterMapper.getByIds(ids);
        List<LootReward> lootRewards = new ArrayList<>();//掉落物品集合
        for (FishingMonster fishingMonster : fishingMonsterList) {
            List<Long> collect = Arrays.stream(fishingMonster.getFallId().split(","))
                                    .map(Long::parseLong)
                                    .collect(Collectors.toList());
            System.out.println("怪物编号为"+fishingMonster.getId()+"的掉落组id:"+fishingMonster.getFallId());
            lootRewards.add(weightAlgorithm(collect));
        }

        return AjaxResult.success(lootRewards);
    }

    /**
     * 新手场掉落
     *
     * */
    @Override
    public AjaxResult noviceFalling(){
        SysUser user = userService.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
        if ("1".equals(user.getNovice()))return AjaxResult.error("您不是新用户!");
        Integer novice = userPrizeService.isNovice(user.getUserId());
        if (novice>0){
            List<Goods> goodsByIsDebris = goodsService.selectByIsDebris();//所有碎片商品
            Goods goods = RandomUtil.randomEle(goodsByIsDebris);
            addUserPrize(goods);
            return AjaxResult.success("中奖").put("goods",goods).put("novice","0");
        }else {
            List<Integer> list = Arrays.asList(35, 44, 45, 46, 47, 48, 49, 50, 52);
            Integer id = RandomUtil.randomEle(list);
            Goods goods = goodsService.selectGoodsById(id.longValue());
            addUserPrize(goods);
//            userService.updateByNovice(user.getUserId());
            return AjaxResult.success("中奖").put("goods",goods).put("novice","0");
        }
    }
//
//    /**
//     * 新手场奖品
//     *
//     * */
//    @Override
//    public AjaxResult novicePrize(){
//        SysUser user = userService.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
////        SysUser user = SecurityUtils.getLoginUser().getUser();
//        if (!"0".equals(user.getNovice()))return AjaxResult.error("您不是新用户!");
//        ShotsNum shotsNum = shotsNumMapper.selectShotsNumByUserId(user.getUserId(), 0);
//        int raffleNum = 0;//新手抽奖次数
//        if (!ObjectUtils.isEmpty(shotsNum)){
//            raffleNum = shotsNum.getNum();
//        }
//        List<Goods> goodsByIsDebris = goodsService.selectByIsDebris();//所有碎片商品
//        List<Integer> list = Arrays.asList(35, 44, 45, 46, 47, 48, 49, 50, 52, 96, 97, 98);//所有赠送商品编号
////        if (raffleNum == 1 || raffleNum == 3 || raffleNum == 4 || raffleNum == 6 || raffleNum == 9)return AjaxResult.warn("未中奖");
//        if (raffleNum>0 && raffleNum < 5){
//            Goods goods = RandomUtil.randomEle(goodsByIsDebris);
//            Goods prize = addUserPrize(goods);
//            return AjaxResult.success("中奖").put("goods",prize).put("novice","0").put("raffleNum",raffleNum);
//        }
//        if (raffleNum >= 5){
//            Integer id = RandomUtil.randomEle(list);
//            Goods goods = goodsService.selectGoodsById(id.longValue());
//            Goods prize = addUserPrize(goods);
//            updateByNovice(user.getUserId());
//            shotsNum.setNum(0);
//            shotsNumMapper.updateShotsNum(shotsNum);
//            return AjaxResult.success("中奖").put("goods",prize).put("novice","1").put("raffleNum",raffleNum);
//        }
//        return AjaxResult.warn("未中奖");
//    }

    /**
     *
     * 修改用户为老用户
     * */
    @Override
    public AjaxResult updateByNovice(Long userId){
//        UserBullets byUserId = userBulletsMapper.selectUserBulletsByUserId(userId, 1L);
//        byUserId.setBulletsNum(0);
//        byUserId.setUpdateTime(new Date());
        int i = userService.updateByNovice(userId);
//        i +=userBulletsMapper.updateUserBullets(byUserId);
        if (i<=0)return AjaxResult.error();
        return AjaxResult.success();
    }

    /**
     * 判断盲盒是否掉落物品
     *
     * */
    @Override
    public AjaxResult isFalling(FishingMonster fm){
        BlindBoxProbability blindBoxProbability = blindBoxProbabilityService.selectBlindBoxProbabilityById(1L);
        Double k = blindBoxProbability.getProbability();//获取概率

        FishingMonster fishingMonster = this.selectFishingMonsterById(fm.getId());
        if (ObjectUtils.isEmpty(fishingMonster))return AjaxResult.error();
        Integer tall = Integer.parseInt(fishingMonster.getTall());
        Integer low = Integer.parseInt(fishingMonster.getLow());
        List<Goods> goodsList = goodsService.selectGoodsByPriceRange(low, tall);//该盲盒价值区间的商品
        Jackpot jackpot = jackpotService.selectJackpotById(1L);//奖池
        Double t = Double.parseDouble(jackpot.getTotal())*0.95;//奖池金额(t)

        UserConsume userConsume = userConsumeService.selectUserConsumeByUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        Double m = Double.parseDouble(userConsume.getConsume());//用户消耗金额
        Double p = 0.0;
        if(t > 0){
            p = m / (k*t);//中奖概率 P = M / (K * T)
        }
        logger.info("奖池金额(*0.8后)----->"+t);
        logger.info("用户消耗金额----->"+m);
        logger.info("中奖概率----->"+p);
        Goods goods = drawPrize(p, goodsList);
        if (ObjectUtils.isEmpty(goods))return AjaxResult.warn("对不起,未中奖!");
        if (Double.parseDouble(goods.getCashPrice())>t)return AjaxResult.warn("对不起,未中奖!");
        //中奖后的操作
        Double total = Double.parseDouble(jackpot.getTotal())-Double.parseDouble(goods.getCashPrice());
        jackpot.setTotal(StringUtils.doubleTrans(total < 0 ? 0 : total));
        jackpotService.updateJackpot(jackpot);
        Double consume = m-Double.parseDouble(goods.getCashPrice());
        userConsume.setConsume(StringUtils.doubleTrans(consume < 0 ? 0 : consume));
        userConsumeService.updateUserConsume(userConsume);
        addUserPrize(goods);//中奖记录
        return AjaxResult.success("中奖").put("goods",goods).put("fishingMonster",fishingMonster);
    }

    /**
     * 判断盲盒是否掉落物品 新公式
     * */
    @Override
    public AjaxResult isFallingTwo(FishingMonster fm){
        BlindBoxProbability blindBoxProbability = blindBoxProbabilityService.selectBlindBoxProbabilityById(1L);
        Double k = blindBoxProbability.getProbability();//获取概率
        if (!fm.getParams().containsKey("fieldId"))return AjaxResult.error("参数缺失");
        int fieldId = Integer.parseInt(fm.getParams().get("fieldId").toString());//场次编号
        FishingMonster fishingMonster = this.selectFishingMonsterById(fm.getId());
        if (ObjectUtils.isEmpty(fishingMonster))return AjaxResult.error("参数异常");
        List<Goods> goodsList = goodsService.selectGoodsByPriceRange(Integer.parseInt(fishingMonster.getLow()),
                Integer.parseInt(fishingMonster.getTall()));//该盲盒价值区间的商品
        Jackpot jackpot = jackpotService.selectJackpotById(1L);//奖池
        Double t = Arith.mul(Double.parseDouble(jackpot.getTotal()),0.95);//奖池金额(t)
        UserConsume userConsume = userConsumeService.selectUserConsumeByUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        Double w = Double.parseDouble(userConsume.getConsume());//用户消耗金额
        Double m = 1.0;//放大倍率
        if (t>0){
        //   M = W / (K * T)
            m = Arith.div(w,Arith.mul(k,t));
            if (m<1){
                m = 1.0;
            }
        }
        logger.info("奖池金额T(*0.95后)----->"+t);
        logger.info("用户消耗金额W----->"+w);
        logger.info("放大倍率M----->"+m);
        logger.info("k----->"+k);
        Goods goods = obtainGoods(m, fieldId, fm.getId(), goodsList);
        if (ObjectUtils.isEmpty(goods))return AjaxResult.warn("对不起,未中奖");
        if (Double.parseDouble(goods.getCashPrice())>t)return AjaxResult.warn("对不起,未中奖");
        //中奖后的操作
        goods = addUserPrize(goods);//中奖记录
        if ("0".equals(goods.getIsDebris())){
            double num = Double.parseDouble(goods.getParams().get("num").toString());
            Double total = Arith.sub(Double.parseDouble(jackpot.getTotal()),num);
            jackpot.setTotal(StringUtils.doubleTrans(total < 0 ? 0 : total));
            Double consume = Arith.sub(w,num);
            userConsume.setConsume(StringUtils.doubleTrans(consume < 0 ? 0 : consume));
        }else {
            Double total = Arith.sub(Double.parseDouble(jackpot.getTotal()),Double.parseDouble(goods.getCashPrice()));
            jackpot.setTotal(StringUtils.doubleTrans(total < 0 ? 0 : total));
            Double consume = Arith.sub(w,Double.parseDouble(goods.getCashPrice()));
            userConsume.setConsume(StringUtils.doubleTrans(consume < 0 ? 0 : consume));
        }
        jackpotService.updateJackpot(jackpot);//修改奖池金额
        userConsumeService.updateUserConsume(userConsume);//修改用户消耗金额
        ShotsNum shotsNum = shotsNumMapper.selectShotsNumByUserId(SecurityUtils.getLoginUser().getUser().getUserId(), fieldId);
        shotsNum.setNum(0);
        shotsNumMapper.updateShotsNum(shotsNum);
        return AjaxResult.success("中奖").put("goods",goods).put("fishingMonster",fishingMonster);
    }

    /**
     * 盲盒概率第四版
     * */
    public AjaxResult isFallingFour(FishingMonster fm){
        if (!fm.getParams().containsKey("fieldId"))return AjaxResult.error("参数缺失");
        if (!fm.getParams().containsKey("number"))return AjaxResult.error("参数缺失");
        int fieldId = Integer.parseInt(fm.getParams().get("fieldId").toString());//场次编号
        Long number = Long.parseLong(fm.getParams().get("number").toString());//扣除能量
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取用户电池
        UserAssets userAssets= userAssetsMapper.selectByUserId(user.getUserId(),2L);
        if(ObjUtil.isNull(userAssets)){
            return AjaxResult.error("用户资产不存在");
        }
        if(number>Integer.parseInt(userAssets.getTotal())){
            return AjaxResult.error("能量电池不足");
        }
        //
        userAssets.setTotal(String.valueOf(Integer.parseInt(userAssets.getTotal())-number));
        userAssetsMapper.updateUserAssets(userAssets);
        //新增资产记录表
        AssetDetails assetDetails = new AssetDetails();
        setAssetDetails(assetDetails,user,userAssets,number);
        assetDetailsMapper.insertAssetDetails(assetDetails);
        //更新仓库
        setWarehouse(user,userAssets);
        FishingMonster fishingMonster = getByFieldId(fieldId);
        List<Goods> goodsList = goodsService.selectGoodsByPriceRange(Integer.parseInt(fishingMonster.getLow()),
        Integer.parseInt(fishingMonster.getTall()));//该盲盒价值区间的商品
        Jackpot jackpot = jackpotService.selectJackpotById(1L);//奖池
        Double t = Arith.mul(Double.parseDouble(jackpot.getTotal()),0.3);//奖池金额(t)
        logger.info("奖池金额T(*0.8后)----->"+t);
//        UserConsume userConsume = userConsumeService.selectUserConsumeByUserId(SecurityUtils.getLoginUser().getUser().getUserId());
//        Double w = Double.parseDouble(userConsume.getConsume());//用户消耗金额
        Goods goods = obtainGoodsFour(goodsList);
        if (ObjectUtils.isEmpty(goods)){
            goodsList =goodsService.selectGoodsByPriceRange(0,10);
            goods = obtainGoodsFour(goodsList);
            if (ObjectUtils.isEmpty(goods))return AjaxResult.warn("未中奖");
        }
        Integer debrisNum = getDebrisNum(fieldId);
        if ("0".equals(goods.getIsDebris())){
            goods.setCashPrice(debrisNum.toString());
        }
        if (Double.parseDouble(goods.getCashPrice())>t){
            goodsList =goodsService.selectGoodsByPriceRange(0,10);
            goods = obtainGoodsFour(goodsList);
            if (ObjectUtils.isEmpty(goods))return AjaxResult.warn("未中奖");
            if ("0".equals(goods.getIsDebris())){
                goods.setCashPrice(debrisNum.toString());
            }
            if (Double.parseDouble(goods.getCashPrice())>t)return AjaxResult.warn("未中奖");
        }

        goods = addUserPrizeTwo(goods, debrisNum);
        if ("0".equals(goods.getIsDebris())){
            double num = Double.parseDouble(goods.getParams().get("num").toString());
            Double total = Arith.sub(Double.parseDouble(jackpot.getTotal()),num);
            jackpot.setTotal(StringUtils.doubleTrans(total < 0 ? 0 : total));
        }else {
            Double total = Arith.sub(Double.parseDouble(jackpot.getTotal()),Double.parseDouble(goods.getCashPrice()));
            jackpot.setTotal(StringUtils.doubleTrans(total < 0 ? 0 : total));
        }
        //累加奖池
        jackpot.setTotal(String.valueOf(Double.valueOf(jackpot.getTotal())+number));
        jackpotService.updateJackpot(jackpot);//修改奖池金额

        Warehouse warehouse = new Warehouse();
        //获取用户对应商品明细
        WarehouseDetails warehouseDetails = new WarehouseDetails();
        warehouseDetails.setUserId(user.getUserId());
        warehouseDetails.setGoodsId(goods.getId());
        warehouseDetails.setState(0L);
        List<WarehouseDetails> warList = warehouseDetailsMapper.selectWarehouseDetailsList(warehouseDetails);
        if(CollUtil.isEmpty(warList)){
            //新增仓库记录
            warehouse.setUserId(user.getUserId());
            warehouse.setNickName(user.getNickName());
            warehouse.setQuantity(1L);
            warehouse.setUserPhone(user.getPhonenumber());
            warehouse.setTotalPrice(goods.getPlatformPrice());
            warehouseMapper.insertWarehouse(warehouse);
            //新增仓库详情记录
            warehouseDetails = new WarehouseDetails();
            warehouseDetails.setUserId(user.getUserId());
            warehouseDetails.setWid(warehouse.getId());
            warehouseDetails.setGoodsId(goods.getId());
            warehouseDetails.setType("1");
            warehouseDetails.setGoodsImage(goods.getImage());
            warehouseDetails.setGoodsName(goods.getName());
            warehouseDetails.setQuantity(1L);
            warehouseDetails.setPrice(goods.getPlatformPrice());
            warehouseDetails.setState(0L);
            warehouseDetails.setSource("盲盒奖品");
            warehouseDetails.setCashPrice(goods.getCashPrice());
            warehouseDetailsMapper.insertWarehouseDetails(warehouseDetails);
            return  AjaxResult.success("中奖").put("goods",goods).put("fishingMonster",fishingMonster);
        }
        warehouseDetails = warList.get(0);
        //获取用户仓库能量电池
        warehouse = warehouseMapper.selectWarehouseById(warehouseDetails.getWid());
        //更新用户仓库，仓库明细
        warehouseDetails.setQuantity(warehouseDetails.getQuantity()+1L);
        warehouseDetails.setPrice(String.valueOf(Long.parseLong(goods.getPlatformPrice())));
        warehouseDetails.setCashPrice(goods.getCashPrice());
        warehouseDetailsMapper.updateWarehouseDetails(warehouseDetails);
        warehouse.setQuantity(warehouse.getQuantity()+1L);
        warehouse.setTotalPrice(String.valueOf(Long.parseLong(warehouse.getTotalPrice())+Long.parseLong(goods.getPlatformPrice())));
        warehouseMapper.updateWarehouse(warehouse);
        return AjaxResult.success("中奖").put("goods",goods).put("fishingMonster",fishingMonster);
    }

    /**
     * 赋值资产记录
     * @param assetDetails
     * @param user
     * @param userAssets
     * @param number
     * @return
     */
    public void setAssetDetails(AssetDetails assetDetails,SysUser user,UserAssets userAssets,Long number){
        assetDetails.setImage("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/images/1694419558989.jpg");
        assetDetails.setUserPhone(user.getPhonenumber());
        assetDetails.setUserId(user.getUserId());
        assetDetails.setBalance(userAssets.getTotal());
        assetDetails.setNotes("盲盒抽奖");
        assetDetails.setCreatedBy(user.getUserName());
        assetDetails.setUpdatedBy(user.getUserName());
        assetDetails.setType(5L);
        assetDetails.setAddOrSubtract("0");
        assetDetails.setRecords(String.valueOf(number));
    }

    public void setWarehouse(SysUser user,UserAssets userAssets){
        Warehouse warehouse = new Warehouse();
        //获取用户对应能量电池明细
        WarehouseDetails warehouseDetails = warehouseDetailsMapper.getByGoodsId(user.getUserId(),276L);
        if(ObjUtil.isNull(warehouseDetails)){
            //新增仓库记录
            warehouse.setUserId(user.getUserId());
            warehouse.setNickName(user.getNickName());
            warehouse.setQuantity(Long.parseLong(userAssets.getTotal()));
            warehouse.setUserPhone(user.getPhonenumber());
            warehouse.setTotalPrice("0");
            warehouseMapper.insertWarehouse(warehouse);
            //查询能量电池记录
            Goods goods = goodsMapper.selectGoodsById(276L);
            //新增仓库详情记录
            warehouseDetails = new WarehouseDetails();
            warehouseDetails.setUserId(user.getUserId());
            warehouseDetails.setWid(warehouse.getId());
            warehouseDetails.setGoodsId(goods.getId());
            warehouseDetails.setType("2");
            warehouseDetails.setGoodsImage(goods.getImage());
            warehouseDetails.setGoodsName(goods.getName());
            warehouseDetails.setQuantity(Long.parseLong(userAssets.getTotal()));
            warehouseDetailsMapper.insertWarehouseDetails(warehouseDetails);
        }
        //获取用户仓库能量电池
        warehouse = warehouseMapper.selectWarehouseById(warehouseDetails.getWid());
        //更新用户仓库，仓库明细
        warehouseDetails.setQuantity(Long.parseLong(userAssets.getTotal()));
        warehouseDetailsMapper.updateWarehouseDetails(warehouseDetails);
        warehouse.setQuantity(Long.parseLong(userAssets.getTotal()));
        warehouseMapper.updateWarehouse(warehouse);
    }

    /**
     * 是否获取物品
     *
     * @param p  中奖概率
     * @param goodsList 物品集合
     * */
    private Goods drawPrize(Double p,List<Goods> goodsList){
        // 计算每种奖励物品的中奖概率

        Double totalWeight = goodsList.stream().mapToDouble(goods ->Double.parseDouble(goods.getWeight())).sum();//总权重

        HashMap<Double, Goods> map = new HashMap<>();
        for (Goods goods : goodsList) {
            //Pi = (Wi / ΣWj) * P
            Double prizeProbability = (Double.parseDouble(goods.getWeight()) / totalWeight) * p;//每个物品中奖概率
            map.put(prizeProbability,goods);
        }

        // 随机抽取奖励物品
        Double random = Math.random();//随机数
        logger.info("总权重--->"+totalWeight);
        logger.info("random----->"+random);
        Double cumulativeProbability = 0.0;
        for (Double key : map.keySet()) {
            cumulativeProbability += key;
            if (cumulativeProbability > 1.0) {
                cumulativeProbability = 1.0; // 限制cumulativeProbability不超过1
            }
            logger.info("物品id为:"+map.get(key).getId()+" 中奖概率为:"+key);
            logger.info("累计概率---->"+cumulativeProbability+"\n");
            if (random < cumulativeProbability) {
                logger.info("中奖!该物品中奖概率为:"+key+" 物品权重为:"+map.get(key).getWeight()+" 物品id为:"+map.get(key).getId());
                return map.get(key);
            }
        }
        logger.info("未中奖");
        return null;
    }

    //添加奖品
    private Goods addUserPrize(Goods goods){
        SysUser sysUser = userService.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
        UserPrize userPrize = userPrizeService.getByUserIdAndGoodsId(SecurityUtils.getLoginUser().getUser().getUserId(), goods.getId());
        if (ObjectUtils.isEmpty(userPrize)){
            userPrize = new UserPrize();
            userPrize.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            userPrize.setState("0");
            userPrize.setType(goods.getIsDebris());
            userPrize.setGoodsId(goods.getId());
//            userPrize.setCreateTime(new Date());
            userPrize.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserName());
            if ("0".equals(goods.getIsDebris())){
                Integer i = RandomUtil.randomInt(100, 300);
                userPrize.setNum(i.longValue());
                goods.setParams(new HashMap<String, Object>(){{
                    put("num",i);
                }});
                userPrize.setState("2");
            }else {
                userPrize.setNum(1L);
                userPrize.setState("0");
                goods.setParams(new HashMap<String, Object>(){{
                    put("num",1);
                }});
            }
            if ("0".equals(sysUser.getNovice()))userPrize.setIsNovice("0");//是新手奖品
            userPrizeService.insertUserPrize(userPrize);
        }else {
            if ("0".equals(goods.getIsDebris())){
                Integer i = RandomUtil.randomInt(100, 300);
                userPrize.setNum(userPrize.getNum()+i.longValue());
                goods.setParams(new HashMap<String, Object>(){{
                    put("num",i);
                }});
                userPrize.setState("2");
            }else {
                userPrize.setNum(userPrize.getNum()+1L);
                goods.setParams(new HashMap<String, Object>(){{
                    put("num",1);
                }});
                userPrize.setState("0");
            }
            userPrize.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
//            userPrize.setUpdateTime(new Date());
            userPrizeService.updateUserPrize(userPrize);
        }
        return goods;
    }


//    //添加奖品
//    private Goods addUserPrizeTwo(Goods goods,Integer num){
//        SysUser sysUser = userService.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
//        UserPrize userPrize = userPrizeService.getByUserIdAndGoodsId(SecurityUtils.getLoginUser().getUser().getUserId(), goods.getId());
//        if (ObjectUtils.isEmpty(userPrize)){
//            userPrize = new UserPrize();
//            userPrize.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
//            userPrize.setState("0");
//            userPrize.setType(goods.getIsDebris());
//            userPrize.setGoodsId(goods.getId());
//            userPrize.setCreateTime(new Date());
//            userPrize.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserName());
//            if ("0".equals(goods.getIsDebris())){
//                Integer i = num;
//                userPrize.setState("2");
//                userPrize.setNum(i.longValue());
//                goods.setParams(new HashMap<String, Object>(){{
//                    put("num",i);
//                }});
//            }else {
//                userPrize.setNum(1L);
//                goods.setParams(new HashMap<String, Object>(){{
//                    put("num",1);
//                }});
//                userPrize.setState("0");
//            }
////            if ("0".equals(sysUser.getNovice()))userPrize.setIsNovice("0");//是新手奖品
//            userPrizeService.insertUserPrize(userPrize);
//        }else {
//            if ("0".equals(goods.getIsDebris())){
//                Integer i = num;
//                userPrize.setNum(userPrize.getNum()+i.longValue());
//                userPrize.setState("2");
//                goods.setParams(new HashMap<String, Object>(){{
//                    put("num",i);
//                }});
//            }else {
//                userPrize.setNum(userPrize.getNum()+1L);
//                goods.setParams(new HashMap<String, Object>(){{
//                    put("num",1);
//                }});
//                userPrize.setState("0");
//            }
//            userPrize.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
//            userPrize.setUpdateTime(new Date());
//            userPrizeService.updateUserPrize(userPrize);
//        }
//        return goods;
//    }


    //添加奖品
    private Goods addUserPrizeTwo(Goods goods,Integer num){
        SysUser sysUser = userService.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId());
            UserPrize userPrize = new UserPrize();
            userPrize.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            userPrize.setState("0");
            userPrize.setType(goods.getIsDebris());
            userPrize.setGoodsId(goods.getId());
//            userPrize.setCreateTime(new Date());
            userPrize.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserName());
            if ("0".equals(goods.getIsDebris())){
                Integer i = num;
                userPrize.setState("2");
                userPrize.setNum(i.longValue());
                goods.setParams(new HashMap<String, Object>(){{
                    put("num",i);
                }});
            }else {
                userPrize.setNum(1L);
                goods.setParams(new HashMap<String, Object>(){{
                    put("num",1);
                }});
                userPrize.setState("0");
            }
            userPrizeService.insertUserPrize(userPrize);
        return goods;
    }

    //根据场次编号获取碎片数量
    private Integer getDebrisNum(Integer fieldId){
        int temp = 0;
        switch (fieldId){
            case 1:
                temp = RandomUtil.randomInt(1,8);
                break;
            case 2:
                temp = RandomUtil.randomInt(8,16);
                break;
            case 3:
                temp = RandomUtil.randomInt(16,40);
                break;
            default:
                temp = 3;
                break;
        }
        return temp;
    }

    /**
     * 掉落物品
     * @param ids 奖励组编号集合
     * @return
     * */
    public LootReward weightAlgorithm(List<Long> ids){//权重算法
        List<RewardPackage> rewardPackages = rewardPackageMapper.selectRewardPackageAndLootReward(ids);
        UserVipLevel userVipLevel = userVipLevelMapper.getByUserIdUserVipLevel(SecurityUtils.getLoginUser().getUser().getUserId());//用户VIP等级
        Random random = new Random();
        RewardPackage rewardPackageByWeighed = getRewardPackageByWeighed(rewardPackages,userVipLevel.getLevel().intValue(), random);
        LootReward lr = selectRandomWeighted(rewardPackageByWeighed.getLootRewards(),userVipLevel.getLevel().intValue(), random);
        return lr;
    }


    //奖励掉落权重
    private LootReward selectRandomWeighted(List<LootReward> list,int vipLevel, Random random) {
        int totalWeight = 0;
        for (LootReward item : list) {
//            totalWeight += item.getDropRewardWeight()+vipLevel;
            totalWeight += getWeightByRarity(item.getRarityLevel(),item.getDropRewardWeight().intValue(),vipLevel);
        }

        int randomNum = random.nextInt(totalWeight);
        int currentSum = 0;
        for (LootReward item : list) {
//            currentSum += item.getDropRewardWeight()+vipLevel;
            currentSum += getWeightByRarity(item.getRarityLevel(),item.getDropRewardWeight().intValue(),vipLevel);
            if (randomNum < currentSum)
                return item;
        }
        return null; // 如果列表不为空，则不应到达此处
    }

    //奖励组权重
    private RewardPackage getRewardPackageByWeighed(List<RewardPackage> list,int vipLevel, Random random){
        int totalWeight = 0;
        for (RewardPackage item : list) {
//            totalWeight +=  item.getWeight()+vipLevel;
            totalWeight += getWeightByRarity(item.getRarityLevel(),item.getWeight().intValue(),vipLevel);
        }
        int randomNum = random.nextInt(totalWeight);
        int currentSum = 0;
        for (RewardPackage item : list) {
//            currentSum += item.getWeight()+vipLevel;
            currentSum += getWeightByRarity(item.getRarityLevel(),item.getWeight().intValue(),vipLevel);
            if (randomNum < currentSum)
                return item;
        }
        return null;
    }

    /**
     *
     * @param rarityLevel 稀有程度
     * @param weight 权重
     * @param vipLevel vip等级
     * */
    private int getWeightByRarity(String rarityLevel,int weight,int vipLevel){
        int value = 0;
        switch (rarityLevel){
            case "0"://普通物品默认添加3点权重,减去自身vip等级
                value = weight+3-vipLevel;
                if (value<=0) return 0;
                return value;
            case "1"://高级物品权重不做改变,添加自身vip等级
                value = weight+vipLevel;
                if (value<=0) return 0;
                return value;
            case "2"://稀有物品减去1点权重,添加自身vip等级
                value = weight - 1 + vipLevel;
                if (value<=0) return 0;
                return value;
            case "3"://传说物品减去2点权重,添加自身vip等级
                value = weight - 2 + vipLevel;
                if (value<=0) return 0;
                return value;
            case "4"://神话物品减去3点权重,添加自身vip等级
                value = weight - 3 + vipLevel;
                if (value<=0) return 0;
                return value;
            default:
                logger.error("稀有程度为{},数据异常!",rarityLevel);
                return 0;
        }
    }


    /**
     * 获取场次盲盒概率
     *
     * @param m 倍率m
     * @param userId 用户编号
     * @param fieldId 场次编号
     * @param fMId 盲盒编号
     *
     * */
    private Double getPi(Double m,Long userId,Integer fieldId,Long fMId){
        ShotsNum shotsNum = shotsNumMapper.selectShotsNumByUserId(userId, fieldId);
        Double raffleNum = 0.0;//抽奖次数
        if (!ObjectUtils.isEmpty(shotsNum)){
            raffleNum = shotsNum.getNum().doubleValue();
        }
        ProbabilityConfig probabilityConfig = probabilityConfigMapper.selectByFieldId(fMId, fieldId);
        Double Fn = probabilityConfig.getFn();//次数配置
        Double Oi = probabilityConfig.getOi();//Oi概率配置
        Double Ui = probabilityConfig.getUi();//Ui概率配置
        Double pi = 0.0;
        logger.info("抽奖次数:----->"+raffleNum);
        logger.info("Fn次数配置:----->"+Fn);
        logger.info("Oi概率配置:----->"+Oi);
        logger.info("Ui概率配置:----->"+Ui);
        if (raffleNum <= Fn){
            pi = Arith.mul(Oi,m);
        }else if (raffleNum > Fn){
            Double rFn = Arith.sub(raffleNum,Fn);
            Double mul = Arith.mul(rFn, Ui);
            Double add = Arith.add(Oi, mul);
            pi = Arith.mul(add,m);
        }
        if (pi >= 1)return 1.0;
        return pi;
    }

    /**
     * 获取盲盒中奖物品
     * @param m 倍率m
     * @param fieldId 场次编号
     * @param fMId 盲盒编号
     * */
    private Goods obtainGoods(Double m,Integer fieldId,Long fMId,List<Goods> goodsList){
        Double totalWeight = goodsList.stream().mapToDouble(goods ->Double.parseDouble(goods.getWeight())).sum();//总权重 ΣWj
        HashMap<Long, HashMap<String,Object>> map = new HashMap<>();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Double pi = getPi(m,user.getUserId(),fieldId,fMId);
        logger.info("不同场地的中奖概率Pi------>"+pi);
        for (Goods goods : goodsList) {
            //Pj = (Wi / ΣWj) * Pi(lv)
            Double pj = Arith.mul(Arith.div(Double.parseDouble(goods.getWeight()),totalWeight),pi);//每个物品中奖概率
            map.put(goods.getId(),new HashMap<String,Object>(){{
                put("goods",goods);
                put("pj",pj);
            }});
        }
        // 随机数判断是否中奖
        Double random = Math.random();//随机数
        logger.info("总权重--->"+totalWeight);
        logger.info("随机数----->"+random);
        Double cumulativeProbability = 0.0;
        for (Long key : map.keySet()){
            Goods goods = new ObjectMapper().convertValue(map.get(key).get("goods"), Goods.class);
            Double pj = Double.parseDouble(map.get(key).get("pj").toString());
            logger.info("物品id为:"+goods.getId()+" 中奖概率为:"+pj);
            cumulativeProbability += pj;
            if (cumulativeProbability > 1.0) {
                cumulativeProbability = 1.0; // 限制cumulativeProbability不超过1
            }
            logger.info("累计概率---->"+cumulativeProbability+"\n");
            if (random < cumulativeProbability) {
                logger.info("中奖!该物品中奖概率为:"+pj+" 物品权重为:"+goods.getWeight()+" 物品id为:"+goods.getId());
                return goods;
            }
        }
        logger.info("未中奖");
        return null;
    }

    /**
     * 根据场次编号获取盲盒
     * */
    private FishingMonster getByFieldId(Integer fieldId){
        List<FishingMonster> list = new ArrayList<>();
        switch (fieldId){
            case 1:
                list = fishingMonsterMapper.getByTall(0, 300);
                break;
            case 2:
                list = fishingMonsterMapper.getByTall(0, 1500);
                break;
            case 3:
                list = fishingMonsterMapper.getByTall(10,10000);
//            case 3:
//                list = fishingMonsterMapper.selectFishingMonsterList(new FishingMonster());
                break;
            default:
                list = fishingMonsterMapper.getByTall(0, 50);
                break;
        }
        FishingMonster byWeight = getByWeight(list, fieldId);
        if (ObjectUtils.isEmpty(byWeight)){
            byWeight = RandomUtil.randomEle(list);
        }
        return byWeight;
    }

    private FishingMonster getByWeight(List<FishingMonster> list,Integer fieldId){
        Double totalWeight = 0.0;
        switch (fieldId){
            case 1:
                totalWeight = list.stream().mapToDouble(fishingMonster ->fishingMonster.getWeightLow()).sum();//低级场总权重
                double random = Math.random() * totalWeight;
                double cumulativeWeight = 0.0;
                for (FishingMonster fishingMonster : list) {
                    cumulativeWeight +=fishingMonster.getWeightLow();
                    if (random < cumulativeWeight) {
                        return fishingMonster;
                    }
                }
                break;
            case 2:
                totalWeight = list.stream().mapToDouble(fishingMonster ->fishingMonster.getWeightCentre()).sum();//中级场总权重
                double r = Math.random() * totalWeight;
                double c = 0.0;
                for (FishingMonster fishingMonster : list) {
                    c +=fishingMonster.getWeightCentre();
                    if (r < c) {
                        return fishingMonster;
                    }
                }
                break;
            case 3:
                totalWeight = list.stream().mapToDouble(fishingMonster ->fishingMonster.getWeightTall()).sum();//高级场总权重
                double d= Math.random() * totalWeight;
                double l = 0.0;
                for (FishingMonster fishingMonster : list) {
                    l +=fishingMonster.getWeightTall();
                    if (d < l) {
                        return fishingMonster;
                    }
                }
                break;
            default:
                return null;
        }
        return null;
    }

    /**
     * 获取奖品 第四版
     * */
    private Goods obtainGoodsFour(List<Goods> goodsList){
        if (CollUtil.isEmpty(goodsList))return null;
        Double totalWeight = goodsList.stream().mapToDouble(goods ->Double.parseDouble(goods.getWeight())).sum();//总权重 ΣWj
        HashMap<Long, HashMap<String,Object>> map = new HashMap<>();
        for (Goods goods : goodsList) {
            //Pi = Wi  /  ΣWi
            Double pi = Arith.div(Double.parseDouble(goods.getWeight()),totalWeight);//每个物品中奖概率
            map.put(goods.getId(),new HashMap<String,Object>(){{
                put("goods",goods);
                put("pi",pi);
            }});
        }
        // 随机数判断是否中奖
        Double random = Math.random();//随机数
        logger.info("总权重ΣWi--->"+totalWeight);
        logger.info("随机数----->"+random);
        Double cumulativeProbability = 0.0;
        for (Long key : map.keySet()){
            Goods goods = new ObjectMapper().convertValue(map.get(key).get("goods"), Goods.class);
            Double pi = Double.parseDouble(map.get(key).get("pi").toString());
            logger.info("物品id为:"+goods.getId()+" 中奖概率为:"+pi);
            cumulativeProbability += pi;
            if (cumulativeProbability > 1.0) {
                cumulativeProbability = 1.0; // 限制cumulativeProbability不超过1
            }
            logger.info("累计概率---->"+cumulativeProbability);
            if (random < cumulativeProbability) {
                logger.info("中奖!该物品中奖概率为:"+pi+" 物品权重Wi为:"+goods.getWeight()+" 物品id为:"+goods.getId());
                return goods;
            }
        }
        logger.info("未中奖");
        return null;
    }

}
