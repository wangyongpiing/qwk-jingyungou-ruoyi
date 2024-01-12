package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.domain.SerialCardExchangeRecords;
import com.ruoyi.qwk.service.ISerialCardExchangeRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 序列卡兑换记录 Controller
 *
 * @author cjf
 * @date 2023-12-01
 */
@RestController
@RequestMapping("/qwk/serialCard/exchange/records")
@Api(tags = "序列卡兑换记录API")
public class SerialCardExchangeRecordsController extends BaseController
{
    @Autowired
    private ISerialCardExchangeRecordsService serialCardExchangeRecordsService;

    /**
     * 查询序列卡兑换记录 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(SerialCardExchangeRecords serialCardExchangeRecords)
    {
        startPage();
        List<SerialCardExchangeRecords> list = serialCardExchangeRecordsService.selectSerialCardExchangeRecordsList(serialCardExchangeRecords);
        return getDataTable(list);
    }

    /**
     * 导出序列卡兑换记录 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:export')")
    @Log(title = "序列卡兑换记录 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SerialCardExchangeRecords serialCardExchangeRecords)
    {
        List<SerialCardExchangeRecords> list = serialCardExchangeRecordsService.selectSerialCardExchangeRecordsList(serialCardExchangeRecords);
        ExcelUtil<SerialCardExchangeRecords> util = new ExcelUtil<SerialCardExchangeRecords>(SerialCardExchangeRecords.class);
        util.exportExcel(response, list, "序列卡兑换记录 数据");
    }

    /**
     * 获取序列卡兑换记录 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(serialCardExchangeRecordsService.selectSerialCardExchangeRecordsById(id));
    }

    /**
     * 新增序列卡兑换记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:add')")
    @Log(title = "序列卡兑换记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SerialCardExchangeRecords serialCardExchangeRecords)
    {
        return toAjax(serialCardExchangeRecordsService.insertSerialCardExchangeRecords(serialCardExchangeRecords));
    }

    /**
     * 修改序列卡兑换记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:edit')")
    @Log(title = "序列卡兑换记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SerialCardExchangeRecords serialCardExchangeRecords)
    {
        return toAjax(serialCardExchangeRecordsService.updateSerialCardExchangeRecords(serialCardExchangeRecords));
    }

    /**
     * 删除序列卡兑换记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:records:remove')")
    @Log(title = "序列卡兑换记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serialCardExchangeRecordsService.deleteSerialCardExchangeRecordsByIds(ids));
    }

    /**
     * App兑换序列卡
     * @param cardCode
     * @return
     */
    @ApiOperation(value="app序列卡兑换")
    @GetMapping(value = "/app/exchange")
    public AjaxResult exchangeCardApp(@RequestParam("cardCode") String cardCode,@RequestParam("userId") String userId){
        return serialCardExchangeRecordsService.exchangeCardApp(cardCode,userId);
    }
}
