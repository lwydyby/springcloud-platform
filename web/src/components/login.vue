<template>
  <div class="content-cha">
    <div v-bind:style="{'margin-top': marginTop + 'px'}">
      <el-row>
        <el-col style="width:500px" v-bind:style="{'margin-left': marginLeft + 'px'}">
          <el-form :model="formData" :rules="rules" ref="formData" label-width="100px" class="demo-ruleForm1">
            <el-row>
              <el-col :offset="9"><h2 style="color:#ffffff">欢迎登陆业务平台</h2></el-col>
            </el-row>
            <el-form-item prop="username">
              <el-input type="text" v-model="formData.username" auto-complete="off" placeholder="登录名"
                        @keyup.13.native="show($event)"></el-input>
            </el-form-item>
            <el-form-item prop="passwords">
              <el-input type="password" v-model="formData.passwords" auto-complete="off" placeholder="密码"
                        @keyup.13.native="show($event)"></el-input>
            </el-form-item>
            <el-form-item>
              <el-row>
                <el-col >
                  <el-button type="primary" @click="submitForm('formData')" style="width: 100%"
                             :loading="loading">登录
                  </el-button>

                </el-col>
              </el-row>

            </el-form-item>
          </el-form>
        </el-col>
      </el-row>

    </div>
  </div>
</template>
<style>

  .demo-ruleForm1{
    padding-top: 50px;
    padding-right: 80px;
    padding-bottom: 50px;
    background-color: rgba(54, 106, 163, 0.5);
  }
  .content-cha{
    width: 100%;
    height: 100%;    position: fixed;
    background: url(../../static/images/login/u10504.jpg) fixed center center  no-repeat ;
    background-size: cover;
  }
</style>


<script>
  import {ERR_MSG, USER, MENUS, MENUID, ALL_MENUS, URL_CENTER_URL, URL} from '../assets/constants/constant-common';
  import {mixin} from '../assets/util/mixin';
  import store from '../store/store';
  import utils from '../assets/util/utils';
  import Des from '../assets/util/des';


  export default {
      data() {
        return{
          submitData:{username:"",password:""},
          createData:{username:"",passwords:""},
          rules: {
            username: [
              { required: true, message: '请输入用户名', trigger: 'blur' },
            ],
            passwords:[
              { required: true, message: '请输入密码', trigger: 'blur' }
            ]
          },
          loading: false,


          marginTop: (document.documentElement.clientHeight - 400) / 2,
          marginLeft: (document.documentElement.clientWidth - 500) / 2,
          user:{id:null,name:null},
        }

        },
      mixins: [mixin],
      created:function () {
        localStorage.clear();
        this.setLoginOut
        this.loginchange(true);
        this.formData=utils.clone(this.createData);
        let brow=$.browser;
        var explorer = window.navigator.userAgent ;
        if(explorer.indexOf("MSIE") >= 0) {
          if (brow.version < 10) {
            this.setMsg("你好！IE9及以下浏览器不支持该系统正常运行！");
          }
        }

      },
      store,

    mounted () {
        window.onresize = () => {
          return (() => {
            this.marginTop = (document.documentElement.clientHeight - 400) / 2;
            this.marginLeft = (document.documentElement.clientWidth - 500) / 2;
          })()
        }
    },
    props:['flag'],
      methods:{
        show: function (ev) {
          if (ev.keyCode === 13) {
            this.login();
          }
        },
        openFullScreen2() {
          const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });
          setTimeout(() => {
            loading.close();
          }, 1000);
        },
        login:function(){
          this.setMsg(null);
          localStorage.clear();

          this.submitData.password = this.getDesPwd(this.formData.passwords);
          this.submitData.username = this.formData.username;
//          this.openFullScreen2()
          this.loading = true
          this.$http.post(URL_CENTER_URL+'gameley-auth/oauth/token',this.submitData).then(function (response) {
            let data=response.data.data;
            this.loading = false;
            if(response.body.status===200){
              localStorage.setItem("token", data.token);
              this.user.id=data.id;
              this.user.name=data.name;
              this.setUser(this.user);
              localStorage.setItem(USER, JSON.stringify(this.user));
              this.$http.post(URL_CENTER_URL+'user-api/menu/getallmenu').then(function (response) {
                let Base64 = require('js-base64').Base64;
                let clientid=response.body.data;
                let payload=clientid.substring(clientid.indexOf(".")+1,clientid.lastIndexOf("."));
                let basedata=JSON.parse(Base64.decode(payload));
                let menudata=basedata.menu;
                let elementcode=basedata.element;
                this.setAllMenus(elementcode);
                this.setMenus(menudata);
                localStorage.setItem("client",clientid);
                this.go('/System/menuManage',null);
                this.loginchange(false);

              }, function (response) {

              });




            }else{
              this.setMsg(response.body.message);
            }

          }, function (response) {
            this.loading = false
            this.setMsg(null);
          });
        },
        loginchange(flag){
          this.$emit('loginchange',flag);
        },
        transformToFormat: function(sNodes) {
          var i,l,
            key = "menu_id",
            parentKey = "menu_pid",
            childKey = "sub_menus";
          var   visible = "menu_visible";
          if (!key || key=="" || !sNodes) return [];
          let len = sNodes.length;
          if (utils.isArray(sNodes)) {
            for (let k=len-1; k>=0; k--) {
              if(sNodes[k][visible]==0){
                sNodes.splice(k, 1);
              }
            }
            len = sNodes.length;
            if(len==0){
              return null;
            }
            var r = [];
            var tmpMap = {};
            for (i=0, l=sNodes.length; i<l; i++) {
              tmpMap[sNodes[i][key]] = sNodes[i];
            }
            for (i=0, l=sNodes.length; i<l; i++) {
              if (tmpMap[sNodes[i][parentKey]] && sNodes[i][key] != sNodes[i][parentKey]) {
                if (!tmpMap[sNodes[i][parentKey]][childKey])
                  tmpMap[sNodes[i][parentKey]][childKey] = [];
                tmpMap[sNodes[i][parentKey]][childKey].push(sNodes[i]);
              } else {
                r.push(sNodes[i]);
              }
            }
            return r;
          }else {
            return [sNodes];
          }
        },
        getDesPwd:function(pwd){
          let desPwd = '';
          let key1 = "xmob";
          let key2 = "X";
          let key3 = "MOB";
          desPwd = Des.strEnc(pwd,key1,key2,key3);
          return desPwd;
        },
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
             this.login();
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        }
      }

    }
</script>
