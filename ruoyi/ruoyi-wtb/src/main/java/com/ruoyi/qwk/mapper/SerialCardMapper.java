package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.SerialCard;
import org.apache.ibatis.annotations.Param;

/**
 * 生肖序列卡 Mapper接口
 *
 * @author cjf
 * @date 2023-12-01
 */
@DataSource(value = DataSourceType.SLAVE)
public interface SerialCardMapper
{
    /**
     * 查询生肖序列卡
     *
     * @param id 生肖序列卡 主键
     * @return 生肖序列卡
     */
    public SerialCard selectSerialCardById(Long id);

    /**
     * 查询生肖序列卡 列表
     *
     * @param serialCard 生肖序列卡
     * @return 生肖序列卡 集合
     */
    public List<SerialCard> selectSerialCardList(SerialCard serialCard);

    /**
     * 新增生肖序列卡
     *
     * @param serialCard 生肖序列卡
     * @return 结果
     */
    public int insertSerialCard(SerialCard serialCard);

    /**
     * 修改生肖序列卡
     *
     * @param serialCard 生肖序列卡
     * @return 结果
     */
    public int updateSerialCard(SerialCard serialCard);

    /**
     * 删除生肖序列卡
     *
     * @param id 生肖序列卡 主键
     * @return 结果
     */
    public int deleteSerialCardById(Long id);

    /**
     * 批量删除生肖序列卡
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSerialCardByIds(Long[] ids);

    /**
     *根据卡号获取卡信息
     * @param cardCode
     * @return
     */
    public SerialCard selectSerialCardByCardCode(@Param("cardCode") String cardCode);

    /**
     * 获取所有卡号
     * @return
     */
    public List<String> selectCardCodeList();
}
