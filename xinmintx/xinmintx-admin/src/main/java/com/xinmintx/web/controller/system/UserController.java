package com.xinmintx.web.controller.system;

import com.xinmintx.common.annotation.Log;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.core.page.TableDataInfo;
import com.xinmintx.common.enums.BusinessType;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.system.domain.User;
import com.xinmintx.system.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户信息Controller
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
/*@Controller
@RequestMapping("/system/user")*/
public class UserController extends BaseController
{
    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询用户信息列表
     */
    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(User user)
    {
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
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
    @RequiresPermissions("system:user:add")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(User user)
    {
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        User user = userService.selectUserById(id);
        mmap.put("user", user);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户信息
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(User user)
    {
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户信息
     */
    @RequiresPermissions("system:user:remove")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userService.deleteUserByIds(ids));
    }
}
