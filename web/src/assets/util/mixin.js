/**
 * 前端混合使用对象
 * 2016-12-12
 */
import {
 URL, MENUID, ERR_MSG,
} from '../constants/constant-common';
import { mapGetters,mapMutations } from 'vuex'
import {
  GET_USER, SET_USER, GET_MSG, SET_MSG, GET_MENUS, SET_MENUS, GET_ALLMENUS, SET_ALLMENUS,
  GET_PRODUCTCOUNT, GET_PRODUCTINFO, SET_PRODUCTCOUNT, SET_PRODUCTINFO
} from '../../store/mutations'


import alertMsg from '../../components/common/alertMsg.vue';
import deleteDialog from '../../components/common/deleteDialog.vue'
import page from '../../components/common/page.vue';
import utils from './utils';
import chartUtil from './chartUtil';
import Vue from 'vue'
import Enum from "es6-enum";

var mixin = {
  data(){
    return {
      items:[],
      page:{current:1,allCount:1,size:10,menuId:0,items:[]},
      queryItems:null,
      buttonHidden:[],
      formData:null,
      tmpFormData:null,
      dialogTitle:'添加',
      updateIndex:-1,
      isloading:false,
      dialogFormVisible: false,
      dialogDeleteFormVisible:false,
      //dialogDeleteVisible:true,
      deleteObj:null,
      deleteMsg:'',
      dateType:4,
      legendData:[],
    }
  },
  components:{
    alertMsg, page, deleteDialog,
  },
  created: function(){
    this.formData=utils.clone(this.createData);
    this.page.menuId = this.menuId;
  },
  computed:{
    ...mapGetters([
      GET_USER, GET_MENUS, GET_ALLMENUS, GET_MSG, GET_PRODUCTCOUNT, GET_PRODUCTINFO
    ]),
  },
  methods:{
    ...mapMutations([
      SET_USER, SET_MENUS, SET_ALLMENUS, SET_PRODUCTCOUNT, SET_PRODUCTINFO
    ]),
    setMsg:function(msg){
      if(msg==null){
        return;
      }
      this.$message({
        showClose: true,
        message: msg,
        type: 'warning',
        duration:2000
      });
    },
    deleteSubmit:function(){
      console.log("进行删除操作");
    },
    callback:function(type){

    },
    /**
     * 分页获取数据的回调参数
     */
    getItems:function(){

    },
    /**
     * 处理日期时间范围组件调用查询
     */
    handleWithDate:function(type){
      chartUtil.mergeObjValue2First(this.$refs.page.query,this.queryForm);
      this.dateQuery(type);
    },
    /**
     *  与时间范围组件一起使用的查询方法
     */
    dateQuery:function(type){
      this.dateType = type;
      this.query();
      this.getChartsDataRequest(type);
    },
    query:function(){
      // console.log("mixin query");
      //debugger
      //this.sType = sType;
      this.$refs.page.getList();
    },
    go:function (path, data) {
      this.$router.push({
          path:path,
          query: data
        }
      );
    },
    goBack:function (){
      this.$router.history.go(-1);
    },
    /**
     * 获取相关菜单是否有相应权限
     * @param menuIds 要查询权限的菜单ID数组
     */
    getHiddenButtons: function(menuIds){
      this.buttonHidden = utils.getHiddenButtons(this.getAllMenus,menuIds);

    },
    handleReset() {
      this.$refs.ruleForm.resetFields();
    },
    handleCreate(flag){
      this.isloading=false;
      this.formData = utils.clone(this.createData);
      this.updateIndex = -1;
      this.formData.formId=-1;
      flag = true;
    },
    handleEdit(flag,index, row) {
      let data=utils.clone(this.createData)
      chartUtil.mergeAttrValue2First(data,row);
      this.isloading=false;
      this.formData = data;
      this.updateIndex = index;
      this.formData.formId=1;
      flag= true;
    },
    formValidate(){
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.isloading=true;
          this.formSubmit();
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(flag) {
      this.$refs['form'].resetFields();
      flag = false;
    },
    deleteResult:function (menuId,id,index) {
      this.$http.post(URL,utils.getRequestData(menuId,{"id":id})).then(function (response) {
        let data = response.data;
        if(!utils.isSuccess(data.state))return;
        if(this.page.menuId>0){//分页
          this.query();
        }else {//不分页
          this.items.splice(index, 1);
        }
        this.setMsg("删除成功");
      }, function (response) {
        this.setMsg(ERR_MSG);
      });
    },
    handleDelete(index, row) {
      this.deleteObj = row;
      this.dialogDeleteFormVisible = true;
    },
    deleteSubmit:function(type){
      if(type==1){
        this.$http.post(URL,utils.getRequestData(this.deleteUrl,{"id":this.deleteObj.id})).then(function (response) {
          let data = response.data;
          // if(this.page.menuId>0){//分页
            this.query();
          // }else {//不分页
          //   this.items.splice(this.updateIndex, 1);
          // }
          this.setMsg("删除成功");
        }, function (response) {
          this.setMsg(ERR_MSG);
        });
      }
      this.dialogDeleteFormVisible = false;
    },
    deletePicBase: function (data) {
      this.$http.post(URL, utils.getRequestData(MENUID.DeletePicHandler, data)).then(function (response) {
        let data = response.data;
        return data.sendimage

      }, function (response) {

        this.setMsg(ERR_MSG);
      });
    }
  },
  filters:{
    formatNum:function(num){

      if(!isNaN(num)){
        if(typeof num == 'number'){
          return num.toFixed(2);
        }
      }
      return num;
    },

  }
};

var spinButtons = {
  data(){
    return{
      spinButton:null,
    }
  },
  created: function(){
    //this.spinButton = Ladda.create(document.querySelector('.mt-ladda-btn'));
  },
};


export {mixin};
