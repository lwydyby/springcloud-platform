/**
 * vuex的混合
 */

import store from '../../store/store';

var storeMixin = {
  data(){
    return {}
  },
  store,
  computed:{
    getMessa:function(){
      let msg = this.$store.getters.getMessa;
      msg = (msg== null)? '':msg;
      if(this.showS == this.showPage){
        return msg;
      }else {
        return '';
      }

    },
    getUser:function(){
      return this.$store.getters.getUser;
    },
    getMenus:function(){
      return this.$store.getters.getMenus;
    },
    getAllMenus:function(){
      return this.$store.getters.getAllMenus;
    },

  },
  methods:{
    addUser:function(user){
      this.$store.dispatch('setUser',user);
    },
    addMsg:function (msg) {
      this.$store.dispatch('setMsg',msg);
    },
    addMenus: function (menus) {
      this.$store.dispatch('setMenus',menus);
    },
    addAllMenus: function (allMenus) {
      this.$store.dispatch('setAllMenus',allMenus);
    },
  }
};

export {storeMixin}
