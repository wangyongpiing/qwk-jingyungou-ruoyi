package com.ruoyi.qwk.service.impl;

import java.util.*;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.mapper.*;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.service.IWarehouseDetailsService;
import org.springframework.util.ObjectUtils;

/**
 * 仓库详情 Service业务层处理
 * 
 * @author cjf
 * @date 2023-06-29
 */
@Service
public class WarehouseDetailsServiceImpl implements IWarehouseDetailsService 
{
    @Autowired
    private WarehouseDetailsMapper warehouseDetailsMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PropProbabilityMapper propProbabilityMapper;

    @Autowired
    private PropRecordMapper propRecordMapper;

    /**
     * 查询仓库详情 
     * 
     * @param id 仓库详情 主键
     * @return 仓库详情 
     */
    @Override
    public WarehouseDetails selectWarehouseDetailsById(Long id)
    {
        return warehouseDetailsMapper.selectWarehouseDetailsById(id);
    }

    /**
     * 查询仓库详情 列表
     * 
     * @param warehouseDetails 仓库详情 
     * @return 仓库详情 
     */
    @Override
    public List<WarehouseDetails> selectWarehouseDetailsList(WarehouseDetails warehouseDetails)
    {
        return warehouseDetailsMapper.selectWarehouseDetailsList(warehouseDetails);
    }

    /**
     * 新增仓库详情 
     * 
     * @param warehouseDetails 仓库详情 
     * @return 结果
     */
    @Override
    public int insertWarehouseDetails(WarehouseDetails warehouseDetails)
    {
        return warehouseDetailsMapper.insertWarehouseDetails(warehouseDetails);
    }

    /**
     * 修改仓库详情 
     * 
     * @param warehouseDetails 仓库详情 
     * @return 结果
     */
    @Override
    public int updateWarehouseDetails(WarehouseDetails warehouseDetails)
    {
        return warehouseDetailsMapper.updateWarehouseDetails(warehouseDetails);
    }

    /**
     * 批量删除仓库详情 
     * 
     * @param ids 需要删除的仓库详情 主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseDetailsByIds(Long[] ids)
    {
        return warehouseDetailsMapper.deleteWarehouseDetailsByIds(ids);
    }

    /**
     * 删除仓库详情 信息
     * 
     * @param id 仓库详情 主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseDetailsById(Long id)
    {
        return warehouseDetailsMapper.deleteWarehouseDetailsById(id);
    }

    /**
     * 兑换积分
     *
     * @param wd 仓库详情
     * @return 结果
     */
    @Override
    public AjaxResult exchange(WarehouseDetails wd){
        int value = 0;
        Date date = new Date();
        WarehouseDetails warehouseDetails = warehouseDetailsMapper.selectWarehouseDetailsById(wd.getId());
        if (ObjectUtils.isEmpty(warehouseDetails))return AjaxResult.error("物品不存在,请刷新后重试");
        int quantity = warehouseDetails.getQuantity().intValue();
        int wdQuantity = wd.getQuantity().intValue();
        if (quantity<wdQuantity){
            return AjaxResult.error("库存不足!");//兑换数量小于库存数量
        }
//        warehouseDetails.setState(1L);
        warehouseDetails.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
        warehouseDetails.setUpdateTime(date);
        UserAssets userAssets = userAssetsMapper.selectByUserId(warehouseDetails.getUserId(), 1L);
        userAssets.setUserId(warehouseDetails.getUserId());
        Double temp = (Double.parseDouble(warehouseDetails.getPrice())) * wdQuantity;
        Double redemptionPoints =  temp - (temp*0.1);//扣除手续费 10%
        Double total = (Double.parseDouble(userAssets.getTotal())) + redemptionPoints;
        userAssets.setTotal(StringUtils.doubleTrans(total));
        userAssets.setUpdatedBy(SecurityUtils.getUsername());
        userAssets.setUpdatedTime(date);
        value += userAssetsMapper.updateUserAssetsByUserId(userAssets);
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setUserId(warehouseDetails.getUserId());
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        assetDetails.setUserPhone(sysUser.getPhonenumber());
        assetDetails.setType(1L);
        assetDetails.setAddOrSubtract("1");
        assetDetails.setImage(warehouseDetails.getGoodsImage());
        assetDetails.setRecords(StringUtils.doubleTrans(redemptionPoints));
        assetDetails.setBalance(StringUtils.doubleTrans(total));
        assetDetails.setNotes("商品兑换积分");
        assetDetails.setCreatedBy(SecurityUtils.getLoginUser().getUser().getUserName());
        assetDetails.setCreatedTime(date);
        value += assetDetailsMapper.insertAssetDetails(assetDetails);
        Integer residue = quantity - wdQuantity;//剩余数量
        if (residue<=0){
            value += warehouseDetailsMapper.deleteWarehouseDetailsById(wd.getId());
        }
        warehouseDetails.setQuantity(residue.longValue());
        value += warehouseDetailsMapper.updateWarehouseDetails(warehouseDetails);
        if (value<=0){
            return AjaxResult.error("兑换异常!");
        }
        return AjaxResult.success();
    }

    /**
     * 使用道具
     *
     * @param id 仓库详情 主键
     * @return 结果
     */
    @Override
    public int useProps(Long id){
        WarehouseDetails warehouseDetails = warehouseDetailsMapper.selectWarehouseDetailsById(id);
        Integer quantity = warehouseDetails.getQuantity().intValue()-1;
        if (quantity <=0){
            return warehouseDetailsMapper.deleteWarehouseDetailsById(id);
        }
        warehouseDetails.setQuantity(Long.parseLong(quantity.toString()));
        warehouseDetails.setState(4L);
        return warehouseDetailsMapper.updateWarehouseDetails(warehouseDetails);
    }

    /**
     * 是否掉落道具
     *
     * @param userId 用户id
     * @param gameId 游戏id
     * @param num 关卡数
     * @return 结果
     */
    @Override
    public int ifFallen(Long userId,Long gameId,Long num){

        PropProbability probability = propProbabilityMapper.selectPropProbabilityById(1L);
        int pNum = probability.getProbability().intValue();
        pNum = num.intValue()*10 + pNum;
        if (!probability(pNum))return 0;
        int i = 0;
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        Warehouse warehouse = warehouseMapper.selectWarehouseByUserId(userId);
        if (ObjectUtils.isEmpty(warehouse)){
            warehouse = new Warehouse();
            warehouse.setUserId(userId);
            warehouse.setNickName(sysUser.getNickName());
            warehouse.setUserPhone(sysUser.getPhonenumber());
            warehouse.setQuantity(1L);
            i+=warehouseMapper.insertWarehouse(warehouse);
        }else {
            warehouse.setQuantity(warehouse.getQuantity()+1);
            warehouse.setUpdatedBy(sysUser.getUserName());
            warehouse.setUpdatedTime(new Date());
            i+=warehouseMapper.updateWarehouse(warehouse);
        }
        WarehouseDetails warehouseDetails = new WarehouseDetails();
        warehouseDetails.setType("2");
        warehouseDetails.setUserId(userId);
        List<WarehouseDetails> details = warehouseDetailsMapper.selectWarehouseDetailsList(warehouseDetails);
        if (details.size()>0){
            WarehouseDetails value = details.get(0);
            value.setQuantity(value.getQuantity()+1);
            i+=warehouseDetailsMapper.updateWarehouseDetails(value);
        }else {
            warehouseDetails.setQuantity(1L);
            warehouseDetails.setWid(warehouse.getId());
            warehouseDetails.setGoodsImage("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/images/1694419558989.jpg");
            warehouseDetails.setGoodsName("游戏道具");
            warehouseDetails.setState(0L);
            warehouseDetails.setSource("游戏获取");
            warehouseDetails.setCreateBy(sysUser.getUserName());
            warehouseDetails.setCreateTime(new Date());
            i+=warehouseDetailsMapper.insertWarehouseDetails(warehouseDetails);
        }
        if (i<=0)return 0;

        PropRecord propRecord = new PropRecord();
        propRecord.setGameId(gameId.longValue());
        propRecord.setGameLevel(num.longValue());
        propRecord.setPropId(1L);
        propRecord.setPropNum(1L);
        propRecord.setCreateBy(sysUser.getUserName());
        propRecord.setCreateTime(new Date());
        i+=propRecordMapper.insertPropRecord(propRecord);
        return i;
    }

    /**
     * 转赠好友
     *
     * @param wDId 仓库详情id 主键
     * @param userId 好友id
     * @return 结果
     */
    @Override
    public int transferFriends(Long wDId,String userId){
        int temp = 0;
        SysUser sysUser = userMapper.selectUserByUserName(userId);//被转赠好友对象
        WarehouseDetails warehouseDetails = this.selectWarehouseDetailsById(wDId);//操作者的仓库详情
        if ("0".equals(warehouseDetails.getIsNovice()))return -1;
        if(!warehouseDetails.getUserId().equals(SecurityUtils.getUserId())){
            return 0;
        }
        Warehouse warehouse = this.warehouseMapper.selectWarehouseById(warehouseDetails.getWid());//操作者的仓库
        Warehouse wByUserId = warehouseMapper.selectWarehouseByUserId(sysUser.getUserId());//转赠好友的仓库
        if (ObjectUtil.isNull(wByUserId)){
            wByUserId = new Warehouse();
            wByUserId.setTotalPrice("0");
            wByUserId.setQuantity(0L);
            wByUserId.setUserId(sysUser.getUserId());
            wByUserId.setNickName(sysUser.getNickName());
            wByUserId.setUserPhone(sysUser.getPhonenumber());
            warehouseMapper.insertWarehouse(wByUserId);
        }
        WarehouseDetails wdByUser = warehouseDetails;
        int i = warehouseDetails.getQuantity().intValue() - 1;
        if (i<=0){
//            warehouseDetails.setQuantity(0L);
//            warehouseDetails.setState(2L);//设置已转赠
            temp+=warehouseDetailsMapper.deleteWarehouseDetailsById(warehouseDetails.getId());
        }else {
            warehouseDetails.setQuantity(Long.parseLong(i+""));
        }
        wByUserId.setQuantity(Long.parseLong((wByUserId.getQuantity().intValue()+1)+""));
        switch (warehouseDetails.getType()){
            case "1":
                int wTotalPrice = Integer.parseInt(warehouse.getTotalPrice());//商品总价
                int wdPrice = Integer.parseInt(warehouseDetails.getPrice());//商品价格
                warehouse.setTotalPrice((wTotalPrice-wdPrice)+"");
                int wByUserTotalPrice = Integer.parseInt(wByUserId.getTotalPrice());//商品总价
                warehouse.setTotalPrice((wByUserTotalPrice+wdPrice)+"");
                break;
            case "2":
                break;
        }
        Date date = new Date();
        warehouseDetails.setUpdateTime(date);
        warehouseDetails.setUpdateBy(SecurityUtils.getUsername());
        temp+=warehouseDetailsMapper.updateWarehouseDetails(warehouseDetails);
        temp+=warehouseMapper.updateWarehouse(warehouse);
        wByUserId.setUpdatedTime(date);
        wByUserId.setUpdatedBy(SecurityUtils.getUsername());
        temp+=warehouseMapper.updateWarehouse(wByUserId);
        WarehouseDetails byGoodsId = warehouseDetailsMapper.getByGoodsId(sysUser.getUserId(), warehouseDetails.getGoodsId());
        if (ObjectUtil.isNull(byGoodsId)){
            wdByUser.setId(null);
            wdByUser.setUserId(sysUser.getUserId());
            wdByUser.setQuantity(1L);
            wdByUser.setUpdateBy(null);
            wdByUser.setUpdateTime(null);
            wdByUser.setState(0L);
            wdByUser.setWid(wByUserId.getId());
            wdByUser.setOrdersId(null);
            wdByUser.setCreateTime(date);
            wdByUser.setCreateBy(SecurityUtils.getUsername());
            wdByUser.setSource("好友"+SecurityUtils.getUsername()+"赠送");
            temp+=warehouseDetailsMapper.insertWarehouseDetails(wdByUser);
        }else {
            byGoodsId.setQuantity(Long.parseLong((byGoodsId.getQuantity().intValue()+1)+""));
            byGoodsId.setSource("好友"+SecurityUtils.getUsername()+"赠送");
            temp+=warehouseDetailsMapper.updateWarehouseDetails(byGoodsId);
        }

        return temp;
    }

    /**
     * 好友助力
     *
     * @param wDId 仓库详情id
     * */
    @Override
    public int addAssistanceNum(Long wDId){
        WarehouseDetails warehouseDetails = warehouseDetailsMapper.selectWarehouseDetailsById(wDId);
        warehouseDetails.setAssistanceNum(warehouseDetails.getAssistanceNum()+1);
        warehouseDetails.setUpdateTime(new Date());
        warehouseDetails.setUpdateBy("好友助力");
        return warehouseDetailsMapper.updateWarehouseDetails(warehouseDetails);
    }


    /**
     * 兑换糖果
     * */
    @Override
    public AjaxResult exchangeCandy(HashMap<String,Object> map){
        if (!map.containsKey("num"))return AjaxResult.error("缺少必要参数");
//        Long wDId = Long.parseLong(map.get("wDId").toString());// 仓库编号
        Integer num = Integer.parseInt(map.get("num").toString());// 数量
        if (num%100 != 0)return AjaxResult.error("兑换数量异常");
        WarehouseDetails details = new WarehouseDetails();
        details.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        details.setVirtualProductType("1");
        WarehouseDetails warehouseDetails = warehouseDetailsMapper.getByVPType(details);
        if (ObjectUtils.isEmpty(warehouseDetails))return AjaxResult.error("对不起，您的能量不足");
        if (!"1".equals(warehouseDetails.getVirtualProductType()))return AjaxResult.error("该物品无法兑换糖果");
        Integer temp = num /100;
        if (warehouseDetails.getQuantity().intValue() < temp)return AjaxResult.error("对不起，您的能量不足");
        Date date = new Date();
        int i = 0;
        warehouseDetails.setQuantity(warehouseDetails.getQuantity()-temp);
        if ((warehouseDetails.getQuantity()-temp)<=0){
            i += warehouseDetailsMapper.deleteWarehouseDetailsById(warehouseDetails.getId());
        }else {
            warehouseDetails.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserName());
            warehouseDetails.setUpdateTime(date);
            i += warehouseDetailsMapper.updateWarehouseDetails(warehouseDetails);
        }
        UserAssets userAssets = userAssetsMapper.selectByUserId(SecurityUtils.getLoginUser().getUser().getUserId(), 3L);
        Double total = Double.parseDouble(userAssets.getTotal())+num;
        userAssets.setTotal(StringUtils.doubleTrans(total));
        userAssets.setUpdatedBy(SecurityUtils.getLoginUser().getUser().getUserName());
        userAssets.setCreatedTime(date);
        i += userAssetsMapper.updateUserAssets(userAssets);
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        assetDetails.setUserPhone(SecurityUtils.getLoginUser().getUser().getPhonenumber());
        assetDetails.setType(3L);
        assetDetails.setAddOrSubtract("1");
        assetDetails.setRecords(num.toString());
        assetDetails.setBalance(StringUtils.doubleTrans(total));
        assetDetails.setNotes("能量兑换糖果");
        assetDetails.setCreatedBy(SecurityUtils.getLoginUser().getUser().getUserName());
        assetDetails.setCreatedTime(date);
        assetDetails.setImage("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/images/1694419558989.jpg");
        i += assetDetailsMapper.insertAssetDetails(assetDetails);
        if (i<=0)return AjaxResult.error("兑换异常");
        return AjaxResult.success();
    }



    private boolean probability(Integer gl){
        int i = RandomUtil.randomInt(1, 100);
        Set<Integer> integers = RandomUtil.randomEleSet(getIdList(), gl);
        for (Integer integer : integers) {
            if (integer.equals(i))return true;
        }
        return false;
    }

    private List<Integer> getIdList(){
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 1; i <= 100 ; i++) {
            integers.add(i);
        }
        return integers;
    }

}
