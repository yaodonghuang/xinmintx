package com.xinmintx.factory.api.controller;


import com.xinmintx.factory.api.common.ResultCode;
import com.xinmintx.factory.api.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: com.xinmintx.factory.controller.LogisticsController
 * @Author:Pikachu
 * @Date: 2019/12/5 16:14
 * @Version: v1.0
 */
@RestController
@RequestMapping("/logistics")
public class LogisticsController {
    /**
     * 对接物流
     * @param no
     * @param type
     * @return
     */
    @RequestMapping("/queryLogistics")
   public ResultCode queryLogistics(@RequestParam("no") String no,@RequestParam("type") String type) throws Exception {
       String host = "https://wuliu.market.alicloudapi.com";
       String path = "/kdi";
       String method = "GET";
       String appcode = "1f76542d13c04df98e08955454d05e62";  // !!!替换填写自己的AppCode 在买家中心查看
       Map<String, String> headers = new HashMap<String, String>();
       headers.put("Authorization", "APPCODE " + appcode); //格式为:Authorization:APPCODE 83359fd73fe11248385f570e3c139xxx
       Map<String, String> querys = new HashMap<String, String>();
       querys.put("no", no);// !!! 请求参数
       querys.put("type", type);// !!! 请求参数
           /**
            * 重要提示如下:
            * HttpUtils请从
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
            * 或者直接下载：
            * http://code.fegine.com/HttpUtils.zip
            * 下载
            *
            * 相应的依赖请参照
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
            * 相关jar包（非pom）直接下载：
            * http://code.fegine.com/aliyun-jar.zip
            */
           HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
           //System.out.println(response.toString());//如不输出json, 请打开这行代码，打印调试头部状态码。
           //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
           //获取response的body
         ResultCode code = new ResultCode();
        code.setData(EntityUtils.toString(response.getEntity())); //输出json
        return code;
    }
}
