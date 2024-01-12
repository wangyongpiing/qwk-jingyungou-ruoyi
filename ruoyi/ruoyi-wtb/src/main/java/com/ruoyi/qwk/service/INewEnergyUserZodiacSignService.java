package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.NewEnergyUserZodiacSign;

/**
 * 用户生肖资产Service接口
 * 
 * @author cjf
 * @date 2023-12-08
 */
public interface INewEnergyUserZodiacSignService 
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
     * 批量删除用户生肖资产
     * 
     * @param ids 需要删除的用户生肖资产主键集合
     * @return 结果
     */
    public int deleteNewEnergyUserZodiacSignByIds(String[] ids);

    /**
     * 删除用户生肖资产信息
     * 
     * @param id 用户生肖资产主键
     * @return 结果
     */
    public int deleteNewEnergyUserZodiacSignById(String id);
}
