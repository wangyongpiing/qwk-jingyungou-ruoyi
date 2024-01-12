package com.ruoyi.qwk.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.WarehouseMapper;
import com.ruoyi.qwk.domain.Warehouse;
import com.ruoyi.qwk.service.IWarehouseService;

/**
 * 仓库 Service业务层处理
 * 
 * @author cjf
 * @date 2023-06-29
 */
@Service
public class WarehouseServiceImpl implements IWarehouseService 
{
    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * 查询仓库 
     * 
     * @param id 仓库 主键
     * @return 仓库 
     */
    @Override
    public Warehouse selectWarehouseById(Long id)
    {
        return warehouseMapper.selectWarehouseById(id);
    }

    /**
     * 查询仓库 列表
     * 
     * @param warehouse 仓库 
     * @return 仓库 
     */
    @Override
    public List<Warehouse> selectWarehouseList(Warehouse warehouse)
    {
        return warehouseMapper.selectWarehouseList(warehouse);
    }

    /**
     * 新增仓库 
     * 
     * @param warehouse 仓库 
     * @return 结果
     */
    @Override
    public int insertWarehouse(Warehouse warehouse)
    {
        return warehouseMapper.insertWarehouse(warehouse);
    }

    /**
     * 修改仓库 
     * 
     * @param warehouse 仓库 
     * @return 结果
     */
    @Override
    public int updateWarehouse(Warehouse warehouse)
    {
        return warehouseMapper.updateWarehouse(warehouse);
    }

    /**
     * 批量删除仓库 
     * 
     * @param ids 需要删除的仓库 主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseByIds(Long[] ids)
    {
        return warehouseMapper.deleteWarehouseByIds(ids);
    }

    /**
     * 删除仓库 信息
     * 
     * @param id 仓库 主键
     * @return 结果
     */
    @Override
    public int deleteWarehouseById(Long id)
    {
        return warehouseMapper.deleteWarehouseById(id);
    }

    /**
     * 初始化用户仓库信息
     * */
    @Override
    public int addWarehouse(SysUser user){
        Warehouse warehouse = new Warehouse();
        warehouse.setUserId(user.getUserId());
        warehouse.setUserPhone(user.getPhonenumber());
        warehouse.setNickName(user.getNickName());
        warehouse.setQuantity(0L);
        warehouse.setTotalPrice("0");
        return warehouseMapper.insertWarehouse(warehouse);
    }

}
