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
import com.xinmintx.system.domain.TbSeller;
import com.xinmintx.system.service.ITbSellerService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author xinmintx
 * @date 2019-12-25
 */
@Controller
@RequestMapping("/system/seller")
public class TbSellerController extends BaseController
{
    private String prefix = "system/seller";

    @Autowired
    private ITbSellerService tbSellerService;

    @RequiresPermissions("system:seller:view")
    @GetMapping()
    public String seller()
    {
        return prefix + "/seller";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:seller:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbSeller tbSeller)
    {
        startPage();
        List<TbSeller> list = tbSellerService.selectTbSellerList(tbSeller);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:seller:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbSeller tbSeller)
    {
        List<TbSeller> list = tbSellerService.selectTbSellerList(tbSeller);
        ExcelUtil<TbSeller> util = new ExcelUtil<TbSeller>(TbSeller.class);
        return util.exportExcel(list, "seller");
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
    @RequiresPermissions("system:seller:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbSeller tbSeller)
    {
        return toAjax(tbSellerService.insertTbSeller(tbSeller));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{sellerId}")
    public String edit(@PathVariable("sellerId") String sellerId, ModelMap mmap)
    {
        TbSeller tbSeller = tbSellerService.selectTbSellerById(sellerId);
        mmap.put("tbSeller", tbSeller);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:seller:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbSeller tbSeller)
    {
        return toAjax(tbSellerService.updateTbSeller(tbSeller));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:seller:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbSellerService.deleteTbSellerByIds(ids));
    }
}
