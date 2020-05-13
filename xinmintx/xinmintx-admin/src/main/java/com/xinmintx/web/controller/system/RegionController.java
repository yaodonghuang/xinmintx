package com.xinmintx.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.xinmintx.system.domain.Region;
import com.xinmintx.system.service.IRegionService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 全国省市区（县）基础数据Controller
 *
 * @author xinmintx
 * @date 2019-11-11
 */
@Controller
@RequestMapping("/region")
public class RegionController extends BaseController {
    private String prefix = "system/region";

    @Autowired
    private IRegionService regionService;

    /**
     * 查询省
     */
    @PostMapping("/getProvince")
    @ResponseBody
    public AjaxResult getProvince() {
        Region region = new Region();
        region.setLevel(1L);
        List<Region> list = regionService.selectRegionList(region);
        return AjaxResult.success(list);
    }
    /**
     * 查询城市
     */
    @PostMapping("/getCity/{id}")
    @ResponseBody
    public AjaxResult getCity(@PathVariable("id") Long id) {
        Region region = new Region();
        region.setLevel(2L);
        region.setPid(id);
        List<Region> list = regionService.selectRegionList(region);
        return AjaxResult.success(list);
    }
    /**
     * 查询区县
     */
    @PostMapping("/getDistrict/{id}")
    @ResponseBody
    public AjaxResult getDistrict(@PathVariable("id") Long id) {
        Region region = new Region();
        region.setLevel(3L);
        region.setPid(id);
        List<Region> list = regionService.selectRegionList(region);
        return AjaxResult.success(list);
    }
}
