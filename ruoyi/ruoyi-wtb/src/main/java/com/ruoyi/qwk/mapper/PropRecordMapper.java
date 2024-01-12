package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.PropRecord;

/**
 * 道具产出记录Mapper接口
 * 
 * @author cjf
 * @date 2023-07-31
 */
@DataSource(value = DataSourceType.SLAVE)
public interface PropRecordMapper 
{
    /**
     * 查询道具产出记录
     * 
     * @param id 道具产出记录主键
     * @return 道具产出记录
     */
    public PropRecord selectPropRecordById(Long id);

    /**
     * 查询道具产出记录列表
     * 
     * @param propRecord 道具产出记录
     * @return 道具产出记录集合
     */
    public List<PropRecord> selectPropRecordList(PropRecord propRecord);

    /**
     * 新增道具产出记录
     * 
     * @param propRecord 道具产出记录
     * @return 结果
     */
    public int insertPropRecord(PropRecord propRecord);

    /**
     * 修改道具产出记录
     * 
     * @param propRecord 道具产出记录
     * @return 结果
     */
    public int updatePropRecord(PropRecord propRecord);

    /**
     * 删除道具产出记录
     * 
     * @param id 道具产出记录主键
     * @return 结果
     */
    public int deletePropRecordById(Long id);

    /**
     * 批量删除道具产出记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePropRecordByIds(Long[] ids);
}
