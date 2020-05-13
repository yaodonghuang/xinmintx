<template>
  <div class="cart">
    <header>
      <p>购物车</p>
      <span>编辑</span>
    </header>
    <div class="center">
      <!-- <van-checkbox v-model="checked">复选框</van-checkbox> -->
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style lang="less" scoped>
.cart{
  width: 100%;
  background-color: #f5f5f5;
  header{
    background-color: #fff;
    position: fixed;
    top:0px;
    width: 100%;
    height:51px;
    font-family:PingFang SC;
    font-weight:bold;
    padding-bottom: 10px;
    box-sizing: border-box;

    p{
      font-size:14px;
      text-align: center;
      padding-top: 27px;
    }
    span{
      float: right;
      margin-right: 20px;
      margin-top: -12px;
      font-size: 11px;
    }
  }
  .center{
    margin-top: 63px;
    margin: 0 auto;
    border: 1px solid #000;
    border-top: 1px solid #000;
    width: 92%;
    height: 400px;
    background-color: #fff;
  }
}
</style>
