package com.xinmintx.web.controller.system;

import java.util.List;

import com.xinmintx.web.controller.tool.File2OSSUtils;
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
import com.xinmintx.system.domain.Gift;
import com.xinmintx.system.service.IGiftService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 礼包Controller
 * 
 * @author xinmintx
 * @date 2019-12-16
 */
@Controller
@RequestMapping("/system/gift")
public class GiftController extends BaseController
{
    private String prefix = "system/gift";

    @Autowired
    private IGiftService giftService;

    @Autowired
    private File2OSSUtils file2OSSUtils;

    @RequiresPermissions("system:gift:view")
    @GetMapping()
    public String gift()
    {
        return prefix + "/gift";
    }

    /**
     * 查询礼包列表
     */
    @RequiresPermissions("system:gift:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Gift gift)
    {
        startPage();
        List<Gift> list = giftService.selectGiftList(gift);
        return getDataTable(list);
    }

    /**
     * 导出礼包列表
     */
    @RequiresPermissions("system:gift:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Gift gift)
    {
        List<Gift> list = giftService.selectGiftList(gift);
        ExcelUtil<Gift> util = new ExcelUtil<Gift>(Gift.class);
        return util.exportExcel(list, "gift");
    }

    /**
     * 新增礼包
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存礼包
     */
    @RequiresPermissions("system:gift:add")
    @Log(title = "礼包", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Gift gift)
    {
        return toAjax(giftService.insertGift(gift));
    }

    /**
     * 修改礼包
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Gift gift = giftService.selectGiftById(id);
        mmap.put("gift", gift);
        return prefix + "/edit";
    }

    /**
     * 修改保存礼包
     */
    @RequiresPermissions("system:gift:edit")
    @Log(title = "礼包", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Gift gift)
    {
        return toAjax(giftService.updateGift(gift));
    }

    /**
     * 删除礼包
     */
    @RequiresPermissions("system:gift:remove")
    @Log(title = "礼包", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(giftService.deleteGiftByIds(ids));
    }

    /**
     * 删除礼包
     */
    @RequiresPermissions("system:gift:del")
    @Log(title = "礼包", businessType = BusinessType.DELETE)
    @PostMapping( "/del")
    @ResponseBody
    public AjaxResult del(Long ids)
    {
        return toAjax(giftService.delGiftByIds(ids));
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
