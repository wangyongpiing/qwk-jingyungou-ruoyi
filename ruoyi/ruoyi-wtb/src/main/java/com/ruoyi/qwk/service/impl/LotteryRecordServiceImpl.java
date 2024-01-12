package com.ruoyi.qwk.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.RandomUtil;
import com.ruoyi.qwk.domain.*;
import com.ruoyi.qwk.mapper.*;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.service.ILotteryRecordService;

/**
 * 抽奖记录 Service业务层处理
 *
 * @author cjf
 * @date 2023-12-21
 */
@Service
public class LotteryRecordServiceImpl implements ILotteryRecordService
{
    @Autowired
    private LotteryRecordMapper lotteryRecordMapper;

    @Autowired
    private AssistanceDrawNumberMapper assistanceDrawNumberMapper;

    @Autowired
    private TurntableRedEnvelopeRecordMapper turntableRedEnvelopeRecordMapper;

    @Autowired
    private TurntableRedEnvelopeMapper turntableRedEnvelopeMapper;

    @Autowired
    private UserAccountBalanceMapper userAccountBalanceMapper;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    /**
     * 查询抽奖记录
     *
     * @param id 抽奖记录 主键
     * @return 抽奖记录
     */
    @Override
    public LotteryRecord selectLotteryRecordById(Long id)
    {
        return lotteryRecordMapper.selectLotteryRecordById(id);
    }

    /**
     * 查询抽奖记录 列表
     *
     * @param lotteryRecord 抽奖记录
     * @return 抽奖记录
     */
    @Override
    public List<LotteryRecord> selectLotteryRecordList(LotteryRecord lotteryRecord)
    {
        return lotteryRecordMapper.selectLotteryRecordList(lotteryRecord);
    }

    /**
     * 新增抽奖记录
     *
     * @param lotteryRecord 抽奖记录
     * @return 结果
     */
    @Override
    public int insertLotteryRecord(LotteryRecord lotteryRecord)
    {
        lotteryRecord.setCreateTime(DateUtils.getNowDate());
        return lotteryRecordMapper.insertLotteryRecord(lotteryRecord);
    }

    /**
     * 修改抽奖记录
     *
     * @param lotteryRecord 抽奖记录
     * @return 结果
     */
    @Override
    public int updateLotteryRecord(LotteryRecord lotteryRecord)
    {
        lotteryRecord.setUpdateTime(DateUtils.getNowDate());
        return lotteryRecordMapper.updateLotteryRecord(lotteryRecord);
    }

    /**
     * 批量删除抽奖记录
     *
     * @param ids 需要删除的抽奖记录 主键
     * @return 结果
     */
    @Override
    public int deleteLotteryRecordByIds(Long[] ids)
    {
        return lotteryRecordMapper.deleteLotteryRecordByIds(ids);
    }

    /**
     * 删除抽奖记录 信息
     *
     * @param id 抽奖记录 主键
     * @return 结果
     */
    @Override
    public int deleteLotteryRecordById(Long id)
    {
        return lotteryRecordMapper.deleteLotteryRecordById(id);
    }

    /**
     * 生肖大转盘抽奖
     * @return
     */
    @Override
    public AjaxResult drawLottery() {
        Map<String,Object> map = new HashMap<>();
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if(ObjUtil.isNull(user)){
            return AjaxResult.error("会话失效，请重新登录");
        }
        //获取用户抽奖信息
        AssistanceDrawNumber assistanceDrawNumber = new AssistanceDrawNumber();
        assistanceDrawNumber.setAssistanceId(String.valueOf(user.getUserId()));
        List<AssistanceDrawNumber> assList = assistanceDrawNumberMapper.selectAssistanceDrawNumberList(assistanceDrawNumber);
        if(CollUtil.isEmpty(assList) || assList.get(0).getNoLotteryDrawNum()==0){
            return AjaxResult.error("无法抽奖，请先获取抽奖机会！");
        }
        if("1".equals(assList.get(0).getIsLotteryAllowed())){
            return AjaxResult.error("您已领取百元红包，无法再次参与活动，敬请期待下次活动！\n" +
                    "感谢您的支持！");
        }
        AssistanceDrawNumber ass = assList.get(0);
        String remark = "";
        Double money = 0.00;
        //下标
        int index = 3;
        Random rand = new Random();
        //第一次抽奖必中5元红包
        if("1".equals(ass.getIsFree())){
            money = 5.00;
            index = 0;
            ass.setIsFree("0");
        }else{
            DecimalFormat df = new DecimalFormat("0.00");
            if(ass.getActualExtractionNum()>0){
                //生成0.5-2范围随机数
                money = RandomUtil.getRandomForDoubleBounded4(0.5,2);
                ass.setActualExtractionNum(ass.getActualExtractionNum()-1);
            }else if(ass.getVacuumingNum()>0){
                //生成0.01-0.05范围随机数
                money = RandomUtil.getRandomForDoubleBounded4(0.01,0.05);
                ass.setVacuumingNum(ass.getVacuumingNum()-1);
            }
            String result = df.format(money);
            money = Double.parseDouble(result);
        }
        ass.setNoLotteryDrawNum(ass.getNoLotteryDrawNum()-1);
        remark = "恭喜获得"+money+"元提现金额";
        //插入抽奖记录表
        LotteryRecord lottery = addLotteryRecord("1","3",remark,user);
        //插入领取红包详情记录
        addRedRecord(user, money, "3", remark,lottery.getId());
        //查询账户余额
        UserAccountBalance userAccountBalance = new UserAccountBalance();
        userAccountBalance.setUserId(String.valueOf(user.getUserId()));
        userAccountBalance.setStatus("0");
        List<UserAccountBalance> balanceList = userAccountBalanceMapper.selectUserAccountBalanceList(userAccountBalance);
        if(CollUtil.isEmpty(balanceList)){
            return AjaxResult.error("账户存在异常记录");
        }
        UserAccountBalance userAccount = balanceList.get(0);
        DecimalFormat df = new DecimalFormat("#0.00");
        Double sumBalance = userAccount.getAccountBalance()+money;
        Double balance = Double.parseDouble(df.format(sumBalance));
        map.put("balance",balance);
        //是否满足100提现条件
        if(balance-100>0){
            //查询钱包资产
            UserAssets userAssets = userAssetsMapper.selectByUserId(user.getUserId(),4L);
            //更新钱包资产
            userAssets.setTotal(String.valueOf(Double.parseDouble(userAssets.getTotal())+100));
            userAssetsMapper.updateUserAssets(userAssets);
            balance = balance-100;
            //插入资产明细记录
            addAssetDetails(user,String.valueOf(userAssets.getTotal()),100L,"1");
            addAssetDetails(user,String.valueOf(balance),100L,"0");
            ass.setIsLotteryAllowed("1");
        }
        assistanceDrawNumberMapper.updateAssistanceDrawNumber(ass);
        //更新账户余额
        userAccount.setAccountBalance(balance);
        userAccountBalanceMapper.updateUserAccountBalance(userAccount);
        if(money==2.00){
            index = 4;
        }
        map.put("redEnvelope",money);
        map.put("index",index);
        map.put("remainingTimes",ass.getNoLotteryDrawNum());
        return AjaxResult.success(map);
    }

    /**
     * 插入领取红包
     * @param user
     * @return
     */
    public TurntableRedEnvelope addTurntableRedEnvelope(SysUser user){
        TurntableRedEnvelope lope = new TurntableRedEnvelope();
        lope.setUserId(String.valueOf(user.getUserId()));
        lope.setUserHead(user.getAvatar());
        lope.setUserName(user.getUserName());
        lope.setIsReceive("1");
        turntableRedEnvelopeMapper.insertTurntableRedEnvelope(lope);
        return lope;
    }

    /**
     * 插入红包记录
     * @param user
     * @param money
     * @param source
     * @param remark
     * @param lottyId
     */
    public void addRedRecord(SysUser user, Double money, String source, String remark,Long lottyId){
        TurntableRedEnvelopeRecord record = new TurntableRedEnvelopeRecord();
        record.setLotteryId(String.valueOf(lottyId));
        record.setUserId(String.valueOf(user.getUserId()));
        record.setUserHead(user.getAvatar());
        record.setUserName(user.getPhonenumber());
        record.setIsReceive("1");
        record.setMoney(money);
        record.setSource(source);
        record.setRemark(remark);
        turntableRedEnvelopeRecordMapper.insertTurntableRedEnvelopeRecord(record);
    }

    /**
     * 插入抽奖记录表
     * @param gameId
     * @param type
     * @param remark
     * @param user
     */
    public LotteryRecord addLotteryRecord(String gameId,String type,String remark,SysUser user){
        LotteryRecord lotteryRecord = new LotteryRecord();
        lotteryRecord.setGameId(gameId);
        lotteryRecord.setUserId(String.valueOf(user.getUserId()));
        lotteryRecord.setUserName(user.getUserName());
        lotteryRecord.setIsHit("1");
        lotteryRecord.setType(type);
        lotteryRecord.setIsSend("1");
        lotteryRecord.setSendMsg(remark);
        lotteryRecord.setCreateBy(user.getUserName());
        lotteryRecord.setUpdateBy(user.getUserName());
        lotteryRecordMapper.insertLotteryRecord(lotteryRecord);
        return lotteryRecord;
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
        assetDetails.setNotes("账户余额提现到生肖宝钱包");
        assetDetails.setCreatedBy(user.getUserName());
        assetDetails.setUpdatedBy(user.getUserName());
        assetDetails.setType(12L);
        assetDetails.setAddOrSubtract(addType);
        assetDetails.setRecords(String.valueOf(number));
        assetDetailsMapper.insertAssetDetails(assetDetails);
    }
}
