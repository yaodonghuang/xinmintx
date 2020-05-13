package com.xinmintx.web.controller.system;

import java.util.List;

import com.xinmintx.system.domain.Member;
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
import com.xinmintx.system.domain.GoodPanicBuy;
import com.xinmintx.system.service.IGoodPanicBuyService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 限时抢购Controller
 *
 * @author xinmintx
 * @date 2020-02-18
 */
@Controller
@RequestMapping("/system/buy")
public class GoodPanicBuyController extends BaseController {
    private String prefix = "system/buy";

    @Autowired
    private IGoodPanicBuyService goodPanicBuyService;

    @RequiresPermissions("system:buy:view")
    @GetMapping()
    public String buy() {
        return prefix + "/buy";
    }

    /**
     * 查询限时抢购列表
     */
    @RequiresPermissions("system:buy:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GoodPanicBuy goodPanicBuy) {
        startPage();
        List<GoodPanicBuy> list = goodPanicBuyService.selectGoodPanicBuyList(goodPanicBuy);
        return getDataTable(list);
    }

    /**
     * 导出限时抢购列表
     */
    @RequiresPermissions("system:buy:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GoodPanicBuy goodPanicBuy) {
        List<GoodPanicBuy> list = goodPanicBuyService.selectGoodPanicBuyList(goodPanicBuy);
        ExcelUtil<GoodPanicBuy> util = new ExcelUtil<GoodPanicBuy>(GoodPanicBuy.class);
        return util.exportExcel(list, "buy");
    }

    /**
     * 新增限时抢购
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存限时抢购
     */
    @RequiresPermissions("system:buy:add")
    @Log(title = "限时抢购", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoodPanicBuy goodPanicBuy) {
        return toAjax(goodPanicBuyService.insertGoodPanicBuy(goodPanicBuy));
    }

    /**
     * 修改限时抢购
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        GoodPanicBuy goodPanicBuy = goodPanicBuyService.selectGoodPanicBuyById(id);
        mmap.put("goodPanicBuy", goodPanicBuy);
        return prefix + "/edit";
    }

    /**
     * 修改保存限时抢购
     */
    @RequiresPermissions("system:buy:edit")
    @Log(title = "限时抢购", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoodPanicBuy goodPanicBuy) {
        return toAjax(goodPanicBuyService.updateGoodPanicBuy(goodPanicBuy));
    }

    /**
     * 删除限时抢购
     */
    @RequiresPermissions("system:buy:remove")
    @Log(title = "限时抢购", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(goodPanicBuyService.deleteGoodPanicBuyByIds(ids));
    }

    /**
     * 查询商品
     *
     * @return
     */
    @GetMapping("/selectGoods")
    public String selectGoods() {
        return prefix + "/goods";
    }

    /**
     * 会员
     *
     * @return
     */
    @GetMapping("/selectMembers")
    public String selectMembers() {
        return prefix + "/member";
    }

    /**
     * 检查该商品是否已经添加过了
     *
     * @param goodsId 商品id
     * @return boolean
     */
    @PostMapping("/checkGoods/{goodsId}")
    @ResponseBody
    public boolean checkGoods(@PathVariable("goodsId") int goodsId) {
        return goodPanicBuyService.checkGoods(goodsId);
    }


    @PostMapping("/member")
    @ResponseBody
    public TableDataInfo member(Member member)
    {
        startPage();
        List<Member> list = goodPanicBuyService.selectMemberList(member);
        return getDataTable(list);
    }
}
