package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Factory;
import com.xinmintx.hstx.pojo.po.Merchant;
import com.xinmintx.hstx.pojo.vo.ResultCode;

public interface SettleInService {
    ResultCode merchantSettleIn(Merchant merchant);

    ResultCode factorySettleIn(Factory factory);
}
