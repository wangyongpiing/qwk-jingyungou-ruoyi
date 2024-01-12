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
import com.ruoyi.qwk.domain.OperatorInformation;
import com.ruoyi.qwk.service.IOperatorInformationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 运营商信息 Controller
 *
 * @author cjf
 * @date 2023-12-01
 */
@RestController
@RequestMapping("/qwk/information")
public class OperatorInformationController extends BaseController
{
    @Autowired
    private IOperatorInformationService operatorInformationService;

    /**
     * 查询运营商信息 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:information:list')")
    @GetMapping("/list")
    public TableDataInfo list(OperatorInformation operatorInformation)
    {
        startPage();
        List<OperatorInformation> list = operatorInformationService.selectOperatorInformationList(operatorInformation);
        return getDataTable(list);
    }

    /**
     * 导出运营商信息 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:information:export')")
    @Log(title = "运营商信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OperatorInformation operatorInformation)
    {
        List<OperatorInformation> list = operatorInformationService.selectOperatorInformationList(operatorInformation);
        ExcelUtil<OperatorInformation> util = new ExcelUtil<OperatorInformation>(OperatorInformation.class);
        util.exportExcel(response, list, "运营商信息 数据");
    }

    /**
     * 获取运营商信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(operatorInformationService.selectOperatorInformationById(id));
    }

    /**
     * 新增运营商信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:information:add')")
    @Log(title = "运营商信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OperatorInformation operatorInformation)
    {
        return toAjax(operatorInformationService.insertOperatorInformation(operatorInformation));
    }

    /**
     * 修改运营商信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:information:edit')")
    @Log(title = "运营商信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OperatorInformation operatorInformation)
    {
        return toAjax(operatorInformationService.updateOperatorInformation(operatorInformation));
    }

    /**
     * 删除运营商信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:information:remove')")
    @Log(title = "运营商信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(operatorInformationService.deleteOperatorInformationByIds(ids));
    }
}
