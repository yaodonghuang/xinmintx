package com.xinmintx.web.controller.system;

import com.xinmintx.common.annotation.Log;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.core.page.TableDataInfo;
import com.xinmintx.common.enums.BusinessType;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.framework.shiro.service.SysPasswordService;
import com.xinmintx.framework.util.ShiroUtils;
import com.xinmintx.system.domain.Factory;
import com.xinmintx.system.service.IFactoryService;
import com.xinmintx.web.controller.tool.File2OSSUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 厂家信息Controller
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/system/factory")
public class FactoryController extends BaseController
{
    private String prefix = "system/factory";

    @Autowired
    private IFactoryService factoryService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("system:factory:view")
    @GetMapping()
    public String factory()
    {
        return prefix + "/factory";
    }

    /**
     * 查询厂家信息列表
     */
    @RequiresPermissions("system:factory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Factory factory)
    {
        startPage();
        List<Factory> list = factoryService.selectFactoryList(factory);
        return getDataTable(list);
    }

    /**
     * 导出厂家信息列表
     */
    @RequiresPermissions("system:factory:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Factory factory)
    {
        List<Factory> list = factoryService.selectFactoryList(factory);
        ExcelUtil<Factory> util = new ExcelUtil<Factory>(Factory.class);
        return util.exportExcel(list, "factory");
    }

    /**
     * 新增厂家信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存厂家信息
     */
    @RequiresPermissions("system:factory:add")
    @Log(title = "厂家信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Factory factory)
    {
        factory.setSalt(ShiroUtils.randomSalt());
        factory.setPassword(passwordService.encryptPassword(factory.getName(), factory.getPassword(), factory.getSalt()));
        return toAjax(factoryService.insertFactory(factory));
    }

    /**
     * 修改厂家信息
     */
    @GetMapping("/edit/{factoryId}")
    public String edit(@PathVariable("factoryId") Long factoryId, ModelMap mmap)
    {
        Factory factory = factoryService.selectFactoryById(factoryId);
        mmap.put("factory", factory);
        return prefix + "/edit";
    }

    /**
     * 修改保存厂家信息
     */
    @RequiresPermissions("system:factory:edit")
    @Log(title = "厂家信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Factory factory)
    {
        factory.setSalt(ShiroUtils.randomSalt());
        factory.setPassword(passwordService.encryptPassword(factory.getName(), factory.getPassword(), factory.getSalt()));
        return toAjax(factoryService.updateFactory(factory));
    }

    /**
     * 删除厂家信息
     */
    @RequiresPermissions("system:factory:remove")
    @Log(title = "厂家信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(factoryService.deleteFactoryByIds(ids));
    }
}
