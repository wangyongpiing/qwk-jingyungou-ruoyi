package com.ruoyi.qwk.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.qwk.domain.AssetDetails;
import com.ruoyi.qwk.mapper.AssetDetailsMapper;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.UserAssetsMapper;
import com.ruoyi.qwk.domain.UserAssets;
import com.ruoyi.qwk.service.IUserAssetsService;
import org.springframework.util.ObjectUtils;

/**
 * 用户资产 Service业务层处理
 * 
 * @author cjf
 * @date 2023-06-29
 */
@Service
public class UserAssetsServiceImpl implements IUserAssetsService
{
    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 查询用户资产 
     * 
     * @param id 用户资产 主键
     * @return 用户资产 
     */
    @Override
    public UserAssets selectUserAssetsById(Long id)
    {
        return userAssetsMapper.selectUserAssetsById(id);
    }

    /**
     * 查询用户资产 列表
     * 
     * @param userAssets 用户资产 
     * @return 用户资产 
     */
    @Override
    public List<UserAssets> selectUserAssetsList(UserAssets userAssets)
    {
        return userAssetsMapper.selectUserAssetsList(userAssets);
    }

    /**
     * 新增用户资产 
     * 
     * @param userAssets 用户资产 
     * @return 结果
     */
    @Override
    public int insertUserAssets(UserAssets userAssets)
    {
        return userAssetsMapper.insertUserAssets(userAssets);
    }

    /**
     * 修改用户资产 
     * 
     * @param userAssets 用户资产 
     * @return 结果
     */
    @Override
    public int updateUserAssets(UserAssets userAssets)
    {
        return userAssetsMapper.updateUserAssets(userAssets);
    }

    /**
     * 批量删除用户资产 
     * 
     * @param ids 需要删除的用户资产 主键
     * @return 结果
     */
    @Override
    public int deleteUserAssetsByIds(Long[] ids)
    {
        return userAssetsMapper.deleteUserAssetsByIds(ids);
    }

    /**
     * 删除用户资产 信息
     * 
     * @param id 用户资产 主键
     * @return 结果
     */
    @Override
    public int deleteUserAssetsById(Long id)
    {
        return userAssetsMapper.deleteUserAssetsById(id);
    }

    /**
     * 根据用户id查询用户资产
     *
     * @param userId 用户id
     * @param type 资产类型
     * @return 结果
     */
    @Override
    public UserAssets selectByUserId( Long userId, Long type){
        return userAssetsMapper.selectByUserId(userId,type);
    }

    /**
     * 游戏获取资产
     *
     * @param userId 用户id
     * @param gameId 游戏id
     * @param type 用户资产类型
     * @param number 数量
     * @return 结果
     */
    @Override
    public int updateAssetsByGame(Long userId,Long gameId, Long type, Integer number) {
        Date date = new Date();
        int i = 0;
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        if (ObjectUtils.isEmpty(sysUser))return i;
        UserAssets userAssets = userAssetsMapper.selectByUserId(userId, type);
        Integer total = Integer.parseInt(userAssets.getTotal())+number;
        userAssets.setTotal(total.toString());
        userAssets.setUpdatedTime(date);
        userAssets.setUpdatedBy(sysUser.getUserName());

        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setUserId(userId);
        assetDetails.setUserPhone(sysUser.getPhonenumber());
        assetDetails.setType(type);
        assetDetails.setCreatedBy(sysUser.getUserName());
        assetDetails.setCreatedTime(date);
        assetDetails.setBalance(total.toString());
        assetDetails.setRecords(number.toString());
        assetDetails.setAddOrSubtract("1");
        String s = dictDataMapper.selectDictLabel("qwk_game", gameId.toString());
        assetDetails.setNotes(s+"游戏获取");
        i+=assetDetailsMapper.insertAssetDetails(assetDetails);
        i+= userAssetsMapper.updateUserAssets(userAssets);
        return i;
    }
}
