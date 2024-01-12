package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.TurntableRedEnvelopeRecord;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 转盘领取红包记录Service接口
 *
 * @author cjf
 * @date 2023-12-20
 */
public interface ITurntableRedEnvelopeRecordService
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
     * 批量删除转盘领取红包记录
     *
     * @param ids 需要删除的转盘领取红包记录主键集合
     * @return 结果
     */
    public int deleteTurntableRedEnvelopeRecordByIds(Long[] ids);

    /**
     * 删除转盘领取红包记录信息
     *
     * @param id 转盘领取红包记录主键
     * @return 结果
     */
    public int deleteTurntableRedEnvelopeRecordById(Long id);

    /**
     * 是否领取下载，注册红包
     * @return
     */
    public AjaxResult checkReceiveRedEnvelope();

    /**
     * 领取下载，注册红包
     * @return
     */
    public AjaxResult receiveRedEnvelope();
}
