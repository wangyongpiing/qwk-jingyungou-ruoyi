package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
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
import com.ruoyi.qwk.domain.LotteryRecord;
import com.ruoyi.qwk.service.ILotteryRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 抽奖记录 Controller
 *
 * @author cjf
 * @date 2023-12-21
 */
@RestController
@RequestMapping("/qwk/lotteryrecord")
public class LotteryRecordController extends BaseController
{
    @Autowired
    private ILotteryRecordService lotteryRecordService;

    /**
     * 查询抽奖记录 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(LotteryRecord lotteryRecord)
    {
        startPage();
        List<LotteryRecord> list = lotteryRecordService.selectLotteryRecordList(lotteryRecord);
        return getDataTable(list);
    }

    /**
     * 导出抽奖记录 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:export')")
    @Log(title = "抽奖记录 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LotteryRecord lotteryRecord)
    {
        List<LotteryRecord> list = lotteryRecordService.selectLotteryRecordList(lotteryRecord);
        ExcelUtil<LotteryRecord> util = new ExcelUtil<LotteryRecord>(LotteryRecord.class);
        util.exportExcel(response, list, "抽奖记录 数据");
    }

    /**
     * 获取抽奖记录 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(lotteryRecordService.selectLotteryRecordById(id));
    }

    /**
     * 新增抽奖记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:add')")
    @Log(title = "抽奖记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LotteryRecord lotteryRecord)
    {
        return toAjax(lotteryRecordService.insertLotteryRecord(lotteryRecord));
    }

    /**
     * 修改抽奖记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:edit')")
    @Log(title = "抽奖记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LotteryRecord lotteryRecord)
    {
        return toAjax(lotteryRecordService.updateLotteryRecord(lotteryRecord));
    }

    /**
     * 删除抽奖记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:remove')")
    @Log(title = "抽奖记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(lotteryRecordService.deleteLotteryRecordByIds(ids));
    }


    /**
     * 生肖大转盘抽奖
     * @return
     */
    @ApiOperation(value = "生肖大转盘抽奖")
    @GetMapping("/drawLottery")
    public AjaxResult drawLottery(){
        return lotteryRecordService.drawLottery();
    }
}
