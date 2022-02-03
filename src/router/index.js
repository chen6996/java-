import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import denglu from '../views/denglu.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/Home',
    name: 'Home',
    component: Home
  },
  {
    path:'/',
    component:denglu
  }
]
// router.beforeEach((to, from, next) => {
//   if(to.path == '/'){
//         next();
//       }
//   if (to.matched.some(res => res.meta.requireAuth)) {// 判断是否需要登录权限
//     if (localStorage.getItem('token')) {// 判断是否登录
//       next()
//     } else {// 没登录则跳转到登录界面
//       next({
//         path: '/',
//         query: {redirect: to.fullPath}
//       })
//     }
//   } else {
//     next()
//   }
// })


const router = new VueRouter({
  routes
})

export default router
