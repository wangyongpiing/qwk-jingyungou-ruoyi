package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.PropRecord;
import com.ruoyi.qwk.service.IPropRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 道具产出记录Controller
 *
 * @author cjf
 * @date 2023-07-31
 */
@RestController
@RequestMapping("/qwk/record")
public class PropRecordController extends BaseController
{
    @Autowired
    private IPropRecordService propRecordService;

    /**
     * 查询道具产出记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(PropRecord propRecord)
    {
        startPage();
        List<PropRecord> list = propRecordService.selectPropRecordList(propRecord);
        return getDataTable(list);
    }

    /**
     * 导出道具产出记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:export')")
    @Log(title = "道具产出记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PropRecord propRecord)
    {
        List<PropRecord> list = propRecordService.selectPropRecordList(propRecord);
        ExcelUtil<PropRecord> util = new ExcelUtil<PropRecord>(PropRecord.class);
        util.exportExcel(response, list, "道具产出记录数据");
    }

    /**
     * 获取道具产出记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(propRecordService.selectPropRecordById(id));
    }

    /**
     * 新增道具产出记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:add')")
    @Log(title = "道具产出记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PropRecord propRecord)
    {
        return toAjax(propRecordService.insertPropRecord(propRecord));
    }

    /**
     * 修改道具产出记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:edit')")
    @Log(title = "道具产出记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PropRecord propRecord)
    {
        return toAjax(propRecordService.updatePropRecord(propRecord));
    }

    /**
     * 删除道具产出记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:remove')")
    @Log(title = "道具产出记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(propRecordService.deletePropRecordByIds(ids));
    }
}
