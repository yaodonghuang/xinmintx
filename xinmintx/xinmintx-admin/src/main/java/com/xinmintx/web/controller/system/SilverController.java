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
import com.xinmintx.system.service.ISilverService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 银卡信息Controller
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
@Controller
@RequestMapping("/system/silver")
public class SilverController extends BaseController
{
    private String prefix = "system/silver";

    @Autowired
    private ISilverService silverService;

    @RequiresPermissions("system:silver:view")
    @GetMapping()
    public String silver()
    {
        return prefix + "/silver";
    }

    /**
     * 查询银卡信息列表
     */
    @RequiresPermissions("system:silver:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Member member)
    {
        startPage();
        List<Member> list = silverService.selectSilverList(member);
        return getDataTable(list);
    }

    /**
     * 导出银卡信息列表
     */
    @RequiresPermissions("system:silver:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Member member)
    {
        List<Member> list = silverService.selectSilverList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        return util.exportExcel(list, "silver");
    }

    /**
     * 新增银卡信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存银卡信息
     */
    @RequiresPermissions("system:silver:add")
    @Log(title = "银卡信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Member member)
    {
        int i = silverService.insertSilver(member);
        if(i > 0){
            return AjaxResult.success("成功");
        }else{
            return AjaxResult.error("信息已存在");
        }
    }

    /**
     * 修改银卡信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Member silver = silverService.selectSilverById(id);
        mmap.put("silver",silver);
        return prefix + "/edit";
    }

    /**
     * 修改保存银卡信息
     */
    @RequiresPermissions("system:silver:edit")
    @Log(title = "银卡信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Member member)
    {
        return toAjax(silverService.updateSilver(member));
    }

    /**
     * 删除银卡信息
     */
    @RequiresPermissions("system:silver:remove")
    @Log(title = "银卡信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(silverService.deleteSilverByIds(ids));
    }
}
