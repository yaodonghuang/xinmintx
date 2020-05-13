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
import com.xinmintx.system.domain.RedEnvelopeActivities;
import com.xinmintx.system.service.IRedEnvelopeActivitiesService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 活动Controller
 * 
 * @author xinmintx
 * @date 2020-03-10
 */
@Controller
@RequestMapping("/system/activities")
public class RedEnvelopeActivitiesController extends BaseController
{
    private String prefix = "system/activities";

    @Autowired
    private IRedEnvelopeActivitiesService redEnvelopeActivitiesService;

    @RequiresPermissions("system:activities:view")
    @GetMapping()
    public String activities()
    {
        return prefix + "/activities";
    }

    /**
     * 查询活动列表
     */
    @RequiresPermissions("system:activities:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RedEnvelopeActivities redEnvelopeActivities)
    {
        startPage();
        List<RedEnvelopeActivities> list = redEnvelopeActivitiesService.selectRedEnvelopeActivitiesList(redEnvelopeActivities);
        return getDataTable(list);
    }

    /**
     * 导出活动列表
     */
    @RequiresPermissions("system:activities:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RedEnvelopeActivities redEnvelopeActivities)
    {
        List<RedEnvelopeActivities> list = redEnvelopeActivitiesService.selectRedEnvelopeActivitiesList(redEnvelopeActivities);
        ExcelUtil<RedEnvelopeActivities> util = new ExcelUtil<RedEnvelopeActivities>(RedEnvelopeActivities.class);
        return util.exportExcel(list, "activities");
    }

    /**
     * 新增活动
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存活动
     */
    @RequiresPermissions("system:activities:add")
    @Log(title = "活动", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RedEnvelopeActivities redEnvelopeActivities)
    {
        return toAjaxRed(redEnvelopeActivitiesService.insertRedEnvelopeActivities(redEnvelopeActivities));
    }

    /**
     * 修改活动
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        RedEnvelopeActivities redEnvelopeActivities = redEnvelopeActivitiesService.selectRedEnvelopeActivitiesById(id);
        mmap.put("redEnvelopeActivities", redEnvelopeActivities);
        return prefix + "/edit";
    }

    /**
     * 修改保存活动
     */
    @RequiresPermissions("system:activities:edit")
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RedEnvelopeActivities redEnvelopeActivities)
    {
        return toAjaxRed(redEnvelopeActivitiesService.updateRedEnvelopeActivities(redEnvelopeActivities));
    }

    /**
     * 删除活动
     */
    @RequiresPermissions("system:activities:remove")
    @Log(title = "活动", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(redEnvelopeActivitiesService.deleteRedEnvelopeActivitiesByIds(ids));
    }
}
