package com.xinmintx.web.controller.system;

import java.util.List;

import com.xinmintx.system.domain.BaseFeatredFirst;
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
import com.xinmintx.system.domain.GoodsFeaturedFirst;
import com.xinmintx.system.service.IGoodsFeaturedFirstService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 首页推荐位置商品Controller
 * 
 * @author xinmintx
 * @date 2019-12-16
 */
@Controller
@RequestMapping("/system/first")
public class GoodsFeaturedFirstController extends BaseController
{
    private String prefix = "system/first";

    @Autowired
    private IGoodsFeaturedFirstService goodsFeaturedFirstService;

    @RequiresPermissions("system:first:view")
    @GetMapping()
    public String first()
    {
        return prefix + "/first";
    }

    /**
     * 查询首页推荐位置商品列表
     */
    @RequiresPermissions("system:first:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BaseFeatredFirst baseFeatredFirst)
    {
        startPage();
        List<BaseFeatredFirst> list = goodsFeaturedFirstService.selectGoodsFeaturedFirstList(baseFeatredFirst);
        return getDataTable(list);
    }

    /**
     * 导出首页推荐位置商品列表
     */
    @RequiresPermissions("system:first:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BaseFeatredFirst baseFeatredFirst)
    {
        List<BaseFeatredFirst> list = goodsFeaturedFirstService.selectGoodsFeaturedFirstList(baseFeatredFirst);
        ExcelUtil<BaseFeatredFirst> util = new ExcelUtil<BaseFeatredFirst>(BaseFeatredFirst.class);
        return util.exportExcel(list, "first");
    }

    /**
     * 新增首页推荐位置商品
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存首页推荐位置商品
     */
    @RequiresPermissions("system:first:add")
    @Log(title = "首页推荐位置商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoodsFeaturedFirst goodsFeaturedFirst)
    {
        return toAjax(goodsFeaturedFirstService.insertGoodsFeaturedFirst(goodsFeaturedFirst));
    }

    /**
     * 修改首页推荐位置商品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BaseFeatredFirst baseFeatredFirst = goodsFeaturedFirstService.selectGoodsFeaturedFirstById(id);
        mmap.put("baseFeatredFirst", baseFeatredFirst);
        return prefix + "/edit";
    }

    /**
     * 修改保存首页推荐位置商品
     */
    @RequiresPermissions("system:first:edit")
    @Log(title = "首页推荐位置商品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoodsFeaturedFirst goodsFeaturedFirst)
    {
        return toAjax(goodsFeaturedFirstService.updateGoodsFeaturedFirst(goodsFeaturedFirst));
    }

    /**
     * 删除首页推荐位置商品
     */
    @RequiresPermissions("system:first:remove")
    @Log(title = "首页推荐位置商品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(goodsFeaturedFirstService.deleteGoodsFeaturedFirstByIds(ids));
    }

    @GetMapping("/selectGoods")
    public String selectGoods()
    {
        return prefix + "/goods";
    }
}
