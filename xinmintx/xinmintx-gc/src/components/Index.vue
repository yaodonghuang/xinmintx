<template>
  <div class="index">
    <router-view></router-view>
    <div class="idou"></div>
    <van-tabbar
      v-model="active"
      active-color="#000"
      inactive-color="#959090"
      route
    >
      <van-tabbar-item to="/sy">
        <span>首页</span>
        <img
          slot="icon"
          slot-scope="props"
          :src="props.active ? icon.active : icon.inactive"
        />
      </van-tabbar-item>
      <van-tabbar-item to="/welfare">
        <span>福利社</span>
        <img
          slot="icon"
          slot-scope="props"
          :src="props.active ? icon.active1 : icon.inactive1"
        />
      </van-tabbar-item>
      <van-tabbar-item to="/ShopingIndex">
        <span>购物车</span>
        <img
          slot="icon"
          slot-scope="props"
          :src="props.active ? icon.active2 : icon.inactive2"
        />
      </van-tabbar-item>
      <van-tabbar-item to="/myself">
        <span>我的</span>
        <img
          slot="icon"
          slot-scope="props"
          :src="props.active ? icon.active3 : icon.inactive3"
        />
      </van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      value: "欢迎",
      icon: {
        active:
          "http://hstx.oss-cn-shanghai.aliyuncs.com/vue/e67f36a20a631ffc627617dcf64c780.png",
        inactive:
          "https://hstx.oss-cn-shanghai.aliyuncs.com/vue/215e61cf7d3cd2b7d8a0a0677b14693.png",
        active1:
          "https://hstx.oss-cn-shanghai.aliyuncs.com/vue/0409b7b1376933fcbd12f738b4910f8.png",
        inactive1:
          "https://hstx.oss-cn-shanghai.aliyuncs.com/vue/9759536ed3474c1858a2393a598b2e7.png",
        active2:
          "https://hstx.oss-cn-shanghai.aliyuncs.com/vue/af372a31c9bbcc10edc63156d6c8bde.png",
        inactive2:
          "https://hstx.oss-cn-shanghai.aliyuncs.com/vue/b6618f21ee61685d9f9bf4c79712f4f.png",
        active3:
          "https://hstx.oss-cn-shanghai.aliyuncs.com/vue/f134c61e41d0462ad11c5cde4005556.png",
        inactive3:
          "https://hstx.oss-cn-shanghai.aliyuncs.com/vue/5d0fd02cc90e031fe772b1792a1c282.png"
      }
    };
  }
};
</script>

<style lang="less">
.idou {
  padding-bottom: 25px;
}
</style>
