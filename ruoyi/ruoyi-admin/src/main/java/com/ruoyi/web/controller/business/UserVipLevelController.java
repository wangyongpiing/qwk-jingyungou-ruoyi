package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.UserVipLevel;
import com.ruoyi.qwk.service.IUserVipLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户vip等级记录Controller
 *
 * @author cjf
 * @date 2023-09-13
 */
@RestController
@RequestMapping("/qwk/userVipLevel")
public class UserVipLevelController extends BaseController
{
    @Autowired
    private IUserVipLevelService userVipLevelService;

    /**
     * 查询用户vip等级记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:userVipLevel:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserVipLevel userVipLevel)
    {
        startPage();
        List<UserVipLevel> list = userVipLevelService.selectUserVipLevelList(userVipLevel);
        return getDataTable(list);
    }

    /**
     * 导出用户vip等级记录列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:userVipLevel:export')")
    @Log(title = "用户vip等级记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVipLevel userVipLevel)
    {
        List<UserVipLevel> list = userVipLevelService.selectUserVipLevelList(userVipLevel);
        ExcelUtil<UserVipLevel> util = new ExcelUtil<UserVipLevel>(UserVipLevel.class);
        util.exportExcel(response, list, "用户vip等级记录数据");
    }

    /**
     * 获取用户vip等级记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:userVipLevel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userVipLevelService.selectUserVipLevelById(id));
    }

    /**
     * 新增用户vip等级记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:userVipLevel:add')")
    @Log(title = "用户vip等级记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserVipLevel userVipLevel)
    {
        return toAjax(userVipLevelService.insertUserVipLevel(userVipLevel));
    }

    /**
     * 修改用户vip等级记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:userVipLevel:edit')")
    @Log(title = "用户vip等级记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserVipLevel userVipLevel)
    {
        return toAjax(userVipLevelService.updateUserVipLevel(userVipLevel));
    }

    /**
     * 删除用户vip等级记录
     */
    @PreAuthorize("@ss.hasPermi('qwk:userVipLevel:remove')")
    @Log(title = "用户vip等级记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userVipLevelService.deleteUserVipLevelByIds(ids));
    }
}
