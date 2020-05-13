<template>
  <div class="wlyc">
    <header>
      <img src="../../assets/images/ysh/ysh.img/arrow.png" alt="">
      <span>物流异常</span>
    </header>
    <div class="main">
      <div class="product">
        <img src="../../assets/images/ysh/ysh.img/product.png" alt="">
        <div class="dec">
          <span>姓名：汤某人</span>
          <span>电话：13115996542</span>
          <p>地址：浙江省嘉兴市嘉善县上海人才创业园</p>
          <p>泰国乳胶枕</p>
          <span>颜色：白色</span>
          <span>尺码：M</span><br>
          <span>￥35.00</span>
          <span><s>￥50.00</s></span>
          <span>x1</span>
          <span>物流异常</span>
        </div>
        <div class="yc">
          <p>包裹异常:</p>
          <p>包裹在运输途中遇到异常情况，我们会尽快解决，敬请谅解</p>
        </div>
      </div>
       <div class="product">
        <img src="../../assets/images/ysh/ysh.img/product.png" alt="">
        <div class="dec">
          <span>姓名：汤某人</span>
          <span>电话：13115996542</span>
          <p>地址：浙江省嘉兴市嘉善县上海人才创业园</p>
          <p>泰国乳胶枕</p>
          <span>颜色：白色</span>
          <span>尺码：M</span><br>
          <span>￥35.00</span>
          <span><s>￥50.00</s></span>
          <span>x1</span>
          <span>物流异常</span>
        </div>
        <div class="yc">
          <p>包裹异常:</p>
          <p>包裹在运输途中遇到异常情况，我们会尽快解决，敬请谅解</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style lang="less" scoped>
  .wlyc{
    width: 100%;
    height: 100%;
    header{
      width: 100%;
      height: 37px;
      background-color: #fff;
      padding-top: 14px;
      position: relative;
      img{
        width: 8px;
        height: 14px;
        margin-left: 12px;
      }
      span{
        position: absolute;
        top:50%;
        left:50%;
        transform: translate(-50%,-50%);
        font-size:14px;
        font-family:PingFang SC;
        font-weight:bold;
        color:rgba(0,0,0,1);
        margin: 0 auto;
      }
    }
    .main{
      // border: 1px solid #000;
      margin-top: 62px;
      width: 95%;
      margin: 0 auto;
      height: 60px;
      .product{
        margin-top: 12px;
        // border: 1px solid #f00;
        width: 98%;
        position: relative;
        background-color: #fff;
        padding: 8px 0px 9px 8px;
        margin-bottom: 8px;
        img{
          width: 72px;
          height: 72px;

        }
        .dec{
          position: absolute;
          width:72%;
          // background-color: #ff0;
          top:8px;
          left:91px;
          font-size:9px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(77,77,77,1);
          span:nth-child(1){
            margin-right:16px;
          }
          p:nth-child(4){
           color:rgba(174,76,76,1);
            margin-top: 7px;
          }
          span:nth-child(5){
            color:rgba(125,125,125,1);
            margin-right:7px;
          }
           span:nth-child(6){
            color:rgba(125,125,125,1);
          }
          span:nth-child(8){
            color:rgba(76,76,76,1);
            font-size:11px;
            margin-right: 7px;
          }
          span:nth-child(9){
            color:rgba(148,148,148,1);
            margin-right: 7px;
          }
           span:nth-child(10){
            color:rgba(148,148,148,1);

          }
          span:nth-child(11){
            float: right;
            margin-right:20px;
            font-weight:bold;
            color:rgba(247,26,26,1);
          }
        }
        .yc{
          margin-top: 13px;
          font-size:7px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(0,0,0,1);
          p:last-child{
            margin-left:22px;
            color:rgba(51,51,51,1);
          }
        }
      }
    }
  }
</style>
