package com.xinmintx.agent.controller;

import com.xinmintx.agent.model.CommodityType;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.modelDTO.CommodityAndImage;
import com.xinmintx.agent.service.ProcurementMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/agent/procurementMarket")
public class ProcurementMarketController {

    @Autowired
    private ProcurementMarketService procurementMarketService;
    /**
     * 跳转商品
     * @return
     */
    @GetMapping("/jumpProcurementMarket")
    public String jumpProcurementMarket(Model model){
        //商品分类
        List<CommodityType> commodityTypeList = procurementMarketService.selectShoppingType();
        model.addAttribute("commodityTypeList",commodityTypeList);
        return "procurement";
    }

    /**
     * 跳转商品正在销售
     * @return
     */
    @GetMapping("/jumpOnSale")
    public String jumpOnSale(Model model, HttpServletRequest request){
        User attribute = (User)request.getSession().getAttribute("user");
        List<CommodityAndImage> commodityAndImages = procurementMarketService.commodityAndImageList(attribute.getId());
        model.addAttribute("commodityAndImages",commodityAndImages);
        return "doing";
    }

    /**
     * 根据类型查找 商品
     * @param id
     * @return
     */
    @GetMapping("/selectByTypeShopping")
    @ResponseBody
    public List<CommodityAndImage> selectByTypeShopping(Integer id){
        List<CommodityAndImage> commodityAndImages = procurementMarketService.selectByTypeShopping(id);
        return commodityAndImages;
    }
    /**
     * 根据类型查找 热销
     * @param id
     * @return
     */
    @GetMapping("/selectHotShopping")
    @ResponseBody
    public List<CommodityAndImage> selectHotShopping(Integer id){
        List<CommodityAndImage> commodityAndImages = procurementMarketService.selectHotShopping(id);
        return commodityAndImages;
    }
}
