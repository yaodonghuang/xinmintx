package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.ShopVo;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/18 0018
 * @time: 上午 9:59
 * @Description:
 */
public interface PtOrderService {
    ResultCode initiateGroup(ShopVo shopVo);

    ResultCode joinGroup(ShopVo shopVo);
}
