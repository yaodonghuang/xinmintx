package com.xinmintx.merchant.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.mapper.mbplusMapper.MerchantsMapper;
import com.xinmintx.merchant.model.mbplusModel.Merchant;
import com.xinmintx.merchant.service.NearbyBusinessesService;
import com.xinmintx.merchant.util.LocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.xinmintx.common.config.datasource.DynamicDataSourceContextHolder.log;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2020/3/17 14:37
 * @Description: 获取附近商户
 */
@Service
public class NearbyBusinessesServiceImpl implements NearbyBusinessesService {
    @Autowired
    private MerchantsMapper merchantsMapper;

    /**
     * 根据用户当前地址获取附近商户
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @return ResultCode
     */
    @Override
    public ResultCode queryNearbyBusinesses(double latitude, double longitude) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        double minlat = 0;//定义经纬度四个极限值。
        double maxlat = 0;
        double minlng = 0;
        double maxlng = 0;

        // 先计算查询点的经纬度范围
        double r = 6371;// 地球半径千米
        double dis = 20;// 距离(单位:千米)，查询范围 20km内的所有商铺
        double dlng = 2 * Math.asin(Math.sin(dis / (2 * r))
                / Math.cos(longitude * Math.PI / 180));
        dlng = dlng * 180 / Math.PI;// 角度转为弧度
        double dlat = dis / r;
        dlat = dlat * 180 / Math.PI;
        if (dlng < 0) {
            minlng = longitude + dlng;//拿到最大经度和最小经度
            maxlng = longitude - dlng;
        } else {
            minlng = longitude - dlng;
            maxlng = longitude + dlng;
        }
        if (dlat < 0) {
            minlat = latitude + dlat;//拿到最大纬度和最小纬度
            maxlat = latitude - dlat;
        } else {
            minlat = latitude - dlat;
            maxlat = latitude + dlat;
        }


        log.info("最大经度：{},最小经度：{}", maxlng, minlng);
        log.info("最大纬度：{},最小纬度：{}", maxlat, minlat);
        final List<Merchant> merchantList = new ArrayList();//定义一个空 list保存范围内的店铺

        //查询所有商铺,条件是经纬度不能为空,已经审核的
        List<Merchant> merchantLists = new LambdaQueryChainWrapper<>(merchantsMapper)
                .eq(Merchant::getIsReview, 1)
                .isNotNull(Merchant::getLatitude)
                .isNotNull(Merchant::getLongitude)
                .select(Merchant::getId,
                        Merchant::getMerchantName,
                        Merchant::getMerchantType,
                        Merchant::getMerchantCategory,
                        Merchant::getPerCapita,
                        Merchant::getToken,
                        Merchant::getAvatar,
                        Merchant::getAnnouncement,
                        Merchant::getDoorHeadPhoto,
                        Merchant::getLatitude,
                        Merchant::getLongitude,
                        Merchant::getMerchantCategoryDetail)
                .list();
        if (merchantLists == null || merchantLists.size() == 0) {
            resultCode.setData(new ArrayList<>());
            return resultCode;
        }
        for (Merchant merchant : merchantLists) {
            double _long = Double.parseDouble(merchant.getLongitude());//拿到店铺的坐标，判断是否在这个范围内
            double _lat = Double.parseDouble(merchant.getLatitude());
            if (_long >= minlng && _long <= maxlng && _lat >= minlat && _lat <= maxlat) {
                merchantList.add(merchant);//将在这个范围内的店铺添加到一个空list中。
            }
        }
        Map<Merchant, Double> map = new HashMap<Merchant, Double>();//定义一个空的map存储店铺
        //计算范围内每个店铺距离给定坐标的距离
        for (Merchant _merchant : merchantList) {
            Double distance = LocationUtils.getDistance(Double.parseDouble(_merchant.getLatitude()), Double.parseDouble(_merchant.getLongitude()), latitude, longitude);
            log.info("店铺名称：{},经度：{},纬度：{},距离：{}", _merchant.getMerchantName(), _merchant.getLongitude(), _merchant.getLatitude(), distance);
            map.put(_merchant, distance);//将计算出来的距离作为value，该店铺作为key
        }
        final List<Merchant> list = new ArrayList();//存储排序之后的店铺
        //这里使用Java8的map根据value排序，距离最近排序，按value排序，升序
        Map<Merchant, Double> doubleMap = map
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));
        for (Map.Entry<Merchant, Double> entry : doubleMap.entrySet()) {
            list.add(entry.getKey());//循环map拿到key并保存到list中。
        }

        resultCode.setData(list);
        return resultCode;
    }
}
