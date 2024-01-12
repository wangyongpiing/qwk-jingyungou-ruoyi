package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.HomepageRotatingPictures;
import com.ruoyi.qwk.service.IHomepageRotatingPicturesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *  首页轮播图片Controller
 *
 * @author cjf
 * @date 2023-11-27
 */
@RestController
@RequestMapping("/qwk/pictures")
@Api(tags = "app首页轮播图片API")
public class HomepageRotatingPicturesController extends BaseController
{
    @Autowired
    private IHomepageRotatingPicturesService homepageRotatingPicturesService;

    /**
     * 查询 首页轮播图片列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:pictures:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomepageRotatingPictures homepageRotatingPictures)
    {
        startPage();
        List<HomepageRotatingPictures> list = homepageRotatingPicturesService.selectHomepageRotatingPicturesList(homepageRotatingPictures);
        return getDataTable(list);
    }

    /**
     * 导出 首页轮播图片列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:pictures:export')")
    @Log(title = " 首页轮播图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomepageRotatingPictures homepageRotatingPictures)
    {
        List<HomepageRotatingPictures> list = homepageRotatingPicturesService.selectHomepageRotatingPicturesList(homepageRotatingPictures);
        ExcelUtil<HomepageRotatingPictures> util = new ExcelUtil<HomepageRotatingPictures>(HomepageRotatingPictures.class);
        util.exportExcel(response, list, " 首页轮播图片数据");
    }

    /**
     * 获取 首页轮播图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:pictures:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(homepageRotatingPicturesService.selectHomepageRotatingPicturesById(id));
    }

    /**
     * 新增 首页轮播图片
     */
    @PreAuthorize("@ss.hasPermi('qwk:pictures:add')")
    @Log(title = " 首页轮播图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomepageRotatingPictures homepageRotatingPictures)
    {
        return toAjax(homepageRotatingPicturesService.insertHomepageRotatingPictures(homepageRotatingPictures));
    }

    /**
     * 修改 首页轮播图片
     */
    @PreAuthorize("@ss.hasPermi('qwk:pictures:edit')")
    @Log(title = " 首页轮播图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomepageRotatingPictures homepageRotatingPictures)
    {
        return toAjax(homepageRotatingPicturesService.updateHomepageRotatingPictures(homepageRotatingPictures));
    }

    /**
     * 删除 首页轮播图片
     */
    @PreAuthorize("@ss.hasPermi('qwk:pictures:remove')")
    @Log(title = " 首页轮播图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homepageRotatingPicturesService.deleteHomepageRotatingPicturesByIds(ids));
    }
}
