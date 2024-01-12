package com.ruoyi.qwk.blindBox.controller;

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
import com.ruoyi.qwk.blindBox.domain.Jackpot;
import com.ruoyi.qwk.blindBox.service.IJackpotService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 奖池Controller
 * 
 * @author cjf
 * @date 2023-09-24
 */
@RestController
@RequestMapping("/qwk/jackpot")
public class JackpotController extends BaseController
{
    @Autowired
    private IJackpotService jackpotService;

//    /**
//     * 查询奖池列表
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:jackpot:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(Jackpot jackpot)
//    {
//        startPage();
//        List<Jackpot> list = jackpotService.selectJackpotList(jackpot);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出奖池列表
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:jackpot:export')")
//    @Log(title = "奖池", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, Jackpot jackpot)
//    {
//        List<Jackpot> list = jackpotService.selectJackpotList(jackpot);
//        ExcelUtil<Jackpot> util = new ExcelUtil<Jackpot>(Jackpot.class);
//        util.exportExcel(response, list, "奖池数据");
//    }
//
//    /**
//     * 获取奖池详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:jackpot:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id)
//    {
//        return success(jackpotService.selectJackpotById(id));
//    }
//
//    /**
//     * 新增奖池
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:jackpot:add')")
//    @Log(title = "奖池", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody Jackpot jackpot)
//    {
//        return toAjax(jackpotService.insertJackpot(jackpot));
//    }
//
//    /**
//     * 修改奖池
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:jackpot:edit')")
//    @Log(title = "奖池", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody Jackpot jackpot)
//    {
//        return toAjax(jackpotService.updateJackpot(jackpot));
//    }
//
//    /**
//     * 删除奖池
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:jackpot:remove')")
//    @Log(title = "奖池", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(jackpotService.deleteJackpotByIds(ids));
//    }
}
