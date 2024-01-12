package com.ruoyi.qwk.blindBox.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.blindBox.domain.UserBullets;
import com.ruoyi.qwk.blindBox.service.IUserBulletsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户子弹数量记录Controller
 * 
 * @author cjf
 * @date 2023-09-21
 */
@RestController
@RequestMapping("/qwk/UserBullets")
@Api(tags = "用户炮弹API")
public class UserBulletsController extends BaseController
{
    @Autowired
    private IUserBulletsService userBulletsService;

    /**
     * 查询用户子弹数量记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:UserBullets:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserBullets userBullets)
    {
        startPage();
        List<UserBullets> list = userBulletsService.selectUserBulletsList(userBullets);
        return getDataTable(list);
    }

    /**
     * 导出用户子弹数量记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:UserBullets:export')")
    @Log(title = "用户子弹数量记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserBullets userBullets)
    {
        List<UserBullets> list = userBulletsService.selectUserBulletsList(userBullets);
        ExcelUtil<UserBullets> util = new ExcelUtil<UserBullets>(UserBullets.class);
        util.exportExcel(response, list, "用户子弹数量记录数据");
    }

    /**
     * 获取用户子弹数量记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:UserBullets:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userBulletsService.selectUserBulletsById(id));
    }

    /**
     * 新增用户子弹数量记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:UserBullets:add')")
    @Log(title = "用户子弹数量记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserBullets userBullets)
    {
        return toAjax(userBulletsService.insertUserBullets(userBullets));
    }

    /**
     * 修改用户子弹数量记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:UserBullets:edit')")
    @Log(title = "用户子弹数量记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserBullets userBullets)
    {
        return toAjax(userBulletsService.updateUserBullets(userBullets));
    }

    /**
     * 删除用户子弹数量记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:UserBullets:remove')")
    @Log(title = "用户子弹数量记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userBulletsService.deleteUserBulletsByIds(ids));
    }


//    --------------------------------app---------------------------------------------
    /**
     *
     * 购买子弹
     * @param userBullets 用户子弹数量对象
     * */
    @Log(title = "用户购买炮弹",businessType = BusinessType.UPDATE)
    @PostMapping("/purchaseBullets")
    @ApiOperation("用户积分购买炮弹")
    public AjaxResult purchaseBullets(@RequestBody UserBullets userBullets){
        return userBulletsService.purchaseBullets(userBullets);
    }

    @GetMapping("/queryBulletsNum/{bulletsId}")
    @ApiOperation("根据炮弹类型查询数量")
    public AjaxResult queryBulletsNum(@PathVariable("bulletsId") Long bulletsId){
        return success(userBulletsService.queryBulletsNum(SecurityUtils.getLoginUser().getUser().getUserId(),bulletsId));
    }

    /**
     *
     * 用户消耗炮弹
     * @param userBullets 用户子弹数量对象
     * */
    @Log(title = "用户消耗炮弹",businessType = BusinessType.UPDATE)
    @PostMapping("/consumption")
    @ApiOperation("用户消耗炮弹")
    public AjaxResult consumption(@RequestBody UserBullets userBullets){
        UserBullets queryBulletsNum = userBulletsService.queryBulletsNum(SecurityUtils.getLoginUser().getUser().getUserId(), userBullets.getBulletsId());
        if (ObjectUtils.isEmpty(queryBulletsNum))return AjaxResult.error("炮弹不足,请先购买炮弹","");
        return toAjax(userBulletsService.consumption(SecurityUtils.getLoginUser().getUser().getUserId(),userBullets.getBulletsId()));
    }

    /**
     *
     * 新用户消耗炮弹
     * */
    @Log(title = "新用户消耗炮弹",businessType = BusinessType.UPDATE)
    @GetMapping("/noviceConsumption")
    @ApiOperation("新用户消耗炮弹")
    public AjaxResult noviceConsumption(){
        return userBulletsService.noviceConsumption(SecurityUtils.getLoginUser().getUser().getUserId());
    }

    /**
     * 获取用户信息
     * */
    @GetMapping("/getUserDetails")
    @ApiOperation("获取用户信息")
    public AjaxResult getUserDetails(){
        return userBulletsService.getUserDetails();
    }


}
