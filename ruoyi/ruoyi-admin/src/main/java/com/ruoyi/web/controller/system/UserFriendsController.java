package com.ruoyi.web.controller.system;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.UserFriends;
import com.ruoyi.system.service.IUserFriendsService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户好友 Controller
 * 
 * @author cjf
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/qwk/friends")
@Api(tags = "用户好友API")
public class UserFriendsController extends BaseController
{
    @Autowired
    private IUserFriendsService userFriendsService;

    /**
     * 查询用户好友 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:friends:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserFriends userFriends)
    {
        startPage();
        List<UserFriends> list = userFriendsService.selectUserFriendsList(userFriends);
        return getDataTable(list);
    }

    /**
     * 导出用户好友 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:friends:export')")
    @Log(title = "用户好友 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserFriends userFriends)
    {
        List<UserFriends> list = userFriendsService.selectUserFriendsList(userFriends);
        ExcelUtil<UserFriends> util = new ExcelUtil<UserFriends>(UserFriends.class);
        util.exportExcel(response, list, "用户好友 数据");
    }

    /**
     * 获取用户好友 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:friends:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(userFriendsService.selectUserFriendsByUserId(userId));
    }

    /**
     * 新增用户好友 
     */
//    @PreAuthorize("@ss.hasPermi('qwk:friends:add')")
    @ApiOperation("添加用户好友")
    @Log(title = "用户好友 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserFriends userFriends)
    {
        userFriends.setCreateTime(new Date());
        return toAjax(userFriendsService.insertUserFriends(userFriends));
    }

    /**
     * 修改用户好友 
     */
    @PreAuthorize("@ss.hasPermi('qwk:friends:edit')")
    @Log(title = "用户好友 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserFriends userFriends)
    {
        return toAjax(userFriendsService.updateUserFriends(userFriends));
    }

    /**
     * 删除用户好友 
     */
    @PreAuthorize("@ss.hasPermi('qwk:friends:remove')")
    @Log(title = "用户好友 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userFriendsService.deleteUserFriendsByUserIds(userIds));
    }

    /**
     * 查询用户好友 列表
     */
    @GetMapping("/getUserFriends")
    @ApiOperation("查询用户好友")
    public AjaxResult getUserFriends(UserFriends userFriends){
        startPage();
        List<UserFriends> list = userFriendsService.selectUserFriendsList(userFriends);
        TableDataInfo dataTable = getDataTable(list);
        return success(dataTable);
    }

    /**
     * 删除用户好友
     */
    @ApiOperation("删除用户好友")
    @Log(title = "用户好友 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{userId}/{friendsId}")
    public AjaxResult del(@PathVariable("userId") Long userId ,@PathVariable("friendsId") Long friendsId)
    {
        return toAjax(userFriendsService.deleteUserFriends(userId,friendsId));
    }

}
