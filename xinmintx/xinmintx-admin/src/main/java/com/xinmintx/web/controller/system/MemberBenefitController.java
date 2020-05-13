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
import com.xinmintx.system.domain.MemberBenefit;
import com.xinmintx.system.service.IMemberBenefitService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 消费分润Controller
 * 
 * @author xinmintx
 * @date 2020-01-07
 */
@Controller
@RequestMapping("/system/benefit")
public class MemberBenefitController extends BaseController
{
    private String prefix = "system/benefit";

    @Autowired
    private IMemberBenefitService memberBenefitService;

    @RequiresPermissions("system:benefit:view")
    @GetMapping()
    public String benefit()
    {
        return prefix + "/benefit";
    }

    /**
     * 查询消费分润列表
     */
    @RequiresPermissions("system:benefit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MemberBenefit memberBenefit)
    {
        startPage();
        List<MemberBenefit> list = memberBenefitService.selectMemberBenefitList(memberBenefit);
        return getDataTable(list);
    }

    /**
     * 导出消费分润列表
     */
    @RequiresPermissions("system:benefit:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberBenefit memberBenefit)
    {
        List<MemberBenefit> list = memberBenefitService.selectMemberBenefitList(memberBenefit);
        ExcelUtil<MemberBenefit> util = new ExcelUtil<MemberBenefit>(MemberBenefit.class);
        return util.exportExcel(list, "benefit");
    }

    /**
     * 新增消费分润
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存消费分润
     */
    @RequiresPermissions("system:benefit:add")
    @Log(title = "消费分润", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MemberBenefit memberBenefit)
    {
        return toAjax(memberBenefitService.insertMemberBenefit(memberBenefit));
    }

    /**
     * 修改消费分润
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MemberBenefit memberBenefit = memberBenefitService.selectMemberBenefitById(id);
        mmap.put("memberBenefit", memberBenefit);
        return prefix + "/edit";
    }

    /**
     * 修改保存消费分润
     */
    @RequiresPermissions("system:benefit:edit")
    @Log(title = "消费分润", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MemberBenefit memberBenefit)
    {
        return toAjax(memberBenefitService.updateMemberBenefit(memberBenefit));
    }

    /**
     * 删除消费分润
     */
    @RequiresPermissions("system:benefit:remove")
    @Log(title = "消费分润", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(memberBenefitService.deleteMemberBenefitByIds(ids));
    }
}
