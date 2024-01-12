package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.Problem;
import com.ruoyi.qwk.service.IProblemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 问题投诉Controller
 *
 * @author cjf
 * @date 2023-08-30
 */
@RestController
@RequestMapping("/qwk/problem")
@Api(tags = "问题投诉API")
public class ProblemController extends BaseController
{
    @Autowired
    private IProblemService problemService;

    /**
     * 查询问题投诉列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:problem:list')")
    @GetMapping("/list")
    public TableDataInfo list(Problem problem)
    {
        startPage();
        List<Problem> list = problemService.selectProblemList(problem);
        return getDataTable(list);
    }

    /**
     * 导出问题投诉列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:problem:export')")
    @Log(title = "问题投诉", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Problem problem)
    {
        List<Problem> list = problemService.selectProblemList(problem);
        ExcelUtil<Problem> util = new ExcelUtil<Problem>(Problem.class);
        util.exportExcel(response, list, "问题投诉数据");
    }

    /**
     * 获取问题投诉详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:problem:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(problemService.selectProblemById(id));
    }

    /**
     * 新增问题投诉
     */
    @PreAuthorize("@ss.hasPermi('qwk:problem:add')")
    @Log(title = "问题投诉", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Problem problem)
    {
        return toAjax(problemService.insertProblem(problem));
    }

    /**
     * 修改问题投诉
     */
    @PreAuthorize("@ss.hasPermi('qwk:problem:edit')")
    @Log(title = "问题投诉", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Problem problem)
    {
        return toAjax(problemService.updateProblem(problem));
    }

    /**
     * 删除问题投诉
     */
    @PreAuthorize("@ss.hasPermi('qwk:problem:remove')")
    @Log(title = "问题投诉", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(problemService.deleteProblemByIds(ids));
    }

//    --------------------------------------------app-----------------------
    /**
     * 新增问题投诉
     */
    @Log(title = "问题投诉", businessType = BusinessType.INSERT)
    @PostMapping("/addProblem")
    @ApiOperation("添加问题投诉")
    public AjaxResult addProblem(@RequestBody Problem problem){
        return toAjax(problemService.insertProblem(problem));
    }

}
