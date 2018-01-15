<template>
  <div>
  <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
    <el-tab-pane label="权限组管理" name="first">
      <el-row>
        <el-col :span="3">
          <el-button type="primary" @click="open3" v-if="!getHiddenButtons(4002)">添加权限组</el-button>
          <el-menu mode="vertical"  class="el-menu-vertical-demo">
            <el-menu-item-group>
              <template v-for="item in RoleGroups">
              <el-menu-item :index="tostring(item.id)" @click="select(item.id)"  v-if="item.roleState===1">
                <i class="el-icon-edit" @click="editname(item)"></i>
                {{item.roleName}}
                </el-menu-item>
              </template>
            </el-menu-item-group>
          </el-menu>
        </el-col>
        <el-col :span="21">
          <template>
            <el-tree
              :data="tree"
              show-checkbox
              ref="tree"
              node-key="id"
              :default-checked-keys="checkkeys"
              check-strictly="true"
              :props="defaultProps">
            </el-tree>
            <el-button type="primary" style="float: right" @click="CommitAdd()">保存权限</el-button>
            <el-button style="float: right;margin-right: 10px" @click="cancelAdd()">取消修改</el-button>
          </template>

        </el-col>
      </el-row>
    </el-tab-pane>
    <el-tab-pane label="配置管理" name="second">
      <el-table :data="RoleGroups" border style="width: 100%">
        <el-table-column prop="roleName" label="权限组名称"></el-table-column>
        <el-table-column :formatter="state" label="权限组状态"></el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="120">
          <template scope="scope">
            <el-button
              @click.native.prevent="startRow(scope.$index, RoleGroups)"
              type="text"
              size="mini">
              启用
            </el-button>
            <el-button
              @click.native.prevent="stopRow(scope.$index, RoleGroups)"
              type="text"
              size="mini">
              停用
            </el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-tab-pane>
    <el-tab-pane label="角色管理" name="third" >

        <el-select v-model="username"  clearable  filterable placeholder="用户名">
          <el-option
            v-for="item in allusers"
            :label="item.username"
            :value="item.id">
          </el-option>
        </el-select>
        <el-button type="primary" @click="selectuser" >查询</el-button>

      <el-table :data="users" border style="width: 481px">
        <el-table-column prop="username" label="用户名" width="180"></el-table-column>
        <el-table-column :formatter="roles" label="权限组" width="180"></el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template scope="scope">
            <el-button
              @click.native.prevent="EditRole(scope.$index, users)"
              type="text"
              size="mini">
              分配权限组
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-tab-pane>
  </el-tabs>
    <el-dialog title="分配权限组" :visible.sync="dialogFormVisible2">
    <el-form>
      <el-form-item label="权限组" >
        <el-select v-model="rolename" placeholder="请选择">
          <template v-for="item in rols">
            <el-option
              :key="item.id"
              :label="item.roleName"
              :value="item.id">
            </el-option>
          </template>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible2 = false">取 消</el-button>
      <el-button type="primary" @click="SubmitEditRole()">确 定</el-button>
    </div>
    </el-dialog>
  </div>
</template>

<style>

</style>

<script>
  import {ERR_MSG, MENUID, URL} from "../../assets/constants/constant-common";
  import utils from "../../assets/util/utils";
  import {mixin}from '../../assets/util/mixin';
    export default {
        data() {
          return{
            activeName2:"first",
            RoleGroups:[],
            RoleMenus:[],
            dialogFormVisible:false,
            dialogFormVisible2:false,
            roleid:null,
            menuids:null,
            form:"",
            users:[],
            allusers:[],
            username:null,
            rolename:null,
            Edituserid:null,
            Editindex:null,
            elmenuid:0,
            rols:[],
            tree:[],
            defaultProps:{
              children: 'children',
              label: 'label'
            },
            checkkeys:[]
          }

        },
      created:function(){
        this.$http.post(URL+MENUID.getmenutree).then(function (response) {
          let data = response.body;
          this.tree=data;

        }, function (response) {

        });
        this.$http.post(URL+MENUID.getrolename).then(function (response) {

          let data = response.body.data;

          this.RoleGroups = data;
          this.rols=data;


        }, function (response) {

        });


      },
      mixins: [mixin],
      methods: {
        tostring(id){
          var str = String(id);
          return str;
        },
        SubmitEditRole(){
          let id=this.rolename;
          this.$http.post(URL+MENUID.editUserRole, {"id":this.Edituserid,"roleid":id}).then(function (response) {
            this.users[this.Editindex].roleid=id;
            this.$message({
              type: 'success',
              message: '修改成功'

            });
            this.dialogFormVisible2=false
          }, function (response) {

          });

        },
        EditRole(index,rowmenu){
          this.Edituserid=rowmenu[index].userid;
          this.Editindex=index;
          this.dialogFormVisible2=true

        },
        selectuser(){
          if(this.username!=""){
            this.$http.post(URL+MENUID.getUserName+this.username).then(function (response) {
              let data = response.body.data;
              this.users = [];
              this.users=data;

            }, function (response) {

            });
          }else{
            this.loaduser();
          }

        },
        handleClick(tab, event) {
          let name=tab._props.name
          if(name==="third"){
            this.loaduser()
          }
        },
        loaduser:function () {
          this.$http.post(URL+MENUID.getUserName+0).then(function (response) {
            let data = response.body.data;
            this.users = data;
            this.allusers=data;

          }, function (response) {

          });


        },
        roles:function( row, column, cellValue) {
          let roleid=row.roleid;
          if(roleid==null||roleid==""){
            return "暂未分配"
          }else{
            let groupa=this.RoleGroups;
            for(let i=0;i<groupa.length;i++){
              if(roleid==groupa[i].id){
                return groupa[i].roleName
              }

            }

          }

        },
        state:function (row, column, cellValue) {
          if(row.roleState===1){
            return "启用"
          }else{
            return "停用"
          }


        },
        cancelAdd(){
          if(this.roleid!=null){
            this.select(this.roleid);
          }


        },
        CommitAdd(){
          if(this.roleid==null){
            this.$message({
              type: 'error',
              message: '请先选择权限组'

            })
            return false
          }
          this.menuids=this.$refs.tree.getCheckedKeys();
          this.$http.post(URL+MENUID.saveRolebyId,{"roleid": this.roleid,"menuids":this.menuids}).then(function (response) {
            this.$message({
              type: 'success',
              message: '修改成功'

            });

          }, function (response) {

          });

        },
        select(index) {

          this.$http.post(URL+MENUID.getrolebyid,{"roleid": index}).then(function (response) {
            let data = response.body.data;
            this.$refs.tree.setCheckedKeys([])
            for(let i=0;i<data.length;i++){
              this.$refs.tree.setChecked(data[i],true)
            }

            this.roleid=index;

          }, function (response) {
          });
        },
        editname(item){
          this.$prompt('请输入权限组名称', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            inputValue:item.roleName
          }).then(({value}) => {
            item.roleName=value;
            this.$http.post(URL+MENUID.editRole, item).then(function (response) {
              this.$message({
                type: 'success',
                message: '修改成功'

              });
            }, function (response) {


            });

          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消修改'
            });
          });
        },
        open3() {
          this.$prompt('请输入权限组名称', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',

          }).then(({value}) => {
            this.$http.post(URL+MENUID.addRole, {"rolename": value,"rolestate":1}).then(function (response) {

              let rel=response.body.rel;
              if(!rel){
                this.$message({
                  type: 'error',
                  message: '创建失败'

                });
                return
              }else{
                let data = response.body.data;
                this.RoleGroups.push(data)
              }




            }, function (response) {

            });
            this.$message({
              type: 'success',
              message: '创建成功'

            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消输入'
            });
          });
        },
        deleteRow(index,rowmenu){
          this.$confirm('此操作将移除该菜单权限, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let menuid=rowmenu[index].menuid
            let roleid=this.roleid;
            this.$http.post(URL, utils.getRequestData(MENUID.DeleteMediaHandler, {"roleid": roleid,"menuid":menuid})).then(function (response) {
              let data = response.data;
              if (!utils.isSuccess(data.state)) return;
              this.RoleMenus.splice(index,1);


            }, function (response) {

            });
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
            return false

          });

        },
        startRow(index,rowmenu){
          this.$confirm('此操作将启用该权限组, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let infos=rowmenu[index]
            infos.roleState=1;
            this.$http.post(URL+MENUID.editRole, infos).then(function (response) {
              this.RoleGroups[index].roleState=1;
              this.$message({
                type: 'success',
                message: '启用成功!'
              });

            }, function (response) {

            });

          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消'
            });
            return false

          });

        },
        stopRow(index,rowmenu){
          this.$confirm('此操作将停用该权限组, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let infos=rowmenu[index]
            infos.roleState=0;
            this.$http.post(URL+MENUID.editRole, infos).then(function (response) {
              this.RoleGroups[index].roleState=0;


            }, function (response) {


            });
            this.$message({
              type: 'success',
              message: '停用成功!'
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消'
            });
            return false

          });

        },


      }
    }
</script>
