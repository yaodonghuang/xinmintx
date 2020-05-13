package com.xinmintx.factory.service.impl;

import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.factory.mapper.DeliveryMapper;
import com.xinmintx.factory.mapper.VenderMapper;
import com.xinmintx.factory.model.*;
import com.xinmintx.factory.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: com.xinmintx.factory.service.impl.DeliveryServiceImpl
 * @Author:Pikachu
 * @Date: 2019/12/4 14:52
 * @Version: v1.0
 */
@Transactional
@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryMapper delieryMapper;
    @Autowired
    private VenderMapper venderMapper;

    @Override
    public List query(String token, Integer statu) {
        Factory factory = venderMapper.queryByToken(token);
        if (factory != null) {
            long id = factory.getFactoryId();
            String str = "";
            if (statu != null) {// 待评论
                str = " AND gd.evaluate is null ";
            }
            if (statu != null && statu == 8) {
                List<VenderOrderMain> vomList = delieryMapper.queryVom(id, statu, str);
                if (vomList != null && vomList.size() > 0) {
                    vomList.forEach(vom -> {
                        dealListInfo(vom.getVoList());
                    });
                }
                return vomList;
            } else {
                List<VenderOrder> list = delieryMapper.query(id, statu, str);
                dealListInfo(list);
                return list;
            }
        } else {
            return null;
        }
    }

    private void dealListInfo(List<VenderOrder> list) {
        if (list != null && list.size() > 0) {
            for (VenderOrder vs : list) {
                Map<String, String> map = new HashMap<>();
                String num = vs.getSpecValueId();
                if (StringUtils.isNotEmpty(num)) {
                    String[] s = num.split("_");
                    if (s != null && s.length > 0) {
                        int i = 1;
                        for (String s1 : s) {
                            Specs specs = delieryMapper.queryId(s1);
                            if (specs!=null){
                                String name = delieryMapper.queryNum(specs.getSpecId());
                                specs.setName(name);
                                map.put("key" + i, specs.getName() + ":" + specs.getValue());
                                i++;
                            }
                        }
                    }
                }
                vs.setParamMap(map);
            }
        }
    }

    @Override
    public List queryEvaluate(String token) {
        Factory factory = venderMapper.queryByToken(token);
        if (factory != null) {
            long id = factory.getFactoryId();
            List<VenderOrder> list = delieryMapper.queryEvaluate(id);
            if (list != null && list.size() > 0) {
                for (VenderOrder vs : list) {
                    Map<String, String> map = new HashMap<>();
                    String num = vs.getSpecValueId();
                    if (StringUtils.isNotEmpty(num)) {
                        String[] s = num.split("_");
                        if (s != null && s.length > 0) {
                            int i = 1;
                            for (String s1 : s) {
                                Specs specs = delieryMapper.queryId(s1);
                                String name = delieryMapper.queryNum(specs.getSpecId());
                                specs.setName(name);
                                map.put("key" + i, specs.getName() + ":" + specs.getValue());
                                i++;
                            }
                        }
                        vs.setParamMap(map);
                    }
                }
            }
            return list;
        } else {
            return null;
        }
    }

    @Override
    public List queryByTime(String token, String bDate, String eDate) {
        Factory factory = venderMapper.queryByToken(token);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String beginDate = null;
        String endDate = null;
        try {
            Date getBDate = sdf.parse(bDate);
            Date getEDate = sdf.parse(eDate);
            beginDate = String.valueOf(getBDate.getTime() / 1000);
            endDate = String.valueOf(getEDate.getTime() / 1000 + Long.valueOf(86400));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (factory != null) {
            long id = factory.getFactoryId();
            List<VenderOrder> list = delieryMapper.queryByTime(id, beginDate, endDate);
            if (list != null && list.size() > 0) {
                for (VenderOrder vs : list) {
                    vs.setDateTime(String.valueOf(vs.getCreateTime().getTime()));
                    Map<String, String> map = new HashMap<>();
                    String num = vs.getSpecValueId();
                    if (StringUtils.isNotEmpty(num)) {
                        String[] s = num.split("_");
                        if (s != null && s.length > 0) {
                            int i = 1;
                            for (String s1 : s) {
                                Specs specs = delieryMapper.queryId(s1);
                                String name = delieryMapper.queryNum(specs.getSpecId());
                                specs.setName(name);
                                map.put("key" + i, specs.getName() + ":" + specs.getValue());
                                i++;
                            }
                        }
                        vs.setParamMap(map);
                    }
                }
            }
            return list;
        } else {
            return null;
        }
    }

    @Override
    public List queryAll(String token) {
        Factory factory = venderMapper.queryByToken(token);
        if (factory != null) {
            long id = factory.getFactoryId();
            List<GoodsOrder> list = delieryMapper.queryAll(id);
            return list;
        } else {
            return null;
        }
    }

    @Override
    public int upStatu(int statu, String orderNum) {
        int i = venderMapper.upStatu(orderNum, statu);
        return i;
    }

    @Override
    public GoodsOrder queryByOrderId(String orderId) {
        return venderMapper.queryByOrderId(orderId);
    }

    @Override
    public int upMainStatu(int statu, String orderNum) {
        int i = venderMapper.upMainOrder(orderNum, statu);
        return i;
    }

    @Override
    public void upSonStatu(long order, int statu) {
        venderMapper.upSonStatu(order, statu);
    }

    @Override
    public VenderOrder queryById(String orderNum) {
        return venderMapper.queryById(orderNum);
    }


}
