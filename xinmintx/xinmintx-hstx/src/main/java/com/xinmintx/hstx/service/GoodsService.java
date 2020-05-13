package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.bo.GoodsSkuBo;
import com.xinmintx.hstx.pojo.po.GoodPanicBuy;
import com.xinmintx.hstx.pojo.po.GoodsSku;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.GoodsSkuVo;
import com.xinmintx.hstx.pojo.vo.GoodsTypeVo;
import com.xinmintx.hstx.pojo.vo.GoodsVo;
import com.xinmintx.hstx.pojo.vo.PageData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface GoodsService {

    List<GoodsTypeVo> getGoodsTypes();

    List<GoodsVo> getGoodList(String name, Integer goodsType, PageData pageData);

    GoodsVo getGoodById(Integer id, String token);

    GoodsSkuVo getMemberSkuByGoodsId(Integer id, String token);

    void setMemberGoodsSkuPrice(GoodsSku goodsSku, Member member);

    void setMemberGoodsSkuPrice(GoodsSkuBo goodsSkuBo, Member member);

    BigDecimal getGoodsSkuPriceByMemberType(GoodsSku goodsSku, Integer memberType);

    GoodsSkuBo getGoodsSkuBo(GoodsSku goodsSku, Integer total);

    GoodsSkuVo getSkuById(Integer id, BigDecimal ptPrice);

    List<GoodsVo> getGoodsByType(Integer id, Integer price, Integer sales, PageData pageData);

    List<Map<String, Object>> getGoodsFeaturedFirst();

    List<GoodsVo> getPtGoods(PageData pageData);

    GoodsVo getPtGoodById(Integer id);

    GoodsVo getPtGoodByAgent(Integer id);

    List<GoodsVo> getGoodPanicBuy(PageData pageData, String token);

    void setMemberPanicSkuPrice(GoodPanicBuy goodPanicBuy, GoodsSku goodsSku, Member member);

    void setMemberPanicSkuPrice(GoodPanicBuy goodPanicBuy, GoodsSkuBo goodsSkuBo, Member member);

    GoodsVo getGoodByPanicId(Integer id, String token);

    GoodsSkuVo getMemberSkuByPanicId(Integer id, String token);

    int getMemberRestriction(GoodPanicBuy goodPanicBuy, Integer memberId);

    boolean checkMemberRestriction(GoodPanicBuy goodPanicBuy, Integer memberId, Integer quantity);
}
