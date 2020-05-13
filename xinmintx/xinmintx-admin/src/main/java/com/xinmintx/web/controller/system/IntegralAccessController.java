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
import com.xinmintx.system.domain.IntegralAccess;
import com.xinmintx.system.service.IIntegralAccessService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 积分获取方式Controller
 * 
 * @author xinmintx
 * @date 2019-11-20
 */
@Controller
@RequestMapping("/system/access")
public class IntegralAccessController extends BaseController
{
    private String prefix = "system/access";

    @Autowired
    private IIntegralAccessService integralAccessService;

    @RequiresPermissions("system:access:view")
    @GetMapping()
    public String access()
    {
        return prefix + "/access";
    }

    /**
     * 查询积分获取方式列表
     */
    @RequiresPermissions("system:access:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(IntegralAccess integralAccess)
    {
        startPage();
        List<IntegralAccess> list = integralAccessService.selectIntegralAccessList(integralAccess);
        return getDataTable(list);
    }

    /**
     * 导出积分获取方式列表
     */
    @RequiresPermissions("system:access:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IntegralAccess integralAccess)
    {
        List<IntegralAccess> list = integralAccessService.selectIntegralAccessList(integralAccess);
        ExcelUtil<IntegralAccess> util = new ExcelUtil<IntegralAccess>(IntegralAccess.class);
        return util.exportExcel(list, "access");
    }

    /**
     * 新增积分获取方式
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存积分获取方式
     */
    @RequiresPermissions("system:access:add")
    @Log(title = "积分获取方式", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(IntegralAccess integralAccess)
    {
        return toAjax(integralAccessService.insertIntegralAccess(integralAccess));
    }

    /**
     * 修改积分获取方式
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        IntegralAccess integralAccess = integralAccessService.selectIntegralAccessById(id);
        mmap.put("integralAccess", integralAccess);
        return prefix + "/edit";
    }

    /**
     * 修改保存积分获取方式
     */
    @RequiresPermissions("system:access:edit")
    @Log(title = "积分获取方式", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(IntegralAccess integralAccess)
    {
        return toAjax(integralAccessService.updateIntegralAccess(integralAccess));
    }

    /**
     * 删除积分获取方式
     */
    @RequiresPermissions("system:access:remove")
    @Log(title = "积分获取方式", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(integralAccessService.deleteIntegralAccessByIds(ids));
    }
}
