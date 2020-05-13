<template>
  <div class="login">
    <div class="main">
      <img src="../images/img/login.img/four.png" alt="">
      <img src="../images/img/login.img/seven.png" alt="">
      <img src="../images/img/login.img/one.png" alt="">
      <form action="" id="login_form">
        <label for="tel">账      号：
          <input type="tel" v-model="menu.tel" name="phone" id="tel"  placeholder="请输入您的手机号"></label><br>
        <label for="pw">验证码：
          <input type="password" v-model="menu.pw" name="code" id="pw"  placeholder="请输入您的验证码">
        </label>
        <input type="button" class="btn" value="获取验证码" @click="send(),sendEmail()"/>
        <div class="lg" @click="sub()" >
          <img src="../images/img/login.img/five.png" alt="">
          <p>登录</p>
        </div>
      </form>
    </div>
    <div class="footer">
      <input type="checkbox" checked="checked">
      <span>登录即代表同意</span>
      <a>《惠商科技条约》</a>
    </div>
  </div>
</template>

<script>
import router from '../router'
import $ from 'jquery'
import qs from 'qs'
export default {
  data () {
    return {
      menu: {
        tel: '',
        pw: ''
      },
      time: 60,
      access: {
        token: '',
        code: ''
      }
    }
  },
  methods: {
    send: function () {
      var rule = /^[1][3,4,5,6,7,8,9][0-9]{9}$/
      var tel = qs.stringify(this.menu)
      if (!rule.test(this.menu.tel)) {
        alert('你输入的号码有误')
      } else {
        this.$axios({
          url: 'http://114.55.103.26:8085/member/login/getCode/' + this.menu.tel,
          method: 'post',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data: tel
        }).then((res) => {
          console.log(res)
          console.log(tel)
          if (res === '500' || res === '400') {
            alert('账号不存在')
          }
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    sendEmail: function () {
      let rule = /^[1][3,4,5,6,7,8,9][0-9]{9}$/
      let that = this
      if (!rule.test(this.menu.tel)) {
      } else if (this.time === 0) {
        $('.btn').attr('disabled', false)
        $('.btn').val('获取验证码')
        this.time = 60
        return false
      } else {
        $('.btn').attr('disabled', true)
        $('.btn').val('正在发送 ' + this.time)
        this.time--
      }
      setTimeout(function () {
        that.sendEmail()
      }, 1000)
    }, // // router.push('index')
    sub: function () {
      let data = qs.stringify(this.menu)
      console.log(data)
      // let that = this
      if (this.menu.pw === undefined || this.menu.pw === null || this.menu.pw === '') {
        alert('验证码不能为空')
        return
      }
      if (this.menu.tel === null || this.menu.tel === undefined || this.menu.tel === '' || this.menu.tel.length < 1) {
        alert('你输入的号码有误')
      } else {
        this.$axios({
          url: 'http://114.55.103.26:8085/member/login/login/',
          method: 'post',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data: data
        }).then((res) => {
          // console.log(res)
          // console.log(res.data.data.token)
          if (res.data.code === 200) {
            let token = res.data.data.token
            this.access.token = token
            localStorage.setItem('token', token)
            this.weLogin()
          }
          if (res.data.code === 404 || res.data.code === 500) {
            alert('验证码错误')
          }
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    weLogin () { // 获取code
      let urlNow = encodeURIComponent(window.location.href)
      let scope = 'snsapi_userinfo'
      let appid = 'wxfae6eed7965225be'
      let url = `https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${urlNow}&response_type=code&scope=${scope}&state=STATE#wechat_redirect`
      window.location.replace(url)
    },
    getUrlKey (name) { // 获取url参数
      return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ''])[1].replace(/\ + /g, ' %20 ')) || null
    }
  },
  created () {
    let code = this.getUrlKey('code')
    let tokens = localStorage.getItem('token')
    this.access.code = code
    if (code) {
      this.access.token = tokens
      let data = qs.stringify(this.access)
      // alert(data)
      this.$axios({
        url: 'http://114.55.103.26:8085/member/login/getOpenid',
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: data
      }).then((res) => {
        console.log(res)
        if (res.data.code === 200) {
          router.push('/index')
        }
        // router.push('index')
      }).catch((err) => {
        console.log(err)
      })
    } else if (tokens) {
      let data = qs.stringify({ 'token': tokens })
      console.log(data)
      this.$axios({
        url: 'http://114.55.103.26:8085/member/login/getMember',
        method: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: data
      }).then((res) => {
        console.log(res)
        if (res.data.code === 200) {
          router.push('/index')
        }
      }).catch((err) => {
        console.log(err)
      })
      // })
    }
  }
}
</script>

<style lang="less" scoped>
  .login{
    .main{
      position: relative;
      img{
        width: 100%;
        height: 173px;
        &:nth-child(2){
          position: absolute;
          left: 50%;
          top:93px ;
          transform: translateX(-50%);
          width: 102px;
          height: 102px;
        }
        &:nth-child(3) {
          position: absolute;
          left: 50%;
          top:119px;
          transform: translateX(-50%);
          width:56px;
          height: 50px;
        }
      }
      form{
        margin-top: 37px;
        position: relative;
        label {
           margin-left: 30px;
          font-size: 12px;
          font-family:Source Han Sans CN;
          font-weight:400;
          color:rgba(0,0,34,1);

          position: relative;

          &:nth-child(1) input{
            margin-left: 12px;

          }
          input {
            font-size:11px;
            /* 28/32 */
          ;
           width:65% ;
           margin-left: 4px;
            font-family: Source Han Sans CN;
            font-weight: 400;
            color: rgba(179, 179, 179, 1);
            border: none;
            padding-bottom:8px;
            border-bottom:1px solid #f0f0f0;
            margin-bottom: 34px;
          }
        }
        .btn{
          position: absolute;
          top:68px;
          right:19px;
          font-size:9px;
          font-family:Source Han Sans CN;
          font-weight:400;
          color:rgba(28,83,220,1);
           border: none;
           background-color: #fff;
        }
        .lg{
          text-align: center;
          width:88%;
          height:36px;
          margin: 0 auto;
          position: relative;
          // top: 1.33rem;
          img{
            width: 100%;
            height: 100%;
          }
          p{
            font-size: 14px;
            color:#fff;
            position: absolute;
            top: 50%;
            left: 50%;
            transform:translate(-50%,-50%)

          }
        }
      }
    }
    .footer{
      font-size:8px;
      font-family:Source Han Sans CN;
      font-weight:400;
      position: fixed;
      bottom:30px;
      left:50%;
      transform: translateX(-50%);
      input{
        position: relative;
        top:.25rem /* 8/32 */;
        margin-right: .3125rem /* 10/32 */;

      }
      span {
        color:#999;
      }
      a{
        color:#FF1D5C;
      }
    }
  }
</style>
