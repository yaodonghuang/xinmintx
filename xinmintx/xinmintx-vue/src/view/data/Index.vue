<template>
<div>
  <div class="bg_color">
    <div class="gift_imgtop"><img src="../../images/img/gift/black.png" @click="$router.back(-1)" /></div>
    <div class="gift_topwen"><span>DATA</span></div>
  </div>
  <div class="top">
    <div><span class="top_s1">今日红利（元）</span></div>
    <div class="top_div1"><span class="top_s2">88574</span></div>
    <div class="top_div2"><span class="top_s3">查看所有明细</span></div>
    <div class="top_div3"><p class="top_p1">DATA总量（值）</p><p class="top_p2">{{this.data}}</p></div>
  </div>
  <div class="fx"><img class="img2" src="../../images/img/data/lb.png"/><span>分享给朋友能提升DATA值哦！</span></div>
</div>
</template>

<script>
export default {
  data () {
    return {
      data: ''
    }
  },
  mounted () {
    var token = localStorage.getItem('token')
    this.$axios({
      url: 'http://114.55.103.26:8085/api/member/accountNum',
      methods: 'post',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }).then(res => {
      this.data = res.data.data
    })
  }
}
</script>

<style lang="less" scoped>
  .bg_color {
    background-color: #ffffff;
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
  .top{
    background-color: #ffffff;
  }
  .top_s1{
    font-size: 10px;
  }
  .top_div1{
    width: 100%;
    text-align: center;
  }
  .top_s2{
    font-size: 27px;
    font-weight: 1000;
    line-height: 130px;
  }
  .top_div2{
    width: 100%;
    text-align: center;
  }
  .top_s3{
    border-radius: 30px;
    background-color: #FD593C;
    color: #ffffff;
    line-height: 30px;
    padding-top: 10px;
    padding-bottom: 10px;
    padding-right: 20px;
    padding-left: 20px;
  }
  .top_div3{
    padding-top: 10px;
    width: 100%;
    text-align: center;
    padding-bottom: 10px;
  }
  .top_p1{
    font-size: 10px;
  }
  .top_p2{
    font-size: 10px;
  }
  .fx{
    padding-top: 10px;
    text-align: center;
  }
  .img2{
    height: 14px;
    width: 14px;
  }
</style>
