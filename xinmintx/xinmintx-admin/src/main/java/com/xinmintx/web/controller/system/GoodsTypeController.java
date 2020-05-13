package com.xinmintx.web.controller.system;

import java.util.List;

import com.xinmintx.common.constant.UserConstants;
import com.xinmintx.common.core.domain.Ztree;
import com.xinmintx.framework.util.ShiroUtils;
import com.xinmintx.system.domain.GoodsTypeBean;
import com.xinmintx.system.domain.SysMenu;
import com.xinmintx.system.service.IGoodsService;
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
import com.xinmintx.system.domain.GoodsType;
import com.xinmintx.system.service.IGoodsTypeService;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.common.core.page.TableDataInfo;

/**
 * 商品分类Controller
 * 
 * @author xinmintx
 * @date 2019-12-12
 */
@Controller
@RequestMapping("/system/type")
public class GoodsTypeController extends BaseController
{
    private String prefix = "system/type";

    @Autowired
    private IGoodsTypeService goodsTypeService;

    @RequiresPermissions("system:type:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    /**
     * 查询商品分类列表
     */
    @RequiresPermissions("system:type:list")
    @PostMapping("/list")
    @ResponseBody
    public List<GoodsType> list(GoodsType goodsType)
    {
        //startPage();
        List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
        return list;
    }

    /**
     * 导出商品分类列表
     */
    @RequiresPermissions("system:type:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GoodsType goodsType)
    {
        List<GoodsType> list = goodsTypeService.selectGoodsTypeList(goodsType);
        ExcelUtil<GoodsType> util = new ExcelUtil<GoodsType>(GoodsType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 新增商品分类
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        GoodsType goodsType = null;
        if (0L != parentId){
            goodsType = goodsTypeService.selectGoodsTypeById(parentId);
        }else{
            goodsType = new GoodsType();
            goodsType.setId(0L);
            goodsType.setLevel(0L);
            goodsType.setTypeName("主目录");
        }
        mmap.put("type", goodsType);
        return prefix + "/add";
    }

    /**
     * 新增保存商品分类
     */
    @RequiresPermissions("system:type:add")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoodsType goodsType)
    {
        String menuNameUnique = checkMenuNameUnique(goodsType);
        if (UserConstants.MENU_NAME_NOT_UNIQUE.equals(menuNameUnique)){
            return AjaxResult.error("已存在该分类");
        }
        return toAjax(goodsTypeService.insertGoodsType(goodsType));
    }

    /**
     * 修改商品分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GoodsType goodsType = goodsTypeService.selectGoodsTypeById(id);
        mmap.put("goodsType", goodsType);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品分类
     */
    @RequiresPermissions("system:type:edit")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoodsType goodsType)
    {
        if (goodsType.getId().equals(goodsType.getParentId())){
            return AjaxResult.error("上级分类不能为本身");
        }
        return toAjax(goodsTypeService.updateGoodsType(goodsType));
    }

    /**
     * 删除商品分类
     */
    @RequiresPermissions("system:type:remove")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @GetMapping( "/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        if (goodsTypeService.selectCountMenuByParentId(id) > 0)
        {
            return AjaxResult.warn("存在子菜单,不允许删除");
        }
        return toAjax(goodsTypeService.deleteGoodsTypeById(id));
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    public List<Ztree> menuTreeData()
    {
        List<Ztree> ztrees = goodsTypeService.menuTreeData();
        return ztrees;
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long menuId, ModelMap mmap)
    {
        if (menuId == 0){
            GoodsType type = new GoodsType();
            type.setId(0L);
            type.setTypeName("主目录");
            type.setLevel(0L);
            mmap.put("type", type);
        }else{
            mmap.put("type", goodsTypeService.selectGoodsTypeById(menuId));
        }
        return prefix + "/tree";
    }

    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(GoodsType goodsType)
    {
        return goodsTypeService.checkMenuNameUnique(goodsType);
    }

}
