<template>
  <div class="merchants">
    <div class="bg_color">
      <div class="gift_imgtop"><img src="../images/img/gift/black.png"/></div>
      <div class="gift_topwen"><span>开通商户</span></div>
    </div>
    <div class="ABC" action="">
      <div class="idCard">
        <p>请拍摄并上传你的有效身份证</p>
        <ul class="idCard_list">
          <li>
            <img src="../images/login.img/card1.png" alt="" id="idCard_1">
            <input type="file" accept="image/*" @change="onchangeid1($event)" id="card_1">
            <div class="up_btn">上传身份证正面</div>
          </li>
          <li>
            <img src="../images/login.img/card2.png" alt="" id="idCard_2">
            <input type="file" accept="image/*" @change="onchangeid2($event)" id="card_2">
            <div class="up_btn">上传身份证正面</div>
          </li>
        </ul>
      </div>
      <ul class="menu_list">
        <li>请输入商户信息</li>
        <li>
          <label for="name">姓名</label>
          <input type="text" id="name" name="name">
        </li>
        <li>
          <label for="card">身份证号码</label>
          <input type="number" id="card" name="idcard">
        </li>
        <li>
          <label for="map">地址</label>
          <input type="text" id="map" name="address">
        </li>
        <li class="my-container">
          <van-cell @click="showPopup">
            <label for="maps">地区</label>
            <input type="text" id="maps" name="">
          </van-cell>
          <van-popup position="bottom" :get-container="getContainer" v-model="show">
            <van-area @confirm.self='confirmFn' @cancel="cancel" :area-list="areaList" :columns-num="3"/>
          </van-popup>
        </li>
        <li>
          <label for="name">入驻类型</label>
          <select name="merchantType" id="select_1">
            <option value="1">基本商户</option>
            <option value="2">黄金商户</option>
          </select>
        </li>
        <li>
          <label for="name">商户类别</label>
          <select name="merchantType" id="select_2">
            <option value="1">类别</option>
            <option value="2">餐饮</option>
            <option value="3">酒店</option>
            <option value="4">美容养生</option>
          </select>
        </li>
        <li>
          <label for="idCard">银行卡号</label>
          <input type="number" id="idCard" name="bankCard">
        </li>
        <li>
          <label for="cellphone">预留手机号</label>
          <input type="number" id="cellphone" name="cellphone">
        </li>
      </ul>
      <div class="card_tu">
        <p>请上传银行卡照片</p>
        <ul class="card_tu_list">
          <li>
            <img src="../images/login.img/pic1.png" alt="" id="bankCard_1">
            <input type="file" accept="image/*" @change="onchangeid3($event)" id="bank_1">
            <div class="up_btn">请上传银行卡照片</div>
            <span>正</span>
          </li>
          <li>
            <img src="../images/login.img/pic1.png" alt="" id="bankCard_2">
            <input type="file" accept="image/*" @change="onchangeid4($event)" id="bank_2">
            <div class="up_btn">请上传银行卡照片</div>
            <span>正</span>
          </li>
        </ul>
      </div>
      <div class="footer">
        <p>上传商户有效照片</p>
        <ul class="card_tu_list">
          <li>
            <img src="../images/login.img/pic1.png" alt="" id="idCard_3">
            <input type="file" accept="image/*" @change="onchangeid5($event)" id="card_3">
            <div class="up_btn">上传门头照片</div>
          </li>
          <li>
            <img src="../images/login.img/pic1.png" alt="" id="idCard_4">
            <input type="file" accept="image/*" @change="onchangeid6($event)" id="card_4">
            <div class="up_btn">上传店内照片</div>
          </li>
          <li>
            <img src="../images/login.img/pic1.png" alt="" id="idCard_5">
            <input type="file" accept="image/*" @change="onchangeid7($event)" id="card_5">
            <div class="up_btn">上传店内照片</div>
          </li>
          <li>
            <img src="../images/login.img/pic1.png" alt="" id="idCard_6">
            <input type="file" accept="image/*" @change="onchangeid8($event)" id="card_6">
            <div class="up_btn">上传营业执照</div>
          </li>
        </ul>
      </div>
      <input type="button" value="确认上传" class="btn" @click="upLoading ">
    </div>
  </div>
</template>

<script>
import router from '../router'
import areaList from '../js/area.js'
import $ from 'jquery'
import qs from 'qs'
import wx from 'weixin-js-sdk'
export default {
  name: 'Merchants',
  data () {
    return {
      show: false,
      areaList: areaList,
      // pic: [
      //   { 'idPic1': '' },
      //   { 'idPic2': '' },
      //   { 'bankPic1': '' },
      //   { 'bankPic2': '' },
      //   { 'idPic3': '' },
      //   { 'idPic4': '' },
      //   { 'idPic5': '' },
      //   { 'idPic6': '' }
      // ],
      list: {
        card1: '',
        card2: '',
        name: '',
        idcard: '',
        address: '',
        regionName: '',
        regionCode: '',
        select1: '',
        select2: '',
        bank: '',
        cellphone: '',
        bank1: '',
        bank2: '',
        card3: '',
        card4: '',
        card5: '',
        card6: '',
        token: ''
      }
    }
  },
  methods: {
    getContainer () {
      return document.querySelector('.my-container')
    },
    showPopup () {
      this.show = true
    },
    confirmFn (res) {
      let site = res
      var arr = []
      for (var i = 0; i < site.length; i++) {
        var code = site[2].code
        arr.push(site[i].name)
        var arr1 = arr.join('')
      }
      this.list.regionCode = code
      // console.log(arr1)
      $('#maps').val(arr1)
      this.show = false
    },
    cancel () {
      this.show = false
    },
    onchangeid1 (e) {
      this.imgLoad(1, 'card_1', 'idCard_1')
    },
    onchangeid2 (e) {
      this.imgLoad(2, 'card_2', 'idCard_2')
    },
    onchangeid3 (e) {
      this.imgLoad(3, 'bank_1', 'bankCard_1')
    },
    onchangeid4 (e) {
      this.imgLoad(4, 'bank_2', 'bankCard_2')
    },
    onchangeid5 (e) {
      this.imgLoad(5, 'card_3', 'idCard_3')
    },
    onchangeid6 (e) {
      this.imgLoad(6, 'card_4', 'idCard_4')
    },
    onchangeid7 (e) {
      this.imgLoad(7, 'card_5', 'idCard_5')
    },
    onchangeid8 (e) {
      this.imgLoad(8, 'card_6', 'idCard_6')
    },
    imgLoad (type, cards, idCards) {
      var formData = new FormData()
      formData.append('file', $('#' + cards)[0].files[0])
      // let argument = qs.stringify(data)
      this.$axios({
        url: 'http://114.55.103.26:8085/api/file/imageUpload',
        data: formData,
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then((res) => {
        console.log(res)
        // $('#' + cards).val(res.id)
        $('#' + idCards).attr('src', res.data)
      })
    },
    upLoading: function () {
      this.list.token = localStorage.getItem('token')
      let card_1 = $('#card_1').val()
      let card_2 = $('#card_2').val()
      this.list.name = $('#name').val()
      this.list.idcard = $('#card').val()
      this.list.address = $('#map').val()
      this.list.regionName = $('#maps').val()
      this.list.select1 = $('#select_1').val()
      this.list.select2 = $('#select_2').val()
      this.list.bank = $('#idCard').val()
      this.list.cellphone = $('#cellphone').val()
      this.list.card1 = $('#idCard_1').attr('src')
      this.list.card2 = $('#idCard_2').attr('src')
      this.list.bank1 = $('#bankCard_1').attr('src')
      this.list.bank2 = $('#bankCard_2').attr('src')
      this.list.card3 = $('#idCard_3').attr('src')
      this.list.card4 = $('#idCard_4').attr('src')
      this.list.card5 = $('#idCard_5').attr('src')
      this.list.card6 = $('#idCard_6').attr('src')
      let bank_1 = $('#bank_1').val()
      let bank_2 = $('#bank_2').val()
      let card_3 = $('#card_3').val()
      let card_4 = $('#card_4').val()
      let card_5 = $('#card_6').val()
      let card_6 = $('#card_6').val()
      let myRegcard = /^[1-9]\d{5}(18|19|20|(3\d))\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
      let myreg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/
      if (card_1 === undefined || card_1 === null || card_1 === '' || card_1.length < 1) {
        alert('请选择身份证正面')
      }
      if (card_2 === undefined || card_2 === null || card_2 === '' || card_2.length < 1) {
        alert('请选择身份证反面')
      }
      if (this.list.name === undefined || this.list.name === null || this.list.name === '' || this.list.name.length < 1) {
        alert('姓名不能为空')
      }
      if (!myRegcard.test(this.list.idcard)) {
        alert('身份证号码有误')
      }
      if (this.list.regionName === undefined || this.list.regionName === null || this.list.regionName === '' || this.list.regionName.length < 1) {
        alert('地址不能为空')
      }
      if (this.list.regionCode === undefined || this.list.regionCode === null || this.list.regionCode === '' || this.list.regionCode.length < 1) {
        alert('地区不能为空')
      }
      if (this.list.select2 === undefined || this.list.select2 === null || this.list.select2 === '' || this.list.select2.length < 1 || this.list.select_2 == 1) {
        alert('请选择类别')
      }
      if (this.list.bank === undefined || this.list.bank === null || this.list.bank === '' || this.list.bank.length < 1) {
        alert('银行卡号不能为空')
      }
      if (!myreg.test(this.list.cellphone)) {
        alert('手机号有误')
      }
      if (bank_1 === undefined || bank_1 === null || bank_1 === '' || bank_1.length < 1) {
        alert('请选择银行卡照片正面')
      }
      if (bank_2 === undefined || bank_2 === null || bank_2 === '' || bank_2.length < 1) {
        alert('请选择银行卡照片反面')
      }
      if (card_3 === undefined || card_3 === null || card_3 === '' || card_3.length < 1) {
        alert('请选择门头照片')
      }
      if (card_4 === undefined || card_4 === null || card_4 === '' || card_4.length < 1) {
        alert('请选择店内照片1')
      }
      if (card_5 === undefined || card_5 === null || card_5 === '' || card_5.length < 1) {
        alert('请选择店内照片2')
      }
      if (card_6 === undefined || card_6 === null || card_6 === '' || card_6.length < 1) {
        alert('请选择营业执照')
      }
      let information = qs.stringify(this.list)
      this.$axios({
        url: 'http://114.55.103.26:8085/api/merchant/addMerchant',
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: information
      }).then((res) => {
        console.log(res)
        if (res.data.code === 200) {
          let args = res.data.data
          this.wexinPay(args)
          alert('提交成功！')
          router.push('/index')
        } else {
          alert('提交失败！')
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
  .merchants{
    width: 100%;
    overflow-x: hidden;
    background-color: #F5F7FC ;
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
  .ABC{
    .idCard{
      background: rgba(255,255,255,1);
      width: 100%;
      height: 160px;
      p{
        font-size:12px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
        text-indent: 12px;
      }
      .idCard_list{
        background:rgba(255,255,255,1);
        display: flex;
        position: relative;
        li{
          top: 8px;
          width:130px;
          height:112px;
          background:rgba(244,248,254,1);
          border-radius:4px;
          position: relative;
          img{
            width: 98px;
            height: 66px;
            margin: 11px 0 0 16px;
          }
          .up_btn{
            width:130px;
            height:24px;
            background:rgba(85,129,255,1);
            border-radius:0px 0px 4px 4px;
            text-align: center;
            line-height: 24px;
            font-size:11px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(255,255,255,1);
            position: absolute;
            bottom: 0;
          }
          input{
            position: absolute;
            bottom: 0;
            z-index: 999;
            left: 0;
            opacity: 0;
          }
        }
        li:nth-of-type(1){
          margin-left: 12px;
          margin-right: 17px;
        }
      }
    }
    .menu_list{
      margin-top: 11px;
      li:nth-of-type(1){
        margin-bottom: 0;
      }
      li{
        width:300px;
        height:35px;
        background:rgba(255,255,255,1);
        /*outline: 1px solid red;*/
        display: flex;
        line-height: 35px;
        justify-content: space-between;
        text-indent: 12px;
        margin-bottom: 10px;
        label{
          font-size:12px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(51,51,51,1);
        }
        input{
          outline: none;
          text-align: right;
          border: 0;
          width: 200px;
          margin-right: 15px;
          font-size:12px;
          font-family:PingFang SC;
          font-weight:500;
        }
        select{
          background-color: #FFF;
          border: 0;
          outline: none;
          margin-right: 15px;
          font-size:12px;
          font-family:PingFang SC;
          font-weight:500;
        }
      }
    }
    .card_tu{
      width: 100%;
      height: 190px;
      background-color: #FFF;
      margin-top: 9px;
      p{
        font-size:12px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
        text-indent: 12px;
        position: relative;
        top: 8px;
      }
      .card_tu_list{
        display: flex;
        margin: 12px 0 0 12px;
        li:nth-of-type(1){
          margin-right: 16px;
        }
        li{
          width:130px;
          height:112px;
          background:rgba(244,248,254,1);
          border-radius:4px;
          position: relative;
          top: 20px;
          img{
            width: 98px;
            height: 66px;
            margin: 11px 0 0 16px;
          }
          .up_btn{
            width:130px;
            height:24px;
            background:rgba(85,129,255,1);
            border-radius:0px 0px 4px 4px;
            font-size:11px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(255,255,255,1);
            text-align: center;
            line-height: 24px;
            position: absolute;
            bottom: 0;
          }
          input{
            position: absolute;
            bottom: 0;
            left: 0;
            z-index: 999;
            opacity: 0;
          }
          span{
            position: absolute;
            left: 46px;
            top: 30px;
            font-size:31px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(229,224,224,1);
            line-height:35px;
            opacity:0.68;
          }
        }
      }
    }
    .footer{
      width: 100%;
      height: 296px;
      background-color: #FFF;
      margin-top: 22px;
      p{
        font-size:12px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
        text-indent: 12px;
        position: relative;
        top: 8px;
      }
      .card_tu_list{
        display: flex;
        margin: 0 0 0 12px;
        flex-wrap: wrap;
        li:nth-of-type(1){
          margin-right: 16px;
        }
        li:nth-of-type(3){
          margin: 23px 15px 0 0;
        }
        li:nth-of-type(4){
          margin-top:23px;
        }
        li{
          width:130px;
          height:112px;
          background:rgba(244,248,254,1);
          border-radius:4px;
          position: relative;
          top: 20px;
          img{
            width: 98px;
            height: 66px;
            margin: 11px 0 0 16px;
          }
          .up_btn{
            width:130px;
            height:24px;
            background:rgba(85,129,255,1);
            border-radius:0px 0px 4px 4px;
            font-size:11px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(255,255,255,1);
            text-align: center;
            line-height: 24px;
            position: absolute;
            bottom: 0;
          }
          input{
            position: absolute;
            bottom: 0;
            left: 0;
            z-index: 999;
            opacity: 0;
          }
          span{
            position: absolute;
            left: 46px;
            top: 30px;
            font-size:31px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(229,224,224,1);
            line-height:35px;
            opacity:0.68;
          }
        }
      }
    }
    .btn{
      width:276px;
      height:35px;
      background:rgba(85,129,255,1);
      box-shadow:0px 3px 6px 0px rgba(36,134,255,0.2);
      border-radius:18px;
      font-size:14px;
      font-family:PingFang SC;
      font-weight:bold;
      color:rgba(255,255,255,1);
      line-height: 35px;
      text-align: center;
      border: 0;
      outline: none;
      margin: 38px 0 35px 12px;
    }
  }
  .van-cell{
    padding: 0px!important;
    display: flex;
    justify-content: space-between;
  }
  .van-cell__value{
    height: 35px!important;
    input{
      margin-left: 48px;
      height: 35px;
      text-align: right;
    }
  }
</style>
