package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.HomepageRotatingPictures;

/**
 *  首页轮播图片Service接口
 * 
 * @author cjf
 * @date 2023-11-27
 */
public interface IHomepageRotatingPicturesService 
{
    /**
     * 查询 首页轮播图片
     * 
     * @param id  首页轮播图片主键
     * @return  首页轮播图片
     */
    public HomepageRotatingPictures selectHomepageRotatingPicturesById(Long id);

    /**
     * 查询 首页轮播图片列表
     * 
     * @param homepageRotatingPictures  首页轮播图片
     * @return  首页轮播图片集合
     */
    public List<HomepageRotatingPictures> selectHomepageRotatingPicturesList(HomepageRotatingPictures homepageRotatingPictures);

    /**
     * 新增 首页轮播图片
     * 
     * @param homepageRotatingPictures  首页轮播图片
     * @return 结果
     */
    public int insertHomepageRotatingPictures(HomepageRotatingPictures homepageRotatingPictures);

    /**
     * 修改 首页轮播图片
     * 
     * @param homepageRotatingPictures  首页轮播图片
     * @return 结果
     */
    public int updateHomepageRotatingPictures(HomepageRotatingPictures homepageRotatingPictures);

    /**
     * 批量删除 首页轮播图片
     * 
     * @param ids 需要删除的 首页轮播图片主键集合
     * @return 结果
     */
    public int deleteHomepageRotatingPicturesByIds(Long[] ids);

    /**
     * 删除 首页轮播图片信息
     * 
     * @param id  首页轮播图片主键
     * @return 结果
     */
    public int deleteHomepageRotatingPicturesById(Long id);
}
