/**
 * Created by C168 on 2016/12/19.
 */

import Vue from 'vue'
import Vuex from 'vuex'
import event from './event';
import theme from './theme';
Vue.use(Vuex)
const store = new Vuex.Store({
  state: {
    user: null,
    menus: null,
    allMenus: null,
    msg: null,
    productInfo: null,
    productCount: null,

  },
  modules: {
    event,
    theme
  },
  getters: {
    getUser: function (state) {
      return state.user
    },
    getMenus: function (state) {
      return state.menus
    },
    getAllMenus: function (state) {
      return state.allMenus
    },
    getMsg: function (state) {
      return state.msg
    },
    getProductInfo: function (state) {
      return state.productInfo
    },
    getProductCount: function (state) {
      return state.productCount
    },

  },
  mutations: {
    setUser: function (state, user) {
      // console.log("setUser");
      state.user = user
    },
    setMenus: function (state, menus) {
      // console.log("setMenus");
      state.menus = menus
    },
    setAllMenus: function (state, allMenus) {
      // console.log("setAllMenus");
      state.allMenus = allMenus
    },
    setMsg: function (state, msg) {
      // console.log("setMsg");
      state.msg = msg
    },
    setProductInfo: function (state, productInfo) {
      // console.log("setMsg");
      state.productInfo = productInfo
    },
    setProductCount: function (state, productCount) {
      // console.log("setMsg");
      state.productCount = productCount
    },
    setLoginOut: function (state) {
      // console.log("setLoginOut");
      state.user = null
      state.menus = null
      state.allMenus = null
    }
  },

  strict: process.env.NODE_ENV !== 'production' // 是否开启严格模式
})

export default store
