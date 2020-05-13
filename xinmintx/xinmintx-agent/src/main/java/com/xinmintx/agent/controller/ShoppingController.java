package com.xinmintx.agent.controller;
import com.xinmintx.agent.model.ProcurementCommodities;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/agent/shopping")
public class ShoppingController {
    @Autowired
    private ShoppingService shoppingService;
    /**
     * 跳转采购页面
     * @return
     */
    @GetMapping("/goShopping")
    public String goShopping(Model model,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        List<ProcurementCommodities> procurementCommodities = shoppingService.selectProcurementCommodities(user.getId());
        model.addAttribute("procurementCommodities",procurementCommodities);
        return "purchase";
    }
    /**
     * 跳转采购展示详情
     * @return
     */
    @GetMapping("/selectByIdProcurementCommodities/{id}")
    public String goShopping(@PathVariable("id") int id,Model model){

        ProcurementCommodities procurementCommodities = shoppingService.selectByIdProcurementCommodities(id);
        model.addAttribute("procurementCommodities",procurementCommodities);
        return "shoppingselect";
    }

    /**
     * 跳转采购页面
     * @return
     */
    @GetMapping("/jumpAddShopping")
    public String jumpAddShopping(){
        return "shopping";
    }
    /**
     * 采购商品
     * @param photo
     * @param procurementCommodities
     * @return
     */
    @PostMapping("/addShopping")
    @ResponseBody
    public boolean byShopping(Integer[] photo,ProcurementCommodities procurementCommodities, HttpServletRequest request){
        int i = shoppingService.byShopping(procurementCommodities,photo,request);
        return i > 0;
    }
}
