<template>
  <div>
  <div class="red_color">
    <div class="gift_imgtop"><img src="../images/img/shop/blak.png" /></div>
    <div class="gift_topwen"><span class="rw_x3">软文</span></div>
  </div>

  <div class="rw_topwenzi">热哦是个怕身边拼接滴感觉哦iwjogiiwo给我</div>

  <div class="rw_zuozhe">
    <img src="../images/img/article/f4.jpg" />
    <p class="rw_x1">汤</p>
    <p class="rw_x2">惠商天下</p>
  </div>

  <div class="rw_ruanwen">软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文
    软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文
    软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文
    软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文
    软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文
    软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文
    软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文软文
  </div>

  <div class="rw_dp">
    <div class="rw_dp1"><img src="../images/img/article/sc.png">收藏</div>
    <div class="rw_dp2"><img src="../images/img/article/sc.png">好文章</div>
    <div class="rw_dp3"><img src="../images/img/article/sc.png">投诉</div>
  </div>

  <div class="rw_x4"></div>

  <div class="rw_down">
    <img src="../images/img/article/f4.jpg" />
    <p class="rw_x1">汤</p>
    <p class="rw_x2">惠商天下</p>
    <span class="rw_gz">关注公众号</span>
    <div class="rw_x5"></div>
  </div>

  </div>
</template>

<script>
</script>

<style lang="less" scoped>
  .red_color {
    background: -webkit-gradient(linear, 0 100%, 100% 0, from(#f84a4d), to(#f08364));
  }
  .gift_imgtop {
    position: absolute;
    float: left;
    margin-top: 1.5875rem;
    margin-left: 1.875rem;
    img {
      width: 1.2rem;
      height: 2rem;
    }
  }
  .gift_topwen {
    height: 5rem;
    font-size: 2rem;
    display: flex;
    justify-content: center;
    border-bottom: 1px solid #666666;
    span {
      padding-top: 0px;
      margin-top: 0.74rem;
    }
  }
  .rw_x1 {
    font-weight: 800;
  }
  .rw_x2 {
    color: #666666;
    padding-bottom: 1.5rem;
    padding-top: 1rem;
  }
  .rw_x3 {
    color: #ffffff;
  }
  .rw_x4 {
    height: 5rem;
  }
  .rw_topwenzi {
    margin: 0 auto;
    width: 80%;
    text-align: center;
    font-size: 2rem;
    font-weight: 800;
    padding-bottom: 5rem;
  }
  .rw_zuozhe {
    position: relative;
    padding-top: 5rem;
    width: 90%;
    margin: 1rem auto;
    flex-direction: column;
    box-shadow: 0 0 2rem #d8d8d8;
    img {
      position: absolute;
      top: -2.5rem;
      margin-left: 50%;
      transform: translateX(-50%);
      height: 5.5rem;
      width: 5.5rem;
      border-radius: 100rem;
    }
    p {
      font-size: 1.8rem;
      display: flex;
      justify-content: center;
    }
  }
  .rw_ruanwen {
    padding-top: 4rem;
    margin: 0 auto;
    width: 90%;
    font-size: 1.5rem;
  }
  .rw_dp {
    margin-top: 2rem;
    display: flex;
    font-size: 1rem;
    img {
      height: 1rem;
      margin-left: 0.5rem;
    }
  }
  .rw_dp1 {
    width: 4rem;
    border: 1px solid black;
    border-radius: 2rem;
    margin-left: 20%;
  }
  .rw_dp2 {
    width: 5rem;
    border: 1px solid black;
    border-radius: 2rem;
    margin-left: 17%;
    transform: translateX(-50%);
  }
  .rw_dp3 {
    width: 4rem;
    border: 1px solid black;
    border-radius: 2rem;
    margin-left: 16%;
    transform: translateX(-100%);
  }
  .rw_down {
    position: relative;
    padding-top: 3rem;
    width: 90%;
    margin: 0 auto;
    flex-direction: column;
    background-color: #f0f0f0;
    border-top-left-radius: 2rem;
    img {
      position: absolute;
      top: -2.5rem;
      margin-left: 50%;
      transform: translateX(-50%);
      height: 5.5rem;
      width: 5.5rem;
      border-radius: 100rem;
      border: 0.3rem solid #ffffff;
    }
    p {
      font-size: 1.8rem;
      display: flex;
      justify-content: center;
    }
  }
  .rw_gz {
    font-size: 1rem;
    display: flex;
    justify-content: center;
    background-color: #e5e5e5;
    width: 20%;
    margin-left: 50%;
    transform: translateX(-50%);
    border-radius: 2rem;
  }
  .rw_x5 {
    height: 2rem;
  }

</style>
