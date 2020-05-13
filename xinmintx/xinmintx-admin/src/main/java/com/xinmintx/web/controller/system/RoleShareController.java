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
import com.xinmintx.system.domain.RoleShare;
import com.xinmintx.system.service.IRoleShareService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 分润规则Controller
 * 
 * @author xinmintx
 * @date 2019-11-22
 */
@Controller
@RequestMapping("/system/share")
public class RoleShareController extends BaseController
{
    private String prefix = "system/share";

    @Autowired
    private IRoleShareService roleShareService;

    @RequiresPermissions("system:share:view")
    @GetMapping()
    public String share()
    {
        return prefix + "/share";
    }

    /**
     * 查询分润规则列表
     */
    @RequiresPermissions("system:share:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RoleShare roleShare)
    {
        startPage();
        List<RoleShare> list = roleShareService.selectRoleShareList(roleShare);
        return getDataTable(list);
    }

    /**
     * 导出分润规则列表
     */
    @RequiresPermissions("system:share:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RoleShare roleShare)
    {
        List<RoleShare> list = roleShareService.selectRoleShareList(roleShare);
        ExcelUtil<RoleShare> util = new ExcelUtil<RoleShare>(RoleShare.class);
        return util.exportExcel(list, "share");
    }

    /**
     * 新增分润规则
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存分润规则
     */
    @RequiresPermissions("system:share:add")
    @Log(title = "分润规则", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RoleShare roleShare)
    {
        return toAjax(roleShareService.insertRoleShare(roleShare));
    }

    /**
     * 修改分润规则
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        RoleShare roleShare = roleShareService.selectRoleShareById(id);
        mmap.put("roleShare", roleShare);
        return prefix + "/edit";
    }

    /**
     * 修改保存分润规则
     */
    @RequiresPermissions("system:share:edit")
    @Log(title = "分润规则", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RoleShare roleShare)
    {
        return toAjax(roleShareService.updateRoleShare(roleShare));
    }

    /**
     * 删除分润规则
     */
    @RequiresPermissions("system:share:remove")
    @Log(title = "分润规则", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(roleShareService.deleteRoleShareByIds(ids));
    }
}
