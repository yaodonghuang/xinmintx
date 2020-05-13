package com.xinmintx.web.controller.system;

import com.xinmintx.common.annotation.Log;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.core.page.TableDataInfo;
import com.xinmintx.common.enums.BusinessType;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.system.domain.MemberCardOrder;
import com.xinmintx.system.domain.MemberCardOrderDetail;
import com.xinmintx.system.service.IMemberCardOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员卡订单Controller
 *
 * @author xinmintx
 * @date 2020-02-15
 */
@Controller
@RequestMapping("/system/memberCardOrder")
public class MemberCardOrderController extends BaseController {
    private String prefix = "system/memberCardOrder";

    @Autowired
    private IMemberCardOrderService memberCardOrderService;

    @RequiresPermissions("system:memberCardOrder:view")
    @GetMapping()
    public String order() {
        return prefix + "/order";
    }

    /**
     * 查询会员卡订单列表
     */
    @RequiresPermissions("system:memberCardOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MemberCardOrder memberCardOrder) {
        startPage();
        List<MemberCardOrder> list = memberCardOrderService.selectMemberCardOrderList(memberCardOrder);
        return getDataTable(list);
    }

    /**
     * 导出会员卡订单列表
     */
    @RequiresPermissions("system:memberCardOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberCardOrder memberCardOrder) {
        List<MemberCardOrder> list = memberCardOrderService.selectMemberCardOrderList(memberCardOrder);
        ExcelUtil<MemberCardOrder> util = new ExcelUtil<MemberCardOrder>(MemberCardOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增会员卡订单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存会员卡订单
     */
    @RequiresPermissions("system:memberCardOrder:add")
    @Log(title = "会员卡订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MemberCardOrder memberCardOrder) {
        return toAjax(memberCardOrderService.insertMemberCardOrder(memberCardOrder));
    }

    /**
     * 修改会员卡订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        MemberCardOrder memberCardOrder = memberCardOrderService.selectMemberCardOrderById(id);
        mmap.put("memberCardOrder", memberCardOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存会员卡订单
     */
    @RequiresPermissions("system:memberCardOrder:edit")
    @Log(title = "会员卡订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MemberCardOrder memberCardOrder) {
        return toAjax(memberCardOrderService.updateMemberCardOrder(memberCardOrder));
    }

    /**
     * 删除会员卡订单
     */
    @RequiresPermissions("system:memberCardOrder:remove")
    @Log(title = "会员卡订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(memberCardOrderService.deleteMemberCardOrderByIds(ids));
    }

    /**
     * 获取订单详情
     */
    @RequiresPermissions("system:memberCardOrder:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap) {
        List<MemberCardOrderDetail> memberCardOrderDetails = memberCardOrderService.selectMemberCardOrderDetail(id);
        mmap.put("goodsOrderDetailList", memberCardOrderDetails);
        return prefix + "/detail";
    }

    /**
     * 添加或修改订单号页面跳转
     *
     */
    @RequestMapping("/addCourierView/{id}")
    public String jumpParameter(@PathVariable("id") String id,ModelMap map){
        map.put("id",id);
        return prefix + "/addCourier";
    }

    /**
     * 添加或修改快递单号
     * @param id id
     * @param courierNumber  快递号
     * @return
     */
    @RequestMapping("/addCourier")
    @ResponseBody
    public AjaxResult addParameter(long id,String courierNumber){
        return toAjax(memberCardOrderService.insertCourierNumber(id, courierNumber));
    }
}
