package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.Prize;

/**
 * 奖品 Service接口
 * 
 * @author cjf
 * @date 2023-12-20
 */
public interface IPrizeService 
{
    /**
     * 查询奖品 
     * 
     * @param id 奖品 主键
     * @return 奖品 
     */
    public Prize selectPrizeById(Long id);

    /**
     * 查询奖品 列表
     * 
     * @param prize 奖品 
     * @return 奖品 集合
     */
    public List<Prize> selectPrizeList(Prize prize);

    /**
     * 新增奖品 
     * 
     * @param prize 奖品 
     * @return 结果
     */
    public int insertPrize(Prize prize);

    /**
     * 修改奖品 
     * 
     * @param prize 奖品 
     * @return 结果
     */
    public int updatePrize(Prize prize);

    /**
     * 批量删除奖品 
     * 
     * @param ids 需要删除的奖品 主键集合
     * @return 结果
     */
    public int deletePrizeByIds(Long[] ids);

    /**
     * 删除奖品 信息
     * 
     * @param id 奖品 主键
     * @return 结果
     */
    public int deletePrizeById(Long id);
}
