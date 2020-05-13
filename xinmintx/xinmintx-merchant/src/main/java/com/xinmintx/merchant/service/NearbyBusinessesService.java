package com.xinmintx.merchant.service;

import com.xinmintx.merchant.common.ResultCode;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2020/3/17 14:37
 * @Description:       获取附近商户
 */
public interface NearbyBusinessesService {
    ResultCode queryNearbyBusinesses (double latitude,double longitude);
}
