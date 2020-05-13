<template>
  <div class="order">
    <div class="header">
      <img src="../../images/img/cart.img/back.png" alt="" @click="back()">
      确认订单
    </div>
    <div class="map">
      <img src="../../images/shoppping/map.png" alt="" class="map_img">
      {{map}}
      <img src="../../images/shoppping/right.png" alt="" class="map_right">
    </div>
    <ul class="order_list">
      <li v-for="(item,index) in list" :key="index">
        <img :src="item.imge" alt="">
        <div class="order_commodity">
          <p class="order_commodity_p1">{{item.commodityName}}</p>
          <p class="order_commodity_p2">
            {{item.typologyName}}-{{item.kindName}}
          </p>
        </div>
        <div class="order_price">
          <p class="order_price_p1">￥<span>{{item.price}}</span></p>
          <div class="order_price_p2">x<span>{{item.total}}</span></div>
        </div>
      </li>
    </ul>
    <div class="manner">
      <div class="manner_way">
        <p class="way_p1">支付方式</p>
        <p class="way_p2">
          {{way}}
          <img src="../../images/shoppping/right.png" alt="">
        </p>
      </div>
      <div class="manner_way2">
        <p class="way2_p1">配送方式</p>
        <p class="way2_p2">普通快递: 运费({{money.toFixed(2)}}) <img src="../../images/shoppping/right.png" alt=""></p>
      </div>
      <div class="manner_way3">
        <p class="way3_p">
          <img src="../../images/shoppping/gold.png" alt="">
          可用58新民豆抵用0.58元
        </p>
        <input type="radio">
      </div>
    </div>
    <div class="footer">
      <div class="footer_content">
        <p class="footer_p1">实付金额:<span></span><span>￥</span><span>{{sum.toFixed(2)}}</span></p>
        <p class="footer_p2">快递费:<span>{{money.toFixed(2)}}</span></p>
        <div class="btn">结算</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Orders',
  data () {
    return {
      map: '中国嘉兴',
      list: [],
      way: '在线支付',
      money: 10,
      sum: 0
    }
  },
  methods: {
    back: function () {
      this.$router.go(-1)
    },
    allMoney () {
      // alert(1)
      this.list.forEach(item1 => {
        this.sum += item1.price * item1.total + this.money
      })
    }
  },
  created () {
    this.list = this.$route.params.carShop
    this.allMoney()
  }
}
</script>

<style lang="less" scoped>
.order{
  width: 100%;
  height: 534px;
  background-color: #FFF;
  .header {
    width: 100%;
    height: 38px;
    position: relative;
    line-height: 38px;
    text-align: center;
    font-size: 14px;
    font-family: PingFang SC;
    font-weight: bold;
    color: rgba(0, 0, 0, 1);
    background-color: #FFF;
    img {
      position: absolute;
      left: 12px;
      top: 10px;
      width: 10px;
      height: 14px;
    }
    span{
      position: absolute;
      right: 17px;
      font-size:11px;
      font-family:PingFang SC;
      font-weight:bold;
      color:rgba(0,0,0,1);
    }
  }
  .map{
    width:300px;
    height:36px;
    background:rgba(204,204,204,1);
    line-height: 36px;
    font-size:10px;
    font-family:PingFang SC;
    font-weight:500;
    color:rgba(0,0,0,1);
    position: relative;
    margin-bottom: 5px;
    .map_img{
      width: 12px;
      height: 15px;
      margin-left: 22px;
      margin-right: 6px;
      position: relative;
      top: 3px;
    }
    .map_right{
      width: 6px;
      height: 13px;
      position: absolute;
      top: 12px;
      right: 13px;
    }
  }
  .order_list{
    li{
      display: flex;
      margin-top: 19px;
      margin-left: 18px;
      position: relative;
      img{
        width:38px;
        height:39px;
        background:rgba(133,133,133,1);
        border:0px solid rgba(226,226,226,1);
        border-radius:4px;
      }
      .order_commodity{
        margin-left: 11px;
        .order_commodity_p1{
          font-size:12px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(51,51,51,1);
          line-height:16px;
        }
        .order_commodity_p2{
          font-size:7px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(128,128,128,1);
          margin-top: 8px;
        }
      }
      .order_price{
        display: flex;
        position: absolute;
        top: 22px;
        right: 20px;
        .order_price_p1{
          font-size:12px;
          font-family:PingFang SC;
          font-weight:bold;
          color:rgba(252, 121, 79, 1);
        }
        .order_price_p2{
          font-size:10px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(120,120,120,1);
          margin-top: 2px;
          margin-left: 2px;
        }
      }
    }
  }
  .manner{
    margin-top: 32px;
    .manner_way{
      display: flex;
      justify-content: space-between;
      width:100%;
      height:35px;
      .way_p1{
        font-size:12px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
        line-height: 35px;
        text-indent: 22px;
      }
      .way_p2{
        display: flex;
        line-height: 35px;
        font-size:10px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
        img{
          width: 6px;
          height: 12px;
          line-height: 35px;
          margin-right: 12px;
          margin-left: 7px;
          position: relative;
          top: 12px;
        }
      }
    }
    .manner_way2{
      width:100%;
      height:35px;
      margin-top: 5px;
      display: flex;
      justify-content: space-between;
      .way2_p1{
        font-size:12px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
        text-indent: 22px;
        line-height: 35px;
      }
      .way2_p2{
        font-size:10px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
        line-height: 35px;
        img{
          width: 6px;
          height: 12px;
          line-height: 35px;
          margin-right: 12px;
          margin-left: 7px;
          position: relative;
          top: 2px;
        }
      }
    }
    .manner_way3{
      display: flex;
      justify-content: space-between;
      .way3_p{
        font-size:12px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
        line-height:35px;
        img{
          width: 13px;
          height: 13px;
          margin-left: 22px;
          margin-right: 4px;
          position: relative;
          top: 2px;
        }
      }
      input{
        width: 14px;
        height: 14px;
        display: block;
        position: relative;
        top: 12px;
        right: 16px;
      }
    }
  }
  .footer{
    width: 100%;
    height: 40px;
    position: fixed;
    bottom: 0;
    display: flex;
    line-height: 40px;
    .footer_content{
      position: relative;
      width: 100%;
      display: flex;
      .footer_p1{
        font-size:9px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
        text-indent: 18px;
        padding-right: 16px;
        span{
          font-size:12px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(253,95,64,1);
        }
      }
      .footer_p2 {
        font-size: 9px;
        font-family: PingFang SC;
        font-weight: 500;
        color: rgba(51, 51, 51, 1);
        span{
          font-size:12px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(253,94,63,1);
        }
      }
      .btn{
        width:59px;
        height:25px;
        background:linear-gradient(90deg,rgba(253,89,60,1),rgba(252,121,79,1));
        border-radius:12px;
        text-align: center;
        line-height: 25px;
        font-size:11px;
        font-family:SourceHanSansCN;
        font-weight:400;
        color:rgba(255,255,255,1);
        position: absolute;
        top: 8px;
        right: 14px;
      }
    }
  }
}
</style>
