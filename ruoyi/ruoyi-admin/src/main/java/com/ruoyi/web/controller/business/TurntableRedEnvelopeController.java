package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.qwk.domain.TurntableRedEnvelope;
import com.ruoyi.qwk.service.ITurntableRedEnvelopeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 转盘领取红包Controller
 *
 * @author cjf
 * @date 2023-12-20
 */
@RestController
@RequestMapping("/qwk/envelope")
public class TurntableRedEnvelopeController extends BaseController
{
    @Autowired
    private ITurntableRedEnvelopeService turntableRedEnvelopeService;

    /**
     * 查询转盘领取红包列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:envelope:list')")
    @GetMapping("/list")
    public TableDataInfo list(TurntableRedEnvelope turntableRedEnvelope)
    {
        startPage();
        List<TurntableRedEnvelope> list = turntableRedEnvelopeService.selectTurntableRedEnvelopeList(turntableRedEnvelope);
        return getDataTable(list);
    }

    /**
     * 导出转盘领取红包列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:envelope:export')")
    @Log(title = "转盘领取红包", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TurntableRedEnvelope turntableRedEnvelope)
    {
        List<TurntableRedEnvelope> list = turntableRedEnvelopeService.selectTurntableRedEnvelopeList(turntableRedEnvelope);
        ExcelUtil<TurntableRedEnvelope> util = new ExcelUtil<TurntableRedEnvelope>(TurntableRedEnvelope.class);
        util.exportExcel(response, list, "转盘领取红包数据");
    }

    /**
     * 获取转盘领取红包详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:envelope:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(turntableRedEnvelopeService.selectTurntableRedEnvelopeById(id));
    }

    /**
     * 新增转盘领取红包
     */
    @PreAuthorize("@ss.hasPermi('qwk:envelope:add')")
    @Log(title = "转盘领取红包", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TurntableRedEnvelope turntableRedEnvelope)
    {
        return toAjax(turntableRedEnvelopeService.insertTurntableRedEnvelope(turntableRedEnvelope));
    }

    /**
     * 修改转盘领取红包
     */
    @PreAuthorize("@ss.hasPermi('qwk:envelope:edit')")
    @Log(title = "转盘领取红包", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TurntableRedEnvelope turntableRedEnvelope)
    {
        return toAjax(turntableRedEnvelopeService.updateTurntableRedEnvelope(turntableRedEnvelope));
    }

    /**
     * 删除转盘领取红包
     */
    @PreAuthorize("@ss.hasPermi('qwk:envelope:remove')")
    @Log(title = "转盘领取红包", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(turntableRedEnvelopeService.deleteTurntableRedEnvelopeByIds(ids));
    }
}
