package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.pojo.po.Factory;
import com.xinmintx.hstx.pojo.po.Merchant;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.SettleInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  入驻controller
 * @author hyd
 */
@RestController
@RequestMapping("/hs/settle")
public class JoinUsController {
    @Autowired
    private SettleInService settleInService;

    /**
     *  商户入驻
     * @param merchant
     * @return
     */
    @PostMapping("/merchantSettleIn")
    public ResultCode merchantSettleIn(@RequestBody Merchant merchant){
        return settleInService.merchantSettleIn(merchant);
    }

    /**
     *  工厂入驻
     * @param factory
     * @return
     */
    @PostMapping("/factorySettleIn")
    public ResultCode factorySettleIn(@RequestBody Factory factory){
        return settleInService.factorySettleIn(factory);
    }

}
