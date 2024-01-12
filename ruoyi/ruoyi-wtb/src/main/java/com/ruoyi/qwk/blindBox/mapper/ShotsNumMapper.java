package com.ruoyi.qwk.blindBox.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.blindBox.domain.ShotsNum;
import org.apache.ibatis.annotations.Param;

/**
 * 射击次数Mapper接口
 * 
 * @author cjf
 * @date 2023-10-20
 */
@DataSource(value = DataSourceType.SLAVE)
public interface ShotsNumMapper 
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
     * 删除射击次数
     * 
     * @param id 射击次数主键
     * @return 结果
     */
    public int deleteShotsNumById(Long id);

    /**
     * 批量删除射击次数
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShotsNumByIds(Long[] ids);

    /**
     * 查询用户射击次数
     *
     * @param userId 用户编号
     * @param fieldId 场次编号
     * @return 射击次数
     */
    public ShotsNum selectShotsNumByUserId(@Param("userId") Long userId,@Param("fieldId") Integer fieldId);
}
