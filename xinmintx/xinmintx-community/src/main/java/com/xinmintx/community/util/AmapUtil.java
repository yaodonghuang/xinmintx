package com.xinmintx.community.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.community.model.amap.AmapEntity;
import com.xinmintx.community.model.amap.DistantResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 高德地图工具类
 */
public class AmapUtil {
    private Logger logger = LoggerFactory.getLogger(AmapUtil.class);
    //高德地图key
    private static String key = "e6f18e67884d76d8fe1d2142d8dcde86";

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                logger.info(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
//            logger.info("发送GET请求出现异常！" + e.getMessage());
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    /**
     * api 距离测量
     * https://lbs.amap.com/api/webservice/guide/api/direction#distance
     *
     * @param origins      起点经纬度:116.481028,39.989643|114.481028,39.989643|115.481028,39.989643  坐标对见用“| ”分隔；经度和纬度用","分隔
     * @param destination  终点经纬度
     * @param type    路径计算的方式和方法 0：直线距离1：驾车导航距离2：公交规划距离3：步行规划距离
     * @param output 返回数据格式类型 JSON  ,XML
     * @return Map<String,Integer>  {1=260296, 2=103903, 3=198544}   key 坐标序号  val距离
     * @throws IOException
     */
    public static Map<Integer,Integer> getDistanceList(String origins, String destination, String type,String output){
        if(StringUtils.isBlank(origins)||StringUtils.isBlank(destination)){
//            throw new BaseException("经纬度参数不能为空");
            return new HashMap<>();
        }
        if (StringUtils.isBlank(type)) {
            type = "1";
        }
        if (StringUtils.isBlank(output)) {
            output = "JSON";
        }
        StringBuffer s = new StringBuffer();
        s.append("key=").append(key).
                append("&origins=").append(origins).
                append("&destination=").append(destination).
                append("&type=").append(type).
                append("&output=").append(output);
        String res = sendGet("https://restapi.amap.com/v3/distance", s.toString());
        AmapEntity amapEntity = JSONUtil.toBean(res, AmapEntity.class);
        JSONArray jsonArray = (JSONArray) amapEntity.getResults();

        List<DistantResult> distantResultList = JSONUtil.toList(jsonArray,DistantResult.class);
        Map<Integer,Integer> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(distantResultList)) {
            for(DistantResult distantResult : distantResultList){
                map.put(distantResult.getOrigin_id(),distantResult.getDistance());
            }
            return map;
        }
        return null;
    }


//    public static void main(String[] args) throws IOException{
//        String origins = "116.481028,39.989643|114.481028,39.989643|115.481028,39.989643";
//        String destination = "114.465302,40.004717";
//        Map<String,Integer> map =  AmapUtil.getDistanceList(origins,destination,"","");
//        System.out.println(map);
//    }
}
