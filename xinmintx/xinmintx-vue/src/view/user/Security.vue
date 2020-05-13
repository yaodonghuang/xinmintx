<template>
  <div>
    <div class="bg_color">
      <div class="gift_imgtop"><img v-on:click="back" src="../../images/img/gift/black.png"/></div>
      <div class="gift_topwen"><span>安全设置</span></div>
    </div>
    <div class="tiao1">
      <van-cell title="登录密码" is-link/>
    </div>
    <div class="tiao1">
      <van-cell title="交易密码" is-link/>
    </div>
    <div class="tiao1">
      <van-cell title="实名认证" is-link/>
    </div>
    <div class="tiao1">
      <van-cell title="银行卡绑定" is-link/>
    </div>

    <div class="bown">
      <div class="qd">确定</div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    back() {
      this.$router.go(-1) //返回上一层
    }
  }
}
</script>

<style lang="less" scoped>
  .tiao1 {
    width: 90%;
    margin: 10px auto;
    border-radius: 20px;
  }

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

  .bown {
    display: flex;
    justify-content: center;
  }

  .qd {
    width: 30%;
    border-radius: 20px;
    background: linear-gradient(90deg, rgba(#FC794F), rgba(#FD593C));
    margin-top: 50px;
    line-height: 25px;
    text-align: center;
  }
</style>
