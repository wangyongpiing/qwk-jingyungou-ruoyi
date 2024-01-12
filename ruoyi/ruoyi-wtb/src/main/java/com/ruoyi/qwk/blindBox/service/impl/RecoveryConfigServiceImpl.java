package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.RecoveryConfigMapper;
import com.ruoyi.qwk.blindBox.domain.RecoveryConfig;
import com.ruoyi.qwk.blindBox.service.IRecoveryConfigService;

/**
 * 玩家商品自动回收配置Service业务层处理
 * 
 * @author cjf
 * @date 2023-10-25
 */
@Service
public class RecoveryConfigServiceImpl implements IRecoveryConfigService 
{
    @Autowired
    private RecoveryConfigMapper recoveryConfigMapper;

    /**
     * 查询玩家商品自动回收配置
     * 
     * @param id 玩家商品自动回收配置主键
     * @return 玩家商品自动回收配置
     */
    @Override
    public RecoveryConfig selectRecoveryConfigById(Long id)
    {
        return recoveryConfigMapper.selectRecoveryConfigById(id);
    }

    /**
     * 查询玩家商品自动回收配置列表
     * 
     * @param recoveryConfig 玩家商品自动回收配置
     * @return 玩家商品自动回收配置
     */
    @Override
    public List<RecoveryConfig> selectRecoveryConfigList(RecoveryConfig recoveryConfig)
    {
        return recoveryConfigMapper.selectRecoveryConfigList(recoveryConfig);
    }

    /**
     * 新增玩家商品自动回收配置
     * 
     * @param recoveryConfig 玩家商品自动回收配置
     * @return 结果
     */
    @Override
    public int insertRecoveryConfig(RecoveryConfig recoveryConfig)
    {
        recoveryConfig.setCreateTime(DateUtils.getNowDate());
        return recoveryConfigMapper.insertRecoveryConfig(recoveryConfig);
    }

    /**
     * 修改玩家商品自动回收配置
     * 
     * @param recoveryConfig 玩家商品自动回收配置
     * @return 结果
     */
    @Override
    public int updateRecoveryConfig(RecoveryConfig recoveryConfig)
    {
        recoveryConfig.setUpdateTime(DateUtils.getNowDate());
        return recoveryConfigMapper.updateRecoveryConfig(recoveryConfig);
    }

    /**
     * 批量删除玩家商品自动回收配置
     * 
     * @param ids 需要删除的玩家商品自动回收配置主键
     * @return 结果
     */
    @Override
    public int deleteRecoveryConfigByIds(Long[] ids)
    {
        return recoveryConfigMapper.deleteRecoveryConfigByIds(ids);
    }

    /**
     * 删除玩家商品自动回收配置信息
     * 
     * @param id 玩家商品自动回收配置主键
     * @return 结果
     */
    @Override
    public int deleteRecoveryConfigById(Long id)
    {
        return recoveryConfigMapper.deleteRecoveryConfigById(id);
    }
}
