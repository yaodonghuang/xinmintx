<template>
    <div class="ShopingIndex">
      <div class="header">
        购物车<span @click="editors" class="header_span">{{editor}}</span>
      </div>
      <div v-for="(item,index) in list" :key="index" class="shopping">
        <div class="shopping_p1">
          <div class="shopping_p1_input">
            <input type="checkbox"  v-model="item.check" @change="change(index)" class="check_box tui-checkbox">
          </div>
          <p class="shopping_p1_p">{{item.shopName }}></p>
        </div>
        <div class="shop">
          <div v-for="(car,id) in item.carVos" :key="id" class="shop_content">
            <input type="checkbox"
                   :price="car.price"
                   :num="car.total"
                   :dataId="item.carVos.id"
                   name ="goodRadio"
                   v-model="car.checked"
                   @change="change1(index)"
                   class="check_box tui-checkbox tu-checkbox">
            <img :src="car.imge" alt="">
            <div class="shop_content_commodity">
              <p class="commodity">{{car.commodityName}}</p>
              <p class="kindName">{{car.kindName}}-{{car.typologyName}}</p>
              <p class="price">￥<span>{{car.price}}</span></p>
            </div>
            <div class="btn">
              <span class="btn_first" @click="down(index,id)">-</span>
              <span class="btn_number">{{car.total}}</span>
              <span class="btn_second" @click="up(index,id)">+</span>
            </div>
          </div>
        </div>
      </div>
      <div class="payment">
        <label for="chack">
          <input type="checkbox" @change="changeAll($event)" id="chack" v-model="allcheck" class="check_box tui-checkbox">
          全选
        </label>
        <div class="payment_toggle">
          <span class="text">不含运费</span>
          <p>共计：<span>￥</span><span>{{totalPrice}}</span></p>
          <div class="toggle" @click="pay()">{{menu}}</div>
        </div>
      </div>
    </div>
</template>

<script>
// import qs from 'qs'
import router from '../../router'
export default {
  name: 'ShopingIndex',
  data () {
    return {
      editor: '编辑',
      isEditor: true,
      list: [],
      money: 0, // 合计金额
      menu: '去结算',
      carVos: [],
      totalNumber: 0, // 总数
      toggle: false
    }
  },
  computed: {
    totalPrice () {
      var total = 0
      this.list.forEach(item => {
        item.carVos.forEach(item1 => {
          if (item1.checked) {
            total += item1.total * item1.price
          }
        })
      })
      return total
    },
    allcheck () {
      let flag = this.list.every(item => item.check)
      return flag
    }
  },
  methods: {
    up: function (index, id) {
      var spanList = event.currentTarget.parentNode.children
      var caculNum = 0
      for (var i = 0; i < spanList.length; i++) {
        if (spanList[i].getAttribute('class') === 'btn_number') {
          caculNum = spanList[i].innerHTML
          caculNum ++
        }
      }
      this.list[index].carVos[id].total = caculNum
    },
    down (index, id) {
      var spanList = event.currentTarget.parentNode.children
      for (var i = 0; i < spanList.length; i++) {
        if (spanList[i].getAttribute('class') == 'btn_number') {
          // spanList[i].style.display = 'block';
          var caculNum = spanList[i].innerHTML
          if (caculNum < 2) {
            alert('宝贝不能再少了哦')
          } else {
            caculNum --
          }
        }
      }
      this.list[index].carVos[id].total = caculNum
    },
    pay () {
      var carShop = []
      if (this.isEditor === true) {
        this.list.forEach(item => {
          item.carVos.forEach(item1 => {
            if (item1.checked) {
              carShop.push(item1)
            }
          })
        })
        if (carShop.length < 1) {
          return
        }
        this.$axios({
          url: 'http://114.55.103.26:8085/api/car/updateCar',
          method: 'post',
          data: carShop
        }).then((res) => {
          if (res.data.code === 200) {
            router.push({ name: 'Orders', params: { carShop: carShop }})
            // console.log(carShop)
          }
        }).catch((err) => {
          console.log(err)
        })
      } else {
        alert(2)
      }
    },
    editors: function () {
      this.isEditor = !this.isEditor
      if (this.isEditor === true) {
        this.editor = '编辑'
        this.menu = '去结算'
      } else {
        this.editor = '完成'
        this.menu = '删除'
      }
    },
    change: function (index) {
      for (var i = 0; i < this.list[index].carVos.length; i++) {
        this.list[index].carVos[i].checked = true
      }
      if (!this.list[index].check) {
        for (var j = 0; j < this.list[index].carVos.length; j++) {
          this.list[index].carVos[j].checked = false
        }
      }
    },
    change1: function (index) {
      if (this.list[index].carVos.every(item => item.checked)) {
        this.list[index].check = true
      } else {
        this.list[index].check = false
      }
    },
    changeAll: function (e) {
      if (e.target.checked) {
        this.list.forEach(item => {
          item.check = true
          item.carVos.forEach(item1 => {
            item1.checked = true
          })
        })
      } else {
        this.list.forEach(item => {
          item.check = false
          item.carVos.forEach(item1 => {
            item1.checked = false
          })
        })
      }
    }
  },
  mounted () {
    this.$axios({
      url: 'http://114.55.103.26:8085/api/car/selectCar',
      method: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
        // 'token': '9cc56b81-858e-4ebd-b3f6-7a309fedf347'
      }
    }).then((res) => {
      if (res.data.code === 200) {
        this.list = res.data.data
        this.carVos = this.list.carVos
        this.total = this.carVos.total
        this.caculate()
      } else if (res.data.code === 500) {
        // alert(0)
      }
    }).catch((err) => {
      console.log(err)
    })
  }
}
</script>

<style lang="less" scoped>
.ShopingIndex{
  .header{
    width: 100%;
    height: 38px;
    position: relative;
    line-height: 38px;
    text-align: center;
    font-size:14px;
    font-family:PingFang SC;
    font-weight:bold;
    color:rgba(0,0,0,1);
    background-color: #FFF;
    margin-bottom: 12px;
    .header_span{
      position: absolute;
      right: 16px;
      font-size:11px;
      font-family:PingFang SC;
      font-weight:bold;
      color:rgba(0,0,0,1);
    }
  }
  .payment{
    width:275px;
    height:52px;
    background:rgba(255,255,255,1);
    border-radius:4px;
    position: fixed;
    bottom: 54px;
    left: 12px;
    display: flex;
    label{
      margin: 18px 0 0 8px;
      font-size:12px;
      font-family:PingFang SC;
      font-weight:500;
      color:rgba(0,0,0,1);
      #chack{
        margin-right: 4px;
        margin-top: 2px;
      }
    }
    .payment_toggle{
      display: flex;
      .text{
        font-size:6px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(118,118,118,1);
        margin-left: 26px;
        margin-top: 18px;
      }
      p{
        font-size:12px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(0,0,0,1);
        margin-right: 12px;
        margin-top: 14px;
        margin-left: 4px;
        padding-right: 6px;
        span{
          color:rgba(243, 11, 11, 1);
        }
      }
      .toggle{
        width:64px;
        height:28px;
        background:linear-gradient(-90deg,rgba(252,121,79,1),rgba(254,88,59,1));
        border-radius:14px;
        line-height: 28px;
        text-align: center;
        font-size:12px;
        font-family:PingFang SC;
        font-weight:bold;
        color:rgba(255,255,255,1);
        margin-top: 13px;
      }
    }
  }
  .shopping{
    width:275px;
    background:rgba(255,255,255,1);
    border-radius:4px;
    margin:0 0 8px 12px;
    padding-bottom: 12px;
    .shopping_p1{
      font-size:8px;
      font-family:PingFang SC;
      font-weight:500;
      color:rgba(0,0,0,1);
      display: flex;
      .shopping_p1_input{
        margin-right: 11px;
        margin-left: 8px;
        width: 13px;
        height: 14px;
        margin-top: 9px;
      }
      .shopping_p1_p{
        font-size:8px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(0,0,0,1);
        padding-top: 6px;
      }
    }
    .shop{
      margin-top:-8px;
      .shop_content{
        margin-top: 20px;
        height: 51px;
        display: flex;
        position: relative;
        img{
          width:49px;
          height:51px;
          border-radius:4px;
        }
        .shop_content_commodity{
          margin-left: 18px;
          .commodity{
            font-size:10px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(0,0,0,1);
          }
          .kindName{
            font-size:7px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(128,128,128,1);
          }
          .price{
            font-size:12px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(234,23,23,1);
          }
        }
        .btn{
          width:54px;
          height:15px;
          border:1px solid rgba(166,166,176,1);
          border-radius:4px;
          display: flex;
          position: absolute;
          top: 34px;
          right: 10px;
          span{
            display: block;
          }
          .btn_first{
            width: 16px;
            height: 15px;
            line-height: 15px;
            text-align: center;
            font-size:16px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(166,166,176,1);
          }
          .btn_number{
            width: 22px;
            height: 15px;
            line-height: 15px;
            text-align: center;
            border-right:1px solid rgba(166,166,176,1);
            border-left:1px solid rgba(166,166,176,1);
            font-size:10px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(166,166,176,1);
          }
          .btn_second{
            width: 16px;
            height: 15px;
            line-height: 15px;
            text-align: center;
            font-size:16px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(166,166,176,1);
          }
        }
      }
    }
  }
  /*input表框的样式*/
  .tui-checkbox:checked {
    background: #FC6A46;
    border: solid 1px #FC6A46;
  }
  .tui-checkbox {
    width: 13px;
    height: 14px;
    background-color: #ffffff;
    border: solid 1px #dddddd;
    -webkit-border-radius: 50%;
    border-radius: 50%;
    font-size: 0.8rem;
    margin: 0;
    padding: 0;
    position: relative;
    display: inline-block;
    vertical-align: top;
    cursor: default;
    -webkit-appearance: none;
    -webkit-user-select: none;
    user-select: none;
    -webkit-transition: background-color ease 0.6s;
    transition: background-color ease 0.6s;
  }
  .tu-checkbox{
    margin: 19px 11px 0 8px;
  }
  .tui-checkbox:checked::after {
    content: '';
    top: 3px;
    left: 2px;
    position: absolute;
    background: transparent;
    border: #fff solid 2px;
    border-top: none;
    border-right: none;
    height: 3px;
    width: 5px;
    -moz-transform: rotate(-45deg);
    -ms-transform: rotate(-45deg);
    -webkit-transform: rotate(-45deg);
    transform: rotate(-45deg);
  }
}
</style>
