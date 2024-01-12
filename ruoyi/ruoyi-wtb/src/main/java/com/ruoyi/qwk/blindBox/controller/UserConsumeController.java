package com.ruoyi.qwk.blindBox.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.qwk.blindBox.domain.UserConsume;
import com.ruoyi.qwk.blindBox.service.IUserConsumeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户消耗Controller
 * 
 * @author cjf
 * @date 2023-09-24
 */
@RestController
@RequestMapping("/qwk/userConsume")
public class UserConsumeController extends BaseController
{
    @Autowired
    private IUserConsumeService userConsumeService;

    /**
     * 查询用户消耗列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:userConsume:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserConsume userConsume)
    {
        startPage();
        List<UserConsume> list = userConsumeService.selectUserConsumeList(userConsume);
        return getDataTable(list);
    }

    /**
     * 导出用户消耗列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:userConsume:export')")
    @Log(title = "用户消耗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserConsume userConsume)
    {
        List<UserConsume> list = userConsumeService.selectUserConsumeList(userConsume);
        ExcelUtil<UserConsume> util = new ExcelUtil<UserConsume>(UserConsume.class);
        util.exportExcel(response, list, "用户消耗数据");
    }

    /**
     * 获取用户消耗详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:userConsume:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userConsumeService.selectUserConsumeById(id));
    }

    /**
     * 新增用户消耗
     */
    @PreAuthorize("@ss.hasPermi('qwk:userConsume:add')")
    @Log(title = "用户消耗", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserConsume userConsume)
    {
        return toAjax(userConsumeService.insertUserConsume(userConsume));
    }

    /**
     * 修改用户消耗
     */
    @PreAuthorize("@ss.hasPermi('qwk:userConsume:edit')")
    @Log(title = "用户消耗", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserConsume userConsume)
    {
        return toAjax(userConsumeService.updateUserConsume(userConsume));
    }

    /**
     * 删除用户消耗
     */
    @PreAuthorize("@ss.hasPermi('qwk:userConsume:remove')")
    @Log(title = "用户消耗", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userConsumeService.deleteUserConsumeByIds(ids));
    }
}
