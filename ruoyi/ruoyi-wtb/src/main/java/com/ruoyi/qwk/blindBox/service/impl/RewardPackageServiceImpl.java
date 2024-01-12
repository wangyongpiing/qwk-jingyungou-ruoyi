package com.ruoyi.qwk.blindBox.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.RewardPackageMapper;
import com.ruoyi.qwk.blindBox.domain.RewardPackage;
import com.ruoyi.qwk.blindBox.service.IRewardPackageService;

/**
 * 奖励包Service业务层处理
 * 
 * @author cjf
 * @date 2023-09-14
 */
@Service
public class RewardPackageServiceImpl implements IRewardPackageService 
{
    @Autowired
    private RewardPackageMapper rewardPackageMapper;

    /**
     * 查询奖励包
     * 
     * @param id 奖励包主键
     * @return 奖励包
     */
    @Override
    public RewardPackage selectRewardPackageById(Long id)
    {
        return rewardPackageMapper.selectRewardPackageById(id);
    }

    /**
     * 查询奖励包列表
     * 
     * @param rewardPackage 奖励包
     * @return 奖励包
     */
    @Override
    public List<RewardPackage> selectRewardPackageList(RewardPackage rewardPackage)
    {
        return rewardPackageMapper.selectRewardPackageList(rewardPackage);
//        String s = "1,2";
//        ArrayList<Long> longs = new ArrayList<>();
//        longs.add(1L);
//        longs.add(2L);
//        return rewardPackageMapper.selectRewardPackageAndLootReward(longs);
    }

    /**
     * 新增奖励包
     * 
     * @param rewardPackage 奖励包
     * @return 结果
     */
    @Override
    public int insertRewardPackage(RewardPackage rewardPackage)
    {
        rewardPackage.setCreateTime(DateUtils.getNowDate());
        return rewardPackageMapper.insertRewardPackage(rewardPackage);
    }

    /**
     * 修改奖励包
     * 
     * @param rewardPackage 奖励包
     * @return 结果
     */
    @Override
    public int updateRewardPackage(RewardPackage rewardPackage)
    {
        rewardPackage.setUpdateTime(DateUtils.getNowDate());
        return rewardPackageMapper.updateRewardPackage(rewardPackage);
    }

    /**
     * 批量删除奖励包
     * 
     * @param ids 需要删除的奖励包主键
     * @return 结果
     */
    @Override
    public int deleteRewardPackageByIds(Long[] ids)
    {
        return rewardPackageMapper.deleteRewardPackageByIds(ids);
    }

    /**
     * 删除奖励包信息
     * 
     * @param id 奖励包主键
     * @return 结果
     */
    @Override
    public int deleteRewardPackageById(Long id)
    {
        return rewardPackageMapper.deleteRewardPackageById(id);
    }
}
