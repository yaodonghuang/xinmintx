package com.xinmintx.web.controller.system;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.xinmintx.system.domain.CheckInUser;
import com.xinmintx.system.domain.SysCard;
import com.xinmintx.system.service.ISysCardService;
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
 * 后台打卡Controller
 * 
 * @author sw
 * @date 2019-11-13
 */
@Controller
@RequestMapping("/system/card")
public class SysCardController extends BaseController
{
    private String prefix = "system/card";

    @Autowired
    private ISysCardService sysCardService;


    @RequiresPermissions("system:card:view")
    @GetMapping()
    public String card()
    {
        return prefix + "/card";
    }

    /**
     * 查询后台打卡列表
     */
    @RequiresPermissions("system:card:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCard sysCard)
    {
        startPage();
        List<SysCard> list = sysCardService.selectSysCardList(sysCard);
        return getDataTable(list);
    }

    /**
     * 导出后台打卡列表
     */
    @RequiresPermissions("system:card:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCard sysCard)
    {
        List<SysCard> list = sysCardService.selectSysCardList(sysCard);
        ExcelUtil<SysCard> util = new ExcelUtil<SysCard>(SysCard.class);
        return util.exportExcel(list, "card");
    }

    /**
     * 新增后台打卡
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存后台打卡
     */
    @RequiresPermissions("system:card:add")
    @Log(title = "后台打卡", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCard sysCard)
    {
        return toAjax(sysCardService.insertSysCard(sysCard));
    }

    /**
     * 修改后台打卡
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysCard sysCard = sysCardService.selectSysCardById(id);
        mmap.put("sysCard", sysCard);
        return prefix + "/edit";
    }

    /**
     * 修改保存后台打卡
     */
    @RequiresPermissions("system:card:edit")
    @Log(title = "后台打卡", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCard sysCard)
    {
        return toAjax(sysCardService.updateSysCard(sysCard));
    }

    /**
     * 删除后台打卡
     */
    @RequiresPermissions("system:card:remove")
    @Log(title = "后台打卡", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysCardService.deleteSysCardByIds(ids));
    }

    /**
     * 查询打卡人
     * @param id 打卡表id
     * @param mmap
     * @return
     */
    @RequiresPermissions("system:card:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap)
    {
        List<CheckInUser> users = sysCardService.selectCheckInById(id);
        mmap.put("users", users);
        return prefix + "/detail";
    }

    /**
     * 根据id删除打卡人
     * @param id 打卡表id
     */
    @RequestMapping("/deleteMemberCheckIn/{id}")
    @ResponseBody
    public void deleteMemberCheckIn (@PathVariable("id") Integer id) {
        sysCardService.deleteMemberCheckIn(id);
    }

    /**
     * 根据id删除评论
     * @param id 评论表id
     */
    @RequestMapping("/deleteComment/{id}")
    @ResponseBody
    public void deleteComment (@PathVariable("id") Integer id) {
        sysCardService.deleteComment(id);
    }

    /**
     * 根据id删除点赞
     * @param id 点赞表id
     */
    @RequestMapping("/deleteLike/{id}")
    @ResponseBody
    public void deleteLike (@PathVariable("id") Integer id) {
        sysCardService.deleteLike(id);
    }


}
