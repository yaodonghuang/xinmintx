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
 * 合伙人管理Controller
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
@Controller
@RequestMapping("/system/partner")
public class PartnerController extends BaseController
{
    private String prefix = "system/partner";

    @Autowired
    private IUserService userService;

    @RequiresPermissions("system:partner:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/partner";
    }

    /**
     * 查询合伙人信息列表
     */
    @RequiresPermissions("system:partner:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user)
    {
        startPage();
        user.setUserRole(2);
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出合伙人信息列表
     */
    @RequiresPermissions("system:partner:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(User user)
    {
        user.setUserRole(2);
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增合伙人信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合伙人信息
     */
    @RequiresPermissions("system:partner:add")
    @Log(title = "合伙人信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(User user)
    {
        user.setUserRole(2);
        int i = userService.insertUser(user);
        if(i > 0){
            return AjaxResult.success("成功");
        }else{
            return AjaxResult.error("信息已存在");
        }
    }

    /**
     * 修改合伙人信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        User user = userService.selectUserById(id);
        mmap.put("user", user);
        return prefix + "/edit";
    }

    /**
     * 修改保存合伙人信息
     */
    @RequiresPermissions("system:partner:edit")
    @Log(title = "合伙人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(User user)
    {
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除合伙人信息
     */
    @RequiresPermissions("system:partner:remove")
    @Log(title = "合伙人信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userService.deleteUserByIds(ids));
    }
}
