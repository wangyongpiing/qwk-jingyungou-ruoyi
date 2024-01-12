package com.ruoyi.qwk.blindBox.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.blindBox.mapper.JackpotMapper;
import com.ruoyi.qwk.blindBox.domain.Jackpot;
import com.ruoyi.qwk.blindBox.service.IJackpotService;

/**
 * 奖池Service业务层处理
 * 
 * @author cjf
 * @date 2023-09-24
 */
@Service
public class JackpotServiceImpl implements IJackpotService 
{
    @Autowired
    private JackpotMapper jackpotMapper;

    /**
     * 查询奖池
     * 
     * @param id 奖池主键
     * @return 奖池
     */
    @Override
    public Jackpot selectJackpotById(Long id)
    {
        return jackpotMapper.selectJackpotById(id);
    }

    /**
     * 查询奖池列表
     * 
     * @param jackpot 奖池
     * @return 奖池
     */
    @Override
    public List<Jackpot> selectJackpotList(Jackpot jackpot)
    {
        return jackpotMapper.selectJackpotList(jackpot);
    }

    /**
     * 新增奖池
     * 
     * @param jackpot 奖池
     * @return 结果
     */
    @Override
    public int insertJackpot(Jackpot jackpot)
    {
        jackpot.setCreateTime(DateUtils.getNowDate());
        return jackpotMapper.insertJackpot(jackpot);
    }

    /**
     * 修改奖池
     * 
     * @param jackpot 奖池
     * @return 结果
     */
    @Override
    public int updateJackpot(Jackpot jackpot)
    {
        jackpot.setUpdateTime(DateUtils.getNowDate());
        return jackpotMapper.updateJackpot(jackpot);
    }

    /**
     * 批量删除奖池
     * 
     * @param ids 需要删除的奖池主键
     * @return 结果
     */
    @Override
    public int deleteJackpotByIds(Long[] ids)
    {
        return jackpotMapper.deleteJackpotByIds(ids);
    }

    /**
     * 删除奖池信息
     * 
     * @param id 奖池主键
     * @return 结果
     */
    @Override
    public int deleteJackpotById(Long id)
    {
        return jackpotMapper.deleteJackpotById(id);
    }
}
