package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.Warehouse;

/**
 * 仓库 Mapper接口
 * 
 * @author cjf
 * @date 2023-06-29
 */
@DataSource(value = DataSourceType.SLAVE)
public interface WarehouseMapper 
{
    /**
     * 查询仓库 
     * 
     * @param id 仓库 主键
     * @return 仓库 
     */
    public Warehouse selectWarehouseById(Long id);

    /**
     * 查询仓库 列表
     * 
     * @param warehouse 仓库 
     * @return 仓库 集合
     */
    public List<Warehouse> selectWarehouseList(Warehouse warehouse);

    /**
     * 新增仓库 
     * 
     * @param warehouse 仓库 
     * @return 结果
     */
    public int insertWarehouse(Warehouse warehouse);

    /**
     * 修改仓库 
     * 
     * @param warehouse 仓库 
     * @return 结果
     */
    public int updateWarehouse(Warehouse warehouse);

    /**
     * 删除仓库 
     * 
     * @param id 仓库 主键
     * @return 结果
     */
    public int deleteWarehouseById(Long id);

    /**
     * 批量删除仓库 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWarehouseByIds(Long[] ids);

    /**
     * 查询仓库
     *
     * @param id 用户id
     * @return 仓库
     */
    public Warehouse selectWarehouseByUserId(Long id);

    /**
     * 批量删除用户仓库
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWarehouseByUserId(Long[] ids);

}
