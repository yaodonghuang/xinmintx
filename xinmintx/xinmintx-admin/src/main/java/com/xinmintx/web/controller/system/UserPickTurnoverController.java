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
import com.xinmintx.system.domain.UserPickTurnover;
import com.xinmintx.system.service.IUserPickTurnoverService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 提货点营业额百分比设置Controller
 * 
 * @author xinmintx
 * @date 2020-03-19
 */
@Controller
@RequestMapping("/system/turnover")
public class UserPickTurnoverController extends BaseController
{
    private String prefix = "system/turnover";

    @Autowired
    private IUserPickTurnoverService userPickTurnoverService;

    @RequiresPermissions("system:turnover:view")
    @GetMapping()
    public String turnover()
    {
        return prefix + "/turnover";
    }

    /**
     * 查询提货点营业额百分比设置列表
     */
    @RequiresPermissions("system:turnover:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserPickTurnover userPickTurnover)
    {
        startPage();
        List<UserPickTurnover> list = userPickTurnoverService.selectUserPickTurnoverList(userPickTurnover);
        return getDataTable(list);
    }

    /**
     * 导出提货点营业额百分比设置列表
     */
    @RequiresPermissions("system:turnover:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserPickTurnover userPickTurnover)
    {
        List<UserPickTurnover> list = userPickTurnoverService.selectUserPickTurnoverList(userPickTurnover);
        ExcelUtil<UserPickTurnover> util = new ExcelUtil<UserPickTurnover>(UserPickTurnover.class);
        return util.exportExcel(list, "turnover");
    }

    /**
     * 新增提货点营业额百分比设置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存提货点营业额百分比设置
     */
    @RequiresPermissions("system:turnover:add")
    @Log(title = "提货点营业额百分比设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserPickTurnover userPickTurnover)
    {
        return toAjax(userPickTurnoverService.insertUserPickTurnover(userPickTurnover));
    }

    /**
     * 修改提货点营业额百分比设置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserPickTurnover userPickTurnover = userPickTurnoverService.selectUserPickTurnoverById(id);
        mmap.put("userPickTurnover", userPickTurnover);
        return prefix + "/edit";
    }

    /**
     * 修改保存提货点营业额百分比设置
     */
    @RequiresPermissions("system:turnover:edit")
    @Log(title = "提货点营业额百分比设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserPickTurnover userPickTurnover)
    {
        return toAjax(userPickTurnoverService.updateUserPickTurnover(userPickTurnover));
    }

    /**
     * 删除提货点营业额百分比设置
     */
    @RequiresPermissions("system:turnover:remove")
    @Log(title = "提货点营业额百分比设置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userPickTurnoverService.deleteUserPickTurnoverByIds(ids));
    }
}
