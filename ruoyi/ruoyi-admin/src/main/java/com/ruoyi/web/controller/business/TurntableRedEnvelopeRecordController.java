package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.qwk.domain.TurntableRedEnvelopeRecord;
import com.ruoyi.qwk.service.ITurntableRedEnvelopeRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 转盘领取红包记录Controller
 *
 * @author cjf
 * @date 2023-12-20
 */
@RestController
@RequestMapping("/qwk/enveloperecord")
public class TurntableRedEnvelopeRecordController extends BaseController
{
    @Autowired
    private ITurntableRedEnvelopeRecordService turntableRedEnvelopeRecordService;

    /**
     * 查询转盘领取红包记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(TurntableRedEnvelopeRecord turntableRedEnvelopeRecord)
    {
        startPage();
        List<TurntableRedEnvelopeRecord> list = turntableRedEnvelopeRecordService.selectTurntableRedEnvelopeRecordList(turntableRedEnvelopeRecord);
        return getDataTable(list);
    }

    /**
     * 导出转盘领取红包记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:export')")
    @Log(title = "转盘领取红包记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TurntableRedEnvelopeRecord turntableRedEnvelopeRecord)
    {
        List<TurntableRedEnvelopeRecord> list = turntableRedEnvelopeRecordService.selectTurntableRedEnvelopeRecordList(turntableRedEnvelopeRecord);
        ExcelUtil<TurntableRedEnvelopeRecord> util = new ExcelUtil<TurntableRedEnvelopeRecord>(TurntableRedEnvelopeRecord.class);
        util.exportExcel(response, list, "转盘领取红包记录数据");
    }

    /**
     * 获取转盘领取红包记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(turntableRedEnvelopeRecordService.selectTurntableRedEnvelopeRecordById(id));
    }

    /**
     * 新增转盘领取红包记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:add')")
    @Log(title = "转盘领取红包记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TurntableRedEnvelopeRecord turntableRedEnvelopeRecord)
    {
        return toAjax(turntableRedEnvelopeRecordService.insertTurntableRedEnvelopeRecord(turntableRedEnvelopeRecord));
    }

    /**
     * 修改转盘领取红包记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:edit')")
    @Log(title = "转盘领取红包记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TurntableRedEnvelopeRecord turntableRedEnvelopeRecord)
    {
        return toAjax(turntableRedEnvelopeRecordService.updateTurntableRedEnvelopeRecord(turntableRedEnvelopeRecord));
    }

    /**
     * 删除转盘领取红包记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:remove')")
    @Log(title = "转盘领取红包记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(turntableRedEnvelopeRecordService.deleteTurntableRedEnvelopeRecordByIds(ids));
    }

    /**
     * 是否领取下载，注册红包
     * @return
     */
    @ApiOperation(value = "是否领取下载，注册红包")
    @GetMapping("/checkReceiveRedEnvelope")
    public AjaxResult checkReceiveRedEnvelope(){
         return turntableRedEnvelopeRecordService.checkReceiveRedEnvelope();
    }

    /**
     * APP领取下载，注册红包
     * @return
     */
    @ApiOperation(value = "APP领取下载，注册红包")
    @GetMapping("/receiveRedEnvelope")
    public AjaxResult receiveRedEnvelope(){
        return turntableRedEnvelopeRecordService.receiveRedEnvelope();
    }
}
