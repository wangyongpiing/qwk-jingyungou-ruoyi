package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.TurntableRedEnvelope;
import org.apache.ibatis.annotations.Param;

/**
 * 转盘领取红包Mapper接口
 *
 * @author cjf
 * @date 2023-12-20
 */
@DataSource(value = DataSourceType.SLAVE)
public interface TurntableRedEnvelopeMapper
{
    /**
     * 查询转盘领取红包
     *
     * @param id 转盘领取红包主键
     * @return 转盘领取红包
     */
    public TurntableRedEnvelope selectTurntableRedEnvelopeById(Long id);

    /**
     * 查询转盘领取红包列表
     *
     * @param turntableRedEnvelope 转盘领取红包
     * @return 转盘领取红包集合
     */
    public List<TurntableRedEnvelope> selectTurntableRedEnvelopeList(TurntableRedEnvelope turntableRedEnvelope);

    /**
     * 新增转盘领取红包
     *
     * @param turntableRedEnvelope 转盘领取红包
     * @return 结果
     */
    public int insertTurntableRedEnvelope(TurntableRedEnvelope turntableRedEnvelope);

    /**
     * 修改转盘领取红包
     *
     * @param turntableRedEnvelope 转盘领取红包
     * @return 结果
     */
    public int updateTurntableRedEnvelope(TurntableRedEnvelope turntableRedEnvelope);

    /**
     * 删除转盘领取红包
     *
     * @param id 转盘领取红包主键
     * @return 结果
     */
    public int deleteTurntableRedEnvelopeById(Long id);

    /**
     * 批量删除转盘领取红包
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTurntableRedEnvelopeByIds(Long[] ids);

}
