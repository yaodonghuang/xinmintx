package com.xinmintx.hstx.service.impl;

import com.xinmintx.hstx.pojo.vo.GoodsSkuVo;
import com.xinmintx.hstx.mapper.GoodPtcodeMapper;
import com.xinmintx.hstx.mapper.GoodPtgoodMapper;
import com.xinmintx.hstx.pojo.po.GoodPtcode;
import com.xinmintx.hstx.pojo.po.GoodPtgood;
import com.xinmintx.hstx.service.GoodsService;
import com.xinmintx.hstx.service.PtGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/17 0017
 * @time: 下午 16:25
 * @Description:
 */
@Service
public class PtGoodsServiceImpl implements PtGoodsService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodPtgoodMapper goodPtgoodMapper;
    @Autowired
    private GoodPtcodeMapper goodPtcodeMapper;

    @Override
    public GoodsSkuVo getPtGoodsSku(Integer id) {
        //按照商品id和人数获取拼团的商品
        GoodPtgood goodPtgood = goodPtgoodMapper.selectById(id);
        return goodsService.getSkuById(goodPtgood.getGoodsId(), goodPtgood.getPtPrice());
    }

    @Override
    public GoodsSkuVo getPtGoodsSkuByCodeId(Integer id) {
        GoodPtcode goodPtcode = goodPtcodeMapper.selectById(id);
        GoodPtgood goodPtgood = goodPtgoodMapper.selectById(goodPtcode.getPtgoodsId());
        return goodsService.getSkuById(goodPtgood.getGoodsId(), goodPtgood.getPtPrice());
    }
}
