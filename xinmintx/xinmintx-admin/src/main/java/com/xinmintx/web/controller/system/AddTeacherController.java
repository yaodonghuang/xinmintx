package com.xinmintx.web.controller.system;

import java.util.List;
import com.xinmintx.system.domain.AddTeacher;
import com.xinmintx.system.service.AddTeacherService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import com.xinmintx.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xinmintx.common.annotation.Log;
import com.xinmintx.common.enums.BusinessType;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * @ClassName:.AddTeacherController
 * @author:chf
 * @Date:2020/1/17：17:13
 * @developerKits： win 10     jdk1.8
 */
@Controller
@RequestMapping("/system/addTeacher")
public class AddTeacherController extends BaseController{
    private String prefix = "system/teacher";

    @Autowired
    private AddTeacherService teacherService;

    @RequiresPermissions("system:addTeacher:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询用户信息列表
     */
    @RequiresPermissions("system:addTeacher:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AddTeacher user)
    {
        startPage();
        List<AddTeacher> list = teacherService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @RequiresPermissions("system:addTeacher:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AddTeacher user)
    {
        List<AddTeacher> list = teacherService.selectUserList(user);
        ExcelUtil<AddTeacher> util = new ExcelUtil<AddTeacher>(AddTeacher.class);
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
    @RequiresPermissions("system:addTeacher:add")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AddTeacher user)
    {
        return toAjax(teacherService.insertUser(user));
    }

    /**
     * 修改用户信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AddTeacher user = teacherService.selectUserById(id);
        mmap.put("user", user);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户信息
     */
    @RequiresPermissions("system:addTeacher:edit")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AddTeacher user)
    {
        return toAjax(teacherService.updateUser(user));
    }

    /**
     * 删除用户信息
     */
    @RequiresPermissions("system:addTeacher:remove")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(teacherService.deleteUserByIds(ids));
    }
}
