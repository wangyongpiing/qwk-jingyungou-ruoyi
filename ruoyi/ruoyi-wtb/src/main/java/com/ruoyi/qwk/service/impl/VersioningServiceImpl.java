package com.ruoyi.qwk.service.impl;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.VersioningMapper;
import com.ruoyi.qwk.domain.Versioning;
import com.ruoyi.qwk.service.IVersioningService;

/**
 * 版本管理Service业务层处理
 * 
 * @author cjf
 * @date 2023-08-25
 */
@Service
public class VersioningServiceImpl implements IVersioningService 
{
    @Autowired
    private VersioningMapper versioningMapper;

    /**
     * 查询版本管理
     * 
     * @param id 版本管理主键
     * @return 版本管理
     */
    @Override
    public Versioning selectVersioningById(Long id)
    {
        return versioningMapper.selectVersioningById(id);
    }

    /**
     * 查询版本管理列表
     * 
     * @param versioning 版本管理
     * @return 版本管理
     */
    @Override
    public List<Versioning> selectVersioningList(Versioning versioning){
        return versioningMapper.selectVersioningList(versioning);
    }

    /**
     * 新增版本管理
     * 
     * @param versioning 版本管理
     * @return 结果
     */
    @Override
    public int insertVersioning(Versioning versioning){
        Integer fileLength = 0;
        try {
           fileLength = FileUtils.getFileLength(versioning.getFileUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer size = (fileLength/1024)/1024;
        versioning.setFileSize(size.toString());
        versioning.setCreateBy(SecurityUtils.getUsername());
        versioning.setCreateTime(DateUtils.getNowDate());
        return versioningMapper.insertVersioning(versioning);
    }

    /**
     * 修改版本管理
     * 
     * @param versioning 版本管理
     * @return 结果
     */
    @Override
    public int updateVersioning(Versioning versioning){
        versioning.setUpdateTime(DateUtils.getNowDate());
        return versioningMapper.updateVersioning(versioning);
    }

    /**
     * 批量删除版本管理
     * 
     * @param ids 需要删除的版本管理主键
     * @return 结果
     */
    @Override
    public int deleteVersioningByIds(Long[] ids){
//        for (Long id : ids) {
//            delFile(id);
//        }
        return versioningMapper.deleteVersioningByIds(ids);
    }

    /**
     * 删除版本管理信息
     * 
     * @param id 版本管理主键
     * @return 结果
     */
    @Override
    public int deleteVersioningById(Long id){
        Versioning versioning = versioningMapper.selectVersioningById(id);
        // 本地资源路径
        String localPath = RuoYiConfig.getProfile();
        // 数据库资源地址
        String path = localPath + StringUtils.substringAfter(versioning.getFileUrl(), Constants.RESOURCE_PREFIX);
        FileUtils.deleteFile(path);
        return versioningMapper.deleteVersioningById(id);
    }

    /**
     * 获取最新的版本信息
     *
     * @return 结果
     */
    public Versioning getLatest(){
        return versioningMapper.getLatest();
    }

    //删除文件
    private void delFile(Long id){
        Versioning versioning = versioningMapper.selectVersioningById(id);
        // 数据库资源地址
        String path = RuoYiConfig.getProfile() + StringUtils.substringAfter(versioning.getFileUrl(), Constants.RESOURCE_PREFIX);
        FileUtils.deleteFile(path);
    }

}
