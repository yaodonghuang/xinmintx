<template>
<div>
  <div class="bgcolor">
  <div class="gift_imgtop"><img src="../../assets/images/index/black.png" /></div>
  <div class="gift_topwen"><span>修改交易密码</span></div>
  </div>
  <div class="tiao1 bgcolor">
    <p class="p1">公告：</p>
    <p class="p2">        老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最帅老板最</p>
  </div>
  <!-- main -->
  <div class="main">
    <div class="nav">
      <div class="nav_item">
        <img src="../../assets/images/index/ysh.png" alt="">
        <p>已收货</p>
      </div>
      <div class="nav_item">
        <img src="../../assets/images/index/dfh.png" alt="">
        <p>待发货</p>
      </div>
      <div class="nav_item">
        <img src="../../assets/images/index/sh.png" alt="">
        <p>退款/售后</p>
      </div>
      <div class="nav_item">
        <img src="../../assets/images/index/syt.png" alt="">
        <p>收银台</p>
      </div>
    </div>
  </div>
<!--  end mian-->

</div>
</template>

<script>
    export default {
        name: "Index"
    }
</script>

<style lang="less" scoped>
  .bgcolor{
    background-color:#ffffff;
  }
  .gift_imgtop {
    position: absolute;
    float: left;
    margin-top: 13px;
    margin-left: 7px;
    img {
      width: 8px;
      height: 14px;
    }
  }
  .gift_topwen {
    height: 45px;
    font-size: 14px;
    display: flex;
    justify-content: center;

    span {
      padding-top: 0px;
      margin-top: 10px;
    }
  }
  .tiao1{
    width: 90%;
    margin: 10px auto;
    .p1{
      width: 90%;
      margin: 10px auto;
      font-size: 11px;
    }
    .p2{
      width: 90%;
      margin: 10px auto;
      font-size: 8px;
      color: #808080;
    }
  }
  .main {
    width: 90%;
    background-color: #ffffff;
    margin: 10px auto;
  }
  .main .nav {
    margin-top: 24px;
    display: flex;
    width: 100%;
    text-align: center;
  }
  .main .nav .nav_item {
    width: 25%;
  }
  .main .nav .nav_item img {
    width: 25px;
    height:25px;
  }
  .main .nav .nav_item p {
    font-size: 10px;
  }
</style>
