package com.xinmintx.factory.service.impl;
import com.google.common.collect.Lists;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.exception.BaseRunException;
import com.xinmintx.factory.exception.errorCodeStants.ErrorCodeConStants;
import com.xinmintx.factory.mapper.GoodsSkuMapper;
import com.xinmintx.factory.mapper.po.GoodsMapper;
import com.xinmintx.factory.mapper.VenderMapper;
import com.xinmintx.factory.mapper.po.GoodsPhotoMapper;
import com.xinmintx.factory.mapper.po.GoodsSpecMapper;
import com.xinmintx.factory.mapper.po.GoodsSpecValueMapper;
import com.xinmintx.factory.model.Factory;
import com.xinmintx.factory.model.GoodsSku;
import com.xinmintx.factory.model.bo.Good;
import com.xinmintx.factory.model.bo.GoodsSkuBo;
import com.xinmintx.factory.model.po.Goods;
import com.xinmintx.factory.model.po.GoodsPhoto;
import com.xinmintx.factory.model.po.GoodsSpec;
import com.xinmintx.factory.model.po.GoodsSpecValue;
import com.xinmintx.factory.model.vo.GoodsSkuVo;
import com.xinmintx.factory.model.vo.GoodsSpecValueVo;
import com.xinmintx.factory.model.vo.GoodsTreeVo;
import com.xinmintx.factory.service.GoodsService;
import com.xinmintx.factory.util.BeanUtil;
import com.xinmintx.factory.util.FieldUtils;
import com.xinmintx.factory.util.File2OSSUtils;
import com.xinmintx.factory.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private File2OSSUtils file2OSSUtils;

    @Autowired
    private VenderMapper venderMapper;

    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    @Autowired
    private GoodsSpecValueMapper goodsSpecValueMapper;

    @Autowired
    private GoodsSpecMapper goodsSpecMapper;

    @Autowired
    private GoodsPhotoMapper goodsPhotoMapper;

    //过滤登录信息
    private Factory verifyToken(String token) {
        if (null == token || "".equals(token.trim())) {
            throw new BaseRunException(ErrorCodeConStants.SYSTEM_ABNORMAL_TOKEN);
        }
        Factory factory = venderMapper.queryByToken(token);
        if (null == factory || 0 == factory.getFactoryId()) {
            throw new BaseRunException(ErrorCodeConStants.SYSTEM_ABNORMAL_TOKEN);
        }
        return factory;
    }

    @Override
    public Page<Goods> queryGoodsByFactoryId(String token, Integer pagination , Integer numberOfPages) {

        Factory factory = verifyToken(token);
        //过滤参数信息
        if (null == pagination || pagination == 0) {
            pagination= 5;
        }
        int Index = 0;
        if (null != numberOfPages) {
            Index = numberOfPages;
        }

/*        List<Goods> goodsList = new LambdaQueryChainWrapper<>(goodsMapper)
                .eq(Goods::getRelateId, factory.getFactoryId())
                .eq(Goods::getSource,1)
                .last("LIMIT "+(Index-1)*page.getPagination()+","+page.getPagination())
                .list();
        return goodsList;*/

        //分页查询
        Goods goods = new Goods();
        QueryWrapper<Goods> goodsQueryWrapperoods = new QueryWrapper<>();
        goodsQueryWrapperoods.eq("source", 1);
        goodsQueryWrapperoods.eq("is_delete", 0);
        goodsQueryWrapperoods.eq("relate_id", factory.getFactoryId());
        Page<Goods> queryPage = new Page<>(Index, pagination);
        Page<Goods> goodsPage = goods.selectPage(queryPage, goodsQueryWrapperoods);
        for (int i = 0; i < goodsPage.getRecords().size(); i++) {
            String[] array = goodsPage.getRecords().get(i).getTurnsPhoto().split(",");
            if (array.length == 0) {
                goodsPage.getRecords().get(i).setTurnsPhoto("");
            } else {
                GoodsPhoto goodsPhoto = new LambdaQueryChainWrapper<>(goodsPhotoMapper)
                        .eq(GoodsPhoto::getId, array[0])
                        .one();
                goodsPage.getRecords().get(i).setTurnsPhoto(goodsPhoto.getPhotoUrl());
            }

        }
        return goodsPage;
    }

    @Override
    public ResultCode imageUpload(String token, MultipartFile[] files) {
        ResultCode resultCode = new ResultCode();
        if (files != null && files.length > 0) {
            List<String> urlList = new ArrayList<>();
            for (MultipartFile multipartFile : files) {
                String fileUrl = file2OSSUtils.fileUploadOSS(multipartFile);
                urlList.add(fileUrl);
            }
            resultCode.setCode(200);
            resultCode.setData(urlList);
        } else {
            resultCode.setCode(500);
            resultCode.setData("未选择图片");
        }
        return resultCode;
    }



    @Override
    public ResultCode deleteGoodsById(String token, Integer goodsId) {
        Factory factory = verifyToken(token);
        ResultCode resultCode = new ResultCode();
        Goods good = new Goods();
        good.setRelateId(factory.getFactoryId().intValue());
        Goods one = new LambdaQueryChainWrapper<>(goodsMapper)
                .eq(Goods::getRelateId, factory.getFactoryId().intValue())
                .eq(Goods::getId, goodsId)
                .one();
        if (one != null) {
            //判断有没有删除过
            if (one.getIsDelete() == 1) {
                resultCode.setCode(200);
                resultCode.setMsg("已删除");
            } else {
                good.setId(one.getId());
                good.setIsDelete(1);
                goodsMapper.updateById(good);
                resultCode.setCode(200);
                resultCode.setMsg("删除成功");
            }
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("删除失败");
        }
        return resultCode;
    }

    @Override
    public GoodsSkuVo getMemberSkuByGoodsId(Integer id, String token) {
        Factory factory = verifyToken(token);
        GoodsSkuVo goodsSkuVo = getSkuById(id, BigDecimal.ZERO);
        return goodsSkuVo;
    }

    //添加goodsSku goodsPhoto spec  spec_value
    public void insertSku(GoodsSkuVo goodsSkuVo,int goodsId) {
        List<GoodsSkuBo> list = goodsSkuVo.getList();
        //添加spec
        List<GoodsTreeVo> tree = goodsSkuVo.getTree();
        if (tree != null) {
            //根据k_s分组的规格值
            Map<String, List<Map<String, GoodsSpecValue>>> specMap = new HashMap<>();
            int i = 2;
            tree.forEach(goodsTreeVo -> {
                //当前k_s下的全部规格值
                List<Map<String, GoodsSpecValue>> goodsSpecValues = new ArrayList<>();
                GoodsSpec goodsSpec = new GoodsSpec();
                goodsSpec.setName(goodsTreeVo.getK());
                goodsSpec.setGoodsId(goodsId);
                goodsSpec.setCode(String.valueOf(i));
                //1.保存全部的规格组
                goodsSpec.insert();
                List<GoodsSpecValueVo> v = goodsTreeVo.getV();
                v.forEach(goodsSpecValueVo -> {
                    GoodsSpecValue goodsSpecValue = new GoodsSpecValue();
                    goodsSpecValue.setSpecId(goodsSpec.getId());
                    goodsSpecValue.setCode(i);
                    goodsSpecValue.setGoodsId(goodsId);
                    goodsSpecValue.setValue(goodsSpecValueVo.getName());
                    //2.保存全部的规格值
                    goodsSpecValue.insert();
                    //key为前端id的规格值
                    Map<String, GoodsSpecValue> specValueMap = new HashMap<>();
                    specValueMap.put(String.valueOf(goodsSpecValueVo.getId()), goodsSpecValue);
                    goodsSpecValues.add(specValueMap);
                });
                specMap.put(goodsTreeVo.getK_s(), goodsSpecValues);
            });
            List<GoodsSkuBo> goodsSkuBos = goodsSkuVo.getList();
            goodsSkuBos.forEach(goodsSkuBo -> {
                goodsSkuBo.setGoodsId(goodsId);
                saveGoodsSkuId(goodsSkuBo, specMap,goodsId);
            });
        }
        //更新图片表
        if (list!=null && list.size()>0) {
            for (int t = 0; t < list.size(); t++) {
                GoodsPhoto goodsPhoto = new GoodsPhoto();
                goodsPhoto.setPhotoUrl(list.get(t).getPhotoUrl());
                goodsPhoto.setCreateTime(new Date());
                goodsPhoto.insert();
                GoodsSku goodsSku = BeanUtil.copyProperties(list.get(t), GoodsSku.class);
                goodsSku.setPhotoId(goodsPhoto.getId());
            }
            for (int k = 0; k < list.size(); k++) {
                GoodsSku goodsSku = BeanUtil.copyProperties(list.get(k), GoodsSku.class, false);
                goodsSku.insert();
            }
        }
    }
    /**
     * 保存sku组合
     *
     * @param goodsSkuBo
     * @param specMap
     */
    private void saveGoodsSkuId(GoodsSkuBo goodsSkuBo, Map<String, List<Map<String, GoodsSpecValue>>> specMap,int goodId) {
        List<Integer> specIds = new ArrayList<>();
        List<String> specValues = new ArrayList<>();
        specMap.forEach((key, value) -> {
            Object name = FieldUtils.getFieldsValueByName(goodsSkuBo, key);
            if (name != null) {
                String id = name.toString();
                value.forEach(specValueMap -> {
                    if (specValueMap.containsKey(id)) {
                        GoodsSpecValue goodsSpecValue = specValueMap.get(id);
                        specIds.add(goodsSpecValue.getId());
                        specValues.add(goodsSpecValue.getValue());
                    }
                });
            }
        });
        GoodsSku goodsSku = FieldUtils.fieldTrans(goodsSkuBo, GoodsSku.class);
        goodsSku.setSpecValueId(specIds.toString());
        goodsSku.setGoodsId(goodId);
        goodsSku.insert();
    }

    @Override
    public ResultCode updateSku(String token, GoodsSkuVo goodsSkuVo) {
        List<GoodsSkuBo> list = goodsSkuVo.getList();
        //验证token是否存在
        verifyToken(token);
        GoodsPhoto goodsPhoto = new GoodsPhoto();
        ResultCode resultCode = new ResultCode();
        //更新图片表
        if (list!=null && list.size()>0) {
            boolean b = false;
            for (int i = 0; i < list.size(); i++) {
                goodsPhoto.setId(list.get(i).getPhotoId());
                goodsPhoto.setPhotoUrl(list.get(i).getPhotoUrl());
                goodsPhoto.setUpdateTime(new Date());
                 b = goodsPhoto.insertOrUpdate();
            }
            for (int i = 0; i < list.size(); i++) {
                GoodsSku goodsSku = BeanUtil.copyProperties(list.get(i), GoodsSku.class, false);
                 b = goodsSku.insertOrUpdate();
            }
            if (b) {
                resultCode.setCode(200);
                resultCode.setMsg("修改成功");
            }else {
                resultCode.setCode(500);
                resultCode.setMsg("修改失败");
            }
        }else {
            resultCode.setCode(500);
        }
        return resultCode;
    }

    @Override
    public ResultCode insertGood(String token, Good good) {
        //过滤登录信息
        Factory factory = verifyToken(token);
        ResultCode resultCode = new ResultCode();
        //添加商品基本信息 goods表
        Goods goods = new Goods();
        goods.setSource(1);
        goods.setRelateId(factory.getFactoryId().intValue());
        goods.setTypeId(good.getProductCategoryId());
        goods.setGoodsListName(good.getName());
        goods.setGoodsName(good.getName());
        goods.setContent(good.getDetailHtml());
        //图片  串
        goods.setTurnsPhoto(goodPhoto(good.getPic()));
        goods.setSpeType(2);
        goods.setPrice(good.getPrice());
        goods.setLinePrice(good.getOriginalPrice());
        goods.setStockNum(1);
        goods.setProcurementPrice(good.getTurePrice());
        goods.setSalesInitial(good.getNewSalesvolume());
        goods.setActivityTitle(good.getSubTitle());
        goods.setStatus(1);
        goods.setIsDelete(0);
        goods.setCreateTime(new Date());
        goods.setUpdateTime(new Date());
        //参数
        List<Map<String, String>> shopCanshu = good.getShopCanshu();
        goods.setParameter(JSON.toJSONString(shopCanshu));
        goods.setFactoryStart(good.getPublishStatus());
        boolean a = goods.insert();
        //添加goodsSku
        if (good.getGoodsSkuVo().getList()!=null) {
            insertSku(good.getGoodsSkuVo(),goods.getId());
        }

        return resultCode;
    }

    @Override
    public ResultCode selectGoodsAndSku(String token, Integer goodsId) {
        ResultCode resultCode = new ResultCode();
        //过滤登录信息
        Factory factory = verifyToken(token);
        //判断商品是否属于该厂家
        Goods one = new LambdaQueryChainWrapper<>(goodsMapper)
                .eq(Goods::getRelateId, factory.getFactoryId().intValue())
                .eq(Goods::getId, goodsId)
                .one();
        if(one != null){
            //goods 基本信息
            Goods goods = goodsMapper.selectById(goodsId);
            //goodsSku 商品
            GoodsSkuVo goodsSkuVo = getMemberSkuByGoodsId(goodsId, token);
            Good good = new Good();
            good.setProductCategoryId(goods.getTypeId());
            good.setName(goods.getGoodsListName());
            good.setSubTitle(goods.getActivityTitle());
            //图片处理
            good.setPic(Lists.newArrayList(goodPhoto(goods.getTurnsPhoto())));
            good.setPublishStatus(goods.getFactoryStart());
            good.setNewSalesvolume(goods.getSalesInitial());
            good.setPrice(goods.getPrice());
            good.setOriginalPrice(goods.getLinePrice());
            good.setTurePrice(goods.getProcurementPrice());
            good.setDetailHtml(goods.getContent());
            good.setShopCanshu(JSON.parseObject(goods.getParameter(), List.class));
            good.setGoodsSkuVo(goodsSkuVo);
            resultCode.setData(good);
        }else {
            resultCode.setCode(500);
            resultCode.setMsg("error");
        }
        return resultCode;
    }
    //回显商品轮播图处理
    public String goodPhoto(String pic){
        String[] split = pic.split(",");
        String str = "";
        for (String s :split){
            GoodsPhoto goodsPhoto = goodsPhotoMapper.selectById(s);
            str += ","+goodsPhoto.getPhotoUrl();
        }
        return str.substring(1);
    }

    //添加商品时 轮播图处理
    public String goodPhoto(List<String> pic){
        String str = "";
        for (String p : pic){
            GoodsPhoto goodsPhoto = new GoodsPhoto();
            goodsPhoto.setPhotoUrl(p);
            goodsPhoto.setCreateTime(new Date());
            goodsPhoto.setUpdateTime(new Date());
            goodsPhoto.insert();
            str +=","+goodsPhoto.getId();
        }
        return str.substring(1);
    }
    @Override
    public void updateGoodsStartByGoodsId(String token, Integer status, Integer goodsId) {

        //过滤登录信息
        Factory factory = verifyToken(token);

        //查询商品是否存在
        Goods goods = new LambdaQueryChainWrapper<>(goodsMapper)
                .eq(Goods::getId, goodsId)
                .eq(Goods::getSource, 1)
                .eq(Goods::getRelateId, factory.getFactoryId())
                .one();
        if (null == goods || goods.getId() == null) {
            throw new BaseRunException(ErrorCodeConStants.SYSTEM_OBJECT_ALREADY_NONENTITY);
        }

        //参数处理
        int Index = 0;
        if (null != status && 0 != status) {
            Index = status;
        }

        if (0 == goods.getStatus() && Index == 1) {
            throw new BaseRunException(ErrorCodeConStants.SYSTEM_GOODS_STATUS_ERR);
        }
        if (Index == goods.getFactoryStart()) {
            throw new BaseRunException(ErrorCodeConStants.SYSTEM_RESULT_NULL);
        }

/*        //修改状态
        goods.setStatus(Index);
        int updateIndex = goodsMapper.update(goods,
                new LambdaUpdateChainWrapper<>(goodsMapper)
                        .eq(Goods::getId, goods.getId().toString())
        );*/

        //修改状态
        Goods goodsNew = new Goods();
        goodsNew.setId(goodsId);
        goodsNew.setFactoryStart(Index);
        int updateIndex = goodsMapper.updateById(goodsNew);

        //执行结果判断
        if (updateIndex < 1) {
            throw new BaseRunException(ErrorCodeConStants.SYSTEM_RESULT_NULL);
        }

    }

    public GoodsSkuVo getSkuById(Integer id, BigDecimal discountsPrice) {
        //1.获取该商品的sku集合
        List<GoodsSku> goodsSkus = new LambdaQueryChainWrapper<>(goodsSkuMapper)
                .eq(GoodsSku::getGoodsId, id)
                .list();
        if (goodsSkus == null || goodsSkus.size() < 1) {
            return null;
        }
        Set<String> specValueIds = new HashSet<>();
        List<GoodsSkuBo> goodsSkuBos = ListUtils.listTrans(goodsSkus, GoodsSkuBo.class);
        for (GoodsSkuBo goodsSkuBo : goodsSkuBos) {
            String specValueId = goodsSkuBo.getSpecValueId();
            String[] specValueIdArr = specValueId.split("_");
            specValueIds.addAll(Arrays.asList(specValueIdArr));
            BigDecimal price = goodsSkuBo.getPrice();
            price = price.subtract(discountsPrice);
            goodsSkuBo.setPrice(price);
            goodsSkuBo.setPhotoUrl(goodsPhotoMapper.selectById(goodsSkuBo.getPhotoId()).getPhotoUrl());
            goodsSkuBo.setSpecValueIds(specValueIdArr);
        }
        List<GoodsSpecValue> goodsSpecValues = goodsSpecValueMapper.selectBatchIds(specValueIds);
        Map<String, List<GoodsSpecValue>> listMap = ListUtils.listGroup(goodsSpecValues, "specId");
        List<GoodsTreeVo> goodsTreeVos = new ArrayList<>();
        final int[] i = {1};
        listMap.forEach((key, value) -> {
            GoodsSpec goodsSpec = goodsSpecMapper.selectById(Integer.parseInt(key));
            GoodsTreeVo goodsTreeVo = new GoodsTreeVo();
            goodsTreeVo.setK(goodsSpec.getName());
            goodsTreeVo.setK_s("s" + i[0]);
            List<GoodsSpecValueVo> goodsSpecValueVos = new ArrayList<>();
            for (GoodsSpecValue goodsSpecValue : value) {
                GoodsSpecValueVo goodsSpecValueVo = new GoodsSpecValueVo();
                goodsSpecValueVo.setId(goodsSpecValue.getId());
                goodsSpecValueVo.setName(goodsSpecValue.getValue());
                goodsSpecValueVos.add(goodsSpecValueVo);
            }
            goodsTreeVo.setV(goodsSpecValueVos);
            goodsTreeVos.add(goodsTreeVo);
            i[0]++;
        });
        for (GoodsSkuBo goodsSkuBo : goodsSkuBos) {
            String[] valueIds = goodsSkuBo.getSpecValueIds();
            for (String valueId : valueIds) {
                for (GoodsTreeVo goodsTreeVo : goodsTreeVos) {
                    List<GoodsSpecValueVo> goodsSpecValueVos = goodsTreeVo.getV();
                    for (GoodsSpecValueVo goodsSpecValueVo : goodsSpecValueVos) {
                        String specValueId = String.valueOf(goodsSpecValueVo.getId());
                        if (valueId.equals(specValueId)) {
                            String k_s = goodsTreeVo.getK_s();
                            try {
                                Field field = goodsSkuBo.getClass().getDeclaredField(k_s);
                                field.setAccessible(true);
                                field.set(goodsSkuBo, valueId);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        GoodsSkuVo goodsSkuVo = new GoodsSkuVo();
        goodsSkuVo.setTree(goodsTreeVos);
        goodsSkuVo.setList(goodsSkuBos);
        return goodsSkuVo;
    }
}
