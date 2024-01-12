package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
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
import com.ruoyi.qwk.domain.SerialCardOrderRecords;
import com.ruoyi.qwk.service.ISerialCardOrderRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 序列卡订单记录 Controller
 *
 * @author cjf
 * @date 2023-12-01
 */
@RestController
@RequestMapping("/qwk/serialcard/order/records")
@Api(tags = "序列卡订单记录API")
public class SerialCardOrderRecordsController extends BaseController
{
    @Autowired
    private ISerialCardOrderRecordsService serialCardOrderRecordsService;

    /**
     * 查询序列卡订单记录 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(SerialCardOrderRecords serialCardOrderRecords)
    {
        startPage();
        List<SerialCardOrderRecords> list = serialCardOrderRecordsService.selectSerialCardOrderRecordsList(serialCardOrderRecords);
        return getDataTable(list);
    }

    /**
     * 导出序列卡订单记录 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:export')")
    @Log(title = "序列卡订单记录 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SerialCardOrderRecords serialCardOrderRecords)
    {
        List<SerialCardOrderRecords> list = serialCardOrderRecordsService.selectSerialCardOrderRecordsList(serialCardOrderRecords);
        ExcelUtil<SerialCardOrderRecords> util = new ExcelUtil<SerialCardOrderRecords>(SerialCardOrderRecords.class);
        util.exportExcel(response, list, "序列卡订单记录 数据");
    }

    /**
     * 获取序列卡订单记录 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(serialCardOrderRecordsService.selectSerialCardOrderRecordsById(id));
    }

    /**
     * 新增序列卡订单记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:add')")
    @Log(title = "序列卡订单记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SerialCardOrderRecords serialCardOrderRecords)
    {
        return toAjax(serialCardOrderRecordsService.insertSerialCardOrderRecords(serialCardOrderRecords));
    }

    /**
     * 修改序列卡订单记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:edit')")
    @Log(title = "序列卡订单记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SerialCardOrderRecords serialCardOrderRecords)
    {
        return toAjax(serialCardOrderRecordsService.updateSerialCardOrderRecords(serialCardOrderRecords));
    }

    /**
     * 删除序列卡订单记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:remove')")
    @Log(title = "序列卡订单记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serialCardOrderRecordsService.deleteSerialCardOrderRecordsByIds(ids));
    }
}
