package com.xinmintx.agent.utilAgent;

import com.google.gson.Gson;
import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.PayInfo;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.xml.parsers.FactoryConfigurationError;
import java.io.StringReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLConvertor {
    public static Map xml2Map(String receiveMsg) {
        Map rtnMap = new HashMap();
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new InputSource(new StringReader(receiveMsg)));
            Element root = doc.getRootElement();
            Iterator<Attribute> it = root.attributeIterator();
            while (it.hasNext()) {
                Attribute att = it.next();
                if (att != null) {
                    rtnMap.put(att.getName(), att.getValue());
                }
            }
            String xml = "retCode=" + root.attributeValue("retCode");
            xml = xml + "&retMsg=" + root.attributeValue("retMsg");
            if (root.attributeValue("retCode").equals("0001")) {
                if (root.getName().equals("bankList")) {
                    List odrArrList = new ArrayList();
                    List odrList = root.elements("order");
                    for (int i = 0; i < odrList.size(); i++) {
                        Map odrArrMap = new HashMap();
                        Element ordElement = (Element) odrList.get(i);
                        Iterator ordElementAtr = ordElement.attributeIterator();
                        while (ordElementAtr.hasNext()) {
                            Attribute attr = (Attribute) ordElementAtr.next();
                            if (attr != null) {
                                odrArrMap.put(attr.getName(), attr.getText());
                            }
                            odrArrList.add(odrArrMap);
                        }
                    }
                    rtnMap.put("odrArrList", odrArrList);
                }
            } else {
                List traInfoList = root.elements();
                for (int i = 0; i < traInfoList.size(); i++) {
                    Element temp = (Element) traInfoList.get(i);
                    if (temp.getNodeType() == 1) {
                        Element tempElement = temp;
                        if (!tempElement.getName().equals("signature")) {
                            xml = "&" + tempElement.getName() + "=" + tempElement.getText();
                            rtnMap.put("xml", xml);
                        }
                        List<Attribute> list = tempElement.attributes();
                        if (list != null && list.size() > 0) {
                            list.forEach(att -> {
                                if (att != null) {
                                    rtnMap.put(att.getName(), att.getValue());
                                }
                            });
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtnMap;
    }

    public static String xml2String(String xml) {
        String msg = "";
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new InputSource(new StringReader(xml)));
            Element root = doc.getRootElement();
            List traInfoList = root.elements();
            for (int i = 0; i < traInfoList.size(); i++) {
                Element temp = (Element) traInfoList.get(i);
                if (temp.getNodeType() == 1) {
                    Element tempElement = temp;
                    if (i == 0)
                        msg = tempElement.getName() + "=" + tempElement.getText();
                    else
                        msg = msg + "&" + tempElement.getName() + "=" + tempElement.getText();
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FactoryConfigurationError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    // 处理返回结果
    public static void dealResult(String info, ResultCode rc) {
        StringBuffer sb = new StringBuffer();
        Map rtnMap = xml2Map(info);
        rc.setCode(200);
        if (rtnMap != null) {
            String retCode = (String) rtnMap.get("retCode");
            if ("00002".equalsIgnoreCase(retCode) || "0002".equalsIgnoreCase(retCode)) {
                rc.setCode(500);
            }
            rtnMap.forEach((k, v) -> {
                sb.append(k + ":" + v + ",");
            });
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            rc.setMsg(sb.toString());
            if (rtnMap.get("payInfo") != null) {// 担保支付成功之后的返回值存入data中,通过前台调起微信付款
                PayInfo pi = new Gson().fromJson(rtnMap.get("payInfo").toString(), PayInfo.class);
                if (pi != null) {
                    pi.setPackages("prepay_id=" + (String) rtnMap.get("prepay_id"));
                    rc.setData(pi);
                }
            } else {
                rc.setData(rtnMap);
            }
        }
    }

    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}