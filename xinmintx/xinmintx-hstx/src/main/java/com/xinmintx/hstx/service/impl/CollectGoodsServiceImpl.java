package com.xinmintx.hstx.service.impl;

import com.xinmintx.hstx.mapper.GoodPanicBuyMapper;
import com.xinmintx.hstx.mapper.GoodsMapper;
import com.xinmintx.hstx.mapper.GoodsPhotoMapper;
import com.xinmintx.hstx.mapper.MemberCollectGoodsMapper;
import com.xinmintx.hstx.pojo.po.GoodPanicBuy;
import com.xinmintx.hstx.pojo.po.Goods;
import com.xinmintx.hstx.pojo.po.GoodsPhoto;
import com.xinmintx.hstx.pojo.po.MemberCollectGoods;
import com.xinmintx.hstx.service.CollectGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectGoodsServiceImpl implements CollectGoodsService {

    @Autowired
    private MemberCollectGoodsMapper memberCollectGoodsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsPhotoMapper goodsPhotoMapper;

    @Autowired
    private GoodPanicBuyMapper goodPanicBuyMapper;

    /**
     * 用户添加收藏商品
     *
     * @param memberId 会员id
     * @param goodsId  商品id
     * @param type     收藏类型
     */
    @Override
    public boolean insertCollectGoods(Integer memberId, Integer goodsId, Integer type) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("goods_id", goodsId);
        hashMap.put("member_id", memberId);
        hashMap.put("type", type);
        List<MemberCollectGoods> list = memberCollectGoodsMapper.selectByMap(hashMap);
        //如果为空就添加收藏
        if (list == null || list.size() == 0) {
            MemberCollectGoods memberCollectGoods = new MemberCollectGoods();
            memberCollectGoods.setMemberId(memberId);
            memberCollectGoods.setGoodsId(goodsId);
            memberCollectGoods.setType(type);
            memberCollectGoodsMapper.insert(memberCollectGoods);
            return true;
        } else {
            //否则就不添加
            return false;
        }
    }

    /**
     * 获取全部收藏的商品
     *
     * @param memberId 会员id
     * @return
     */
    @Override
    public List getCollectGoodsList(Integer memberId) {
        //获取全部收藏列表
        Map<String, Object> map = new HashMap<>();
        map.put("member_id", memberId);
        List<MemberCollectGoods> memberCollectGoods = memberCollectGoodsMapper.selectByMap(map);
        List<Object> list = new ArrayList<>();
        //如果为空
        if (memberCollectGoods == null || memberCollectGoods.size() == 0) {
            return list;
        }
        //遍历收藏表
        for (MemberCollectGoods memberCollectGood : memberCollectGoods) {
            Integer goodsId = memberCollectGood.getGoodsId();
            //普通商品
            if (memberCollectGood.getType() == 1 || memberCollectGood.getType() == 2) {
                HashMap commonGoods = getCommonGoods(memberCollectGood, goodsId, memberCollectGood.getType());
                list.add(commonGoods);
            } else if (memberCollectGood.getType() == 4) {
                HashMap panicBuyGoods = getPanicBuyGoods(memberCollectGood);
                list.add(panicBuyGoods);
            }
        }
        return list;
    }

    /**
     * 获取热销商品的收藏
     *
     * @param memberCollectGood
     */
    private HashMap getPanicBuyGoods(MemberCollectGoods memberCollectGood) {
        //查询热销商品
        GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(memberCollectGood.getGoodsId());
        HashMap<Object, Object> hashMap = new HashMap<>();
        //获取商品
        if (goodPanicBuy != null) {
            Goods goods = goodsMapper.selectById(goodPanicBuy.getGoodsId());
            if (goods == null) {
                return hashMap;
            }
            //收藏表id
            hashMap.put("collectId", memberCollectGood.getId());
            //收藏类型
            hashMap.put("type", memberCollectGood.getType());
            //商品id
            hashMap.put("id", goodPanicBuy.getId());
            //商品名字
            hashMap.put("goodsName", goodPanicBuy.getGoodsName());

            //获取商品图片
            String[] split = goods.getTurnsPhoto().split(",");
            GoodsPhoto goodsPhoto = goodsPhotoMapper.selectById(Integer.parseInt(split[0]));
            if (goodsPhoto != null) {
                //商品图片路径
                hashMap.put("picture", goodsPhoto.getPhotoUrl());
            } else {
                hashMap.put("picture", null);
            }
            //商品价格
            hashMap.put("price", goodPanicBuy.getPrice());
            //商品状态(上下架)
            hashMap.put("status", goodPanicBuy.getStatus());
            //是否删除
            //hashMap.put("isDelete", good.getIsDelete());
            hashMap.put("checked", false);
            //获取收藏数
            Map<String, Object> collectMap = new HashMap<>();
            collectMap.put("goods_id", goodPanicBuy.getId());
            collectMap.put("type", 4);
            List<MemberCollectGoods> collectGoodsListlist = memberCollectGoodsMapper.selectByMap(collectMap);
            hashMap.put("count", collectGoodsListlist.size());
        }
        return hashMap;
    }

    /**
     * 获取普通商品
     *
     * @param memberCollectGood
     * @param goodsId
     */
    private HashMap getCommonGoods(MemberCollectGoods memberCollectGood, Integer goodsId, Integer type) {
        //获取goods
        Goods good = goodsMapper.selectById(goodsId);
        HashMap<Object, Object> hashMap = new HashMap<>();
        if (good != null) {
            //收藏表id
            hashMap.put("collectId", memberCollectGood.getId());
            //收藏类型
            hashMap.put("type", memberCollectGood.getType());
            //商品id
            hashMap.put("id", good.getId());
            //商品名字
            hashMap.put("goodsName", good.getGoodsName());
            //获取商品图片
            String[] split = good.getTurnsPhoto().split(",");
            GoodsPhoto goodsPhoto = goodsPhotoMapper.selectById(Integer.parseInt(split[0]));
            if (goodsPhoto != null) {
                //商品图片路径
                hashMap.put("picture", goodsPhoto.getPhotoUrl());
            } else {
                hashMap.put("picture", null);
            }
            //商品价格
            hashMap.put("price", good.getPrice());
            //商品状态(上下架)
            hashMap.put("status", good.getStatus());
            //是否删除
            hashMap.put("isDelete", good.getIsDelete());
            hashMap.put("checked", false);
            //获取收藏数
            Map<String, Object> collectMap = new HashMap<>();
            collectMap.put("goods_id", good.getId());
            collectMap.put("type", type);
            List<MemberCollectGoods> collectGoodsListlist = memberCollectGoodsMapper.selectByMap(collectMap);
            hashMap.put("count", collectGoodsListlist.size());
        }
        return hashMap;
    }

    /**
     * 删除收藏
     *
     * @param ids 收藏主表id
     */
    @Override
    public void deleteCollectGoods(Integer[] ids) {
        for (Integer integer : ids) {
            memberCollectGoodsMapper.deleteById(integer);
        }
    }

    /**
     * 检查商品是否已经被收藏
     *
     * @param memberId 会员id
     * @param goodsId  商品id
     * @param type     收藏类型
     * @return boolean
     */
    @Override
    public boolean checkCollectGoods(Integer memberId, Integer goodsId, Integer type) {
        Map<String, Object> map = new HashMap<>();
        map.put("member_id", memberId);
        map.put("goods_id", goodsId);
        map.put("type", type);
        List<MemberCollectGoods> memberCollectGoods = memberCollectGoodsMapper.selectByMap(map);
        //如果没有收藏该商品
        if (memberCollectGoods == null || memberCollectGoods.size() == 0) {
            return false;
            //如果有收藏该商品
        } else {
            return true;
        }
    }
}
