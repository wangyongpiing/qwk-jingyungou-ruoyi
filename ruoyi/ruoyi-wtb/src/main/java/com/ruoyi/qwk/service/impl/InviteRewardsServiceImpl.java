package com.ruoyi.qwk.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.xorpay.QRCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.InviteRewardsMapper;
import com.ruoyi.qwk.domain.InviteRewards;
import com.ruoyi.qwk.service.IInviteRewardsService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

/**
 * 用户邀请奖励Service业务层处理
 *
 * @author cjf
 * @date 2023-10-09
 */
@Service
public class InviteRewardsServiceImpl implements IInviteRewardsService
{
    @Autowired
    private InviteRewardsMapper inviteRewardsMapper;



    /**
     * 查询用户邀请奖励
     *
     * @param id 用户邀请奖励主键
     * @return 用户邀请奖励
     */
    @Override
    public InviteRewards selectInviteRewardsById(Long id)
    {
        return inviteRewardsMapper.selectInviteRewardsById(id);
    }

    /**
     * 查询用户邀请奖励列表
     *
     * @param inviteRewards 用户邀请奖励
     * @return 用户邀请奖励
     */
    @Override
    public List<InviteRewards> selectInviteRewardsList(InviteRewards inviteRewards)
    {
        return inviteRewardsMapper.selectInviteRewardsList(inviteRewards);
    }

    /**
     * 新增用户邀请奖励
     *
     * @param inviteRewards 用户邀请奖励
     * @return 结果
     */
    @Override
    public int insertInviteRewards(InviteRewards inviteRewards)
    {
        inviteRewards.setCreateTime(DateUtils.getNowDate());
        return inviteRewardsMapper.insertInviteRewards(inviteRewards);
    }

    /**
     * 修改用户邀请奖励
     *
     * @param inviteRewards 用户邀请奖励
     * @return 结果
     */
    @Override
    public int updateInviteRewards(InviteRewards inviteRewards)
    {
        inviteRewards.setUpdateTime(DateUtils.getNowDate());
        return inviteRewardsMapper.updateInviteRewards(inviteRewards);
    }

    /**
     * 批量删除用户邀请奖励
     *
     * @param ids 需要删除的用户邀请奖励主键
     * @return 结果
     */
    @Override
    public int deleteInviteRewardsByIds(Long[] ids)
    {
        return inviteRewardsMapper.deleteInviteRewardsByIds(ids);
    }

    /**
     * 删除用户邀请奖励信息
     *
     * @param id 用户邀请奖励主键
     * @return 结果
     */
    @Override
    public int deleteInviteRewardsById(Long id)
    {
        return inviteRewardsMapper.deleteInviteRewardsById(id);
    }


    /**
     * 根据用户id查询
     * */
    @Override
    public InviteRewards selectByUserId(Long userId){
        InviteRewards inviteRewards = inviteRewardsMapper.selectByUserId(userId);
        if (ObjectUtils.isEmpty(inviteRewards)){
            inviteRewards = new InviteRewards();
            inviteRewards.setIncome(0.0);
            inviteRewards.setUserId(userId);
            inviteRewards.setUserName(SecurityUtils.getLoginUser().getUser().getUserName());
            inviteRewards.setCreateTime(new Date());
            inviteRewards.setCreateBy(SecurityUtils.getLoginUser().getUser().getUserName());
            inviteRewardsMapper.insertInviteRewards(inviteRewards);
        }
        return inviteRewards;
    }

}
