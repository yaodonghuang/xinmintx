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
import com.xinmintx.system.domain.GoodsPriceType;
import com.xinmintx.system.service.IGoodsPriceTypeService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 用户商品价格分类Controller
 * 
 * @author xinmintx
 * @date 2020-02-13
 */
@Controller
@RequestMapping("/system/goodsType")
public class GoodsPriceTypeController extends BaseController
{
    private String prefix = "system/priceType";

    @Autowired
    private IGoodsPriceTypeService goodsPriceTypeService;

    @RequiresPermissions("system:goodsType:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    /**
     * 查询用户商品价格分类列表
     */
    @RequiresPermissions("system:goodsType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GoodsPriceType goodsPriceType)
    {
        startPage();
        List<GoodsPriceType> list = goodsPriceTypeService.selectGoodsPriceTypeList(goodsPriceType);
        return getDataTable(list);
    }

    /**
     * 导出用户商品价格分类列表
     */
    @RequiresPermissions("system:goodsType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GoodsPriceType goodsPriceType)
    {
        List<GoodsPriceType> list = goodsPriceTypeService.selectGoodsPriceTypeList(goodsPriceType);
        ExcelUtil<GoodsPriceType> util = new ExcelUtil<GoodsPriceType>(GoodsPriceType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 新增用户商品价格分类
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户商品价格分类
     */
    @RequiresPermissions("system:goodsType:add")
    @Log(title = "用户商品价格分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoodsPriceType goodsPriceType)
    {
        return toAjax(goodsPriceTypeService.insertGoodsPriceType(goodsPriceType));
    }

    /**
     * 修改用户商品价格分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GoodsPriceType goodsPriceType = goodsPriceTypeService.selectGoodsPriceTypeById(id);
        mmap.put("goodsPriceType", goodsPriceType);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户商品价格分类
     */
    @RequiresPermissions("system:goodsType:edit")
    @Log(title = "用户商品价格分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoodsPriceType goodsPriceType)
    {
        return toAjax(goodsPriceTypeService.updateGoodsPriceType(goodsPriceType));
    }

    /**
     * 删除用户商品价格分类
     */
    @RequiresPermissions("system:goodsType:remove")
    @Log(title = "用户商品价格分类", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(goodsPriceTypeService.deleteGoodsPriceTypeByIds(ids));
    }
}
