<template>
  <div v-if="getMsg" align="center">
    <!--<b><el-alert :title="getMsg" type="success"  @close="close"></el-alert></b>-->
  </div>
</template>
<style>
</style>
<script>
  import { mapGetters,mapMutations } from 'vuex'
  import {GET_MSG,SET_MSG} from '../../store/mutations'
  export default{
    data(){
      return{
      }
    },
    computed:{
      ...mapGetters([
        GET_MSG,
      ]),
    },
    methods:{
      ...mapMutations([
        SET_MSG
      ]),
      close:function () {
//        console.log("close");
        this.setMsg(null);
      }
    }
  }
</script>
