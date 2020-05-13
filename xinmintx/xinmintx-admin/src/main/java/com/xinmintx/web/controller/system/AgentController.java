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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代理管理Controller
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
@Controller
@RequestMapping("/system/agent")
public class AgentController extends BaseController
{
    private String prefix = "system/agent";

    @Autowired
    private IUserService userService;

    @RequiresPermissions("system:agent:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/agent";
    }

    /**
     * 查询代理信息列表
     */
    @RequiresPermissions("system:agent:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user)
    {
        startPage();
        user.setUserRole(4);
        List<User> list = userService.selectUserList(user);
        user.setUserRole(5);
        list.addAll(userService.selectUserList(user));
        return getDataTable(list);
    }

    /**
     * 导出代理信息列表
     */
    @RequiresPermissions("system:agent:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(User user)
    {
        user.setUserRole(4);
        List<User> list = userService.selectUserList(user);
        user.setUserRole(5);
        list.addAll(userService.selectUserList(user));
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增代理信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存代理信息
     */
    @RequiresPermissions("system:agent:add")
    @Log(title = "代理信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(User user)
    {
        user.setUserRole(5);
        int i = userService.insertUser(user);
        if(i > 0){
            return AjaxResult.success("成功");
        }else{
            return AjaxResult.error("信息已存在");
        }
    }

    /**
     * 修改代理信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        User user = userService.selectUserById(id);
        mmap.put("user", user);
        return prefix + "/edit";
    }

    /**
     * 修改保存代理信息
     */
    @RequiresPermissions("system:agent:edit")
    @Log(title = "代理信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(User user)
    {
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除代理信息
     */
    @RequiresPermissions("system:agent:remove")
    @Log(title = "代理信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userService.deleteUserByIds(ids));
    }
}
