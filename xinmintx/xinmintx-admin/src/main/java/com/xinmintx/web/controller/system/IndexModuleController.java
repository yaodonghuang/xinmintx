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
import com.xinmintx.system.domain.IndexModule;
import com.xinmintx.system.service.IIndexModuleService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 首页模块配置Controller
 * 
 * @author xinmintx
 * @date 2020-01-06
 */
@Controller
@RequestMapping("/system/module")
public class IndexModuleController extends BaseController
{
    private String prefix = "system/module";

    @Autowired
    private IIndexModuleService indexModuleService;

    @RequiresPermissions("system:module:view")
    @GetMapping()
    public String module()
    {
        return prefix + "/module";
    }

    /**
     * 查询首页模块配置列表
     */
    @RequiresPermissions("system:module:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(IndexModule indexModule)
    {
        startPage();
        List<IndexModule> list = indexModuleService.selectIndexModuleList(indexModule);
        return getDataTable(list);
    }

    /**
     * 导出首页模块配置列表
     */
    @RequiresPermissions("system:module:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IndexModule indexModule)
    {
        List<IndexModule> list = indexModuleService.selectIndexModuleList(indexModule);
        ExcelUtil<IndexModule> util = new ExcelUtil<IndexModule>(IndexModule.class);
        return util.exportExcel(list, "module");
    }

    /**
     * 新增首页模块配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存首页模块配置
     */
    @RequiresPermissions("system:module:add")
    @Log(title = "首页模块配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(IndexModule indexModule)
    {
        return toAjax(indexModuleService.insertIndexModule(indexModule));
    }

    /**
     * 修改首页模块配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        IndexModule indexModule = indexModuleService.selectIndexModuleById(id);
        mmap.put("indexModule", indexModule);
        return prefix + "/edit";
    }

    /**
     * 修改保存首页模块配置
     */
    @RequiresPermissions("system:module:edit")
    @Log(title = "首页模块配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(IndexModule indexModule)
    {
        return toAjax(indexModuleService.updateIndexModule(indexModule));
    }

    /**
     * 删除首页模块配置
     */
    @RequiresPermissions("system:module:remove")
    @Log(title = "首页模块配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(indexModuleService.deleteIndexModuleByIds(ids));
    }
}
