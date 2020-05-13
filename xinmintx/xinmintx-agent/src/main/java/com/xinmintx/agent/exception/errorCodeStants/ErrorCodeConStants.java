package com.xinmintx.agent.exception.errorCodeStants;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/10
 * @time: 11:32
 * @Description:
 */

public enum ErrorCodeConStants {
    OBJECT_ALREADY_NONENTITY_1("200", "没有活动"),
    EX_OTHER_CODE("500", "系统异常"),
    ABNORMAL_TOKEN("501","token异常"),
    OBJECT_ALREADY_EXISTS("502","对象已存在"),
    OBJECT_ALREADY_NONENTITY("503","对象不存在"),
    PARAM_ALREADY_NONENTITY("503","参数不能为空"),
    ABNORMAL_SQL("504","sql异常，sql 未执行成功"),
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


