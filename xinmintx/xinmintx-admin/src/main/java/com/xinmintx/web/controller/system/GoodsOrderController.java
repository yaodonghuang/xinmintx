package com.xinmintx.web.controller.system;

import java.util.List;

import com.xinmintx.system.domain.CheckInUser;
import com.xinmintx.system.domain.GoodsOrderDetail;
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
import com.xinmintx.system.domain.GoodsOrder;
import com.xinmintx.system.service.IGoodsOrderService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 商品订单Controller
 * 
 * @author xinmintx
 * @date 2019-12-13
 */
@Controller
@RequestMapping("/system/order")
public class GoodsOrderController extends BaseController
{
    private String prefix = "system/order";

    @Autowired
    private IGoodsOrderService goodsOrderService;

    @RequiresPermissions("system:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询商品订单列表
     */
    @RequiresPermissions("system:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GoodsOrder goodsOrder)
    {
        startPage();
        List<GoodsOrder> list = goodsOrderService.selectGoodsOrderList(goodsOrder);
        return getDataTable(list);
    }

    /**
     * 导出商品订单列表
     */
    @RequiresPermissions("system:order:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GoodsOrder goodsOrder)
    {
        List<GoodsOrder> list = goodsOrderService.selectGoodsOrderList(goodsOrder);
        ExcelUtil<GoodsOrder> util = new ExcelUtil<GoodsOrder>(GoodsOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增商品订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商品订单
     */
    @RequiresPermissions("system:order:add")
    @Log(title = "商品订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoodsOrder goodsOrder)
    {
        return toAjax(goodsOrderService.insertGoodsOrder(goodsOrder));
    }

    /**
     * 修改商品订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GoodsOrder goodsOrder = goodsOrderService.selectGoodsOrderById(id);
        mmap.put("goodsOrder", goodsOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品订单
     */
    @RequiresPermissions("system:order:edit")
    @Log(title = "商品订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoodsOrder goodsOrder)
    {
        return toAjax(goodsOrderService.updateGoodsOrder(goodsOrder));
    }

    /**
     * 删除商品订单
     */
    @RequiresPermissions("system:order:remove")
    @Log(title = "商品订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(goodsOrderService.deleteGoodsOrderByIds(ids));
    }

    /**
     * 获取订单详情
     */
    @RequiresPermissions("system:order:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap)
    {
        List<GoodsOrderDetail> goodsOrderDetails = goodsOrderService.selectGoodsDetail(id);
        mmap.put("goodsOrderDetailList", goodsOrderDetails);
        return prefix + "/detail";
    }
}
