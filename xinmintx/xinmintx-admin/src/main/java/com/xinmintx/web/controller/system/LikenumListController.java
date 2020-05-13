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
import com.xinmintx.system.domain.LikenumList;
import com.xinmintx.system.service.ILikenumListService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 点赞列Controller
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/system/list")
public class LikenumListController extends BaseController
{
    private String prefix = "system/list";

    @Autowired
    private ILikenumListService likenumListService;

    @RequiresPermissions("system:list:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/list";
    }

    /**
     * 查询点赞列列表
     */
    @RequiresPermissions("system:list:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LikenumList likenumList)
    {
        startPage();
        List<LikenumList> list = likenumListService.selectLikenumListList(likenumList);
        return getDataTable(list);
    }

    /**
     * 导出点赞列列表
     */
    @RequiresPermissions("system:list:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LikenumList likenumList)
    {
        List<LikenumList> list = likenumListService.selectLikenumListList(likenumList);
        ExcelUtil<LikenumList> util = new ExcelUtil<LikenumList>(LikenumList.class);
        return util.exportExcel(list, "list");
    }

    /**
     * 新增点赞列
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存点赞列
     */
    @RequiresPermissions("system:list:add")
    @Log(title = "点赞列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LikenumList likenumList)
    {
        return toAjax(likenumListService.insertLikenumList(likenumList));
    }

    /**
     * 修改点赞列
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        LikenumList likenumList = likenumListService.selectLikenumListById(id);
        mmap.put("likenumList", likenumList);
        return prefix + "/edit";
    }

    /**
     * 修改保存点赞列
     */
    @RequiresPermissions("system:list:edit")
    @Log(title = "点赞列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LikenumList likenumList)
    {
        return toAjax(likenumListService.updateLikenumList(likenumList));
    }

    /**
     * 删除点赞列
     */
    @RequiresPermissions("system:list:remove")
    @Log(title = "点赞列", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(likenumListService.deleteLikenumListByIds(ids));
    }
}
