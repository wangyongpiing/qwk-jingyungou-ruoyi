package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.Address;

/**
 * 地址管理 Mapper接口
 * 
 * @author cjf
 * @date 2023-06-26
 */
@DataSource(value = DataSourceType.SLAVE)
public interface AddressMapper 
{
    /**
     * 查询地址管理 
     * 
     * @param id 地址管理 主键
     * @return 地址管理 
     */
    public Address selectAddressById(Long id);

    /**
     * 查询地址管理 列表
     * 
     * @param address 地址管理 
     * @return 地址管理 集合
     */
    public List<Address> selectAddressList(Address address);

    /**
     * 新增地址管理 
     * 
     * @param address 地址管理 
     * @return 结果
     */
    public int insertAddress(Address address);

    /**
     * 修改地址管理 
     * 
     * @param address 地址管理 
     * @return 结果
     */
    public int updateAddress(Address address);

    /**
     * 删除地址管理 
     * 
     * @param id 地址管理 主键
     * @return 结果
     */
    public int deleteAddressById(Long id);

    /**
     * 批量删除地址管理 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAddressByIds(Long[] ids);
}
