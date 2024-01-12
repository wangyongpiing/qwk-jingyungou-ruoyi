package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.TurntableRedEnvelope;

/**
 * 转盘领取红包Service接口
 * 
 * @author cjf
 * @date 2023-12-20
 */
public interface ITurntableRedEnvelopeService 
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
     * 批量删除转盘领取红包
     * 
     * @param ids 需要删除的转盘领取红包主键集合
     * @return 结果
     */
    public int deleteTurntableRedEnvelopeByIds(Long[] ids);

    /**
     * 删除转盘领取红包信息
     * 
     * @param id 转盘领取红包主键
     * @return 结果
     */
    public int deleteTurntableRedEnvelopeById(Long id);
}
