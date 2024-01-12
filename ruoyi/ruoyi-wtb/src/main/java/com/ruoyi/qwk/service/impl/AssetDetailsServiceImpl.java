package com.ruoyi.qwk.service.impl;

import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.ijpay.alipay.AliPayApi;
import com.ijpay.alipay.AliPayApiConfig;
import com.ijpay.alipay.AliPayApiConfigKit;
import com.ruoyi.common.alipay.AliPayBean;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.mapper.*;
import com.ruoyi.system.mapper.SysUserMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.service.IAssetDetailsService;


import javax.annotation.Resource;

/**
 * 资产详情 Service业务层处理
 *
 * @author cjf
 * @date 2023-06-30
 */
@Service
public class AssetDetailsServiceImpl implements IAssetDetailsService
{
    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private WarehouseDetailsMapper warehouseDetailsMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserAccountBalanceMapper userAccountBalanceMapper;

    @Resource
    private AliPayBean aliPayBean;

    @Autowired
    private TurntableRedEnvelopeRecordMapper turntableRedEnvelopeRecordMapper;

    @Autowired
    private AccountWithdrawalRecordMapper accountWithdrawalRecordMapper;

    @Autowired
    private AssistanceDrawNumberMapper assistanceDrawNumberMapper;


    /**
     * 查询资产详情
     *
     * @param id 资产详情 主键
     * @return 资产详情
     */
    @Override
    public AssetDetails selectAssetDetailsById(Long id)
    {
        return assetDetailsMapper.selectAssetDetailsById(id);
    }

    /**
     * 查询资产详情 列表
     *
     * @param assetDetails 资产详情
     * @return 资产详情
     */
    @Override
    public List<AssetDetails> selectAssetDetailsList(AssetDetails assetDetails)
    {
        return assetDetailsMapper.selectAssetDetailsList(assetDetails);
    }

    /**
     * 新增资产详情
     *
     * @param assetDetails 资产详情
     * @return 结果
     */
    @Override
    public int insertAssetDetails(AssetDetails assetDetails)
    {
        return assetDetailsMapper.insertAssetDetails(assetDetails);
    }

    /**
     * 修改资产详情
     *
     * @param assetDetails 资产详情
     * @return 结果
     */
    @Override
    public int updateAssetDetails(AssetDetails assetDetails)
    {
        return assetDetailsMapper.updateAssetDetails(assetDetails);
    }

    /**
     * 批量删除资产详情
     *
     * @param ids 需要删除的资产详情 主键
     * @return 结果
     */
    @Override
    public int deleteAssetDetailsByIds(Long[] ids)
    {
        return assetDetailsMapper.deleteAssetDetailsByIds(ids);
    }

    /**
     * 删除资产详情 信息
     *
     * @param id 资产详情 主键
     * @return 结果
     */
    @Override
    public int deleteAssetDetailsById(Long id)
    {
        return assetDetailsMapper.deleteAssetDetailsById(id);
    }

    /**
     * App兑换资产（电池兑换糖果，糖果兑换电池）
     * @param type 1-电池兑换糖果 2-糖果兑换电池
     * @param number 兑换数量
     * @return
     */
    @Override
    public AjaxResult exchangeProperty(String type,Long number){
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取个人资产信息
        UserAssets bo = new UserAssets();
        bo.setUserId(user.getUserId());
        List<UserAssets> assetsList = userAssetsMapper.selectUserAssetsList(bo);
        if(CollUtil.isEmpty(assetsList)){
            return AjaxResult.error("用户资产信息不存在");
        }
        if(number<1){
            return AjaxResult.error("兑换数量有误，无法兑换！");
        }

        return computationalAssets(type, number,assetsList,user);
    }

    /**
     * 计算兑换资产
     * @param type
     * @param number
     * @param assetsList
     * @return
     */
    public AjaxResult computationalAssets(String type,Long number,List<UserAssets> assetsList,SysUser user){
        //糖果信息
        List<UserAssets> candyList = assetsList.stream().filter(s-> 3==s.getType()).collect(Collectors.toList());
        //电池信息
        List<UserAssets> batteryList = assetsList.stream().filter(s-> 2==s.getType()).collect(Collectors.toList());
        //电池
        UserAssets battery = new UserAssets();
        //糖果
        UserAssets candy = new UserAssets();
        //电池数量
        Long batteryNum = 0L;
        //糖果数量
        Long candyNum = 0L;
        if(CollUtil.isNotEmpty(batteryList)){
            battery = batteryList.get(0);
            batteryNum = Long.parseLong(battery.getTotal());
        }
        if(CollUtil.isNotEmpty(candyList)){
            candy = candyList.get(0);
            candyNum = Long.parseLong(candy.getTotal());
        }
        //电池兑换糖果
        AssetDetails assetDetails = new AssetDetails();
        Long typeVal =0L;
        String notes = "";
        Long num = 0L;
        String addOrSubtract = "";
        if("1".equals(type)){
            if(CollUtil.isEmpty(batteryList) || batteryNum<number){
                return AjaxResult.error("数量不足，无法兑换！");
            }
            batteryNum-=number;
            num = number*100;
            candyNum+=(number*100);
            notes ="电池兑换糖果";
            typeVal = 2L;
            assetDetails.setAddOrSubtract("0");
            assetDetails.setRecords(String.valueOf(number));
            addOrSubtract = "1";
        //糖果兑换电池
        }else if("2".equals(type)){
            if(CollUtil.isEmpty(candyList)  || candyNum<number){
                return AjaxResult.error("数量不足，无法兑换！");
            }
            if(number/100<1){
                return AjaxResult.error("兑换数量有误，请重新输入！");
            }
            num = number;
            batteryNum+=number/100;
            candyNum-=number;
            typeVal = 3L;
            notes ="糖果兑换电池";
            assetDetails.setAddOrSubtract("1");
            assetDetails.setRecords(String.valueOf(number/100));
            addOrSubtract = "0";
        }
        battery = batteryList.get(0);
        candy = candyList.get(0);
        battery.setTotal(batteryNum.toString());
        candy.setTotal(candyNum.toString());
        //更新用户资产记录
        userAssetsMapper.updateUserAssets(battery);
        userAssetsMapper.updateUserAssets(candy);
        //新增资产变化记录
        assetDetails.setImage("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/images/1694419558989.jpg");
        assetDetails.setUserPhone(user.getPhonenumber());
        assetDetails.setUserId(user.getUserId());
        assetDetails.setBalance(battery.getTotal());
        assetDetails.setNotes(notes);
        assetDetails.setCreatedBy(user.getUserName());
        assetDetails.setUpdatedBy(user.getUserName());
        assetDetails.setType(typeVal);
        assetDetailsMapper.insertAssetDetails(assetDetails);
        AssetDetails candyAss = new AssetDetails();
        candyAss = assetDetails;
        candyAss.setId(null);
        candyAss.setAddOrSubtract(addOrSubtract);
        candyAss.setBalance(candy.getTotal());
        candyAss.setRecords(String.valueOf(num));
        candyAss.setNotes(notes);
        assetDetailsMapper.insertAssetDetails(candyAss);
        //更新用户仓库
        setWarehouse(user,battery);
        return AjaxResult.success("兑换成功");
    }

    /**
     * App赠送电池
     * @param phone
     * @param number
     * @return
     */
    public AjaxResult freeBatteries(String phone,Long number){
        //校验转赠数量
        if(number<10){
            return AjaxResult.error("转赠数量有误，请重新输入！");
        }
        //校验手机号是否存在
        SysUser friend = sysUserMapper.selectUserByPhone(phone);
        if(ObjUtil.isNull(friend)){
           return AjaxResult.error("手机号输入错误或该手机用户不存在！");
        }
        //获取好友电池
        UserAssets friendAssets= userAssetsMapper.selectByUserId(friend.getUserId(),2L);
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取用户电池
        UserAssets userAssets= userAssetsMapper.selectByUserId(user.getUserId(),2L);
        if(number>Long.parseLong(userAssets.getTotal())){
            return AjaxResult.error("电池数量不足，无法转赠！");
        }
        userAssets.setTotal(String.valueOf(Long.parseLong(userAssets.getTotal())-number));
        friendAssets.setTotal(String.valueOf(Long.parseLong(friendAssets.getTotal())+number));
        //更新用户,转赠好友资产
        userAssetsMapper.updateUserAssets(userAssets);
        userAssetsMapper.updateUserAssets(friendAssets);
        //新增资产记录表
        AssetDetails assetDetails = new AssetDetails();
        setAssetDetails(assetDetails,user,userAssets,number);
        assetDetailsMapper.insertAssetDetails(assetDetails);
        AssetDetails friendDetails = new AssetDetails();
        setAssetDetails(friendDetails,user,friendAssets,number);
        friendDetails.setId(null);
        friendDetails.setAddOrSubtract("1");
        assetDetailsMapper.insertAssetDetails(friendDetails);
        //更新用户仓库
        setWarehouse(user,userAssets);
        //更新好友仓库
        setWarehouse(user,friendAssets);
        return AjaxResult.success();
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
     * 赋值资产记录
     * @param assetDetails
     * @param user
     * @param userAssets
     * @param number
     * @return
     */
    public void setAssetDetails(AssetDetails assetDetails,SysUser user,UserAssets userAssets,Long number){
        assetDetails.setImage("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/images/1694419558989.jpg");
        assetDetails.setUserPhone(userAssets.getUserPhone());
        assetDetails.setUserId(userAssets.getUserId());
        assetDetails.setBalance(userAssets.getTotal());
        assetDetails.setNotes("转赠能量电池");
        assetDetails.setCreatedBy(user.getUserName());
        assetDetails.setUpdatedBy(user.getUserName());
        assetDetails.setType(4L);
        assetDetails.setAddOrSubtract("0");
        assetDetails.setRecords(String.valueOf(number));
    }

    /**
     * 获取钱包余额及明细记录
     * @return
     */
    public AjaxResult walletBalance(){
        Map<String,Object> map = new HashMap<>();
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取钱包余额
        UserAssets assets = userAssetsMapper.selectByUserId(user.getUserId(),4L);
        //获取明细记录
        AssetDetails detail = new AssetDetails();
        detail.setUserId(user.getUserId());
        List<Integer> typeList = Arrays.asList(13,14);
        detail.setParams(new HashMap<String, Object>(){{
            put("typeList",typeList);
        }});
        List<AssetDetails> detailList = new ArrayList<>();
        detailList = assetDetailsMapper.selectAssetDetailsList(detail);
        //获取抽奖红包记录
        TurntableRedEnvelopeRecord lope = new TurntableRedEnvelopeRecord();
        lope.setUserId(String.valueOf(user.getUserId()));
        lope.setSource("3");
        List<TurntableRedEnvelopeRecord> recordList = turntableRedEnvelopeRecordMapper.selectTurntableRedEnvelopeRecordList(lope);
        AssistanceDrawNumber dra = new AssistanceDrawNumber();
        dra.setAssistanceId(String.valueOf(user.getUserId()));
        List<AssistanceDrawNumber> assList = assistanceDrawNumberMapper.selectAssistanceDrawNumberList(dra);
        if(CollUtil.isNotEmpty(recordList) && !"0".equals(assList.get(0).getIsLotteryAllowed())){
            Double sumPrice = recordList.stream().mapToDouble(TurntableRedEnvelopeRecord::getMoney).sum();
            AssetDetails de = new AssetDetails();
            de.setAddOrSubtract("1");
            de.setUserId(user.getUserId());
            de.setType(15L);
            de.setNotes("幸运大转盘抽奖获得");
            de.setRecords("100");
            TurntableRedEnvelopeRecord red = recordList.get(recordList.size() - 1);
            de.setCreatedTime(red.getCreateTime());
            detailList.add(de);
            detailList.stream().sorted(Comparator.comparing(AssetDetails::getCreatedTime, Comparator.reverseOrder()));
        }
        map.put("walletBalance",assets.getTotal());
        map.put("detailList",detailList);
        return AjaxResult.success(map);
    }

    /**
     * 立即提现
     * @return
     */
    public AjaxResult immediateWithdrawal(String totalAmount){
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取钱包余额
        UserAssets assets = userAssetsMapper.selectByUserId(user.getUserId(),4L);
        if(StrUtil.isEmpty(totalAmount) || Double.parseDouble(totalAmount)<0){
            return AjaxResult.error("提现金额传参格式不正确！");
        }
        //获取今日提现记录
        AccountWithdrawalRecord draw = new AccountWithdrawalRecord();
        draw.setIsSuccess("0");
        draw.setUserId(String.valueOf(user.getUserId()));
        draw.setCreateTime(DateUtil.date());
        List<AccountWithdrawalRecord> drawList = accountWithdrawalRecordMapper.selectAccountWithdrawalRecordList(draw);
        if(CollUtil.isNotEmpty(drawList)){
            return AjaxResult.error("今日已提现！");
        }
        Double money = Double.parseDouble(assets.getTotal()) -Double.parseDouble(totalAmount);
        if(money < 0){
            return AjaxResult.error("钱包余额不在足，无法提现");
        }
        //获取支付宝账号信息
        UserAccountBalance balance = new UserAccountBalance();
        balance.setUserId(String.valueOf(user.getUserId()));
        List<UserAccountBalance> balanceList = userAccountBalanceMapper.selectUserAccountBalanceList(balance);
        balance = balanceList.get(0);
        if(StrUtil.isEmpty(balance.getAlipayAccount()) || StrUtil.isEmpty(balance.getAlipayAccountName())){
            return AjaxResult.error("请先绑定支付宝账号");
        }
        //转账
        //初始化方法
        AliPayApiConfigKit.putApiConfig(getApiConfig());
        AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
        model.setOutBizNo(StringUtils.getOutTradeNo());
        model.setPayeeType("ALIPAY_LOGONID");
        model.setPayeeAccount(balance.getAlipayAccount());
        model.setAmount(totalAmount);
        model.setPayerShowName("生肖宝提现");
        model.setPayerRealName("湖北仟万客网络科技有限公司");
        model.setRemark("生肖宝提现转账到支付宝");

        try {
            String str = AliPayApi.transferToResponse(model).getBody();
            JSONObject jsonObject = new JSONObject(str);
            JSONObject transferResponse = jsonObject.getJSONObject("alipay_fund_trans_toaccount_transfer_response");
            String code = transferResponse.getString("code");
            AccountWithdrawalRecord record = new AccountWithdrawalRecord();
            record.setUserId(String.valueOf(user.getUserId()));
            record.setType("1");
            record.setWithdrawalAmount(totalAmount);
            record.setRemark(str);
            if("10000".equals(code)){
                record.setIsSuccess("0");
                accountWithdrawalRecordMapper.insertAccountWithdrawalRecord(record);
                //更新钱包余额
                assets.setTotal(String.valueOf(money));
                userAssetsMapper.updateUserAssets(assets);
                //更新资产明细记录
                addAssetDetails(user,assets.getTotal(),100L,"0");
                return AjaxResult.success("提现成功");
            }
            String subMsg = transferResponse.getString("sub_msg");
            record.setIsSuccess("1");
            accountWithdrawalRecordMapper.insertAccountWithdrawalRecord(record);
            if("付款方余额不足".equals(subMsg)){
                subMsg = "系统繁忙，请稍后重试";
            }
            return AjaxResult.error(subMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 插入资产明细记录
     * @param user
     * @param total
     * @param number
     * @param addType
     */
    public void addAssetDetails(SysUser user,String total,Long number,String addType){
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setImage("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/images/1694419558989.jpg");
        assetDetails.setUserPhone(user.getPhonenumber());
        assetDetails.setUserId(user.getUserId());
        assetDetails.setBalance(total);
        assetDetails.setNotes("生肖宝钱包提现到支付宝");
        assetDetails.setCreatedBy(user.getUserName());
        assetDetails.setUpdatedBy(user.getUserName());
        assetDetails.setType(15L);
        assetDetails.setAddOrSubtract(addType);
        assetDetails.setRecords(String.valueOf(number));
        assetDetailsMapper.insertAssetDetails(assetDetails);
    }


    public AliPayApiConfig getApiConfig(){
        AliPayApiConfig aliPayApiConfig;
        try {
            aliPayApiConfig = AliPayApiConfigKit.getApiConfig(aliPayBean.getAppId());
        } catch (Exception e) {
            aliPayApiConfig = AliPayApiConfig.builder()
                    .setAppId(aliPayBean.getAppId())
                    .setAliPayPublicKey(aliPayBean.getPublicKey())
                    .setAppCertPath(aliPayBean.getAppCertPath())
                    .setAliPayCertPath(aliPayBean.getAliPayCertPath())
                    .setAliPayRootCertPath(aliPayBean.getAliPayRootCertPath())
                    .setCharset("UTF-8")
                    .setPrivateKey(aliPayBean.getPrivateKey())
                    .setServiceUrl(aliPayBean.getServerUrl())
                    .setSignType("RSA2")
                    // 普通公钥方式
                    .build();
            // 证书模式
//                    .buildByCert();

        }
        return aliPayApiConfig;
    }
}
