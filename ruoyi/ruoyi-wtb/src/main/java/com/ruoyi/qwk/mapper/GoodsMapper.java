package com.ruoyi.qwk.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.qwk.domain.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * 商品 Mapper接口
 * 
 * @author cjf
 * @date 2023-06-26
 */
@DataSource(value = DataSourceType.SLAVE)
public interface GoodsMapper 
{
    /**
     * 查询商品 
     * 
     * @param id 商品 主键
     * @return 商品 
     */
    public Goods selectGoodsById(Long id);

    /**
     * 查询商品 列表
     * 
     * @param goods 商品 
     * @return 商品 集合
     */
    public List<Goods> selectGoodsList(Goods goods);

    /**
     * 新增商品 
     * 
     * @param goods 商品 
     * @return 结果
     */
    public int insertGoods(Goods goods);

    /**
     * 修改商品 
     * 
     * @param goods 商品 
     * @return 结果
     */
    public int updateGoods(Goods goods);

    /**
     * 删除商品 
     * 
     * @param id 商品 主键
     * @return 结果
     */
    public int deleteGoodsById(Long id);

    /**
     * 批量删除商品 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsByIds(Long[] ids);

    /**
     * 查询爆款商品
     *
     * @return 商品 集合
     */
    public List<Goods> selectHotSellingItem();

    /**
     * 根据价格区间查找商品
     *
     * */
    public List<Goods> selectGoodsByPriceRange(@Param("low")Integer low,@Param("tall")Integer tall);

    /**
     * 查询所有支持碎片的商品
     *
     * */
    public List<Goods> selectByIsDebris();


}
