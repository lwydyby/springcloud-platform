<template>
  <el-form-item>
  <el-date-picker
    v-model="queryForm.time"
    type="daterange"
    start-placeholder="开始日期"
    end-placeholder="结束日期">
  </el-date-picker>
  </el-form-item>

</template>
<style>
    body{
        background-color:#ff0000;
    }
</style>
<script>
  import utils from '../../assets/util/utils'
  import page from '../../components/common/page.vue';
    export default{
        props:['queryForm'],
        data(){
            return{
//              time:[]
            }
        },
        components:{
          page
        },
        created:function(){
          if(this.queryForm.time.length <=0){
            const  end = new Date();
            const  start = new Date();
            start.setTime(start.getTime()-3600*1000*24*7);
            this.queryForm.time.push(start);
            this.queryForm.time.push(end);
          }
        },
        methods:{
        }
    }
</script>
