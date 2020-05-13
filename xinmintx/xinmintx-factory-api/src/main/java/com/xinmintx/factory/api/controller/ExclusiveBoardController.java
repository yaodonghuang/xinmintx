package com.xinmintx.factory.api.controller;


import com.xinmintx.factory.api.common.ResultCode;
import com.xinmintx.factory.api.pojo.Gift;
import com.xinmintx.factory.api.service.ExclusiveBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName: com.xinmintx.api.controller.ExclusiveBoardController
 * @Author:Pikachu
 * @Date: 2019/12/16 17:01
 * @Version: v1.0
 */
@Transactional
@RestController
@RequestMapping("/exclusive")
public class ExclusiveBoardController {
    @Autowired
    private ExclusiveBoardService exclusiveBoardService;

    /**
     * 按分类查询店铺
     *
     * @param category
     * @return
     */
    @RequestMapping("/queryByClassify")
    public ResultCode queryByClassify(@RequestParam("merchantCategory") String category) {
        ResultCode code = new ResultCode();
        List list = exclusiveBoardService.queryByClassify(category);
       if (list!=null){
           code.setCode(200);
           code.setData(list);
       }else {
           code.setMsg("店铺为空");
       }
        return code;
    }
    /**
     * 根据店铺Id查询代金券礼包
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryByShop")
    public ResultCode queryByShop(@RequestParam("id") int id) {
        ResultCode code = new ResultCode();
        List list = exclusiveBoardService.queryByShop(id);
        if (list!=null){
            code.setData(list);
            code.setCode(200);
        }else {
        code.setMsg("代金券礼包为空");
        }
        return code;
    }
    /**
     * 添加商户礼包
     *
     * @param gift
     * @return
     */
    @RequestMapping("/addMerchantsGift")
    public ResultCode addMerchantsGift(@RequestBody Gift gift) {
        ResultCode code = new ResultCode();
        if (gift != null) {
            gift.setGiftType("merchant");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                gift.setEndDate(sdf.parse(gift.getEndTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int i = exclusiveBoardService.addMerchantsGift(gift);
            if (i > 0) {
                code.setCode(200);
                code.setMsg("添加成功");
            } else {
                code.setCode(500);
                code.setMsg("添加失败");
            }
        } else {
            code.setCode(500);
            code.setMsg("未填写完数据");
        }

        return code;
    }
    /**
     * 禁用/启用礼包
     *
     * @param giftId
     * @return
     */
    @RequestMapping("/delMerchantsGift")
    public ResultCode delMerchantsGift(@RequestParam("giftId") long giftId) {
        ResultCode code = new ResultCode();
        int i = exclusiveBoardService.delMerchantsGift(giftId);
        if (i > 0) {
            code.setCode(200);
            code.setMsg("操作成功");
        } else {
            code.setCode(500);
            code.setMsg("操作失败");
        }
        return code;
    }
    /**
     * 修改商户礼包
     *
     * @param gift
     * @return
     */
    @RequestMapping("/upMerchantsGift")
    public ResultCode upMerchantsGift(@RequestBody Gift gift) {
        ResultCode code = new ResultCode();
        if (gift.getEndTime() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                gift.setEndDate(sdf.parse(gift.getEndTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (gift != null) {
            int i = exclusiveBoardService.upMerchantsGift(gift);
            if (i > 0) {
                code.setCode(200);
                code.setMsg("修改成功");
            } else {
                code.setCode(500);
                code.setMsg("修改失败");
            }
        }
        return code;
    }

    /**
     * 查询所有商铺
     * @return
     */
    @RequestMapping("/queryAllShop")
    public ResultCode queryAllShop(String regionCode,String giftGroup) {
        ResultCode code = new ResultCode();
        List list = exclusiveBoardService.queryAllShop(regionCode, giftGroup);
        if (list!=null){
            code.setCode(200);
            code.setData(list);
        }else {
            code.setCode(500);
            code.setMsg("店铺不存在");
        }
        return code;
    }
    @RequestMapping("/queryByGiftId")
    public ResultCode queryById(@RequestParam("giftId")String giftId){
        ResultCode code =new ResultCode();
        List list=exclusiveBoardService.queryById(giftId);
        if (list!=null){
            code.setCode(200);
            code.setData(list);
        }else{
            code.setCode(500);
            code.setMsg("礼包不存在");
        }

        return code;
    }
}
