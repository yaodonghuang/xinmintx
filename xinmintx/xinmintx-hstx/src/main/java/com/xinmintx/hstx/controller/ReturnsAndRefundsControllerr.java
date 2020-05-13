package com.xinmintx.hstx.controller;


import com.xinmintx.hstx.mapper.GoodsOrderDetailMapper;
import com.xinmintx.hstx.mapper.GoodsOrderMapper;
import com.xinmintx.hstx.pojo.po.GoodsOrder;
import com.xinmintx.hstx.pojo.po.GoodsOrderDetail;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.RefundBenefitService;
import com.xinmintx.hstx.service.ReturnGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2020/1/10 0016
 * @time: 上午 10:25
 * @Description: 退货退款分润扣除
 */
@RestController
@RequestMapping("/hs/divided")
public class ReturnsAndRefundsControllerr {

    @Autowired
    private RefundBenefitService refundBenefitService;

    @Autowired
    private ReturnGoodsService returnGoodsService;

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Autowired
    private GoodsOrderDetailMapper goodsOrderDetailMapper;

    /**
     * 退货分润扣除
     *
     * @param id 子订单id
     * @return
     */
    @PostMapping("/refunds/{id}")
    public ResultCode refunds(@PathVariable("id") Integer id) {
        GoodsOrderDetail goodsOrderDetail = goodsOrderDetailMapper.selectById(id);
        ResultCode<Object> resultCode = new ResultCode<>();
        if (goodsOrderDetail != null) {
            returnGoodsService.returnGoods(goodsOrderDetail, -1);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
        }
        return resultCode;
    }

    /**
     * 退款分润扣除
     *
     * @param id 主订单id
     * @return
     */
    @PostMapping("/returns/{id}")
    public ResultCode returns(@PathVariable("id") Integer id) {
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(id);
        ResultCode<Object> resultCode = new ResultCode<>();
        if (goodsOrder != null) {
            refundBenefitService.refundBenefit(goodsOrder);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
        }
        return resultCode;
    }
}
