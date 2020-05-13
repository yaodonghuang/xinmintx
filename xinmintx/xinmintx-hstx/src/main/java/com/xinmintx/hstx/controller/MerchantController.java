package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.MerchantVo;
import com.xinmintx.hstx.pojo.po.Merchant;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController extends BaseController {
    @Autowired
    private MerchantService merchantService;

    @Autowired
    private IMemberService IMemberService;

    /**
     * 添加商户
     *
     * @param merchantVo
     * @return
     */
    @PostMapping("/addMerchant")
    public ResultCode addMerchant(MerchantVo merchantVo) {
        merchantVo.setToken(token);
        ResultCode resultCode = new ResultCode();
        Member member = IMemberService.findMemberByToken(merchantVo.getToken());
        if (member == null) {
            resultCode.setMsg("FAIL");
            resultCode.setCode(500);
            return resultCode;
        }

        Merchant merchant = merchantService.addMerchant(merchantVo);
        if (merchant != null) {
            Map map = merchantService.createOrder(merchant, member);
            if (map != null){
                resultCode.setData(map);
                resultCode.setCode(200);
                resultCode.setMsg("SUCCESS");
            }else{
                resultCode.setMsg("FAIL");
                resultCode.setCode(500);
            }
        } else {
            resultCode.setMsg("FAIL");
            resultCode.setCode(500);
        }
        return resultCode;
    }
}
