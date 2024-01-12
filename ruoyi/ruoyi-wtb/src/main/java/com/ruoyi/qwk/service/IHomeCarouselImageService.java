package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.HomeCarouselImage;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页轮播图 Service接口
 *
 * @author cjf
 * @date 2024-01-11
 */
public interface IHomeCarouselImageService
{
    /**
     * 查询首页轮播图
     *
     * @param id 首页轮播图 主键
     * @return 首页轮播图
     */
    public HomeCarouselImage selectHomeCarouselImageById(Long id);

    /**
     * 查询首页轮播图 列表
     *
     * @param homeCarouselImage 首页轮播图
     * @return 首页轮播图 集合
     */
    public List<HomeCarouselImage> selectHomeCarouselImageList(HomeCarouselImage homeCarouselImage);

    /**
     * 新增首页轮播图
     *
     * @param homeCarouselImage 首页轮播图
     * @return 结果
     */
    public int insertHomeCarouselImage(HomeCarouselImage homeCarouselImage);

    /**
     * 修改首页轮播图
     *
     * @param homeCarouselImage 首页轮播图
     * @return 结果
     */
    public int updateHomeCarouselImage(HomeCarouselImage homeCarouselImage);

    /**
     * 批量删除首页轮播图
     *
     * @param ids 需要删除的首页轮播图 主键集合
     * @return 结果
     */
    public int deleteHomeCarouselImageByIds(Long[] ids);

    /**
     * 删除首页轮播图 信息
     *
     * @param id 首页轮播图 主键
     * @return 结果
     */
    public int deleteHomeCarouselImageById(Long id);

    /**
     * 获取首页轮盘图
     * @return
     */
    public AjaxResult getList();
}
