package com.xinmintx.agent.mchpay;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 统一支付请求返回对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WXUnifiedOrderResult extends MchBase {
    @XmlElement
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String device_info;

    @XmlElement
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String trade_type;

    @XmlElement
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String prepay_id;

    @XmlElement
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String code_url;

    @XmlElement
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String sub_appid;

    @XmlElement
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String sub_mch_id;

    @XmlElement
    @XmlJavaTypeAdapter(value = AdaptorCDATA.class)
    private String mweb_url;
}
