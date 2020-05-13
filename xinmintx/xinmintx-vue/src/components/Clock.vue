<template>
  <body>
  <div class="dk_bg_top">
    <img class="bgtop" :src="userList[2].pictureUrl">
    <img @click="$router.back(-1)" class="dk_b1" src="../images/img/back.png">
    <div class="dk_top_xx">
      <span class=" dk_x4">8</span></div>
    <div class="dk_top_time">
      <p class="dk_top_wenan_1">{{userList[0] | dateFormate}}月</p>
      <p class="dk_top_wenan_2">{{userList[0] | dateFormated}}日</p>
    </div>

  </div>
  <div><img class="dk_l2" src="../images/img/11111.png">
    <span class="dk_l3">惠商陪伴您的 第<span class="dk_m1">{{userday}}</span>天</span></div>

  <div class="dk_bg_wenan">
    <div class="dk_l4">
      <img class="kd_tx"  :src=" userList[1].avatarUrl "/>
      <span class="dk_l5">{{userList[1].name}}</span>
    </div>
    <div class="dk_l6">

      <p class="wenzi">{{userList[2].characters}}</p>

    </div>

    <div class="dk_x1">我也来录音>>></div>
  </div>

  <div>
    <p class="dk_aixin"><van-icon @click="dianzan" :name="tubiao" size="20px"  :color="color"/><span class="dk_aixin_s1">{{dianzanshu}}</span></p>
   <span class="dk_pl" @click="showPopup">评论</span>
  </div>
  <van-popup v-model="show" position="bottom" close-icon="close" >
    <van-cell-group>
      <van-field
        v-model="message"
        rows="1"
        label="评论"
        type="textarea"
        maxlength="50"
        placeholder="请输入留言"
        right-icon="upgrade"
        @click-right-icon="putText"
      />
    </van-cell-group>
  </van-popup>

  <div class="dk_x2" >
    <div class="dk_bg_xiaoxi" v-for = " (plitem,index) in pl" v-if= "index<=2"  :key="pl.index">
      <div class="dk_shuxin">
        <img class="dk_xiaoxi_tx" :src="plitem.avatarUrl">
        <span>
          <p class="dk_wenzi_1">{{plitem.commentName}}</p>
          <p class="dk_wenzi_2">{{plitem.createTime}}</p>
        </span>
      </div>
      <div class="dk_xiaoxi">
        <span class="dk_x3">{{plitem.comment}}</span>
      </div>
    </div>
    <div class="dk_m2">查看更多>>></div>
  </div>

  <div class="anniu">你好徽商&nbsp;&nbsp;一起打卡</div>
  <audio loop id="media" autoplay preload>
    <source :src="audio">
  </audio>
  </body></template>

<script>
export default {
  data () {
    return {
      sysCardId: '',
      token: '',
      // token: 'this.$route.params',
      userList: [],
      userday: ' ',
      pl: [ ],
      plitem: [ ],
      show: false,
      message: '',
      usertoken: '',
      dianzanshu: '',
      color: '',
      audio: '',
      dakaId: '',
      tubiao: 'like-o'
    }
  },
  methods: {

    dianzan(){
      this.$axios({
        url: 'http://114.55.103.26:8085/api/userLike/addUserLike',
        methods: 'post',
        params: {
          id: this.dakaId,
          memberToken: this.token
        },
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        if (res.data.code === 200) {
          this.dianzanshu = this.dianzanshu + 1
        }
        if (res.data.code === 500) {
          this.$notify({ type: 'danger', message: '已经点过赞了' })
          this.color = '#FD5E3F'
          this.tubiao = 'like'
        }
      })
    },
    showPopup () {
      this.show = true
    },
    putText () {
      this.$axios({
        url: 'http://114.55.103.26:8085/api/userComment/comment',
        methods: 'post',
        params: {
          id: this.dakaId,
          comment: this.message,
          token: this.usertoken
        },
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        if (res.data.code = 200) {
          this.$notify({ type: 'success', message: '评论成功' })
          this.show = false
        }
      }).catch(res => {
        this.$notify({ type: 'danger', message: '评论失败' })
      })
    }
  },
  mounted () {
    this.dakaId = this.$route.query.dakaId
    this.usertoken = localStorage.getItem('token')
    this.$axios({
      url: 'http://114.55.103.26:8085/api/memberCheckIn/getchs?id=' + this.dakaId,
      methods: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then(res => {
      this.userList = res.data.data
      this.audio = res.data.data[1].record_url
      console.log(res.data.data)
    })
    this.$axios({
      url: 'http://114.55.103.26:8085/api/memberCheckIn/countCard?id=' + this.dakaId,
      methods: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then(res => {
      this.userday = res.data
    })
    this.$axios({
      url: 'http://114.55.103.26:8085/api/userLike/countLike?id=' + this.dakaId,
      methods: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then(res => {
      this.dianzanshu = res.data.data[1]
      console.log(res.data.data[1])
      if (res.data.data[0] === true) {
        this.color = '#FD5E3F'
        this.tubiao = 'like'
      }
    })
    this.$axios({
      url: 'http://114.55.103.26:8085/api/userComment/getComment?id=' + this.dakaId,
      methods: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then(res => {
      this.pl = res.data
    })
  }
}
</script>

<style lang="less" scoped>
  .bgtop{
    position: absolute;
    width: 100%;
    height: 210px;
    z-index: -10000;
  }
  .dk_bg_top {
    height: 210px;
    /*background-image: url('../images/img/banneryexaczo2rld7hjp.png');*/
    /*background-repeat: no-repeat;*/
    /*background-size: 100% 100%;*/
    /*-moz-background-size: 100% 100%;*/
    position: sticky;
  }

  .dk_b1 {
    float: left;
    height: 14px;
    width: 14px;
    margin-top: 1rem;
    margin-left: 0.5rem;
    z-index: 10;
  }

  .dk_top_xx {
    height: 16px;
    width: 16px;
    background-image: url('../images/img/xiaoxi.png');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
    float: right;
    margin-top: 15px;
    margin-right: 12px;
  }

  .dk_x4 {
    position: absolute;
    top: 10px;
    right: 10px;
    font-size: 5px;
    color: #FFFFFF;
    background-color: red;
    border-radius: 10000px;
    line-height: 10px;
    width: 10px;
    text-align: center;
  }

  .dk_top_time {
    width: 50px;
    height: 50px;
    background-image: url('../images/img/bgtime.png');
    float: right;
    margin-right: 45px;
  }

  .dk_top_wenan_1 {
    text-align: center;
    font-size: 14px;
    color: #ffffff;
    margin-right: 10px;
  }

  .dk_top_wenan_2 {
    text-align: center;
    font-size: 14px;
    color: #ffffff;
  }

  .dk_music {
    position: absolute;
    right: 2rem;
    bottom: 2.5rem;

    img {
      width: 3.125rem;
      height: 3.125rem;
    }
  }

  .dk_l2 {
    vertical-align: middle;
    margin-left: 5%;
    height: 20px;
  }

  .dk_l3 {
    font-size: 13px;
    vertical-align: middle;
  }

  .dk_bg_wenan {
    background-image: url('../images/img/bgtext.png');
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
    width: 90%;
    margin: 10px auto;
    border-radius: 4px;
  }

  .dk_xiaoxi_tx {
    height: 32px;
    width: 32px;
    border-radius: 10000px;
  }

  .dk_l4 {
    display: flex;
  }

  .kd_tx {
    height: 30px;
    width: 30px;
    margin-top: 8px;
    margin-left: 8px;
    border-radius: 100rem;
  }

  .dk_l5 {
    font-size: 11px;
    display: flex;
    align-items: center;
    margin-top: 10px;
    margin-left: 5px;
    font-weight: bold;
  }

  .dk_l6 {
    width: 70%;
    margin: 0 auto;
  }

  .wenzi {
    font-size: 10px;
    /*text-align: center;*/
    line-height: 20px ;
    width: 80%;
    margin: 0 auto;
  }

  .dk_x1 {
    margin-left: 70%;
    line-height: 20px;
    font-size: 8px;
  }

  .dk_aixin {
    float: right;
    text-align: center;
    margin-right: 5%;
    font-size: 8px;
    /*margin-top: -1rem;*/
  }
  .dk_aixin_s1{
    float: right;
    padding-left: 5px;
  }
  .dk_m3 {
    height: 10px;
  }

  .dk_pl {
    color: #ffffff;
    background-color: red;
    text-align: center;
    float: right;
    margin-right: 20px;
    border-radius: 0.3125rem;
    width: 30px;
    font-size: 9px;
    line-height: 15px;
  }

  .dk_x2 {
    margin-top: 5rem;
    margin-left: 5%;
  }

  .dk_shuxin {
    display: flex;
  }

  .dk_wenzi_1 {
    display: flex;
    align-items: center;
    margin-top: 5px;
    margin-left: 10px;
    font-size: 10px;
    line-height: 20px;
  }

  .dk_wenzi_2 {
    display: flex;
    align-items: center;
    margin-left: 10px;
    font-size: 5px;
    color: darkgrey;
  }

  .dk_shuxin_aixin {
    margin-top: 1.5rem;
    margin-left: auto;
    text-align: center;

    img {
      height: 1.8125rem;
    }
  }

  .dk_x3 {
    margin-left: 42px;
    font-size: 10px;
  }

  .anniu {
    margin: 0 auto;
    width: 60%;
    background: -webkit-gradient(linear, 0 100%, 100% 0, from(#FD593C), to(#FC794F));
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 50px;
    margin-bottom: 1.8125rem;
    margin-top: 2rem;
    font-size: 12px;
    color: #FFFFFF;
    font-weight: bold;
  }

  .dk_bg_xiaoxi {
    margin-top: 10px;
  }

  .dk_m1 {
    color: red;
    font-size: 20px;
  }

  .dk_m2 {
    margin-top: 20px;
    font-size: 8px;
    margin-left: 70%;
    line-height: 15px;
  }

  .dk_welcome {
    margin: 0 auto;
    height: 5rem;
    background-image: url('../images/img/11111.png');
    background-repeat: no-repeat;
    width: 85%;
  }

  .b7 {
    margin-left: 11rem;
    font-size: 1rem;
  }

  .dk_img2 {
    height: 1.8125rem;
  }

  .dk_b8 {
    margin-left: 11rem;
    font-size: 1rem;
  }

  .dk_img3 {
    height: 1.8125rem;
  }

  .dk_b9 {
    margin-left: 11rem;
    font-size: 1rem;
  }

  .dk_b10 {
    margin-left: 80%;
    line-height: 3rem;
  }

  .dk_b13 {
    height: 1.8rem;
  }

</style>
