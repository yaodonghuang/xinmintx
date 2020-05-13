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
import com.xinmintx.system.domain.MemberTree;
import com.xinmintx.system.service.IMemberTreeService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author xinmintx
 * @date 2020-01-13
 */
@Controller
@RequestMapping("/system/tree")
public class MemberTreeController extends BaseController
{
    private String prefix = "system/tree";

    @Autowired
    private IMemberTreeService memberTreeService;

    @RequiresPermissions("system:tree:view")
    @GetMapping()
    public String tree()
    {
        return prefix + "/tree";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:tree:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MemberTree memberTree)
    {
        startPage();
        List<MemberTree> list = memberTreeService.selectMemberTreeList(memberTree);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:tree:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTree memberTree)
    {
        List<MemberTree> list = memberTreeService.selectMemberTreeList(memberTree);
        ExcelUtil<MemberTree> util = new ExcelUtil<MemberTree>(MemberTree.class);
        return util.exportExcel(list, "tree");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("system:tree:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MemberTree memberTree)
    {
        return toAjax(memberTreeService.insertMemberTree(memberTree));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{treeId}")
    public String edit(@PathVariable("treeId") Long treeId, ModelMap mmap)
    {
        MemberTree memberTree = memberTreeService.selectMemberTreeById(treeId);
        mmap.put("memberTree", memberTree);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:tree:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MemberTree memberTree)
    {
        return toAjax(memberTreeService.updateMemberTree(memberTree));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:tree:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(memberTreeService.deleteMemberTreeByIds(ids));
    }
}
