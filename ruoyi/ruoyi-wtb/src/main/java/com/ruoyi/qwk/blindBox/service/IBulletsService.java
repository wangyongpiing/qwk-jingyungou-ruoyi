package com.ruoyi.qwk.blindBox.service;

import java.util.List;
import com.ruoyi.qwk.blindBox.domain.Bullets;

/**
 * 子弹配置Service接口
 * 
 * @author cjf
 * @date 2023-09-14
 */
public interface IBulletsService 
{
    /**
     * 查询子弹配置
     * 
     * @param id 子弹配置主键
     * @return 子弹配置
     */
    public Bullets selectBulletsById(Long id);

    /**
     * 查询子弹配置列表
     * 
     * @param bullets 子弹配置
     * @return 子弹配置集合
     */
    public List<Bullets> selectBulletsList(Bullets bullets);

    /**
     * 新增子弹配置
     * 
     * @param bullets 子弹配置
     * @return 结果
     */
    public int insertBullets(Bullets bullets);

    /**
     * 修改子弹配置
     * 
     * @param bullets 子弹配置
     * @return 结果
     */
    public int updateBullets(Bullets bullets);

    /**
     * 批量删除子弹配置
     * 
     * @param ids 需要删除的子弹配置主键集合
     * @return 结果
     */
    public int deleteBulletsByIds(Long[] ids);

    /**
     * 删除子弹配置信息
     * 
     * @param id 子弹配置主键
     * @return 结果
     */
    public int deleteBulletsById(Long id);

    /**
     * 查询所有子弹配置
     * */
    public List<Bullets> getAllBullets();

    /**
     * 击发子弹扣除积分
     * @param bulletsId 子弹配置id
     * */
    public int shoot(Long bulletsId);

}
