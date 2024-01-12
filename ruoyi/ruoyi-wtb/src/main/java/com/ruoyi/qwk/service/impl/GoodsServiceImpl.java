package com.ruoyi.qwk.service.impl;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qwk.mapper.GoodsMapper;
import com.ruoyi.qwk.domain.Goods;
import com.ruoyi.qwk.service.IGoodsService;

/**
 * 商品 Service业务层处理
 *
 * @author cjf
 * @date 2023-06-26
 */
@Service
public class GoodsServiceImpl implements IGoodsService
{
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询商品
     *
     * @param id 商品 主键
     * @return 商品
     */
    @Override
    public Goods selectGoodsById(Long id)
    {
        return goodsMapper.selectGoodsById(id);
    }

    /**
     * 查询商品 列表
     *
     * @param goods 商品
     * @return 商品
     */
    @Override
    public List<Goods> selectGoodsList(Goods goods)
    {
        return goodsMapper.selectGoodsList(goods);
    }

    /**
     * 新增商品
     *
     * @param goods 商品
     * @return 结果
     */
    @Override
    public int insertGoods(Goods goods){
        goods.setCreatedTime(new Date());
        return goodsMapper.insertGoods(goods);
    }

    /**
     * 修改商品
     *
     * @param goods 商品
     * @return 结果
     */
    @Override
    public int updateGoods(Goods goods){
        Date date = new Date();
        goods.setUpdatedBy(SecurityUtils.getUsername());
        goods.setUpdatedTime(date);
        return goodsMapper.updateGoods(goods);
    }

    /**
     * 批量修改商品
     *
     * @param goods 商品
     * @return 结果
     */
    @Override
    public int updateGoodsList(List<Goods> goods) {
        int i = 0;
        Date date = new Date();
        for (Goods good : goods) {
            switch (good.getStatus()){
                case "1":
                    good.setListingTime(date);
                    break;
                case "2":
                    good.setOffShelfTime(date);
                    break;
                default:
                    break;
            }
            good.setUpdatedBy(SecurityUtils.getUsername());
            good.setUpdatedTime(date);
            i += goodsMapper.updateGoods(good);
        }
        return i;
    }

    /**
     * 批量删除商品
     *
     * @param ids 需要删除的商品 主键
     * @return 结果
     */
    @Override
    public int deleteGoodsByIds(Long[] ids)
    {
        return goodsMapper.deleteGoodsByIds(ids);
    }

    /**
     * 删除商品 信息
     *
     * @param id 商品 主键
     * @return 结果
     */
    @Override
    public int deleteGoodsById(Long id)
    {
        return goodsMapper.deleteGoodsById(id);
    }

    /**
     * 查询爆款商品
     *
     * @return 商品 集合
     */
    @Override
    public List<Goods> selectHotSellingItem(){
        return goodsMapper.selectHotSellingItem();
    }

    /**
     * 根据价格区间查找商品
     *
     * */
    @Override
    public List<Goods> selectGoodsByPriceRange(Integer low,Integer tall){
        return goodsMapper.selectGoodsByPriceRange(low,tall);
    }

    /**
     * 查询所有支持碎片的商品
     *
     * */
    @Override
    public List<Goods> selectByIsDebris(){
        return goodsMapper.selectByIsDebris();
    }

    /**
     * App查询助农产品
     *
     * @return 助农产品集合
     */
    public AjaxResult selectAgriculturalProducts(String type) {
        Goods goods = new Goods();
        goods.setStatus("1");
        if(StrUtil.isEmpty(type)){
            List<Long> categoryIdList = Arrays.asList(19L,20L,21L,22L);
            goods.setParams(new HashMap<String, Object>(){{
                put("categoryIdList",categoryIdList);
            }});
        }else{
            goods.setCategoryId(Long.parseLong(type));
        }
        List<Goods> list = goodsMapper.selectGoodsList(goods);
        return AjaxResult.success(list);
    }
}
