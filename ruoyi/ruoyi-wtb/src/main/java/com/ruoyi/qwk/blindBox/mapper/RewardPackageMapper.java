package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.RewardPackage;
import org.apache.ibatis.annotations.Param;

/**
 * 奖励包Mapper接口
 * 
 * @author cjf
 * @date 2023-09-14
 */
@DataSource(value = DataSourceType.SLAVE)
public interface RewardPackageMapper 
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
     * 删除奖励包
     * 
     * @param id 奖励包主键
     * @return 结果
     */
    public int deleteRewardPackageById(Long id);

    /**
     * 批量删除奖励包
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRewardPackageByIds(Long[] ids);

    /**
     * 根据奖励包id查询奖励包集合
     * */
    public List<RewardPackage> selectRewardPackageAndLootReward(@Param("ids") List<Long> ids);

}
