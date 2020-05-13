package com.xinmintx.hstx.pojo.bo;

import com.xinmintx.hstx.pojo.po.GoodsSku;
import lombok.Data;

/**
 * <p>
 * 商品sku属性表
 * </p>
 *
 * @author wcj
 * @since 2019-12-25
 */
@Data
public class GoodsSkuBo extends GoodsSku {

    private String photoUrl;
    /**
     * 关联id
     */
    private Integer relateId;
    /**
     * 店家名称
     */
    private String shopName;

    private String goodsName;

    private Integer total;

    private String specName;

    private String[] specValueIds;

    private String s1;

    private String s2;

    private String s3;

    /**
     * 类型,1普通商品,2抢购商品
     */
    private Integer skuType;
}
