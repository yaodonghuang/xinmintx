package com.xinmintx.hstx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.bo.GoodsSkuBo;
import com.xinmintx.hstx.pojo.entity.MemberGoodsPricePercent;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.*;
import com.xinmintx.hstx.service.GoodsService;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.util.DateUtil;
import com.xinmintx.hstx.util.FieldUtils;
import com.xinmintx.hstx.util.ListUtils;
import com.xinmintx.hstx.util.MapUtil;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsPhotoMapper photoMapper;
    @Resource
    private GoodsTypeMapper goodsTypeMapper;
    @Resource
    private GoodsFeaturedFirstMapper featuredFirstMapper;
    @Resource
    private GoodPtgoodMapper goodPtgoodMapper;
    @Resource
    private GoodsPtUserMapper goodsPtUserMapper;
    @Resource
    private GoodsSkuMapper goodsSkuMapper;
    @Resource
    private GoodsPhotoMapper getPhotoMapper;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private GoodsPriceTypeMapper goodsPriceTypeMapper;
    @Autowired
    private GoodsSpecMapper goodsSpecMapper;
    @Autowired
    private GoodsSpecValueMapper goodsSpecValueMapper;
    @Autowired
    private GoodsPhotoMapper goodsPhotoMapper;
    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    private GoodPanicBuyMapper goodPanicBuyMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private GoodPanicBuyMemberMapper goodPanicBuyMemberMapper;
    @Autowired
    private GoodsPublicMapper goodsPublicMapper;
    @Autowired
    private MemberBenefitMapper memberBenefitMapper;

    @Override
    public List<GoodsTypeVo> getGoodsTypes() {
        QueryWrapper<GoodsType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", 1);
        queryWrapper.orderByAsc("order_num");
        List<GoodsType> types = goodsTypeMapper.selectList(queryWrapper);
        return ListUtils.listTrans(types, GoodsTypeVo.class);
//        Map<String, List<GoodsTypeVo>> listMap = ListUtils.listGroup(goodsTypeVos, "level");
//        List<GoodsTypeVo> firsts = listMap.get("1");
//        List<GoodsTypeVo> seconds = listMap.get("2");
//        List<GoodsTypeVo> thirds = listMap.get("3");
//        firsts.forEach(first -> {
//            Integer firstId = first.getId();
//            List<GoodsTypeVo> secondValues = new ArrayList<>();
//            seconds.forEach(second -> {
//                Integer secondParentId = second.getParentId();
//                if (firstId.equals(secondParentId)) {
//                    secondValues.add(second);
//                    first.setTypes(secondValues);
//                }
//                Integer secondId = second.getId();
//                List<GoodsTypeVo> thirdValues = new ArrayList<>();
//                thirds.forEach(third -> {
//                    Integer thirdParentId = third.getParentId();
//                    if (secondId.equals(thirdParentId)) {
//                        thirdValues.add(third);
//                        second.setTypes(thirdValues);
//                    }
//                });
//            });
//        });
//        return firsts;
    }

    @Override
    public List<GoodsVo> getGoodsByType(Integer id, Integer price, Integer sales, PageData pageData) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0);
        queryWrapper.eq("status", 1);
        queryWrapper.eq("type_id", id);
        queryWrapper.eq("gift_bag", 0);
        if (price != null && price != 0) {
            if (price > 0) {
                queryWrapper.orderByDesc("price");
            } else {
                queryWrapper.orderByAsc("price");
            }
        }
        if (sales != null && sales != 0) {
            if (sales > 0) {
                queryWrapper.orderByDesc("sales_initial + sales_actual");
            } else {
                queryWrapper.orderByAsc("sales_initial + sales_actual");
            }
        }
        Page<Goods> goodsPage = goodsMapper.selectPage(new Page<>(pageData.getPageIndex(), pageData.getPageSize()), queryWrapper);
        return getGoods(goodsPage.getRecords());
    }

    @Override
    public List<Map<String, Object>> getGoodsFeaturedFirst() {
        List<GoodsFeaturedFirst> featuredFirsts = featuredFirstMapper.selectByMap(null);
        List<Integer> ids = new ArrayList<>();
        if (featuredFirsts.size() < 1) {
            return null;
        }
        featuredFirsts.forEach(goodsFeaturedFirst -> {
            ids.add(goodsFeaturedFirst.getGoodsId());
        });
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gift_bag", 0);
        queryWrapper.in("id", ids);
        List<Goods> goods = goodsMapper.selectList(queryWrapper);
        List<GoodsVo> goodsList = getGoods(goods);
        Map<String, List<GoodsFeaturedFirst>> listGroup = ListUtils.listGroup(featuredFirsts, "place");
        Map<String, List<GoodsFeaturedFirst>> sortMapByKey = MapUtil.orderByAsc(listGroup);
        List<Map<String, Object>> listMap = new ArrayList<>();
        AtomicInteger i = new AtomicInteger(1);
        sortMapByKey.forEach((key, value) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("place", i.get());
            List<GoodsVo> list = new ArrayList<>();
            value.forEach(goodsFeaturedFirst -> {
                goodsList.forEach(good -> {
                    if (goodsFeaturedFirst.getGoodsId().equals(good.getId())) {
                        list.add(good);
                    }
                });
            });
            i.getAndIncrement();
            map.put("goods", list);
            listMap.add(map);
        });
        return listMap;
    }

    /**
     * 获取拼团商品
     *
     * @return
     */
    @Override
    public List<GoodsVo> getPtGoods(PageData pageData) {
        QueryWrapper<GoodPtgood> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_sale", 1);
        queryWrapper.ge("end_time", new Date());
        queryWrapper.eq("group_type", 2);
        List<GoodPtgood> goodPtgoods = goodPtgoodMapper.selectList(queryWrapper);
        Set<Integer> set = new HashSet<>();
        if (goodPtgoods.size() > 0) {
            for (GoodPtgood goodPtgood : goodPtgoods) {
                set.add(goodPtgood.getGoodsId());
            }
        }
        if (set.size() < 1) {
            return new ArrayList<>();
        }
        QueryWrapper<Goods> goodsQueryWrapper = new QueryWrapper<>();
        goodsQueryWrapper.in("id", set);
        goodsQueryWrapper.eq("gift_bag", 0);
        Page<Goods> goodsPage = goodsMapper.selectPage(new Page<>(pageData.getPageIndex(), pageData.getPageSize()), goodsQueryWrapper);
        List<Goods> goods = goodsPage.getRecords();
        for (Goods good : goods) {
            List<GoodPtgood> list = new LambdaQueryChainWrapper<>(goodPtgoodMapper)
                    .eq(GoodPtgood::getGoodsId, good.getId())
                    .orderByDesc(GoodPtgood::getPtPrice).list();
            if (list != null && list.size() > 0) {
                GoodPtgood goodPtgood = list.get(0);
                good.setActivityTitle(goodPtgood.getNameActivity());
                good.setPrice(good.getPrice().subtract(goodPtgood.getPtPrice()));
            }
        }
        return getGoods(goods);
    }

    /**
     * 按条件获取商品
     *
     * @param name
     * @param goodsType 1.今日精选,2热销,3.预购
     * @return
     */
    @Override
    public List<GoodsVo> getGoodList(String name, Integer goodsType, PageData pageData) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("goods_name", name);
        }
        queryWrapper.eq("is_delete", 0);
        queryWrapper.eq("status", 1);
        queryWrapper.eq("gift_bag", 0);
        switch (goodsType) {
            case 1:
                queryWrapper.eq("choiceness", 1);
                break;
            case 2:
                queryWrapper.eq("hot_sale", 1);
                queryWrapper.orderByDesc("sales_initial + sales_actual");
                break;
            default:
                break;
        }
        Page<Goods> goodsPage = goodsMapper.selectPage(new Page<>(pageData.getPageIndex(), pageData.getPageSize()), queryWrapper);
        return getGoods(goodsPage.getRecords());
    }

    /**
     * 获取抢购商品列表
     *
     * @param pageData
     * @return
     */
    @Override
    public List<GoodsVo> getGoodPanicBuy(PageData pageData, String token) {
        Member member = null;
        if (StringUtils.isNotBlank(token)) {
            member = memberService.findMemberByToken(token);
        }
        Page<GoodPanicBuy> page = new LambdaQueryChainWrapper<>(goodPanicBuyMapper)
                .eq(GoodPanicBuy::getStatus, 1)
                .eq(GoodPanicBuy::getIsSale, 1)
                .lt(GoodPanicBuy::getStartTime, new Date())
                .gt(GoodPanicBuy::getEndTime, new Date())
                .page(new Page<>(pageData.getPageIndex(), pageData.getPageSize()));
        List<GoodPanicBuy> goodPanicBuys = page.getRecords();
        List<GoodsVo> goodsList = new ArrayList<>();
        for (GoodPanicBuy goodPanicBuy : goodPanicBuys) {
            GoodsVo goodsVo = getGoodByPanicId(goodPanicBuy, member);
            if (goodsVo != null) {
                goodsList.add(goodsVo);
            }
        }
        return goodsList;
    }

    private List<GoodsVo> getGoods(List<Goods> goods) {
        if (goods.size() < 1) {
            return new ArrayList<>();
        }
        List<GoodsVo> goodsVos = ListUtils.listTrans(goods, GoodsVo.class);
        goodsVos.forEach(good -> {
            String turnsPhoto = good.getTurnsPhoto();
            Integer photoId = Integer.parseInt(turnsPhoto.split(",")[0]);
            GoodsPhoto goodsPhoto = photoMapper.selectById(photoId);
            good.setTurnsPhoto(goodsPhoto.getPhotoUrl());
            String parameter = good.getParameter();
            good.setParameterList(JSONObject.parseObject(parameter, List.class));
            good.setSales(good.getSalesInitial() + good.getSalesActual());
        });
        return goodsVos;
    }

    @Override
    public GoodsVo getGoodById(Integer id, String token) {
        Goods goods = goodsMapper.selectById(id);
        if (goods == null || goods.getStatus() == null || goods.getStatus() == 0 || goods.getIsDelete() == null || goods.getIsDelete() == 1) {
            //商品下架
            return null;
        }
        Member member = null;
        if (StringUtils.isNotBlank(token)) {
            member = memberService.findMemberByToken(token);
        }
        GoodsVo goodsVo = FieldUtils.fieldTrans(goods, GoodsVo.class);
        //获取轮播图
        setGoodsTurnsPhotoList(goodsVo);
        goodsVo.setSales(goodsVo.getSalesInitial() + goodsVo.getSalesActual());
        String parameter = goodsVo.getParameter();
        goodsVo.setParameterList(JSONObject.parseObject(parameter, List.class));
        setGoodsDiscounts(member, goodsVo);
        setGoodsPublics(goodsVo, 1);
        return goodsVo;
    }

    @Override
    public GoodsVo getPtGoodById(Integer id) {
        GoodPtgood goodPtgood = new LambdaQueryChainWrapper<>(goodPtgoodMapper)
                .eq(GoodPtgood::getGoodsId, id)
                .eq(GoodPtgood::getIsSale, 1)
                .eq(GoodPtgood::getGroupType, 2)
                .ge(GoodPtgood::getEndTime, new Date())
                .orderByDesc(GoodPtgood::getPtPrice)
                .last("limit 1")
                .one();
        if (goodPtgood == null) {
            return null;
        }
        GoodsVo goodsVo = getPtGoodsByGoodsId(goodPtgood);
        setGoodsPublics(goodsVo, 2);
        return goodsVo;
    }


    @Override
    public GoodsVo getPtGoodByAgent(Integer id) {
        GoodsPtUser goodsPtUser = goodsPtUserMapper.selectById(id);
        GoodPtgood goodPtgood = new LambdaQueryChainWrapper<>(goodPtgoodMapper)
                .eq(GoodPtgood::getGoodsId, goodsPtUser.getGoodsId())
                .eq(GoodPtgood::getIsSale, 1)
                .eq(GoodPtgood::getGroupType, 1)
                .ge(GoodPtgood::getEndTime, new Date())
                .orderByDesc(GoodPtgood::getPtPrice)
                .last("limit 1")
                .one();
        if (goodPtgood == null) {
            return null;
        }
        GoodsVo goodsVo = getPtGoodsByGoodsId(goodPtgood);
        setGoodsPublics(goodsVo, 2);
        return goodsVo;
    }

    private GoodsVo getPtGoodsByGoodsId(GoodPtgood goodPtgood) {
        Goods goods = goodsMapper.selectById(goodPtgood.getGoodsId());
        if (goods == null || goods.getStatus() == null || goods.getStatus() == 0 || goods.getIsDelete() == null || goods.getIsDelete() == 1) {
            //商品下架
            return null;
        }
        GoodsVo goodsVo = FieldUtils.fieldTrans(goods, GoodsVo.class);
        String turnsPhoto = goodsVo.getTurnsPhoto();
        String[] photos = turnsPhoto.split(",");
        Integer[] photosIds = (Integer[]) ConvertUtils.convert(photos, Integer.class);
        List<Integer> ids = Arrays.asList(photosIds);
        List<GoodsPhoto> goodsPhotos = photoMapper.selectBatchIds(ids);
        List<String> turnsPhotoList = new ArrayList<>();
        goodsPhotos.forEach(goodsPhoto -> {
            turnsPhotoList.add(goodsPhoto.getPhotoUrl());
        });
        goodsVo.setTurnsPhotoList(turnsPhotoList);
        goodsVo.setSales(goodsVo.getSalesInitial() + goodsVo.getSalesActual());
        goodsVo.setPrice(goodsVo.getPrice().subtract(goodPtgood.getPtPrice()));
        return goodsVo;
    }

    /**
     * 根据用户会员等级获取规格
     *
     * @param id
     * @param token
     * @return
     */
    @Override
    public GoodsSkuVo getMemberSkuByGoodsId(Integer id, String token) {
        Member member = null;
        if (StringUtils.isNotBlank(token)) {
            member = memberService.findMemberByToken(token);
        }
        Goods goods = goodsMapper.selectById(id);
        if (goods == null) {
            return null;
        }
        GoodsSkuVo goodsSkuVo = getSkuById(id, BigDecimal.ZERO);
        List<GoodsSkuBo> list = goodsSkuVo.getList();
        if (goods.getGiftBag() != null && goods.getGiftBag() == 1) {
            //礼包商品,价格显示为0,限购数量为1
            for (GoodsSkuBo goodsSkuBo : list) {
                goodsSkuBo.setPrice(BigDecimal.ZERO);
            }
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setRestriction(1);
            goodsSkuVo.setGoods(goodsVo);
        } else {
            for (GoodsSkuBo goodsSkuBo : list) {
                setMemberGoodsSkuPrice(goodsSkuBo, member);
            }
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setRestriction(-1);
            goodsSkuVo.setGoods(goodsVo);
        }
        return goodsSkuVo;
    }

    /**
     * 获取该商品用户的sku
     *
     * @param goodsSku
     * @param member
     * @return
     */
    @Override
    public void setMemberGoodsSkuPrice(GoodsSku goodsSku, Member member) {
        Integer memberType = MemberGoodsPricePercent.REGULAR;
        if (member != null) {
            if (member.getMemberType() != null && member.getMemberType() > 0) {
                //如果用户不是普通会员
                //判断用户会员是否过期
                if (member.getCardStatus() != null && member.getCardStatus() == 1) {
                    //未过期,为用户等级
                    memberType = member.getMemberType();
                }
            }
            if (member.getUserId() != null && member.getUserId() > 0) {
                memberType = MemberGoodsPricePercent.AGENT;
            }
        }

        switch (memberType) {
            case 0:
                //普通会员
                break;
            case 1:
                //E卡
                if (goodsSku.getEPrice() != null) {
                    goodsSku.setPrice(goodsSku.getEPrice());
                    break;
                }
                setMemberGoodsSkuPrice(goodsSku, memberType);
                break;
            case 2:
                //金卡
                if (goodsSku.getGlodPrice() != null) {
                    goodsSku.setPrice(goodsSku.getGlodPrice());
                    break;
                }
                setMemberGoodsSkuPrice(goodsSku, memberType);
                break;
            case 3:
                //代理
                if (goodsSku.getAgentPrice() != null) {
                    goodsSku.setPrice(goodsSku.getAgentPrice());
                    break;
                }
                setMemberGoodsSkuPrice(goodsSku, memberType);
                break;
            default:
                break;
        }
    }

    private void setMemberGoodsSkuPrice(GoodsSku goodsSku, Integer memberType) {
        //获取会员价格优惠比例
        GoodsPriceType goodsPriceType = new LambdaQueryChainWrapper<>(goodsPriceTypeMapper)
                .eq(GoodsPriceType::getPriceType, memberType)
                .one();
        if (goodsPriceType != null) {
            //普通会员的商品价格 -（商品售价 - 商品的成本价） * 配置的% = 该用户的商品价格
            //商品售价
            BigDecimal price = goodsSku.getPrice();
            //商品的成本价
            BigDecimal purchasePrice = goodsSku.getPurchasePrice();
            //利润
            BigDecimal profit = price.subtract(purchasePrice);
            //优惠百分比
            Double percent = goodsPriceType.getPercent();
            //优惠多少金额
            BigDecimal discounts = profit.multiply(BigDecimal.valueOf(percent));
            //该用户的商品价格
            BigDecimal realityPrice = price.subtract(discounts);
            goodsSku.setPrice(realityPrice);
        }
    }

    /**
     * 根据会员等级获取sku价格
     *
     * @param goodsSku
     * @param memberType 0普通,1E卡,2金卡,3代理
     * @return
     */
    @Override
    public BigDecimal getGoodsSkuPriceByMemberType(GoodsSku goodsSku, Integer memberType) {
        switch (memberType) {
            case 0:
                //普通会员
                return goodsSku.getPrice();
            case 1:
                //E卡
                if (goodsSku.getEPrice() != null) {
                    return goodsSku.getEPrice();
                }
                break;
            case 2:
                //金卡
                if (goodsSku.getGlodPrice() != null) {
                    return goodsSku.getGlodPrice();
                }
                break;
            case 3:
                //代理
                if (goodsSku.getAgentPrice() != null) {
                    return goodsSku.getAgentPrice();
                }
                break;
            default:
                break;
        }
        //获取会员价格优惠比例
        GoodsPriceType goodsPriceType = new LambdaQueryChainWrapper<>(goodsPriceTypeMapper)
                .eq(GoodsPriceType::getPriceType, memberType)
                .one();
        if (goodsPriceType != null) {
            //普通会员的商品价格 -（商品售价 - 商品的成本价） * 配置的% = 该用户的商品价格
            //商品售价
            BigDecimal price = goodsSku.getPrice();
            //商品的成本价
            BigDecimal purchasePrice = goodsSku.getPurchasePrice();
            //利润
            BigDecimal profit = price.subtract(purchasePrice);
            //优惠百分比
            Double percent = goodsPriceType.getPercent();
            //优惠多少金额
            BigDecimal discounts = profit.multiply(BigDecimal.valueOf(percent));
            //该用户的商品价格
            return price.subtract(discounts);
        }
        return null;
    }

    /**
     * 获取该商品用户的sku
     *
     * @param goodsSkuBo
     * @param member
     * @return
     */
    @Override
    public void setMemberGoodsSkuPrice(GoodsSkuBo goodsSkuBo, Member member) {
        Integer memberType = MemberGoodsPricePercent.REGULAR;
        if (member != null) {
            if (member.getMemberType() != null && member.getMemberType() > 0) {
                //如果用户不是普通会员
                //判断用户会员是否过期
                if (member.getCardStatus() != null && member.getCardStatus() == 1) {
                    //未过期,为用户等级
                    memberType = member.getMemberType();
                }
            }
            if (member.getUserId() != null && member.getUserId() > 0) {
                memberType = MemberGoodsPricePercent.AGENT;
            }
        }

        switch (memberType) {
            case 0:
                //普通会员
                break;
            case 1:
                //E卡
                if (goodsSkuBo.getEPrice() != null) {
                    goodsSkuBo.setPrice(goodsSkuBo.getEPrice());
                    break;
                }
                setMemberGoodsSkuPrice(goodsSkuBo, memberType);
                break;
            case 2:
                //金卡
                if (goodsSkuBo.getGlodPrice() != null) {
                    goodsSkuBo.setPrice(goodsSkuBo.getGlodPrice());
                    break;
                }
                setMemberGoodsSkuPrice(goodsSkuBo, memberType);
                break;
            case 3:
                //代理
                if (goodsSkuBo.getAgentPrice() != null) {
                    goodsSkuBo.setPrice(goodsSkuBo.getAgentPrice());
                    break;
                }
                setMemberGoodsSkuPrice(goodsSkuBo, memberType);
                break;
            default:
                break;
        }
    }


    private void setMemberGoodsSkuPrice(GoodsSkuBo goodsSkuBo, Integer memberType) {
        //获取会员价格优惠比例
        GoodsPriceType goodsPriceType = new LambdaQueryChainWrapper<>(goodsPriceTypeMapper)
                .eq(GoodsPriceType::getPriceType, memberType)
                .one();
        if (goodsPriceType != null) {
            //普通会员的商品价格 -（商品售价 - 商品的成本价） * 配置的% = 该用户的商品价格
            //商品售价
            BigDecimal price = goodsSkuBo.getPrice();
            //商品的成本价
            BigDecimal purchasePrice = goodsSkuBo.getPurchasePrice();
            //利润
            BigDecimal profit = price.subtract(purchasePrice);
            //优惠百分比
            Double percent = goodsPriceType.getPercent();
            //优惠多少金额
            BigDecimal discounts = profit.multiply(BigDecimal.valueOf(percent));
            //该用户的商品价格
            BigDecimal realityPrice = price.subtract(discounts);
            goodsSkuBo.setPrice(realityPrice);
        }
    }

    @Override
    public GoodsSkuBo getGoodsSkuBo(GoodsSku goodsSku, Integer total) {
        GoodsSkuBo goodsSkuBo = FieldUtils.fieldTrans(goodsSku, GoodsSkuBo.class);
        goodsSkuBo.setTotal(total);
        GoodsPhoto goodsPhoto = goodsPhotoMapper.selectById(goodsSku.getPhotoId());
        goodsSkuBo.setPhotoUrl(goodsPhoto.getPhotoUrl());
        Goods goods = goodsMapper.selectById(goodsSku.getGoodsId());
        if (goods == null) {
            return null;
        }
        if (goods.getSource() == 1) {
            //厂家
            Factory factory = factoryMapper.selectById(goods.getRelateId());
            goodsSkuBo.setRelateId(goods.getRelateId());
            if (factory != null) {
                goodsSkuBo.setShopName(factory.getName());
            }
        }
        goodsSkuBo.setGoodsName(goods.getGoodsListName());
        String specValueId = goodsSku.getSpecValueId();
        String[] specValues = specValueId.split("_");
        StringBuilder builder = new StringBuilder();
        for (String specValue : specValues) {
            GoodsSpecValue goodsSpecValue = goodsSpecValueMapper.selectById(Integer.parseInt(specValue));
            if (goodsSpecValue != null) {
                String value = goodsSpecValue.getValue();
                builder.append(value);
                builder.append("-");
            }
        }
        if (StringUtils.isNotBlank(builder)) {
            String specName = builder.substring(0, builder.length() - 1);
            goodsSkuBo.setSpecName(specName);
        } else {
            goodsSkuBo.setSpecName("无规格");
        }
        return goodsSkuBo;
    }

    @Override
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
            goodsSkuBo.setPhotoUrl(getPhotoMapper.selectById(goodsSkuBo.getPhotoId()).getPhotoUrl());
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


    /**
     * 获取该抢购商品用户的sku
     *
     * @param goodsSku
     * @param member
     * @return
     */
    @Override
    public void setMemberPanicSkuPrice(GoodPanicBuy goodPanicBuy, GoodsSku goodsSku, Member member) {
        if (goodPanicBuy != null) {
            BigDecimal discounts = getDiscountsByPanic(goodPanicBuy, member);
            BigDecimal price = goodsSku.getPrice();
            price = price.subtract(discounts);
            goodsSku.setPrice(price);
        }
    }

    /**
     * 获取该抢购商品用户的sku
     *
     * @param goodsSkuBo
     * @param member
     * @return
     */
    @Override
    public void setMemberPanicSkuPrice(GoodPanicBuy goodPanicBuy, GoodsSkuBo goodsSkuBo, Member member) {
        if (goodPanicBuy != null) {
            BigDecimal discounts = getDiscountsByPanic(goodPanicBuy, member);
            BigDecimal price = goodsSkuBo.getPrice();
            price = price.subtract(discounts);
            goodsSkuBo.setPrice(price);
        }
    }

    /**
     * 获取抢购商品的优惠金额
     *
     * @return
     */
    private BigDecimal getDiscountsByPanic(GoodPanicBuy goodPanicBuy, Member member) {
        Integer memberType = MemberGoodsPricePercent.REGULAR;
        if (member != null) {
            if (member.getMemberType() != null && member.getMemberType() > 0) {
                //如果用户不是普通会员
                //判断用户会员是否过期
                if (member.getCardStatus() != null && member.getCardStatus() == 1) {
                    //未过期,为用户等级
                    memberType = member.getMemberType();
                }
            }
        }
        BigDecimal discounts = BigDecimal.ZERO;
        switch (memberType) {
            case 0:
                //普通会员
                discounts = goodPanicBuy.getGeneralPrice();
                break;
            case 1:
                //E卡
                discounts = goodPanicBuy.getEPrice();
                break;
            case 2:
                //金卡
                discounts = goodPanicBuy.getGoldPrice();
                break;
            default:
                break;
        }
        return discounts;
    }

    /**
     * 获取抢购商品的商品详情
     *
     * @param id
     * @param token
     * @return
     */
    @Override
    public GoodsVo getGoodByPanicId(Integer id, String token) {
        Member member = null;
        if (StringUtils.isNotBlank(token)) {
            member = memberService.findMemberByToken(token);
        }
        GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(id);
        return getGoodByPanicId(goodPanicBuy, member);
    }

    private GoodsVo getGoodByPanicId(GoodPanicBuy goodPanicBuy, Member member) {
        if (goodPanicBuy == null) {
            return null;
        }
        int i = DateUtil.compareDateWithNow(goodPanicBuy.getEndTime());
        if (i < 0) {
            //过期
            return null;
        }
        if (goodPanicBuy.getIsSale() == 0 || goodPanicBuy.getStatus() == 0) {
            return null;
        }
        Goods goods = goodsMapper.selectById(goodPanicBuy.getGoodsId());
        if (goods == null || goods.getStatus() == null || goods.getStatus() == 0 || goods.getIsDelete() == null || goods.getIsDelete() == 1) {
            //商品下架
            return null;
        }
        if (goods.getGiftBag() == null || goods.getGiftBag() == 0) {
            GoodsVo goodsVo = FieldUtils.fieldTrans(goods, GoodsVo.class);
            goodsVo.setId(goodPanicBuy.getId());
            //获取轮播图
            setGoodsTurnsPhotoList(goodsVo);
            goodsVo.setPrice(goodPanicBuy.getPrice());
            goodsVo.setLinePrice(goodPanicBuy.getLinePrice());
            goodsVo.setActivityTitle(goodPanicBuy.getActivityTitle());
            goodsVo.setTurnsPhoto(goodPanicBuy.getActivityImg());
            goodsVo.setStockNum(goodPanicBuy.getStockNum());
            goodsVo.setNowTime(System.currentTimeMillis());
            goodsVo.setEndTime(goodPanicBuy.getEndTime().getTime());
            int salesInitial = goodPanicBuy.getSalesInitial() == null ? 0 : goodPanicBuy.getSalesInitial();
            int salesActual = goodPanicBuy.getSalesActual() == null ? 0 : goodPanicBuy.getSalesActual();
            goodsVo.setSales(salesInitial + salesActual);
            String parameter = goodsVo.getParameter();
            goodsVo.setParameterList(JSONObject.parseObject(parameter, List.class));
            GoodsSku goodsSku = new LambdaQueryChainWrapper<>(goodsSkuMapper)
                    .eq(GoodsSku::getGoodsId, goods.getId())
                    .orderByAsc(GoodsSku::getPrice)
                    .last("limit 1")
                    .one();
            BigDecimal price = goodsSku.getPrice();
            String memberType = null;
            //优惠的金额
            BigDecimal discounts = BigDecimal.ZERO;
            if (member != null && member.getMemberType() != null && member.getMemberType() > 0) {
                switch (member.getMemberType()) {
                    case 1:
                        //E卡
                        memberType = "E卡";
                        discounts = goodPanicBuy.getEPrice();
                        break;
                    case 2:
                        //金卡
                        memberType = "金卡";
                        discounts = goodPanicBuy.getGoldPrice();
                        break;
                    default:
                        break;
                }
                price = price.subtract(discounts);
                goodsVo.setMemberType(memberType);
                goodsVo.setMemberPrice(price);
            }
            setGoodsDiscounts(member, goodPanicBuy, goodsVo);
            setGoodsPublics(goodsVo, 3);
            //获取抢购商品的用户头像
            //判断销量时候超过8
            int sales = goodsVo.getSales();
            //需要显示的头像数量
            int number = 0;
            if (sales > 8) {
                //显示8个头像
                number = 8;
            } else {
                //显示销量数量的头像
                number = sales;
            }
            setVirtualMembers(goodsVo, goodPanicBuy, number);
            String token = null;
            if (member != null) {
                token = member.getToken();
            }
            GoodsSkuVo goodsSkuVo = getMemberSkuByPanicId(goodPanicBuy.getId(), token);
            List<GoodsSkuBo> list = goodsSkuVo.getList();
            BigDecimal maxPrice = BigDecimal.ZERO;
            for (GoodsSkuBo goodsSkuBo : list) {
                BigDecimal skuBoPrice = goodsSkuBo.getPrice();
                if (skuBoPrice.compareTo(maxPrice) > 0) {
                    maxPrice = skuBoPrice;
                }
            }
            goodsVo.setNewCurrency("购买此商品可获得" + getNewCurrency(maxPrice) + "新民币(可线下消费抵扣)");
            return goodsVo;
        }
        return null;
    }

    /**
     * 设置抢购商品用户的头像
     *
     * @param goodsVo
     * @param goodPanicBuy
     * @param number       需要设置的头像数量
     */
    private void setVirtualMembers(GoodsVo goodsVo, GoodPanicBuy goodPanicBuy, int number) {
        List<String> virtualMembers = new ArrayList<>();
        //用户id集合
        Set<Integer> memberIds = new LinkedHashSet<>();
        //查询该商品购买的实际用户数
        List<GoodPanicBuyMember> goodPanicBuyMembers = new LambdaQueryChainWrapper<>(goodPanicBuyMemberMapper)
                .eq(GoodPanicBuyMember::getPanicBuyId, goodPanicBuy.getId())
                .list();
        if (goodPanicBuyMembers != null && goodPanicBuyMembers.size() > 0) {
            for (GoodPanicBuyMember goodPanicBuyMember : goodPanicBuyMembers) {
                memberIds.add(goodPanicBuyMember.getMemberId());
            }
        }
        //判断实际购买的用户数量和需要显示的头像数量对比
        if (memberIds.size() < number) {
            //头像数量不够.添加虚拟用户头像
            if (StringUtils.isNotBlank(goodPanicBuy.getVirtualMember())) {
                //设置虚拟用户的情况
                String virtualMember = goodPanicBuy.getVirtualMember();
                String[] memberIdArr = virtualMember.split(",");
                for (String memberId : memberIdArr) {
                    memberIds.add(Integer.parseInt(memberId));
                }
            }
        }
        //添加虚拟用户之后头像数量还是不够的情况
        if (memberIds.size() < number) {
            //还需要多少头像数量
            int i = number - memberIds.size();
            //头像数量不够.数据库随机获取用户头像
            List<Member> members = new LambdaQueryChainWrapper<>(memberMapper)
                    .isNotNull(Member::getAvatarUrl)
                    .notIn(Member::getId, memberIds)
                    .last("limit " + i)
                    .list();
            for (Member member : members) {
                memberIds.add(member.getId());
            }
        }
        for (Integer memberId : memberIds) {
            if (virtualMembers.size() == number) {
                break;
            }
            Member member = memberMapper.selectById(memberId);
            if (member != null && StringUtils.isNotBlank(member.getAvatarUrl())) {
                virtualMembers.add(member.getAvatarUrl());
            }
        }
        //如果头像数量还是不够的话,再一次数据库进行添加
        if (virtualMembers.size() < number) {
            int i = number - virtualMembers.size();
            List<Member> members = new LambdaQueryChainWrapper<>(memberMapper)
                    .isNotNull(Member::getAvatarUrl)
                    .notIn(Member::getId, memberIds)
                    .last("limit " + i)
                    .list();
            for (Member member : members) {
                virtualMembers.add(member.getAvatarUrl());
            }
        }
        goodsVo.setVirtualMembers(virtualMembers);
    }


    /**
     * 获取轮播图
     *
     * @param goodsVo
     */
    private void setGoodsTurnsPhotoList(GoodsVo goodsVo) {
        String turnsPhoto = goodsVo.getTurnsPhoto();
        String[] photos = turnsPhoto.split(",");
        Integer[] photosIds = (Integer[]) ConvertUtils.convert(photos, Integer.class);
        List<Integer> ids = Arrays.asList(photosIds);
        List<GoodsPhoto> goodsPhotos = photoMapper.selectBatchIds(ids);
        List<String> turnsPhotoList = new ArrayList<>();
        goodsPhotos.forEach(goodsPhoto -> {
            turnsPhotoList.add(goodsPhoto.getPhotoUrl());
        });
        goodsVo.setTurnsPhotoList(turnsPhotoList);
    }

    @Override
    public GoodsSkuVo getMemberSkuByPanicId(Integer id, String token) {
        Member member = null;
        if (StringUtils.isNotBlank(token)) {
            member = memberService.findMemberByToken(token);
        }
        GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(id);
        BigDecimal discounts = getDiscountsByPanic(goodPanicBuy, member);
        GoodsSkuVo goodsSkuVo = getSkuById(goodPanicBuy.getGoodsId(), discounts);
        int restriction = 0;
        if (member != null) {
            restriction = getMemberRestriction(goodPanicBuy, member.getId());
        } else {
            restriction = getMemberRestriction(goodPanicBuy, null);
        }
        //库存数量
        int stockNum = goodPanicBuy.getStockNum();
        if (restriction == 0) {
            //没有限购数量,设置库存为0
            stockNum = 0;
        }
        if (restriction > 0) {
            if (restriction < stockNum) {
                stockNum = restriction;
            }
        }
        List<GoodsSkuBo> goodsSkuBos = goodsSkuVo.getList();
        for (GoodsSkuBo goodsSkuBo : goodsSkuBos) {
            goodsSkuBo.setStockNum(stockNum);
        }
        GoodsVo goodsVo = new GoodsVo();
        goodsVo.setRestriction(restriction);
        goodsSkuVo.setGoods(goodsVo);
        return goodsSkuVo;
    }

    /**
     * 获取用户限购数量
     *
     * @param goodPanicBuy
     * @param memberId
     * @return -1为不限
     */
    @Override
    public int getMemberRestriction(GoodPanicBuy goodPanicBuy, Integer memberId) {
        if (goodPanicBuy.getRestriction() != null) {
            if (goodPanicBuy.getRestriction() == -1) {
                return -1;
            }
            //限购的数量
            int restriction = goodPanicBuy.getRestriction();
            if (memberId == null) {
                return restriction;
            }
            //获取已购数量
            int memberBuyTotal = goodPanicBuyMemberMapper.getMemberBuyTotal(goodPanicBuy.getId(), memberId);
            restriction -= memberBuyTotal;
            return Math.max(restriction, 0);
        }
        return -1;
    }

    /**
     * 判断用户是否超过了限购数量
     *
     * @param goodPanicBuy
     * @param memberId
     * @return true 可以购买,false不可购买
     */
    @Override
    public boolean checkMemberRestriction(GoodPanicBuy goodPanicBuy, Integer memberId, Integer quantity) {
        //可购买数量
        int restriction = getMemberRestriction(goodPanicBuy, memberId);
        if (restriction == -1) {
            return true;
        } else {
            return restriction >= quantity;
        }
    }

    /**
     * 获取普通商品的会员提示标语
     *
     * @param member
     * @param goodsVo
     * @return
     */
    private void setGoodsDiscounts(Member member, GoodsVo goodsVo) {
        //获取该商品的原价(默认sku)
        GoodsSkuVo goodsSkuVo = getSkuById(goodsVo.getId(), BigDecimal.ZERO);
        List<GoodsSkuBo> list = goodsSkuVo.getList();
        if (list == null || list.size() == 0) {
            return;
        }
        int memberType = 0;
        if (member != null && member.getMemberType() != null) {
            memberType = member.getMemberType();
        }
        BigDecimal minPrice = null;
        BigDecimal maxPrice = BigDecimal.ZERO;
        for (GoodsSkuBo goodsSkuBo : list) {
            GoodsSku goodsSku = FieldUtils.fieldTrans(goodsSkuBo, GoodsSku.class);
            BigDecimal price = getGoodsSkuPriceByMemberType(goodsSku, memberType);
            if (minPrice == null) {
                minPrice = price;
            }
            if (minPrice != null && minPrice.compareTo(price) > 0) {
                minPrice = price;
            }
            if (price.compareTo(maxPrice) > 0) {
                maxPrice = price;
            }
        }
        goodsVo.setNewCurrency("购买此商品可获得" + getNewCurrency(maxPrice) + "新民币(可线下消费抵扣)");
        switch (memberType) {
            case 0:
                //普通,显示E卡的优惠金额
                goodsVo.setDiscounts("现在升级新民E卡,此商品最高可优惠" + getByMemberType(list, 1) + "元");
                break;
            case 1:
                //E卡,显示金卡的优惠金额
                goodsVo.setDiscounts("现在升级新民金卡,此商品最高可优惠" + getByMemberType(list, 2) + "元");
                goodsVo.setMemberType("E卡");
                goodsVo.setMemberPrice(minPrice);
                break;
            case 2:
                //金卡
                goodsVo.setMemberType("金卡");
                goodsVo.setMemberPrice(minPrice);
                break;
            default:
                break;
        }
    }

    private BigDecimal getNewCurrency(BigDecimal price) {
        MemberBenefit benefit = new LambdaQueryChainWrapper<>(memberBenefitMapper)
                .eq(MemberBenefit::getType, 7)
                .one();
        if (benefit != null) {
            BigDecimal percent = BigDecimal.valueOf(benefit.getPercent());
            return percent.multiply(price);
        }
        return null;
    }

    /**
     * 根据会员等级获取该商品最大优惠
     *
     * @param list
     * @param memberType
     * @return
     */
    private BigDecimal getByMemberType(List<GoodsSkuBo> list, Integer memberType) {
        BigDecimal discounts = BigDecimal.ZERO;
        for (GoodsSkuBo goodsSkuBo : list) {
            GoodsSku goodsSku = FieldUtils.fieldTrans(goodsSkuBo, GoodsSku.class);
            BigDecimal price = getGoodsSkuPriceByMemberType(goodsSku, memberType);
            //优惠的金额
            BigDecimal subtract = goodsSku.getPrice().subtract(price);
            if (discounts.compareTo(subtract) < 0) {
                discounts = subtract;
            }
        }
        return discounts;
    }

    /**
     * 获取抢购商品的会员提示标语
     *
     * @param member
     * @param goodPanicBuy
     * @param goodsVo
     * @return
     */
    private void setGoodsDiscounts(Member member, GoodPanicBuy goodPanicBuy, GoodsVo goodsVo) {
        int memberType = 0;
        if (member != null && member.getMemberType() != null) {
            memberType = member.getMemberType();
        }
        switch (memberType) {
            case 0:
                //普通,显示E卡的优惠金额
                BigDecimal ePrice = goodPanicBuy.getEPrice();
                goodsVo.setDiscounts("现在升级新民E卡,此商品最高可优惠" + ePrice + "元");
                break;
            case 1:
                //E卡,显示金卡的优惠金额
                BigDecimal goldPrice = goodPanicBuy.getGoldPrice();
                goodsVo.setDiscounts("现在升级新民金卡,此商品最高可优惠" + goldPrice + "元");
                break;
            case 2:
                //金卡
                break;
            default:
                break;
        }
    }

    private void setGoodsPublics(GoodsVo goodsVo, Integer type) {
        if (goodsVo == null) {
            return;
        }
        List<GoodsPublic> goodsPublics = new LambdaQueryChainWrapper<>(goodsPublicMapper)
                .eq(GoodsPublic::getStatus, 1)
                .eq(GoodsPublic::getType, type)
                .le(GoodsPublic::getStartTime, new Date())
                .ge(GoodsPublic::getEndTime, new Date())
                .orderByAsc(GoodsPublic::getPlace)
                .orderByAsc(GoodsPublic::getSort)
                .list();
        goodsVo.setGoodsPublics(goodsPublics);
    }
}
