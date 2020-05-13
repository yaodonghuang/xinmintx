package com.xinmintx.merchant.common;

public enum ErrorCodeConstants {

  EX_OTHER_CODE("500", "系统异常"),

  MERCHANT_NOT_EXIST_ERR("501", "该手机号码无法查到供应商"),

  TOKEN_ERR("502", "  token不存在,或者用户未登陆,请重新登陆"),

  VOTING_ENDED_ERR("503", "投票结束"),

  VOTINGED_ERR("504", "你已投票"),

  MERCHANT_NOT_FOUNT_ERR("505", "  商户不存在"),

  SMS_CODE_ERR("506", "  验证码错误"),

  BIND_CARD_ERR("507", "  绑卡失败"),

  REPEAT_BIND_CARD_ERR("508", "该卡已被绑定"),

  DEFAULT("", "");

    private String code;
  private String msg;

  private ErrorCodeConstants(String code, String msg) {
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
