package com.ruoyi.qwk.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.RechargeBill;

/**
 * 充值流水 Service接口
 * 
 * @author cjf
 * @date 2023-06-30
 */
public interface IRechargeBillService 
{
    /**
     * 查询充值流水 
     * 
     * @param id 充值流水 主键
     * @return 充值流水 
     */
    public RechargeBill selectRechargeBillById(Long id);

    /**
     * 查询充值流水 列表
     * 
     * @param rechargeBill 充值流水 
     * @return 充值流水 集合
     */
    public List<RechargeBill> selectRechargeBillList(RechargeBill rechargeBill);

    /**
     * 新增充值流水 
     * 
     * @param rechargeBill 充值流水 
     * @return 结果
     */
    public int insertRechargeBill(RechargeBill rechargeBill);

    /**
     * 修改充值流水 
     * 
     * @param rechargeBill 充值流水 
     * @return 结果
     */
    public int updateRechargeBill(RechargeBill rechargeBill);

    /**
     * 批量删除充值流水 
     * 
     * @param ids 需要删除的充值流水 主键集合
     * @return 结果
     */
    public int deleteRechargeBillByIds(Long[] ids);

    /**
     * 删除充值流水 信息
     * 
     * @param id 充值流水 主键
     * @return 结果
     */
    public int deleteRechargeBillById(Long id);

    /**
     * 查询统计数据
     *
     * @return 结果
     */
    public Map<String,String> selectByStatistics();

    /**
     * 积分充值
     * @param rechargeBill 充值流水对象
     * @return 结果
     * */
    public AjaxResult pointRecharge(RechargeBill rechargeBill);
}
