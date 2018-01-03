<template>
  <el-dialog size="tiny" title="删除确认框" :visible.sync="dialogDeleteVisible" @close="close">
    <template v-if="deleteObj">
      <el-form>
        <div align="center">
          <p>你确定要删除【{{ deleteMsg }}】吗？</p>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="close">取 消</el-button>
        <el-button type="danger" @click="deleteCon">确 定</el-button>
      </div>
    </template>
  </el-dialog>
</template>
<style>
</style>
<script>
    export default{
      props:['deleteObj','deleteMsg','dialogDeleteVisible'],
      data(){
          return{
          }
      },
      methods:{
        deleteCon:function(){
          this.$emit('deleteCon',1);
        },
        close:function(){
          this.$emit('deleteCon',2);
        }
      }
    }
</script>
