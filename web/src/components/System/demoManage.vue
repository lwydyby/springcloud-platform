<template>
  <div>
      <el-form :inline="true" :model="queryForm" class="demo-form-inline">
          <el-form-item>
              <el-date-picker v-model="queryForm.time" type="date" placeholder="选择日期"></el-date-picker>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" @click="query">查询</el-button>
              <el-button type="primary" @click="handleCreate()">新建</el-button>
          </el-form-item>
      </el-form>

    <el-table :data="page.items"   border style="width: 100%">

      <el-table-column align="center" label="id" width="65">
      <template scope="scope">
        <span>{{scope.row.id}}</span>
      </template>
    </el-table-column>
        <el-table-column min-width="200px" align="center" label="名称">
      <template scope="scope">
        <span>{{scope.row.name}}</span>
      </template>
    </el-table-column>
        <el-table-column min-width="200px" align="center" label="创建时间">
      <template scope="scope">
        <span>{{scope.row.createdate}}</span>
      </template>
    </el-table-column>
        <el-table-column min-width="200px" align="center" label="备注">
      <template scope="scope">
        <span>{{scope.row.remark}}</span>
      </template>
    </el-table-column>
        <el-table-column fixed="right" align="center" label="操作" width="150">
          <template scope="scope">
        <el-button  size="mini" type="primary" @click="handleEdit(scope.index,scope.row)">编辑
        </el-button>
        <el-button  size="mini" type="danger" @click="handleDelete(scope.index,scope.row)">删除
        </el-button>
      </template>
      </el-table-column>
    </el-table>
      <page :page.once="page" :query.once="queryForm" ref="page"></page>
      <deleteDialog v-on:deleteCon="deleteSubmit" :deleteObj="deleteObj" deleteMsg="" :dialogDeleteVisible="dialogDeleteFormVisible"></deleteDialog>

      <el-dialog title="新建" :visible.sync="dialogFormVisible">
      <el-form :model="formData" :rules="rules" ref="form" label-width="120px">
        <el-form-item label="名称" prop="name">
      <el-input v-model="formData.name" placeholder="请输入"></el-input>
    </el-form-item>
        <el-form-item label="创建时间" prop="createdate">
      <el-input v-model="formData.createdate" placeholder="请输入"></el-input>
    </el-form-item>
        <el-form-item label="备注" prop="remark">
      <el-input v-model="formData.remark" placeholder="请输入"></el-input>
    </el-form-item>
        </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible=false">取 消</el-button>
        <el-button  type="primary" @click="formValidate()" :loading="isloading">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import Vue from 'vue'
    import page from '../common/page.vue'
    import utils from '../../assets/util/utils';
    import {mixin}from '../../assets/util/mixin';
    import {ERR_MSG, MENUID, URL} from "../../assets/constants/constant-common"

    export default {
    data() {
      return {
          createData: {formId:-1,
    id : null,
    name : null,
    createdate : null,
    remark : null,
    deleteFlag : null
        },
        rules: {
    name: [
  {
    required: true,
    message: '请输入',
    trigger: 'blur'
  },
  {
    min: 3,
    max: 20,
    message: '长度在 3 到 20 个字符',
    trigger: 'blur'
  }
],   createdate: [
  {
    required: true,
    message: '请输入',
    trigger: 'blur'
  }
],   remark: [
  {
    required: true,
    message: '请输入',
    trigger: 'blur'
  },
  {
    min: 3,
    max: 20,
    message: '长度在 3 到 20 个字符',
    trigger: 'blur'
  }
]        },
        queryForm:{time:null,
                id:null,
        },
                menuId:MENUID.getpageDemo,
                deleteUrl:URL+MENUID.deleteDemo,



      }
    },
    created() {
        this.formData=utils.clone(this.createData);

    },
      mixins: [mixin],
        methods: {
            formSubmit:function () {
                let id=this.formData.formId;
                if(id===-1){
                    this.$http.post(URL+MENUID.addDemo,this.formData).then(function (response){
                    this.query();
                    this.dialogFormVisible=false;
                    this.setMsg("创建成功");
                    }, function (response) {
                        this.isloading=false;

                    });
                }else{
                    this.$http.post(URL+MENUID.editDemo,this.formData).then(function (response) {

                    this.query();
                    this.setMsg("修改成功");
                    this.dialogFormVisible = false
                    }, function (response) {
                    this.isloading=false;

                    });
                }

            },


    }
  }
</script>
