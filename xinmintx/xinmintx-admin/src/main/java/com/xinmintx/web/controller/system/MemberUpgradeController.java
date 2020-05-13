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
import com.xinmintx.system.domain.MemberUpgrade;
import com.xinmintx.system.service.IMemberUpgradeService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 会员卡升级金额或积分Controller
 * 
 * @author xinmintx
 * @date 2019-11-28
 */
@Controller
@RequestMapping("/system/upgrade")
public class MemberUpgradeController extends BaseController
{
    private String prefix = "system/upgrade";

    @Autowired
    private IMemberUpgradeService memberUpgradeService;

    @RequiresPermissions("system:upgrade:view")
    @GetMapping()
    public String upgrade()
    {
        return prefix + "/upgrade";
    }

    /**
     * 查询会员卡升级金额或积分列表
     */
    @RequiresPermissions("system:upgrade:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MemberUpgrade memberUpgrade)
    {
        startPage();
        List<MemberUpgrade> list = memberUpgradeService.selectMemberUpgradeList(memberUpgrade);
        return getDataTable(list);
    }

    /**
     * 导出会员卡升级金额或积分列表
     */
    @RequiresPermissions("system:upgrade:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberUpgrade memberUpgrade)
    {
        List<MemberUpgrade> list = memberUpgradeService.selectMemberUpgradeList(memberUpgrade);
        ExcelUtil<MemberUpgrade> util = new ExcelUtil<MemberUpgrade>(MemberUpgrade.class);
        return util.exportExcel(list, "upgrade");
    }

    /**
     * 新增会员卡升级金额或积分
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会员卡升级金额或积分
     */
    @RequiresPermissions("system:upgrade:add")
    @Log(title = "会员卡升级金额或积分", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MemberUpgrade memberUpgrade)
    {
        return toAjax(memberUpgradeService.insertMemberUpgrade(memberUpgrade));
    }

    /**
     * 修改会员卡升级金额或积分
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        MemberUpgrade memberUpgrade = memberUpgradeService.selectMemberUpgradeById(id);
        mmap.put("memberUpgrade", memberUpgrade);
        return prefix + "/edit";
    }

    /**
     * 修改保存会员卡升级金额或积分
     */
    @RequiresPermissions("system:upgrade:edit")
    @Log(title = "会员卡升级金额或积分", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MemberUpgrade memberUpgrade)
    {
        return toAjax(memberUpgradeService.updateMemberUpgrade(memberUpgrade));
    }

    /**
     * 删除会员卡升级金额或积分
     */
    @RequiresPermissions("system:upgrade:remove")
    @Log(title = "会员卡升级金额或积分", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(memberUpgradeService.deleteMemberUpgradeByIds(ids));
    }
}
