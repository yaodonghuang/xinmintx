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
import com.xinmintx.system.domain.MerchantPrinter;
import com.xinmintx.system.service.IMerchantPrinterService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 商户打印机编号Controller
 * 
 * @author xinmintx
 * @date 2020-03-11
 */
@Controller
@RequestMapping("/system/printer")
public class MerchantPrinterController extends BaseController
{
    private String prefix = "system/printer";

    @Autowired
    private IMerchantPrinterService merchantPrinterService;

    @RequiresPermissions("system:printer:view")
    @GetMapping()
    public String printer()
    {
        return prefix + "/printer";
    }

    /**
     * 查询商户打印机编号列表
     */
    @RequiresPermissions("system:printer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MerchantPrinter merchantPrinter)
    {
        startPage();
        List<MerchantPrinter> list = merchantPrinterService.selectMerchantPrinterList(merchantPrinter);
        return getDataTable(list);
    }

    /**
     * 导出商户打印机编号列表
     */
    @RequiresPermissions("system:printer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MerchantPrinter merchantPrinter)
    {
        List<MerchantPrinter> list = merchantPrinterService.selectMerchantPrinterList(merchantPrinter);
        ExcelUtil<MerchantPrinter> util = new ExcelUtil<MerchantPrinter>(MerchantPrinter.class);
        return util.exportExcel(list, "printer");
    }

    /**
     * 新增商户打印机编号
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商户打印机编号
     */
    @RequiresPermissions("system:printer:add")
    @Log(title = "商户打印机编号", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MerchantPrinter merchantPrinter)
    {
        return toAjax(merchantPrinterService.insertMerchantPrinter(merchantPrinter));
    }

    /**
     * 修改商户打印机编号
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MerchantPrinter merchantPrinter = merchantPrinterService.selectMerchantPrinterById(id);
        mmap.put("merchantPrinter", merchantPrinter);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户打印机编号
     */
    @RequiresPermissions("system:printer:edit")
    @Log(title = "商户打印机编号", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MerchantPrinter merchantPrinter)
    {
        return toAjax(merchantPrinterService.updateMerchantPrinter(merchantPrinter));
    }

    /**
     * 删除商户打印机编号
     */
    @RequiresPermissions("system:printer:remove")
    @Log(title = "商户打印机编号", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(merchantPrinterService.deleteMerchantPrinterByIds(ids));
    }

    /**
     * 查询商品
     *
     * @return
     */
    @GetMapping("/selectMerchant")
    public String selectGoods() {
        return prefix + "/merchant";
    }
}
