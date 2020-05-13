<template>
  <div class="csdl">
    <header>
      <img src="../../assets/images/index/csdl.img/four.png" alt="">
      <div class="round"></div>
      <img src="../../assets/images/index/csdl.img/two.png" alt="">
    </header>
    <form action="">
      <label for="tel"><img src="../../assets/images/index/csdl.img/mobile.png" alt="">
        <input type="tel" v-model="menu.tel" name="phone" id="tel" placeholder="输入手机号">
        <input type="button" class="btn"  @click="send(),sendEmail()" value="获取验证码"/></label><br><br>
      <label for="pw"><img src="../../assets/images/index/csdl.img/key.png" alt="">
        <input class="ininin" type="text" v-model="menu.pw" name="code" id="pw" placeholder="输入验证码"></label>
      <div class="dl" @click="sub()" >
        <p>登录</p>
      </div>
    </form>
  </div>
</template>

<script>
  import router from '../../router'
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
        var tel = JSON.stringify(this.menu)
        if (!rule.test(this.menu.tel)) {
          alert('你输入的号码有误')
        } else {
          this.$axios({
            url: 'http://192.168.1.103:8086/vender/getCode/' + this.menu.tel,
            method: 'post',
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: tel
          }).then((res) => {
            // console.log(res)
            // console.log(tel)
            if (res === '500' || res === '400') {
              alert('账号不存在')
            }
          }).catch((err) => {
            // console.log(err)
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
        let data =JSON.stringify({'phone': this.menu.tel ,'code': this.menu.pw })
        // let that = this
        if (this.menu.pw === undefined || this.menu.pw === null || this.menu.pw === '') {
          alert('验证码不能为空')
          return
        }
        if (this.menu.tel === null || this.menu.tel === undefined || this.menu.tel === '' || this.menu.tel.length < 1) {
          alert('你输入的号码有误')
        } else {
          console.log(data)
          this.$axios({
            url: 'http://192.168.1.103:8086/vender/login?phone=' + this.menu.tel + '&code=' + this.menu.pw,
            method: 'post',
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            // data: data
          }).then((res) => {
            // console.log(res)
            // console.log(res.data.data.token)
            if (res.data.code === 200) {
              let token = res.data.data.token
              this.access.token = token
              localStorage.setItem('token', token)
              console.log(res)
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
          url: 'http://192.168.1.103:8086/vender/getToken',
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
        let data = JSON.stringify({ 'token': tokens })
        console.log(data)
        this.$axios({
          url: 'http://192.168.1.103:8086/vender/getToken',
          method: 'post',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data: data
        }).then((res) => {
          console.log(res)
          if (res.data.code === 200) {
            router.push('/Index')
          }
        }).catch((err) => {
          console.log(err)
        })
      }
    }
  }
</script>

<style lang="less" scoped>
.csdl{
  width: 100%;
  height: 100%;
  position: absolute;
  background-color: #fff;
  header{
    position: relative;
    img:nth-child(1){
     height: 486px;
     width: 100%;
    }
    .round{
      position: absolute;
      top:79px;
      left:50%;
      transform: translateX(-50%);
      width:174px;
      height:174px;
      background:rgba(255,255,255,1);
      border-radius:50%;
    }
    img:nth-child(3) {
      width: 141px;
      height: 126px;
      position: absolute;
      top:100px;
      left:50%;
      transform: translateX(-50%);
    }
  }
  form{
   width: 80%;
   margin: 0 auto;
   margin-top:72px;
   /*border: 1px solid #000;*/
   position: relative;
   label{
     border-bottom: 1px solid #eee;
     padding-bottom: 11px;
     margin-bottom: 25px;

     img{
       width: 27px;
       height: 43px;
       margin-right:14px;
     }
     input{
       border:none;
       font-size:32px;
       font-family:SourceHanSansSC;
       font-weight:500;
       color: #d2d2d2;
       margin-right: 5%;
     }
     .btn{
       position: absolute;
       right:19px;
       font-family:Source Han Sans CN;
       border: none;
       height:50px;
       background:rgba(125,197,235,1);
       border-radius:10px;
       font-size:8px;
       font-weight:500;
       color:#ffffff;
     }
   }
   label:nth-child(2){
    position: relative;
    top:40px;

     ininin{
     margin-right:40%;
    }
   }
  .dl{
   position: absolute;
    border:none;
    top: 250px;
    left:50%;
    transform: translateX(-50%);
     width:60%;
     height:90px;
     background:linear-gradient(0deg,rgba(125,197,235,1),rgba(125,197,235,1));
    box-shadow: 0px 4px 14px 0px rgba(125, 197, 235, 0.2);
    border-radius: 100px;
    font-size: 14px;
    font-family: PingFang SC;
    font-weight: bold;
    color: rgba(255, 255, 255, 1);
    text-align: center;
    line-height: 90px;
  }
  }
}

</style>
