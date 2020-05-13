package com.xinmintx.hstx.pojo.vo;

import com.xinmintx.hstx.pojo.po.GoodsOrderDetail;
import lombok.Data;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author wcj
 * @since 2019-12-25
 */
@Data
public class GoodsOrderDetailVo extends GoodsOrderDetail {
    private String specValue;
}
