package com.ruoyi.qwk.mapper;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.TakeDeliveryAddress;
import org.apache.ibatis.annotations.Param;

/**
 * takeDeliveryAddressMapper接口
 * 
 * @author cjf
 * @date 2023-07-03
 */
@DataSource(value = DataSourceType.SLAVE)
public interface TakeDeliveryAddressMapper 
{
    /**
     * 查询收货地址
     *
     * @param id 收货地址主键
     * @return 收货地址
     */
    public TakeDeliveryAddress selectTakeDeliveryAddressById(Long id);

    /**
     * 查询收货地址列表
     *
     * @param takeDeliveryAddress 收货地址
     * @return 收货地址集合
     */
    public List<TakeDeliveryAddress> selectTakeDeliveryAddressList(TakeDeliveryAddress takeDeliveryAddress);

    /**
     * 新增收货地址
     *
     * @param takeDeliveryAddress 收货地址
     * @return 结果
     */
    public int insertTakeDeliveryAddress(TakeDeliveryAddress takeDeliveryAddress);

    /**
     * 修改收货地址
     *
     * @param takeDeliveryAddress 收货地址
     * @return 结果
     */
    public int updateTakeDeliveryAddress(TakeDeliveryAddress takeDeliveryAddress);

    /**
     * 删除收货地址
     *
     * @param id 收货地址主键
     * @return 结果
     */
    public int deleteTakeDeliveryAddressById(Long id);

    /**
     * 批量删除收货地址
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTakeDeliveryAddressByIds(Long[] ids);

//    updateByNotDefault updateByDefault
    /**
     * 批量修改收货地址为非默认
     *
     * @param updateBy 修改者
     * @param updateTime 修改时间
     * @param userId 用户id
     * @return 结果
     */
    public int updateByNotDefault(@Param("updateBy") String updateBy,@Param("updateTime") String updateTime,@Param("userId") Long userId);

    /**
     * 修改收货地址为默认
     *
     * @param updateBy 修改者
     * @param updateTime 修改时间
     * @param id 主键
     * @param userId 用户id
     * @return 结果
     */
    public int updateByDefault(@Param("updateBy") String updateBy,@Param("updateTime") String updateTime,@Param("id") Long id,@Param("userId") Long userId);

}
