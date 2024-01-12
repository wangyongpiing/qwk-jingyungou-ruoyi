package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.HomepageGame;
import com.ruoyi.qwk.service.IHomepageGameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 首页游戏列 Controller
 *
 * @author cjf
 * @date 2023-11-27
 */
@RestController
@RequestMapping("/qwk/game")
@Api(tags = "app首页游戏API")
public class HomepageGameController extends BaseController
{
    @Autowired
    private IHomepageGameService homepageGameService;

    /**
     * 查询首页游戏列 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:list')")
    @PostMapping("/list")
    public TableDataInfo list(HomepageGame homepageGame)
    {
        startPage();
        List<HomepageGame> list = homepageGameService.selectHomepageGameList(homepageGame);
        return getDataTable(list);
    }

    /**
     * 导出首页游戏列 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:export')")
    @Log(title = "首页游戏列 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HomepageGame homepageGame)
    {
        List<HomepageGame> list = homepageGameService.selectHomepageGameList(homepageGame);
        ExcelUtil<HomepageGame> util = new ExcelUtil<HomepageGame>(HomepageGame.class);
        util.exportExcel(response, list, "首页游戏列 数据");
    }

    /**
     * 获取首页游戏列 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(homepageGameService.selectHomepageGameById(id));
    }

    /**
     * 新增首页游戏列
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:add')")
    @Log(title = "首页游戏列 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomepageGame homepageGame)
    {
        return toAjax(homepageGameService.insertHomepageGame(homepageGame));
    }

    /**
     * 修改首页游戏列
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:edit')")
    @Log(title = "首页游戏列 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomepageGame homepageGame)
    {
        return toAjax(homepageGameService.updateHomepageGame(homepageGame));
    }

    /**
     * 删除首页游戏列
     */
    @PreAuthorize("@ss.hasPermi('qwk:game:remove')")
    @Log(title = "首页游戏列 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homepageGameService.deleteHomepageGameByIds(ids));
    }

    /**
     * 查询APP首页游戏列表，图片组
     * @return
     */
    @ApiOperation(value = "查询APP首页游戏列表，图片组")
    @GetMapping(value = "/app/list")
    public AjaxResult getAppHomepageGame(){
        return success(homepageGameService.getAppHomepageGame());
    }
}
