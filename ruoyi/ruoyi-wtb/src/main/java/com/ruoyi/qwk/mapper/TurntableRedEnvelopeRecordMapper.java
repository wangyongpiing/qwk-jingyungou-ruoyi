package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.TurntableRedEnvelopeRecord;

/**
 * 转盘领取红包记录Mapper接口
 *
 * @author cjf
 * @date 2023-12-20
 */
@DataSource(value = DataSourceType.SLAVE)
public interface TurntableRedEnvelopeRecordMapper
{
    /**
     * 查询转盘领取红包记录
     *
     * @param id 转盘领取红包记录主键
     * @return 转盘领取红包记录
     */
    public TurntableRedEnvelopeRecord selectTurntableRedEnvelopeRecordById(Long id);

    /**
     * 查询转盘领取红包记录列表
     *
     * @param turntableRedEnvelopeRecord 转盘领取红包记录
     * @return 转盘领取红包记录集合
     */
    public List<TurntableRedEnvelopeRecord> selectTurntableRedEnvelopeRecordList(TurntableRedEnvelopeRecord turntableRedEnvelopeRecord);

    /**
     * 新增转盘领取红包记录
     *
     * @param turntableRedEnvelopeRecord 转盘领取红包记录
     * @return 结果
     */
    public int insertTurntableRedEnvelopeRecord(TurntableRedEnvelopeRecord turntableRedEnvelopeRecord);

    /**
     * 修改转盘领取红包记录
     *
     * @param turntableRedEnvelopeRecord 转盘领取红包记录
     * @return 结果
     */
    public int updateTurntableRedEnvelopeRecord(TurntableRedEnvelopeRecord turntableRedEnvelopeRecord);

    /**
     * 删除转盘领取红包记录
     *
     * @param id 转盘领取红包记录主键
     * @return 结果
     */
    public int deleteTurntableRedEnvelopeRecordById(Long id);

    /**
     * 批量删除转盘领取红包记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTurntableRedEnvelopeRecordByIds(Long[] ids);
}
