package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.PointsDetails;

/**
 * 仓库商品积分详情 Mapper接口
 * 
 * @author cjf
 * @date 2023-07-10
 */
@DataSource(value = DataSourceType.SLAVE)
public interface PointsDetailsMapper 
{
    /**
     * 查询仓库商品积分详情 
     * 
     * @param id 仓库商品积分详情 主键
     * @return 仓库商品积分详情 
     */
    public PointsDetails selectPointsDetailsById(Long id);

    /**
     * 查询仓库商品积分详情 列表
     * 
     * @param pointsDetails 仓库商品积分详情 
     * @return 仓库商品积分详情 集合
     */
    public List<PointsDetails> selectPointsDetailsList(PointsDetails pointsDetails);

    /**
     * 新增仓库商品积分详情 
     * 
     * @param pointsDetails 仓库商品积分详情 
     * @return 结果
     */
    public int insertPointsDetails(PointsDetails pointsDetails);

    /**
     * 修改仓库商品积分详情 
     * 
     * @param pointsDetails 仓库商品积分详情 
     * @return 结果
     */
    public int updatePointsDetails(PointsDetails pointsDetails);

    /**
     * 删除仓库商品积分详情 
     * 
     * @param id 仓库商品积分详情 主键
     * @return 结果
     */
    public int deletePointsDetailsById(Long id);

    /**
     * 批量删除仓库商品积分详情 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePointsDetailsByIds(Long[] ids);
}
