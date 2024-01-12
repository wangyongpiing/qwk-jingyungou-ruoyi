package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.xorpay.QRCodeUtils;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.qwk.domain.Goods;
import com.ruoyi.qwk.domain.InviteRewards;
import com.ruoyi.qwk.service.IGoodsService;
import com.ruoyi.qwk.service.IInviteRewardsRecordService;
import com.ruoyi.qwk.service.IInviteRewardsService;
import com.ruoyi.qwk.service.IInviteSubordinateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户邀请奖励Controller
 *
 * @author cjf
 * @date 2023-10-09
 */
@RestController
@RequestMapping("/qwk/inviteRewards")
@Api(tags = "用户邀请奖励API")
public class InviteRewardsController extends BaseController
{
    @Autowired
    private IInviteRewardsService inviteRewardsService;

    @Autowired
    private IInviteRewardsRecordService inviteRewardsRecordService;

    @Autowired
    private IInviteSubordinateService inviteSubordinateService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ServerConfig serverConfig;

    // 链接地址
    @Value("${share.url}")
    private String shareUrl;



    /**
     * 查询用户邀请奖励列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewards:list')")
    @GetMapping("/list")
    public TableDataInfo list(InviteRewards inviteRewards)
    {
        startPage();
        List<InviteRewards> list = inviteRewardsService.selectInviteRewardsList(inviteRewards);
        return getDataTable(list);
    }

    /**
     * 导出用户邀请奖励列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewards:export')")
    @Log(title = "用户邀请奖励", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InviteRewards inviteRewards)
    {
        List<InviteRewards> list = inviteRewardsService.selectInviteRewardsList(inviteRewards);
        ExcelUtil<InviteRewards> util = new ExcelUtil<InviteRewards>(InviteRewards.class);
        util.exportExcel(response, list, "用户邀请奖励数据");
    }

    /**
     * 获取用户邀请奖励详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewards:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inviteRewardsService.selectInviteRewardsById(id));
    }

    /**
     * 新增用户邀请奖励
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewards:add')")
    @Log(title = "用户邀请奖励", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InviteRewards inviteRewards)
    {
        return toAjax(inviteRewardsService.insertInviteRewards(inviteRewards));
    }

    /**
     * 修改用户邀请奖励
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewards:edit')")
    @Log(title = "用户邀请奖励", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InviteRewards inviteRewards)
    {
        return toAjax(inviteRewardsService.updateInviteRewards(inviteRewards));
    }

    /**
     * 删除用户邀请奖励
     */
    @PreAuthorize("@ss.hasPermi('qwk:inviteRewards:remove')")
    @Log(title = "用户邀请奖励", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inviteRewardsService.deleteInviteRewardsByIds(ids));
    }


//    -------------------------------------app-----------------------------------------------------

    @GetMapping("/getInviteRewards")
    @ApiOperation("获取邀请收益等信息")
    public AjaxResult getInviteRewards(){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        InviteRewards inviteRewards = inviteRewardsService.selectByUserId(userId);
        Double todayEarnings = inviteRewardsRecordService.todayEarnings(userId);
        Integer todayPeople = inviteSubordinateService.todayPeople(userId);
        Integer totalPeople = inviteSubordinateService.totalPeople(userId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("cumulativeGain", StringUtils.doubleTrans(inviteRewards.getIncome()));
        map.put("todayEarnings",StringUtils.doubleTrans(todayEarnings));
        map.put("todayPeople",todayPeople);
        map.put("totalPeople",totalPeople);
        map.put("invitationLink","https://www.hbqwk.com/download/?userId="+userId);
        return AjaxResult.success(map);
    }

//    https://www.hbqwk.com/assistance/?userId=1&imgUrl=https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/uploadPath/2023/10/15/917b9c778a02a062_20231015203735A055.jpg&goodsName=%E6%B5%8B%E8%AF%95
    @GetMapping("/getAssistanceUrl/{goodsId}")
    @ApiOperation("获取助力链接")
    public AjaxResult getAssistanceUrl(@PathVariable("goodsId") Long goodsId){
        Goods goods = goodsService.selectGoodsById(goodsId);
        List<String> list = Arrays.asList(goods.getImage().split(","));
        HashMap<String, String> map = new HashMap<>();
        String url = "https://www.hbqwk.com/assistance/?userId="+SecurityUtils.getLoginUser().getUser().getUserId()
                      +"&imgUrl="+list.get(0)+"&goodsName="+goods.getName();
        map.put("url",url);
        return success(map);
    }


    /**
     * 生肖宝App获取分享链接
     * @return
     */
    @GetMapping("/getShareLink")
    @ApiOperation("生肖宝App获取分享链接")
    public AjaxResult getShareLink()throws Exception {
        Map<String,Object> map = new HashMap<>();
        //获取用户信息
        SysUser user = SecurityUtils.getLoginUser().getUser();
        String sharUrl = shareUrl+"?userId="+user.getUserId();
        map.put("shareUrl",sharUrl);
        //生成二维码
        //得到BufferedImage对象
        BufferedImage bufferedImage = QRCodeUtils.createImage("utf-8",sharUrl,300,300);
        //创建一个ByteArrayOutputStream
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //把BufferedImage写入ByteArrayOutputStream
        ImageIO.write(bufferedImage, "jpg", os);
        //ByteArrayOutputStream转成InputStream
        InputStream input = new ByteArrayInputStream(os.toByteArray());
        //InputStream转成MultipartFile
        MultipartFile multipartFile =new MockMultipartFile("file", "file.jpg", "text/plain", input);
        // 上传文件路径
        String filePath = RuoYiConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, multipartFile);
        String url = fileName;
        map.put("url", url);
        map.put("fileName", fileName);
        map.put("newFileName", FileUtils.getName(fileName));
        map.put("originalFilename", multipartFile.getOriginalFilename());
        return AjaxResult.success(map);
    }

}
