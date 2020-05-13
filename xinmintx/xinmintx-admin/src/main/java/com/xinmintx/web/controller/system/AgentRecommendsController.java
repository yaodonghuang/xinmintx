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
import com.xinmintx.system.domain.AgentRecommends;
import com.xinmintx.system.service.IAgentRecommendsService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 代理升级规则Controller
 * 
 * @author xinmintx
 * @date 2020-02-05
 */
@Controller
@RequestMapping("/system/recommends")
public class AgentRecommendsController extends BaseController
{
    private String prefix = "system/recommends";

    @Autowired
    private IAgentRecommendsService agentRecommendsService;

    @RequiresPermissions("system:recommends:view")
    @GetMapping()
    public String recommends()
    {
        return prefix + "/recommends";
    }

    /**
     * 查询代理升级规则列表
     */
    @RequiresPermissions("system:recommends:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AgentRecommends agentRecommends)
    {
        startPage();
        List<AgentRecommends> list = agentRecommendsService.selectAgentRecommendsList(agentRecommends);
        return getDataTable(list);
    }

    /**
     * 导出代理升级规则列表
     */
    @RequiresPermissions("system:recommends:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AgentRecommends agentRecommends)
    {
        List<AgentRecommends> list = agentRecommendsService.selectAgentRecommendsList(agentRecommends);
        ExcelUtil<AgentRecommends> util = new ExcelUtil<AgentRecommends>(AgentRecommends.class);
        return util.exportExcel(list, "recommends");
    }

    /**
     * 新增代理升级规则
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存代理升级规则
     */
    @RequiresPermissions("system:recommends:add")
    @Log(title = "代理升级规则", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AgentRecommends agentRecommends)
    {
        return toAjax(agentRecommendsService.insertAgentRecommends(agentRecommends));
    }

    /**
     * 修改代理升级规则
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AgentRecommends agentRecommends = agentRecommendsService.selectAgentRecommendsById(id);
        mmap.put("agentRecommends", agentRecommends);
        return prefix + "/edit";
    }

    /**
     * 修改保存代理升级规则
     */
    @RequiresPermissions("system:recommends:edit")
    @Log(title = "代理升级规则", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AgentRecommends agentRecommends)
    {
        return toAjax(agentRecommendsService.updateAgentRecommends(agentRecommends));
    }

    /**
     * 删除代理升级规则
     */
    @RequiresPermissions("system:recommends:remove")
    @Log(title = "代理升级规则", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(agentRecommendsService.deleteAgentRecommendsByIds(ids));
    }
}
