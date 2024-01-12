package com.ruoyi.web.controller.business;

import com.beust.ah.A;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.AssetDetails;
import com.ruoyi.qwk.service.IAssetDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 资产详情 Controller
 *
 * @author cjf
 * @date 2023-06-30
 */
@RestController
@RequestMapping("/qwk/assetDetails")
@Api(tags = "用户资产详情API")
public class AssetDetailsController extends BaseController
{
    @Autowired
    private IAssetDetailsService assetDetailsService;

    /**
     * 查询资产详情 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:assetDetails:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetDetails assetDetails)
    {
        startPage();
        List<AssetDetails> list = assetDetailsService.selectAssetDetailsList(assetDetails);
        return getDataTable(list);
    }

    /**
     * 导出资产详情 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:assetDetails:export')")
    @Log(title = "资产详情 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssetDetails assetDetails)
    {
        List<AssetDetails> list = assetDetailsService.selectAssetDetailsList(assetDetails);
        ExcelUtil<AssetDetails> util = new ExcelUtil<AssetDetails>(AssetDetails.class);
        util.exportExcel(response, list, "资产详情 数据");
    }

    /**
     * 获取资产详情 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:assetDetails:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assetDetailsService.selectAssetDetailsById(id));
    }

    /**
     * 新增资产详情
     */
    @PreAuthorize("@ss.hasPermi('qwk:assetDetails:add')")
    @Log(title = "资产详情 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetDetails assetDetails)
    {
        return toAjax(assetDetailsService.insertAssetDetails(assetDetails));
    }

    /**
     * 修改资产详情
     */
    @PreAuthorize("@ss.hasPermi('qwk:assetDetails:edit')")
    @Log(title = "资产详情 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetDetails assetDetails)
    {
        return toAjax(assetDetailsService.updateAssetDetails(assetDetails));
    }

    /**
     * 删除资产详情
     */
    @PreAuthorize("@ss.hasPermi('qwk:assetDetails:remove')")
    @Log(title = "资产详情 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetDetailsService.deleteAssetDetailsByIds(ids));
    }

    /**
     * App获取资产详情 详细信息
     */
    @GetMapping(value = "/getAssetDetailsInfo")
    @ApiOperation(value = "获取用户资产变更明细")
    public AjaxResult getAssetDetailsInfo(AssetDetails assetDetails){
        //用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        startPage();
        assetDetails.setUserId(user.getUserId());
        List<AssetDetails> list = assetDetailsService.selectAssetDetailsList(assetDetails);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * App兑换资产（电池兑换糖果，糖果兑换电池）
     * @param type 1-电池兑换糖果 2-糖果兑换电池
     * @param number 兑换数量
     * @return
     */
    @GetMapping(value = "/exchange")
    @ApiOperation(value = "App兑换资产（电池兑换糖果，糖果兑换电池）")
    public AjaxResult exchangeProperty(@RequestParam("type") String type,@RequestParam("number") Long number){
        return assetDetailsService.exchangeProperty(type,number);
    }

    /**
     * App赠送电池
     * @param phone
     * @param number
     * @return
     */
    @GetMapping(value = "/freeBatteries")
    @ApiOperation(value = "App赠送电池")
    public AjaxResult freeBatteries(@RequestParam("phone") String phone,@RequestParam("number") Long number){
        return assetDetailsService.freeBatteries(phone,number);
    }

    /**
     * 获取钱包余额及明细记录
     * @return
     */
    @GetMapping("/walletBalance")
    @ApiOperation(value = "获取钱包余额及明细记录")
    public AjaxResult walletBalance(){
        return assetDetailsService.walletBalance();
    }

    /**
     * 立即提现
     * @return
     */
    @GetMapping("/immediateWithdrawal")
    @ApiOperation(value = "立即提现")
    public AjaxResult immediateWithdrawal(@RequestParam("totalAmount") String totalAmount){
        return assetDetailsService.immediateWithdrawal(totalAmount);
    }
}
