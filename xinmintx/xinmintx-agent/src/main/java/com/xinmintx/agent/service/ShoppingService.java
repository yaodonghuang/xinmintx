package com.xinmintx.agent.service;
import com.xinmintx.agent.model.ProcurementCommodities;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ShoppingService {
    int byShopping(ProcurementCommodities procurementCommodities, Integer[] photo,HttpServletRequest request);

    List<ProcurementCommodities> selectProcurementCommodities(int id);

    ProcurementCommodities selectByIdProcurementCommodities(int id);
}
