package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
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
import com.ruoyi.qwk.domain.HomeCarouselImage;
import com.ruoyi.qwk.service.IHomeCarouselImageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 首页轮播图 Controller
 *
 * @author cjf
 * @date 2024-01-11
 */
@RestController
@RequestMapping("/qwk/homeCarouselImage")
public class HomeCarouselImageController extends BaseController
{
    @Autowired
    private IHomeCarouselImageService homeCarouselImageService;

    /**
     * 查询首页轮播图 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:image:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomeCarouselImage homeCarouselImage)
    {
        startPage();
        List<HomeCarouselImage> list = homeCarouselImageService.selectHomeCarouselImageList(homeCarouselImage);
        return getDataTable(list);
    }

    /**
     * 导出首页轮播图 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:image:export')")
    @Log(title = "首页轮播图 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomeCarouselImage homeCarouselImage)
    {
        List<HomeCarouselImage> list = homeCarouselImageService.selectHomeCarouselImageList(homeCarouselImage);
        ExcelUtil<HomeCarouselImage> util = new ExcelUtil<HomeCarouselImage>(HomeCarouselImage.class);
        util.exportExcel(response, list, "首页轮播图 数据");
    }

    /**
     * 获取首页轮播图 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:image:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(homeCarouselImageService.selectHomeCarouselImageById(id));
    }

    /**
     * 新增首页轮播图
     */
    @PreAuthorize("@ss.hasPermi('qwk:image:add')")
    @Log(title = "首页轮播图 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeCarouselImage homeCarouselImage)
    {
        return toAjax(homeCarouselImageService.insertHomeCarouselImage(homeCarouselImage));
    }

    /**
     * 修改首页轮播图
     */
    @PreAuthorize("@ss.hasPermi('qwk:image:edit')")
    @Log(title = "首页轮播图 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomeCarouselImage homeCarouselImage)
    {
        return toAjax(homeCarouselImageService.updateHomeCarouselImage(homeCarouselImage));
    }

    /**
     * 删除首页轮播图
     */
    @PreAuthorize("@ss.hasPermi('qwk:image:remove')")
    @Log(title = "首页轮播图 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homeCarouselImageService.deleteHomeCarouselImageByIds(ids));
    }

    /**
     * 获取首页轮盘图
     * @return
     */
    @GetMapping("/getList")
    @ApiOperation(value = "获取首页轮播图片")
    @Anonymous
    public AjaxResult getList(){
        return homeCarouselImageService.getList();
    }
}
