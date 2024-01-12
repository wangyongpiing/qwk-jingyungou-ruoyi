package com.ruoyi.web.controller.business;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.Goods;
import com.ruoyi.qwk.service.IGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品 Controller
 *
 * @author cjf
 * @date 2023-06-26
 */
@RestController
@RequestMapping("/qwk/goods")
@Api(tags = "商品 API 接口")
public class GoodsController extends BaseController
{
    @Autowired
    private IGoodsService goodsService;

    /**
     * 查询商品 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(Goods goods)
    {
        startPage();
        List<Goods> list = goodsService.selectGoodsList(goods);
        return getDataTable(list);
    }



    /**
     * 导出商品 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:goods:export')")
    @Log(title = "商品 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Goods goods)
    {
        List<Goods> list = goodsService.selectGoodsList(goods);
        ExcelUtil<Goods> util = new ExcelUtil<Goods>(Goods.class);
        util.exportExcel(response, list, "商品 数据");
    }

    /**
     * 获取商品 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:goods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(goodsService.selectGoodsById(id));
    }



    /**
     * 新增商品
     */
    @PreAuthorize("@ss.hasPermi('qwk:goods:add')")
    @Log(title = "商品 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Goods goods)
    {
        return toAjax(goodsService.insertGoods(goods));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.hasPermi('qwk:goods:edit')")
    @Log(title = "商品 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Goods goods)
    {
        return toAjax(goodsService.updateGoods(goods));
    }

    /**
     * 上架或下架商品
     */
    @PreAuthorize("@ss.hasPermi('qwk:goods:edit')")
    @Log(title = "商品 ", businessType = BusinessType.UPDATE)
//    @ApiOperation(value = "上架或下架商品", notes = "")
    @PutMapping("/updateGoodsList")
    public AjaxResult updateGoodsList(@RequestBody List<Goods> goods)
    {
        return toAjax(goodsService.updateGoodsList(goods));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.hasPermi('qwk:goods:remove')")
    @Log(title = "商品 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(goodsService.deleteGoodsByIds(ids));
    }


//    ---------------------------app接口-------------------------------

    /**
     * App查询商品列表
     */
    @PostMapping("/appList")
    @ApiOperation(value = "查询商品列表", notes = "")
    public AjaxResult appList(@RequestBody Goods goods)
    {
//        startPage();
        goods.setStatus("1");
        //获取助农商品
//        if("2".equals(goods.getType())){
//            List<Long> categoryIdList = Arrays.asList(19L,20L,21L,22L);
//            goods.setParams(new HashMap<String, Object>(){{
//                put("categoryIdList",categoryIdList);
//            }});
//        }
        List<Goods> list = goodsService.selectGoodsList(goods);
//        TableDataInfo dataTable = getDataTable(list);
        return AjaxResult.success(list);
    }

    /**
     * App获取商品 详细信息
     */
    @GetMapping(value = "/getAppInfo/{id}")
    @ApiOperation(value = "获取商品详细信息", notes = "")
    public AjaxResult getAppInfo(@PathVariable("id") Long id)
    {
        return success(goodsService.selectGoodsById(id));
    }

    /**
     * App查询爆款商品
     *
     * @return 商品 集合
     */
    @GetMapping(value = "/getHotSellingItem")
    @ApiOperation(value = "查询爆款商品", notes = "")
    public AjaxResult selectHotSellingItem(){
        return success(goodsService.selectHotSellingItem());
    }

    /**
     * App查询助农产品
     *
     * @return 助农产品集合
     */
    @GetMapping(value = "/agriculturalProducts")
    @ApiOperation(value = "App查询助农产品", notes = "")
    public AjaxResult selectAgriculturalProducts(@RequestParam("type") String type){
        return AjaxResult.success(goodsService.selectAgriculturalProducts(type));
    }

}
