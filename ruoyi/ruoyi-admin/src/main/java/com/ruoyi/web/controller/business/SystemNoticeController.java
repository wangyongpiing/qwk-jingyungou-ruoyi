package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.SystemNotice;
import com.ruoyi.qwk.service.ISystemNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统消息通知Controller
 *
 * @author cjf
 * @date 2023-10-10
 */
@RestController
@RequestMapping("/qwk/systemNotice")
@Api(tags = "系统消息通知API")
public class SystemNoticeController extends BaseController
{
    @Autowired
    private ISystemNoticeService systemNoticeService;

    /**
     * 查询系统消息通知列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:systemNotice:list')")
    @GetMapping("/list")
    public TableDataInfo list(SystemNotice systemNotice)
    {
        startPage();
        List<SystemNotice> list = systemNoticeService.selectSystemNoticeList(systemNotice);
        return getDataTable(list);
    }

    /**
     * 导出系统消息通知列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:systemNotice:export')")
    @Log(title = "系统消息通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SystemNotice systemNotice)
    {
        List<SystemNotice> list = systemNoticeService.selectSystemNoticeList(systemNotice);
        ExcelUtil<SystemNotice> util = new ExcelUtil<SystemNotice>(SystemNotice.class);
        util.exportExcel(response, list, "系统消息通知数据");
    }

    /**
     * 获取系统消息通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:systemNotice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(systemNoticeService.selectSystemNoticeById(id));
    }

    /**
     * 新增系统消息通知
     */
    @PreAuthorize("@ss.hasPermi('qwk:systemNotice:add')")
    @Log(title = "系统消息通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SystemNotice systemNotice)
    {
        return toAjax(systemNoticeService.insertSystemNotice(systemNotice));
    }

    /**
     * 修改系统消息通知
     */
    @PreAuthorize("@ss.hasPermi('qwk:systemNotice:edit')")
    @Log(title = "系统消息通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SystemNotice systemNotice)
    {
        return toAjax(systemNoticeService.updateSystemNotice(systemNotice));
    }

    /**
     * 删除系统消息通知
     */
    @PreAuthorize("@ss.hasPermi('qwk:systemNotice:remove')")
    @Log(title = "系统消息通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(systemNoticeService.deleteSystemNoticeByIds(ids));
    }


    @PreAuthorize("@ss.hasPermi('qwk:systemNotice:add')")
    @Log(title = "发送系统消息通知", businessType = BusinessType.OTHER)
    @PostMapping("/sendNotice")
    public AjaxResult sendNotice(@RequestBody SystemNotice systemNotice){
        return systemNoticeService.sendNotice(systemNotice);
    }

//    ------------------------------------------app------------------------------------------------------------

    @ApiOperation("获取用户系统通知消息")
    @GetMapping("/getNotice")
    public AjaxResult getNotice(){
        return systemNoticeService.getNotice();
    }

    /**
     * 获取系统消息通知详细信息
     */
    @GetMapping(value = "/getNoticeInfo/{id}")
    @ApiOperation("获取系统消息通知详细信息")
    public AjaxResult getNoticeInfo(@PathVariable("id") Long id) {
        SystemNotice systemNotice = systemNoticeService.selectSystemNoticeById(id);
        return success(ObjectUtils.isEmpty(systemNotice) ? "":systemNotice);
    }

}
