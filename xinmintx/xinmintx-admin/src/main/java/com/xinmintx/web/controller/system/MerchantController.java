package com.xinmintx.web.controller.system;

import java.util.List;

import com.xinmintx.system.domain.MerchantPhoto;
import com.xinmintx.system.domain.Region;
import com.xinmintx.system.domain.UnitPhoto;
import com.xinmintx.system.service.IRegionService;
import com.xinmintx.system.service.IUnitPhotoService;
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
import com.xinmintx.system.domain.Merchant;
import com.xinmintx.system.service.IMerchantService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 商家信息Controller
 *
 * @author xinmintx
 * @date 2019-11-11
 */
@Controller
@RequestMapping("/system/merchant")
public class MerchantController extends BaseController {
    private String prefix = "system/merchant";

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IRegionService regionService;

    @RequiresPermissions("system:merchant:view")
    @GetMapping()
    public String merchant() {
        return prefix + "/merchant";
    }

    /**
     * 查询商家信息列表
     */
    @RequiresPermissions("system:merchant:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Merchant merchant) {
        startPage();
        List<Merchant> list = merchantService.selectMerchantList(merchant);
        return getDataTable(list);
    }

    /**
     * 导出商家信息列表
     */
    @RequiresPermissions("system:merchant:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Merchant merchant) {
        List<Merchant> list = merchantService.selectMerchantList(merchant);
        ExcelUtil<Merchant> util = new ExcelUtil<Merchant>(Merchant.class);
        return util.exportExcel(list, "merchant");
    }

    /**
     * 新增商家信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存商家信息
     */
    @RequiresPermissions("system:merchant:add")
    @Log(title = "商家信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Merchant merchant) {
        //获取省市县区
        //区
        Region district = regionService.selectRegionById(Long.valueOf(merchant.getRegionCode()));
        //市
        Region city = regionService.selectRegionById(district.getPid());
        //省
        Region province = regionService.selectRegionById(city.getPid());
        merchant.setRegionName(province.getName() + "," + city.getName() + "," + district.getName());
        return toAjax(merchantService.insertMerchant(merchant));
    }

    /**
     * 修改商家信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Merchant merchant = merchantService.selectMerchantById(id);
        //获取省市县区
        //区
        Region district = regionService.selectRegionById(Long.valueOf(merchant.getRegionCode()));
        //市
        Region city = regionService.selectRegionById(district.getPid());
        //省
        Region province = regionService.selectRegionById(city.getPid());
        mmap.put("merchant", merchant);
        mmap.put("province", province.getId());
        mmap.put("city", city.getId());
        mmap.put("district", district.getId());
        return prefix + "/edit";
    }

    /**
     * 修改保存商家信息
     */
    @RequiresPermissions("system:merchant:edit")
    @Log(title = "商家信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Merchant merchant) {
        //获取省市县区
        //区
        Region district = regionService.selectRegionById(Long.valueOf(merchant.getRegionCode()));
        //市
        Region city = regionService.selectRegionById(district.getPid());
        //省
        Region province = regionService.selectRegionById(city.getPid());
        merchant.setRegionName(province.getName()+","+city.getName()+","+district.getName());
        return toAjax(merchantService.updateMerchant(merchant));
    }

    /**
     * 删除商家信息
     */
    @RequiresPermissions("system:merchant:remove")
    @Log(title = "商家信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(merchantService.deleteMerchantByIds(ids));
    }

    /**
     * 查询商家信息列表添加到打印机配置表
     */
    @RequiresPermissions("system:merchant:lists")
    @PostMapping("/lists")
    @ResponseBody
    public TableDataInfo lists(Merchant merchant) {
        startPage();
        List<Merchant> list = merchantService.selectMerchantLists(merchant);
        return getDataTable(list);
    }
}
