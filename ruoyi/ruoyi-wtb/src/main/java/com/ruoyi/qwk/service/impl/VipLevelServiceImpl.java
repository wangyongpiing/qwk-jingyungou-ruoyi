package com.ruoyi.qwk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.VipLevelMapper;
import com.ruoyi.qwk.domain.VipLevel;
import com.ruoyi.qwk.service.IVipLevelService;

/**
 * vip等级Service业务层处理
 * 
 * @author cjf
 * @date 2023-09-13
 */
@Service
public class VipLevelServiceImpl implements IVipLevelService 
{
    @Autowired
    private VipLevelMapper vipLevelMapper;

    /**
     * 查询vip等级
     * 
     * @param id vip等级主键
     * @return vip等级
     */
    @Override
    public VipLevel selectVipLevelById(Long id)
    {
        return vipLevelMapper.selectVipLevelById(id);
    }

    /**
     * 查询vip等级列表
     * 
     * @param vipLevel vip等级
     * @return vip等级
     */
    @Override
    public List<VipLevel> selectVipLevelList(VipLevel vipLevel)
    {
        return vipLevelMapper.selectVipLevelList(vipLevel);
    }

    /**
     * 新增vip等级
     * 
     * @param vipLevel vip等级
     * @return 结果
     */
    @Override
    public int insertVipLevel(VipLevel vipLevel)
    {
        vipLevel.setCreateTime(DateUtils.getNowDate());
        return vipLevelMapper.insertVipLevel(vipLevel);
    }

    /**
     * 修改vip等级
     * 
     * @param vipLevel vip等级
     * @return 结果
     */
    @Override
    public int updateVipLevel(VipLevel vipLevel)
    {
        vipLevel.setUpdateTime(DateUtils.getNowDate());
        return vipLevelMapper.updateVipLevel(vipLevel);
    }

    /**
     * 批量删除vip等级
     * 
     * @param ids 需要删除的vip等级主键
     * @return 结果
     */
    @Override
    public int deleteVipLevelByIds(Long[] ids)
    {
        return vipLevelMapper.deleteVipLevelByIds(ids);
    }

    /**
     * 删除vip等级信息
     * 
     * @param id vip等级主键
     * @return 结果
     */
    @Override
    public int deleteVipLevelById(Long id)
    {
        return vipLevelMapper.deleteVipLevelById(id);
    }
}
