/**
 * Created by C168 on 2016/12/19.
 */

import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
  state: {
    user: null,
    menus: null,
    allMenus: null,
    msg: null,
    paths: null,
    productCount: null,

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
    getPaths: function (state) {
      return state.paths
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
    setPaths: function (state, paths) {
      // console.log("setMsg");
      state.paths = paths
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
