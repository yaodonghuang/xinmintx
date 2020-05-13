package com.xinmintx.system.domain;

import com.xinmintx.common.annotation.Excel;
import com.xinmintx.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商户打印机编号对象 merchant_printer
 * 
 * @author xinmintx
 * @date 2020-03-11
 */
public class MerchantPrinter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 商户id */
    @Excel(name = "商户id")
    private Long merchantId;

    /** 商户名字 */
    @Excel(name = "商户名字")
    private String merchantName;

    /** 编号 */
    @Excel(name = "编号")
    private String sn;

    /** 密钥 */
    @Excel(name = "密钥")
    private String printkey;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMerchantId(Long merchantId) 
    {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() 
    {
        return merchantId;
    }
    public void setSn(String sn) 
    {
        this.sn = sn;
    }

    public String getSn() 
    {
        return sn;
    }
    public void setPrintkey(String printkey) 
    {
        this.printkey = printkey;
    }

    public String getPrintkey() 
    {
        return printkey;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merchantId", getMerchantId())
            .append("sn", getSn())
            .append("printkey", getPrintkey())
            .toString();
    }
}
