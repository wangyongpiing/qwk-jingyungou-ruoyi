package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.HomeCarouselImage;

/**
 * 首页轮播图 Mapper接口
 *
 * @author cjf
 * @date 2024-01-11
 */
@DataSource(value = DataSourceType.SLAVE)
public interface HomeCarouselImageMapper
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
     * 删除首页轮播图
     *
     * @param id 首页轮播图 主键
     * @return 结果
     */
    public int deleteHomeCarouselImageById(Long id);

    /**
     * 批量删除首页轮播图
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHomeCarouselImageByIds(Long[] ids);
}
