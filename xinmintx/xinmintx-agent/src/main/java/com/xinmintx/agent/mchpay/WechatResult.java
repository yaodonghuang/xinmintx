package com.xinmintx.agent.mchpay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WechatResult implements Serializable {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    private static final long serialVersionUID = 1L;
    protected String return_code;

    protected String return_msg;

    public WechatResult() {
    }

    public WechatResult(String returnCode) {
        this.return_code = returnCode;
    }

    public WechatResult(String returnCode, String returnMsg) {
        this.return_code = returnCode;
        this.return_msg = returnMsg;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

}
