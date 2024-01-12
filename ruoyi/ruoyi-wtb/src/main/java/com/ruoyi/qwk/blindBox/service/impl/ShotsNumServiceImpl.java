package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.ShotsNumMapper;
import com.ruoyi.qwk.blindBox.domain.ShotsNum;
import com.ruoyi.qwk.blindBox.service.IShotsNumService;

/**
 * 射击次数Service业务层处理
 * 
 * @author cjf
 * @date 2023-10-20
 */
@Service
public class ShotsNumServiceImpl implements IShotsNumService 
{
    @Autowired
    private ShotsNumMapper shotsNumMapper;

    /**
     * 查询射击次数
     * 
     * @param id 射击次数主键
     * @return 射击次数
     */
    @Override
    public ShotsNum selectShotsNumById(Long id)
    {
        return shotsNumMapper.selectShotsNumById(id);
    }

    /**
     * 查询射击次数列表
     * 
     * @param shotsNum 射击次数
     * @return 射击次数
     */
    @Override
    public List<ShotsNum> selectShotsNumList(ShotsNum shotsNum)
    {
        return shotsNumMapper.selectShotsNumList(shotsNum);
    }

    /**
     * 新增射击次数
     * 
     * @param shotsNum 射击次数
     * @return 结果
     */
    @Override
    public int insertShotsNum(ShotsNum shotsNum)
    {
        shotsNum.setCreateTime(DateUtils.getNowDate());
        return shotsNumMapper.insertShotsNum(shotsNum);
    }

    /**
     * 修改射击次数
     * 
     * @param shotsNum 射击次数
     * @return 结果
     */
    @Override
    public int updateShotsNum(ShotsNum shotsNum)
    {
        shotsNum.setUpdateTime(DateUtils.getNowDate());
        return shotsNumMapper.updateShotsNum(shotsNum);
    }

    /**
     * 批量删除射击次数
     * 
     * @param ids 需要删除的射击次数主键
     * @return 结果
     */
    @Override
    public int deleteShotsNumByIds(Long[] ids)
    {
        return shotsNumMapper.deleteShotsNumByIds(ids);
    }

    /**
     * 删除射击次数信息
     * 
     * @param id 射击次数主键
     * @return 结果
     */
    @Override
    public int deleteShotsNumById(Long id)
    {
        return shotsNumMapper.deleteShotsNumById(id);
    }
}
