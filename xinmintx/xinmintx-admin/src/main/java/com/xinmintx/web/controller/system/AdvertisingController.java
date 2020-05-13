package com.xinmintx.web.controller.system;

import java.util.List;

import com.xinmintx.system.domain.GoodPanicBuy;
import com.xinmintx.system.domain.Goods;
import com.xinmintx.system.domain.GoodsType;
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
import com.xinmintx.system.domain.Advertising;
import com.xinmintx.system.service.IAdvertisingService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 广告Controller
 *
 * @author xinmintx
 * @date 2019-12-16
 */
@Controller
@RequestMapping("/system/advertising")
public class AdvertisingController extends BaseController {
    private String prefix = "system/advertising";

    @Autowired
    private IAdvertisingService advertisingService;

    @RequiresPermissions("system:advertising:view")
    @GetMapping()
    public String advertising() {
        return prefix + "/advertising";
    }

    /**
     * 查询广告列表
     */
    @RequiresPermissions("system:advertising:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Advertising advertising) {
        startPage();
        List<Advertising> list = advertisingService.selectAdvertisingList(advertising);
        return getDataTable(list);
    }

    /**
     * 导出广告列表
     */
    @RequiresPermissions("system:advertising:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Advertising advertising) {
        List<Advertising> list = advertisingService.selectAdvertisingList(advertising);
        ExcelUtil<Advertising> util = new ExcelUtil<Advertising>(Advertising.class);
        return util.exportExcel(list, "advertising");
    }

    /**
     * 新增广告
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        List<GoodsType> goodsTypes = advertisingService.selectTypes();
        modelMap.put("goodsTypes", goodsTypes);
        return prefix + "/add";
    }

    /**
     * 新增保存广告
     */
    @RequiresPermissions("system:advertising:add")
    @Log(title = "广告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Advertising advertising) {
        return toAjax(advertisingService.insertAdvertising(advertising));
    }

    /**
     * 修改广告
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Advertising advertising = advertisingService.selectAdvertisingById(id);
        int i = advertising.getLinkType().intValue();
        if (i == 2 || i == 3) {
            //查询商品
            Goods goods = advertisingService.selectGood(advertising.getRelateId());
            mmap.put("linkTypeName", goods.getGoodsListName());
        } else if (i == 7) {
            GoodPanicBuy goodPanicBuy = advertisingService.selectGoodPanicBuy(advertising.getRelateId());
            mmap.put("linkTypeName",goodPanicBuy.getGoodsName());
        }
        List<GoodsType> goodsTypes = advertisingService.selectTypes();
        mmap.put("goodsTypes", goodsTypes);
        mmap.put("advertising", advertising);
        return prefix + "/edit";
    }

    /**
     * 修改保存广告
     */
    @RequiresPermissions("system:advertising:edit")
    @Log(title = "广告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Advertising advertising) {
        return toAjax(advertisingService.updateAdvertising(advertising));
    }

    /**
     * 删除广告
     */
    @RequiresPermissions("system:advertising:remove")
    @Log(title = "广告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(advertisingService.deleteAdvertisingByIds(ids));
    }

    @GetMapping("/selectGoodPanicBuy")
    public String GoodPanicBuy()
    {
        return prefix + "/buy";
    }
}
