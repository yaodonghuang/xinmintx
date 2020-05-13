import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import 'vant/lib/index.css'
import axios from 'axios'
import vant from 'vant'
import qs from 'qs'
import { AddressList} from 'vant'
Vue.use(AddressList)
Vue.use(vant)
Vue.config.productionTip = false
Vue.prototype.$axios = axios
axios.defaults.baseURL = 'http://114.55.103.26:8085/'
axios.interceptors.request.use(function (config) {
  // config.headers.token = localStorage.getItem('token')
  config.headers.token = '9cc56b81-858e-4ebd-b3f6-7a309fedf347'
  return config
}, function (error) {
  return Promise.reject(error)
})

Vue.filter('dateFormate',function (val) {
  var date = new Date(val.replace(/-/g, '/'))
  let m = date.getMonth() + 1
  if (m < 10) {
    m = '0' + m
  }
  return m
})
Vue.filter('dateFormated',function (val) {
  var date = new Date(val.replace(/-/g, '/'))
  let d = date.getDate()
  return d
})
new Vue({
  render: h => h(App),
  router
}).$mount('#app')
