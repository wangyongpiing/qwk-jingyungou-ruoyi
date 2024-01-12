package com.ruoyi.qwk.blindBox.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
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
import com.ruoyi.qwk.blindBox.domain.Bullets;
import com.ruoyi.qwk.blindBox.service.IBulletsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 子弹配置Controller
 * 
 * @author cjf
 * @date 2023-09-14
 */
@RestController
@RequestMapping("/qwk/bullets")
@Api(tags = "获取子弹配置")
public class BulletsController extends BaseController
{
    @Autowired
    private IBulletsService bulletsService;



    /**
     * 查询子弹配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:bullets:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bullets bullets)
    {
        startPage();
        List<Bullets> list = bulletsService.selectBulletsList(bullets);
        return getDataTable(list);
    }

    /**
     * 导出子弹配置列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:bullets:export')")
    @Log(title = "子弹配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Bullets bullets)
    {
        List<Bullets> list = bulletsService.selectBulletsList(bullets);
        ExcelUtil<Bullets> util = new ExcelUtil<Bullets>(Bullets.class);
        util.exportExcel(response, list, "子弹配置数据");
    }

    /**
     * 获取子弹配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:bullets:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bulletsService.selectBulletsById(id));
    }

    /**
     * 新增子弹配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:bullets:add')")
    @Log(title = "子弹配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bullets bullets)
    {
        return toAjax(bulletsService.insertBullets(bullets));
    }

    /**
     * 修改子弹配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:bullets:edit')")
    @Log(title = "子弹配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bullets bullets)
    {
        return toAjax(bulletsService.updateBullets(bullets));
    }

    /**
     * 删除子弹配置
     */
    @PreAuthorize("@ss.hasPermi('qwk:bullets:remove')")
    @Log(title = "子弹配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bulletsService.deleteBulletsByIds(ids));
    }

//------------------------------------app-----------------------------------------------


    @GetMapping("/getAllBullets")
    @ApiOperation("获取所有子弹配置")
    public AjaxResult getAllBullets(){
        List<Bullets> allBullets = bulletsService.getAllBullets();
        return AjaxResult.success(allBullets);
    }

    @PostMapping("/shoot")
    @ApiOperation("发射子弹扣除积分")
    public AjaxResult shoot(@RequestBody Bullets bullets){
        return toAjax(bulletsService.shoot(bullets.getId()));
    }

}
