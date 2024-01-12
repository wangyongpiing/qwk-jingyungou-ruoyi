package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.Versioning;
import com.ruoyi.qwk.service.IVersioningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 版本管理Controller
 *
 * @author cjf
 * @date 2023-08-25
 */
@RestController
@RequestMapping("/qwk/versioning")
@Api(tags = "版本信息API")
public class VersioningController extends BaseController
{
    @Autowired
    private IVersioningService versioningService;

    /**
     * 查询版本管理列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:versioning:list')")
    @GetMapping("/list")
    public TableDataInfo list(Versioning versioning)
    {
        startPage();
        List<Versioning> list = versioningService.selectVersioningList(versioning);
        return getDataTable(list);
    }

    /**
     * 导出版本管理列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:versioning:export')")
    @Log(title = "版本管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Versioning versioning)
    {
        List<Versioning> list = versioningService.selectVersioningList(versioning);
        ExcelUtil<Versioning> util = new ExcelUtil<Versioning>(Versioning.class);
        util.exportExcel(response, list, "版本管理数据");
    }

    /**
     * 获取版本管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:versioning:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(versioningService.selectVersioningById(id));
    }

    /**
     * 新增版本管理
     */
    @PreAuthorize("@ss.hasPermi('qwk:versioning:add')")
    @Log(title = "版本管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Versioning versioning)
    {
        return toAjax(versioningService.insertVersioning(versioning));
    }

    /**
     * 修改版本管理
     */
    @PreAuthorize("@ss.hasPermi('qwk:versioning:edit')")
    @Log(title = "版本管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Versioning versioning)
    {
        return toAjax(versioningService.updateVersioning(versioning));
    }

    /**
     * 删除版本管理
     */
    @PreAuthorize("@ss.hasPermi('qwk:versioning:remove')")
    @Log(title = "版本管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(versioningService.deleteVersioningByIds(ids));
    }

    @GetMapping("/getLatest")
    @ApiOperation("获取最新的版本信息")
    @Anonymous
    public AjaxResult getLatest() throws IOException {
        Versioning latest = versioningService.getLatest();
        byte[] file = FileUtils.downLoadFromUrl(latest.getFileUrl());
        String md5Hex = DigestUtils.md5DigestAsHex(file);
        HashMap<String, Object> map = new HashMap<>();
        map.put("versioning",latest);
        map.put("md5Hex",md5Hex);
        return success(map);
    }

    /**
     * 下载生肖宝
     * @return
     * @throws IOException
     */
    @GetMapping("/getSxb")
    @ApiOperation("下载生肖宝")
    @Anonymous
    public AjaxResult getSxb(){
        Versioning versioning = new Versioning();
        versioning.setFileType("2");
        List<Versioning> versionList = versioningService.selectVersioningList(versioning);
        return AjaxResult.success(versionList.get(0).getFileUrl());
    }

}
