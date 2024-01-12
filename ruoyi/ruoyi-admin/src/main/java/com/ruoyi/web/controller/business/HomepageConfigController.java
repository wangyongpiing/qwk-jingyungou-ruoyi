package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.HomepageConfig;
import com.ruoyi.qwk.service.IHomepageConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 首页配置 Controller
 *
 * @author cjf
 * @date 2023-06-26
 */
@RestController
@RequestMapping("/qwk/config")
@Api(tags = "首页配置")
public class HomepageConfigController extends BaseController
{
    @Autowired
    private IHomepageConfigService homepageConfigService;

    /**
     * 查询首页配置 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomepageConfig homepageConfig)
    {
        startPage();
        List<HomepageConfig> list = homepageConfigService.selectHomepageConfigList(homepageConfig);
        return getDataTable(list);
    }

    /**
     * 导出首页配置 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:config:export')")
    @Log(title = "首页配置 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomepageConfig homepageConfig)
    {
        List<HomepageConfig> list = homepageConfigService.selectHomepageConfigList(homepageConfig);
        ExcelUtil<HomepageConfig> util = new ExcelUtil<HomepageConfig>(HomepageConfig.class);
        util.exportExcel(response, list, "首页配置 数据");
    }

    /**
     * 获取首页配置 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(homepageConfigService.selectHomepageConfigById(id));
    }

    /**
     * 新增首页配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:config:add')")
    @Log(title = "首页配置 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomepageConfig homepageConfig)
    {
        return toAjax(homepageConfigService.insertHomepageConfig(homepageConfig));
    }

    /**
     * 修改首页配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:config:edit')")
    @Log(title = "首页配置 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomepageConfig homepageConfig)
    {
        return toAjax(homepageConfigService.updateHomepageConfig(homepageConfig));
    }

    /**
     * 删除首页配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:config:remove')")
    @Log(title = "首页配置 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homepageConfigService.deleteHomepageConfigByIds(ids));
    }

//    -------------------------------app-----------------------------------------

    /**
     * 查询App首页配置
     *
     *
     * @return 结果
     */
    @GetMapping("/getHomepageConfigByClassify")
    @ApiOperation(value = "查询App首页配置", notes = "")
    public AjaxResult getHomepageConfigByClassify(){
        return success(homepageConfigService.getHomepageConfigByClassify());
    }

    /**
     * 查询启动页
     *
     *
     * @return 结果
     */
    @Anonymous
    @GetMapping("/getLaunchPage")
    @ApiOperation(value = "查询启动页", notes = "")
    public AjaxResult getLaunchPage(){
        HomepageConfig homepageConfig = new HomepageConfig();
        homepageConfig.setClassify(5L);
        homepageConfig.setState(1L);
        List<HomepageConfig> configList = homepageConfigService.selectHomepageConfigList(homepageConfig);
        return success(configList);
    }

    //Homepage push
    /**
     * 查询首页推送
     *
     *
     * @return 结果
     */
    @Anonymous
    @GetMapping("/getHomepagePush")
    @ApiOperation(value = "查询首页推送", notes = "")
    public AjaxResult getHomepagePush(){
        HomepageConfig homepageConfig = new HomepageConfig();
        homepageConfig.setClassify(6L);
        homepageConfig.setState(1L);
        List<HomepageConfig> configList = homepageConfigService.selectHomepageConfigList(homepageConfig);
        return success(configList);
    }
}
