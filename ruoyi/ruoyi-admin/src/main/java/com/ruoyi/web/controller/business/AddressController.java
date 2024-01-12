package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qwk.domain.Address;
import com.ruoyi.qwk.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 地址管理 Controller
 *
 * @author cjf
 * @date 2023-06-26
 */
@RestController
@RequestMapping("/qwk/address")
public class AddressController extends BaseController
{
    @Autowired
    private IAddressService addressService;

    /**
     * 查询地址管理 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:address:list')")
    @GetMapping("/list")
    public AjaxResult list(Address address)
    {
        List<Address> list = addressService.selectAddressList(address);
        return success(list);
    }

    /**
     * 导出地址管理 列表
     */
    @PreAuthorize("@ss.hasPermi('qwk:address:export')")
    @Log(title = "地址管理 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Address address)
    {
        List<Address> list = addressService.selectAddressList(address);
        ExcelUtil<Address> util = new ExcelUtil<Address>(Address.class);
        util.exportExcel(response, list, "地址管理 数据");
    }

    /**
     * 获取地址管理 详细信息
     */
    @PreAuthorize("@ss.hasPermi('qwk:address:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(addressService.selectAddressById(id));
    }

    /**
     * 新增地址管理
     */
    @PreAuthorize("@ss.hasPermi('qwk:address:add')")
    @Log(title = "地址管理 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Address address)
    {
        return toAjax(addressService.insertAddress(address));
    }

    /**
     * 修改地址管理
     */
    @PreAuthorize("@ss.hasPermi('qwk:address:edit')")
    @Log(title = "地址管理 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Address address)
    {
        return toAjax(addressService.updateAddress(address));
    }

    /**
     * 删除地址管理
     */
    @PreAuthorize("@ss.hasPermi('qwk:address:remove')")
    @Log(title = "地址管理 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(addressService.deleteAddressByIds(ids));
    }
}
