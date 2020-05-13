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
import com.xinmintx.system.domain.MemberBonus;
import com.xinmintx.system.service.IMemberBonusService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 会员奖金池Controller
 * 
 * @author xinmintx
 * @date 2020-03-12
 */
@Controller
@RequestMapping("/system/bonus")
public class MemberBonusController extends BaseController
{
    private String prefix = "system/bonus";

    @Autowired
    private IMemberBonusService memberBonusService;

    @RequiresPermissions("system:bonus:view")
    @GetMapping()
    public String bonus()
    {
        return prefix + "/bonus";
    }

    /**
     * 查询会员奖金池列表
     */
    @RequiresPermissions("system:bonus:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MemberBonus memberBonus)
    {
        startPage();
        List<MemberBonus> list = memberBonusService.selectMemberBonusList(memberBonus);
        return getDataTable(list);
    }

    /**
     * 导出会员奖金池列表
     */
    @RequiresPermissions("system:bonus:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberBonus memberBonus)
    {
        List<MemberBonus> list = memberBonusService.selectMemberBonusList(memberBonus);
        ExcelUtil<MemberBonus> util = new ExcelUtil<MemberBonus>(MemberBonus.class);
        return util.exportExcel(list, "bonus");
    }

    /**
     * 新增会员奖金池
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会员奖金池
     */
    @RequiresPermissions("system:bonus:add")
    @Log(title = "会员奖金池", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MemberBonus memberBonus)
    {
        return toAjaxRed(memberBonusService.insertMemberBonus(memberBonus));
    }

    /**
     * 修改会员奖金池
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MemberBonus memberBonus = memberBonusService.selectMemberBonusById(id);
        mmap.put("memberBonus", memberBonus);
        return prefix + "/edit";
    }

    /**
     * 修改保存会员奖金池
     */
    @RequiresPermissions("system:bonus:edit")
    @Log(title = "会员奖金池", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MemberBonus memberBonus)
    {
        return toAjaxRed(memberBonusService.updateMemberBonus(memberBonus));
    }

    /**
     * 删除会员奖金池
     */
    @RequiresPermissions("system:bonus:remove")
    @Log(title = "会员奖金池", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(memberBonusService.deleteMemberBonusByIds(ids));
    }
}
