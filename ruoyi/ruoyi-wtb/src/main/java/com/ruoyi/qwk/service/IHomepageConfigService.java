package com.ruoyi.qwk.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.qwk.domain.HomepageConfig;

/**
 * 首页配置 Service接口
 * 
 * @author cjf
 * @date 2023-06-26
 */
public interface IHomepageConfigService 
{
    /**
     * 查询首页配置 
     * 
     * @param id 首页配置 主键
     * @return 首页配置 
     */
    public HomepageConfig selectHomepageConfigById(Long id);

    /**
     * 查询首页配置 列表
     * 
     * @param homepageConfig 首页配置 
     * @return 首页配置 集合
     */
    public List<HomepageConfig> selectHomepageConfigList(HomepageConfig homepageConfig);

    /**
     * 新增首页配置 
     * 
     * @param homepageConfig 首页配置 
     * @return 结果
     */
    public int insertHomepageConfig(HomepageConfig homepageConfig);

    /**
     * 修改首页配置 
     * 
     * @param homepageConfig 首页配置 
     * @return 结果
     */
    public int updateHomepageConfig(HomepageConfig homepageConfig);

    /**
     * 批量删除首页配置 
     * 
     * @param ids 需要删除的首页配置 主键集合
     * @return 结果
     */
    public int deleteHomepageConfigByIds(Long[] ids);

    /**
     * 删除首页配置 信息
     * 
     * @param id 首页配置 主键
     * @return 结果
     */
    public int deleteHomepageConfigById(Long id);

    /**
     * 查询App首页配置 列表
     *
     *
     * @return 结果
     */
    public Map<String, Object> getHomepageConfigByClassify();
}
