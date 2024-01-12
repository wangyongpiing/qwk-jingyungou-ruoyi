package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.TakeDeliveryAddress;
import com.ruoyi.qwk.service.ITakeDeliveryAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * takeDeliveryAddressController
 *
 * @author cjf
 * @date 2023-07-03
 */
@RestController
@RequestMapping("/qwk/takeDeliveryAddress")
@Api(tags = "收货地址API")
public class TakeDeliveryAddressController extends BaseController
{
    @Autowired
    private ITakeDeliveryAddressService takeDeliveryAddressService;

    /**
     * 查询收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:TakeDeliveryAddress:list')")
    @GetMapping("/list")
    public TableDataInfo list(TakeDeliveryAddress takeDeliveryAddress)
    {
        startPage();
        List<TakeDeliveryAddress> list = takeDeliveryAddressService.selectTakeDeliveryAddressList(takeDeliveryAddress);
        return getDataTable(list);
    }

    /**
     * 导出收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:TakeDeliveryAddress:export')")
    @Log(title = "收货地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TakeDeliveryAddress takeDeliveryAddress)
    {
        List<TakeDeliveryAddress> list = takeDeliveryAddressService.selectTakeDeliveryAddressList(takeDeliveryAddress);
        ExcelUtil<TakeDeliveryAddress> util = new ExcelUtil<TakeDeliveryAddress>(TakeDeliveryAddress.class);
        util.exportExcel(response, list, "收货地址数据");
    }

    /**
     * 获取收货地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:TakeDeliveryAddress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(takeDeliveryAddressService.selectTakeDeliveryAddressById(id));
    }

    /**
     * 新增收货地址
     */
    @PreAuthorize("@ss.hasPermi('qwk:TakeDeliveryAddress:add')")
    @Log(title = "收货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TakeDeliveryAddress takeDeliveryAddress)
    {
        return toAjax(takeDeliveryAddressService.insertTakeDeliveryAddress(takeDeliveryAddress));
    }

    /**
     * 修改收货地址
     */
    @PreAuthorize("@ss.hasPermi('qwk:TakeDeliveryAddress:edit')")
    @Log(title = "收货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TakeDeliveryAddress takeDeliveryAddress)
    {
        return toAjax(takeDeliveryAddressService.updateTakeDeliveryAddress(takeDeliveryAddress));
    }

    /**
     * 删除收货地址
     */
    @PreAuthorize("@ss.hasPermi('qwk:TakeDeliveryAddress:remove')")
    @Log(title = "收货地址", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(takeDeliveryAddressService.deleteTakeDeliveryAddressByIds(ids));
    }


//    ---------------------------------app-----------------------------------------------

    /**
     * 添加收货地址
     */
    @Log(title = "takeDeliveryAddress", businessType = BusinessType.INSERT)
    @PostMapping("/addTakeDeliveryAddress")
    @ApiOperation(value = "添加收货地址")
    public AjaxResult addTakeDeliveryAddress(@RequestBody TakeDeliveryAddress takeDeliveryAddress){
        return toAjax(takeDeliveryAddressService.insertTakeDeliveryAddress(takeDeliveryAddress));
    }

    /**
     * 查询收货地址列表
     */
    @GetMapping("/getList")
    @ApiOperation(value = "获取收货地址列表")
    public AjaxResult getTakeDeliveryAddressList(){
        TakeDeliveryAddress takeDeliveryAddress = new TakeDeliveryAddress();
        takeDeliveryAddress.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        startPage();
        List<TakeDeliveryAddress> list = takeDeliveryAddressService.selectTakeDeliveryAddressList(takeDeliveryAddress);
        return success(getDataTable(list));
    }


    /**
     * 删除收货地址
     */
    @Log(title = "takeDeliveryAddress", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除收货地址")
    public AjaxResult del(@ApiParam(name = "收货地址唯一id") @PathVariable("id") Long id){
        return toAjax(takeDeliveryAddressService.deleteTakeDeliveryAddressById(id));
    }

    /**
     * 修改收货地址
     */
    @Log(title = "收货地址", businessType = BusinessType.UPDATE)
    @PutMapping("/updateAddress")
    @ApiOperation(value = "修改收货地址")
    public AjaxResult updateAddress(@RequestBody TakeDeliveryAddress takeDeliveryAddress)
    {
        return toAjax(takeDeliveryAddressService.updateTakeDeliveryAddress(takeDeliveryAddress));
    }

    /**
     * 修改收货地址为默认地址
     */
    @Log(title = "收货地址", businessType = BusinessType.UPDATE)
    @PutMapping("/updateDefault/{id}")
    @ApiOperation(value = "修改收货地址")
    public AjaxResult updateDefault(@PathVariable("id")Long id){
        String time = DateUtils.getTime();
        int i = takeDeliveryAddressService.updateByNotDefault(SecurityUtils.getUsername(), time, SecurityUtils.getLoginUser().getUser().getUserId());
        i+=takeDeliveryAddressService.updateByDefault(SecurityUtils.getUsername(), time,id, SecurityUtils.getLoginUser().getUser().getUserId());
        return toAjax(i);
    }

}
