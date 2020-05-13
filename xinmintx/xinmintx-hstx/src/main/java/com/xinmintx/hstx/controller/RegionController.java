package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.pojo.po.User;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.po.Region;
import com.xinmintx.hstx.service.RegoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hs/region")
public class RegionController {

    @Autowired
    private RegoinService regoinService;

    @Autowired
    private Jedis jedis;

    /**
     * 查询全国地区表
     *
     * @return
     */
    @PostMapping("/selectRegion")
    public ResultCode selectRegion() {
        ResultCode resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        List<List<Region>> regions = regoinService.selectRegion();
        if (regions != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(regions);
        }
        return resultCode;
    }

    /**
     * 根据名称模糊查询地址
     * @param name
     * @return
     */
    @PostMapping("/selectRegionByName")
    public ResultCode selectRegionByName(@RequestParam String name) {
        ResultCode resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        List<Region> regions = regoinService.selectRegionByName(name);
        if (regions != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(regions);
        }
        return resultCode;
    }

    /**
     * 查询热门城市
     * @return
     */
    @PostMapping("/selectHotCity")
    public ResultCode selectHotCity() {
        ResultCode resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        List<User> regions = regoinService.selectHotCity();
        if (regions != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(regions);
        }
        return resultCode;
    }

    /**
     *查找该地区下属地区
     * @return
     */
    @RequestMapping("/selectByCityId")
    public ResultCode selectByCityId(@RequestParam String cityName) {
        ResultCode resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        List<Region> regions = regoinService.selectByCityId(cityName);
        if (regions != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(regions);
        }
        return resultCode;
    }

    /**
     * 查询所有历史记录
     * @return
     */
    @RequestMapping("/history")
    public ResultCode historyRegion(HttpServletRequest request){
        String token = request.getHeader("token");
        ResultCode resultCode = new ResultCode();
        List<String> lrange = jedis.lrange(token, 0, -1);//查看
        if(lrange != null){
            resultCode.setCode(200);
            resultCode.setData(lrange);
            return resultCode;
        }
        resultCode.setCode(200);
        resultCode.setMsg("暂无历史记录");
        return resultCode;
    }

    /**
     * 添加历史记录
     * @param region
     */
    @RequestMapping("/addHistory")
    public ResultCode addHistoryRegion(@RequestParam String region,HttpServletRequest request){
        String token = request.getHeader("token");
        ResultCode resultCode = new ResultCode();
        jedis.lpush(token,region);//头部追加
        resultCode.setCode(200);
        resultCode.setMsg("添加成功");
        return resultCode;
    }

}
