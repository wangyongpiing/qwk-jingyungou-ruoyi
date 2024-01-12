package com.ruoyi.qwk.service.impl;

import java.util.*;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.HomepageConfigMapper;
import com.ruoyi.qwk.domain.HomepageConfig;
import com.ruoyi.qwk.service.IHomepageConfigService;

/**
 * 首页配置 Service业务层处理
 * 
 * @author cjf
 * @date 2023-06-26
 */
@Service
public class HomepageConfigServiceImpl implements IHomepageConfigService 
{
    @Autowired
    private HomepageConfigMapper homepageConfigMapper;

    /**
     * 查询首页配置 
     * 
     * @param id 首页配置 主键
     * @return 首页配置 
     */
    @Override
    public HomepageConfig selectHomepageConfigById(Long id)
    {
        return homepageConfigMapper.selectHomepageConfigById(id);
    }

    /**
     * 查询首页配置 列表
     * 
     * @param homepageConfig 首页配置 
     * @return 首页配置 
     */
    @Override
    public List<HomepageConfig> selectHomepageConfigList(HomepageConfig homepageConfig)
    {
        return homepageConfigMapper.selectHomepageConfigList(homepageConfig);
    }

    /**
     * 新增首页配置 
     * 
     * @param homepageConfig 首页配置 
     * @return 结果
     */
    @Override
    public int insertHomepageConfig(HomepageConfig homepageConfig)
    {
        homepageConfig.setCreationTime(new Date());
        return homepageConfigMapper.insertHomepageConfig(homepageConfig);
    }

    /**
     * 修改首页配置 
     * 
     * @param homepageConfig 首页配置 
     * @return 结果
     */
    @Override
    public int updateHomepageConfig(HomepageConfig homepageConfig)
    {
//        homepageConfig.setUpdatedBy(SecurityUtils.getUsername());
//        homepageConfig.setUpdatedTime(new Date());
        return homepageConfigMapper.updateHomepageConfig(homepageConfig);
    }

    /**
     * 批量删除首页配置 
     * 
     * @param ids 需要删除的首页配置 主键
     * @return 结果
     */
    @Override
    public int deleteHomepageConfigByIds(Long[] ids)
    {
        return homepageConfigMapper.deleteHomepageConfigByIds(ids);
    }

    /**
     * 删除首页配置 信息
     * 
     * @param id 首页配置 主键
     * @return 结果
     */
    @Override
    public int deleteHomepageConfigById(Long id)
    {
        return homepageConfigMapper.deleteHomepageConfigById(id);
    }


    /**
     * 查询App首页配置 列表
     *
     *
     * @return 结果
     */
    @Override
    public Map<String, Object> getHomepageConfigByClassify(){
        Map<String, Object> map = new HashMap<>();
        List<HomepageConfig> carousel = homepageConfigMapper.getHomepageConfigByClassify(1L,5L);//轮播
        List<HomepageConfig> fineSelection = homepageConfigMapper.getHomepageConfigByClassify(2L,4L);//精选
        List<HomepageConfig> welfare = homepageConfigMapper.getHomepageConfigByClassify(3L,3L);//福利
        map.put("carousel",carousel);
        map.put("fineSelection",fineSelection);
        map.put("welfare",welfare);
        return map;
    }

}
