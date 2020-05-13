package com.xinmintx.factory.controller;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.model.bo.Good;
import com.xinmintx.factory.model.dto.Page;
import com.xinmintx.factory.model.po.Goods;
import com.xinmintx.factory.model.vo.GoodsSkuVo;
import com.xinmintx.factory.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@Api(tags = "商品")
@RestController
@RequestMapping("/factory/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    /**
     * 分页查询商品
     * @param token
     * @param pagination
     * @param numberOfPages
     * @param resultCode
     * @return
     */
    @ApiOperation("分页查询商品")
    @PostMapping("/queryGoodsByFactoryId")
    public ResultCode queryGoodsByFactoryId(@RequestHeader("token")String token, @RequestParam("pagination")Integer pagination ,@RequestParam("numberOfPages")Integer numberOfPages,@Ignore ResultCode resultCode){
        resultCode.setData(goodsService.queryGoodsByFactoryId(token,pagination,numberOfPages));
        return resultCode;
    }

    /**
     * 上传图片
     * @param token
     * @param files
     * @param
     * @return
     */
    @ApiOperation("上传图片")
    @PostMapping("/imageUpload")
    @ResponseBody
    public ResultCode imageUpload(@RequestHeader("token")String token, @RequestParam("files") MultipartFile[] files){
        return goodsService.imageUpload(token,files);
    }


    /**
     * 删除商品
     * @param token
     * @param goodsId
     * @param resultCode
     * @return
     */
    @ApiOperation("删除商品")
    @PostMapping("/deleteGoods")
    @ResponseBody
    public ResultCode deleteGoodsById(@RequestHeader("token")String token, Integer goodsId, @Ignore ResultCode resultCode){
        resultCode = goodsService.deleteGoodsById(token,goodsId);
        return resultCode;
    }

    /**
     * 修改商品状态
     * @param token 登录信息 修改条件
     * @param status 商品状态 修改信息
     * @param goodsId 商品 id 修改条件
     * @param resultCode 忽略，此为返回值
     * @return ResultCode
     */
    @ApiOperation("修改商品状态")
    @PostMapping("/updateGoodsStartByGoodsId")
    public ResultCode updateGoodsStartByGoodsId(@RequestHeader("token")String token,@RequestParam("status")Integer status,@RequestParam("goodsId")Integer goodsId,@Ignore ResultCode resultCode){
        goodsService.updateGoodsStartByGoodsId(token, status, goodsId);
        return resultCode;
    }

    /**
     * 商品规格
     *
     * @param id
     * @return
     */
    @ApiOperation("商品规格查询")
    @PostMapping("/getSkuById")
    public ResultCode getSkuById(@RequestHeader("token")String token, Integer id) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        GoodsSkuVo goodsSku = goodsService.getMemberSkuByGoodsId(id, token);
        if (goodsSku != null) {
            resultCode.setData(goodsSku);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        }
        return resultCode;
    }

    /**
     * 商品规格修改
     * @param token
     * @param goodsSkuVo
     * @return
     */
    @ApiOperation("商品规格修改")
    @PostMapping("/updateSku")
    public ResultCode updateSku(@RequestHeader("token")String token, @RequestBody GoodsSkuVo goodsSkuVo){
        return goodsService.updateSku(token,goodsSkuVo);
    }

    /**
     * 商品添加
     * @param token
     * @param resultCode
     * @param good
     * @return
     */
    @ApiOperation("商品添加")
    @PostMapping("/insertSku")
    public ResultCode insertGoods(@RequestHeader("token")String token, @Ignore ResultCode resultCode, @RequestBody Good good){
        return goodsService.insertGood(token,good);
    }

    /**
     * 商品详情
     * @param token
     * @param resultCode
     * @param goodsId
     * @return
     */
    @ApiOperation("商品详情")
    @PostMapping("/selectGoodsAndSku")
    public ResultCode selectGoodsAndSku(@RequestHeader("token")String token, @Ignore ResultCode resultCode, Integer goodsId){
        return goodsService.selectGoodsAndSku(token,goodsId);
    }

}
