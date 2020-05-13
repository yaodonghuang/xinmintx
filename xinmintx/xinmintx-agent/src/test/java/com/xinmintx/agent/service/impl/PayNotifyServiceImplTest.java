package com.xinmintx.agent.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinmintx.agent.model.PayNotify;
import com.xinmintx.agent.service.PayNotifyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/19 0019
 * @time: 下午 16:03
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayNotifyServiceImplTest {
   @Autowired
   private PayNotifyService service;

    @Test
    public void wechatMemberPayNotify() {
        String json = "{\"completeDate\":\"20200119140227\",\"merchantId\":\"00000000035514\",\"notifyTyp\":\"0\",\"notifyUrl\":\"http://agent2.xinmintx.cn/api/wechatPayNotify\",\"orderAmt\":\"500000\",\"orderId\":\"1579517079577\",\"payOrdNo\":\"P020011900001946\",\"settleDate\":\"20200119140227\",\"signType\":\"CFCA\",\"signature\":\"MIIEngYJKoZIhvcNAQcCoIIEjzCCBIsCAQExCzAJBgUrDgMCGgUAMAsGCSqGSIb3DQEHAaCCA5gwggOUMIICfKADAgECAgUQNTFHgTANBgkqhkiG9w0BAQUFADAhMQswCQYDVQQGEwJDTjESMBAGA1UEChMJQ0ZDQSBPQ0ExMB4XDTE1MTEwOTEwMTI1MFoXDTE3MTEwOTEwMTI1MFowgY4xCzAJBgNVBAYTAkNOMQ4wDAYDVQQKEwVHWlNXVDEOMAwGA1UECxMFR1pTV1QxGTAXBgNVBAsTEE9yZ2FuaXphdGlvbmFsLTExRDBCBgNVBAMMOzA1MUDlub/kuJzmnpflronnianmtYHlj5HlsZXmnInpmZDlhazlj7hAODQ0MDAwMDAwMDA3MjkwM0AxMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKvl7h7yHgvmpc3Q0WGvMFt8TbobhYvGL3fC7wucU58q7df+ETPlHS11ZKcrtb/aIeKGJWn0noy2ZNcaaSekbEHA1hFYagS2fCT/eLErpIzKN+EJ788Al2sT8I8ZpPhCvRQt7WCZcrMgtYLWBfDVQGb50g3w9U9vMznMIJdGej5wIDAQABo4HoMIHlMB8GA1UdIwQYMBaAFNHb6YiC5d0aj0yqAIy+fPKrG/bZMEgGA1UdIARBMD8wPQYIYIEchu8qAQEwMTAvBggrBgEFBQcCARYjaHR0cDovL3d3dy5jZmNhLmNvbS5jbi91cy91cy0xNC5odG0wNwYDVR0fBDAwLjAsoCqgKIYmaHR0cDovL2NybC5jZmNhLmNvbS5jbi9SU0EvY3JsMjMyNS5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBQouFyhEREnXSLgQinPf7USxv+BxTATBgNVHSUEDDAKBggrBgEFBQcDAjANBgkqhkiG9w0BAQUFAAOCAQEAFZdexrxIdO611kvBw5dgjv6i5+p6GPmor79YOl1SrfyH5srPKsTyBHn9BGnAIjAJiGAkNl9CSGXxtvPCNxsyUTHY3W1NTRSn81Me+VgnSVER9I0KNI97pqPx16pbjruNS9WUURG7jeJU2FCRKP/6BIPHVkoEgpTjkdjtW7/Ota8p9CnqBi2mmApMNOv6TqfBwPX1uBddAfWqJBVz9QvU7MB10n8osNKe443hLNSZMTTBOQQu4LzWbH+hrMmiwLEPRFaKd1EA5S2AjLL2FXjYmbTbuFupMkd2ISx3LFa+SoxKTC6nGDnZMth4vXBLCU4VRmtmjVNCukTobGKLF6KSOzGBzzCBzAIBATAqMCExCzAJBgNVBAYTAkNOMRIwEAYDVQQKEwlDRkNBIE9DQTECBRA1MUeBMAkGBSsOAwIaBQAwDQYJKoZIhvcNAQEBBQAEgYC7tqVQpeftFDWSc/XAaad5EHPGmMt3iz3ITTzyllNyCzXu3nqcy5qKLg1LyxtLIy458o9W/LPBP+hS0HzP/IJy2JX9LhKyGhnDl6K1c1r8nBlVBbGxirs2sNx3iPNwH2vXlTeHVPp6fWCNkafheNdXL5dS+BaCLgTcrdCc9heBEA==\",\"status\":\"1\",\"versionId\":\"5.0.0\"}";
        PayNotify payNotify = JSONObject.parseObject(json, PayNotify.class);
        service.partner(payNotify);
    }
}
