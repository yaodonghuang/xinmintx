package com.xinmintx.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.xinmintx.common.core.text.Convert;
import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.system.domain.*;
import com.xinmintx.system.mapper.*;
import com.xinmintx.system.service.IGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 商品Service业务层处理
 *
 * @author xinmintx
 * @date 2019-12-11
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsExtMapper goodsExtMapper;

    @Autowired
    private GoodsTypeMapper typeMapper;

    @Autowired
    private GoodsPhotoMapper photoMapper;

    @Autowired
    private GoodsSpecMapper specMapper;

    @Autowired
    private GoodsSpecValueMapper specValueMapper;

    @Autowired
    private GoodsSkuMapper skuMapper;

    @Autowired
    private GoodsTypeExtMapper selectSpecMapper;

    @Autowired
    private GoodsExtentMapper beanMapper;

    @Autowired
    private GoodsSkuBeanMapper skuBeanMapper;

    /**
     * 查询商品
     *
     * @param id 商品ID
     * @return 商品
     */
    @Override
    public Goods selectGoodsById(Long id) {
        return goodsMapper.selectGoodsById(id);
    }

    /**
     * 查询商品列表
     *
     * @param goods 商品
     * @return 商品
     */
    @Override
    public List<GoodsExt> selectGoodsList(Goods goods) {
        return goodsExtMapper.selectByCategoryCode(goods);
    }

    /**
     * 新增商品
     *
     * @return 结果
     */
    @Override
    public int insertGoods(GoodsBean goodsBean) {
        try {
            //添加goods_photo表  轮播图
//            List<Integer> photoId = new ArrayList<>();
//            GoodsPhoto goodsPhoto = new GoodsPhoto();
//            String[] turnsPhotoList = goodsBean.getTurnsPhotoList();
//            for (String turnsPhoto : turnsPhotoList) {
//                goodsPhoto.setPhotoUrl(turnsPhoto);
//                goodsPhoto.setCreateTime(new Date());
//                goodsPhoto.setUpdateTime(new Date());
//                photoMapper.insert(goodsPhoto);
//                photoId.add(goodsPhoto.getId());
//            }

            //添加goods表
            Goods goods = new Goods();
            goods.setSource(goodsBean.getSource().intValue());
            goods.setRelateId(goodsBean.getRelateId().intValue());
            goods.setTypeId(goodsBean.getParentId().intValue());
            goods.setChoiceness(goodsBean.getChoiceness());
            goods.setHotSale(goodsBean.getHotSale());
            goods.setPreorder(goodsBean.getPreorder());
            goods.setGoodsListName(goodsBean.getGoodsListName());
            goods.setGoodsName(goodsBean.getGoodsName());
            goods.setContent(goodsBean.getContent());
            goods.setGiftBag(goodsBean.getGiftBag());
            String comma =goodsBean.getTurnsPhoto().substring(0,1);
            if (",".equals(comma)){
                goodsBean.setTurnsPhoto(goodsBean.getTurnsPhoto().substring(1));
            }
            goods.setTurnsPhoto(goodsBean.getTurnsPhoto());
            goods.setSpeType(goodsBean.getSpeType());
            goods.setPrice(BigDecimal.valueOf(goodsBean.getPrice()));
            goods.setAgencyPrice(BigDecimal.valueOf(goodsBean.getAgencyPrice()));
            goods.setBazaarPrice(BigDecimal.valueOf(goodsBean.getBazaarPrice()));
            goods.setProcurementPrice(BigDecimal.valueOf(goodsBean.getProcurementPrice()));
            goods.setLinePrice(BigDecimal.valueOf(goodsBean.getLinePrice()));
            goods.setStockNum(goodsBean.getStockNum().intValue());
            goods.setSalesInitial(goodsBean.getSalesInitial().intValue());
            goods.setStatus(goodsBean.getStatus());
            goods.setSort(goodsBean.getSort().intValue());
            goods.setActivityTitle(goodsBean.getActivityTitle());
            goodsMapper.insertGoods(goods);

            if (StringUtils.isNotBlank(goodsBean.getSpecName())) {
                //添加goods_sprc表
                GoodsSpec goodsSpec = new GoodsSpec();
                goodsSpec.setName(goodsBean.getSpecName());
                goodsSpec.setGoodsId(goods.getId().intValue());
                goodsSpec.setCode("1");
                specMapper.insert(goodsSpec);

                //添加goods_spec_value表
                int m = 1;
                GoodsSpecValue specValue = new GoodsSpecValue();
                List<Integer> specId = new ArrayList<>();
                String[] specValues = goodsBean.getSpecValue();
                for (String value : specValues) {
                    specValue.setCode(m);
                    specValue.setSpecId(goodsSpec.getId());
                    specValue.setValue(value);
                    specValue.setGoodsId(goods.getId().intValue());
                    specValueMapper.insert(specValue);
                    specId.add(specValue.getId());
                    m++;
                }

                //添加goods_sku表
                GoodsPhoto goodsPhoto1 = new GoodsPhoto();
                GoodsSku goodsSku = new GoodsSku();
                List<Integer> photoList = new ArrayList<>();
                String[] skuPhotos = goodsBean.getSkuPhoto();
                for (String skuPhoto : skuPhotos) {
                    goodsPhoto1.setPhotoUrl(skuPhoto);
                    goodsPhoto1.setCreateTime(new Date());
                    goodsPhoto1.setUpdateTime(new Date());
                    photoMapper.insert(goodsPhoto1);
                    photoList.add(goodsPhoto1.getId());
                }
                String[] skuId = goodsBean.getSkuId();
                Double[] skuPrice = goodsBean.getSkuPrice();
                Integer[] skuStockNum = goodsBean.getSkuStockNum();
                Double[] GoodsWeight = goodsBean.getGoodsWeight();
                for (int i = 0; i < photoList.size(); i++) {
                    goodsSku.setSkuId(skuId[i]);
                    goodsSku.setGoodsId(goods.getId().intValue());
                    goodsSku.setSpecValueId(specId.get(i).toString());
                    goodsSku.setPrice(BigDecimal.valueOf(skuPrice[i]));
                    goodsSku.setLinePrice(BigDecimal.valueOf(goodsBean.getLinePrice()));
                    goodsSku.setAgentPrice(BigDecimal.valueOf(goodsBean.getAgentPrice()));
                    goodsSku.setStockNum(skuStockNum[i]);
                    goodsSku.setPhotoId(photoList.get(i));
                    goodsSku.setGoodsWeight(GoodsWeight[i]);
                    goodsSku.setCreateTime(new Date());
                    goodsSku.setUpdateTime(new Date());
                    skuMapper.insertSelective(goodsSku);
                }
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 修改商品
     *
     * @param goodsBean 商品
     * @return 结果
     */
    @Override
    public int updateGoods(GoodsBean goodsBean) {
        try {
//            //修改goods_photo表  轮播图
//            if (goodsBean.getTurnsPhotoList()!=null && goodsBean.getTurnsPhotoList().length>0) {
//                GoodsPhoto goodsPhoto = new GoodsPhoto();
//                String[] turnsPhotoList = goodsBean.getTurnsPhotoList();
//                List<Integer> list = new ArrayList<>();
//                for (int i = 0; i <turnsPhotoList.length ; i++) {
//                    if (!"".equals(turnsPhotoList[i])) {
//                        goodsPhoto.setPhotoUrl(turnsPhotoList[i]);
//                        goodsPhoto.setCreateTime(new Date());
//                        goodsPhoto.setUpdateTime(new Date());
//                        photoMapper.insert(goodsPhoto);
//                        list.add(goodsPhoto.getId());
//                    }
//                }
//                Goods good = goodsMapper.selectGoodsById(goodsBean.getId());
//                if (list.size() > 0) {
//                    StringBuffer stringBuffer = new StringBuffer();
//                    for (Integer photo : list) {
//                        stringBuffer.append("," + photo);
//                    }
//                    String photos = stringBuffer.toString().substring(1);
//                    String photo = good.getTurnsPhoto()+","+photos;
//                    Goods goods = new Goods();
////                    goods.setId(goodsBean.getId().intValue());
////                    goods.setTurnsPhoto(photo);
//                    goodsMapper.updateGoods(goods);
//                }
//            }
            Goods good = new Goods();
            String comma =goodsBean.getTurnsPhoto().substring(0,1);
            if (",".equals(comma)){
                goodsBean.setTurnsPhoto(goodsBean.getTurnsPhoto().substring(1));
            }
            good.setId(goodsBean.getId().intValue());
            good.setTurnsPhoto(goodsBean.getTurnsPhoto());
            goodsMapper.updateGoods(good);

            //修改goods表
            Goods goods = new Goods();
            goods.setId(goodsBean.getId().intValue());
            goods.setRelateId(goodsBean.getRelateId().intValue());
            goods.setTypeId(goodsBean.getParentId().intValue());
            goods.setChoiceness(goodsBean.getChoiceness());
            goods.setHotSale(goodsBean.getHotSale());
            goods.setPreorder(goodsBean.getPreorder());
            goods.setGoodsListName(goodsBean.getGoodsListName());
            goods.setGoodsName(goodsBean.getGoodsName());
            goods.setContent(goodsBean.getContent());
            goods.setSpeType(goodsBean.getSpeType());
            goods.setGiftBag(goodsBean.getGiftBag());
            goods.setPrice(BigDecimal.valueOf(goodsBean.getPrice()));
            goods.setAgencyPrice(BigDecimal.valueOf(goodsBean.getAgencyPrice()));
            goods.setBazaarPrice(BigDecimal.valueOf(goodsBean.getBazaarPrice()));
            goods.setProcurementPrice(BigDecimal.valueOf(goodsBean.getProcurementPrice()));
            goods.setLinePrice(BigDecimal.valueOf(goodsBean.getLinePrice()));
            goods.setStockNum(goodsBean.getStockNum().intValue());
            goods.setSalesInitial(goodsBean.getSalesInitial().intValue());
            goods.setStatus(goodsBean.getStatus());
            goods.setSort(goodsBean.getSort().intValue());
            goods.setActivityTitle(goodsBean.getActivityTitle());
            goodsMapper.updateGoods(goods);
//            if (StringUtils.isNotBlank(goodsBean.getSpecName())) {
//                //添加goods_sprc表
//                GoodsSpec goodsSpec = new GoodsSpec();
//                goodsSpec.setName(goodsBean.getSpecName());
//                goodsSpec.setGoodsId(goods.getId().intValue());
//                specMapper.insert(goodsSpec);
//
//                //添加goods_spec_value表
//                GoodsSpecValue specValue = new GoodsSpecValue();
//                List<Integer> specId = new ArrayList<>();
//                String[] specValues = goodsBean.getSpecValue();
//                for (String value : specValues) {
//                    specValue.setSpecId(goodsSpec.getId());
//                    specValue.setValue(value);
//                    specValue.setGoodsId(goods.getId().intValue());
//                    specValueMapper.insert(specValue);
//                    specId.add(specValue.getId());
//                }

//                //添加goods_sku表
//                GoodsPhoto goodsPhoto1 = new GoodsPhoto();
//                GoodsSku goodsSku = new GoodsSku();
//                List<Integer> photoList = new ArrayList<>();
//                String[] skuPhotos = goodsBean.getSkuPhoto();
//                for (String skuPhoto : skuPhotos) {
//                    goodsPhoto1.setPhotoUrl(skuPhoto);
//                    goodsPhoto1.setCreateTime(new Date());
//                    goodsPhoto1.setUpdateTime(new Date());
//                    photoMapper.insert(goodsPhoto1);
//                    photoList.add(goodsPhoto1.getId());
//                }
//                String[] skuId = goodsBean.getSkuId();
//                Double[] skuPrice = goodsBean.getSkuPrice();
//                Integer[] skuStockNum = goodsBean.getSkuStockNum();
//                Double[] GoodsWeight = goodsBean.getGoodsWeight();
//                for (int i = 0; i < photoList.size(); i++) {
//                    goodsSku.setSkuId(skuId[i]);
//                    goodsSku.setGoodsId(goods.getId().intValue());
//                    goodsSku.setSpecValueId(specId.get(i).toString());
//                    goodsSku.setPrice(BigDecimal.valueOf(skuPrice[i]));
//                    goodsSku.setLinePrice(BigDecimal.valueOf(goodsBean.getLinePrice()));
//                    goodsSku.setStockNum(skuStockNum[i]);
//                    goodsSku.setPhotoId(photoList.get(i));
//                    goodsSku.setGoodsWeight(GoodsWeight[i]);
//                    goodsSku.setCreateTime(new Date());
//                    goodsSku.setUpdateTime(new Date());
//                    skuMapper.insert(goodsSku);
//                }
//            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除商品对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGoodsByIds(String ids) {
        String[] split = ids.split(",");
        List<String> list = Arrays.asList(split);
        Goods goods = new Goods();
        try {
            for (int i = 0; i <list.size() ; i++) {
                Goods good = goodsMapper.selectGoodsById(Long.parseLong(list.get(i)));
                String[] photos = good.getTurnsPhoto().split(",");
                for (int j = 0; j <photos.length ; j++) {
                    photoMapper.deleteByPrimaryKey(Integer.parseInt(photos[j]));
                }
                skuMapper.deleteByPrimaryKey(Integer.parseInt(list.get(i)));
                specMapper.deleteByPrimaryKey(Integer.parseInt(list.get(i)));
                specValueMapper.deleteByPrimaryKey(Integer.parseInt(list.get(i)));
                goodsMapper.deleteGoodsById(Long.parseLong(list.get(i)));
            }
            return 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除商品信息
     *
     * @param id 商品ID
     * @return 结果
     */
    @Override
    public int deleteGoodsById(Long id) {
        return goodsMapper.deleteGoodsById(id);
    }

    @Override
    public List<GoodsType> selectParent() {
        return typeMapper.select();
    }

    @Override
    public List<GoodsType> selectByParentId(Integer parentCode) {
        return typeMapper.selectByParentId(parentCode);
    }

    @Override
    public GoodsExtent selectGoods(Long id) {
        GoodsExtent goodsExtent = beanMapper.selectGoodsById(id);
        Integer typeId = goodsMapper.selectTypeId(goodsExtent.getFirstCode());
        goodsExtent.setThreeTypeId(typeId);
        List<String> list = new ArrayList<>();
        String[] turnsPhoto = goodsExtent.getTurnsPhoto().split(",");
        for (String s : turnsPhoto) {
            if (!"".equals(s)) {
                String photo = photoMapper.selectByKey(Integer.parseInt(s));
                list.add(photo);
            }
        }
        goodsExtent.setTurnsPhotoList(list);
        return goodsExtent;
    }

    @Override
    public List<GoodsType> getThreeType(Integer threeType) {
        return typeMapper.selectByParentId(threeType);
    }

    @Override
    public List<GoodsBean> getType(Integer id, Integer typeId) {
        return beanMapper.selectType(id,typeId);
    }

    private void saveSpec(Spec spec) {
        List<SpecParam> specParams = spec.getSpecParams();
        specParams.forEach(specParam -> {
            //保存参数
            GoodsSpec goodsSpec = new GoodsSpec();
            goodsSpec.setName(specParam.getSpecName());
            goodsSpec.setCode(specParam.getSpecId());
            goodsSpec.setGoodsId(spec.getGoodId());
            GoodsSpecExample specExample = new GoodsSpecExample();
            GoodsSpecExample.Criteria specExampleCriteria = specExample.createCriteria();
            specExampleCriteria.andCodeEqualTo(goodsSpec.getCode());
            specExampleCriteria.andGoodsIdEqualTo(goodsSpec.getGoodsId());
            List<GoodsSpec> goodsSpecs = specMapper.selectByExample(specExample);
            if (goodsSpecs.size() > 0){
                goodsSpec.setId(goodsSpecs.get(0).getId());
                specMapper.updateByPrimaryKeySelective(goodsSpec);
            }else{
                specMapper.insert(goodsSpec);
            }
            List<SpecValue> values = specParam.getValues();
            values.forEach(specValue -> {
                //保存属性
                GoodsSpecValue goodsSpecValue = new GoodsSpecValue();
                goodsSpecValue.setSpecId(goodsSpec.getId());
                goodsSpecValue.setValue(specValue.getValueName());
                goodsSpecValue.setGoodsId(spec.getGoodId());
                goodsSpecValue.setCode(specValue.getValueCode());
                GoodsSpecValueExample valueExample = new GoodsSpecValueExample();
                GoodsSpecValueExample.Criteria valueExampleCriteria = valueExample.createCriteria();
                valueExampleCriteria.andSpecIdEqualTo(goodsSpec.getId());
                valueExampleCriteria.andGoodsIdEqualTo(spec.getGoodId());
                valueExampleCriteria.andCodeEqualTo(specValue.getValueCode());
                List<GoodsSpecValue> goodsSpecValues = specValueMapper.selectByExample(valueExample);
                if (goodsSpecValues.size() > 0){
                    goodsSpecValue.setId(goodsSpecValues.get(0).getId());
                    specValueMapper.updateByPrimaryKeySelective(goodsSpecValue);
                }else{
                    specValueMapper.insert(goodsSpecValue);
                }
            });
        });
    }

    @Override
    public boolean addSku(Spec spec) {
        saveSpec(spec);
        List<Specification> spec1 = spec.getSpec();

        for (Specification specification : spec1) {
            String specValues = "";//拼接sku中的规格值  _ 分割
            String propvalids = specification.getPropvalids();//获取一条Sku 的code 串
            String[] split = propvalids.split(",");
            int[] specValueId = new int[split.length];
            for (int i=0 ;i<split.length;i++) {
                GoodsSpecValueExample goodsSpecValueExample = new GoodsSpecValueExample();
                GoodsSpecValueExample.Criteria criteria = goodsSpecValueExample.createCriteria();
                criteria.andGoodsIdEqualTo(spec.getGoodId());
                criteria.andCodeEqualTo(Integer.parseInt(split[i]));
                List<GoodsSpecValue> goodsSpecValues = specValueMapper.selectByExample(goodsSpecValueExample);
                Integer id = goodsSpecValues.get(0).getId();
                specValueId[i] = id;

                //specValues += "_" + s1;
            }
            Arrays.sort(specValueId);
            for (int i : specValueId) {
                specValues += "_"+i;
            }
            //添加商品图片表
            GoodsPhoto goodsPhoto = new GoodsPhoto();
            goodsPhoto.setPhotoUrl(specification.getPhoto());
            goodsPhoto.setCreateTime(DateUtils.getNowDate());
            goodsPhoto.setUpdateTime(DateUtils.getNowDate());
            photoMapper.insertSelective(goodsPhoto);
            //截取规格值 1_3_5
            String substring = specValues.substring(1);
            GoodsSku goodsSku = new GoodsSku();
            goodsSku.setCreateTime(DateUtils.getNowDate());
            goodsSku.setGoodsWeight(Double.parseDouble(specification.getGoodsWeight()));
            goodsSku.setPrice(BigDecimal.valueOf(Double.parseDouble(specification.getSkuPrice())));
            goodsSku.setStockNum(Integer.parseInt(specification.getSkuStock()));
            goodsSku.setSkuId(specification.getSkuId());
            goodsSku.setSpecValueId(substring);
            goodsSku.setUpdateTime(DateUtils.getNowDate());
            goodsSku.setGoodsId(spec.getGoodId());
            goodsSku.setPhotoId(goodsPhoto.getId());
            //采购价
            if(specification.getProcurementPrice() == null || specification.getProcurementPrice() == ""){
                goodsSku.setPurchasePrice(null);
            }else {
                goodsSku.setPurchasePrice(BigDecimal.valueOf(Double.parseDouble(specification.getProcurementPrice())));
            }
            //e卡价
            if(specification.getProcurementEPrice() == null || specification.getProcurementEPrice() == ""){
                goodsSku.setePrice(null);
            }else {
                goodsSku.setePrice(BigDecimal.valueOf(Double.parseDouble(specification.getProcurementEPrice())));
            }
            //金卡价
            if(specification.getProcurementGlodPrice() == null || specification.getProcurementGlodPrice() == ""){
                goodsSku.setGlodPrice(null);
            }else {
                goodsSku.setGlodPrice(BigDecimal.valueOf(Double.parseDouble(specification.getProcurementGlodPrice())));

            }
            //代理价
            if(specification.getAgentPrice() == null || specification.getAgentPrice() == ""){
                goodsSku.setAgentPrice(null);
            }else {
                goodsSku.setAgentPrice(BigDecimal.valueOf(Double.parseDouble(specification.getAgentPrice())));
            }
            //查看记录是否已有
            GoodsSku good=selectSpecMapper.selectSku(goodsSku);
            if(good != null){
                goodsSku.setId(good.getId());
                int i = skuMapper.updateByPrimaryKey(goodsSku);
            }else {
                skuMapper.insertSelective(goodsSku);
            }
        }
        return true;
    }

    @Override
    public List<SpecSelect> specSelect(Integer id) {
        List<SpecSelect> specSelect = selectSpecMapper.selectSpec(id);
        if (specSelect.size() > 0) {
            for (SpecSelect select : specSelect) {
                if(select.getCode() != null && select.getTextCode() != null){
                    String[] codes = select.getCode().split(",");
                    String[] names = select.getTextCode().split(",");
                    List<SpecSelectValue> values = new ArrayList<>();
                    for (int i = 0; i < codes.length; i++) {
                        SpecSelectValue value = new SpecSelectValue();
                        value.setCode(codes[i]);
                        value.setName(names[i]);
                        values.add(value);
                    }
                    Collections.sort(values);
                    select.setValues(values);
                }
            }
            return specSelect;
        }
        return null;
    }

    @Override
    public List<GoodsSkuBean> querySku(String goodsId) {
        List<GoodsSkuBean> list = new ArrayList<>();
        List<GoodsSku> skus = skuBeanMapper.selectSku(goodsId);
        skus.forEach(goodsSku -> {
            GoodsPhoto goodsPhoto = photoMapper.selectByPrimaryKey(goodsSku.getPhotoId());
            GoodsSkuBean goodsSkuBean = new GoodsSkuBean();
            goodsSkuBean.setId(goodsSku.getId());
            goodsSkuBean.setSkuId(goodsSku.getSkuId());
            goodsSkuBean.setPrice(goodsSku.getPrice());
            goodsSkuBean.setePrice(goodsSku.getePrice());
            goodsSkuBean.setGlodPrice(goodsSku.getGlodPrice());
            goodsSkuBean.setAgentPrice(goodsSku.getAgentPrice());
            goodsSkuBean.setLinePrice(goodsSku.getLinePrice());
            goodsSkuBean.setStockNum(goodsSku.getStockNum());
            if (goodsPhoto!=null) {
                goodsSkuBean.setPhoto(goodsPhoto.getPhotoUrl());
            }
            goodsSkuBean.setGoodsSales(goodsSku.getGoodsSales());
            goodsSkuBean.setGoodsWeight(goodsSku.getGoodsWeight());
            goodsSkuBean.setPurchasePrice(goodsSku.getPurchasePrice());
            List<GoodsSkuBean> values = new ArrayList<>();
            String[] specValueId = goodsSku.getSpecValueId().split("_");
            for (String specValue : specValueId) {
                GoodsSpecValue specValues = specValueMapper.selectById(specValue);
                if (specValues!=null){
                    GoodsSpec goodsSpec = specMapper.selectByPrimaryKey(specValues.getSpecId());
                    GoodsSkuBean value = new GoodsSkuBean();
                    value.setSpecName(goodsSpec.getName());
                    value.setSpecValue(specValues.getValue());
                    values.add(value);
                }
            }
            goodsSkuBean.setValues(values);
            list.add(goodsSkuBean);
        });

//        for (int i = 0; i <skus.size() ; i++) {
//            GoodsPhoto goodsPhoto = photoMapper.selectByPrimaryKey(skus.get(i).getPhotoId());
//            String[] specValueId = skus.get(i).getSpecValueId().split("_");
//            for (int j = 0; j <specValueId.length ; j++) {
//                List<GoodsSpecValue> specValues = specValueMapper.selectById(specValueId[j]);
//                for (GoodsSpecValue specValue : specValues) {
//                    GoodsSpec goodsSpec = specMapper.selectByPrimaryKey(specValue.getSpecId());
//                    goodsSkuBean.setId(skus.get(i).getId());
//                    goodsSkuBean.setSkuId(skus.get(i).getSkuId());
//                    goodsSkuBean.setSpecName(goodsSpec.getName());
//                    goodsSkuBean.setSpecValue(specValues.get(j).getValue());
//                    goodsSkuBean.setPrice(skus.get(i).getPrice());
//                    goodsSkuBean.setAgentPrice(skus.get(i).getAgentPrice());
//                    goodsSkuBean.setLinePrice(skus.get(i).getLinePrice());
//                    goodsSkuBean.setStockNum(skus.get(i).getStockNum());
//                    goodsSkuBean.setPhoto(goodsPhoto.getPhotoUrl());
//                    goodsSkuBean.setGoodsSales(skus.get(i).getGoodsSales());
//                    goodsSkuBean.setGoodsWeight(skus.get(i).getGoodsWeight());
//                    skuBeans.add(goodsSkuBean);
//                }
//            }
//        }
        
        return list;
    }

    @Override
    public GoodsSku selectMaxSpec(Integer id) {
        return selectSpecMapper.selectSkuMaxSpec(id);
    }
    @Override
    public GoodsSku selectMaxSpecValue(Integer id) {
        return selectSpecMapper.selectSkuMaxSpecValue(id);
    }

    @Override
    public int insertParameter(Integer id,String[] attributeName, String[] attributeValue) {
        List<Map> list = new ArrayList<>();
        for (int i = 0; i <attributeName.length ; i++) {
            Map<String,String> map = new HashMap<>();
            map.put("key",attributeName[i]);
            map.put("value",attributeValue[i]);
            list.add(map);
        }
        Goods goods = new Goods();
        goods.setId(id);
        goods.setParameter(JSON.toJSONString(list));
        int i = goodsMapper.updateGoods(goods);
        return i;
    }

    @Override
    public String selectParameter(String id) {
        return goodsMapper.selectParameter(id);
    }

    /**
     * 删除规格
     * @param id
     * @return
     */
    @Override
    public int deleteSku(Integer id) {
        try {
//            GoodsSku goodsSku = skuMapper.selectByPrimaryKey(id);
//            if (goodsSku.getPhotoId()!=null && goodsSku.getPhotoId()!=0) {
//                photoMapper.deleteByPrimaryKey(goodsSku.getPhotoId());
//            }
            skuMapper.deleteById(id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
