package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.Versioning;

/**
 * 版本管理Service接口
 * 
 * @author cjf
 * @date 2023-08-25
 */
public interface IVersioningService 
{
    /**
     * 查询版本管理
     * 
     * @param id 版本管理主键
     * @return 版本管理
     */
    public Versioning selectVersioningById(Long id);

    /**
     * 查询版本管理列表
     * 
     * @param versioning 版本管理
     * @return 版本管理集合
     */
    public List<Versioning> selectVersioningList(Versioning versioning);

    /**
     * 新增版本管理
     * 
     * @param versioning 版本管理
     * @return 结果
     */
    public int insertVersioning(Versioning versioning);

    /**
     * 修改版本管理
     * 
     * @param versioning 版本管理
     * @return 结果
     */
    public int updateVersioning(Versioning versioning);

    /**
     * 批量删除版本管理
     * 
     * @param ids 需要删除的版本管理主键集合
     * @return 结果
     */
    public int deleteVersioningByIds(Long[] ids);

    /**
     * 删除版本管理信息
     * 
     * @param id 版本管理主键
     * @return 结果
     */
    public int deleteVersioningById(Long id);

    /**
     * 获取最新的版本信息
     *
     * @return 结果
     */
    public Versioning getLatest();

}
