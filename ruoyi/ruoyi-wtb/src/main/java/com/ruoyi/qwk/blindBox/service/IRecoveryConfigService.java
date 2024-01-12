package com.ruoyi.qwk.blindBox.service;

import java.util.List;
import com.ruoyi.qwk.blindBox.domain.RecoveryConfig;

/**
 * 玩家商品自动回收配置Service接口
 * 
 * @author cjf
 * @date 2023-10-25
 */
public interface IRecoveryConfigService 
{
    /**
     * 查询玩家商品自动回收配置
     * 
     * @param id 玩家商品自动回收配置主键
     * @return 玩家商品自动回收配置
     */
    public RecoveryConfig selectRecoveryConfigById(Long id);

    /**
     * 查询玩家商品自动回收配置列表
     * 
     * @param recoveryConfig 玩家商品自动回收配置
     * @return 玩家商品自动回收配置集合
     */
    public List<RecoveryConfig> selectRecoveryConfigList(RecoveryConfig recoveryConfig);

    /**
     * 新增玩家商品自动回收配置
     * 
     * @param recoveryConfig 玩家商品自动回收配置
     * @return 结果
     */
    public int insertRecoveryConfig(RecoveryConfig recoveryConfig);

    /**
     * 修改玩家商品自动回收配置
     * 
     * @param recoveryConfig 玩家商品自动回收配置
     * @return 结果
     */
    public int updateRecoveryConfig(RecoveryConfig recoveryConfig);

    /**
     * 批量删除玩家商品自动回收配置
     * 
     * @param ids 需要删除的玩家商品自动回收配置主键集合
     * @return 结果
     */
    public int deleteRecoveryConfigByIds(Long[] ids);

    /**
     * 删除玩家商品自动回收配置信息
     * 
     * @param id 玩家商品自动回收配置主键
     * @return 结果
     */
    public int deleteRecoveryConfigById(Long id);
}
