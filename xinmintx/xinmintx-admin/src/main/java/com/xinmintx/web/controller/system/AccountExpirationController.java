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
import com.xinmintx.system.domain.AccountExpiration;
import com.xinmintx.system.service.IAccountExpirationService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 任务到期时间Controller
 * 
 * @author xinmintx
 * @date 2019-11-23
 */
@Controller
@RequestMapping("/system/expiration")
public class AccountExpirationController extends BaseController
{
    private String prefix = "system/expiration";

    @Autowired
    private IAccountExpirationService accountExpirationService;

    @RequiresPermissions("system:expiration:view")
    @GetMapping()
    public String expiration()
    {
        return prefix + "/expiration";
    }

    /**
     * 查询任务到期时间列表
     */
    @RequiresPermissions("system:expiration:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccountExpiration accountExpiration)
    {
        startPage();
        List<AccountExpiration> list = accountExpirationService.selectAccountExpirationList(accountExpiration);
        return getDataTable(list);
    }

    /**
     * 导出任务到期时间列表
     */
    @RequiresPermissions("system:expiration:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AccountExpiration accountExpiration)
    {
        List<AccountExpiration> list = accountExpirationService.selectAccountExpirationList(accountExpiration);
        ExcelUtil<AccountExpiration> util = new ExcelUtil<AccountExpiration>(AccountExpiration.class);
        return util.exportExcel(list, "expiration");
    }

    /**
     * 新增任务到期时间
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存任务到期时间
     */
    @RequiresPermissions("system:expiration:add")
    @Log(title = "任务到期时间", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AccountExpiration accountExpiration)
    {
        return toAjax(accountExpirationService.insertAccountExpiration(accountExpiration));
    }

    /**
     * 修改任务到期时间
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AccountExpiration accountExpiration = accountExpirationService.selectAccountExpirationById(id);
        mmap.put("accountExpiration", accountExpiration);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务到期时间
     */
    @RequiresPermissions("system:expiration:edit")
    @Log(title = "任务到期时间", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AccountExpiration accountExpiration)
    {
        return toAjax(accountExpirationService.updateAccountExpiration(accountExpiration));
    }

    /**
     * 删除任务到期时间
     */
    @RequiresPermissions("system:expiration:remove")
    @Log(title = "任务到期时间", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(accountExpirationService.deleteAccountExpirationByIds(ids));
    }
}
