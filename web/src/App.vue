<template>
  <div id="App">
    <!--<img src="./assets/logo.png">
    <router-view></router-view>-->
    <!--<Test class="main"></Test>-->
    <template v-if="!goLogin">
      <router-view></router-view>
    </template>
    <template v-else>
      <login></login>
    </template>

  </div>
</template>
<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
<script>
//  import Test from './Test.vue'
  import Vue from 'vue';
  import store from './store/store';
  import menus from './menus.vue'
  import login from './components/login.vue'
  import {URL,ERR_MSG,USER,MENUID,MENUS,ALL_MENUS} from './assets/constants/constant-common';
  import { mapGetters,mapMutations } from 'vuex'
  import {GET_USER,SET_USER,GET_MSG,SET_MSG,GET_MENUS,SET_MENUS,GET_ALLMENUS,SET_ALLMENUS,SET_LOGIN_OUT} from './store/mutations'
  import utils from './assets/util/utils';
  export default {
    data(){
      return{
        nav:{modelName:'',menuNav:''},
        msg: 'Hello Vue23!',
        goLogin:true,

      }
    },
    name: 'app',
    store,
    computed:{
      ...mapGetters([
        GET_USER,GET_MENUS,GET_ALLMENUS,GET_MSG,
      ])
    },
    beforeCreate:function (){
//      Vue.config.debug = true;
      Vue.config.debug = false;
      Vue.http.options.emulateJSON = false;
      Vue.http.options.emulateHTTP = true;
    },
    created:function(){
      if(this.getUser==null){
        let o = localStorage.getItem(USER);
        if(o !=null ){
          this.setUser(JSON.parse(o));
        }
      }
      if(localStorage.getItem("token")){
        this.goLogin=false;
      }else{
        this.goLogin=true;
      }
      //ajax 拦截
      Vue.http.interceptors.push((request, next)  => {
        switch(request.method){
          case 'POST':
            if(localStorage.getItem("token")){
              request.headers.set('token', localStorage.getItem("token"))
            }
            break;
          case 'GET':
            break;
        }
        next((response) => {


        });
      });
    },
    components: {
      login,menus
    },
    methods: {
      ...mapMutations([
        SET_USER,SET_MSG,SET_MENUS,SET_ALLMENUS,SET_LOGIN_OUT
      ]),
      loginNotify:function(){
      },
      go: function (path, data) {
        this.$route.router.go({
          path: path,
          query: data
        }
        )
      }
    }
  }


</script>

<style>
  #App{
    padding: 0px;
    margin: 0px;
  }
  #main {
    float: right;
    width: 85%;
  }
  #side {
    float: left;
    width: 15%;
  }
  .menu {
    background-color: #1f2d3d;
    height: 150%;
    position: absolute;
  }
  #router-view {
    background-color: #ecf0f5;
    left: 15%;
    height:139.2%;
    padding: 20px;
    top: 60px;
    position: absolute;/*很重要，不能随便写*/
    width: 83%;
  }
  .el-badge {
    margin-top: -3px;
    margin-right: 40px;
  }
  .main_left {
    float: left;
    padding-top: 20px;
  }
  .main_right {
    float: right;
    padding-right: 20px;
    padding-top: 20px;
  }
</style>
