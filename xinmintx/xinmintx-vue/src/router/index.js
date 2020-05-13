import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Index from '../components/Index.vue'
import Shop from '../components/Shop'
import User from '../view/user/User'
import Text from '../components/Text'
import Clock from '../components/Clock'
import Order from '../components/Order'
import Gift from '../components/Gift'
import Collection from '../components/Collection'
import Balance from '../components/Balance'
import Assemble from '../components/Assemble'
import Article from '../components/Article'
import AgencyShow from '../components/AgencyShow'
import ShopJoin from '../components/ShopJoin'
import PayPassworld from '../view/user/PayPassworld'
import Hyk from '../components/Hyk.vue'
import Myself from '../components/Myself.vue'
import Merchants from '../components/Merchants'
import Set from '../view/user/Set'
import Address from '../view/user/Address'
import Sy from '../view/home/Sy.vue'
import Index_data from '../view/data/Index'
// import Cart from '../components/Cart.vue'
import ShopingIndex from '../view/shoppingCart/ShopingIndex'
import Security from '../view/user/Security'
import Orders from '../view/shoppingCart/Orders'
import Newaddress from '../view/user/Newaddress'
Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/merchants',
      name: 'merchants',
      component: Merchants
    },
    {
      path: '/hyk',
      name: 'hyk',
      component: Hyk
    },
    {
      path: '/index',
      name: 'index',
      component: Index,
      children: [
        { path: '/', redirect: '/sy' },
        { path: '/sy', name: 'sy', component: Sy },
        { path: '/myself', name: 'myself', component: Myself },
        { path: '/shopingIndex', name: 'shopingIndex', component: ShopingIndex }
      ]
    },
    {
      path: '/shop',
      name: 'Shop',
      component: Shop
    },
    {
      path: '/user',
      name: 'User',
      component: User
    },
    {
      path: '/text',
      name: 'Text',
      component: Text
    },
    {
      path: '/clock',
      name: 'Clock',
      component: Clock
    },
    {
      path: '/order',
      name: 'Order',
      component: Order
    },
    {
      path: '/gift',
      name: 'Gift',
      component: Gift
    },
    {
      path: '/collection',
      name: 'Collection',
      component: Collection
    },
    {
      path: '/balance',
      name: 'Balance',
      component: Balance
    },
    {
      path: '/assemble',
      name: 'Assemble',
      component: Assemble
    },
    {
      path: '/article',
      name: 'Article',
      component: Article
    },
    {
      path: '/agencyShow',
      name: 'AgencyShow',
      component: AgencyShow
    },
    {
      path: '/shopJoin',
      name: 'ShopJoin',
      component: ShopJoin
    },
    {
      path: '/payPassworld',
      name: 'PayPassworld',
      component: PayPassworld
    },
    {
      path: '/set',
      name: 'Set',
      component: Set
    },
    {
      path: '/address',
      name: 'address',
      component: Address
    },
    {
      path: '/newAddress',
      name: 'newAddress',
      component: Newaddress
    },
    {
      path: '/security',
      name: 'Security',
      component: Security
    },
    {
      path: '/orders',
      name: 'Orders',
      component: Orders
    },
    {
      path: '/index_data',
      name: 'Index_data',
      component: Index_data
    }
  ]
})
// router.beforeEach((to, from, next) => {
//   // console.log(to)
//   let token = localStorage.getItem('token')
//   console.log(token)
//   if (to.path === '/login' || token || to.path === '/sy') {
//     let data = qs.stringify({ 'token': token })
//     this.$axios({
//       url: 'http://114.55.103.26:8085/member/login/getMember',
//       method: 'post',
//       headers: {
//         'Content-Type': 'application/x-www-form-urlencoded'
//       },
//       data: data
//     }).then((res) => {
//       console.log(res)
//       if (res.data.code === 200) {
//         // router.push('/index')
//         next()
//       }
//     }).catch((err) => {
//       console.log(err)
//     })
//   } else {
//     next('/login')
//   }
// })
export default router
