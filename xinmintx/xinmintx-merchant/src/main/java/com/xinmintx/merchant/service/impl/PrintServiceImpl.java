package com.xinmintx.merchant.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.mapper.MerchantMapper;
import com.xinmintx.merchant.mapper.MerchantPrinterMapper;
import com.xinmintx.merchant.model.*;
import com.xinmintx.merchant.service.PrintService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2020/3/5 10:33
 * @Description: 打印机
 */
@Service
public class PrintServiceImpl implements PrintService {

    @Autowired
    private MerchantPrinterMapper merchantPrinterMapper;
    @Autowired
    private MerchantMapper merchantMapper;

    @Value("${print.Url}")
    public String URL;
    @Value("${print.User}")
    public String USER;
    @Value("${print.UKEY}")
    public String UKEY;

    /**
     * 打印订单
     *
     * @param printerTDO 订单
     * @param merchantId 商户id
     * @return 1为打印成功 2为没有添加打印机
     */
    public int printOrder(PrinterTDO printerTDO, Integer merchantId) {
        //获取打印机信息
        MerchantPrinterExample example = new MerchantPrinterExample();
        MerchantPrinterExample.Criteria criteria = example.createCriteria();
        criteria.andMerchantIdEqualTo(merchantId);
        List<MerchantPrinter> merchantPrinters = merchantPrinterMapper.selectByExample(example);
        //该商户没有打印机
        if (merchantPrinters == null || merchantPrinters.size() == 0) {
            return 2;
        }
        //遍历打印机
        for (MerchantPrinter merchantPrinter : merchantPrinters) {
            String method1 = print(merchantPrinter.getSn(), printerTDO);//该接口只能是小票机使用,如购买的是标签机请使用下面方法2，调用打印
            Map map1 = JSONObject.parseObject(method1, Map.class);
            String msg1 = map1.get("msg").toString();
        }
        return 1;
    }

    /**
     * 添加打印机
     */
    public ResultCode addPrint(String printerName,String token, String sn, String printKey) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        //查询商户是否存在
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant == null) {
            resultCode.setMsg("商户不存在");
            return resultCode;
        }
        //查询该商户是否已经添加过打印机
        MerchantPrinterExample merchantPrinterExample = new MerchantPrinterExample();
        MerchantPrinterExample.Criteria criteria1 = merchantPrinterExample.createCriteria();
        criteria1.andMerchantIdEqualTo(merchant.getId());
        List<MerchantPrinter> list = merchantPrinterMapper.selectByExample(merchantPrinterExample);
        //如果添加过了就不给他添加打印机
        if (list != null && list.size()>0) {
            resultCode.setMsg("您已添加过打印机");
            return resultCode;
        }
        //调用打印机第三方接口添加打印机
        String key = sn + "#" + printKey;
        String method = addprinter(key);
        Map map = JSONObject.parseObject(method, Map.class);
        Map data = JSONObject.parseObject(map.get("data").toString(), Map.class);
        JSONArray ok = JSONArray.parseArray(data.get("ok").toString());
        if (ok != null && ok.size() > 0) {
            resultCode.setCode(200);
            resultCode.setMsg("保存成功");
            MerchantPrinter merchantPrinter = new MerchantPrinter();
            merchantPrinter.setMerchantId(merchant.getId());
            merchantPrinter.setSn(sn);
            merchantPrinter.setPrintkey(printKey);
            merchantPrinter.setPrinterName(printerName);
            merchantPrinterMapper.insert(merchantPrinter);
            return resultCode;
        } else {
            resultCode.setCode(500);
            String[] split = JSONArray.parseArray(data.get("no").toString()).get(0).toString().split(" ");
            resultCode.setMsg(split[1]);
            return resultCode;
        }
    }

    @Override
    public ResultCode selectPrinterStatus(String token) {
            ResultCode code = new ResultCode();
            Merchant merchant = merchantMapper.queryByToken(token);
            if (merchant!=null){
                MerchantPrinter merchantPrinter = merchantMapper.queryPrinterSn(merchant.getId());
                if (merchantPrinter!=null){
                    Map map = JSONObject.parseObject(queryPrinterStatus(merchantPrinter.getSn()), Map.class);
                    //成功：{"msg":"ok","ret":0,"data":"状态","serverExecutedTime":4}
                    //失败：{"msg":"错误描述","ret":非0,"data":"null","serverExecutedTime":5}
                    String data = map.get("data").toString();
                    merchantPrinter.setPrinterStatus(data);
                    code.setData(merchantPrinter);
                    return code;
                }else {
                    code.setCode(500);
                    code.setMsg("商户未绑定打印机");
                    return code;
                }

            }else {
                code.setCode(500);
                code.setMsg("商户不存在");
                return code;
            }
    }

    @Override
    public ResultCode upPrintingStatus(String token, Integer ifAuto) {
        ResultCode code = new ResultCode();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant!=null){
            int i  =merchantPrinterMapper.upPrintingStatus(merchant.getId(),ifAuto);
            if (i>0) {
                code.setCode(200);
                code.setMsg("修改成功");
                return code;
            }else {
                code.setMsg("修改失败");
                return code;
            }
        }else {
            code.setCode(500);
            code.setMsg("商户不存在");
            return code;
        }

    }


    /**
     * 添加打印机第三方接口
     *
     * @param snlist
     * @return
     */
    private String addprinter(String snlist) {

        //通过POST请求，发送打印信息到服务器
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)//读取超时
                .setConnectTimeout(30000)//连接超时
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();

        HttpPost post = new HttpPost(URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user", USER));
        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
        nvps.add(new BasicNameValuePair("stime", STIME));
        nvps.add(new BasicNameValuePair("sig", signature(USER, UKEY, STIME)));
        nvps.add(new BasicNameValuePair("apiname", "Open_printerAddlist"));//固定值,不需要修改
        nvps.add(new BasicNameValuePair("printerContent", snlist));

        CloseableHttpResponse response = null;
        String result = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            response = httpClient.execute(post);
            int statecode = response.getStatusLine().getStatusCode();
            if (statecode == 200) {
                HttpEntity httpentity = response.getEntity();
                if (httpentity != null) {
                    result = EntityUtils.toString(httpentity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                post.abort();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 打印第三方接口
     *
     * @param sn
     * @param printerTDO
     * @return
     */
    private String print(String sn, PrinterTDO printerTDO) {
        //标签说明：
        //单标签:
        //"<BR>"为换行,"<CUT>"为切刀指令(主动切纸,仅限切刀打印机使用才有效果)
        //"<LOGO>"为打印LOGO指令(前提是预先在机器内置LOGO图片),"<PLUGIN>"为钱箱或者外置音响指令
        //成对标签：
        //"<CB></CB>"为居中放大一倍,"<B></B>"为放大一倍,"<C></C>"为居中,<L></L>字体变高一倍
        //<W></W>字体变宽一倍,"<QR></QR>"为二维码,"<BOLD></BOLD>"为字体加粗,"<RIGHT></RIGHT>"为右对齐
        //拼凑订单内容时可参考如下格式
        //根据打印纸张的宽度，自行调整内容的格式，可参考下面的样例格式

        List<OrderExt> voList = printerTDO.getVoList();
        if (voList == null || voList.size() == 0) {
            return "订单为空";
        }
        StringBuilder content = new StringBuilder();
        content.append("<CB>惠商天下</CB><BR>");
        content.append("名称          单价  数量    金额<BR>");
        content.append("--------------------------------<BR>");
        for (OrderExt orderExt : voList) {
            StringBuilder builder = new StringBuilder();
            String name = orderExt.getName();
            int length = 0;
            builder.append(name);

            builder.append("<BR>");

            if (length < 15) {
                int count = 15 - length;
                for (int i = 0; i < count; i++) {
                    builder.append(" ");
                }
                length = 15;
            }
            builder.append(orderExt.getPrice());
            length += String.valueOf(orderExt.getPrice()).getBytes().length;
            if (length < 21) {
                int count = 21 - length;
                for (int i = 0; i < count; i++) {
                    builder.append(" ");
                }
                length = 21;
            }
            builder.append(orderExt.getQuantity());
            length += String.valueOf(orderExt.getQuantity()).getBytes().length;
            if (length < 26) {
                int count = 26 - length;
                for (int i = 0; i < count; i++) {
                    builder.append(" ");
                }
            }
            builder.append(orderExt.getPrice() * orderExt.getQuantity());
            builder.append("<BR>");
            content.append(builder);
        }


        content.append("备注：" + printerTDO.getReceiveMessage() + "<BR>");
        content.append("--------------------------------<BR>");
        content.append("合计：" + printerTDO.getTotalPrice() + "元<BR>");
        content.append("姓名: " + printerTDO.getReceiveName() + "<BR>");
        content.append("送货地点：" + printerTDO.getReceiveAddress() + "<BR>");
        content.append("联系电话：" + printerTDO.getReceivePhone() + "<BR>");
        content.append("下单时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(printerTDO.getCreateTime()) + "<BR>");
        content.append("<B>配送方式:"+ (printerTDO.getDeputyHelp() == 0 ? "自提" : "配送") +"</B><BR>" );
        content.append("<QR>http://www.xinmintx.cn/#/community</QR>");


        //通过POST请求，发送打印信息到服务器
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)//读取超时
                .setConnectTimeout(30000)//连接超时
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
        HttpPost post = new HttpPost(URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user", USER));
        String STIME = String.valueOf(System.currentTimeMillis() / 1000);
        nvps.add(new BasicNameValuePair("stime", STIME));
        nvps.add(new BasicNameValuePair("sig", signature(USER, UKEY, STIME)));
        nvps.add(new BasicNameValuePair("apiname", "Open_printMsg"));//固定值,不需要修改
        nvps.add(new BasicNameValuePair("sn", sn));
        nvps.add(new BasicNameValuePair("content", content.toString()));
        nvps.add(new BasicNameValuePair("times", "1"));//打印联数

        CloseableHttpResponse response = null;
        String result = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            response = httpClient.execute(post);
            int statecode = response.getStatusLine().getStatusCode();
            if (statecode == 200) {
                HttpEntity httpentity = response.getEntity();
                if (httpentity != null) {
                    //服务器返回的JSON字符串，建议要当做日志记录起来
                    result = EntityUtils.toString(httpentity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                post.abort();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    //===========查询打印机的状态==========================
    private  String queryPrinterStatus(String sn){

        //通过POST请求，发送打印信息到服务器
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)//读取超时
                .setConnectTimeout(30000)//连接超时
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();

        HttpPost post = new HttpPost(URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user",USER));
        String STIME = String.valueOf(System.currentTimeMillis()/1000);
        nvps.add(new BasicNameValuePair("stime",STIME));
        nvps.add(new BasicNameValuePair("sig",signature(USER,UKEY,STIME)));
        nvps.add(new BasicNameValuePair("apiname","Open_queryPrinterStatus"));//固定值,不需要修改
        nvps.add(new BasicNameValuePair("sn",sn));

        CloseableHttpResponse response = null;
        String result = null;
        try
        {
            post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
            response = httpClient.execute(post);
            int statecode = response.getStatusLine().getStatusCode();
            if(statecode == 200){
                HttpEntity httpentity = response.getEntity();
                if (httpentity != null){
                    //服务器返回
                    result = EntityUtils.toString(httpentity);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try {
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                post.abort();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    //生成签名字符串
    private static String signature(String USER, String UKEY, String STIME) {
        String s = DigestUtils.sha1Hex(USER + UKEY + STIME);
        return s;
    }

}
