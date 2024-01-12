package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.TurntableRedEnvelopeMapper;
import com.ruoyi.qwk.domain.TurntableRedEnvelope;
import com.ruoyi.qwk.service.ITurntableRedEnvelopeService;

/**
 * 转盘领取红包Service业务层处理
 * 
 * @author cjf
 * @date 2023-12-20
 */
@Service
public class TurntableRedEnvelopeServiceImpl implements ITurntableRedEnvelopeService 
{
    @Autowired
    private TurntableRedEnvelopeMapper turntableRedEnvelopeMapper;

    /**
     * 查询转盘领取红包
     * 
     * @param id 转盘领取红包主键
     * @return 转盘领取红包
     */
    @Override
    public TurntableRedEnvelope selectTurntableRedEnvelopeById(Long id)
    {
        return turntableRedEnvelopeMapper.selectTurntableRedEnvelopeById(id);
    }

    /**
     * 查询转盘领取红包列表
     * 
     * @param turntableRedEnvelope 转盘领取红包
     * @return 转盘领取红包
     */
    @Override
    public List<TurntableRedEnvelope> selectTurntableRedEnvelopeList(TurntableRedEnvelope turntableRedEnvelope)
    {
        return turntableRedEnvelopeMapper.selectTurntableRedEnvelopeList(turntableRedEnvelope);
    }

    /**
     * 新增转盘领取红包
     * 
     * @param turntableRedEnvelope 转盘领取红包
     * @return 结果
     */
    @Override
    public int insertTurntableRedEnvelope(TurntableRedEnvelope turntableRedEnvelope)
    {
        turntableRedEnvelope.setCreateTime(DateUtils.getNowDate());
        return turntableRedEnvelopeMapper.insertTurntableRedEnvelope(turntableRedEnvelope);
    }

    /**
     * 修改转盘领取红包
     * 
     * @param turntableRedEnvelope 转盘领取红包
     * @return 结果
     */
    @Override
    public int updateTurntableRedEnvelope(TurntableRedEnvelope turntableRedEnvelope)
    {
        turntableRedEnvelope.setUpdateTime(DateUtils.getNowDate());
        return turntableRedEnvelopeMapper.updateTurntableRedEnvelope(turntableRedEnvelope);
    }

    /**
     * 批量删除转盘领取红包
     * 
     * @param ids 需要删除的转盘领取红包主键
     * @return 结果
     */
    @Override
    public int deleteTurntableRedEnvelopeByIds(Long[] ids)
    {
        return turntableRedEnvelopeMapper.deleteTurntableRedEnvelopeByIds(ids);
    }

    /**
     * 删除转盘领取红包信息
     * 
     * @param id 转盘领取红包主键
     * @return 结果
     */
    @Override
    public int deleteTurntableRedEnvelopeById(Long id)
    {
        return turntableRedEnvelopeMapper.deleteTurntableRedEnvelopeById(id);
    }
}
