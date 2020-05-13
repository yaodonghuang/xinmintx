<template>
  <body class="dd_x1">
  <div class="bg_color">
    <div class="gift_imgtop"><img src="../images/img/gift/black.png" /></div>
    <div class="gift_topwen"><span>订单</span></div>
  </div>

  <div class="bg_color dd_top">
    <div class="dd_tiao1">
      <span class="dd_s1">我的订单</span>
      <span class="dd_s2">全部订单 ></span>
    </div>
    <div class="dd_tiao2">
      <div class="dd_img"><img src="../images/img/order/dfk.png"/><br>待收货</div>
      <div class="dd_img"><img src="../images/img/order/dfh.png"/><br>待发货</div>
      <div class="dd_img"><img src="../images/img/order/dsh.png"/><br>待收货</div>
      <div class="dd_img"><img src="../images/img/order/dhx.png"/><br>待核销</div>
      <div class="dd_img"><img src="../images/img/order/sh.png"/><br>退款/售后</div>
    </div>
    <div class="dd_tiao3">
      <div class="dd_xbox1">
        <span >物流消息</span>
        <span class="dd_s3">2019-08-22</span>
      </div>
      <div class="dd_xbox2">
        <img src="../images/img/order/p1.png" />
        <p class="dd_p1">运输中</p>
        <p class="dd_p2">[绍兴市] 浙江省绍兴市XX转运中心</p>
      </div>
    </div>
  </div>

  </body>
</template>

<script>

</script>

<style lang="less" scoped>
  .dd_x1 {
    background-color: #f5f5f5;
  }
  .bg_color {
    background-color: #ffffff;
    border-radius: 0.5rem;
  }
  .gift_imgtop {
    position: absolute;
    float: left;
    margin-top: 1.5875rem;
    margin-left: 1.875rem;
    img {
      width: 1.2rem;
      height: 2rem;
    }
  }
  .gift_topwen {
    height: 5rem;
    font-size: 2rem;
    display: flex;
    justify-content: center;
    border-bottom: 1px solid #666666;
    span {
      padding-top: 0px;
      margin-top: 0.74rem;
    }
  }
  .dd_top {
    width: 90%;
    margin: 1rem auto;
    padding-bottom: 1rem;
  }
  .dd_tiao1 {
    width: 90%;
    margin: 0 auto;
    height: 2rem;
  }
  .dd_s1 {
    font-size: 1.042083rem;
    line-height: 2rem;
  }
  .dd_s2 {
    font-size: 0.868333rem;
    color: #6a6a6a;
    float: right;
    line-height: 2rem;
  }
  .dd_tiao2 {
    margin-top: 1rem;
    border-radius: 2rem;
    height: 5rem;
  }
  .dd_img {
    width: 20%;
    float: left;
    align-items: center;
    font-size: 0.781666rem;
    line-height: 2rem;
    text-align: center;
    img {
      height: 2.291666rem;
    }
  }
  .dd_tiao3 {
    background-color: #f5f5f5;
    width: 90%;
    margin: 1rem auto;
    border-radius: 1rem;
  }
  .dd_xbox1 {
    width: 90%;
    margin: 0 auto;
    line-height: 2rem;
    font-size: 0.695rem;
    color: #686868;
  }
  .dd_s3 {
    float: right;
  }
  .dd_xbox2 {
    width: 90%;
    margin: 0 auto;
    height: 3rem;
    padding-bottom: 1rem;
    img {
      height: 3rem;
      width: 3rem;
      float: left;
    }
  }
  .dd_p1 {
    font-size: 1.042083rem;
    margin-left: 3.5rem;
  }
  .dd_p2 {
    font-size: 0.781666rem;
    margin-left: 3.5rem;
  }

</style>
