package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.AssetDetails;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 资产详情 Service接口
 *
 * @author cjf
 * @date 2023-06-30
 */
public interface IAssetDetailsService
{
    /**
     * 查询资产详情
     *
     * @param id 资产详情 主键
     * @return 资产详情
     */
    public AssetDetails selectAssetDetailsById(Long id);

    /**
     * 查询资产详情 列表
     *
     * @param assetDetails 资产详情
     * @return 资产详情 集合
     */
    public List<AssetDetails> selectAssetDetailsList(AssetDetails assetDetails);

    /**
     * 新增资产详情
     *
     * @param assetDetails 资产详情
     * @return 结果
     */
    public int insertAssetDetails(AssetDetails assetDetails);

    /**
     * 修改资产详情
     *
     * @param assetDetails 资产详情
     * @return 结果
     */
    public int updateAssetDetails(AssetDetails assetDetails);

    /**
     * 批量删除资产详情
     *
     * @param ids 需要删除的资产详情 主键集合
     * @return 结果
     */
    public int deleteAssetDetailsByIds(Long[] ids);

    /**
     * 删除资产详情 信息
     *
     * @param id 资产详情 主键
     * @return 结果
     */
    public int deleteAssetDetailsById(Long id);

    /**
     * App兑换资产（电池兑换糖果，糖果兑换电池）
     * @param type 1-电池兑换糖果 2-糖果兑换电池
     * @param number 兑换数量
     * @return
     */
    public AjaxResult exchangeProperty(String type,Long number);

    /**
     * App赠送电池
     * @param phone
     * @param number
     * @return
     */
    public AjaxResult freeBatteries(String phone,Long number);

    /**
     * 获取钱包余额及明细记录
     * @return
     */
    public AjaxResult walletBalance();

    /**
     * 立即提现
     * @return
     */
    public AjaxResult immediateWithdrawal(String totalAmount);
}
