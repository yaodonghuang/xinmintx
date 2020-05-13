package com.xinmintx.merchant.common;

public class ResultCode<T> {

    private int code;
    private String msg;
    private T data;

    public ResultCode() {
        this.code = 200;
        this.msg = "success";
    }

    public ResultCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
