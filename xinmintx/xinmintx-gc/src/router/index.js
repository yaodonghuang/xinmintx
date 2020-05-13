import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/index/Index'
import Login from '../views/index/Login'
import Wlyc from '../views/get/Error'
import Ysh from '../views/get/Index'
import Sh from '../views/service/Sh'
import Tk from '../views/service/Tk'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/Login'
  },
  {
    path: '/index',
    name: 'Index',
    component: Index
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/wlyc',
    name: 'Wlyc',
    component: Wlyc
  },
  {
    path: '/ysh',
    name: 'Ysh',
    component: Ysh
  },
  {
    path: '/sh',
    name: 'Sh',
    component: Sh
  },
  {
    path: '/tk',
    name: 'Tk',
    component: Tk
  }
]

const router = new VueRouter({
  routes
})

export default router
