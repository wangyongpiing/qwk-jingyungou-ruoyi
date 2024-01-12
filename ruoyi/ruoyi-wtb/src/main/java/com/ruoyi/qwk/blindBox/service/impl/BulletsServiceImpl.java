package com.ruoyi.qwk.blindBox.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.qwk.domain.AssetDetails;
import com.ruoyi.qwk.domain.UserAssets;
import com.ruoyi.qwk.mapper.AssetDetailsMapper;
import com.ruoyi.qwk.mapper.UserAssetsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.BulletsMapper;
import com.ruoyi.qwk.blindBox.domain.Bullets;
import com.ruoyi.qwk.blindBox.service.IBulletsService;

/**
 * 子弹配置Service业务层处理
 * 
 * @author cjf
 * @date 2023-09-14
 */
@Service
public class BulletsServiceImpl implements IBulletsService 
{
    @Autowired
    private BulletsMapper bulletsMapper;

    @Autowired
    private UserAssetsMapper userAssetsMapper;

    @Autowired
    private AssetDetailsMapper assetDetailsMapper;

    /**
     * 查询子弹配置
     * 
     * @param id 子弹配置主键
     * @return 子弹配置
     */
    @Override
    public Bullets selectBulletsById(Long id)
    {
        return bulletsMapper.selectBulletsById(id);
    }

    /**
     * 查询子弹配置列表
     * 
     * @param bullets 子弹配置
     * @return 子弹配置
     */
    @Override
    public List<Bullets> selectBulletsList(Bullets bullets)
    {
        return bulletsMapper.selectBulletsList(bullets);
    }

    /**
     * 新增子弹配置
     * 
     * @param bullets 子弹配置
     * @return 结果
     */
    @Override
    public int insertBullets(Bullets bullets)
    {
        bullets.setCreateTime(DateUtils.getNowDate());
        return bulletsMapper.insertBullets(bullets);
    }

    /**
     * 修改子弹配置
     * 
     * @param bullets 子弹配置
     * @return 结果
     */
    @Override
    public int updateBullets(Bullets bullets)
    {
        bullets.setUpdateTime(DateUtils.getNowDate());
        return bulletsMapper.updateBullets(bullets);
    }

    /**
     * 批量删除子弹配置
     * 
     * @param ids 需要删除的子弹配置主键
     * @return 结果
     */
    @Override
    public int deleteBulletsByIds(Long[] ids)
    {
        return bulletsMapper.deleteBulletsByIds(ids);
    }

    /**
     * 删除子弹配置信息
     * 
     * @param id 子弹配置主键
     * @return 结果
     */
    @Override
    public int deleteBulletsById(Long id)
    {
        return bulletsMapper.deleteBulletsById(id);
    }

    /**
     * 查询所有子弹配置
     * */
    @Override
    public List<Bullets> getAllBullets(){
        return bulletsMapper.getAllBullets();
    }

    /**
     * 击发子弹扣除积分
     * @param bulletsId 子弹配置id
     * */
    @Override
    public int shoot(Long bulletsId){
        Bullets bullets = bulletsMapper.selectBulletsById(bulletsId);
        UserAssets userAssets = userAssetsMapper.selectByUserId(SecurityUtils.getLoginUser().getUser().getUserId(), 1L);//获取用户积分详情
        Double total = Double.parseDouble(userAssets.getTotal())-Double.parseDouble(bullets.getConsume().toString());
        if (total<0)return 0;
        int value = 0;
        Date date = new Date();
        userAssets.setTotal(StringUtils.doubleTrans(total));
        userAssets.setUpdatedTime(date);
        userAssets.setUpdatedBy("盲盒消耗修改");
        value += userAssetsMapper.updateUserAssets(userAssets);
        value += assetDetailsMapper.insertAssetDetails(getAssetDetails(date,bullets.getConsume().toString(),StringUtils.doubleTrans(total)));//添加积分消耗记录
        return value;
    }

    private AssetDetails getAssetDetails(Date date,String records,String balance){
        AssetDetails assetDetails = new AssetDetails();
        assetDetails.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        assetDetails.setUserPhone(SecurityUtils.getLoginUser().getUser().getPhonenumber());
        assetDetails.setType(1L);
        assetDetails.setNotes("盲盒游戏消耗");
        assetDetails.setImage("https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/images%2F1694419558989.jpg");
        assetDetails.setAddOrSubtract("0");
        assetDetails.setRecords(records);
        assetDetails.setBalance(balance);
        assetDetails.setCreatedBy(SecurityUtils.getLoginUser().getUser().getUserName());
        assetDetails.setCreatedTime(date);
        return assetDetails;
    }

}
