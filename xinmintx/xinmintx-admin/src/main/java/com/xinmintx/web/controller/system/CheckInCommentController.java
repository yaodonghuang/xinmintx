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
import com.xinmintx.system.domain.CheckInComment;
import com.xinmintx.system.service.ICheckInCommentService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 打卡签到记录评论Controller
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/system/comment")
public class CheckInCommentController extends BaseController
{
    private String prefix = "system/comment";

    @Autowired
    private ICheckInCommentService checkInCommentService;

    @RequiresPermissions("system:comment:view")
    @GetMapping()
    public String comment()
    {
        return prefix + "/comment";
    }

    /**
     * 查询打卡签到记录评论列表
     */
    @RequiresPermissions("system:comment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CheckInComment checkInComment)
    {
        startPage();
        List<CheckInComment> list = checkInCommentService.selectCheckInCommentList(checkInComment);
        return getDataTable(list);
    }

    /**
     * 导出打卡签到记录评论列表
     */
    @RequiresPermissions("system:comment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CheckInComment checkInComment)
    {
        List<CheckInComment> list = checkInCommentService.selectCheckInCommentList(checkInComment);
        ExcelUtil<CheckInComment> util = new ExcelUtil<CheckInComment>(CheckInComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 新增打卡签到记录评论
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存打卡签到记录评论
     */
    @RequiresPermissions("system:comment:add")
    @Log(title = "打卡签到记录评论", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CheckInComment checkInComment)
    {
        return toAjax(checkInCommentService.insertCheckInComment(checkInComment));
    }

    /**
     * 修改打卡签到记录评论
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CheckInComment checkInComment = checkInCommentService.selectCheckInCommentById(id);
        mmap.put("checkInComment", checkInComment);
        return prefix + "/edit";
    }

    /**
     * 修改保存打卡签到记录评论
     */
    @RequiresPermissions("system:comment:edit")
    @Log(title = "打卡签到记录评论", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CheckInComment checkInComment)
    {
        return toAjax(checkInCommentService.updateCheckInComment(checkInComment));
    }

    /**
     * 删除打卡签到记录评论
     */
    @RequiresPermissions("system:comment:remove")
    @Log(title = "打卡签到记录评论", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(checkInCommentService.deleteCheckInCommentByIds(ids));
    }
}
