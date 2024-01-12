package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.Manual;

/**
 * 图鉴Mapper接口
 * 
 * @author cjf
 * @date 2023-10-31
 */
@DataSource(value = DataSourceType.SLAVE)
public interface ManualMapper 
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
     * 删除图鉴
     * 
     * @param id 图鉴主键
     * @return 结果
     */
    public int deleteManualById(Long id);

    /**
     * 批量删除图鉴
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteManualByIds(Long[] ids);
}
