package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.VipLevel;

/**
 * vip等级Service接口
 * 
 * @author cjf
 * @date 2023-09-13
 */
public interface IVipLevelService 
{
    /**
     * 查询vip等级
     * 
     * @param id vip等级主键
     * @return vip等级
     */
    public VipLevel selectVipLevelById(Long id);

    /**
     * 查询vip等级列表
     * 
     * @param vipLevel vip等级
     * @return vip等级集合
     */
    public List<VipLevel> selectVipLevelList(VipLevel vipLevel);

    /**
     * 新增vip等级
     * 
     * @param vipLevel vip等级
     * @return 结果
     */
    public int insertVipLevel(VipLevel vipLevel);

    /**
     * 修改vip等级
     * 
     * @param vipLevel vip等级
     * @return 结果
     */
    public int updateVipLevel(VipLevel vipLevel);

    /**
     * 批量删除vip等级
     * 
     * @param ids 需要删除的vip等级主键集合
     * @return 结果
     */
    public int deleteVipLevelByIds(Long[] ids);

    /**
     * 删除vip等级信息
     * 
     * @param id vip等级主键
     * @return 结果
     */
    public int deleteVipLevelById(Long id);
}
