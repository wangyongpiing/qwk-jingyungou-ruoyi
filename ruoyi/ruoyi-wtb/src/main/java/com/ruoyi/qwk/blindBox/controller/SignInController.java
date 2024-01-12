package com.ruoyi.qwk.blindBox.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.qwk.blindBox.domain.SignIn;
import com.ruoyi.qwk.blindBox.service.ISignInService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 签到Controller
 * 
 * @author cjf
 * @date 2023-11-03
 */
@RestController
@RequestMapping("/qwk/signIn")
@Api(tags = "签到API")
public class SignInController extends BaseController
{
    @Autowired
    private ISignInService signInService;

    /**
     * 查询签到列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:signIn:list')")
    @GetMapping("/list")
    public TableDataInfo list(SignIn signIn)
    {
        startPage();
        List<SignIn> list = signInService.selectSignInList(signIn);
        return getDataTable(list);
    }

    /**
     * 导出签到列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:signIn:export')")
    @Log(title = "签到", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SignIn signIn)
    {
        List<SignIn> list = signInService.selectSignInList(signIn);
        ExcelUtil<SignIn> util = new ExcelUtil<SignIn>(SignIn.class);
        util.exportExcel(response, list, "签到数据");
    }

    /**
     * 获取签到详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:signIn:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(signInService.selectSignInById(id));
    }

    /**
     * 新增签到
     */
    @PreAuthorize("@ss.hasPermi('qwk:signIn:add')")
    @Log(title = "签到", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SignIn signIn)
    {
        return toAjax(signInService.insertSignIn(signIn));
    }

    /**
     * 修改签到
     */
    @PreAuthorize("@ss.hasPermi('qwk:signIn:edit')")
    @Log(title = "签到", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SignIn signIn)
    {
        return toAjax(signInService.updateSignIn(signIn));
    }

    /**
     * 删除签到
     */
    @PreAuthorize("@ss.hasPermi('qwk:signIn:remove')")
    @Log(title = "签到", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(signInService.deleteSignInByIds(ids));
    }

//    ---------------------------------------签到----------------------------------------------------

    @GetMapping("/signIn")
    @ApiOperation("今日签到")
    public AjaxResult signIn(){
        return signInService.signIn();
    }

    @GetMapping("/mySignIn")
    @ApiOperation("我的签到记录")
    public AjaxResult mySignIn(){
        SignIn signIn = new SignIn();
        signIn.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        signIn.setStatus("0");
        List<SignIn> signIns = signInService.selectSignInList(signIn);
        return AjaxResult.success(signIns);
    }

}
