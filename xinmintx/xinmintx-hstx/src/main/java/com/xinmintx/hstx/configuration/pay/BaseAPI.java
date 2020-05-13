package com.xinmintx.hstx.configuration.pay;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

public class BaseAPI {
    protected static final String BASE_URI = "https://api.weixin.qq.com";
    protected static final String MEDIA_URI = "http://file.api.weixin.qq.com";
    protected static final String QRCODE_DOWNLOAD_URI = "https://mp.weixin.qq.com";
    protected static final String BASE_MCH_URI = "https://api.mch.weixin.qq.com";
    protected static final String OPEN_URI = "https://open.weixin.qq.com";


    protected static final String MCH_UNIFIE_DORDER = "/pay/unifiedorder";
    protected static final String MCH_MICRO_PAY = "/pay/micropay";
    protected static final String MCH_ORDER_QUERY = "/pay/orderquery";
    protected static final String MCH_CLOSE_ORDER = "/pay/closeorder";
    protected static final String MCH_REFUND = "/secapi/pay/refund";
    protected static final String MCH_REFUND_QUERYD = "/pay/refundqueryd";

    protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
    protected static Header xmlHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_XML.toString());
}
