package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.GoodPanicBuyMapper;
import com.xinmintx.hstx.mapper.GoodsMapper;
import com.xinmintx.hstx.mapper.GoodsSkuMapper;
import com.xinmintx.hstx.mapper.ShoppingCartDetailMapper;
import com.xinmintx.hstx.pojo.bo.GoodsSkuBo;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.CarVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.ShopCarVo;
import com.xinmintx.hstx.service.GoodsService;
import com.xinmintx.hstx.service.ShoppingCartService;
import com.xinmintx.hstx.util.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ShoppingCartDetailMapper shoppingCartDetailMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    private GoodPanicBuyMapper goodPanicBuyMapper;

    /**
     * 添加购物车
     *
     * @param memberId 会员id
     * @param goodsId  商品id
     * @param count    数量
     * @param skuId    规格id
     */
    @Override
    public void addShoppingCar(Integer memberId, Integer goodsId, Integer count, Integer skuId, Integer type) {
        ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail();
        shoppingCartDetail.setMemberId(memberId);
        shoppingCartDetail.setGoodsId(goodsId);
        shoppingCartDetail.setTotal(count);
        shoppingCartDetail.setSkuId(skuId);
        shoppingCartDetail.setStatus(1);
        shoppingCartDetail.setType(type);
        shoppingCartDetail.setCreateTime(new Date());
        //添加到购物车表
        shoppingCartDetailMapper.insert(shoppingCartDetail);
    }


    /**
     * 根据会员id和规格id判断购物车是否已经存在该商品
     *
     * @param memberId 会员id
     * @param goodsId  商品id
     * @param skuId    规格id
     * @param type     商品类型
     * @return 购物车
     */
    @Override
    public ShoppingCartDetail checkCar(Integer memberId, Integer goodsId, Integer skuId, Integer type) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("member_id", memberId);
        hashMap.put("goods_id", goodsId);
        hashMap.put("sku_id", skuId);
        hashMap.put("status", 1);
        hashMap.put("type", type);
        List<ShoppingCartDetail> shoppingCartDetails = shoppingCartDetailMapper.selectByMap(hashMap);
        if (shoppingCartDetails == null || shoppingCartDetails.size() == 0) {
            return null;
        }
        return shoppingCartDetails.get(0);
    }

    /**
     * 修改购物车个数
     *
     * @param shoppingCartDetail
     */
    @Override
    public void updateShoppingCar(ShoppingCartDetail shoppingCartDetail) {
        shoppingCartDetailMapper.updateById(shoppingCartDetail);
    }


    /**
     * 查询商品是否存在
     *
     * @param goodsId 商品id
     * @return true或false
     */
    @Override
    public boolean checkGoods(Integer goodsId) {
        Goods goods = goodsMapper.selectById(goodsId);
        if (goods != null) {
            return true;
        }
        return false;
    }

    /**
     * 查询规格是否存在
     *
     * @param skuId 规格id
     * @return true或false
     */
    @Override
    public boolean checkGoodsSku(Integer skuId) {
        GoodsSku goodsSku = goodsSkuMapper.selectById(skuId);
        if (goodsSku != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取购物车详情
     *
     * @param member 会员
     * @return 购物车
     */
    @Override
    public List<ShopCarVo> selectCars(Member member) {
        List<ShopCarVo> list = new ArrayList<>();
        //1.获取普通商品
        list.addAll(selectCarsByType(member, 1));
        //2.获取抢购商品
        list.addAll(selectCarsByType(member, 4));
        return list;
    }

    /**
     * 获取购物车详情
     *
     * @param member 会员
     * @return 购物车
     */
    public List<ShopCarVo> selectCarsByType(Member member, Integer type) {
        List<ShopCarVo> list = new ArrayList<>();
        //查询用户加入购物车的普通商品
        List<ShoppingCartDetail> shoppingCartDetails = new LambdaQueryChainWrapper<>(shoppingCartDetailMapper)
                .eq(ShoppingCartDetail::getStatus, 1)
                .eq(ShoppingCartDetail::getMemberId, member.getId())
                .eq(ShoppingCartDetail::getType, type)
                .list();
        if (shoppingCartDetails == null || shoppingCartDetails.size() == 0){
            return list;
        }
        if (type == 4) {
            //抢购商品的购物车列表
            Map<String, List<ShoppingCartDetail>> listMap = ListUtils.listGroup(shoppingCartDetails, "goodsId");
            listMap.forEach((key, value) -> {
                GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(Integer.parseInt(key));
                //如果过期或者下架或不启用的就直接删除
                if (goodPanicBuy == null || goodPanicBuy.getEndTime().before(new Date()) || goodPanicBuy.getStatus() == 0 || goodPanicBuy.getIsSale() == 0) {
                    for (ShoppingCartDetail shoppingCartDetail : value) {
                        shoppingCartDetailMapper.deleteById(shoppingCartDetail.getId());
                    }
                    return;
                }
                //获取该商品该用户所能购买的数量
                int restriction = goodsService.getMemberRestriction(goodPanicBuy, member.getId());
                //获取该商品该用户已经添加在购物车的数量
                int total = shoppingCartDetailMapper.getMemberPanicTotalByPanicId(member.getId(), goodPanicBuy.getId());
                if (total > restriction && restriction > -1) {
                    //已添加的数量超过可以购买的数量
                    for (ShoppingCartDetail shoppingCartDetail : value) {
                        //依次判断各个购物车的数量和限购数对比
                        int carVoTotal = shoppingCartDetail.getTotal();
                        if (carVoTotal > restriction) {
                            //这一条购物车记录超过可够数量,修改购物车数量为剩余可购数量
                            shoppingCartDetail.setTotal(restriction);
                            shoppingCartDetail.updateById();
                            restriction = 0;
                        } else {
                            //可够数量减去这一购物车数量
                            restriction -= carVoTotal;
                        }
                    }
                }
            });
        }
        shoppingCartDetails = new LambdaQueryChainWrapper<>(shoppingCartDetailMapper)
                .eq(ShoppingCartDetail::getStatus, 1)
                .eq(ShoppingCartDetail::getMemberId, member.getId())
                .eq(ShoppingCartDetail::getType, type)
                .list();
        List<CarVo> cars = new ArrayList<>();
        for (ShoppingCartDetail shoppingCartDetail : shoppingCartDetails) {
            GoodsSku goodsSku = goodsSkuMapper.selectById(shoppingCartDetail.getSkuId());
            if (goodsSku == null) {
                continue;
            }
            GoodsSkuBo goodsSkuBo = goodsService.getGoodsSkuBo(goodsSku, shoppingCartDetail.getTotal());
            if (type == 1) {
                //根据sku和用 户设置 该用户的sku价格
                goodsService.setMemberGoodsSkuPrice(goodsSkuBo, member);
            }
            if (type == 4) {
                //设置抢购商品的价格
                GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(shoppingCartDetail.getGoodsId());
                goodsService.setMemberPanicSkuPrice(goodPanicBuy, goodsSkuBo, member);
            }
            CarVo carVo = new CarVo();
            carVo.setId(shoppingCartDetail.getId());
            carVo.setGoodsId(shoppingCartDetail.getGoodsId());
            carVo.setShopId(goodsSkuBo.getRelateId());
            carVo.setShopName(goodsSkuBo.getShopName());
            carVo.setGoodsName(goodsSkuBo.getGoodsName());
            carVo.setSpec(goodsSkuBo.getSpecName());
            carVo.setPrice(goodsSkuBo.getPrice().doubleValue());
            carVo.setTotal(shoppingCartDetail.getTotal());
            carVo.setCreateTime(shoppingCartDetail.getCreateTime());
            carVo.setImge(goodsSkuBo.getPhotoUrl());
            carVo.setSkuId(shoppingCartDetail.getSkuId());
            carVo.setStock(goodsSkuBo.getStockNum());
            carVo.setType(shoppingCartDetail.getType());
            if (StringUtils.isBlank(carVo.getShopName())) {
                continue;
            }
            cars.add(carVo);
        }
        if (type == 4) {
            //抢购商品的购物车列表
            Map<String, List<CarVo>> listMap = ListUtils.listGroup(cars, "goodsId");
            listMap.forEach((key, value) -> {
                GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(Integer.parseInt(key));
                //获取该商品该用户所能购买的数量
                int restriction = goodsService.getMemberRestriction(goodPanicBuy, member.getId());
                //获取该商品该用户已经添加在购物车的数量
                int total = shoppingCartDetailMapper.getMemberPanicTotalByPanicId(member.getId(), goodPanicBuy.getId());
                int max = Math.max(restriction - total, 0);
                for (CarVo carVo : value) {
                    //设置抢购商品用户的限购数为库存
                    if (restriction > -1) {
                        carVo.setStock(max);
                    }
                }
            });
        }
        Map<String, List<CarVo>> map = ListUtils.listGroup(cars, "shopId");
        map.forEach((key, value) -> {
            ShopCarVo shop = new ShopCarVo();
            shop.setShopId(value.get(0).getShopId());
            shop.setShopName(value.get(0).getShopName());
            value.forEach(car -> {
                car.setShopName(null);
                car.setShopId(null);
            });
            shop.setCars(value);
            list.add(shop);
        });
        return list;
    }


    /**
     * 删除购物车
     *
     * @param list 购物车id
     */
    @Override
    public void deleteCar(List<Integer> list) {
        for (Integer integer : list) {
            shoppingCartDetailMapper.deleteById(integer);
        }
    }


    /**
     * 结算购物车数量
     *
     * @param list 购物车详情
     */
    @Override
    public void updateCar(List<CarVo> list) {
        for (CarVo car : list) {
            ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail();
            shoppingCartDetail.setTotal(car.getTotal());
            shoppingCartDetail.setId(car.getId());
            shoppingCartDetailMapper.updateById(shoppingCartDetail);
        }
    }

    /**
     * 修改购物车数量
     *
     * @param id    购物车id
     * @param count 数量
     */
    @Override
    public int changeCar(Integer id, Integer count) {
        ShoppingCartDetail shoppingCartDetail = shoppingCartDetailMapper.selectById(id);
        if (shoppingCartDetail != null) {
            //如果是普通商品
            if (shoppingCartDetail.getType() == 1) {
                changeOrdinaryCar(count, shoppingCartDetail);
                //如果是限购商品
            } else if (shoppingCartDetail.getType() == 4) {
                //如果是抢购商品
                changePurchaseGoodsCar(count, shoppingCartDetail);
            }
        }
        ShoppingCartDetail cartDetail = shoppingCartDetailMapper.selectById(id);
        return cartDetail.getTotal();

    }

    /**
     * 修改购物车抢购商品数量
     *
     * @param count              数量
     * @param shoppingCartDetail 购物车对象
     */
    private void changePurchaseGoodsCar(Integer count, ShoppingCartDetail shoppingCartDetail) {
        GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(shoppingCartDetail.getGoodsId());
        if (goodPanicBuy == null) {
            return;
        }
        //减数量不检查是否超库存或超限购
        if (count < 0) {
            if (shoppingCartDetail.getTotal() + count <= 0) {
                shoppingCartDetail.setTotal(1);
            } else {
                shoppingCartDetail.setTotal(shoppingCartDetail.getTotal() + count);
            }
            shoppingCartDetail.updateById();
        } else {
            //获取用户该规格 sku 限购数量
            int resultCount = goodsService.getMemberRestriction(goodPanicBuy, shoppingCartDetail.getMemberId());
            if (resultCount == -1 || resultCount >= count) {
                addGoodPanicBuyShopCar(shoppingCartDetail.getMemberId(), shoppingCartDetail.getGoodsId(), shoppingCartDetail.getSkuId(), count, resultCount, goodPanicBuy.getStockNum());
            } else if (resultCount == 0) {
                return;
            } else {
                addGoodPanicBuyShopCar(shoppingCartDetail.getMemberId(), shoppingCartDetail.getGoodsId(), shoppingCartDetail.getSkuId(), resultCount, resultCount, goodPanicBuy.getStockNum());
            }
        }
    }

    /**
     * 修改购物车普通商品的数量
     *
     * @param count              数量
     * @param shoppingCartDetail 购物车对象
     */
    private void changeOrdinaryCar(Integer count, ShoppingCartDetail shoppingCartDetail) {
        if (shoppingCartDetail.getTotal() == 1 && count < 0) {
            return;
        }
        int total = shoppingCartDetail.getTotal() + count;
        if (count > 0) {
            Integer skuId = shoppingCartDetail.getSkuId();
            GoodsSku goodsSku = goodsSkuMapper.selectById(skuId);
            if (goodsSku == null) {
                return;
            }
            int stockNum = goodsSku.getStockNum();
            if (total > stockNum) {
                shoppingCartDetail.setTotal(stockNum);
            } else {
                shoppingCartDetail.setTotal(total);
            }
            shoppingCartDetailMapper.updateById(shoppingCartDetail);
        } else {
            if (total < 1) {
                total = 1;
            }
            shoppingCartDetail.setTotal(total);
            shoppingCartDetailMapper.updateById(shoppingCartDetail);
        }
    }

    /**
     * 添加限时抢购购物车
     *
     * @param memberId   会员id
     * @param panicBuyId 限时抢购id
     * @param skuId      skuId
     * @param count      数量
     * @return 结果
     */
    @Override
    public ResultCode addGoodPanicBuyCar(Integer memberId, Integer panicBuyId, Integer skuId, Integer count) {
        ResultCode<Object> resultCode = new ResultCode<>();
        //判断抢购商品是否存在
        GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(panicBuyId);
        if (goodPanicBuy == null) {
            resultCode.setCode(500);
            resultCode.setMsg("对不起,该商品没有参与限时抢购");
            return resultCode;
        }
        //判断限时抢购商品是否已经过期
        Date endTime = goodPanicBuy.getEndTime();
        if (endTime.before(new Date()) || goodPanicBuy.getIsSale() == 0 || goodPanicBuy.getStatus() == 0) {
            resultCode.setCode(501);
            resultCode.setMsg("对不起,该抢购商品已过时");
            return resultCode;
        }
        //获取限时抢购商品的库存
        Integer stockNum = goodPanicBuy.getStockNum();
        //获取用户限购数量
        int resultCount = goodsService.getMemberRestriction(goodPanicBuy, memberId);
        //没超限购数或者为-1,就直接添加
        if (resultCount == -1 || resultCount >= count) {
            //添加购物车
            return addGoodPanicBuyShopCar(memberId, panicBuyId, skuId, count, resultCount, stockNum);
        } else {
            //如果超过限购数了就添加该用户的限购数
            return addGoodPanicBuyShopCar(memberId, panicBuyId, skuId, resultCount, resultCount, stockNum);
        }
    }

    /**
     * 查询购物车是否已经存在限时抢购商品 并添加
     *
     * @param memberId    会员id
     * @param panicBuyId  抢购商品id
     * @param skuId       skuId
     * @param count       数量
     * @param restriction 该用户的商品限购数
     * @param stockNum    限时抢购商品的库存数
     * @return
     */
    private ResultCode addGoodPanicBuyShopCar(Integer memberId, Integer panicBuyId, Integer skuId, Integer count, int restriction, int stockNum) {
        ResultCode<Object> resultCode = new ResultCode<>();
        //获取购物车相同skuid
        ShoppingCartDetail shoppingCartDetailGoods = new LambdaQueryChainWrapper<>(shoppingCartDetailMapper)
                .eq(ShoppingCartDetail::getMemberId, memberId)
                .eq(ShoppingCartDetail::getSkuId, skuId)
                .eq(ShoppingCartDetail::getGoodsId, panicBuyId)
                .eq(ShoppingCartDetail::getStatus, 1)
                .eq(ShoppingCartDetail::getType, 4)
                .one();
        //不同skuid相同goodsId的抢购商品
        List<ShoppingCartDetail> list = new LambdaQueryChainWrapper<>(shoppingCartDetailMapper)
                .eq(ShoppingCartDetail::getMemberId, memberId)
                .eq(ShoppingCartDetail::getGoodsId, panicBuyId)
                .eq(ShoppingCartDetail::getStatus, 1)
                .ne(ShoppingCartDetail::getSkuId, skuId)
                .eq(ShoppingCartDetail::getType, 4)
                .list();
        //购物车不存在该商品
        if (shoppingCartDetailGoods == null && (list == null || list.size() == 0)) {
            //添加到购物车表
            ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail();
            shoppingCartDetail.setMemberId(memberId);
            shoppingCartDetail.setGoodsId(panicBuyId);
            //超库存就加库存数
            if (count > stockNum) {
                shoppingCartDetail.setTotal(stockNum);
                //不超库存数就加添加数
            } else {
                if (count == 0) {
                    resultCode.setCode(200);
                    resultCode.setMsg("添加成功");
                    return resultCode;
                }
                shoppingCartDetail.setTotal(count);
            }
            shoppingCartDetail.setSkuId(skuId);
            shoppingCartDetail.setStatus(1);
            shoppingCartDetail.setType(4);
            shoppingCartDetail.setCreateTime(new Date());
            shoppingCartDetail.insert();
            //购物车存在不同规格的商品
        } else if (shoppingCartDetailGoods == null && (list != null && list.size() > 0)) {
            int flag = 0;
            for (ShoppingCartDetail shoppingCartDetail : list) {
                flag += shoppingCartDetail.getTotal();
            }
            ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail();
            int i = (flag + count) <= stockNum ? count : (stockNum - flag);
            if (restriction == -1) {
                if (i <= 0) {
                    resultCode.setCode(200);
                    resultCode.setMsg("添加成功");
                    return resultCode;
                }
                shoppingCartDetail.setTotal(i);
            } else {
                //判断加入购物车的数量是否大于限购数,如果大于就选限购数减已加的数
                int i1 = (i + flag) >= restriction ? (restriction - flag) : i;
                if (i1 <= 0) {
                    resultCode.setCode(200);
                    resultCode.setMsg("添加成功");
                    return resultCode;
                }
                shoppingCartDetail.setTotal(i1);
            }
            //添加到购物车表
            shoppingCartDetail.setMemberId(memberId);
            shoppingCartDetail.setGoodsId(panicBuyId);
            shoppingCartDetail.setSkuId(skuId);
            shoppingCartDetail.setStatus(1);
            shoppingCartDetail.setType(4);
            shoppingCartDetail.setCreateTime(new Date());
            shoppingCartDetail.insert();
        } else if (shoppingCartDetailGoods != null && (list == null || list.size() == 0)) {
            //如果是不限购
            if (restriction == -1) {
                //判断是否已经超过限购商品的库存
                if (shoppingCartDetailGoods.getTotal() + count > stockNum) {
                    shoppingCartDetailGoods.setTotal(stockNum);
                } else {
                    //不超就添加
                    shoppingCartDetailGoods.setTotal(shoppingCartDetailGoods.getTotal() + count);
                }
                //如果是限购
            } else {
                //判断相加后的数量是否超过该用户的限购数(超过就取限购数)
                int i = (shoppingCartDetailGoods.getTotal() + count) <= restriction ? (shoppingCartDetailGoods.getTotal() + count) : restriction;
                //判断是否超过库存
                if (i > stockNum) {
                    shoppingCartDetailGoods.setTotal(stockNum);
                } else {
                    shoppingCartDetailGoods.setTotal(i);
                }
            }
            shoppingCartDetailMapper.updateById(shoppingCartDetailGoods);
        } else {
            //获取相同skuid的数量
            Integer total = shoppingCartDetailGoods.getTotal();
            //获取不同skuid的数量
            int flag = 0;
            for (ShoppingCartDetail shoppingCartDetail : list) {
                flag += shoppingCartDetail.getTotal();
            }
            //如果是不限购
            if (restriction == -1) {
                //是否超过库存
                int i = (total + flag + count) <= stockNum ? (total + count) : (stockNum - flag);
                shoppingCartDetailGoods.setTotal(i);
                //如果是限购
            } else {
                //判断是否总数超过限购数
                int num = stockNum >= restriction ? restriction : stockNum;
                int i = (total + flag + count) <= num ? (total + count) : (num - flag);
                //判断是否超过库存
                if (i > stockNum) {
                    shoppingCartDetailGoods.setTotal(stockNum - flag);
                } else {
                    shoppingCartDetailGoods.setTotal(i);
                }
            }
            shoppingCartDetailMapper.updateById(shoppingCartDetailGoods);
        }
        resultCode.setCode(200);
        resultCode.setMsg("添加成功");
        return resultCode;
    }
}
