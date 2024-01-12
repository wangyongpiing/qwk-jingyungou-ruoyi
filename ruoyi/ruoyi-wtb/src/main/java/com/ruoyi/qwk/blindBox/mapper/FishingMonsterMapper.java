package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.FishingMonster;
import org.apache.ibatis.annotations.Param;

/**
 * 捕鱼怪物Mapper接口
 * 
 * @author cjf
 * @date 2023-09-14
 */
@DataSource(value = DataSourceType.SLAVE)
public interface FishingMonsterMapper 
{
    /**
     * 查询捕鱼怪物
     * 
     * @param id 捕鱼怪物主键
     * @return 捕鱼怪物
     */
    public FishingMonster selectFishingMonsterById(Long id);

    /**
     * 查询捕鱼怪物列表
     * 
     * @param fishingMonster 捕鱼怪物
     * @return 捕鱼怪物集合
     */
    public List<FishingMonster> selectFishingMonsterList(FishingMonster fishingMonster);

    /**
     * 新增捕鱼怪物
     * 
     * @param fishingMonster 捕鱼怪物
     * @return 结果
     */
    public int insertFishingMonster(FishingMonster fishingMonster);

    /**
     * 修改捕鱼怪物
     * 
     * @param fishingMonster 捕鱼怪物
     * @return 结果
     */
    public int updateFishingMonster(FishingMonster fishingMonster);

    /**
     * 删除捕鱼怪物
     * 
     * @param id 捕鱼怪物主键
     * @return 结果
     */
    public int deleteFishingMonsterById(Long id);

    /**
     * 批量删除捕鱼怪物
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFishingMonsterByIds(Long[] ids);

    /**
     * 查询所有盲盒怪物
     * */
    public List<FishingMonster> getAllFishingMonster();

    /**
     * 根据id集合查询盲盒信息
     * */
    public List<FishingMonster> getByIds(@Param("ids") List<Long> ids);

    /**
     * 估计价值区间查询
     * */
    public List<FishingMonster> getByTall(@Param("low")Integer low,@Param("tall")Integer tall);

}
