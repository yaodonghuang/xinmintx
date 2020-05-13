package com.xinmintx.factory.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.model.bo.Good;
import com.xinmintx.factory.model.bo.GoodsSkuBo;
import com.xinmintx.factory.model.po.Goods;
import com.xinmintx.factory.model.vo.GoodsSkuVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface GoodsService {

    /**
     * @author: create by hjh
     * @time: 2020/3/18 16:36
     * @Description:
     * @param token  验证登录信息 （ 获取当前登录用户信息
     * @param page myBatis_Plus自带架包
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.xinmintx.factory.model.po.Goods>
     */
    Page<Goods> queryGoodsByFactoryId(String token, Integer pagination , Integer numberOfPages);

    /**
     * @author: create by hjh
     * @time: 2020/3/18 10:32
     * @Description: 更改商品状态 （ status
     * @param token 验证登录信息 （ 获取当前登录用户信息
     * @param status 商品状态 修改信息
     * @param goodsId 商品 id 修改条件
     * @return: void
     */
    void updateGoodsStartByGoodsId(String token, Integer status, Integer goodsId);

    ResultCode imageUpload(String token, MultipartFile[] files);

    ResultCode deleteGoodsById(String token,Integer goodsId);

    GoodsSkuVo getMemberSkuByGoodsId(Integer id, String token);

    ResultCode updateSku(String token, GoodsSkuVo goodsSkuVo);

    ResultCode insertGood(String token, Good good);

    ResultCode selectGoodsAndSku(String token, Integer goodsId);
}
