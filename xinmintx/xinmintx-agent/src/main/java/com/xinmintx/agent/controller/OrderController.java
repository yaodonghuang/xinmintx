package com.xinmintx.agent.controller;

import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.UOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api")
public class OrderController {


    @Autowired
    private UOrderService uOrderService;

    /**
     * 微信支付
     *
     * @param request
     * @param totalFee
     * @param body
     * @return
     */
    @PostMapping("/unifiedOrder")
    public Map<String, Object> unifiedorder(HttpServletRequest request,Long totalFee, String body) {
        User user = (User) request.getSession().getAttribute("user");
        Map<String, Object> map = null;
        try {
            //map = orderService.unifiedorder(totalFee, body, user);
        } catch (RuntimeException e) {
            e.printStackTrace();
            map.put("code", "FAIL");
            map.put("msg", "WeChat orders failed");
            map.put("sub_code", "SYSTEMERROR");
        }
        return map;
    }

    /**
     * queryOrder(paipai)
     *
     * @param out_trade_no
     * @return
     */
    @PostMapping("/queryOrder")
    public ResponseEntity<Map<String, String>> queryOrder(String out_trade_no) {
        if (StringUtils.isBlank(out_trade_no)) {
            Map<String, String> map = new HashMap<>();
            map.put("code", "FAIL");
            map.put("msg", "invalid param：订单号不能为空");
            return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
        }
        Map param = new HashMap();
        param.put("out_trade_no", out_trade_no);
        Map<String, String> map = uOrderService.queryOrder(param);
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }

    /**
     * closeorder（Including Alipay and WeChat）
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/closeOrder", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> closeorder(@RequestBody Map<String, String> param) {
        Map<String, String> map = uOrderService.closeorder(param);
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }
}
