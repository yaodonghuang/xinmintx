package com.xinmintx.hstx.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/19 0019
 * @time: 上午 10:02
 * @Description:
 */
@Data
public class PtCodeBean {
    private Integer id;
    private Integer groupId;
    private Integer ptgoodId;
    private String ptcode;
    private Integer ptnumber;
    private Date addtimeDatetime;
    private Date endtimeDatetime;
    private Integer infoId;
    private Integer uid;
    private Integer skuId;
    private BigDecimal perPrice;
    private Integer dreessId;
    private Integer isHeader;
    private Integer lack;
    private Integer goodsOrderId;
    private Integer groupType;
}
