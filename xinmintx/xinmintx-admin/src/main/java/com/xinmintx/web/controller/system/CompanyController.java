package com.xinmintx.web.controller.system;

import com.xinmintx.common.annotation.Log;
import com.xinmintx.common.config.Global;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.core.page.TableDataInfo;
import com.xinmintx.common.enums.BusinessType;
import com.xinmintx.common.utils.file.FileUploadUtils;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.system.domain.Company;
import com.xinmintx.system.domain.UnitPhoto;
import com.xinmintx.system.domain.User;
import com.xinmintx.system.service.IUnitPhotoService;
import com.xinmintx.system.service.IUserService;
import com.xinmintx.web.controller.tool.BASE64DecodedMultipartFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 分公司管理Controller
 *
 * @author xinmintx
 * @date 2019-11-11
 */
@Controller
@RequestMapping("/system/company")
public class CompanyController extends BaseController {
    private String prefix = "system/company";

    @Autowired
    private IUserService userService;

    @Autowired
    private IUnitPhotoService photoService;

    @RequiresPermissions("system:company:view")
    @GetMapping()
    public String user() {
        return prefix + "/company";
    }

    /**
     * 查询分公司信息列表
     */
    @RequiresPermissions("system:company:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user) {
        startPage();
        user.setUserRole(3);
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出分公司信息列表
     */
    @RequiresPermissions("system:company:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(User user) {
        user.setUserRole(3);
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增分公司信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存分公司信息
     */
    @RequiresPermissions("system:company:add")
    @Log(title = "分公司信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(User user, Integer[] file) {
        user.setUserRole(3);
        int i = userService.insertUser(user);
        if(i > 0){
            Long id = user.getId();
            UnitPhoto unitPhoto = new UnitPhoto();
            unitPhoto.setCompanyId(Math.toIntExact(id));
            photoService.updateUnitPhotoByIds(unitPhoto, file);
            return AjaxResult.success("成功");
        }else if (i == 0){
            return AjaxResult.error("信息已存在");
        }else{
            return AjaxResult.error("该区域已存在分公司");
        }
    }

    /**
     * 修改分公司信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        User user = userService.selectUserById(id);
        List<UnitPhoto> unitPhotos = photoService.selelcUnitPhoteByCompanyId(Math.toIntExact(user.getId()));
        Company company = new Company();
        company.setCardUp(unitPhotos.get(0).getPhotoUrl());
        company.setCardUpId(unitPhotos.get(0).getId());
        company.setCardDown(unitPhotos.get(1).getPhotoUrl());
        company.setCardDownId(unitPhotos.get(1).getId());
        company.setId(user.getId());
        company.setOrderId(user.getOrderId());
        company.setName(user.getName());
        company.setCellphone(user.getCellphone());
        company.setGender(user.getGender());
        company.setIdcard(user.getIdcard());
        company.setRecommender(user.getRecommender());
        company.setUserRole(user.getUserRole());
        company.setStatus(user.getStatus());
        company.setAmount(user.getAmount());
        company.setCompanyName(user.getCompanyName());
        company.setCompanyAddress(user.getCompanyAddress());
        String regionName = user.getRegionName();
        String replace = regionName.replace(",", " ");
        company.setRegionName(replace);
        company.setRegionCode(user.getRegionCode());
        company.setIsReview(user.getIsReview());
        company.setCreateUser(user.getCreateUser());
        company.setUpdateUser(user.getUpdateUser());
        company.setLastLogin(user.getLastLogin());
        company.setSearchValue(user.getSearchValue());
        company.setCreateBy(user.getCreateBy());
        company.setCreateTime(user.getCreateTime());
        company.setUpdateBy(user.getUpdateBy());
        company.setUpdateTime(user.getUpdateTime());
        company.setRemark(user.getRemark());
        company.setParams(user.getParams());
        mmap.put("company", company);
        return prefix + "/edit";
    }

    /**
     * 修改保存分公司信息
     */
    @RequiresPermissions("system:company:edit")
    @Log(title = "分公司信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(User user, Integer[] file) {
        int i = userService.updateUser(user);
        if (i > 0){
            Long id = user.getId();
            photoService.deleteUnitPhotoByCompanyIdWithoutIds(Math.toIntExact(id),file);
            UnitPhoto unitPhoto = new UnitPhoto();
            unitPhoto.setCompanyId(Math.toIntExact(id));
            photoService.updateUnitPhotoByIds(unitPhoto,file);
            return AjaxResult.success("成功");
        }else if (i == 0){
            return AjaxResult.error("信息已存在");
        }else{
            return AjaxResult.error("该区域已存在分公司");
        }
    }

    /**
     * 删除分公司信息
     */
    @RequiresPermissions("system:company:remove")
    @Log(title = "分公司信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(userService.deleteUserByIds(ids));
    }
}
