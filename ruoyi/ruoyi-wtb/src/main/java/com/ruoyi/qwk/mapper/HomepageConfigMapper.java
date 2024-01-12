package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.HomepageConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 首页配置 Mapper接口
 * 
 * @author cjf
 * @date 2023-06-26
 */
@DataSource(value = DataSourceType.SLAVE)
public interface HomepageConfigMapper 
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
     * 删除首页配置 
     * 
     * @param id 首页配置 主键
     * @return 结果
     */
    public int deleteHomepageConfigById(Long id);

    /**
     * 批量删除首页配置 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomepageConfigByIds(Long[] ids);

    /**
     * 查询App首页配置 列表
     *
     * @param classify 分类
     * @return 首页配置 集合
     */
    public List<HomepageConfig> getHomepageConfigByClassify(@Param("classify") Long classify,@Param("number") Long number );

}
