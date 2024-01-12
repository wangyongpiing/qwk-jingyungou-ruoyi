package com.ruoyi.qwk.blindBox.service;

import java.util.List;
import com.ruoyi.qwk.blindBox.domain.RewardPackage;

/**
 * 奖励包Service接口
 * 
 * @author cjf
 * @date 2023-09-14
 */
public interface IRewardPackageService 
{
    /**
     * 查询奖励包
     * 
     * @param id 奖励包主键
     * @return 奖励包
     */
    public RewardPackage selectRewardPackageById(Long id);

    /**
     * 查询奖励包列表
     * 
     * @param rewardPackage 奖励包
     * @return 奖励包集合
     */
    public List<RewardPackage> selectRewardPackageList(RewardPackage rewardPackage);

    /**
     * 新增奖励包
     * 
     * @param rewardPackage 奖励包
     * @return 结果
     */
    public int insertRewardPackage(RewardPackage rewardPackage);

    /**
     * 修改奖励包
     * 
     * @param rewardPackage 奖励包
     * @return 结果
     */
    public int updateRewardPackage(RewardPackage rewardPackage);

    /**
     * 批量删除奖励包
     * 
     * @param ids 需要删除的奖励包主键集合
     * @return 结果
     */
    public int deleteRewardPackageByIds(Long[] ids);

    /**
     * 删除奖励包信息
     * 
     * @param id 奖励包主键
     * @return 结果
     */
    public int deleteRewardPackageById(Long id);
}
