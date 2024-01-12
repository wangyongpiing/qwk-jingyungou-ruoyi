package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.VipLevel;

/**
 * vip等级Mapper接口
 * 
 * @author cjf
 * @date 2023-09-13
 */
@DataSource(value = DataSourceType.SLAVE)
public interface VipLevelMapper 
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
     * 删除vip等级
     * 
     * @param id vip等级主键
     * @return 结果
     */
    public int deleteVipLevelById(Long id);

    /**
     * 批量删除vip等级
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVipLevelByIds(Long[] ids);

    /**
     * 查询所有未删除的vip级别
     * @return VipLevels对像集合
     * */
    public List<VipLevel> getAllByStatusVipLevels();
}
