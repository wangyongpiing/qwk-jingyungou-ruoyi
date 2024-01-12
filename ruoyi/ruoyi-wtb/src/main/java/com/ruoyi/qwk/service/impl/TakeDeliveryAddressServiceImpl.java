package com.ruoyi.qwk.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.TakeDeliveryAddressMapper;
import com.ruoyi.qwk.domain.TakeDeliveryAddress;
import com.ruoyi.qwk.service.ITakeDeliveryAddressService;

/**
 * takeDeliveryAddressService业务层处理
 * 
 * @author cjf
 * @date 2023-07-03
 */
@Service
public class TakeDeliveryAddressServiceImpl implements ITakeDeliveryAddressService 
{
    @Autowired
    private TakeDeliveryAddressMapper takeDeliveryAddressMapper;

    /**
     * 查询takeDeliveryAddress
     * 
     * @param id takeDeliveryAddress主键
     * @return takeDeliveryAddress
     */
    @Override
    public TakeDeliveryAddress selectTakeDeliveryAddressById(Long id)
    {
        return takeDeliveryAddressMapper.selectTakeDeliveryAddressById(id);
    }

    /**
     * 查询takeDeliveryAddress列表
     * 
     * @param takeDeliveryAddress takeDeliveryAddress
     * @return takeDeliveryAddress
     */
    @Override
    public List<TakeDeliveryAddress> selectTakeDeliveryAddressList(TakeDeliveryAddress takeDeliveryAddress)
    {
        return takeDeliveryAddressMapper.selectTakeDeliveryAddressList(takeDeliveryAddress);
    }

    /**
     * 新增takeDeliveryAddress
     * 
     * @param takeDeliveryAddress takeDeliveryAddress
     * @return 结果
     */
    @Override
    public int insertTakeDeliveryAddress(TakeDeliveryAddress takeDeliveryAddress)
    {
        takeDeliveryAddress.setCreateBy(SecurityUtils.getUsername());
        takeDeliveryAddress.setCreateTime(new Date());
        int i = 0;
        if ("1".equals(takeDeliveryAddress.getIsDefault().toString())){
             i += takeDeliveryAddressMapper.updateByNotDefault(SecurityUtils.getUsername(), DateUtils.getTime(), takeDeliveryAddress.getUserId());
        }
        i += takeDeliveryAddressMapper.insertTakeDeliveryAddress(takeDeliveryAddress);
        return i;
    }

    /**
     * 修改takeDeliveryAddress
     * 
     * @param takeDeliveryAddress takeDeliveryAddress
     * @return 结果
     */
    @Override
    public int updateTakeDeliveryAddress(TakeDeliveryAddress takeDeliveryAddress)
    {
        takeDeliveryAddress.setUpdateBy(SecurityUtils.getUsername());
        takeDeliveryAddress.setUpdateTime(new Date());
        return takeDeliveryAddressMapper.updateTakeDeliveryAddress(takeDeliveryAddress);
    }

    /**
     * 批量删除takeDeliveryAddress
     * 
     * @param ids 需要删除的takeDeliveryAddress主键
     * @return 结果
     */
    @Override
    public int deleteTakeDeliveryAddressByIds(Long[] ids)
    {
        return takeDeliveryAddressMapper.deleteTakeDeliveryAddressByIds(ids);
    }

    /**
     * 删除takeDeliveryAddress信息
     * 
     * @param id takeDeliveryAddress主键
     * @return 结果
     */
    @Override
    public int deleteTakeDeliveryAddressById(Long id)
    {
        return takeDeliveryAddressMapper.deleteTakeDeliveryAddressById(id);
    }

    /**
     * 批量修改收货地址为非默认
     *
     * @param updateBy 修改者
     * @param updateTime 修改时间
     * @param userId 用户id
     * @return 结果
     */
    @Override
    public int updateByNotDefault(String updateBy,String updateTime,Long userId){
        return takeDeliveryAddressMapper.updateByNotDefault(updateBy,updateTime,userId);
    }

    /**
     * 修改收货地址为默认
     *
     * @param updateBy 修改者
     * @param updateTime 修改时间
     * @param id 主键
     * @param userId 用户id
     * @return 结果
     */
    @Override
    public int updateByDefault(String updateBy,String updateTime,Long id,Long userId){
        return takeDeliveryAddressMapper.updateByDefault(updateBy,updateTime,id,userId);
    }


}
