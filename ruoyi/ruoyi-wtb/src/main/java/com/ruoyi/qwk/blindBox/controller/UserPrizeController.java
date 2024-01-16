package com.ruoyi.qwk.blindBox.controller;

import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.alipay.api.domain.MpcpromoGoodsList;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.qwk.domain.BatchInsertSerialCardType;
import com.ruoyi.qwk.domain.Goods;
import com.ruoyi.qwk.domain.SerialCard;
import com.ruoyi.qwk.service.IGoodsService;
import com.ruoyi.qwk.service.ISerialCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Not;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.blindBox.domain.UserPrize;
import com.ruoyi.qwk.blindBox.service.IUserPrizeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户盲盒爆出奖品Controller
 *
 * @author cjf
 * @date 2023-09-21
 */
@RestController
@RequestMapping("/qwk/userPrize")
@Api(tags = "用户盲盒奖品API")
public class UserPrizeController extends BaseController
{
    @Autowired
    private IUserPrizeService userPrizeService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ISerialCardService serialCardService;


    /**
     * 查询用户盲盒爆出奖品列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:userPrize:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserPrize userPrize)
    {
        startPage();
        List<UserPrize> list = userPrizeService.selectUserPrizeList(userPrize);
        return getDataTable(list);
    }

    /**
     * 导出用户盲盒爆出奖品列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:userPrize:export')")
    @Log(title = "用户盲盒爆出奖品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserPrize userPrize)
    {
        List<UserPrize> list = userPrizeService.selectUserPrizeList(userPrize);
        ExcelUtil<UserPrize> util = new ExcelUtil<UserPrize>(UserPrize.class);
        util.exportExcel(response, list, "用户盲盒爆出奖品数据");
    }

    /**
     * 获取用户盲盒爆出奖品详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:userPrize:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userPrizeService.selectUserPrizeById(id));
    }

    /**
     * 新增用户盲盒爆出奖品
     */
    @PreAuthorize("@ss.hasPermi('qwk:userPrize:add')")
    @Log(title = "用户盲盒爆出奖品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserPrize userPrize)
    {
        return toAjax(userPrizeService.insertUserPrize(userPrize));
    }

    /**
     * 修改用户盲盒爆出奖品
     */
    @PreAuthorize("@ss.hasPermi('qwk:userPrize:edit')")
    @Log(title = "用户盲盒爆出奖品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserPrize userPrize)
    {
        return toAjax(userPrizeService.updateUserPrize(userPrize));
    }

    /**
     * 删除用户盲盒爆出奖品
     */
    @PreAuthorize("@ss.hasPermi('qwk:userPrize:remove')")
    @Log(title = "用户盲盒爆出奖品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userPrizeService.deleteUserPrizeByIds(ids));
    }

//    ----------------------------------------------app--------------------------------------------------------------------

    /**
     * 查询用户盲盒爆出奖品列表
     */
    @GetMapping("/getUserPrizeList")
    @ApiOperation("查询用户盲盒爆出奖品列表")
    public AjaxResult getUserPrizeList(UserPrize userPrize) {
        PageUtils.startPage();
        PageUtils.orderBy("u.type desc,u.update desc");
        userPrize.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        userPrize.setParams(new HashMap<String, Object>(){{
            put("inState","1");
            put("isDebris","0");
        }});
        List<UserPrize> list = userPrizeService.selectUserPrizeList(userPrize);
//        //获取生肖卡
//        Goods goods = new Goods();
//        goods.setCategoryId(18L);
//        List<Goods> goodsList = goodsService.selectGoodsList(goods);
//        goodsList = goodsList.stream().sorted(Comparator.comparing(Goods::getCashPrice)).collect(Collectors.toList());
//        goodsList.forEach(s->{
//            UserPrize up = new UserPrize();
//            up.setGoodsId(s.getId());
//            up.setGoods(s);
//            up.setType("2");
//            list.add(up);
//        });
        TableDataInfo dataTable = getDataTable(list);
        return success(dataTable);
    }

    /**
     * 查询用户盲盒爆出奖品列表(盲盒游戏)
     */
    @GetMapping("/getUserPrizeList/all")
    @ApiOperation("查询用户盲盒爆出奖品列表(盲盒游戏)")

    public AjaxResult getUserPrizeListAll(UserPrize userPrize) {
        PageUtils.startPage();
        PageUtils.orderBy("u.update_time desc");
        userPrize.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
//        userPrize.setParams(new HashMap<String, Object>(){{
//            put("inState","1");
//        }});
        List<UserPrize> list = userPrizeService.selectUserPrizeList(userPrize);
        TableDataInfo dataTable = getDataTable(list);
        return success(dataTable);
    }

    /**
     * 领取奖品
     * */
    @PostMapping("/receivePrize")
    @ApiOperation("领取奖品")
    public AjaxResult receivePrize(@RequestBody UserPrize userPrize){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return userPrizeService.receivePrize(userId,userPrize.getId());
    }

    /**
     * 碎片兑换
     * */
    @PostMapping("/debrisExchange")
    @ApiOperation("碎片兑换")
    public AjaxResult debrisExchange(@RequestBody UserPrize userPrize){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return userPrizeService.debrisExchange(userId,userPrize.getId());
    }

    @GetMapping("/getLuckyList")
    @ApiOperation("获取幸运榜")
    public AjaxResult getLuckyList(){
        return userPrizeService.getLuckyList();
    }


    /**
     * 微信公众号端-批量生成序列卡
     * @param cardList
     * @return
     */
    @ApiOperation(value = "微信公众号端-批量生成序列卡")
    @PostMapping(value = "/batchInsertSerialCardWx")
    public AjaxResult batchInsertSerialCardWx(@RequestBody List<BatchInsertSerialCardType> cardList){
        return serialCardService.batchInsertSerialCardWx(cardList);
    }


    /**
     * 微信公众号端-查询生肖序列卡 列表
     */
    @GetMapping("/card/list")
    public AjaxResult list(SerialCard serialCard)
    {
        Map<String,Object> map = new HashMap<>();
        List<SerialCard> list = serialCardService.selectSerialCardList(serialCard);
        //未兑换
        List<SerialCard> notRedeemed = new ArrayList<>();
        //已兑换
        List<SerialCard> redeemed = new ArrayList<>();
        if(CollUtil.isNotEmpty(list)){
            //未兑换
            notRedeemed = list.stream().filter(s->"0".equals(s.getRedemptionStatus())).collect(Collectors.toList());
            //已兑换
            redeemed = list.stream().filter(s->"1".equals(s.getRedemptionStatus())).collect(Collectors.toList());
        }
        map.put("listCard",notRedeemed);
        map.put("notRedeemed",notRedeemed.size());
        map.put("redeemed",redeemed.size());
        return AjaxResult.success(map);
    }
}
