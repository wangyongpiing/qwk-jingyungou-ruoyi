package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.SerialCardTypeMapper;
import com.ruoyi.qwk.domain.SerialCardType;
import com.ruoyi.qwk.service.ISerialCardTypeService;

/**
 * 序列卡类型 Service业务层处理
 * 
 * @author cjf
 * @date 2023-12-01
 */
@Service
public class SerialCardTypeServiceImpl implements ISerialCardTypeService 
{
    @Autowired
    private SerialCardTypeMapper serialCardTypeMapper;

    /**
     * 查询序列卡类型 
     * 
     * @param id 序列卡类型 主键
     * @return 序列卡类型 
     */
    @Override
    public SerialCardType selectSerialCardTypeById(Long id)
    {
        return serialCardTypeMapper.selectSerialCardTypeById(id);
    }

    /**
     * 查询序列卡类型 列表
     * 
     * @param serialCardType 序列卡类型 
     * @return 序列卡类型 
     */
    @Override
    public List<SerialCardType> selectSerialCardTypeList(SerialCardType serialCardType)
    {
        return serialCardTypeMapper.selectSerialCardTypeList(serialCardType);
    }

    /**
     * 新增序列卡类型 
     * 
     * @param serialCardType 序列卡类型 
     * @return 结果
     */
    @Override
    public int insertSerialCardType(SerialCardType serialCardType)
    {
        serialCardType.setCreateTime(DateUtils.getNowDate());
        return serialCardTypeMapper.insertSerialCardType(serialCardType);
    }

    /**
     * 修改序列卡类型 
     * 
     * @param serialCardType 序列卡类型 
     * @return 结果
     */
    @Override
    public int updateSerialCardType(SerialCardType serialCardType)
    {
        serialCardType.setUpdateTime(DateUtils.getNowDate());
        return serialCardTypeMapper.updateSerialCardType(serialCardType);
    }

    /**
     * 批量删除序列卡类型 
     * 
     * @param ids 需要删除的序列卡类型 主键
     * @return 结果
     */
    @Override
    public int deleteSerialCardTypeByIds(Long[] ids)
    {
        return serialCardTypeMapper.deleteSerialCardTypeByIds(ids);
    }

    /**
     * 删除序列卡类型 信息
     * 
     * @param id 序列卡类型 主键
     * @return 结果
     */
    @Override
    public int deleteSerialCardTypeById(Long id)
    {
        return serialCardTypeMapper.deleteSerialCardTypeById(id);
    }
}
