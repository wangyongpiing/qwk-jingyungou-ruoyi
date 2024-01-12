package com.ruoyi.qwk.service;

import java.util.List;
import com.ruoyi.qwk.domain.TakeDeliveryAddress;
import org.apache.ibatis.annotations.Param;

/**
 * takeDeliveryAddressService接口
 * 
 * @author cjf
 * @date 2023-07-03
 */
public interface ITakeDeliveryAddressService 
{
    /**
     * 查询takeDeliveryAddress
     * 
     * @param id takeDeliveryAddress主键
     * @return takeDeliveryAddress
     */
    public TakeDeliveryAddress selectTakeDeliveryAddressById(Long id);

    /**
     * 查询takeDeliveryAddress列表
     * 
     * @param takeDeliveryAddress takeDeliveryAddress
     * @return takeDeliveryAddress集合
     */
    public List<TakeDeliveryAddress> selectTakeDeliveryAddressList(TakeDeliveryAddress takeDeliveryAddress);

    /**
     * 新增takeDeliveryAddress
     * 
     * @param takeDeliveryAddress takeDeliveryAddress
     * @return 结果
     */
    public int insertTakeDeliveryAddress(TakeDeliveryAddress takeDeliveryAddress);

    /**
     * 修改takeDeliveryAddress
     * 
     * @param takeDeliveryAddress takeDeliveryAddress
     * @return 结果
     */
    public int updateTakeDeliveryAddress(TakeDeliveryAddress takeDeliveryAddress);

    /**
     * 批量删除takeDeliveryAddress
     * 
     * @param ids 需要删除的takeDeliveryAddress主键集合
     * @return 结果
     */
    public int deleteTakeDeliveryAddressByIds(Long[] ids);

    /**
     * 删除takeDeliveryAddress信息
     * 
     * @param id takeDeliveryAddress主键
     * @return 结果
     */
    public int deleteTakeDeliveryAddressById(Long id);

    /**
     * 批量修改收货地址为非默认
     *
     * @param updateBy 修改者
     * @param updateTime 修改时间
     * @param userId 用户id
     * @return 结果
     */
    public int updateByNotDefault(String updateBy,String updateTime,Long userId);

    /**
     * 修改收货地址为默认
     *
     * @param updateBy 修改者
     * @param updateTime 修改时间
     * @param id 主键
     * @param userId 用户id
     * @return 结果
     */
    public int updateByDefault(String updateBy,String updateTime,Long id,Long userId);

}
