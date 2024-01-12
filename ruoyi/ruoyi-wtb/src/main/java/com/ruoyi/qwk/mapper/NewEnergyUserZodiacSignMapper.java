package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.NewEnergyUserZodiacSign;

/**
 * 用户生肖资产Mapper接口
 *
 * @author cjf
 * @date 2023-12-08
 */
@DataSource(value = DataSourceType.SLAVE)
public interface NewEnergyUserZodiacSignMapper
{
    /**
     * 查询用户生肖资产
     *
     * @param id 用户生肖资产主键
     * @return 用户生肖资产
     */
    public NewEnergyUserZodiacSign selectNewEnergyUserZodiacSignById(String id);

    /**
     * 查询用户生肖资产列表
     *
     * @param newEnergyUserZodiacSign 用户生肖资产
     * @return 用户生肖资产集合
     */
    public List<NewEnergyUserZodiacSign> selectNewEnergyUserZodiacSignList(NewEnergyUserZodiacSign newEnergyUserZodiacSign);

    /**
     * 新增用户生肖资产
     *
     * @param newEnergyUserZodiacSign 用户生肖资产
     * @return 结果
     */
    public int insertNewEnergyUserZodiacSign(NewEnergyUserZodiacSign newEnergyUserZodiacSign);

    /**
     * 修改用户生肖资产
     *
     * @param newEnergyUserZodiacSign 用户生肖资产
     * @return 结果
     */
    public int updateNewEnergyUserZodiacSign(NewEnergyUserZodiacSign newEnergyUserZodiacSign);

    /**
     * 删除用户生肖资产
     *
     * @param id 用户生肖资产主键
     * @return 结果
     */
    public int deleteNewEnergyUserZodiacSignById(String id);

    /**
     * 批量删除用户生肖资产
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewEnergyUserZodiacSignByIds(String[] ids);
}
