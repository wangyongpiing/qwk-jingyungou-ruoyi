package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.PointsDetails;
import com.ruoyi.qwk.service.IPointsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 仓库商品积分详情 Controller
 *
 * @author cjf
 * @date 2023-07-10
 */
@RestController
@RequestMapping("/qwk/pointsDetails")
public class PointsDetailsController extends BaseController
{
    @Autowired
    private IPointsDetailsService pointsDetailsService;

    /**
     * 查询仓库商品积分详情 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:pointsDetails:list')")
    @GetMapping("/list")
    public TableDataInfo list(PointsDetails pointsDetails)
    {
        startPage();
        List<PointsDetails> list = pointsDetailsService.selectPointsDetailsList(pointsDetails);
        return getDataTable(list);
    }

    /**
     * 导出仓库商品积分详情 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:pointsDetails:export')")
    @Log(title = "仓库商品积分详情 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PointsDetails pointsDetails)
    {
        List<PointsDetails> list = pointsDetailsService.selectPointsDetailsList(pointsDetails);
        ExcelUtil<PointsDetails> util = new ExcelUtil<PointsDetails>(PointsDetails.class);
        util.exportExcel(response, list, "仓库商品积分详情 数据");
    }

    /**
     * 获取仓库商品积分详情 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:pointsDetails:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pointsDetailsService.selectPointsDetailsById(id));
    }

    /**
     * 新增仓库商品积分详情
     */
    @PreAuthorize("@ss.hasPermi('qwk:pointsDetails:add')")
    @Log(title = "仓库商品积分详情 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PointsDetails pointsDetails)
    {
        return toAjax(pointsDetailsService.insertPointsDetails(pointsDetails));
    }

    /**
     * 修改仓库商品积分详情
     */
    @PreAuthorize("@ss.hasPermi('qwk:pointsDetails:edit')")
    @Log(title = "仓库商品积分详情 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PointsDetails pointsDetails)
    {
        return toAjax(pointsDetailsService.updatePointsDetails(pointsDetails));
    }

    /**
     * 删除仓库商品积分详情
     */
    @PreAuthorize("@ss.hasPermi('qwk:pointsDetails:remove')")
    @Log(title = "仓库商品积分详情 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pointsDetailsService.deletePointsDetailsByIds(ids));
    }

}
