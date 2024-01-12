package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.MoneyRatio;

/**
 * 充值档位及比例 Service接口
 * 
 * @author cjf
 * @date 2023-06-30
 */
public interface IMoneyRatioService 
{
    /**
     * 查询充值档位及比例 
     * 
     * @param id 充值档位及比例 主键
     * @return 充值档位及比例 
     */
    public MoneyRatio selectMoneyRatioById(Long id);

    /**
     * 查询充值档位及比例 列表
     * 
     * @param moneyRatio 充值档位及比例 
     * @return 充值档位及比例 集合
     */
    public List<MoneyRatio> selectMoneyRatioList(MoneyRatio moneyRatio);

    /**
     * 新增充值档位及比例 
     * 
     * @param moneyRatio 充值档位及比例 
     * @return 结果
     */
    public int insertMoneyRatio(MoneyRatio moneyRatio);

    /**
     * 修改充值档位及比例 
     * 
     * @param moneyRatio 充值档位及比例 
     * @return 结果
     */
    public int updateMoneyRatio(MoneyRatio moneyRatio);

    /**
     * 批量删除充值档位及比例 
     * 
     * @param ids 需要删除的充值档位及比例 主键集合
     * @return 结果
     */
    public int deleteMoneyRatioByIds(Long[] ids);

    /**
     * 删除充值档位及比例 信息
     * 
     * @param id 充值档位及比例 主键
     * @return 结果
     */
    public int deleteMoneyRatioById(Long id);

    public MoneyRatio selectMoneyRatioByType(Long id);
}
