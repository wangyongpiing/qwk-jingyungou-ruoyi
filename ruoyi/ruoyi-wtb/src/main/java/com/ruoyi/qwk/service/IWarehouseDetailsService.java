package com.ruoyi.qwk.service;

import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.WarehouseDetails;

/**
 * 仓库详情 Service接口
 * 
 * @author cjf
 * @date 2023-06-29
 */
public interface IWarehouseDetailsService 
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
     * 批量删除仓库详情 
     * 
     * @param ids 需要删除的仓库详情 主键集合
     * @return 结果
     */
    public int deleteWarehouseDetailsByIds(Long[] ids);

    /**
     * 删除仓库详情 信息
     * 
     * @param id 仓库详情 主键
     * @return 结果
     */
    public int deleteWarehouseDetailsById(Long id);

    /**
     * 兑换积分
     *
     * @param wd 仓库详情
     * @return 结果
     */
    public AjaxResult exchange(WarehouseDetails wd);

    /**
     * 使用道具
     *
     * @param id 仓库详情 主键
     * @return 结果
     */
    public int useProps(Long id);

    /**
     * 是否掉落道具
     *
     * @param userId 用户id
     * @param gameId 游戏id
     * @param num 关卡数
     * @return 结果
     */
    public int ifFallen(Long userId,Long gameId,Long num);

    /**
     * 转赠好友
     *
     * @param wDId 仓库详情id 主键
     * @param userId 好友id
     * @return 结果
     */
    public int transferFriends(Long wDId,String userId);

    /**
     * 好友助力
     *
     * @param wDId 仓库详情id
     * */
    public int addAssistanceNum(Long wDId);


    /**
     * 兑换糖果
     * */
    public AjaxResult exchangeCandy(HashMap<String,Object> map);

}
