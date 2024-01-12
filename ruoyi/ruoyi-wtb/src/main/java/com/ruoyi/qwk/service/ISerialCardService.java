package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.BatchInsertSerialCard;
import com.ruoyi.qwk.domain.BatchInsertSerialCardType;
import com.ruoyi.qwk.domain.SerialCard;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 生肖序列卡 Service接口
 *
 * @author cjf
 * @date 2023-12-01
 */
public interface ISerialCardService
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
     * 批量删除生肖序列卡
     *
     * @param ids 需要删除的生肖序列卡 主键集合
     * @return 结果
     */
    public int deleteSerialCardByIds(Long[] ids);

    /**
     * 删除生肖序列卡 信息
     *
     * @param id 生肖序列卡 主键
     * @return 结果
     */
    public int deleteSerialCardById(Long id);

    /**
     * 批量生成序列卡
     * @param card
     * @return
     */
    public AjaxResult batchInsertSerialCard(BatchInsertSerialCard card);

    /**
     * 微信公众号端-批量生成序列卡
     * @param cardList
     * @return
     */
    public AjaxResult batchInsertSerialCardWx(List<BatchInsertSerialCardType> cardList);

    /**
     * App兑换生肖序列卡
     * @param type
     * @return
     */
    public AjaxResult serialCardExchange(String type);

    /**
     * App查询生肖序列卡 列表
     * @return
     */
    public AjaxResult selectSerialCardList();
}
