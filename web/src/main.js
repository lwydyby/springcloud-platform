/* eslint-disable no-new */
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import store from './store/store'
import VueRouter from 'vue-router'
import Vuex from 'vuex'
import vueEventCalendar from 'vue-event-calendar'
import VueResource from 'vue-resource'
import 'vue-event-calendar/dist/style.css'
import VueLocalStorage from 'vue-localstorage'
// import fullCalendar from 'vue-fullcalendar'
import $ from 'jquery'
// import utils from './assets/util/utils'


import utils from './assets/util/utils'
import {MENUID, PRODUCT_INFO, URL, URL_CENTER_URL} from "./assets/constants/constant-common";





Vue.config.productionTip = false
// Vue.component('full-calendar', fullCalendar)
Vue.use(vueEventCalendar, {locale: 'en', color: '#4fc08d'})// locale can be 'zh' or 'en'/
Vue.use(ElementUI)
Vue.use(Vuex)
Vue.use(VueRouter)
Vue.use(VueResource)
Vue.use(VueLocalStorage, {
  productInfo: null,
  productCount: null,
  flag: false//created computed members from your variable declarations
})


Vue.filter('notNull', function (dt) {
  if (dt === null || dt === undefined || dt === '') {
    return '无'
  }

  return dt
})
Vue.filter('formatDate', function (dt, type) {
  if (dt === null || dt === undefined) {
    return '暂无'
  }
  let result = ''
  if (type === undefined || type == null) {
    // YY-mm-dd HH:ii:ss
    result = utils.getDtStr(dt)
    return result
  }
  switch (type) {
    case 2:
      // YY-mm-dd
      result = utils.getDtStr2(dt)
      break
    case 3:
      //yy-MM-dd HH:mm
      result = utils.getDtStr3(dt);
      break
    case 4:
      if (dt.length === 0) {
        return ""
      } else {
        let string = "";
        for (let i = 0; i < dt.length; i++) {
          string += utils.getDtStr2(dt[i]) + "至"

        }
        result = string.substring(0, string.length - 1);

      }
      break;
  }
  return result
})
Vue.filter('fileFormat', function (dt) {
  if (dt === null || dt === undefined) {
    return '无'
  }
  dt = dt.substring(dt.lastIndexOf('/') + 1)
  return dt
})
Vue.filter('thransemoney', function (dt) {
  if (dt === null || dt === undefined) {
    return '无'
  }
  dt = utils.thranemoney(dt)
  return dt
})
const router = new VueRouter({
  mode: 'history',
  base: __dirname,
  routes: [
    {
      path: '/',
      component: function (resolve) {
        require(['./components/System/menuManage.vue'], resolve)
      }
    },
    {
      path: '/login',
      component: function (resolve) {
        require(['./components/login.vue'], resolve)
      }
    },
    {
      path: '/hello',
      component: function (resolve) {
        require(['./components/Hello.vue'], resolve)
      }
    },
    {
      path: '/System/menuManage',
      component: function (resolve) {
        require(['./components/System/menuManage.vue'], resolve)
      }
    }
  ]
})

let ress = ""




new Vue({
  el: '#app',
  router: router,
  store: store,
  template: '<App/>',
  components: { App },
  render: h => h(App)
})
