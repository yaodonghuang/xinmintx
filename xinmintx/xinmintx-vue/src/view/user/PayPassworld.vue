<template>
  <div class="bg_color">
    <div class="gift_imgtop"><img src="../../images/img/gift/black.png" /></div>
    <div class="gift_topwen"><span>修改交易密码</span></div>
    <div class="colorbg">
      <van-field
        readonly
        clickable
        :value="value"
        placeholder="请输入当前交易密码"
        @touchstart.native.stop="show = true"
      />

      <van-number-keyboard
        v-model="value"
        :show="show"
        :maxlength="6"
        @blur="show = false"
      />
    </div>
    <div class="colorbg">
      <van-field
        readonly
        clickable
        :value="passworld"
        placeholder="请输入新的支付密码"
        @touchstart.native.stop="payshow = true"
      />

      <van-number-keyboard
        v-model="passworld"
        :show="payshow"
        :maxlength="6"
        @blur="payshow = false"
      />
    </div>
    <div class="bown">
      <div class="qd">确定</div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      show: false,
      value: '',
      payshow: false,
      passworld: ''
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
  .bown{
    display: flex;
    justify-content: center;
  }
  .qd{
    width: 30%;
    border-radius: 20px;
    background:linear-gradient(90deg,rgba(#FC794F),rgba(#FD593C));
    margin-top: 50px;
    line-height: 25px;
    text-align: center;
  }
</style>
