package com.ruoyi.web.controller.business;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.WarehouseDetails;
import com.ruoyi.qwk.service.IWarehouseDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库详情 Controller
 *
 * @author cjf
 * @date 2023-06-29
 */
@RestController
@RequestMapping("/qwk/details")
@Api(tags = "仓库详情API")
public class WarehouseDetailsController extends BaseController
{
    @Autowired
    private IWarehouseDetailsService warehouseDetailsService;

    /**
     * 查询仓库详情 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:details:list')")
    @GetMapping("/list")
    public TableDataInfo list(WarehouseDetails warehouseDetails)
    {
        startPage();
        List<WarehouseDetails> list = warehouseDetailsService.selectWarehouseDetailsList(warehouseDetails);
        return getDataTable(list);
    }

    /**
     * 导出仓库详情 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:details:export')")
    @Log(title = "仓库详情 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WarehouseDetails warehouseDetails)
    {
        List<WarehouseDetails> list = warehouseDetailsService.selectWarehouseDetailsList(warehouseDetails);
        ExcelUtil<WarehouseDetails> util = new ExcelUtil<WarehouseDetails>(WarehouseDetails.class);
        util.exportExcel(response, list, "仓库详情 数据");
    }

    /**
     * 获取仓库详情 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:details:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(warehouseDetailsService.selectWarehouseDetailsById(id));
    }

    /**
     * 新增仓库详情
     */
    @PreAuthorize("@ss.hasPermi('qwk:details:add')")
    @Log(title = "仓库详情 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WarehouseDetails warehouseDetails)
    {
        return toAjax(warehouseDetailsService.insertWarehouseDetails(warehouseDetails));
    }

    /**
     * 修改仓库详情
     */
    @PreAuthorize("@ss.hasPermi('qwk:details:edit')")
    @Log(title = "仓库详情 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WarehouseDetails warehouseDetails)
    {
        return toAjax(warehouseDetailsService.updateWarehouseDetails(warehouseDetails));
    }

    /**
     * 删除仓库详情
     */
    @PreAuthorize("@ss.hasPermi('qwk:details:remove')")
    @Log(title = "仓库详情 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(warehouseDetailsService.deleteWarehouseDetailsByIds(ids));
    }

    /**
     * 查询仓库详情 列表
     */
    @ApiOperation(value = "查询仓库详情列表")
    @PostMapping("/getList")
    public AjaxResult getList(@RequestBody WarehouseDetails warehouseDetails)
    {
        warehouseDetails.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        warehouseDetails.setState(0L);

        Map<String, Object> params = warehouseDetails.getParams();
        Integer pageNum =Integer.parseInt(params.get("pageNum").toString()) ;
        Integer pageSize =Integer.parseInt(params.get("pageSize").toString()) ;
        PageHelper.startPage(pageNum, pageSize, "wd.is_novice ASC,wd.create_time DESC");
        List<WarehouseDetails> list = warehouseDetailsService.selectWarehouseDetailsList(warehouseDetails);
        TableDataInfo dataTable = getDataTable(list);
        return success(dataTable);
    }

    /**
     * 兑换积分
     */
    @ApiOperation(value = "兑换积分")
    @PostMapping("/exchange")
    public AjaxResult exchange(@RequestBody WarehouseDetails wd){
        return warehouseDetailsService.exchange(wd);
    }

    /**
     * 查询仓库详情 列表
     */
    @GetMapping("/getGameProps")
    @ApiOperation(value = "获取游戏背包道具")
    public AjaxResult getGameProps(){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        WarehouseDetails details = new WarehouseDetails();
        details.setUserId(userId);
        details.setType("2");
        List<WarehouseDetails> list = warehouseDetailsService.selectWarehouseDetailsList(details);
        return success(list);
    }

    /**
     * 使用道具
     *
     * @param id 仓库详情 主键
     * @return 结果
     */
    @GetMapping("/useProps/{id}")
    @ApiOperation(value = "使用游戏背包道具")
    public AjaxResult useProps(@PathVariable Long id){
        int i = warehouseDetailsService.useProps(id);
        if (i <= 0){
            return error();
        }else {
            return success(warehouseDetailsService.selectWarehouseDetailsById(id));
        }
    }

    /**
     * 获取掉落道具
     *
     * @return 结果
     */
    @GetMapping("/getFallen/{gameId}/{num}")
    @ApiOperation(value = "获取游戏道具")
    public AjaxResult getFallen(@PathVariable Long gameId,@PathVariable Long num){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return success(warehouseDetailsService.ifFallen(userId,gameId,num));
    }

    /**
     * 转赠好友
     *
     * @param wDId 仓库详情id 主键
     * @param userId 好友id
     * @return 结果
     */
    @GetMapping("/transferFriends/{wDId}/{userId}")
    @ApiOperation(value = "转赠好友")
    public AjaxResult transferFriends(@PathVariable Long wDId,@PathVariable String userId){
        return toAjax(warehouseDetailsService.transferFriends(wDId,userId));
    }

    @GetMapping("/addAssistanceNum/{wDId}")
    @ApiOperation("好友助力")
    public AjaxResult addAssistanceNum(@PathVariable("wDId") Long wDId){
        return toAjax(warehouseDetailsService.addAssistanceNum(wDId));
    }

    @PostMapping("/exchangeCandy")
    @ApiOperation("兑换糖果")
    public AjaxResult exchangeCandy(@RequestBody HashMap<String,Object> map){
        return warehouseDetailsService.exchangeCandy(map);
    }



}
