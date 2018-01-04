<template>
  <div id="App">
    <!--<img src="./assets/logo.png">
    <router-view></router-view>-->
    <!--<Test class="main"></Test>-->
    <template v-if="!goLogin">
      <el-container style="height: 966px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <menus :menus="getMenus"></menus>
        </el-aside>

        <el-container>
          <el-header style="text-align: right; font-size: 12px">
            <span style="font-size: 20px;line-height: 60px">{{getUser.name}}</span>
            <span style="font-size: 20px;line-height: 60px" @click="logout">登出</span>
          </el-header>

          <el-main>
            <router-view></router-view>
          </el-main>
        </el-container>
      </el-container>


    </template>
    <template v-else>
      <login  v-on:loginchange="loginchange"></login>
    </template>

  </div>
</template>
<style>
  a{
    text-decoration: none;
  }
</style>
<script>

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
      if(localStorage.getItem("token")){
        this.goLogin=false;
      }else{
        this.goLogin=true;
      }
      if(this.getUser==null&&!this.goLogin){
        let o = localStorage.getItem(USER);
        if(o !=null ){
          this.setUser(JSON.parse(o));
        }
      }
      if(this.getMenus==null&&!this.goLogin){
        let o = localStorage.getItem(MENUS);
        if(o !=null ){
          this.setMenus(JSON.parse(o));
        }
      }
      if(this.getAllMenus==null&&!this.goLogin){
        let o = localStorage.getItem(ALL_MENUS);
        if(o !=null ){
          this.setAllMenus(JSON.parse(o));
        }
      }
      this.$router.beforeEach((to, from, next) => {
        let path = to.path;
        let na = next;
        if (path === "/login") {
          next()
        } else {
          next()

        }


      })
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
            if(response.status===401){
              this.$message.error('登录超时');
              this.go("/login")
              localStorage.clear();
              this.setLoginOut
              this.goLogin=true;
            }
          if(response.status===402){
            this.$message.error('编辑保存失败，请核实数据后重试');
          }
          if(response.status===403){
            this.$message.error('您无权限进行该操作');
          }

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
        this.$router.push({
            path: path,
            query: data
          }
        );

      },
      logout:function () {
        localStorage.clear();
        this.setLoginOut
        this.go("/login")
        this.goLogin=true;
      },
      loginchange(flag){
        this.goLogin=flag;

      }
    }
  }


</script>

<style>


</style>
