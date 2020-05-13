package com.xinmintx.web.controller.system;

import java.util.List;

import com.xinmintx.system.domain.Goods;
import com.xinmintx.system.domain.GoodsExt;
import com.xinmintx.system.domain.MemberTree;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xinmintx.common.annotation.Log;
import com.xinmintx.common.enums.BusinessType;
import com.xinmintx.system.domain.Member;
import com.xinmintx.system.service.IMemberService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 会员卡Controller
 * 
 * @author xinmintx
 * @date 2019-11-20
 */
@Controller
@Transactional
@RequestMapping("/system/member")
public class MemberController extends BaseController
{
    private String prefix = "system/member";

    @Autowired
    private IMemberService memberService;

    @RequiresPermissions("system:member:view")
    @GetMapping()
    public String member()
    {
        return prefix + "/member";
    }

    /**
     * 查询会员卡列表
     */
    @RequiresPermissions("system:member:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Member member)
    {
        startPage();
        List<Member> list = memberService.selectMemberList(member);
        return getDataTable(list);
    }

    /**
     * 导出会员卡列表
     */
    @RequiresPermissions("system:member:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Member member)
    {
        List<Member> list = memberService.selectMemberList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        return util.exportExcel(list, "member");
    }

    /**
     * 新增会员卡
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会员卡
     */
    @RequiresPermissions("system:member:add")
    @Log(title = "会员卡", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Member member)
    {
        return toAjax(memberService.insertMember(member));
    }

    /**
     * 修改会员卡
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Member member = memberService.selectMemberById(id);
        mmap.put("member", member);
        return prefix + "/edit";
    }

    /**
     * 修改保存会员卡
     */
    @RequiresPermissions("system:member:edit")
    @Log(title = "会员卡", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Member member)
    {
        return toAjax(memberService.updateMember(member));
    }

    /**
     * 删除会员卡
     */
    @RequiresPermissions("system:member:remove")
    @Log(title = "会员卡", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(memberService.deleteMemberByIds(ids));
    }

    /**
     * 查询矩阵编号
     * @return
     */
@PostMapping("/treeCode")
@ResponseBody
public List<MemberTree> list()
{
    List<MemberTree> list = memberService.selectTreeCode();
    return list;
}
    /**
     * 查询矩阵编号跳转
     * @return
     */
    @GetMapping("/selectTreeCodes")
    public String selectTreeCodes(ModelMap map)
    {
        return prefix + "/tree";
    }

}
