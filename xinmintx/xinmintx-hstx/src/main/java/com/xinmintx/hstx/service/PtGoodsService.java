package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.vo.GoodsSkuVo;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/17 0017
 * @time: 下午 16:22
 * @Description:
 */
public interface PtGoodsService {

    GoodsSkuVo getPtGoodsSku(Integer id);

    GoodsSkuVo getPtGoodsSkuByCodeId(Integer id);
}
