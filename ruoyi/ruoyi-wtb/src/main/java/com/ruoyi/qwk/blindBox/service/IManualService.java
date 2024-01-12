package com.ruoyi.qwk.blindBox.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.blindBox.domain.Manual;

/**
 * 图鉴Service接口
 * 
 * @author cjf
 * @date 2023-10-31
 */
public interface IManualService 
{
    /**
     * 查询图鉴
     * 
     * @param id 图鉴主键
     * @return 图鉴
     */
    public Manual selectManualById(Long id);

    /**
     * 查询图鉴列表
     * 
     * @param manual 图鉴
     * @return 图鉴集合
     */
    public List<Manual> selectManualList(Manual manual);

    /**
     * 新增图鉴
     * 
     * @param manual 图鉴
     * @return 结果
     */
    public int insertManual(Manual manual);

    /**
     * 修改图鉴
     * 
     * @param manual 图鉴
     * @return 结果
     */
    public int updateManual(Manual manual);

    /**
     * 批量删除图鉴
     * 
     * @param ids 需要删除的图鉴主键集合
     * @return 结果
     */
    public int deleteManualByIds(Long[] ids);

    /**
     * 删除图鉴信息
     * 
     * @param id 图鉴主键
     * @return 结果
     */
    public int deleteManualById(Long id);

    /**
     * 获取图鉴
     * */
    public AjaxResult getManual(Manual manual);

}
