package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.CommonQuestions;
import com.ruoyi.qwk.service.ICommonQuestionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 常见问题Controller
 *
 * @author cjf
 * @date 2023-08-22
 */
@RestController
@RequestMapping("/qwk/commonQuestions")
@Api(tags = "常见问题API")
public class CommonQuestionsController extends BaseController
{
    @Autowired
    private ICommonQuestionsService commonQuestionsService;

    /**
     * 查询常见问题列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:commonQuestions:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommonQuestions commonQuestions)
    {
        startPage();
        List<CommonQuestions> list = commonQuestionsService.selectCommonQuestionsList(commonQuestions);
        return getDataTable(list);
    }

    /**
     * 导出常见问题列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:commonQuestions:export')")
    @Log(title = "常见问题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommonQuestions commonQuestions)
    {
        List<CommonQuestions> list = commonQuestionsService.selectCommonQuestionsList(commonQuestions);
        ExcelUtil<CommonQuestions> util = new ExcelUtil<CommonQuestions>(CommonQuestions.class);
        util.exportExcel(response, list, "常见问题数据");
    }

    /**
     * 获取常见问题详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:commonQuestions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(commonQuestionsService.selectCommonQuestionsById(id));
    }

    /**
     * 新增常见问题
     */
    @PreAuthorize("@ss.hasPermi('qwk:commonQuestions:add')")
    @Log(title = "常见问题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommonQuestions commonQuestions)
    {
        return toAjax(commonQuestionsService.insertCommonQuestions(commonQuestions));
    }

    /**
     * 修改常见问题
     */
    @PreAuthorize("@ss.hasPermi('qwk:commonQuestions:edit')")
    @Log(title = "常见问题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommonQuestions commonQuestions)
    {
        return toAjax(commonQuestionsService.updateCommonQuestions(commonQuestions));
    }

    /**
     * 删除常见问题
     */
    @PreAuthorize("@ss.hasPermi('qwk:commonQuestions:remove')")
    @Log(title = "常见问题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(commonQuestionsService.deleteCommonQuestionsByIds(ids));
    }

//    -----------------------------------------------------------------
    /**
     * 查询常见问题列表
     */
    @GetMapping("/getList")
    @ApiOperation("查询常见问题列表")
    public AjaxResult getList() {
        CommonQuestions commonQuestions = new CommonQuestions();
        commonQuestions.setIsDel("1");
        List<CommonQuestions> list = commonQuestionsService.selectCommonQuestionsList(commonQuestions);
        return success(list);
    }

}
