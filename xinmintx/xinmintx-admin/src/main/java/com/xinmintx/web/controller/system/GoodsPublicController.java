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
import com.xinmintx.system.domain.GoodsPublic;
import com.xinmintx.system.service.IGoodsPublicService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 商品详情公共部分配置Controller
 * 
 * @author xinmintx
 * @date 2020-02-24
 */
@Controller
@RequestMapping("/system/public")
public class GoodsPublicController extends BaseController
{
    private String prefix = "system/public";

    @Autowired
    private IGoodsPublicService goodsPublicService;

    @RequiresPermissions("system:public:view")
    @GetMapping()
    public String goodsPublic()
    {
        return prefix + "/public";
    }

    /**
     * 查询商品详情公共部分配置列表
     */
    @RequiresPermissions("system:public:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GoodsPublic goodsPublic)
    {
        startPage();
        List<GoodsPublic> list = goodsPublicService.selectGoodsPublicList(goodsPublic);
        return getDataTable(list);
    }

    /**
     * 导出商品详情公共部分配置列表
     */
    @RequiresPermissions("system:public:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GoodsPublic goodsPublic)
    {
        List<GoodsPublic> list = goodsPublicService.selectGoodsPublicList(goodsPublic);
        ExcelUtil<GoodsPublic> util = new ExcelUtil<GoodsPublic>(GoodsPublic.class);
        return util.exportExcel(list, "public");
    }

    /**
     * 新增商品详情公共部分配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商品详情公共部分配置
     */
    @RequiresPermissions("system:public:add")
    @Log(title = "商品详情公共部分配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoodsPublic goodsPublic)
    {
        return toAjax(goodsPublicService.insertGoodsPublic(goodsPublic));
    }

    /**
     * 修改商品详情公共部分配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GoodsPublic goodsPublic = goodsPublicService.selectGoodsPublicById(id);
        mmap.put("goodsPublic", goodsPublic);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品详情公共部分配置
     */
    @RequiresPermissions("system:public:edit")
    @Log(title = "商品详情公共部分配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoodsPublic goodsPublic)
    {
        return toAjax(goodsPublicService.updateGoodsPublic(goodsPublic));
    }

    /**
     * 删除商品详情公共部分配置
     */
    @RequiresPermissions("system:public:remove")
    @Log(title = "商品详情公共部分配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(goodsPublicService.deleteGoodsPublicByIds(ids));
    }
}
