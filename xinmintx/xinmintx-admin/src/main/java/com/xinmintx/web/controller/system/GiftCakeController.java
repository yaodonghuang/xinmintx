package com.xinmintx.web.controller.system;

import com.xinmintx.common.annotation.Log;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.core.page.TableDataInfo;
import com.xinmintx.common.enums.BusinessType;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.system.domain.GiftCake;
import com.xinmintx.system.service.IGiftCakeService;
import com.xinmintx.web.controller.tool.File2OSSUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 商户生日蛋糕Controller
 * 
 * @author xinmintx
 * @date 2020-01-19
 */
@Controller
@RequestMapping("/system/cake")
@Transactional
public class GiftCakeController extends BaseController
{
    private String prefix = "system/cake";

    @Autowired
    private IGiftCakeService giftCakeService;
    @Autowired
    private File2OSSUtils file2OSSUtils;

    @RequiresPermissions("system:cake:view")
    @GetMapping()
    public String cake()
    {
        return prefix + "/cake";
    }

    /**
     * 查询商户生日蛋糕列表
     */
    @RequiresPermissions("system:cake:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GiftCake giftCake)
    {
        startPage();
        List<GiftCake> list = giftCakeService.selectGiftCakeList(giftCake);
        return getDataTable(list);
    }

    /**
     * 导出商户生日蛋糕列表
     */
    @RequiresPermissions("system:cake:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GiftCake giftCake)
    {
        List<GiftCake> list = giftCakeService.selectGiftCakeList(giftCake);
        ExcelUtil<GiftCake> util = new ExcelUtil<GiftCake>(GiftCake.class);
        return util.exportExcel(list, "cake");
    }

    /**
     * 新增商户生日蛋糕
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商户生日蛋糕
     */
    @RequiresPermissions("system:cake:add")
    @Log(title = "商户生日蛋糕", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GiftCake giftCake)
    {
        return toAjax(giftCakeService.insertGiftCake(giftCake));
    }

    /**
     * 修改商户生日蛋糕
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GiftCake giftCake = giftCakeService.selectGiftCakeById(id);
        mmap.put("giftCake", giftCake);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户生日蛋糕
     */
    @RequiresPermissions("system:cake:edit")
    @Log(title = "商户生日蛋糕", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GiftCake giftCake)
    {
        return toAjax(giftCakeService.updateGiftCake(giftCake));
    }

    /**
     * 删除商户生日蛋糕
     */
    @RequiresPermissions("system:cake:remove")
    @Log(title = "商户生日蛋糕", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(giftCakeService.deleteGiftCakeByIds(ids));
    }

    @ResponseBody
    @PostMapping("/imageListUpload")
    public String imageListUpload(MultipartFile[] file) {
        StringBuffer stringBuffer = new StringBuffer();
        for (MultipartFile multipartFile : file) {
            String file2 = file2OSSUtils.fileUploadOSS(multipartFile);
            stringBuffer.append("#" + file2);
        }
        String filese = stringBuffer.toString();
        return filese.substring(1);
    }
}
