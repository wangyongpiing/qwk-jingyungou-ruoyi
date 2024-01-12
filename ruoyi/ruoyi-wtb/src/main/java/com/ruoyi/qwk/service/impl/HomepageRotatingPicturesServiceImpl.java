package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.HomepageRotatingPicturesMapper;
import com.ruoyi.qwk.domain.HomepageRotatingPictures;
import com.ruoyi.qwk.service.IHomepageRotatingPicturesService;

/**
 *  首页轮播图片Service业务层处理
 * 
 * @author cjf
 * @date 2023-11-27
 */
@Service
public class HomepageRotatingPicturesServiceImpl implements IHomepageRotatingPicturesService 
{
    @Autowired
    private HomepageRotatingPicturesMapper homepageRotatingPicturesMapper;

    /**
     * 查询 首页轮播图片
     * 
     * @param id  首页轮播图片主键
     * @return  首页轮播图片
     */
    @Override
    public HomepageRotatingPictures selectHomepageRotatingPicturesById(Long id)
    {
        return homepageRotatingPicturesMapper.selectHomepageRotatingPicturesById(id);
    }

    /**
     * 查询 首页轮播图片列表
     * 
     * @param homepageRotatingPictures  首页轮播图片
     * @return  首页轮播图片
     */
    @Override
    public List<HomepageRotatingPictures> selectHomepageRotatingPicturesList(HomepageRotatingPictures homepageRotatingPictures)
    {
        return homepageRotatingPicturesMapper.selectHomepageRotatingPicturesList(homepageRotatingPictures);
    }

    /**
     * 新增 首页轮播图片
     * 
     * @param homepageRotatingPictures  首页轮播图片
     * @return 结果
     */
    @Override
    public int insertHomepageRotatingPictures(HomepageRotatingPictures homepageRotatingPictures)
    {
        homepageRotatingPictures.setCreateTime(DateUtils.getNowDate());
        return homepageRotatingPicturesMapper.insertHomepageRotatingPictures(homepageRotatingPictures);
    }

    /**
     * 修改 首页轮播图片
     * 
     * @param homepageRotatingPictures  首页轮播图片
     * @return 结果
     */
    @Override
    public int updateHomepageRotatingPictures(HomepageRotatingPictures homepageRotatingPictures)
    {
        homepageRotatingPictures.setUpdateTime(DateUtils.getNowDate());
        return homepageRotatingPicturesMapper.updateHomepageRotatingPictures(homepageRotatingPictures);
    }

    /**
     * 批量删除 首页轮播图片
     * 
     * @param ids 需要删除的 首页轮播图片主键
     * @return 结果
     */
    @Override
    public int deleteHomepageRotatingPicturesByIds(Long[] ids)
    {
        return homepageRotatingPicturesMapper.deleteHomepageRotatingPicturesByIds(ids);
    }

    /**
     * 删除 首页轮播图片信息
     * 
     * @param id  首页轮播图片主键
     * @return 结果
     */
    @Override
    public int deleteHomepageRotatingPicturesById(Long id)
    {
        return homepageRotatingPicturesMapper.deleteHomepageRotatingPicturesById(id);
    }
}
