<template>
  <body class="gift_x1">
  <div class="bg_color">
    <div class="gift_imgtop"><img src="../images/img/gift/black.png"/></div>
    <div class="gift_topwen"><span>我的礼包</span></div>
  </div>

  <div class="gift_tiao bg_color">
    <div class="gift_wenzi1">已拥有礼包：</div>
    <div class="gift_wenzi2">
      2份
      <img src="../images/img/gift/go.png"/>
    </div>
  </div>
  <div class="gift_tiao bg_color">
    <div class="gift_wenzi1">可领取礼包：</div>
    <div class="gift_wenzi2">
      23份
      <img src="../images/img/gift/go.png"/>
    </div>
  </div>

  <div class="gift_wenzi3">更多--<a>美食</a></div>
  <div class="cardbg">
    <div class="cardtop">
      <div class="imgdiv"><img class="shopimg" src="../images/img/logo.png"/></div>
      <div class="cardtubiao"><p class="cardp3">领取</p></div>
      <div>
        <p class="cardp1">商家礼包名称</p>
        <p class="cardp2">介绍：商品商品商品商品商品商品商品商品商品商品商 品商品商品商品商品商品商品商品商品商商品商品商品 商品商品商品商品商品商品商</p>
      </div>
    </div>
    <div class="carddown">
      <div>
        <div class="touxdiv">店家头像</div>
        <div class="jindian">进店逛逛</div>
        <div class="xiadiv">
          <p>酸柠檬旗舰店</p>
          <p>好评率
            <van-rate v-model="haoping" readonly :size="13"/>
          </p>
        </div>
      </div>
      <div>
        <van-cell @click="showPopup">
          <van-cell-group>
            <van-cell title="参数" is-link/>
          </van-cell-group>
        </van-cell>
      </div>
    </div>
    <van-popup position="left" round v-model="show"  />
      <div class="showzuo"><p>品牌：</p>
        <p>品名： </p>
        <p>重量： </p>
        <p>原厂地： </p>
        <p>适用对象： </p>
        <p>生产厂家：</p></div>
      <div class="showyou"><p>惠商天下</p>
        <p>商品名称商品名称 </p>
        <p>500g </p>
        <p>中国 </p>
        <p>普通人群 </p>
        <p>浙江省嘉善县惠商科技有限公司 </p></div>
    </van-popup>
  </div>
</body>
</template>

<script>
export default {
  data () {
    return {
      haoping: 3,
      show: false
    }
  },
  methods: {
    showPopup () {
      this.show = true
    }
  }
}
</script>

<style lang="less" scoped>
  .cardbg {
    width: 90%;
    margin: 10px auto;
    background-color: #FFFFFF;
    border-radius: 10px;
  }
  .showzuo{
    width: 80px;
    float: left;
  }
  .showyou{
    width: 300px;
  }
  .cardtop {
    height: 130px;
  }

  .imgdiv {
    width: 130px;
    height: 130px;
    text-align: center;
    float: left;
  }
  /*.van-popup--left {*/
  /*  top: 50%;*/
  /*  left: auto;*/
  /*  -webkit-transform: translate3d(0,-50%,0);*/
  /*  transform: translate3d(0,-50%,0);*/
  /*}*/
  .shopimg {
    width: 110px;
    height: 110px;
    margin: 10px;
    border-radius: 10px;
  }

  .cardp1 {
    font-size: 24px;
  }

  .cardp2 {
    width: 336px;
  }

  .cardtubiao {
    float: right;
    display: flex;
    align-items: center;
    height: 130px;
    margin-right: 30px;
  }

  .cardp3 {
    color: #FFFFFF;
    font-size: 30px;
    border-radius: 20px;
    background-color: #E64747;
    width: 111px;
    height: 45px;
    text-align: center;
  }

  .carddown {
  }

  .touxdiv {
    width: 40px;
    height: 40px;
    font-size: 16px;
    background-color: #666666;
    line-height: 20px;
    text-align: center;
    color: #FFFFFF;
    border-radius: 10px;
    margin-left: 10px;
    float: left;
    margin-right: 5px;
  }
  .xiadiv{
    line-height: 20px;
    width: 300px;
  }
  .jindian{
    color: red;
    float: right;
    margin-right: 20px;
  }
  .gift_x1 {
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

  .gift_tiao {
    margin: 0.5rem auto;
    border-radius: 0.5rem;
    width: 90%;
    height: 4.55625rem;
    font-size: 1.875rem;
  }

  .gift_wenzi1 {
    width: 50%;
    line-height: 4.55625rem;
    padding-left: 1rem;
    float: left;
  }

  .gift_wenzi2 {
    float: right;
    line-height: 4.55625rem;
    color: #888888;
    font-size: 1.875rem;

    img {
      line-height: 4.55625rem;
      height: 2.19rem;
      width: 1.25rem;
      margin-right: 1rem;
      margin-left: 2rem;
    }
  }

  .gift_wenzi3 {
    margin-left: 5%;
    width: 80%;
    font-size: 1.625rem;

    a {
      color: blue;
    }
  }

  .gift_tiao2 {
    width: 90%;
    height: 15.5rem;
    margin: 0.5rem auto;
    border-radius: 0.5rem;
  }

  .gift_tx {
    width: 8.875rem;
    height: 15.5rem;
    float: left;
  }

  .gift_m1 {
    height: 6.875rem;
    width: 6.875rem;
    margin: 1rem;
  }

  .gift_p1 {
    padding-top: 0.5rem;
    font-size: 1.5rem;
  }

  .gift_p1 {
    img {
      height: 0.625rem;
      width: 4.375rem;
      margin-left: 1rem;
      margin-bottom: 0.2rem;
    }

    span {
      float: right;
      font-size: 1rem;
      line-height: 2.4rem;
      margin-right: 1rem;

      img {
        height: 1.125rem;
        width: 0.875rem;
        margin-right: 0.5rem;
        float: left;
        margin-top: 0.52rem;
      }
    }
  }

  .gift_p2 {
    font-size: 0.8rem;
    margin-top: 0.5rem;

    span {
      color: #666666;
    }
  }

  .gift_p3 {
    margin-top: 0.5rem;
    width: 90%;
  }

  .gift_tp {
    margin-top: 1.2rem;
    width: 25rem;
    height: 5.9375rem;

    img {
      height: 5.9375rem;
      margin-left: 0.3rem;
    }
  }

  .gift_lq {
    width: 5rem;
    text-align: center;
    float: right;
    font-size: 1.875rem;
    margin-right: 1rem;
    margin-top: -4.5rem;
    background-color: #d9b25f;
    border-radius: 2rem;
    color: #ffffff;
  }

  .gift_x2 {
    margin-left: 1.5rem;
  }
</style>
