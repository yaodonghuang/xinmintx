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
import com.xinmintx.system.domain.Community;
import com.xinmintx.system.service.ICommunityService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 社区Controller
 * 
 * @author xinmintx
 * @date 2020-03-19
 */
@Controller
@RequestMapping("/system/community")
public class CommunityController extends BaseController
{
    private String prefix = "system/community";

    @Autowired
    private ICommunityService communityService;

    @RequiresPermissions("system:community:view")
    @GetMapping()
    public String community()
    {
        return prefix + "/community";
    }

    /**
     * 查询社区列表
     */
    @RequiresPermissions("system:community:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Community community)
    {
        startPage();
        List<Community> list = communityService.selectCommunityList(community);
        return getDataTable(list);
    }

    /**
     * 导出社区列表
     */
    @RequiresPermissions("system:community:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Community community)
    {
        List<Community> list = communityService.selectCommunityList(community);
        ExcelUtil<Community> util = new ExcelUtil<Community>(Community.class);
        return util.exportExcel(list, "community");
    }

    /**
     * 新增社区
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存社区
     */
    @RequiresPermissions("system:community:add")
    @Log(title = "社区", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Community community)
    {
        return toAjax(communityService.insertCommunity(community));
    }

    /**
     * 修改社区
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Community community = communityService.selectCommunityById(id);
        mmap.put("community", community);
        return prefix + "/edit";
    }

    /**
     * 修改保存社区
     */
    @RequiresPermissions("system:community:edit")
    @Log(title = "社区", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Community community)
    {
        return toAjax(communityService.updateCommunity(community));
    }

    /**
     * 删除社区
     */
    @RequiresPermissions("system:community:remove")
    @Log(title = "社区", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(communityService.deleteCommunityByIds(ids));
    }
}
