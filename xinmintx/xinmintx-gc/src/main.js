import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import './plugin/vant.js'
import axios from "axios";
import 'lib-flexible/flexible.js';
import './assets/css/global.css'
Vue.config.productionTip = false;


axios.defaults.baseURL = "http://114.55.103.26:8085/";
axios.interceptors.request.use(
  function(config) {
    config.headers.Authorization = localStorage.getItem("token");
    return config;
  },
  function(error) {
    return Promise.reject(error);
  }
);
// 请求拦截器
axios.interceptors.request.use(
  config => {
    
    const token = store.state.token;
    token && (config.headers.Authorization = token);
    return config;
  },
  error => {
    return Promise.error(error);
  }
);

// 响应拦截器
axios.interceptors.response.use(
  response => {
    if (response.code === 200) {
      return Promise.resolve(response);
    } else {
      return Promise.reject(response);
    }
  },
  // 服务器状态码不是200的情况
  error => {
    if (error.response.code !== 200) {
      this.$toast("获取数据失败");
    }
  }
);

Vue.prototype.$axios = axios;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
