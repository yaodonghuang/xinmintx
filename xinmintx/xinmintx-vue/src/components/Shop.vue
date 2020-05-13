<template>
    <div class="shop_x1">
    <div class="shop_bgtop">
      <a href=""> <img class="shop_x2" src="../images/img/shop/blak.png" /></a>
      <div class="shop_top_xx">
        <img src="../images/img/shop/xx_new.png"/>
      </div>
      <div class="shop_x3">
        <span class="shop_topbar">商家服务</span>
      </div>

      <div>
        <div class="shop_tx">
          <img src="../images/img/shop/tx.png"/>
          <div>
            <p class="shop_name">钱先森</p>
            <p class="shop_boss">会员中心</p>
          </div>
        </div>
      </div>
      <div class="shop_money">
        <div class="shop_up">
          <span class="money_ziti"><img class="shop_x4" src="../images/img/shop/dd_logo.png" >配发订单：</span>
          <span   class="money_ziti1">99/200单</span>
        </div>
        <div class="shop_down">
          <span class="money_ziti"><img class="shop_x5"  src="../images/img/shop/jf_logo.png" >商户积分：</span>
          <span   class="money_ziti1">20000分</span>
        </div>
      </div>
    </div>
    <div class="bg_color shop_m1" >
      <p class="shop_m2">公告：</p>
      <p class="shop_m3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅</p>
    </div>
    <div class="bg_color">
      <div class="shop_x6">
        <div class="tubiao2"><img class="shop_imgx" src="../images/img/shop/qbdd.png"></div>
        <div class="tubiao2"><img class="shop_imgx" src="../images/img/shop/dfk.png"></div>
        <div class="tubiao2"><img class="shop_imgx" src="../images/img/shop/dsh.png"></div>
        <div class="tubiao2"><img class="shop_imgx" src="../images/img/shop/dpj.png"></div>
        <div class="tubiao2"><img class="shop_imgx" src="../images/img/shop/wlyc.png"></div>
        <div class="tubiao3">全部订单</div>
        <div class="tubiao3">待付款</div>
        <div class="tubiao3">待收货</div>
        <div class="tubiao3">待评价</div>
        <div class="tubiao3">物流异常</div>
      </div>
    </div>
    <div class=" bg_color shop_x7">
      <img  src="../images/img/shop/cd.png" />
      <span  >必备功能</span><img class="shop_m4" src="../images/img/shop/tt.png" >
    </div>

    <div class="bg_color shop_lie">
      <div class="shop_pai">
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/yy.png">预约</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/xdd.png">新订单</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/dhx.png">待核销</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/jfsc.png">积分商城</div>
      </div>
      <div class="shop_pai">
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/ylzx.png">引流中心</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/cd2.png">菜单</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/syt.png">收银台</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/hygl.png">会员管理</div>
      </div>
      <div class="shop_pai">
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/kb.png">卡包</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/hf.png">回访</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/ds.png">打赏</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/wlb.png">物料包</div>
      </div>
      <div class="shop_pai">
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/ptfb.png">拼团发布</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/msfb.png">秒杀发布</div>
        <div class="shop_hezi"><img class="shop_img" src="../images/img/shop/rw.png">软文</div>
        <div class=""><img class="" src=" "> </div>
      </div>
    </div>
</div>
</template>

<script>
export default {
  name: 'Shop'

}
</script>

<style lang="less" scoped>
  .shop_x1 {
    background-color: #f5f5f5;
  }
  .shop_bgtop {
    height: 18.3125rem;
    background-image: url("../images/img/shop/bg.png");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
  }
  .shop_x2 {
    position: absolute;
    margin-top: 1.25rem;
    margin-left: 2.0625rem;
    height: 2.375rem;
    width: 1.3125rem;
  }
  .shop_x3 {
    display: flex;
  }
  .shop_topbar {
    font-size: 1.875rem;
    color: #ffffff;
    margin: 0.85rem auto;
  }
  .shop_m1 {
    margin-top: 6.6875rem;
    padding-bottom: 1rem;
  }
  .shop_m2 {
    font-size: 1.959375rem;
  }
  .shop_m3 {
    font-size: 1.399375rem;
    width: 90%;
    margin: 0 auto;
  }
  .shop_m4 {
    margin-left: 0.9375rem;
    height: 1.5625rem;
    margin-top: 1.5rem;
  }
  .shop_top_xx {
    position: relative;
    height: 2.125rem;
    width: 2.125rem;
    background-image: url("../images/img/shop/xx.png");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
    position: absolute;
    margin-left: 90%;
    margin-top: 1.0625rem;
  /*span {*/
  /*  position: absolute;*/
  /*  top: -0.5rem;*/
  /*  right: -0.5rem;*/
  /*  font-size: 0.6875rem;*/
  img {
    position: absolute;
    top: -0.3rem;left: 1.6rem;
    height: 0.8rem;
    width: 0.8rem;
  /*}*/
  }
  }
  .shop_tx {
    display: flex;
    margin-left: 2.75rem;
    margin-bottom: 6.5625rem;
  img {
    height: 6.5rem;
    width: 7.5rem;
  }
  }
  .shop_name {
    margin-left: 0.53375rem;
    margin-top: 0.9375rem;
    font-size: 1.875rem;
    color: #ffffff;
  }
  .shop_boss {
    margin-left: 0.53375rem;
    margin-top: 0.5625rem;
    font-size: 1.5rem;
    color: #ffffff;
  }
  .shop_up {
    height: 50%;
    width: 90%;
    display: flex;
    align-items: center;
    display: flex;
    flex-flow: row nowrap;
    justify-content: space-between;
  }
  .money_ziti {
    color: #ffffff;
    font-size: 1.33125rem;
  }
  .money_ziti1 {
    color: #ffffff;
    font-size: 1.33125rem;
    display: block;
    float: right;
  }
  .shop_down {
    width: 90%;
    height: 50%;
    display: flex;
    align-items: center;
    display: flex;
    flex-flow: row nowrap;
    justify-content: space-between;
  }
  .shop_x4 {
    height: 1.5625rem;
    width: 1.5625rem;
  }
  .shop_x5 {
    height: 1.5625rem;
    width: 1.5625rem;
  }
  .shop_money {
    background-image: url("../images/img/shop/bg_shoptop.png");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
    height: 8.0625rem;
    width: 80%;
    margin: 0 auto;
    margin-top: -4.4375rem;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .shop_x6 {
    margin-top: 2rem;
    height: 8.625rem;
  }
  .bg_color {
    background-color: #ffffff;
    border-radius: 0.5rem;
  }
  .tubiao2 {
    margin-top: 1.8125rem;
    width: 19%;
    display: inline-block;
    text-align: center;
  }
  .shop_imgx{
    height: 2.56rem;
    width: 2.56rem;
  }
  .tubiao3 {
    margin-top: 0.5625rem;
    width: 19%;
    display: inline-block;
    text-align: center;
    font-size: 1.5rem;
  }
  .shop_pai {
    display: flex;
  }
  .shop_lie {
    display: flex;
    flex-direction: column;
  }
  .shop_hezi {
    height: 7.75rem;
    width: 25%;
    border-top: 1px solid #f5f5f5;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: 1.5rem;
  }
  .shop_img {
    height: 2.5rem;
    width: 2.5rem;
  }
  .shop_x7 {
    margin-top: 1rem;
    height: 4.375rem;
  img {
    margin-left: 0.9375rem;
    margin-top: 1.5rem;
    height: 1.5625rem;
  }
  span {
    margin-top: 1.5rem;
    margin-left: 0.9375rem;
    font-size: 1.875rem;
  }
  }
  .noadd {
    position: absolute;
    color: #ffffff;
    font-size: 2.456875rem;
    margin-top: 1.7rem;
  }
  .money_ziti2 {
    color: #8c8c8c;
    font-size: 1.33125rem;
  }
  .money_ziti12 {
    color: #8c8c8c;
    font-size: 1.33125rem;
    display: block;
    float: right;
  }
  .shop_m5 {
    height: 1.5625rem;
    width: 1.5625rem;
  }

</style>
