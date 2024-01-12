package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.SerialCardType;

/**
 * 序列卡类型 Service接口
 * 
 * @author cjf
 * @date 2023-12-01
 */
public interface ISerialCardTypeService 
{
    /**
     * 查询序列卡类型 
     * 
     * @param id 序列卡类型 主键
     * @return 序列卡类型 
     */
    public SerialCardType selectSerialCardTypeById(Long id);

    /**
     * 查询序列卡类型 列表
     * 
     * @param serialCardType 序列卡类型 
     * @return 序列卡类型 集合
     */
    public List<SerialCardType> selectSerialCardTypeList(SerialCardType serialCardType);

    /**
     * 新增序列卡类型 
     * 
     * @param serialCardType 序列卡类型 
     * @return 结果
     */
    public int insertSerialCardType(SerialCardType serialCardType);

    /**
     * 修改序列卡类型 
     * 
     * @param serialCardType 序列卡类型 
     * @return 结果
     */
    public int updateSerialCardType(SerialCardType serialCardType);

    /**
     * 批量删除序列卡类型 
     * 
     * @param ids 需要删除的序列卡类型 主键集合
     * @return 结果
     */
    public int deleteSerialCardTypeByIds(Long[] ids);

    /**
     * 删除序列卡类型 信息
     * 
     * @param id 序列卡类型 主键
     * @return 结果
     */
    public int deleteSerialCardTypeById(Long id);
}
