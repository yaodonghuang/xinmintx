package com.xinmintx.factory.api.service;

import java.util.Map;

public interface ShoppingUnitService {
    /**
     * userId	用户编号
     * cardNum	卡号
     * phone	手机号
     * name	姓名
     * idCard	用户身份证号
     * @param paramMap
     */
    void tiedCard(Map<String, String> paramMap);
}
