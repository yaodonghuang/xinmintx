<template>
  <div class="tk">
    <header>
      <img src="../../assets/images/ysh/ysh.img/arrow.png" alt="">
      <span>售后</span>
    </header>
    <div class="center">
      <span>退款</span>
      <span>售后</span>
    </div>
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
        </div>
        <div class="yc">
          <div class="ly">
            <span>退款理由：</span>
            <span>拍错了</span>
          </div>
          <div class="xz">
            <span>拒绝</span>
            <span>同意</span>
          </div>
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
        </div>
        <div class="yc">
          <div class="ly">
            <span>退款理由：</span>
            <span>拍错了</span>
          </div>
          <div class="xz">
            <span>拒绝</span>
            <span>同意</span>
          </div>
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
  .tk{
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
    .center{
      margin-left: 12px;
      margin-top: 8px;
      height: 18px;
      margin-bottom: 8px;
      // height: 20px;
      // border: 1px solid #000;
      span{
        width:46px;
        height:18px;
        background:rgba(255,255,255,1);
        border:0px solid rgba(238,238,238,1);
        border-radius:2px;
        margin-right: 12px;
        font-size:10px;
        font-family:PingFang SC;
        font-weight:500;
        color:rgba(0,0,0,1);
        display: block;
        text-align: center;
        line-height: 18px;
        float: left;
      }
      span:nth-child(2){
        background:rgba(254,89,59,1);
        color:rgba(255,255,255,1);
      }
    }
     .main{

      margin-top: 62px;
      width: 95%;
      margin: 0 auto;

      .product{
        margin-top: 12px;
        // border: 1px solid #f00;
        width: 98%;
        position: relative;
        background-color: #fff;
        padding: 8px 0px 9px 8px;
        margin-bottom: 8px;
        height: 110px;
        border-radius: 4px;
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
        }
        .yc{
          margin-top: 15px;
          font-size:10px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(102,102,102,1);
          .ly{
            float: left;
            margin-top:5px;
          }
        .xz{
          float: right;
          line-height: 18px;
          text-align: center;
          span{
            float: left;
            display: block;
            width: 46px;
            height: 18px;
            font-size:10px;
            font-family:PingFang SC;
            font-weight:500;
            color:rgba(0,0,0,1);
            border:1px solid rgba(204,204,204,1);
            border-radius:2px;
            margin-right: 12px;
          }
          span:nth-child(2){
            background:rgba(254,89,59,1);
            color:rgba(254,254,254,1);
          }
        }
        }
      }
    }
  }
</style>
