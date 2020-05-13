package com.xinmintx.web.controller.system;

import java.util.List;

import com.xinmintx.system.domain.GoodPtgoodBean;
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
import com.xinmintx.system.domain.GoodPtgood;
import com.xinmintx.system.service.IGoodPtgoodService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author xinmintx
 * @date 2019-12-16
 */
@Controller
@RequestMapping("/system/ptgood")
public class GoodPtgoodController extends BaseController {
    private String prefix = "system/ptgood";

    @Autowired
    private IGoodPtgoodService goodPtgoodService;

    @RequiresPermissions("system:ptgood:view")
    @GetMapping()
    public String ptgood() {
        return prefix + "/ptgood";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:ptgood:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GoodPtgood goodPtgood) {
        startPage();
        List<GoodPtgood> list = goodPtgoodService.selectGoodPtgoodList(goodPtgood);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:ptgood:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GoodPtgood goodPtgood) {
        List<GoodPtgood> list = goodPtgoodService.selectGoodPtgoodList(goodPtgood);
        ExcelUtil<GoodPtgood> util = new ExcelUtil<GoodPtgood>(GoodPtgood.class);
        return util.exportExcel(list, "ptgood");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("system:ptgood:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoodPtgoodBean goodPtgood) {
        int i = goodPtgoodService.insertGoodPtgood(goodPtgood);
        if (i == 0) {
            return AjaxResult.error("该拼团已存在");
        }
        return toAjax(i);
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{ptgoodsId}")
    public String edit(@PathVariable("ptgoodsId") Long ptgoodsId, ModelMap mmap) {
        GoodPtgood goodPtgood = goodPtgoodService.selectGoodPtgoodById(ptgoodsId);
        mmap.put("goodPtgood", goodPtgood);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:ptgood:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoodPtgood goodPtgood) {
        int i = goodPtgoodService.updateGoodPtgood(goodPtgood);
        if (i == 0) {
            return AjaxResult.error("该拼团已存在");
        }
        return toAjax(i);
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:ptgood:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(goodPtgoodService.deleteGoodPtgoodByIds(ids));
    }


    @GetMapping("/selectGoods")
    public String selectGoods() {
        return prefix + "/goods";
    }
}
