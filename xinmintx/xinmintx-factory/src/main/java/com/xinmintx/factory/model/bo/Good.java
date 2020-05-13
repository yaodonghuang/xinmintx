package com.xinmintx.factory.model.bo;

import com.xinmintx.factory.model.vo.GoodsSkuVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class Good {
    private Integer productCategoryId;//商品分类id
    private String name;//商品名称
    private String subTitle;//商品副标题
    private List<String> pic;//轮播图
    private Integer publishStatus;//是否上架
    private String  description;//商品介绍
    private Integer newSalesvolume;//初始销量
    private BigDecimal price;//售价
    private BigDecimal originalPrice;//划线价
    private BigDecimal turePrice;//供货价
    private String detailHtml;//富文本
    private List<Map<String,String>> shopCanshu;//商品参数
    private GoodsSkuVo goodsSkuVo;
}
