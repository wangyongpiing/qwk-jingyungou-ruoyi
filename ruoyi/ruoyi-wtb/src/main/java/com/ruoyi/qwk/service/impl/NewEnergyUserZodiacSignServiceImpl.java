package com.ruoyi.qwk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.NewEnergyUserZodiacSignMapper;
import com.ruoyi.qwk.domain.NewEnergyUserZodiacSign;
import com.ruoyi.qwk.service.INewEnergyUserZodiacSignService;

/**
 * 用户生肖资产Service业务层处理
 * 
 * @author cjf
 * @date 2023-12-08
 */
@Service
public class NewEnergyUserZodiacSignServiceImpl implements INewEnergyUserZodiacSignService 
{
    @Autowired
    private NewEnergyUserZodiacSignMapper newEnergyUserZodiacSignMapper;

    /**
     * 查询用户生肖资产
     * 
     * @param id 用户生肖资产主键
     * @return 用户生肖资产
     */
    @Override
    public NewEnergyUserZodiacSign selectNewEnergyUserZodiacSignById(String id)
    {
        return newEnergyUserZodiacSignMapper.selectNewEnergyUserZodiacSignById(id);
    }

    /**
     * 查询用户生肖资产列表
     * 
     * @param newEnergyUserZodiacSign 用户生肖资产
     * @return 用户生肖资产
     */
    @Override
    public List<NewEnergyUserZodiacSign> selectNewEnergyUserZodiacSignList(NewEnergyUserZodiacSign newEnergyUserZodiacSign)
    {
        return newEnergyUserZodiacSignMapper.selectNewEnergyUserZodiacSignList(newEnergyUserZodiacSign);
    }

    /**
     * 新增用户生肖资产
     * 
     * @param newEnergyUserZodiacSign 用户生肖资产
     * @return 结果
     */
    @Override
    public int insertNewEnergyUserZodiacSign(NewEnergyUserZodiacSign newEnergyUserZodiacSign)
    {
        return newEnergyUserZodiacSignMapper.insertNewEnergyUserZodiacSign(newEnergyUserZodiacSign);
    }

    /**
     * 修改用户生肖资产
     * 
     * @param newEnergyUserZodiacSign 用户生肖资产
     * @return 结果
     */
    @Override
    public int updateNewEnergyUserZodiacSign(NewEnergyUserZodiacSign newEnergyUserZodiacSign)
    {
        return newEnergyUserZodiacSignMapper.updateNewEnergyUserZodiacSign(newEnergyUserZodiacSign);
    }

    /**
     * 批量删除用户生肖资产
     * 
     * @param ids 需要删除的用户生肖资产主键
     * @return 结果
     */
    @Override
    public int deleteNewEnergyUserZodiacSignByIds(String[] ids)
    {
        return newEnergyUserZodiacSignMapper.deleteNewEnergyUserZodiacSignByIds(ids);
    }

    /**
     * 删除用户生肖资产信息
     * 
     * @param id 用户生肖资产主键
     * @return 结果
     */
    @Override
    public int deleteNewEnergyUserZodiacSignById(String id)
    {
        return newEnergyUserZodiacSignMapper.deleteNewEnergyUserZodiacSignById(id);
    }
}
