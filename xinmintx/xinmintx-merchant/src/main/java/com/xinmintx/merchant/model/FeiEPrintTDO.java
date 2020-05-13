package com.xinmintx.merchant.model;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/4
 * @time: 15:35
 * @Description:
 */
public class FeiEPrintTDO {
    public String user;//           飞鹅云后台注册用户名。
    public String stime;//	        当前UNIX时间戳，10位，精确到秒。
    public String sig;//            对参数 user+UKEY+stime拼接后（+号表示连接符）进行SHA1加密得到签名，值为40位小写字符串。
    public String apiname;//        请求的接口名称：Open_printerAddlist
    private String printerContent;//打印机编号(必填) # 打印机识别码(必填) # 备注名称(选填) # 流量卡号码(选填)，多台打印机请换行（\n）添加新打印机信息，每次最多100台。

    private String debug;//         debug=1返回非json格式的数据。仅测试时候使用。
    private String[] data;//        返回1个数组，包含成功和失败的信息。

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getApiname() {
        return apiname;
    }

    public void setApiname(String apiname) {
        this.apiname = apiname;
    }

    public String getPrinterContent() {
        return printerContent;
    }

    public void setPrinterContent(String printerContent) {
        this.printerContent = printerContent;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
