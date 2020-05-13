<template>
  <div class="bgcolore">
  <div class="bg_color">
    <div class="gift_imgtop"><img src="../../images/img/gift/black.png" /></div>
    <div class="gift_topwen"><span>个人信息</span></div>
  </div>

  <div class="user_tiao1 bg_color">
    <div class="user_txzuo">头像</div>
    <img  src="../../images/img/order/p1.png"/>
  </div>

  <div class="user_tiao2 bg_color">
    <div class="user_zuo">ID</div>
    <div class="user_you hei">12345678</div>
  </div>
  <div class="user_tiao2 bg_color">
    <div class="user_zuo">用户名</div>
    <div class="user_you">惠商天下</div>
  </div>
  <div class="user_tiao2 bg_color">
    <div class="user_zuo">性别</div>
    <div class="user_you">中</div>
  </div>
  <div class="user_tiao2 bg_color">
    <div class="user_zuo">生日</div>
    <div class="user_you">1990-12-12</div>
  </div>
  <div class="user_tiao2 bg_color">
    <div class="user_zuo">手机号</div>
    <div class="user_you">18888888888</div>
  </div>
  <div class="user_tiao2 bg_color">
    <div class="user_zuo">微信号</div>
    <div class="user_you">GXM66</div>
  </div>
  <div class="user_tiao2 bg_color">
    <div class="user_zuo">我的汽车</div>
    <div class="user_you">浙D000000</div>
  </div>
  </div>
</template>

<script>

</script>

<style lang="less" scoped>
  .bgcolore {
    background-color: #f5f5f5;
  }
  .bg_color {
    background-color: #ffffff;
    border-radius: 10px;
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
  .user_tiao1 {
    width: 90%;
    height: 50px;
    margin: 5px auto;

  .user_txzuo{
    float: left;
    font-size: 12px;
    margin-left: 5%;
    line-height: 50px;
  }

  img {
    float: right;
    line-height: 6rem;
    margin-right: 5%;
    margin-top:6px;
    width: 38px;
    height:38px;
    border-radius: 10000px;
  }
  }
  .user_tiao2 {
    height: 38px;
    width: 90%;
    margin: 5px auto;
  }
  .user_zuo {
    font-size: 12px;
    float: left;
    margin-left: 5%;
    line-height: 38px;
  }
  .user_you {
    font-size: 12px;
    float: right;
    margin-right: 5%;
    line-height: 38px;
    color: #808080;
  }
  .hei{
    color: #0A0204;
  }
</style>
