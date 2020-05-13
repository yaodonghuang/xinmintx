package com.xinmintx.factory.controller;

import com.xinmintx.factory.model.CashierDesk;
import com.xinmintx.factory.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xinmintx.factory.common.ResultCode;
/**
 * @ClassName: com.xinmintx.factory.controller.StatisticsController
 * @Author:Pikachu
 * @Date: 2019/12/6 16:15
 * @Version: v1.0
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
@Autowired
private StatisticsService statisticsService;

    /**
     * 收银台
     * @param token
     * @param beginDate
     * @param endDate
     * @return
     */
    @RequestMapping("/queryCount")
    public ResultCode queryCount(@RequestHeader("token") String token , @RequestParam("beginDate")String beginDate, @RequestParam("endDate") String endDate)
    {
        ResultCode code=new ResultCode();
        CashierDesk desk= new CashierDesk();
        long size = statisticsService.queryOrderSize(token,beginDate,endDate);
        if(size!=0&&size>0){
            desk.setOrderSize(size);
        }else {
            code.setMsg("总订单数据不存在");
        }
        Double turnover =statisticsService.queryTurnover(token,beginDate,endDate);
        if (turnover!=null &&turnover>0){
            desk.setTurnover(turnover);
        }else {
            desk.setTurnover(Double.valueOf("0"));
        }
        code.setCode(200);
        code.setData(desk);
        return code;
    }
}
