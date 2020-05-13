package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.GoodPtgood;

import java.util.HashMap;
import java.util.List;

public interface PtService {
    List<HashMap> getPt(Integer goodsId, Integer groupType);

    List<GoodPtgood> PtGetHsStyle (Integer goodsId);

    List<GoodPtgood> PtGetProxyStyle (Integer goodsPtUserId);

    HashMap getPtDetail (Integer id);

//    int getPtCount (Integer goodsId);

}
