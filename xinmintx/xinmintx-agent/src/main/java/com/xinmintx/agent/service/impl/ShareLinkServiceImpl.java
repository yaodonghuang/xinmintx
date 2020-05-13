package com.xinmintx.agent.service.impl;

import com.alibaba.fastjson.JSON;
import com.xinmintx.agent.service.ShareLinkService;
import com.xinmintx.agent.util.MapUtil;
import com.xinmintx.agent.util.OpenIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:.ShareServiceImpl
 * @author:chf
 * @Date:2019/12/5：10:17
 * @developerKits： win 10     jdk1.8
 */
@Slf4j
@Service
public class ShareLinkServiceImpl implements ShareLinkService {
    @Autowired
    private OpenIdUtils openIdUtils;

    @Override
    public Map<String,String> ShareLink(String url) {
        String jsapiTicket = openIdUtils.getJsapiTicket();
        Map<String, String> signMap = new HashMap<>();
        long millis = System.currentTimeMillis();
        signMap.put("timestamp", String.valueOf(millis / 1000));
        signMap.put("noncestr", String.valueOf(millis));
        signMap.put("url",url);
        signMap.put("jsapi_ticket",jsapiTicket);
        String sign = generateSignature(signMap);
        signMap.put("signature",sign);
        log.info(JSON.toJSONString(signMap));
        return signMap;
    }

    public String generateSignature(Map<String, String> map) {
        Map<String, String> tmap = MapUtil.order(map);
        if (tmap.containsKey("signature")) {
            tmap.remove("signature");
        }
        log.info(JSON.toJSONString(tmap));
        String str = MapUtil.mapJoin(tmap, false, false);
        return DigestUtils.sha1Hex(str);
    }
}
