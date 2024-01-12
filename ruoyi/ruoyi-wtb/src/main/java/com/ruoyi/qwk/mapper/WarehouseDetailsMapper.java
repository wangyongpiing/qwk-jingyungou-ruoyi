package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.WarehouseDetails;
import org.apache.ibatis.annotations.Param;

/**
 * 仓库详情 Mapper接口
 * 
 * @author cjf
 * @date 2023-06-29
 */
@DataSource(value = DataSourceType.SLAVE)
public interface WarehouseDetailsMapper 
{
    /**
     * 查询仓库详情 
     * 
     * @param id 仓库详情 主键
     * @return 仓库详情 
     */
    public WarehouseDetails selectWarehouseDetailsById(Long id);

    /**
     * 查询仓库详情 列表
     * 
     * @param warehouseDetails 仓库详情 
     * @return 仓库详情 集合
     */
    public List<WarehouseDetails> selectWarehouseDetailsList(WarehouseDetails warehouseDetails);

    /**
     * 新增仓库详情 
     * 
     * @param warehouseDetails 仓库详情 
     * @return 结果
     */
    public int insertWarehouseDetails(WarehouseDetails warehouseDetails);

    /**
     * 修改仓库详情 
     * 
     * @param warehouseDetails 仓库详情 
     * @return 结果
     */
    public int updateWarehouseDetails(WarehouseDetails warehouseDetails);

    /**
     * 删除仓库详情 
     * 
     * @param id 仓库详情 主键
     * @return 结果
     */
    public int deleteWarehouseDetailsById(Long id);

    /**
     * 批量删除仓库详情 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWarehouseDetailsByIds(Long[] ids);

    /**
     * 根据用户id商品id查询仓库详情
     *
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 结果
     */
    public WarehouseDetails getByGoodsId(@Param("userId") Long userId,@Param("goodsId") Long goodsId);

    /**
     * 批量删除用户仓库详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWarehouseDetailsByUserId(Long[] ids);


    public WarehouseDetails getByVPType(WarehouseDetails warehouseDetails);

}
