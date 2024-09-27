import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

//1.引入axios并标准化axios
import axios from 'axios'

//每次请求都会添加这个
axios.defaults.baseURL='http://localhost:8083'
//进行标准化
Vue.prototype.$http=axios

//2.引入 elementui的相关库
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

router.beforeEach((to, from, next) => {
  if(to.path == '/'){
        next();
      }
  let token = window.sessionStorage.getItem('token');
  if (to.path != '/Hemo' && !token) {
    next({
      path: '/'
    })
  } else {
    if (to.path == '/Hemo' && token) {
      next('/Hemo')
    } else {     
      next()
    }
  }
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
