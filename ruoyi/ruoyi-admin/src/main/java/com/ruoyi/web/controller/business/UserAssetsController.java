package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.UserAssets;
import com.ruoyi.qwk.service.IUserAssetsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 用户资产 Controller
 *
 * @author cjf
 * @date 2023-06-29
 */
@RestController
@RequestMapping("/qwk/assets")
@Api(tags = "用户资产API")
public class UserAssetsController extends BaseController
{
    @Autowired
    private IUserAssetsService userAssetsService;

    /**
     * 查询用户资产 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:assets:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserAssets userAssets)
    {
        startPage();
        List<UserAssets> list = userAssetsService.selectUserAssetsList(userAssets);
        return getDataTable(list);
    }

    /**
     * 导出用户资产 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:assets:export')")
    @Log(title = "用户资产 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserAssets userAssets)
    {
        List<UserAssets> list = userAssetsService.selectUserAssetsList(userAssets);
        ExcelUtil<UserAssets> util = new ExcelUtil<UserAssets>(UserAssets.class);
        util.exportExcel(response, list, "用户资产 数据");
    }

    /**
     * 获取用户资产 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:assets:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userAssetsService.selectUserAssetsById(id));
    }

    /**
     * 新增用户资产
     */
    @PreAuthorize("@ss.hasPermi('qwk:assets:add')")
    @Log(title = "用户资产 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserAssets userAssets)
    {
        return toAjax(userAssetsService.insertUserAssets(userAssets));
    }

    /**
     * 修改用户资产
     */
    @PreAuthorize("@ss.hasPermi('qwk:assets:edit')")
    @Log(title = "用户资产 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserAssets userAssets)
    {
        return toAjax(userAssetsService.updateUserAssets(userAssets));
    }

    /**
     * 删除用户资产
     */
    @PreAuthorize("@ss.hasPermi('qwk:assets:remove')")
    @Log(title = "用户资产 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userAssetsService.deleteUserAssetsByIds(ids));
    }

    /**
     * 获取用户资产
     */
    @GetMapping(value = "/getAssets/{id}")
    @ApiOperation(value = "获取用户资产")
    public AjaxResult getAssets(@PathVariable("id") Long id){
        UserAssets userAssets = new UserAssets();
        userAssets.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        return success(userAssetsService.selectUserAssetsList(userAssets));
    }

    /**
     * 根用户id,资产类型获取用户资产
     */
    @GetMapping(value = "/getAssetsByUserId/{id}/{type}")
    @ApiOperation(value = "获取用户资产")
    public AjaxResult getAssetsByUserId(@PathVariable("id") Long id,@PathVariable("type") Long type){
        return success(userAssetsService.selectByUserId(id,type));
    }

    @PostMapping(value = "/updateAssetsByGame")
    @ApiOperation("游戏获得资产")
    public AjaxResult updateAssetsByGame(@RequestBody UserAssets userAssets) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        Map<String, Object> params = userAssets.getParams();
        if (!params.containsKey("gameId"))return error("缺少游戏编号");
        Long gameId = Long.parseLong(params.get("gameId").toString());
        Long total = Long.parseLong(userAssets.getTotal());
//        return toAjax(userAssetsService.updateAssetsByGame(userId,gameId,userAssets.getType(),total.intValue()));
        return toAjax(0);
    }

    /**
     * 获取用户钻石资产
     */
    @GetMapping(value = "/getAssetsByDiamond")
    @ApiOperation(value = "获取用户钻石资产")
    public AjaxResult getAssetsByDiamond(){
        return success(userAssetsService.selectByUserId(SecurityUtils.getLoginUser().getUser().getUserId(),2L));
    }

}
