package com.xinmintx.hstx.pojo.vo;

import com.xinmintx.hstx.pojo.po.RedEnvelopeType;
import lombok.Data;

@Data
public class RedEnvelopeTypeVo extends RedEnvelopeType {
    //红包是否领取 0 没领取 1 领取
    private int redPackStrat;
}
