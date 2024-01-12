package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.qwk.domain.BatchInsertSerialCard;
import com.ruoyi.qwk.domain.BatchInsertSerialCardType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.domain.SerialCard;
import com.ruoyi.qwk.service.ISerialCardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生肖序列卡 Controller
 *
 * @author cjf
 * @date 2023-12-01
 */
@RestController
@RequestMapping("/qwk/card")
@Api(tags = "生肖序列卡API")
public class SerialCardController extends BaseController
{
    @Autowired
    private ISerialCardService serialCardService;

    /**
     * 查询生肖序列卡 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:card:list')")
    @GetMapping("/list")
    public TableDataInfo list(SerialCard serialCard)
    {
        startPage();
        List<SerialCard> list = serialCardService.selectSerialCardList(serialCard);
        return getDataTable(list);
    }

    /**
     * 导出生肖序列卡 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:card:export')")
    @Log(title = "生肖序列卡 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SerialCard serialCard)
    {
        List<SerialCard> list = serialCardService.selectSerialCardList(serialCard);
        ExcelUtil<SerialCard> util = new ExcelUtil<SerialCard>(SerialCard.class);
        util.exportExcel(response, list, "生肖序列卡 数据");
    }

    /**
     * 获取生肖序列卡 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:card:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(serialCardService.selectSerialCardById(id));
    }

    /**
     * 新增生肖序列卡
     */
    @PreAuthorize("@ss.hasPermi('qwk:card:add')")
    @Log(title = "生肖序列卡 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SerialCard serialCard)
    {
        return toAjax(serialCardService.insertSerialCard(serialCard));
    }

    /**
     * 修改生肖序列卡
     */
    @PreAuthorize("@ss.hasPermi('qwk:card:edit')")
    @Log(title = "生肖序列卡 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SerialCard serialCard)
    {
        return toAjax(serialCardService.updateSerialCard(serialCard));
    }

    /**
     * 删除生肖序列卡
     */
    @PreAuthorize("@ss.hasPermi('qwk:card:remove')")
    @Log(title = "生肖序列卡 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serialCardService.deleteSerialCardByIds(ids));
    }

    /**
     * 批量生成序列卡
     * @param card
     * @return
     */
    @ApiOperation(value = "批量生成序列卡")
    @PostMapping(value = "/batchInsertSerialCard")
    public AjaxResult batchInsertSerialCard(@RequestBody BatchInsertSerialCard card){
        return serialCardService.batchInsertSerialCard(card);
    }

    /**
     * App兑换生肖序列卡
     * @param type
     * @return
     */
    @ApiOperation(value = "App兑换生肖序列卡")
    @GetMapping(value = "/serialCardExchange")
    public AjaxResult serialCardExchange(@RequestParam("type") String type){
        return serialCardService.serialCardExchange(type);
    }

    /**
     * App查询生肖序列卡 列表
     */
    @GetMapping("/getList")
    @ApiOperation(value = "App查询生肖序列卡 列表")
    public AjaxResult getList()
    {
        return serialCardService.selectSerialCardList();
    }
}
