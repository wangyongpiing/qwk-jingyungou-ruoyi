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
import com.ruoyi.qwk.domain.BlockheadUserRecord;
import com.ruoyi.qwk.service.IBlockheadUserRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 木头人游戏用户记录Controller
 *
 * @author cjf
 * @date 2023-12-23
 */
@RestController
@RequestMapping("/qwk/blockheadUserRecord")
public class BlockheadUserRecordController extends BaseController
{
    @Autowired
    private IBlockheadUserRecordService blockheadUserRecordService;

    /**
     * 查询木头人游戏用户记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlockheadUserRecord blockheadUserRecord)
    {
        startPage();
        List<BlockheadUserRecord> list = blockheadUserRecordService.selectBlockheadUserRecordList(blockheadUserRecord);
        return getDataTable(list);
    }

    /**
     * 导出木头人游戏用户记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:export')")
    @Log(title = "木头人游戏用户记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlockheadUserRecord blockheadUserRecord)
    {
        List<BlockheadUserRecord> list = blockheadUserRecordService.selectBlockheadUserRecordList(blockheadUserRecord);
        ExcelUtil<BlockheadUserRecord> util = new ExcelUtil<BlockheadUserRecord>(BlockheadUserRecord.class);
        util.exportExcel(response, list, "木头人游戏用户记录数据");
    }

    /**
     * 获取木头人游戏用户记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(blockheadUserRecordService.selectBlockheadUserRecordById(id));
    }

    /**
     * 新增木头人游戏用户记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:add')")
    @Log(title = "木头人游戏用户记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlockheadUserRecord blockheadUserRecord)
    {
        return toAjax(blockheadUserRecordService.insertBlockheadUserRecord(blockheadUserRecord));
    }

    /**
     * 修改木头人游戏用户记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:edit')")
    @Log(title = "木头人游戏用户记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlockheadUserRecord blockheadUserRecord)
    {
        return toAjax(blockheadUserRecordService.updateBlockheadUserRecord(blockheadUserRecord));
    }

    /**
     * 删除木头人游戏用户记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:record:remove')")
    @Log(title = "木头人游戏用户记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(blockheadUserRecordService.deleteBlockheadUserRecordByIds(ids));
    }
}
