<template>
    <div class="address">
      <div class="header">
        <img src="../../images/img/cart.img/back.png" alt="" @click="back()">
          收货地址
      </div>
      <ul class="address_list">
        <li v-for="(item,index) in list" :key="index">
          <p class="address_list_p1">{{item.name}}<span>{{item.cellphone}}</span></p>
          <p class="address_list_p2">{{item.address}}</p>
          <img src="../../images/login.img/right.png" alt="" @click="revise(item.id)">
        </li>
      </ul>
      <div class="btn" @click="newAddress()">新增收货地址</div>
    </div>
</template>

<script>
import router from '../../router'
export default {
  name: 'Address',
  data () {
    return {
      list: []
    }
  },
  methods: {
    revise: function (res) {
      router.push({ path: '/newAddress', query: { id: res } })
    },
    back: function () {
      this.$router.go(-1)
    },
    newAddress: function () {
      router.push('/newAddress')
    }
  },
  created () {
    // let token = '9cc56b81-858e-4ebd-b3f6-7a309fedf347'
    // let data = qs.stringify({ 'token': token })
    this.$axios({
      url: 'http://114.55.103.26:8085/api/shippingAddress/selectmemberAddress',
      method: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
      // data: data
    }).then((res) => {
      if (res.data.code === 200) {
        this.list = res.data.data
      }
    }).catch((err) => {
      console.log(err)
      router.push('/Login')
    })
  }
}
</script>

<style lang="less" scoped>
.address{
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
    img{
      position: absolute;
      left: 12px;
      top: 10px;
      width:10px;
      height: 14px;
    }
  }
  .address_list{
    width: 100%;
    margin-top: 12px;
    li{
      width: 100%;
      height: 40px;
      background-color: #FFF;
      margin-bottom: 5px;
      position: relative;
      img{
        position: absolute;
        top: 14px;
        right: 11px;
        width: 7px;
        height: 13px;
      }
      .address_list_p1{
        font-size:10px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
        text-indent: 12px;
        background-color: #FFF;
        line-height: 20px;
        span{
          margin-left: 29px;
        }
      }
      .address_list_p2{
        font-size:8px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(151,151,151,1);
        text-indent: 12px;
      }
    }
  }
  .btn{
    width:150px;
    height:28px;
    background:linear-gradient(90deg,rgba(253,89,60,1),rgba(252,121,79,1));
    border-radius:14px;
    text-align: center;
    line-height: 28px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    color:rgba(255,255,255,1);
    margin: 145px 0 0 75px;
  }
}
</style>
