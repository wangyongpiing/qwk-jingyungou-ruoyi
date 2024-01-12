package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.PaymentChannels;
import com.ruoyi.qwk.service.IPaymentChannelsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 支付渠道Controller
 *
 * @author cjf
 * @date 2023-07-24
 */
@RestController
@RequestMapping("/qwk/channels")
@Api(tags = "支付渠道Api")
public class PaymentChannelsController extends BaseController
{
    @Autowired
    private IPaymentChannelsService paymentChannelsService;

    /**
     * 查询支付渠道列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:channels:list')")
    @GetMapping("/list")
    public TableDataInfo list(PaymentChannels paymentChannels)
    {
        startPage();
        List<PaymentChannels> list = paymentChannelsService.selectPaymentChannelsList(paymentChannels);
        return getDataTable(list);
    }

    /**
     * 导出支付渠道列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:channels:export')")
    @Log(title = "支付渠道", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaymentChannels paymentChannels)
    {
        List<PaymentChannels> list = paymentChannelsService.selectPaymentChannelsList(paymentChannels);
        ExcelUtil<PaymentChannels> util = new ExcelUtil<PaymentChannels>(PaymentChannels.class);
        util.exportExcel(response, list, "支付渠道数据");
    }

    /**
     * 获取支付渠道详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:channels:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(paymentChannelsService.selectPaymentChannelsById(id));
    }

    /**
     * 新增支付渠道
     */
    @PreAuthorize("@ss.hasPermi('qwk:channels:add')")
    @Log(title = "支付渠道", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaymentChannels paymentChannels)
    {
        return toAjax(paymentChannelsService.insertPaymentChannels(paymentChannels));
    }

    /**
     * 修改支付渠道
     */
    @PreAuthorize("@ss.hasPermi('qwk:channels:edit')")
    @Log(title = "支付渠道", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaymentChannels paymentChannels)
    {
        return toAjax(paymentChannelsService.updatePaymentChannels(paymentChannels));
    }

    /**
     * 删除支付渠道
     */
    @PreAuthorize("@ss.hasPermi('qwk:channels:remove')")
    @Log(title = "支付渠道", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(paymentChannelsService.deletePaymentChannelsByIds(ids));
    }

    /**
     * 查询所有启用的支付渠道
     *
     * @return 支付渠道集合
     */
    @ApiOperation("查询所有启用的支付渠道")
    @PreAuthorize("@ss.hasPermi('qwk:channels:list')")
    @GetMapping("/getByStatusList")
    public AjaxResult getByStatusList(){
        return success(paymentChannelsService.selectByStatusList());
    }

}
