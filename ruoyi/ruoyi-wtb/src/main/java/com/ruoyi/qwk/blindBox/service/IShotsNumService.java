package com.ruoyi.qwk.blindBox.service;

import java.util.List;
import com.ruoyi.qwk.blindBox.domain.ShotsNum;

/**
 * 射击次数Service接口
 * 
 * @author cjf
 * @date 2023-10-20
 */
public interface IShotsNumService 
{
    /**
     * 查询射击次数
     * 
     * @param id 射击次数主键
     * @return 射击次数
     */
    public ShotsNum selectShotsNumById(Long id);

    /**
     * 查询射击次数列表
     * 
     * @param shotsNum 射击次数
     * @return 射击次数集合
     */
    public List<ShotsNum> selectShotsNumList(ShotsNum shotsNum);

    /**
     * 新增射击次数
     * 
     * @param shotsNum 射击次数
     * @return 结果
     */
    public int insertShotsNum(ShotsNum shotsNum);

    /**
     * 修改射击次数
     * 
     * @param shotsNum 射击次数
     * @return 结果
     */
    public int updateShotsNum(ShotsNum shotsNum);

    /**
     * 批量删除射击次数
     * 
     * @param ids 需要删除的射击次数主键集合
     * @return 结果
     */
    public int deleteShotsNumByIds(Long[] ids);

    /**
     * 删除射击次数信息
     * 
     * @param id 射击次数主键
     * @return 结果
     */
    public int deleteShotsNumById(Long id);
}
