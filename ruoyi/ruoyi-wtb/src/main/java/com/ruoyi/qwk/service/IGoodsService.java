package com.ruoyi.qwk.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qwk.domain.Goods;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品 Service接口
 *
 * @author cjf
 * @date 2023-06-26
 */
public interface IGoodsService
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
     * 批量修改商品
     *
     * @param goods 商品
     * @return 结果
     */
    public int updateGoodsList(List<Goods> goods);

    /**
     * 批量删除商品
     *
     * @param ids 需要删除的商品 主键集合
     * @return 结果
     */
    public int deleteGoodsByIds(Long[] ids);

    /**
     * 删除商品 信息
     *
     * @param id 商品 主键
     * @return 结果
     */
    public int deleteGoodsById(Long id);

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
    public List<Goods> selectGoodsByPriceRange(Integer low,Integer tall);

    /**
     * 查询所有支持碎片的商品
     *
     * */
    public List<Goods> selectByIsDebris();

    /**
     * App查询助农产品
     *
     * @return 助农产品集合
     */
    public AjaxResult selectAgriculturalProducts(String type);
}
