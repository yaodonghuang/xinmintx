package com.xinmintx.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.xinmintx.common.annotation.Log;
import com.xinmintx.common.core.controller.BaseController;
import com.xinmintx.common.core.domain.AjaxResult;
import com.xinmintx.common.core.page.TableDataInfo;
import com.xinmintx.common.enums.BusinessType;
import com.xinmintx.common.utils.poi.ExcelUtil;
import com.xinmintx.system.domain.*;
import com.xinmintx.system.mapper.GoodsPhotoMapper;
import com.xinmintx.system.mapper.GoodsSkuBeanMapper;
import com.xinmintx.system.service.IFactoryService;
import com.xinmintx.system.service.IGoodsService;
import com.xinmintx.web.controller.tool.File2OSSUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品Controller
 * 
 * @author xinmintx
 * @date 2019-12-11
 */
@Controller
@RequestMapping("/system/goods")
public class GoodsController extends BaseController
{
    private String prefix = "system/goods";

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private GoodsSkuBeanMapper coodsSkuBeanMapper;

    @Autowired
    private com.xinmintx.system.mapper.GoodsSkuMapper goodsSkuMapper;

    @Autowired
    private IFactoryService factoryService;

    @Autowired
    private File2OSSUtils file2OSSUtils;

    @Autowired
    private GoodsPhotoMapper photoMapper;

    @RequiresPermissions("system:goods:view")
    @GetMapping()
    public String goods()
    {
        return prefix + "/goods";
    }

    @RequiresPermissions("system:goods:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap)
    {
        //todo
        GoodsSku maxSpec = goodsService.selectMaxSpec(id);
        if(maxSpec != null){
            mmap.addAttribute("maxSpec",maxSpec.getId());
        }
        GoodsSku MaxSpecValue = goodsService.selectMaxSpecValue(id);
        if(MaxSpecValue != null){
            mmap.addAttribute("MaxSpecValue",MaxSpecValue.getId());
        }
        List<SpecSelect> selectSpec = goodsService.specSelect(id);
        mmap.addAttribute("selectSpec",selectSpec);

        mmap.addAttribute("selectSpec",selectSpec);
        mmap.addAttribute("goodId",id);
        return prefix + "/spec";
    }
    /**
     * 查询商品列表
     */
    @RequiresPermissions("system:goods:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Goods goods)
    {
        startPage();
        List<GoodsExt> list = goodsService.selectGoodsList(goods);
        return getDataTable(list);
    }

    /**
     * 查询商品列表
     */
    @RequiresPermissions("system:goods:list")
    @PostMapping("/giftGoodslist")
    @ResponseBody
    public TableDataInfo giftGoodslist(Goods goods)
    {
        startPage();
        goods.setGiftBag(Long.valueOf(1));// 查询礼包商品
        List<GoodsExt> list = goodsService.selectGoodsList(goods);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @RequiresPermissions("system:goods:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Goods goods)
    {
        List<GoodsExt> list = goodsService.selectGoodsList(goods);
        ExcelUtil<GoodsExt> util = new ExcelUtil<GoodsExt>(GoodsExt.class);
        return util.exportExcel(list, "goods");
    }

    /**
     * 查询一级分类
     *
     * @return
     */
    @PostMapping("/getParent")
    @ResponseBody
    public List<GoodsType> getParent() {
        List<GoodsType> list = goodsService.selectParent();
        return list;
    }

    /**
     * 查询二级列表
     * @param parentCode
     * @return
     */
    @PostMapping("/getParentId")
    @ResponseBody
    public List getParentId(Integer parentCode) {
        List<GoodsType> commoditysType = goodsService.selectByParentId(parentCode);
        return commoditysType;
    }

    /**
     * 查询三级列表
     * @param typeId
     * @return
     */
    @PostMapping("/getThreeType")
    @ResponseBody
    public List getThreeType(Integer typeId) {
        List<GoodsType> goodsTypes = goodsService.getThreeType(typeId);
        return goodsTypes;
    }

    /**
     * 商品规格
     *
     * @param id
     * @return
     */
    @RequestMapping("/getType/{id}/{typeId}")
    @ResponseBody
    public List<GoodsBean> getType(@PathVariable("id") Integer id,@PathVariable("typeId") Integer typeId) {
        List<GoodsBean> list = goodsService.getType(id,typeId);
        return list;
    }

    /**
     * 查询商户(厂家)
     *
     * @return
     */
    @RequestMapping("/getMerchantId")
    @ResponseBody
    public List<Factory> getMerchantId(Integer source,String name) {
        if (source==1) {
            startPage();
            List<Factory> list = factoryService.selectFactory(name);
            return list;
        }else {
            return null;
        }
    }

    /**
     * 新增商品
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商品
     */
    @RequiresPermissions("system:goods:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoodsBean goodsBean, HttpServletRequest request)
    {
        String content = request.getParameter("content");
        goodsBean.setContent(content);
        goodsBean.setTurnsPhotoList(goodsBean.getTurnsPhoto().split("#"));
        return toAjax(goodsService.insertGoods(goodsBean));
    }

    /**
     * 修改商品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
//      Goods goods = goodsService.selectGoodsById(id);
        GoodsExtent extent = goodsService.selectGoods(id);
        mmap.put("goods", extent);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品
     */
    @RequiresPermissions("system:goods:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoodsBean goodsBean, HttpServletRequest request)
    {
        String content = request.getParameter("content");
        goodsBean.setContent(content);
        goodsBean.setTurnsPhotoList(goodsBean.getTurnsPhoto().split("#"));
        return toAjax(goodsService.updateGoods(goodsBean));
    }

    /**
     * 删除商品
     */
    @RequiresPermissions("system:goods:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(goodsService.deleteGoodsByIds(ids));
    }

    /**
     * 删除规格
     * @param id
     * @return
     */
    @PostMapping( "/delete/{id}")
    @ResponseBody
    public AjaxResult deleteSku(@PathVariable Integer id){
        return toAjax(goodsService.deleteSku(id));
    }

    /**
     * 图片上传阿里云
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("/imageUpload")
    public String imageUpload(MultipartFile file) {
        return file2OSSUtils.fileUploadOSS(file);
    }

    /**
     * 上传文件
     * @param
     * @param file 上传的文件，支持多文件
     * @throws Exception
     */
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(@RequestParam("img")MultipartFile file) {
        StringBuffer stringBuffer = new StringBuffer();
        String files = file2OSSUtils.fileUploadOSS(file);
        return files;
    }

    /**
     * 图片上传
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("/imagesUpload")
    public String imageUpload(MultipartFile[] file) {
        StringBuffer stringBuffer = new StringBuffer();
        for (MultipartFile multipartFile : file) {
            String file2 = file2OSSUtils.fileUploadOSS(multipartFile);
            stringBuffer.append("#" + file2);
        }
        String filese = stringBuffer.toString();
        return filese.substring(1);
    }

    /**
     * 拼接图片
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("/imageListUpload")
    public Map imageListUpload(MultipartFile[] file) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer buffer = new StringBuffer();
        for (MultipartFile multipartFile : file) {
            String file2 = file2OSSUtils.fileUploadOSS(multipartFile);
            GoodsPhoto goodsPhoto = new GoodsPhoto();
            goodsPhoto.setPhotoUrl(file2);
            goodsPhoto.setCreateTime(new Date());
            goodsPhoto.setUpdateTime(new Date());
            photoMapper.insert(goodsPhoto);
            buffer.append("#"+goodsPhoto.getId());
            stringBuffer.append("#" + file2);
        }
        String filese = stringBuffer.toString().substring(1);
        String photoIds = buffer.toString().substring(1);
        Map<String,String> map = new HashMap<>();
        map.put("photoIds",photoIds);
        map.put("filese",filese);
        return map;
    }

    /**
     * 增加规格
     * @param spec
     * @return
     */
    @RequiresPermissions("system:goods:addSku")
    @Log(title = "商品", businessType = BusinessType.OTHER)
    @ResponseBody
    @PostMapping("/addSku")
    public Boolean addSku(@RequestBody Spec spec) {
        return goodsService.addSku(spec);
    }

    /**
     * 跳转规格查询
     * @param id
     * @param map
     * @return
     */
    @RequiresPermissions("system:goods:addSku")
    @RequestMapping("/jumpGoods/{id}")
    public String jumpGoods(@PathVariable("id") String id,ModelMap map){
        map.put("id",id);
        return prefix + "/goodsSku";
    }

    /**
     * 查询规格列表
     * @param id
     * @return
     */
    @RequestMapping("/selectGoods/{id}")
    @ResponseBody
    public TableDataInfo selectGoods(@PathVariable("id") String id)
    {
        startPage();
        List<GoodsSkuBean> goodsSkuBean = goodsService.querySku(id);
        return getDataTable(goodsSkuBean);
    }

    /**
     * 跳转添加参数页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/jumpParameter/{id}")
    public String jumpParameter(@PathVariable("id") String id,ModelMap map){
        map.put("id",id);
        return prefix + "/addParameter";
    }

    /**
     * 添加参数
     * @param id
     * @param attributeName
     * @param attributeValue
     * @return
     */
    @RequestMapping("/addParameter")
    @ResponseBody
    public AjaxResult addParameter(Integer id,String[] attributeName,String[] attributeValue){
        return toAjax(goodsService.insertParameter(id,attributeName,attributeValue));
    }

    /**
     * 修改参数查询
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/updateParameter/{id}")
    public String updateParameter(@PathVariable("id") String id,ModelMap map){
        String parameter = goodsService.selectParameter(id);
        List list = JSONObject.parseObject(parameter, List.class);
        map.put("id",id);
        map.put("list",list);
        return prefix + "/updateParameter";
    }

    /**
     * 查询商户跳转
     * @return
     */
    @GetMapping("/selectMerchant/{id}")
    public String selectGoods(@PathVariable("id") Integer id,ModelMap map)
    {
        map.put("id",id);
        return prefix + "/factory";
    }

    /**
     * 回显 内容
     * @param id
     * @param map
     * @return
     */
    @RequestMapping( "/updateSku/{id}")
    public String updateSku(@PathVariable Integer id,ModelMap map){
        List<GoodsSku> goodsSkuBeans = coodsSkuBeanMapper.selectSkuById(id.toString());
        if(goodsSkuBeans.size() > 0){
            if(goodsSkuBeans.get(0).getPhotoId() != null){
                GoodsPhoto goodsPhoto = photoMapper.selectByPrimaryKey(goodsSkuBeans.get(0).getPhotoId());
                map.put("url",goodsPhoto.getPhotoUrl());
            }
            map.put("goodsSku",goodsSkuBeans.get(0));
        }
        return prefix + "/updata";
    }

    /**
     * 修改Sku 简单信息
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public void update(@RequestParam("photoUrl") String photoUrl,GoodsSku goodsSku){
        if(!photoUrl.equals("")){
            GoodsPhoto goodsPhoto = new GoodsPhoto();
            goodsPhoto.setPhotoUrl(photoUrl);
            goodsPhoto.setCreateTime(new Date());
            goodsPhoto.setUpdateTime(new Date());
            int insert = photoMapper.insert(goodsPhoto);
            goodsSku.setPhotoId(goodsPhoto.getId());
        }
        //e卡价
        if(goodsSku.getePrice() == null || goodsSku.getePrice().toString() == ""){
            goodsSku.setePrice(null);
        }
        //金卡价
        if(goodsSku.getGlodPrice() == null || goodsSku.getGlodPrice().toString() == ""){
            goodsSku.setGlodPrice(null);
        }
        //
        if(goodsSku.getAgentPrice() == null || goodsSku.getAgentPrice().toString() == ""){
            goodsSku.setAgentPrice(null);
        }
        int i = goodsSkuMapper.updateByPrimaryKeySelective(goodsSku);
    }
}
