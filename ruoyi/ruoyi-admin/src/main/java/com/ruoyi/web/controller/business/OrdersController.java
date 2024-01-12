package com.ruoyi.web.controller.business;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.Orders;
import com.ruoyi.qwk.service.IOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品订单 Controller
 *
 * @author cjf
 * @date 2023-07-01
 */
@RestController
@RequestMapping("/qwk/orders")
@Api(tags = "订单API")
public class OrdersController extends BaseController
{
    @Autowired
    private IOrdersService ordersService;

    /**
     * 查询商品订单 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:orders:list')")
    @GetMapping("/list")
    public TableDataInfo list(Orders orders)
    {
        startPage();
        List<Orders> list = ordersService.selectOrdersList(orders);
        return getDataTable(list);
    }

    /**
     * 导出商品订单 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:orders:export')")
    @Log(title = "商品订单 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Orders orders)
    {
        List<Orders> list = ordersService.selectOrdersList(orders);
        ExcelUtil<Orders> util = new ExcelUtil<Orders>(Orders.class);
        util.exportExcel(response, list, "商品订单 数据");
    }

    /**
     * 获取商品订单 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:orders:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ordersService.selectOrdersById(id));
    }

    /**
     * 新增商品订单
     */
    @PreAuthorize("@ss.hasPermi('qwk:orders:add')")
    @Log(title = "商品订单 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Orders orders)
    {
        return toAjax(ordersService.insertOrders(orders));
    }

    /**
     * 修改商品订单
     */
    @PreAuthorize("@ss.hasPermi('qwk:orders:edit')")
    @Log(title = "商品订单 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Orders orders)
    {
        return toAjax(ordersService.updateOrders(orders));
    }

    /**
     * 删除商品订单
     */
    @PreAuthorize("@ss.hasPermi('qwk:orders:remove')")
    @Log(title = "商品订单 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ordersService.deleteOrdersByIds(ids));
    }

    /**
     * 根据订单编号获取商品订单 详细信息
     */
    @GetMapping(value = "/getOrdersByNumber/{id}")
    public AjaxResult getOrdersByNumber(@PathVariable("id") String id){
        return success(ordersService.selectOrdersByNumber(id));
    }

//    ------------------------------------app--------------------------------------------------
    /**
     * 查询商品订单 列表
     */
    @GetMapping("/getOrdersList")
    @ApiOperation(value = "查询商品订单列表")
    public AjaxResult getOrdersList(@ApiParam(value = "订单对象") Orders orders)
    {
        PageUtils.startPage();
        PageUtils.orderBy("o.CREATED_TIME DESC");
        List<Orders> list = ordersService.getOrdersList(orders);
        TableDataInfo dataTable = getDataTable(list);
        return AjaxResult.success(dataTable);
    }

    /**
     * 获取商品订单 详细信息
     */
    @ApiOperation(value = "获取商品订单详细信息")
    @GetMapping(value = "/getOrdersInfo/{id}")
    public AjaxResult getOrdersInfo(@ApiParam(value = "商品订单号")
                                        @PathVariable("id") String id){
        return success(ordersService.selectOrdersByNumber(id));
    }

    /**
     * 获取积分兑换订单列表
     */
    @ApiOperation(value = "获取积分兑换订单列表")
    @GetMapping(value = "/getByPaymentMethod")
    public AjaxResult getOrdersByPaymentMethod(){
        Orders orders = new Orders();
        orders.setPaymentMethod(1L);
        PageUtils.startPage();
        PageUtils.orderBy("o.CREATED_TIME DESC");
        List<Orders> list = ordersService.selectOrdersList(orders);
        return success(getDataTable(list));
    }


    /**
     * 积分兑换下单
     */
    @ApiOperation(value = "积分兑换下单")
    @PostMapping(value = "/placeOrder")
    @RepeatSubmit(interval = 1000, message = "请求过于频繁")
    public AjaxResult placeOrder(@RequestBody Orders orders){
        return ordersService.placeOrder(orders);
    }

    /**
     * 现金购买下单
     */
    @ApiOperation(value = "现金购买下单")
    @PostMapping(value = "/cashPurchase")
    public AjaxResult cashPurchase(@RequestBody Orders orders){
        orders.setPaymentMethod(2L);
        return ordersService.placeOrder(orders);
    }

    /**
     * 确认收货
     */
    @ApiOperation(value = "确认收货")
    @GetMapping(value = "/confirmReceipt/{id}")
    public AjaxResult confirmReceipt(@PathVariable("id")Long id){
        int i = ordersService.confirmReceipt(id);
        if (i>0){
            return success("订单已完成!");
        }else {
            return error("收货异常!");
        }
    }

    /**
     * 删除商品订单
     */
    @ApiOperation(value = "删除商品订单")
    @DeleteMapping("/delOrders/{id}")
    public AjaxResult delOrders(@PathVariable Long id){
        return toAjax(ordersService.deleteOrdersById(id));
    }

    /**
     * 仓库一键发货
     * @param wdId 仓库详情id
     * @param goodsId 商品id
     * @param tDAId 收货地址id
     * @return 结果
     */
    @ApiOperation(value = "仓库一键发货")
    @GetMapping(value = "/sendGoods/{wdId}/{goodsId}/{tDAId}")
    public AjaxResult sendGoods(@PathVariable("wdId") Long wdId,@PathVariable("goodsId") Long goodsId,@PathVariable("tDAId") Long tDAId){
        int i = ordersService.sendGoods(wdId, goodsId, tDAId);
        if (i>0){
            return success("订单已创建!");
        }else if (i == -1){
            return error("助力还差一人");
        }else {
            return error("发货异常!");
        }
    }

    /**
     * 根据用户id,状态查询用户订单条数
     *
     * @param userId 用户id
     * @return 结果
     */
    @ApiOperation(value = "根据状态查询用户订单条数")
    @GetMapping(value = "/getByCount/{userId}")
    public AjaxResult getByCount(@PathVariable("userId") Long userId){
        int i = ordersService.selectByStatusCount(userId, 1L);
        int i1 = ordersService.selectByStatusCount(userId, 2L);
        int i2 = ordersService.selectByStatusCount(userId, 3L);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("toBeShipped",i);
        map.put("takeOver",i1);
        map.put("complete",i2);
        return success(map);
    }

}
