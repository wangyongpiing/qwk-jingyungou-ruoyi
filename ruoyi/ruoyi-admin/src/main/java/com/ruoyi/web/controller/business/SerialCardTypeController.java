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
import com.ruoyi.qwk.domain.SerialCardType;
import com.ruoyi.qwk.service.ISerialCardTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 序列卡类型 Controller
 *
 * @author cjf
 * @date 2023-12-01
 */
@RestController
@RequestMapping("/qwk/type")
@Api(tags = "序列卡类型API")
public class SerialCardTypeController extends BaseController
{
    @Autowired
    private ISerialCardTypeService serialCardTypeService;

    /**
     * 查询序列卡类型 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(SerialCardType serialCardType)
    {
        startPage();
        List<SerialCardType> list = serialCardTypeService.selectSerialCardTypeList(serialCardType);
        return getDataTable(list);
    }

    /**
     * 导出序列卡类型 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:export')")
    @Log(title = "序列卡类型 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SerialCardType serialCardType)
    {
        List<SerialCardType> list = serialCardTypeService.selectSerialCardTypeList(serialCardType);
        ExcelUtil<SerialCardType> util = new ExcelUtil<SerialCardType>(SerialCardType.class);
        util.exportExcel(response, list, "序列卡类型 数据");
    }

    /**
     * 获取序列卡类型 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(serialCardTypeService.selectSerialCardTypeById(id));
    }

    /**
     * 新增序列卡类型
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:add')")
    @Log(title = "序列卡类型 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SerialCardType serialCardType)
    {
        return toAjax(serialCardTypeService.insertSerialCardType(serialCardType));
    }

    /**
     * 修改序列卡类型
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:edit')")
    @Log(title = "序列卡类型 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SerialCardType serialCardType)
    {
        return toAjax(serialCardTypeService.updateSerialCardType(serialCardType));
    }

    /**
     * 删除序列卡类型
     */
    @PreAuthorize("@ss.hasPermi('qwk:type:remove')")
    @Log(title = "序列卡类型 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serialCardTypeService.deleteSerialCardTypeByIds(ids));
    }
}
