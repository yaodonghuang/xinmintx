<template>
   <div class="bgtop">
    <van-search
      placeholder="请输入搜索关键词"
      show-action
      shape="round"
      label="嘉善"
    >
      <div slot="action"><img class="top_xiaoxi" src="../../images/img/xiaoxi.png"/></div>
    </van-search>
    <div class="shop_lbt">
      <van-swipe :autoplay="3000" indicator-color="white">
        <van-swipe-item class="shop_lbt"><img class="img_lbt" src="../../images/img/bg1.png"/></van-swipe-item>
        <van-swipe-item class="shop_lbt"><img class="img_lbt" src="../../images/img/index_home/tu2.png"/></van-swipe-item>
        <van-swipe-item class="shop_lbt"><img class="img_lbt" src="../../images/img/index_home/tu3.png"/></van-swipe-item>
      </van-swipe>
    </div>
    <div class="taio1">
      <div class="kuai1">
        <img class="kuai_img" src="../../images/img/index_home/1r.png"/>
        全部分类
      </div>
      <div class="kuai1">
        <img class="kuai_img" src="../../images/img/index_home/2r.png"/>
        热销榜单
      </div>
      <div class="kuai1">
        <img class="kuai_img" src="../../images/img/index_home/3r.png"/>
        今日精选
      </div>
      <div class="kuai1">
        <img class="kuai_img" src="../../images/img/index_home/4r.png"/>
        我的订单
      </div>
      <div class="kuai1">
        <img class="kuai_img" src="../../images/img/index_home/5r.png"/>
        新手导航
      </div>
    </div>
    <div class="tiao2">免费开店，赠送GXM通行积分</div>
    <div class="biaoqian1">
      <div class="zuo"><img src="../../images/img/zuo.png"/></div>
      <div class="you"><img src="../../images/img/you.png"/></div>
      立即预购
    </div>
     <div class="tiao3" v-for="(commodity, index) in commodity" :key="commodity.index">
      <router-link :to="{ name: 'Article', params: { shopid: commodity.id }}">
      <div><img  class="spimg" :src="commodity.kindUrl"/></div>
      <div>
        <p  class="p1">{{commodity.kindName}}</p>
        <p  class="p2"><img src="">{{commodity.activityTitle}}</p>
        <span class="span1">即将售罄</span>
        <p  class="p3">
          <van-progress :percentage="commodity.market" color="#ee0a24" :show-pivot="false"/>
        </p>
        <p  class="p4">已购5万件+</p>
        <span  class="s1">￥{{commodity.price}}</span><span  class="s2">马上抢</span>
      </div></router-link>
    </div>
    <div class="imgtiao"><img class="imgtiao" src="../../images/img/tu1.png"/></div>
    <div class="biaoqian1">
      <div class="zuo"><img src="../../images/img/zuo.png"/></div>
      <div class="you"><img src="../../images/img/you.png"/></div>
      热销商品
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      commodity:[]
    }
  },
  methods: {
    goshoping () {
      this.$router.push({ path:'/Article' + this.commodity.id})
    }
  },
  mounted () {
    this.$axios({
      url: 'http://114.55.103.26:8085/goods/show/getGood',
      methods: 'get',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then(res => {
      console.log(res.data)
      this.commodity = res.data.data
    })
  }
}
</script>

<style lang="less" scoped>
.bgtop {
    background-color: #FAFAFA;
  }

  .top_xiaoxi {
    width: 19px;
    height: 18px;
  }

  .shop_lbt {
    text-align: center;
  }

  .img_lbt {
    width: 95%;
    height: 115px;
  }

  .taio1 {
    display: flex;
    width: 95%;
    margin: 5px auto;
  }

  .kuai1 {
    width: 20%;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    font-size: 8px;
  }

  .kuai_img {
    width: 30px;
    height: 30px;
  }

  .tiao2 {
    background-image: url("../../images/img/bg1.png");
    height: 48px;
    width: 95%;
    background-size: cover;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #FFFFFF;
    font-size: 16px;
  }

  .biaoqian1 {
    text-align: center;
    font-size: 12px;
    width: 80px;
    margin: 30px auto;
  }

  .zuo {
    float: left;
  }

  .zuo img {
    width: 16px;
    height: 17px;
  }

  .you {
    float: right;
  }

  .you img {
    width: 16px;
    height: 17px;
  }

  .tiao3 {
    width: 95%;
    height: 118px;
    margin: 5px auto;
    background-color: #FFFFFF;
    border-radius: 5px;
    position: sticky;
    color: black;
  }

  .spimg {
    height: 100px;
    width: 100px;
    margin-top: 5px;
    margin-left: 10px;
    padding-right: 10px;
    float: left;
    color: black;
  }

  .p1 {
    padding-top: 2px;
    font-size: 11px;
    width: 95%;
    color: black;
  }

  .p2 {
    font-size: 11px;
    font-weight: bold;
    color: black;
  }

  .p3 {
    padding-left: 120px;
    font-size: 8px;
    color: #666666;
    margin-top: 8px;
    padding-bottom: 5px;
    margin-right: 50px;
  }

  .span1 {
    float: right;
    font-size: 8px;
    margin-right: 5px;
    color: #666666;
    margin-top: 4px;
  }

  .p4 {
    font-size: 5px;
    color: black;
  }

  .s1 {
    position: absolute;
    top: 90px;
    left: 120px;
    color: red;
    font-weight: bold;
    font-size: 12px;
  }

  .s2 {
    position: absolute;
    top: 90px;
    right: 20px;
    color: #FFFFFF;
    font-size: 9px;
    background-color: red;
    width: 40px;
    text-align: center;
    border-radius: 5px;
  }

  .imgtiao {
    margin: 5px auto;
    text-align: center;
  }

  .imgtiao img {
    width: 95%;
    height: 86px;
  }
</style>
