package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.MoneyRatio;

/**
 * 充值档位及比例 Mapper接口
 * 
 * @author cjf
 * @date 2023-06-30
 */
@DataSource(value = DataSourceType.SLAVE)
public interface MoneyRatioMapper 
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
     * 删除充值档位及比例 
     * 
     * @param id 充值档位及比例 主键
     * @return 结果
     */
    public int deleteMoneyRatioById(Long id);

    /**
     * 批量删除充值档位及比例 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMoneyRatioByIds(Long[] ids);

    /**
     * 根据类型查询充值档位及比例
     *
     * @param id 类型id
     * @return 结果
     */
    public MoneyRatio selectMoneyRatioByType(Long id);

}
