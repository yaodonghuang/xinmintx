package com.xinmintx.hstx.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class ResultCode<T> {
    @ApiModelProperty("状态码")
    private Integer code;
    private String msg;
    private T data;

    public ResultCode() {
        this.code = 200;
        this.msg = "success";
    }

    public ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
