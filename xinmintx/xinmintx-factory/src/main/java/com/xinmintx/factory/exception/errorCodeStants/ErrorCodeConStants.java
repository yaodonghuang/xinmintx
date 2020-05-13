package com.xinmintx.factory.exception.errorCodeStants;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/10
 * @time: 11:32
 * @Description:
 */

public enum ErrorCodeConStants {
    SYSTEM_EX_OTHER_CODE("500", "系统异常"),
    SYSTEM_ABNORMAL_TOKEN("501","TOKEN异常"),
    SYSTEM_OBJECT_ALREADY_EXISTS("502","对象已存在"),
    SYSTEM_OBJECT_ALREADY_NONENTITY("503","查询对象不存在"),
    SYSTEM_PARAM_NULL("505","参数不存在"),
    SYSTEM_RESULT_NULL("600","状态未更新"),
    SYSTEM_GOODS_STATUS_ERR("650","商品已被平台下架，禁止用户上架"),
    OCCUPIED("","")
    ;
    private String code;
    private String msg;

    ErrorCodeConStants(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}


