package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.MemberAddressVo;
import com.xinmintx.hstx.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangkang
 */
@RestController
@RequestMapping("/api/shippingAddress")
public class ShippingAddressController extends BaseController {

    @Autowired
    private ShippingAddressService shippingAddressService;



    /**
     * 添加收货地址
     *
     * @param memberAddressVo
     * @returnr
     */
    @RequestMapping("/addmemberAddress")
    public ResultCode addShippingAddress(MemberAddressVo memberAddressVo) {
        ResultCode resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        memberAddressVo.setToken(token);
        int i = shippingAddressService.addShippingAddress(memberAddressVo);
        if (i > 0) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        }
        return resultCode;
    }

    /**
     * 查看消费者收货地址
     *
     * @param
     * @return
     */
    @RequestMapping("/selectmemberAddress")
    public ResultCode selectShippingAddress() {
        ResultCode resultCode = new ResultCode<>();
        List<MemberAddressVo> salist = shippingAddressService.selectShippingAddress(token);
        if (null == salist || salist.size() == 0) {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
            return resultCode;
        }
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(salist);
        return resultCode;
    }

    /**
     * 修改消费者收货地址
     *
     * @param id
     * @return
     */
    @PostMapping("/editAddress")
    public ResultCode editShippingAddress(int id) {
        ResultCode resultCode = new ResultCode<>();
        MemberAddressVo memberAddressVo = shippingAddressService.editShippingAddress(id);
        if (memberAddressVo == null) {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
            return resultCode;
        }
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(memberAddressVo);
        return resultCode;
    }

    /**
     * 保存修改的收货地址
     *
     * @param memberAddressVo
     * @return
     */
    @RequestMapping("/updateAddress")
    public ResultCode updateShippingAddress(MemberAddressVo memberAddressVo) {
        ResultCode resultCode = new ResultCode<>();
        memberAddressVo.setToken(token);
        int i = shippingAddressService.updateShippingAddress(memberAddressVo);
        if (i > 0) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            return resultCode;
        }
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        return resultCode;
    }

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteAddress")
    public ResultCode deleteShippingAddress(int id) {
        ResultCode resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        int i = shippingAddressService.deleteShippingAddress(id);
        if (i > 0) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        }

        return resultCode;
    }

    /**
     * 查询默认收货地址
     *
     * @return
     */
    public ResultCode selectDefaultAddress() {
        ResultCode resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        MemberAddressVo memberAddressVo = shippingAddressService.selectDefaultAddress(token);
        if (memberAddressVo != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(memberAddressVo);
        }
        return resultCode;
    }



}
