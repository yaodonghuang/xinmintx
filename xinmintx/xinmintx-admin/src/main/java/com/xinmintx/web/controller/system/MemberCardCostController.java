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
import com.xinmintx.system.domain.MemberCardCost;
import com.xinmintx.system.service.IMemberCardCostService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 会员卡费用Controller
 * 
 * @author xinmintx
 * @date 2020-02-08
 */
@Controller
@RequestMapping("/system/cost")
public class MemberCardCostController extends BaseController
{
    private String prefix = "system/cost";

    @Autowired
    private IMemberCardCostService memberCardCostService;

    @RequiresPermissions("system:cost:view")
    @GetMapping()
    public String cost()
    {
        return prefix + "/cost";
    }

    /**
     * 查询会员卡费用列表
     */
    @RequiresPermissions("system:cost:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MemberCardCost memberCardCost)
    {
        startPage();
        List<MemberCardCost> list = memberCardCostService.selectMemberCardCostList(memberCardCost);
        return getDataTable(list);
    }

    /**
     * 导出会员卡费用列表
     */
    @RequiresPermissions("system:cost:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberCardCost memberCardCost)
    {
        List<MemberCardCost> list = memberCardCostService.selectMemberCardCostList(memberCardCost);
        ExcelUtil<MemberCardCost> util = new ExcelUtil<MemberCardCost>(MemberCardCost.class);
        return util.exportExcel(list, "cost");
    }

    /**
     * 新增会员卡费用
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会员卡费用
     */
    @RequiresPermissions("system:cost:add")
    @Log(title = "会员卡费用", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MemberCardCost memberCardCost)
    {
        return toAjax(memberCardCostService.insertMemberCardCost(memberCardCost));
    }

    /**
     * 修改会员卡费用
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MemberCardCost memberCardCost = memberCardCostService.selectMemberCardCostById(id);
        mmap.put("memberCardCost", memberCardCost);
        return prefix + "/edit";
    }

    /**
     * 修改保存会员卡费用
     */
    @RequiresPermissions("system:cost:edit")
    @Log(title = "会员卡费用", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MemberCardCost memberCardCost)
    {
        return toAjax(memberCardCostService.updateMemberCardCost(memberCardCost));
    }

    /**
     * 删除会员卡费用
     */
    @RequiresPermissions("system:cost:remove")
    @Log(title = "会员卡费用", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(memberCardCostService.deleteMemberCardCostByIds(ids));
    }
}
