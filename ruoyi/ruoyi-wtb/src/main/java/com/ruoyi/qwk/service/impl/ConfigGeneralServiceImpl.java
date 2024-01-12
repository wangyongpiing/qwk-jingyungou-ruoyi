package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.ConfigGeneralMapper;
import com.ruoyi.qwk.domain.ConfigGeneral;
import com.ruoyi.qwk.service.IConfigGeneralService;

/**
 * app通用配置Service业务层处理
 * 
 * @author cjf
 * @date 2023-08-29
 */
@Service
public class ConfigGeneralServiceImpl implements IConfigGeneralService 
{
    @Autowired
    private ConfigGeneralMapper configGeneralMapper;

    /**
     * 查询app通用配置
     * 
     * @param id app通用配置主键
     * @return app通用配置
     */
    @Override
    public ConfigGeneral selectConfigGeneralById(Long id)
    {
        return configGeneralMapper.selectConfigGeneralById(id);
    }

    /**
     * 查询app通用配置列表
     * 
     * @param configGeneral app通用配置
     * @return app通用配置
     */
    @Override
    public List<ConfigGeneral> selectConfigGeneralList(ConfigGeneral configGeneral)
    {
        return configGeneralMapper.selectConfigGeneralList(configGeneral);
    }

    /**
     * 新增app通用配置
     * 
     * @param configGeneral app通用配置
     * @return 结果
     */
    @Override
    public int insertConfigGeneral(ConfigGeneral configGeneral)
    {
        configGeneral.setCreateBy(SecurityUtils.getUsername());
        configGeneral.setIsDel("1");
        configGeneral.setCreateTime(DateUtils.getNowDate());
        return configGeneralMapper.insertConfigGeneral(configGeneral);
    }

    /**
     * 修改app通用配置
     * 
     * @param configGeneral app通用配置
     * @return 结果
     */
    @Override
    public int updateConfigGeneral(ConfigGeneral configGeneral)
    {
        configGeneral.setUpdateBy(SecurityUtils.getUsername());
        configGeneral.setUpdateTime(DateUtils.getNowDate());
        return configGeneralMapper.updateConfigGeneral(configGeneral);
    }

    /**
     * 批量删除app通用配置
     * 
     * @param ids 需要删除的app通用配置主键
     * @return 结果
     */
    @Override
    public int deleteConfigGeneralByIds(Long[] ids)
    {
        return configGeneralMapper.deleteConfigGeneralByIds(ids);
    }

    /**
     * 删除app通用配置信息
     * 
     * @param id app通用配置主键
     * @return 结果
     */
    @Override
    public int deleteConfigGeneralById(Long id)
    {
        return configGeneralMapper.deleteConfigGeneralById(id);
    }


    /**
     * 查询app通用配置
     *
     * @param type app通用配置标题
     * @return app通用配置
     */
    @Override
    public ConfigGeneral selectConfigGeneralByType(String type)
    {
        return configGeneralMapper.selectConfigGeneralByType(type);
    }
}
