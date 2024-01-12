package com.ruoyi.qwk.blindBox.service;

import java.util.List;
import com.ruoyi.qwk.blindBox.domain.Jackpot;

/**
 * 奖池Service接口
 * 
 * @author cjf
 * @date 2023-09-24
 */
public interface IJackpotService 
{
    /**
     * 查询奖池
     * 
     * @param id 奖池主键
     * @return 奖池
     */
    public Jackpot selectJackpotById(Long id);

    /**
     * 查询奖池列表
     * 
     * @param jackpot 奖池
     * @return 奖池集合
     */
    public List<Jackpot> selectJackpotList(Jackpot jackpot);

    /**
     * 新增奖池
     * 
     * @param jackpot 奖池
     * @return 结果
     */
    public int insertJackpot(Jackpot jackpot);

    /**
     * 修改奖池
     * 
     * @param jackpot 奖池
     * @return 结果
     */
    public int updateJackpot(Jackpot jackpot);

    /**
     * 批量删除奖池
     * 
     * @param ids 需要删除的奖池主键集合
     * @return 结果
     */
    public int deleteJackpotByIds(Long[] ids);

    /**
     * 删除奖池信息
     * 
     * @param id 奖池主键
     * @return 结果
     */
    public int deleteJackpotById(Long id);
}
