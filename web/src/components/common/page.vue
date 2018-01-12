<template>
  <div class="block">
    <!--<span class="demonstration">完整功能</span>-->
    <!---->
    <el-pagination
      @size-change="handleSizeChange"
      :current-page="currentPage"
      @current-change="handleCurrentChange"
      :page-sizes="[10, 30, 50, 100, 300, 500]"
      :page-size="size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.allCount" >
    </el-pagination>
  </div>
</template>
<script>
  import utils from '../../assets/util/utils';
  import {URL,MENUID,ERR_MSG} from '../../assets/constants/constant-common';
  import {condition} from '../../assets/util/conMixin';
  export default {
    data() {
      return {
        size:10,
        current:1
      };
    },
    props: ['page','query'],
    computed: {
      formData:function(){
        let temp2 = {};
        let temp={current:1,allCount:1,size:10};
        temp.current = this.page.current;
        temp.allCount = this.page.allCount;
        temp.size = this.page.size;
        temp2 = utils.merge(this.query, temp);
//        let data = utils.getRequestData(this.page.menuId,JSON.stringify(temp2));
        let data = temp2;
        this.memoryCondition(temp2);
        return data;
      }
    },
    created: function () {
      this.currentPage = this.page.current;
      this.size = this.page.size;
      this.$http.post(URL+this.page.menuId,this.formData).then(function (response) {
        let data = response.data;
        this.page.allCount = data.count;
        this.page.items = data.result;
        //   this.$emit('changeItems');
      }, function (response) {
        // error callback
      });
    },
    mixins:[condition],
    methods: {
      handleSizeChange(val) {
        if(this.page.size!=val){
          this.page.size=val;
          this.getList();

        }
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.currentPage = val;
//        if(this.currentPage != this.page.current){
        this.page.current = this.currentPage;
        this.getList();
//          this.$emit("Titems",this.page);
//        }

      },
      getList:function(){
        this.$http.post(URL+this.page.menuId,this.formData).then(function (response) {
          let data = response.body;
          if(this.page.allCount!==data.count){
            this.page.current=1;
            this.page.allCount=data.count;
          }
          this.page.items = data.result;
        }, function (response) {
          // error callback
        });
      }
    }

  }
</script>
