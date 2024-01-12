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
import com.ruoyi.qwk.domain.AssistanceDrawNumber;
import com.ruoyi.qwk.service.IAssistanceDrawNumberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 *  助力抽奖次数Controller
 *
 * @author cjf
 * @date 2023-12-21
 */
@RestController
@RequestMapping("/qwk/number")
public class AssistanceDrawNumberController extends BaseController
{
    @Autowired
    private IAssistanceDrawNumberService assistanceDrawNumberService;

    /**
     * 查询 助力抽奖次数列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:number:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssistanceDrawNumber assistanceDrawNumber)
    {
        startPage();
        List<AssistanceDrawNumber> list = assistanceDrawNumberService.selectAssistanceDrawNumberList(assistanceDrawNumber);
        return getDataTable(list);
    }

    /**
     * 导出 助力抽奖次数列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:number:export')")
    @Log(title = " 助力抽奖次数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssistanceDrawNumber assistanceDrawNumber)
    {
        List<AssistanceDrawNumber> list = assistanceDrawNumberService.selectAssistanceDrawNumberList(assistanceDrawNumber);
        ExcelUtil<AssistanceDrawNumber> util = new ExcelUtil<AssistanceDrawNumber>(AssistanceDrawNumber.class);
        util.exportExcel(response, list, " 助力抽奖次数数据");
    }

    /**
     * 获取 助力抽奖次数详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:number:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assistanceDrawNumberService.selectAssistanceDrawNumberById(id));
    }

    /**
     * 新增 助力抽奖次数
     */
    @PreAuthorize("@ss.hasPermi('qwk:number:add')")
    @Log(title = " 助力抽奖次数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssistanceDrawNumber assistanceDrawNumber)
    {
        return toAjax(assistanceDrawNumberService.insertAssistanceDrawNumber(assistanceDrawNumber));
    }

    /**
     * 修改 助力抽奖次数
     */
    @PreAuthorize("@ss.hasPermi('qwk:number:edit')")
    @Log(title = " 助力抽奖次数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssistanceDrawNumber assistanceDrawNumber)
    {
        return toAjax(assistanceDrawNumberService.updateAssistanceDrawNumber(assistanceDrawNumber));
    }

    /**
     * 删除 助力抽奖次数
     */
    @PreAuthorize("@ss.hasPermi('qwk:number:remove')")
    @Log(title = " 助力抽奖次数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assistanceDrawNumberService.deleteAssistanceDrawNumberByIds(ids));
    }

    /**
     * APP获取抽奖剩余次数
     */
    @ApiOperation(value = "APP获取抽奖剩余次数")
    @GetMapping("/remainingTimes")
    public AjaxResult userAssistanceCount(){
        return assistanceDrawNumberService.userAssistanceCount();
    }
}
