<template>
    <div class="Newaddress">
      <div class="header">
        <img src="../../images/img/cart.img/back.png" alt="" @click="back()">
        编辑地址
        <span @click="getVal()">保存</span>
      </div>
      <ul class="from_list">
        <li>
          <label for="name">姓名
            <input type="text" id="name" >
          </label>
        </li>
        <li>
          <label for="cellphone">电话
            <input type="number" id="cellphone">
          </label>
        </li>
        <li class="my-container">
          <label for="region">地区</label>
          <van-cell @click="showPopup">
            <input type="text" id="region" name="">
          </van-cell>
          <van-popup position="bottom" :get-container="getContainer" v-model="show">
            <van-area @confirm.self='confirmFn' @cancel="cancel" :area-list="areaList" :columns-num="3"/>
          </van-popup>
        </li>
        <li>
          <label for="address">详细地址
            <input type="text" id="address">
          </label>
        </li>
        <li>
          <label>
            设为默认地址
          </label>
          <van-switch v-model="checked" size="18px" />
        </li>
      </ul>
      <div class="remove" @click="del()">删除收货地址</div>
    </div>
</template>

<script>
import areaList from '../../js/area.js'
import $ from 'jquery'
import router from '../../router'
import qs from 'qs'
export default {
  name: 'Newaddress',
  data () {
    return {
      show: false,
      areaList: areaList,
      checked: '',
      revise: {
        id: ''
        // token: '9cc56b81-858e-4ebd-b3f6-7a309fedf347'
      },
      list: {
        id: '',
        checked: '',
        regionCode: '',
        name: '',
        cellphone: '',
        region: '',
        address: ''
        // token: '9cc56b81-858e-4ebd-b3f6-7a309fedf347'
      }
    }
  },
  methods: {
    remove: function () {
      alert(111)
    },
    back: function () {
      this.$router.go(-1)
    },
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
      $('#region').val(arr1)
      this.show = false
    },
    cancel () {
      this.show = false
    },
    del () { // 删除地址
      let data = qs.stringify(this.revise)
      this.$axios({
        url: 'http://114.55.103.26:8085/api/shippingAddress/deleteAddress',
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: data
      }).then((res) => {
        if (res.data.code === 200) {
          this.list.checked = ''
          this.list.regionCode = ''
          this.list.name = ''
          this.list.cellphone = ''
          this.list.region = ''
          this.list.address = ''
          // console.log(res)
          this.$router.go(-1)
        }
      }).catch((err) => {
        console.log(err)
      })
    },
    getVal () {
      let myreg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/
      this.list.name = $('#name').val()
      this.list.cellphone = $('#cellphone').val()
      this.list.region = $('#region').val()
      this.list.address = $('#address').val()
      if (this.list.name === undefined || this.list.name === null || this.list.name === '' || this.list.name.length < 1) {
        alert('姓名不能为空')
        return
      }
      if (!myreg.test(this.list.cellphone)) {
        alert('手机号有误')
        return
      }
      if (this.list.region === undefined || this.list.region === null || this.list.region === '' || this.list.region.length < 1) {
        alert('地区不能为空')
        return
      }
      if (this.list.address === undefined || this.list.address === null || this.list.address === '' || this.list.address.length < 1) {
        alert('详细地址不能为空')
        return
      }
      this.list.checked = this.checked
      let data = qs.stringify(this.list)
      console.log(data)
      // 修改地址
      if (this.revise.id) {
        this.$axios({
          url: 'http://114.55.103.26:8085/api/shippingAddress/updateAddress',
          method: 'post',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data: data
        }).then((res) => {
          if (res.data.code === 200) {
            console.log(res)
            this.$router.go(-1)
          }
        }).catch((err) => {
          console.log(err)
        })
      } else {
        this.$axios({ // 添加地址
          url: 'http://114.55.103.26:8085/api/shippingAddress/addmemberAddress',
          method: 'post',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data: data
        }).then((res) => {
          if (res.data.code === 200) {
            // console.log(res)
            this.$router.go(-1)
          }
        }).catch((err) => {
          console.log(err)
          router.push('/Login')
        })
      }
    }
  },
  mounted () { // 根据id渲染地址，后修改或删除
    this.revise.id = this.$route.query.id
    this.list.id = this.$route.query.id
    if (!this.revise.id) {
      return
    }
    let data = qs.stringify(this.revise)
    console.log(data)
    this.$axios({
      url: 'http://114.55.103.26:8085/api/shippingAddress/editAddress',
      method: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data: data
    }).then((res) => {
      if (res.data.code === 200) {
        console.log(res.data.data)
        $('#name').val(res.data.data.name)
        $('#cellphone').val(res.data.data.cellphone)
        $('#region').val(res.data.data.region)
        $('#address').val(res.data.data.address)
      }
    }).catch((err) => {
      console.log(err)
    })
  }
}
</script>

<style lang="less" scoped>
.Newaddress {
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
  .from_list{
    width:284px;
    border-radius:4px;
    margin: 10px 0 0 8px;
    li{
      width: 100%;
      height: 33px;
      border-bottom: 1px solid #f0f0f0;
      line-height: 30px;
      text-indent: 12px;
      background:rgba(255,255,255,1);
      position: relative;
      input{
        margin-left: 45px;
      }
      label{
        font-size:12px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(51,51,51,1);
      }
    }
    li:nth-of-type(4){
      border: 0;
    }
    li:nth-of-type(4) input{
      margin-left: 20px;
    }
    li:nth-of-type(5){
      margin-top: 8px;
      border: 0;
      border-radius:4px;
      display: flex;
      .van-switch {
        margin-left: 157px;
        margin-top: 10px;
      }
    }
  }
  .remove{
    width:188px;
    height:30px;
    background:linear-gradient(90deg,rgba(253, 89, 60, 1),rgba(252, 121, 79, 1));
    border-radius:15px;
    font-size:12px;
    font-family:PingFang SC;
    font-weight:500;
    color:rgba(255,255,255,1);
    line-height: 30px;
    text-align: center;
    margin: 50px 0 0 56px;
  }
}
.van-cell{
  height: 33px;
  border:0;
  outline: none;
  border: 0;
  position: absolute;
  left: 40px;
  top: 0px;
  width: 220px;
  padding: 0;
  input{
    margin-top: 16px;
  }
}
.van-cell__value{
  height: 33px;
  position: relative;
  top: -8px;
  border:0;
  outline: none;
  display: flex;
}
.van-cell__value--alone{
  border:0;
  outline: none;
  display: flex;
}
  input{
    outline: none;
    border: 0;
  }
</style>
