package com.xinmintx.hstx.pojo.vo;

import com.xinmintx.hstx.pojo.bo.GoodsSkuBo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/10 0010
 * @time: 下午 21:37
 * @Description:
 */
@Data
public class GoodsSkuVo {

    /**
     * 参数集合
     */
    private List<GoodsTreeVo> tree;
    /**
     * 组合集合
     */
    private List<GoodsSkuBo> list;

    /**
     * 商品属性
     */
    private GoodsVo goods;
}
