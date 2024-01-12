package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.Jackpot;

/**
 * 奖池Mapper接口
 * 
 * @author cjf
 * @date 2023-09-24
 */
@DataSource(value = DataSourceType.SLAVE)
public interface JackpotMapper 
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
     * 删除奖池
     * 
     * @param id 奖池主键
     * @return 结果
     */
    public int deleteJackpotById(Long id);

    /**
     * 批量删除奖池
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJackpotByIds(Long[] ids);
}
