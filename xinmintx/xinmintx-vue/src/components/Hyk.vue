<template>
  <div class="hyk">
    <div class="header">
      <img src="../images/img/hyk.img/bg.png" alt="">
      <div class="arrow" @click="back"></div>
      <p>会员卡</p>
      <div class="hyk" @click="pay()"></div>
    </div>
    <div class="center">
      <img src="../images/img/hyk.img/icon1.png" alt="">
      <span>您的会员卡可在以下店铺中使用</span>
      <div class="my">
        <img src="../images/img/hyk.img/dianpu.png" alt="">
        <span>我的店铺</span>
    </div>
    </div>

  <!-- 搜索框 -->
    <div class="search">
      <input type="text">
      <img src="../images/img/hyk.img/搜索.png" alt="">
      <button>搜索</button>
    </div>
  <!-- 店铺展示 -->
  <div class="dp">
    <div class="dp_item">
      <img src="../images/img/hyk.img/123456.png" alt="" class="one">
      <div class="ms">
          <p>太平洋百货肉蟹煲店</p>
          <p>浙江省嘉兴市嘉善县万联城5-802</p>
          <img class="two" src="../images/img/hyk.img/37e46c6d9c08f4962720a8b618d15cca.png" alt="">
          <span>￥45/人</span>
      </div>
      <div class="right">
          <span>8.8折</span>
          <img class="three" src="../images/img/hyk.img/组 78.png" alt="">
          <img src="../images/img/hyk.img/组 79.png" alt="">
          <img src="../images/img/hyk.img/组 80.png" alt="">
          <p>600m</p>
          <p>余额￥35积分65</p>
      </div>
    </div>
    <div class="dp_item">
      <img src="../images/img/hyk.img/123456.png" alt="" class="one">
      <div class="ms">
          <p>太平洋百货肉蟹煲店</p>
          <p>浙江省嘉兴市嘉善县万联城5-802</p>
          <img class="two" src="../images/img/hyk.img/37e46c6d9c08f4962720a8b618d15cca.png" alt="">
          <span>￥45/人</span>
      </div>
      <div class="right">
          <span>8.8折</span>
          <img class="three" src="../images/img/hyk.img/组 78.png" alt="">
          <img src="../images/img/hyk.img/组 79.png" alt="">
          <img src="../images/img/hyk.img/组 80.png" alt="">
          <p>600m</p>
          <p>余额￥35积分65</p>
      </div>
    </div>
  </div>
  <div class="box">
    <div class="pay">
      <p class="pay_title">提示</p>
      <p class="pay_text">是否升级为银卡会员？</p>
      <div class="btn">
        <div class="btn_left" @click="remove()">取消</div>
        <div class="btn_right" @click="ensure()">确定</div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import $ from 'jquery'
import qs from 'qs'
import wx from 'weixin-js-sdk'
export default {
  methods: {
    back: function () {
      this.$router.go(-1)
    },
    pay: function () {
      let tokens = localStorage.getItem('token')
      let data = qs.stringify({ 'token': tokens })
      // let data = this.access.token
      this.$axios({
        url: 'http://114.55.103.26:8085/member/login/getMember',
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: data
      }).then((res) => {
        if (res.data.code === 200) {
          if (res.data.data.memberType === 1) {
            $('.box').css({ 'display': 'block' })
          }
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    remove: function () {
      $('.box').css({ 'display': 'none' })
    },
    ensure: function () {
      let tokens = localStorage.getItem('token')
      let data = qs.stringify({ 'token': tokens })
      // let data = this.access.token
      this.$axios({
        url: 'http://114.55.103.26:8085/api/member/upgradeSilver',
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: data
      }).then((res) => {
        if (res.data.code === 200) {
          $('.box').css({ 'display': 'none' })
          let args = res.data.data
          this.wexinPay(args)
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    wexinPay (data, cb, errorCb) {
      //获取后台传入的数据
      let appId = data.appId
      let timestamp = data.timeStamp
      let nonceStr = data.nonceStr
      let signature = data.signature
      let packages = data.package
      let paySign = data.paySign
      let signType = 'MD5'
      console.log('发起微信支付')
      //下面要发起微信支付了
      wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature, // 必填，签名，见附录1
        jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
      })
      wx.ready(function () {
        wx.chooseWXPay({
          timestamp: timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
          nonceStr: nonceStr, // 支付签名随机串，不长于 32 位
          package: packages, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
          signType: signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
          paySign: paySign, // 支付签名
          success: function (res) {
            // 支付成功后的回调函数
            cb(res)
          },
          fail: function (res) {
            //失败回调函数
            errorCb(res)
          }
        })
      })
    }
  }
}
</script>

<style lang="less" scoped>
.header {
  position: relative;
}
.header img {
  width: 100%;
  height: 161px;
}
.header .arrow {
  width: 11px;
  height:20px;
  background-image: url('../images/img/hyk.img/fanhui3 .png');
  background-size: 100%;
  position: absolute;
  top:26px ;
  left:9px;
  color: #fff;
}
.header p {
  position: absolute;
  top: 32px;
  left: 50%;
  transform: translateX(-50%);
  font-size: 14px;
  font-family: Adobe Heiti Std;
  font-weight: bold;
  color: #fff;
}
.header .hyk {
  position: absolute;
  top: 71px;
  left: 50%;
  transform: translateX(-50%);
  width: 256px;
  height: 138px;
  background-image: url('../images/img/hyk.img/hyk.png');
  background-size: 100%;
}
.center {
  margin-left: 2px;
  margin-top: 80px;
  margin-right: 25px;
  height: 17px;
  position: relative;
}
.center img {
  width: 18px;
  height: 17px;
  margin-right: 7px;
}
.center span {
  position: absolute;
  font-size: 9px;
  font-family: Adobe Heiti Std;
  font-weight: normal;
  color: #000;
  top: 4px;
}
.center .my{
  float: right;
  border: 1px solid #FD5E3F;
  width: 52px;
  height: 12px;
  top:4px;
  border-radius:4px;
  position: relative;
 }
 .center .my img{
  position: absolute;
   width: 8px;
   height: 7px;
   left:6px;
   top:50%;
   transform:translateY(-50%);
   float: left;
 }
 .center .my span{
   font-size:7px;
   font-family:PingFang SC;
   font-weight:500;
   color:rgba(253,94,63,1);
    position: absolute;
    left:18px;
    top:50%;
     transform:translateY(-50%);
 }
 .search{
   margin: 7px auto;
   position: relative;
   width: 216px;
   height: 20px;
    input{
   width: 216px;
   height: 20px;
   border-radius: 9px;
   background-color: #eee;
   border: none;
 }
 img{
    position: absolute;
     width: 10px;
     height: 10px;
     left: 8px;
     top:50%;
     transform: translateY(-50%);
   }
   button{
     width: 30px;
     height: 20px;
     background-color: #FD5E3F;
     border-radius:10px ;
     border:none;
     position: absolute;
     top:0px;
     right: 0px;
     color:#fff;
     font-size:9px;
     font-family:PingFang SC;
     font-weight:bold;
   }
 }
.dp {
  margin-top: 24px;
  margin-right: 6px;
  margin-left: 10px;
}
.dp .dp_item {
overflow: hidden;
margin-bottom: 14px;
}
.dp .dp_item .one {
  width: 56px;
  height: 56px;
  margin-right:4px;
  float: left;
}
.dp .dp_item .ms {
 padding-top: 2px;
 float: left;
}
.dp .dp_item .ms p {
  font-size: 11px;
  font-family: Adobe Heiti Std;
  font-weight: normal;
  color: #333333;
  // margin-bottom: 6px;
}
.dp .dp_item .ms p:nth-child(2) {
  font-size: 8px;
  font-family: SourceHanSansCN;
  font-weight: 400;
  color: #808080;
  margin-bottom: 8px;
  // margin-bottom:10px;
}
.dp .dp_item .ms span{
  font-size:6px;
  font-family:Adobe Heiti Std;
  font-weight:normal;
  color:rgba(165,165,165,1);
}
.dp .dp_item .ms .two {
  width: 48px;
  height: 9px;
}
.dp .dp_item .right {
  float: right;
  position: relative;
  // overflow: hidden;
}
.dp .dp_item .right span{
  background:rgba(184,199,213,0);
  border:1px solid rgba(239, 162, 111, 1);
  border-radius:2px;
  color:#FD5E3F;
  height: 11px;
  margin-right: 3px;
  box-sizing: border-box;
  font-size: 11px;

}
.dp .dp_item .right img {
  width: 11px;
  height: 11px;
  margin-right:3px;
}
.dp .dp_item .right p {
  font-size:9px;
  position: absolute;
  font-family: SourceHanSansCN;
  font-weight: 400;
  color: #808080;
  top: 20px;
  right:0px
}
.dp .dp_item .right p:nth-child(6) {
   position: absolute;
   top:45px;
}
.box{
  width: 100%;
  height:707px;
  background:rgba(0,0,0,0.35);
  position: fixed;
  top: 0;
  display: none;
  .pay{
    width:232px;
    height:104px;
    background:rgba(255,255,255,1);
    border-radius:4px;
    position: relative;
    top: 215px;
    left: 34px;
    z-index: 999;
    .pay_title{
      font-size:12px;
      font-family:PingFang SC;
      font-weight:bold;
      color:rgba(53,131,240,1);
      text-align: center;
      margin-top: 3px;
    }
    .pay_text{
      font-size:10px;
      font-family:PingFang SC;
      font-weight:500;
      color:rgba(0,0,0,1);
      text-align: center;
      margin-top: 15px;
    }
    .btn{
      display: flex;
      width: 100%;
      position: absolute;
      bottom: 0;
      .btn_left{
        width:116px;
        height:32px;
        background:rgba(245,245,245,1);
        border-radius:0px 0px 0px 4px;
        text-align: center;
        line-height: 32px;
        font-size:12px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(153,153,153,1);
      }
      .btn_right{
        width:116px;
        height:32px;
        background:rgba(53,131,240,1);
        border-radius:0px 0px 4px 0px;
        text-align: center;
        line-height: 32px;
        font-size:12px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(255,255,255,1);
      }
    }
  }
}
</style>
