package com.xinmintx.agent.controller;
import com.xinmintx.agent.model.CommodityExt;
import com.xinmintx.agent.model.CommodityKind;
import com.xinmintx.agent.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Controller
@RequestMapping("/agent/group")
public class GroupController {
    @Autowired
    private GoodsService goodsService;
    @RequestMapping("/jumpGroup/{id}")
    public String jumpGroup(@PathVariable("id") Integer id, Model model){
        CommodityExt commodityExt = goodsService.getGoodById(id);
        String pictyre[] = goodsService.getPictures(id);
        List<CommodityKind> list = goodsService.getGoodType(id);
        List<CommodityExt> type = goodsService.getType(id);
        model.addAttribute("typeList",list);
        model.addAttribute("type",type.get(0));
        model.addAttribute("pictyre",pictyre);
        model.addAttribute("commodityExt",commodityExt);
        return "group";
    }
}
