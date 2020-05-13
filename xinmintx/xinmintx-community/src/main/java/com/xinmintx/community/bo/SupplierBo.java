package com.xinmintx.community.bo;

import lombok.Data;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@Data
public class SupplierBo {

    private String name;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 电话
     */
    private String phone;

    /**
     * 距离
     */
    private Double distance;

}
