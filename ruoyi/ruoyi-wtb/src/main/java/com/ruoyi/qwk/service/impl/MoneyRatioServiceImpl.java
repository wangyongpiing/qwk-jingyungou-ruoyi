package com.ruoyi.qwk.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.MoneyRatioMapper;
import com.ruoyi.qwk.domain.MoneyRatio;
import com.ruoyi.qwk.service.IMoneyRatioService;

/**
 * 充值档位及比例 Service业务层处理
 *
 * @author cjf
 * @date 2023-06-30
 */
@Service
public class MoneyRatioServiceImpl implements IMoneyRatioService
{
    @Autowired
    private MoneyRatioMapper moneyRatioMapper;

    /**
     * 查询充值档位及比例
     *
     * @param id 充值档位及比例 主键
     * @return 充值档位及比例
     */
    @Override
    public MoneyRatio selectMoneyRatioById(Long id)
    {
        return moneyRatioMapper.selectMoneyRatioById(id);
    }

    /**
     * 查询充值档位及比例 列表
     *
     * @param moneyRatio 充值档位及比例
     * @return 充值档位及比例
     */
    @Override
    public List<MoneyRatio> selectMoneyRatioList(MoneyRatio moneyRatio)
    {
        return moneyRatioMapper.selectMoneyRatioList(moneyRatio);
    }

    /**
     * 新增充值档位及比例
     *
     * @param moneyRatio 充值档位及比例
     * @return 结果
     */
    @Override
    public int insertMoneyRatio(MoneyRatio moneyRatio)
    {
        moneyRatio.setCreatedBy(SecurityUtils.getUsername());
        moneyRatio.setCreatedTime(new Date());
        return moneyRatioMapper.insertMoneyRatio(moneyRatio);
    }

    /**
     * 修改充值档位及比例
     *
     * @param moneyRatio 充值档位及比例
     * @return 结果
     */
    @Override
    public int updateMoneyRatio(MoneyRatio moneyRatio){
        moneyRatio.setUpdatedBy(SecurityUtils.getUsername());
        moneyRatio.setUpdatedTime(new Date());
        return moneyRatioMapper.updateMoneyRatio(moneyRatio);
    }

    /**
     * 批量删除充值档位及比例
     *
     * @param ids 需要删除的充值档位及比例 主键
     * @return 结果
     */
    @Override
    public int deleteMoneyRatioByIds(Long[] ids)
    {
        return moneyRatioMapper.deleteMoneyRatioByIds(ids);
    }

    /**
     * 删除充值档位及比例 信息
     *
     * @param id 充值档位及比例 主键
     * @return 结果
     */
    @Override
    public int deleteMoneyRatioById(Long id)
    {
        return moneyRatioMapper.deleteMoneyRatioById(id);
    }

    @Override
    public MoneyRatio selectMoneyRatioByType(Long id){
        return moneyRatioMapper.selectMoneyRatioByType(id);
    }

}
