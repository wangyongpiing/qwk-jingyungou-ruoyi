package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.PrizeMapper;
import com.ruoyi.qwk.domain.Prize;
import com.ruoyi.qwk.service.IPrizeService;

/**
 * 奖品 Service业务层处理
 * 
 * @author cjf
 * @date 2023-12-20
 */
@Service
public class PrizeServiceImpl implements IPrizeService 
{
    @Autowired
    private PrizeMapper prizeMapper;

    /**
     * 查询奖品 
     * 
     * @param id 奖品 主键
     * @return 奖品 
     */
    @Override
    public Prize selectPrizeById(Long id)
    {
        return prizeMapper.selectPrizeById(id);
    }

    /**
     * 查询奖品 列表
     * 
     * @param prize 奖品 
     * @return 奖品 
     */
    @Override
    public List<Prize> selectPrizeList(Prize prize)
    {
        return prizeMapper.selectPrizeList(prize);
    }

    /**
     * 新增奖品 
     * 
     * @param prize 奖品 
     * @return 结果
     */
    @Override
    public int insertPrize(Prize prize)
    {
        prize.setCreateTime(DateUtils.getNowDate());
        return prizeMapper.insertPrize(prize);
    }

    /**
     * 修改奖品 
     * 
     * @param prize 奖品 
     * @return 结果
     */
    @Override
    public int updatePrize(Prize prize)
    {
        prize.setUpdateTime(DateUtils.getNowDate());
        return prizeMapper.updatePrize(prize);
    }

    /**
     * 批量删除奖品 
     * 
     * @param ids 需要删除的奖品 主键
     * @return 结果
     */
    @Override
    public int deletePrizeByIds(Long[] ids)
    {
        return prizeMapper.deletePrizeByIds(ids);
    }

    /**
     * 删除奖品 信息
     * 
     * @param id 奖品 主键
     * @return 结果
     */
    @Override
    public int deletePrizeById(Long id)
    {
        return prizeMapper.deletePrizeById(id);
    }
}
