package com.xinmintx.web.controller.system;

import java.util.List;

import com.xinmintx.system.domain.UserExt;
import com.xinmintx.system.domain.UserInformation;
import com.xinmintx.system.service.UserInformationService;
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
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * @ClassName:用户信息Controller
 * @author:chf
 * @Date:2020/1/14：15:50
 * @developerKits： win 10     jdk1.8
 */

@Controller
@RequestMapping("/system/userInformation")
public class UserInformationController extends BaseController{
    private String prefix = "system/userInformation";

    @Autowired
    private UserInformationService userService;

    @RequiresPermissions("system:userInformation:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询用户信息列表
     */
    @RequiresPermissions("system:userInformation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserInformation user)
    {
        startPage();
        List<UserInformation> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @RequiresPermissions("system:userInformation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserInformation user)
    {
        List<UserInformation> list = userService.selectUserList(user);
        ExcelUtil<UserInformation> util = new ExcelUtil<UserInformation>(UserInformation.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增用户信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户信息
     */
    @RequiresPermissions("system:userInformation:add")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserInformation user)
    {
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        List<UserExt> users = userService.selectById(id);
        mmap.put("users", users);
        return prefix + "/edit";
    }

    /**
     * 修改用户信息
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        mmap.put("id", id);
        return prefix + "/detail";
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/details")
    @ResponseBody
    public TableDataInfo details(UserExt user)
    {
        startPage();
        List<UserExt> users = userService.selectUserById(user);
        return getDataTable(users);
    }

    /**
     * 修改保存用户信息
     */
    @RequiresPermissions("system:userInformation:edit")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserInformation user)
    {
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户信息
     */
    @RequiresPermissions("system:userInformation:remove")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userService.deleteUserByIds(ids));
    }
}
