<template>
  <div class="bgcolor">
    <div class="index_top">
      <div class="black"><img  v-on:click="back" class="black_img" src="../images/img/article/black.png"/></div>
      <div class="go"><img class="go_img" src="../images/img/article/gengduo.png"/></div>
      <van-swipe :autoplay="3000" indicator-color="white">
        <van-swipe-item class="shop_lbt" v-for="imges in imgList"><img class="img_top" :src="imges"/></van-swipe-item>
      </van-swipe>
    </div>
    <div class="tiao1">
      <p class="p1">¥{{shopList.price}}</p>
      <p class="p2">{{shopList.commodityName}}</p>
    </div>
    <div class="tiao1">
      <van-cell @click="showPopup" is-link><span class="s1 ">选择</span><span class="s2 ">颜色分类</span></van-cell>
      <van-cell is-link><span class="s1 ">配送</span><span class="s2 van- ellipsis">{{shopList.address}}至浙江省杭州市 免运费</span>
      </van-cell>
    </div>
    <div>
      <van-sku
        v-model="show"
        :sku="sku"
        ref="shop"
        :goods="goods"
        :goods-id="goodsId"
        :quota="quota"
        :quota-used="quotaUsed"
        :hide-stock="sku.hide_stock"
        :message-config="messageConfig"
        @buy-clicked="onBuyClicked"
        @add-cart="onAddCartClicked"
      />
    </div>
    <div class="tiao1">
      <van-cell is-link><span class="s1 ">商品评价（1635）</span></van-cell>
    </div>
    <div class="tiao2"><img class="logoimg" src="../images/img/logo.png"><span class="s4 ">店家名字在这里</span><span
      class="s3">进店逛逛</span></div>
    <div>
      <img class="showimg" :src="shopList.ossUrl"/>
    </div>
    <div class="dianzi"></div>
    <div>
      <van-goods-action>
        <van-goods-action-icon icon="shop-o" text="店铺"/>
        <van-goods-action-icon icon="chat-o" text="客服"/>
        <van-goods-action-icon icon="star-o" text="收藏"/>
        <van-goods-action-button type="warning" text="加入购物车"/>
        <van-goods-action-button type="danger" text="立即购买" @click="payshop"/>
      </van-goods-action>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
import qs from 'qs'
import wx from 'weixin-js-sdk'

export default {
  data () {
    return {
      id: '87',
      imgList: [],
      shopList: [],
      show: false,
      usertoken: '',
      sku: {
        tree: [
          {
            k: '种类', // skuKeyName：规格类目名称
            v: [],
            k_s: 's1' // skuKeyStr：sku 组合列表（下方 list）中当前类目对应的 key 值，value 值会是从
          },
          {
            k: '规格', // skuKeyName：规格类目名称
            v: [],
            k_s: 's2' // skuKeyStr：sku 组合列表（下方 list）中当前类目对应的 key 值，value 值会是从属于当前类目的一个规格值 id
          }
        ],
        // 所有 sku 的组合列表，比如红色、M 码为一个 sku 组合，红色、S 码为另一个组合
        list: []
      },
      goods: {
      },
      messageConfig: {
        // 数据结构见下方文档
      }
    }
  },
  mounted () {
    // this.id = this.$route.params.shopid
    this.$axios({
      url: 'http://114.55.103.26:8085/goods/show/getGoodById/' + this.id,
      methods: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then(res => {
      this.imgList = res.data.data.pictyre
      this.shopList = res.data.data.commodityExt
    })
    this.$axios({
      url: 'http://114.55.103.26:8085/goods/show/getType/' + this.id,
      methods: 'post'
    }).then(res => {
      var kinds = []
      var types = []
      var makes = []
      for (let i = 0; i < res.data.data.kinds.length; i++) {
        var kindmap = { id: '', name: '' }
        kindmap.id = res.data.data.kinds[i].id
        kindmap.name = res.data.data.kinds[i].kindName
        kinds.push(kindmap)
        this.sku.tree[0].v = kinds
      }
      for (let j = 0; j < res.data.data.types.length; j++) {
        var typemap = { id: '', name: '', previewImgUrl: '' }
        typemap.id = res.data.data.types[j].id
        typemap.name = res.data.data.types[j].kindType
        typemap.previewImgUrl = res.data.data.extents[j].kindUrl
        types.push(typemap)
        this.sku.tree[1].v = types
      }
      for (let k = 0; k < res.data.data.extents.length; k++) {
        var make = { id: '', price: '', s1: '', s2: '', stock_num: '' }
        make.id = res.data.data.extents[k].typeId
        make.price = res.data.data.extents[k].kindPrice
        make.s1 = res.data.data.extents[k].kindId
        make.s2 = res.data.data.extents[k].typeId
        make.stock_num = res.data.data.extents[k].inventory
        makes.push(make)
        this.sku.list = makes
      }
    })
  },
  methods: {
    onAddCartClicked () {
      let tokens = localStorage.getItem('token')
      // this.$refs.shop.getSkuData
      console.log(this.$refs.shop.getSkuData(skuData.selectedSkuComb.id))
      this.$axios({
        url: 'http://114.55.103.26:8085/api/car/addCar?token=' + tokens,
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: {
          // commodityTypologyId: this.$refs.shop.getSkuData(skuData.selectedSkuComb.id),
          // commodityId: this.id,
          // count: this.$refs.shop.getSkuData(JSON.skuData.selectedNum)
        }
      }).then((res) => {
        if (res.data.code === 200) {
          $('.box').css({ 'display': 'block' })
          let args = res.data.data
          this.wexinPay(args)
        }
      })
    },
    back(){
      this.$router.go(-1); //返回上一层
    },
    showPopup () {
      this.show = true
    },
    payshop () {
      let tokens = localStorage.getItem('token')
      let data = qs.stringify({ 'token': tokens, 'kindId': this.kindid })
      this.$axios({
        url: 'http://114.55.103.26:8085/api/commodity/buyCommodity',
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: data
      }).then((res) => {
        if (res.data.code === 200) {
          $('.box').css({ 'display': 'block' })
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
  .img_top {
    width: 100%;
    height: 300px;
  }

  .black {
    position: absolute;
    float: left;
    margin-top: 8px;
    margin-left: 6px;
    z-index: 2020;
  }

  .black_img {
    height: 21px;
    width: 21px;
  }

  .go {
    position: absolute;
    float: left;
    margin-top: 8px;
    margin-left: 90%;
    z-index: 2020;
  }

  .go_img {
    height: 21px;
    width: 21px;
  }

  .p1 {
    color: red;
    font-size: 14px;
    margin-left: 5%;
  }

  .p2 {
    font-size: 14px;
    width: 90%;
    margin: 0 auto;
  }

  .tiao1 {
    background-color: #FFFFFF;
    margin: 5px auto;
    border-bottom: 5px solid #F5F5F5;
  }

  .s1 {
    margin-left: 5%;
    margin-right: 20px;
    font-size: 10px;
    color: #737373;
  }

  .s2 {
    display: inline-block;
    width: 60%;
    font-size: 10px;
    color: #737373;
  }

  .dianzi {
    padding-bottom: 50px;
  }

  .s4 {
    margin-left: 5px;
    margin-right: 20px;
    font-size: 10px;
    color: #737373;
    line-height: 21px;
  }

  .s3 {
    color: red;
    float: right;
    margin-right: 5%;
    line-height: 21px;
    font-size: 10px;
  }

  .logoimg {
    margin-left: 10%;
    float: left;
    width: 21px;
    height: 21px;
  }

  .tiao2 {
    background-color: #FFFFFF;
    margin: 5px auto;
    border-bottom: 5px solid #F5F5F5;
    padding-bottom: 5px;
  }

  .showimg {
    width: 100%;
  }

  .box {
    width: 100%;
    height: 707px;
    background: rgba(0, 0, 0, 0.35);
    position: fixed;
    top: 0;
    display: none;

    .pay {
      width: 232px;
      height: 104px;
      background: rgba(255, 255, 255, 1);
      border-radius: 4px;
      position: relative;
      top: 215px;
      left: 34px;
      z-index: 999;

      .pay_title {
        font-size: 12px;
        font-family: PingFang SC;
        font-weight: bold;
        color: rgba(53, 131, 240, 1);
        text-align: center;
        margin-top: 3px;
      }

      .pay_text {
        font-size: 10px;
        font-family: PingFang SC;
        font-weight: 500;
        color: rgba(0, 0, 0, 1);
        text-align: center;
        margin-top: 15px;
      }

      .btn {
        display: flex;
        width: 100%;
        position: absolute;
        bottom: 0;

        .btn_left {
          width: 116px;
          height: 32px;
          background: rgba(245, 245, 245, 1);
          border-radius: 0px 0px 0px 4px;
          text-align: center;
          line-height: 32px;
          font-size: 12px;
          font-family: PingFang SC;
          font-weight: 500;
          color: rgba(153, 153, 153, 1);
        }

        .btn_right {
          width: 116px;
          height: 32px;
          background: rgba(53, 131, 240, 1);
          border-radius: 0px 0px 4px 0px;
          text-align: center;
          line-height: 32px;
          font-size: 12px;
          font-family: PingFang SC;
          font-weight: 500;
          color: rgba(255, 255, 255, 1);
        }
      }
    }
  }
</style>
