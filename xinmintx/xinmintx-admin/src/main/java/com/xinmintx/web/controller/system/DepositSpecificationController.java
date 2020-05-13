package com.xinmintx.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xinmintx.common.annotation.Log;
import com.xinmintx.common.enums.BusinessType;
import com.xinmintx.system.domain.DepositSpecification;
import com.xinmintx.system.service.IDepositSpecificationService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 提现规格Controller
 * 
 * @author xinmintx
 * @date 2020-01-07
 */
@Controller
@RequestMapping("/system/specification")
public class DepositSpecificationController extends BaseController
{
    private String prefix = "system/specification";

    @Autowired
    private IDepositSpecificationService depositSpecificationService;

    @RequiresPermissions("system:specification:view")
    @GetMapping()
    public String specification()
    {
        return prefix + "/specification";
    }

    /**
     * 查询提现规格列表
     */
    @RequiresPermissions("system:specification:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DepositSpecification depositSpecification)
    {
        startPage();
        List<DepositSpecification> list = depositSpecificationService.selectDepositSpecificationList(depositSpecification);
        return getDataTable(list);
    }

    /**
     * 导出提现规格列表
     */
    @RequiresPermissions("system:specification:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DepositSpecification depositSpecification)
    {
        List<DepositSpecification> list = depositSpecificationService.selectDepositSpecificationList(depositSpecification);
        ExcelUtil<DepositSpecification> util = new ExcelUtil<DepositSpecification>(DepositSpecification.class);
        return util.exportExcel(list, "specification");
    }

    /**
     * 新增提现规格
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存提现规格
     */
    @RequiresPermissions("system:specification:add")
    @Log(title = "提现规格", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DepositSpecification depositSpecification)
    {
        return toAjax(depositSpecificationService.insertDepositSpecification(depositSpecification));
    }

    /**
     * 修改提现规格
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DepositSpecification depositSpecification = depositSpecificationService.selectDepositSpecificationById(id);
        mmap.put("depositSpecification", depositSpecification);
        return prefix + "/edit";
    }

    /**
     * 修改保存提现规格
     */
    @RequiresPermissions("system:specification:edit")
    @Log(title = "提现规格", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DepositSpecification depositSpecification)
    {
        return toAjax(depositSpecificationService.updateDepositSpecification(depositSpecification));
    }

    /**
     * 删除提现规格
     */
    @RequiresPermissions("system:specification:remove")
    @Log(title = "提现规格", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(depositSpecificationService.deleteDepositSpecificationByIds(ids));
    }
}
