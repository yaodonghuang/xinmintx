package com.xinmintx.merchant.model;

public class MerchantPrinter {
    private Integer id;

    private Integer merchantId;

    private String sn;

    private String printkey;

    private String printerName;

    private String printerStatus;

    private Integer ifAuto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getPrintkey() {
        return printkey;
    }

    public void setPrintkey(String printkey) {
        this.printkey = printkey == null ? null : printkey.trim();
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getPrinterStatus() {
        return printerStatus;
    }

    public void setPrinterStatus(String printerStatus) {
        this.printerStatus = printerStatus;
    }

    public Integer getIfAuto() {
        return ifAuto;
    }

    public void setIfAuto(Integer ifAuto) {
        this.ifAuto = ifAuto;
    }
}