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
import com.xinmintx.system.domain.Profit;
import com.xinmintx.system.service.IProfitService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author xinmintx
 * @date 2019-11-15
 */
@Controller
@RequestMapping("/system/profit")
public class ProfitController extends BaseController
{
    private String prefix = "system/profit";

    @Autowired
    private IProfitService profitService;

    @RequiresPermissions("system:profit:view")
    @GetMapping()
    public String profit()
    {
        return prefix + "/profit";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:profit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Profit profit)
    {
        startPage();
        List<Profit> list = profitService.selectProfitList(profit);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:profit:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Profit profit)
    {
        List<Profit> list = profitService.selectProfitList(profit);
        ExcelUtil<Profit> util = new ExcelUtil<Profit>(Profit.class);
        return util.exportExcel(list, "profit");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("system:profit:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Profit profit)
    {
        return toAjax(profitService.insertProfit(profit));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Profit profit = profitService.selectProfitById(id);
        mmap.put("profit", profit);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:profit:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Profit profit)
    {
        return toAjax(profitService.updateProfit(profit));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:profit:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(profitService.deleteProfitByIds(ids));
    }
}
