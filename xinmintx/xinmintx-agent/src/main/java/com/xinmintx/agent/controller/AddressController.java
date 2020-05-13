package com.xinmintx.agent.controller;

import com.xinmintx.agent.model.ShippingAddress;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/agent/address")
public class AddressController {

    @Autowired
    private AddressService  addressService;

    /**
     * 查询地址
     * @param request
     * @return
     */
    @RequestMapping("/selectAddres")
    public String selectAddres(HttpServletRequest request, Model model){
        User user = (User)request.getSession().getAttribute("user");
        List<ShippingAddress> list = addressService.selectAddres(user.getId());
        model.addAttribute("list",list);
        return "location";
    }

    @RequestMapping("/selectDefault")
    public String selectDefault (HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        ShippingAddress address = addressService.shippingAddress(user.getId());
        model.addAttribute("address",address);
        return "order";
    }

    /**
     * 查询地址列表
     * @return
     */
    @RequestMapping("/addAddress")
    public String addAddress(){
        return "location_add";
    }

    /**
     * 新增地址
     * @return
     */
    @RequestMapping("/saveAddress")
    @ResponseBody
    public boolean saveAddress(ShippingAddress shippingAddress,HttpServletRequest request ,Integer bt){
        User user = (User)request.getSession().getAttribute("user");
        Integer id = user.getId();
        shippingAddress.setMemberId(id);
        shippingAddress.setDefaultAddress(bt);
        if(bt == 1){
            int i = addressService.updateDefaultAddress(id);
        }
        int i = addressService.saveAddress(shippingAddress);
        return i>0;
    }

    /**
     * 查询修改
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getUpdate")
    public String getAddress(Integer id ,Model model){
        ShippingAddress shippingAddress = addressService.selectAddress(id);
        model.addAttribute("shippingAddress",shippingAddress);
        return "location_update";
    }

    /**
     * 修改地址
     * @param shippingAddress
     * @param bt
     * @param request
     * @return
     */
    @RequestMapping("/updateAddress")
    @ResponseBody
    public boolean updateAddress(ShippingAddress shippingAddress,Integer bt,HttpServletRequest request ){
        User user = (User)request.getSession().getAttribute("user");
        shippingAddress.setMemberId(user.getId());
        shippingAddress.setDefaultAddress(bt);
        ShippingAddress address = addressService.shippingAddress(user.getId());
        if (address!=null){
            address.setDefaultAddress(0);
            address.setMemberId(user.getId());
            addressService.updateAddress(address);
        }
        int i = addressService.updateAddress(shippingAddress);
        return i>0;
    }

}
