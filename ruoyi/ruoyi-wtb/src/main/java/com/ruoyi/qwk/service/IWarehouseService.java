package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.qwk.domain.Warehouse;

/**
 * 仓库 Service接口
 * 
 * @author cjf
 * @date 2023-06-29
 */
public interface IWarehouseService 
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
     * 批量删除仓库 
     * 
     * @param ids 需要删除的仓库 主键集合
     * @return 结果
     */
    public int deleteWarehouseByIds(Long[] ids);

    /**
     * 删除仓库 信息
     * 
     * @param id 仓库 主键
     * @return 结果
     */
    public int deleteWarehouseById(Long id);

    /**
     * 初始化用户仓库信息
     * */
    public int addWarehouse(SysUser user);

}
