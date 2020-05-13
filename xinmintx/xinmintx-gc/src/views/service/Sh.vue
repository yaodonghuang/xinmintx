<template>
  <div class="sh">
    <header>
      <img src="../../assets/images/ysh/ysh.img/arrow.png" alt="">
      <span>售后</span>
    </header>
    <div class="center">
      <span>退款</span>
      <span>售后</span>
    </div>
    <div class="main">
      <div class="main_item">
        <div class="tx"><img src="" alt=""></div>
        <div class="des">
          <span>豆豆豆</span>
          <span>11:11</span><br>
          <span>我东西牌错了 麻烦退款 麻我退款 .......</span>
          <div class="info">3</div>
        </div>
      </div>
      <div class="main_item">
        <div class="tx"><img src="" alt=""></div>
        <div class="des">
          <span>豆豆豆</span>
          <span>11:11</span><br>
          <span>我东西牌错了 麻烦退款 麻我退款 .......</span>
          <div class="info">3</div>
        </div>
      </div>
      <div class="main_item">
        <div class="tx"><img src="" alt=""></div>
        <div class="des">
          <span>豆豆豆</span>
          <span>11:11</span><br>
          <span>我东西牌错了 麻烦退款 麻我退款 .......</span>
          <div class="info">3</div>
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
  .sh{
    width: 100%;
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
      background-color: #fff;
      .main_item{
         padding: 8px 0px 8px 16px;
         overflow: hidden;
         border-bottom: 1px solid #eee;
        .tx{
        float: left;
        width:32px;
        height:32px;
        background:rgba(204,204,204,1);
        border-radius:50%;
        img{
          width: 100%;
          height: 100%;
        }
      }
      .des{
        width: 80%;
        float: left;
        margin-left:12px;
        span:nth-child(1){
          font-size:10px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(0,0,0,1);
        }
        span:nth-child(2){
          float: right;
          font-size:7px;
          color:rgba(128,128,128,1);
        }
        span:nth-child(4){
          float: left;
          font-size:9px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(128,128,128,1);

        }
        .info{
          float: right;
          width:13px;
          height:13px;
          background:rgba(255,5,0,0.8);
          border-radius:50%;
          font-size:10px;
          font-family:PingFang SC;
          font-weight:500;
          color:rgba(255,255,255,1);
          text-align: center;
        }
      }
      }

    }
  }
</style>
