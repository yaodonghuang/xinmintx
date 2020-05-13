package com.xinmintx.hstx.pojo.vo;

import com.xinmintx.hstx.pojo.po.GoodsOrderDetailInterim;
import com.xinmintx.hstx.pojo.po.GoodsOrderParentInterim;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/12 0012
 * @time: 上午 9:49
 * @Description:
 */
@Data
public class GoodsOrderParentInterimVo extends GoodsOrderParentInterim {
    private List<GoodsOrderDetailInterim> details;
}
