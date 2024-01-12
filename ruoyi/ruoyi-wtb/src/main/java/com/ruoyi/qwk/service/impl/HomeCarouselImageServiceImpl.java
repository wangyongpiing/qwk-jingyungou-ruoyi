package com.ruoyi.qwk.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.HomeCarouselImageMapper;
import com.ruoyi.qwk.domain.HomeCarouselImage;
import com.ruoyi.qwk.service.IHomeCarouselImageService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页轮播图 Service业务层处理
 *
 * @author cjf
 * @date 2024-01-11
 */
@Service
public class HomeCarouselImageServiceImpl implements IHomeCarouselImageService
{
    @Autowired
    private HomeCarouselImageMapper homeCarouselImageMapper;

    /**
     * 查询首页轮播图
     *
     * @param id 首页轮播图 主键
     * @return 首页轮播图
     */
    @Override
    public HomeCarouselImage selectHomeCarouselImageById(Long id)
    {
        return homeCarouselImageMapper.selectHomeCarouselImageById(id);
    }

    /**
     * 查询首页轮播图 列表
     *
     * @param homeCarouselImage 首页轮播图
     * @return 首页轮播图
     */
    @Override
    public List<HomeCarouselImage> selectHomeCarouselImageList(HomeCarouselImage homeCarouselImage)
    {
        return homeCarouselImageMapper.selectHomeCarouselImageList(homeCarouselImage);
    }

    /**
     * 新增首页轮播图
     *
     * @param homeCarouselImage 首页轮播图
     * @return 结果
     */
    @Override
    public int insertHomeCarouselImage(HomeCarouselImage homeCarouselImage)
    {
        homeCarouselImage.setCreateTime(DateUtils.getNowDate());
        return homeCarouselImageMapper.insertHomeCarouselImage(homeCarouselImage);
    }

    /**
     * 修改首页轮播图
     *
     * @param homeCarouselImage 首页轮播图
     * @return 结果
     */
    @Override
    public int updateHomeCarouselImage(HomeCarouselImage homeCarouselImage)
    {
        homeCarouselImage.setUpdateTime(DateUtils.getNowDate());
        return homeCarouselImageMapper.updateHomeCarouselImage(homeCarouselImage);
    }

    /**
     * 批量删除首页轮播图
     *
     * @param ids 需要删除的首页轮播图 主键
     * @return 结果
     */
    @Override
    public int deleteHomeCarouselImageByIds(Long[] ids)
    {
        return homeCarouselImageMapper.deleteHomeCarouselImageByIds(ids);
    }

    /**
     * 删除首页轮播图 信息
     *
     * @param id 首页轮播图 主键
     * @return 结果
     */
    @Override
    public int deleteHomeCarouselImageById(Long id)
    {
        return homeCarouselImageMapper.deleteHomeCarouselImageById(id);
    }

    /**
     * 获取首页轮盘图
     * @return
     */
    public AjaxResult getList(){
        HomeCarouselImage image = new HomeCarouselImage();
        image.setStatus("0");
        List<HomeCarouselImage> list = homeCarouselImageMapper.selectHomeCarouselImageList(image);
        return AjaxResult.success(list);
    }
}
