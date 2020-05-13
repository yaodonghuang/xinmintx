<template>
  <div class="bgcolore">
    <div class="bg_color">
      <div class="gift_imgtop" @click="back()"><img src="../../images/img/gift/black.png"/></div>
      <div class="gift_topwen"><span>设置</span></div>
    </div>
    <div class="tiao">
      <van-cell title="个人信息设置 " is-link/>
    </div>
    <div class="tiao">
      <van-cell title="收货地址" is-link to="address"/>
    </div>

    <div class="tiao">
      <van-cell title="安全设置" is-link/>
    </div>

    <div class="tiao">
      <van-cell title="清除缓存" value="88.88MB"/>
    </div>

    <div class="tiao">
      <van-cell title="关于我们" is-link/>
    </div>

  </div>
</template>

<script>
export default {
  name: 'Set',
  methods: {
    back () {
      this.$router.go(-1)
    }
  }
}
</script>

<style lang="less" scoped>
  .bgcolore {
    background-color: #f5f5f5;
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

  .user_tiao1 {
    width: 90%;
    height: 50px;
    margin: 5px auto;

    .user_txzuo {
      float: left;
      font-size: 12px;
      margin-left: 5%;
      line-height: 50px;
    }

    img {
      float: right;
      line-height: 6rem;
      margin-right: 5%;
      margin-top: 6px;
      width: 38px;
      height: 38px;
      border-radius: 10000px;
    }
  }

  .user_tiao2 {
    height: 38px;
    width: 90%;
    margin: 5px auto;
  }

  .user_zuo {
    font-size: 12px;
    float: left;
    margin-left: 5%;
    line-height: 38px;
  }

  .user_you {
    font-size: 12px;
    float: right;
    margin-right: 5%;
    line-height: 38px;
    color: #808080;
  }

  .hei {
    color: #0A0204;
  }
  .tiao{
    width: 90%;
    margin: 10px auto;
  }
</style>
