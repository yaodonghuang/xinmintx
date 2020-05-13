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
import com.xinmintx.system.domain.ProcurementCommodities;
import com.xinmintx.system.service.IProcurementCommoditiesService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 商品审核Controller
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/system/commodities")
public class ProcurementCommoditiesController extends BaseController
{
    private String prefix = "system/commodities";

    @Autowired
    private IProcurementCommoditiesService procurementCommoditiesService;

    @RequiresPermissions("system:commodities:view")
    @GetMapping()
    public String commodities()
    {
        return prefix + "/commodities";
    }

    /**
     * 查询商品审核列表
     */
    @RequiresPermissions("system:commodities:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProcurementCommodities procurementCommodities)
    {
        startPage();
        List<ProcurementCommodities> list = procurementCommoditiesService.selectProcurementCommoditiesList(procurementCommodities);
        return getDataTable(list);
    }

    /**
     * 导出商品审核列表
     */
    @RequiresPermissions("system:commodities:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProcurementCommodities procurementCommodities)
    {
        List<ProcurementCommodities> list = procurementCommoditiesService.selectProcurementCommoditiesList(procurementCommodities);
        ExcelUtil<ProcurementCommodities> util = new ExcelUtil<ProcurementCommodities>(ProcurementCommodities.class);
        return util.exportExcel(list, "commodities");
    }

    /**
     * 新增商品审核
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商品审核
     */
    @RequiresPermissions("system:commodities:add")
    @Log(title = "商品审核", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProcurementCommodities procurementCommodities)
    {
        return toAjax(procurementCommoditiesService.insertProcurementCommodities(procurementCommodities));
    }

    /**
     * 修改商品审核
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ProcurementCommodities procurementCommodities = procurementCommoditiesService.selectProcurementCommoditiesById(id);
        mmap.put("procurementCommodities", procurementCommodities);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品审核
     */
    @RequiresPermissions("system:commodities:edit")
    @Log(title = "商品审核", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProcurementCommodities procurementCommodities)
    {
        return toAjax(procurementCommoditiesService.updateProcurementCommodities(procurementCommodities));
    }

    /**
     * 删除商品审核
     */
    @RequiresPermissions("system:commodities:remove")
    @Log(title = "商品审核", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(procurementCommoditiesService.deleteProcurementCommoditiesByIds(ids));
    }
}
