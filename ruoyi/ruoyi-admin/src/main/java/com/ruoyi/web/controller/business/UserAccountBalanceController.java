package com.ruoyi.web.controller.business;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qwk.domain.UserAccountBalance;
import com.ruoyi.qwk.service.IUserAccountBalanceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户账户余额Controller
 *
 * @author cjf
 * @date 2023-12-21
 */
@RestController
@RequestMapping("/qwk/balance")
public class UserAccountBalanceController extends BaseController
{
    @Autowired
    private IUserAccountBalanceService userAccountBalanceService;

    /**
     * 查询用户账户余额列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:balance:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserAccountBalance userAccountBalance)
    {
        startPage();
        List<UserAccountBalance> list = userAccountBalanceService.selectUserAccountBalanceList(userAccountBalance);
        return getDataTable(list);
    }

    /**
     * 导出用户账户余额列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:balance:export')")
    @Log(title = "用户账户余额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserAccountBalance userAccountBalance)
    {
        List<UserAccountBalance> list = userAccountBalanceService.selectUserAccountBalanceList(userAccountBalance);
        ExcelUtil<UserAccountBalance> util = new ExcelUtil<UserAccountBalance>(UserAccountBalance.class);
        util.exportExcel(response, list, "用户账户余额数据");
    }

    /**
     * 获取用户账户余额详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:balance:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userAccountBalanceService.selectUserAccountBalanceById(id));
    }

    /**
     * 新增用户账户余额
     */
    @PreAuthorize("@ss.hasPermi('qwk:balance:add')")
    @Log(title = "用户账户余额", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserAccountBalance userAccountBalance)
    {
        return toAjax(userAccountBalanceService.insertUserAccountBalance(userAccountBalance));
    }

    /**
     * 修改用户账户余额
     */
    @PreAuthorize("@ss.hasPermi('qwk:balance:edit')")
    @Log(title = "用户账户余额", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserAccountBalance userAccountBalance)
    {
        return toAjax(userAccountBalanceService.updateUserAccountBalance(userAccountBalance));
    }

    /**
     * 删除用户账户余额
     */
    @PreAuthorize("@ss.hasPermi('qwk:balance:remove')")
    @Log(title = "用户账户余额", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userAccountBalanceService.deleteUserAccountBalanceByIds(ids));
    }

    /**
     * APP查询账户余额
     * @return
     */
    @ApiOperation(value = "APP查询账户余额")
    @GetMapping("/query")
    public AjaxResult queryAccountBalance(){
        return userAccountBalanceService.queryAccountBalance();
    }

    /**
     * APP添加支付宝账户
     * @return
     */
    @ApiOperation(value = "APP添加支付宝账户")
    @GetMapping("/alipayAccount")
    public AjaxResult addAlipayAccount(@RequestParam("alipayAccount") String alipayAccount,@RequestParam("alipayAccountName") String alipayAccountName){
         return userAccountBalanceService.addAlipayAccount(alipayAccount,alipayAccountName);
    }

    /**
     * 分享助力查询账户余额
     * @return
     */
    @ApiOperation(value = "分享助力查询账户余额")
    @GetMapping("/queryUserAccount")
    @Anonymous
    public AjaxResult queryShareAccountBalance(@RequestParam("userId") String userId){
        return userAccountBalanceService.queryShareAccountBalance(userId);
    }
}
