<template>
  <div class="myself">
    <audio autoplay="autoplay" loop preload ><source :src="music"></audio>
    <!-- <audio src="https://hstx.oss-cn-shanghai.aliyuncs.com/music/6f5b0760-1a0a-42e8-b086-44ebf87967b3_1574644751750.mp3" id="audio" aotuplay="autoplay"></audio> -->
     <!-- 头部 -->
    <div class="header">
     <!-- <img src="../images/img/myself.img/图层 1159.png" alt="" > -->
     <img :src="imgurl" alt="">
     <div class="logo">
       <img :src="tx" alt="">
       <span class="hs">{{user.name}}</span>
       <span class="vip">VIP{{user.memberType}}</span>
       <p class="num">ID:{{user.id}}</p>
     </div>
     <div class="des">
       <!-- <p>生活的滋味、甘苦相依，人生的道路、</p>
       <p>五味杂呈；走过崎岖，才知平坦；</p>
       <p>经历风雨，方见彩虹。</p> -->
       <p class="van-multi-ellipsis--l2">{{des}}</p>
     </div>

     <div class="dk" @click="dk">
       <img src="../images/img/myself.img/立体箭头.png" alt="">
       <p>立即打卡</p>
     </div>
    </div>
  <!-- 红利部分 -->
    <div class="info">
      <div class="info_item ">
        <p>GXM通证</p>
        <p>2500</p>
      </div>
      <div class="info_item">
        <p>红利</p>
        <p>20</p>
      </div>
      <div class="info_item ye">
        <p>新民币</p>
        <p class="zh">账  户</p>
      </div>
      <div @click="godata" class="info_item">
        <p>DATA</p>
        <p>{{datanum}}</p>
      </div>
      <div @click="godata" class="info_item">
        <p>红利</p>
        <p>118</p>
      </div>
    </div>
  <!-- messages -->
    <div class="mg">
      {{dianzan}}观看了您的打卡，并为您点赞哦~
    </div>
    <!-- main -->
    <div class="main">
     <div class="nav">
      <div class="nav_item">
        <img src="../images/img/myself.img/消息 (1).png" alt="">
        <p>信息</p>
      </div>
      <div class="nav_item">
        <img src="../images/img/myself.img/资产维护管理.png" alt="">
        <p>我的资产</p>
      </div>
      <div class="nav_item">
        <img src="../images/img/myself.img/礼包.png" alt="">
        <p>我的礼包</p>
      </div>
      <div class="nav_item">
        <img src="../images/img/myself.img/收藏.png" alt="">
        <p>我的收藏</p>
      </div>
     </div>
     <div class="nav">
      <div class="nav_item">
        <img src="../images/img/myself.img/订单.png" alt="">
        <p>订单</p>
      </div>
      <div class="nav_item">
        <router-link :to="{name:'hyk'}">
          <img src="../images/img/myself.img/轻会员.png" alt="">
          <p>会员卡服务</p>
        </router-link>
      </div>
      <div class="nav_item">
        <router-link :to="{name:'Set'}">
          <img src="../images/img/myself.img/yixianshi-01.png" alt="">
          <p>设置</p>
        </router-link>
      </div>
     </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      imgurl: ' ',
      des: '',
      music: '',
      id: '',
      token: '',
      user: [],
      dianzan: '',
      dakaId: '',
      datanum: ''
    }
  },
  created () {
    this.token = localStorage.getItem('token')
    this.$axios.get('http://114.55.103.26:8085/api/memberCheckIn/getch').then(res => {
      this.imgurl = res.data[0].pictureUrl
      this.des = res.data[0].characters
      this.music = res.data[0].record_url
      this.id = res.data[0].id
      console.log(res)
      this.$axios({
        url: 'http://114.55.103.26:8085/api/userLike/lookLike?sysCardId=' + this.id,
        methods: 'post',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        this.dianzan = res.data.msg
      })
    })
    this.$axios.post('http://114.55.103.26:8085/member/login/getMember').then(res => {
      this.user = res.data.data
      this.tx = res.data.data.avatarUrl
    })
    this.$axios({
      url: 'http://114.55.103.26:8085/api/member/accountNum',
      methods: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then(res => {
      this.datanum = res.data.data
    })
  },
  methods: {
    godata () {
      this.$router.push({ path: '/index_data' })
    },
    dk () {
      this.$axios.get('http://114.55.103.26:8085/api/memberCheckIn/userPunch?sysCardId=' + this.id).then(res => {
        // this.$router.push('/clock')
        console.log(res.data)
        this.dakaId = res.data.data
        this.$router.push({ path: '/clock', query: { dakaId: this.dakaId } })
      })
    }
  }

}
</script>
<style  scoped>
.header {
  height: 156px;
  position: relative;
}
.header img {
  width: 100%;
  height: 156px;
}
.header .logo {
  position: absolute;
  top:27px;
  left: 13px;
}
.header .logo img{
  width: 40px;
  height: 40px;
  display: block;
  border-radius: 50%
}
.header .logo .hs {
  font-style: DroidSansFallback;
  font-size: 9px;
  padding-left: 4px;
  padding-top: 20px;
}
.header .logo .vip {
  background-color: #F4E12C;
  height: 10px;
  border-radius: 50% 0px 0px 50%;
  width: 22px;
  font-size:4px;
  text-align: center;
  display: block;
  position: absolute;
  left:42px;
  top:47px;

}
.header .logo .num {
  font-size: 7px;
  margin-left: 4px;
  font-weight:500;
  color:#000;
}

.header .des {
  width: 35%;
  position: absolute;
  top: 41px;
  right: 12px;
  }
  .header .des p{
   font-size: 8px;
   line-height: 10px;
   font-family:PingFang SC;
   font-weight:500;
  }
.header .dk {
  position: absolute;
  bottom: 6px;
  right: 10px;
}
.header .dk img {
  width: 12px;
  height: 14px;
  padding-left: 0.53125rem /* 17/32 */;
}
.header .dk p {
  font-size: 0.53125rem /* 17/32 */;
  color: #030301;
  font-family: FZHZGBJW;

}
.info {
  margin-top: 10px;
  display: flex;
  width: 100%;
}
.info .info_item {
  width: 20%;
}
.info .info_item p {
  text-align: center;
  font-size:8px ;
  font-family: DroidSansFallback;
}
.info .ye {
  border-left: 1px solid #ccc;
  border-right: 1px solid #ccc;
  padding-left: 0.46875rem /* 15/32 */;
  padding-right: 0.46875rem /* 15/32 */;
}
.mg {
  margin-top: 0.6875rem /* 22/32 */;
  margin-left: 0.96875rem /* 31/32 */;
  margin-right: 0.9375rem /* 30/32 */;
  height: 1.8125rem /* 58/32 */;
  background-color: #fff;
  box-shadow: 0rem /* 0/32 */ 0.15625rem /* 5/32 */ 0.15625rem /* 5/32 */ 0rem /* 0/32 */ rgba(65, 65, 65, 0.3);
  border-radius: 0.3125rem /* 10/32 */;
  padding-left: 2.59375rem /* 83/32 */;
  padding-top: 0.53125rem /* 17/32 */;
  font-size: 0.6875rem /* 22/32 */;
  font-family: DroidSansFallback;
  box-sizing: border-box;
}
.main .nav {
  margin-top: 24px;
  display: flex;
  width: 100%;
  text-align: center;
}
.main .nav .nav_item {
  width: 25%;
}
.main .nav .nav_item:nth-child(1) img {
  width: 16px;
  height:18px;
  margin-bottom: 14px;
}
.main .nav .nav_item:nth-child(2) img {
  width:21px;
  height:18px;
  margin-bottom: 15px;
 }
.main .nav .nav_item:nth-child(3) img {
  width: 16px;
  height:18px;
  margin-bottom: 14px;
}
.main .nav .nav_item:nth-child(4) img {
  width: 20px;
  height:18px;
  margin-bottom: 15px;
}
.main .nav .nav_item p {
  font-size: 10px;
}
.main .nav:nth-child(2) .nav_item:nth-child(3) img{
   width: 21px;
   height:18px;
}
</style>
