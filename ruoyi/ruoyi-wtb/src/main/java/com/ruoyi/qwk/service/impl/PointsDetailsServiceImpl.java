package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.PointsDetailsMapper;
import com.ruoyi.qwk.domain.PointsDetails;
import com.ruoyi.qwk.service.IPointsDetailsService;

/**
 * 仓库商品积分详情 Service业务层处理
 *
 * @author cjf
 * @date 2023-07-26
 */
@Service
public class PointsDetailsServiceImpl implements IPointsDetailsService
{
    @Autowired
    private PointsDetailsMapper pointsDetailsMapper;

    /**
     * 查询仓库商品积分详情
     *
     * @param id 仓库商品积分详情 主键
     * @return 仓库商品积分详情
     */
    @Override
    public PointsDetails selectPointsDetailsById(Long id)
    {
        return pointsDetailsMapper.selectPointsDetailsById(id);
    }

    /**
     * 查询仓库商品积分详情 列表
     *
     * @param pointsDetails 仓库商品积分详情
     * @return 仓库商品积分详情
     */
    @Override
    public List<PointsDetails> selectPointsDetailsList(PointsDetails pointsDetails)
    {
        return pointsDetailsMapper.selectPointsDetailsList(pointsDetails);
    }

    /**
     * 新增仓库商品积分详情
     *
     * @param pointsDetails 仓库商品积分详情
     * @return 结果
     */
    @Override
    public int insertPointsDetails(PointsDetails pointsDetails)
    {
        pointsDetails.setCreateTime(DateUtils.getNowDate());
        return pointsDetailsMapper.insertPointsDetails(pointsDetails);
    }

    /**
     * 修改仓库商品积分详情
     *
     * @param pointsDetails 仓库商品积分详情
     * @return 结果
     */
    @Override
    public int updatePointsDetails(PointsDetails pointsDetails)
    {
        pointsDetails.setUpdateTime(DateUtils.getNowDate());
        return pointsDetailsMapper.updatePointsDetails(pointsDetails);
    }

    /**
     * 批量删除仓库商品积分详情 
     *
     * @param ids 需要删除的仓库商品积分详情 主键
     * @return 结果
     */
    @Override
    public int deletePointsDetailsByIds(Long[] ids)
    {
        return pointsDetailsMapper.deletePointsDetailsByIds(ids);
    }

    /**
     * 删除仓库商品积分详情 信息
     *
     * @param id 仓库商品积分详情 主键
     * @return 结果
     */
    @Override
    public int deletePointsDetailsById(Long id)
    {
        return pointsDetailsMapper.deletePointsDetailsById(id);
    }



}
