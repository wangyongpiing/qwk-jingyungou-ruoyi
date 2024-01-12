package com.ruoyi.web.controller.business;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.qwk.domain.InviteFriends;
import com.ruoyi.qwk.service.IInviteFriendsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 邀请好友Controller
 *
 * @author cjf
 * @date 2023-09-26
 */
@RestController
@RequestMapping("/qwk/friends")
@Api(tags = "邀请好友记录")
public class InviteFriendsController extends BaseController
{
    @Autowired
    private IInviteFriendsService inviteFriendsService;

//    /**
//     * 查询邀请好友列表
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:friends:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(InviteFriends inviteFriends)
//    {
//        startPage();
//        List<InviteFriends> list = inviteFriendsService.selectInviteFriendsList(inviteFriends);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出邀请好友列表
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:friends:export')")
//    @Log(title = "邀请好友", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, InviteFriends inviteFriends)
//    {
//        List<InviteFriends> list = inviteFriendsService.selectInviteFriendsList(inviteFriends);
//        ExcelUtil<InviteFriends> util = new ExcelUtil<InviteFriends>(InviteFriends.class);
//        util.exportExcel(response, list, "邀请好友数据");
//    }
//
//    /**
//     * 获取邀请好友详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:friends:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id)
//    {
//        return success(inviteFriendsService.selectInviteFriendsById(id));
//    }
//
//    /**
//     * 新增邀请好友
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:friends:add')")
//    @Log(title = "邀请好友", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody InviteFriends inviteFriends)
//    {
//        return toAjax(inviteFriendsService.insertInviteFriends(inviteFriends));
//    }
//
//    /**
//     * 修改邀请好友
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:friends:edit')")
//    @Log(title = "邀请好友", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody InviteFriends inviteFriends)
//    {
//        return toAjax(inviteFriendsService.updateInviteFriends(inviteFriends));
//    }
//
//    /**
//     * 删除邀请好友
//     */
//    @PreAuthorize("@ss.hasPermi('qwk:friends:remove')")
//    @Log(title = "邀请好友", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(inviteFriendsService.deleteInviteFriendsByIds(ids));
//    }

    @Anonymous
    @ApiOperation("添加邀请人记录")
    @GetMapping("/addInviteFriends/{userId}")
    public AjaxResult addInviteFriends(HttpServletRequest request,@PathVariable("userId") Long userId){
//        String ip = request.getRemoteAddr();
        String ip = IpUtils.getIpAddr();
        System.out.println("-----请求地址IP:"+ip);
        InviteFriends inviteFriends = new InviteFriends();
        String sub = StrUtil.sub(ip, 0, ip.lastIndexOf("."));
        inviteFriends.setInviterId(userId);
        inviteFriends.setIp(sub);
        return toAjax(inviteFriendsService.insertInviteFriends(inviteFriends));
    }

}
