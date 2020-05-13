package com.xinmintx.agent.mchpay;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by nancy on 7/4/16.
 */
@Getter
@AllArgsConstructor
public enum ReturnCode {
    SUCCESS(2000), FAIL(4000), ERROR(5000);

    private int code;
}
