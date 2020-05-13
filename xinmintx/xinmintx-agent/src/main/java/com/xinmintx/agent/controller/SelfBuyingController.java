package com.xinmintx.agent.controller;
import java.math.BigDecimal;
import java.util.Date;

import com.xinmintx.agent.common.PayBean;
import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.configuration.pay.PayConfig;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.AddressService;
import com.xinmintx.agent.service.GoodOrder;
import com.xinmintx.agent.service.GoodsService;
import com.xinmintx.agent.service.SelfBuyingService;
import com.xinmintx.agent.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/agent/SelfBuying")
public class SelfBuyingController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private SelfBuyingService selfBuyingService;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodOrder goodOrder;
    /**
     *商品支付
     * @return
     */
    @PostMapping("/confirmPayment")
    @ResponseBody
    public ResultCode confirmPayment(int id,int addressId,int typeId, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        ShippingAddress shippingAddress = addressService.selectAddress(addressId);//地址
        CommodityExt goodById = goodsService.getGoodById(id);//商品信息
        ResultCode resultCode = new ResultCode();//返回值
        PayBean payBean = new PayBean();
        payBean.setUserId(user.getId());
        payBean.setBody(goodById.getCommodityName());
        payBean.setTotalFee((long)goodById.getPrice());
        payBean.setOpenId(user.getOpenid());
        payBean.setNotifyUrl(PayConfig.WECHAT_NOTIFY_URL2);
        Map<String, Object> unifiedorder = selfBuyingService.unifiedorder(payBean);//创建订单表
        //修改库存 销量 创建（商家）订单 详情
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setUserId(user.getId());
        goodsOrder.setOrderNum((String)unifiedorder.get("out_trade_no"));
        goodsOrder.setOrderState(0);
        goodsOrder.setCreateTime(DateUtils.getNowDate());
        goodsOrder.setUpdateTime(DateUtils.getNowDate());
        goodsOrder.setIfPay(0);
        goodsOrder.setReceiveAddress(shippingAddress.getAddress());
        goodsOrder.setReceiveName(user.getName());
        goodsOrder.setReceivePhone(shippingAddress.getCellphone());
        goodsOrder.setReceiveMessage("");
        goodsOrder.setTotalAmount(BigDecimal.valueOf(goodById.getPrice()/100));
        goodsOrder.setuOrderId((Integer) unifiedorder.get("order_id"));
        //创建商家订单表
        goodOrder.insert(goodsOrder);
        //创建订单详情表
        GoodsOrderDetail detail = new GoodsOrderDetail();
        detail.setOrderId((Integer) unifiedorder.get("order_id"));
        detail.setCommodityId(goodById.getId());
        detail.setCommodityTypologyId(typeId);//规格id
        detail.setKindId(goodById.getKindId());
        //detail.setFactoryId(goodById.); //厂家id
        detail.setPrice(BigDecimal.valueOf(goodById.getPrice()));
        detail.setQuantity(1);
        detail.setCommodityName(goodById.getCommodityName());
        detail.setCommodityPic(goodById.getKindUrl());
        detail.setSendState("0");
        goodOrder.insertDetail(detail);
        resultCode.setData(unifiedorder);
        return resultCode;
    };

    /**
     * 跳转订单表
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/jumpOrder")
    public String jumpOder(Integer id, Model model){
        CommodityExt commodityExt = goodsService.getGoodById(id);
        String pictyre[] = goodsService.getPictures(id);
        List<CommodityExt> type = goodsService.getType(id);
        model.addAttribute("commodityExt",commodityExt);
        model.addAttribute("pictyre",pictyre[0]);
        System.out.println(type.get(0));
        model.addAttribute("type",type.get(0));
        return "order";
    };

    /**
     * 查找用户默认地址
     * @param request
     * @return
     */
    @PostMapping("/selectAddress")
    @ResponseBody
    public ShippingAddress selectAddress(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        ShippingAddress shippingAddress = addressService.shippingAddress(user.getId());
        return shippingAddress;
    };

    /**
     * 循环查询订单状态
     * @param out_trade_no 订单号
     * @return
     */
    @RequestMapping("/queryPayStatus")
    @ResponseBody
    public ResultCode queryPayStatus(String out_trade_no){
        HashMap<String, String> map1 = new HashMap<>();
        ResultCode<Object> objectResultCode = new ResultCode<>();
        map1.put("out_trade_no",out_trade_no);
        while(true){
            //调用查询接口
            Map<String,String> map = selfBuyingService.queryOrder(map1);
            if(map==null){//出错
                objectResultCode.setMsg("未付款");
                break;
            }
            if(map.get("code").equals("SUCCESS")){//如果成功
                objectResultCode.setMsg("已收到付款");
                break;
            }
            try {
                Thread.sleep(3000);//间隔三秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return objectResultCode;
    }
}
