package com.xinmintx.agent.mchpay;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 统一支付请求参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WXUnifiedOrder extends MchBaseRequest {
    private static final long serialVersionUID = 1L;

    @XmlElement
    private String device_info;

    @XmlElement
    //@XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String body;

    @XmlElement
    //@XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String attach;

    @XmlElement
    private String total_fee;

    @XmlElement
    private String spbill_create_ip;

    @XmlElement
    private String time_start;

    @XmlElement
    private String time_expire;

    @XmlElement
    private String goods_tag;

    @XmlElement
    //@XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String notify_url;

    @XmlElement
    private String trade_type;

    @XmlElement
    private String sign_type;

    @XmlElement
    private String openid;

    @XmlElement
    private String product_id;

    //场景信息
    @XmlElement
    private String scene_info;

    //子商户公众账号ID
    @XmlElement
    private String sub_appid;
    //子商户号
    @XmlElement
    private String sub_mch_id;
}
