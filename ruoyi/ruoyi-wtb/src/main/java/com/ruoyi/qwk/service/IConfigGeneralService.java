package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.ConfigGeneral;

/**
 * app通用配置Service接口
 * 
 * @author cjf
 * @date 2023-08-29
 */
public interface IConfigGeneralService 
{
    /**
     * 查询app通用配置
     * 
     * @param id app通用配置主键
     * @return app通用配置
     */
    public ConfigGeneral selectConfigGeneralById(Long id);

    /**
     * 查询app通用配置列表
     * 
     * @param configGeneral app通用配置
     * @return app通用配置集合
     */
    public List<ConfigGeneral> selectConfigGeneralList(ConfigGeneral configGeneral);

    /**
     * 新增app通用配置
     * 
     * @param configGeneral app通用配置
     * @return 结果
     */
    public int insertConfigGeneral(ConfigGeneral configGeneral);

    /**
     * 修改app通用配置
     * 
     * @param configGeneral app通用配置
     * @return 结果
     */
    public int updateConfigGeneral(ConfigGeneral configGeneral);

    /**
     * 批量删除app通用配置
     * 
     * @param ids 需要删除的app通用配置主键集合
     * @return 结果
     */
    public int deleteConfigGeneralByIds(Long[] ids);

    /**
     * 删除app通用配置信息
     * 
     * @param id app通用配置主键
     * @return 结果
     */
    public int deleteConfigGeneralById(Long id);

    /**
     * 查询app通用配置
     *
     * @param type app通用配置标题
     * @return app通用配置
     */
    public ConfigGeneral selectConfigGeneralByType(String type);
}
