package com.xinmintx.merchant.controller;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.service.NearbyBusinessesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2020/3/17 14:27
 * @Description: 附近的商户
 */
@RestController
@RequestMapping("/nearbyBusinesses")
public class NearbyBusinessesController {

    @Autowired
    private NearbyBusinessesService nearbyBusinessesService;

    /**
     * 根据当前地址获取附近商户
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @return ResultCode
     */
    @PostMapping("/queryNearbyBusinesses")
    public ResultCode queryNearbyBusinesses(double latitude, double longitude) {
        return nearbyBusinessesService.queryNearbyBusinesses(latitude, longitude);
    }

}
