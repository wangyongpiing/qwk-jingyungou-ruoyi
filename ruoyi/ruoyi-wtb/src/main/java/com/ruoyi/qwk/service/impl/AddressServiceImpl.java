package com.ruoyi.qwk.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.AddressMapper;
import com.ruoyi.qwk.domain.Address;
import com.ruoyi.qwk.service.IAddressService;

/**
 * 地址管理 Service业务层处理
 * 
 * @author cjf
 * @date 2023-06-26
 */
@Service
public class AddressServiceImpl implements IAddressService 
{
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询地址管理 
     * 
     * @param id 地址管理 主键
     * @return 地址管理 
     */
    @Override
    public Address selectAddressById(Long id)
    {
        return addressMapper.selectAddressById(id);
    }

    /**
     * 查询地址管理 列表
     * 
     * @param address 地址管理 
     * @return 地址管理 
     */
    @Override
    public List<Address> selectAddressList(Address address)
    {
        return addressMapper.selectAddressList(address);
    }

    /**
     * 新增地址管理 
     * 
     * @param address 地址管理 
     * @return 结果
     */
    @Override
    public int insertAddress(Address address)
    {
        address.setCreatedBy(SecurityUtils.getUsername());
        address.setCreatedTime(new Date());
        return addressMapper.insertAddress(address);
    }

    /**
     * 修改地址管理 
     * 
     * @param address 地址管理 
     * @return 结果
     */
    @Override
    public int updateAddress(Address address)
    {
        address.setUpdatedBy(SecurityUtils.getUsername());
        address.setUpdatedTime(new Date());
        return addressMapper.updateAddress(address);
    }

    /**
     * 批量删除地址管理 
     * 
     * @param ids 需要删除的地址管理 主键
     * @return 结果
     */
    @Override
    public int deleteAddressByIds(Long[] ids)
    {
        return addressMapper.deleteAddressByIds(ids);
    }

    /**
     * 删除地址管理 信息
     * 
     * @param id 地址管理 主键
     * @return 结果
     */
    @Override
    public int deleteAddressById(Long id)
    {
        return addressMapper.deleteAddressById(id);
    }
}
